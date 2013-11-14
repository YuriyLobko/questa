package com.questa.core

import javax.servlet.http.HttpServletResponse
import grails.plugin.springsecurity.annotation.Secured
import javax.naming.ldap.PagedResultsControl
import grails.gorm.PagedResultList

class QuestionController {
    static defaultAction = "list"

    static allowedMethods = [list: 'GET', create: 'GET', show: 'GET', edit: 'GET', answers: 'GET',
            save: 'POST', answer: 'POST']

    def questionService
    def userAccessService

    def list(Long page, String q, String tag) {
        [questions: questionService.getPage(page, tag, q), total: questionService.count(), page: page ?: 1]
    }

    @Secured('isFullyAuthenticated()')
    def create() {
        [question: Question.newInstance(params)]
    }

    def show(Long id) {
        withQuestion(id) { Question question ->
            def answers = questionService.getAnswers(question)
            def displayedAnswersCount = answers.list?.size()
            if (answers.total > displayedAnswersCount) {
                response.addHeader('more-answers-offset', displayedAnswersCount.toString())
            }
            [question: question, answers: answers.list]
        }
    }

    @Secured('isFullyAuthenticated()')
    def edit(Long id) {
        withQuestion(id) { Question question ->
            [question: question]
        }
    }

    @Secured('isFullyAuthenticated()')
    def save(Question question, Long id, Long version) {
        if (id) {
            question = withQuestion(id) { Question oldQuestion ->
                bindData(oldQuestion, question)
                oldQuestion
            } as Question
        }

        question = questionService.save(question, version, params.tagNames)
        if (!question.hasErrors()) {
            redirect(action: 'show', id: question.id)
        } else {
            render(view: 'edit', model: [question: question])
        }
    }

    @Secured('isFullyAuthenticated()')
    def answer(Answer answer, Long id) {
        withQuestion(id) { Question question ->
            answer = questionService.addAnswer(question, answer)
            if (!answer.hasErrors()) {
                render(template: 'answer/item', model: [answer: answer])
            } else {
                render(template: 'answer/errors', model: [answer: answer], status: HttpServletResponse.SC_PRECONDITION_FAILED)
            }
        }
    }

    def answers(Long id, Long offset) {
        def answers = questionService.getAnswers(id, offset)
        def displayedAnswersCount = offset + answers.list.size()
        if (answers.total > displayedAnswersCount) {
            response.addHeader('more-answers-offset', displayedAnswersCount.toString())
        }

        render(template: 'answer/list', model: [answers: answers.list])
    }

    private def withQuestion = { Long id, Closure action ->
        def question = Question.get(id)
        if (question) {
            if (userAccessService.hasAccessToQuestion(question, params.action)) {
                action.call question
            } else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED)
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND)
        }
    }
}
