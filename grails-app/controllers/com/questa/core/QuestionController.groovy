package com.questa.core

import javassist.NotFoundException

import javax.servlet.http.HttpServletResponse

class QuestionController {
    static defaultAction = "list"

    static allowedMethods = [list: 'GET', create: 'GET', show: 'GET', edit: 'GET', answers: 'GET',
            save: 'POST', answer: 'POST']

    def questionService

    def list(Long page) {
        [questions: questionService.getPage(page), total: questionService.count(), page: page ?: 1]
    }

    def create() {
        [question: Question.newInstance(params)]
    }

    def show(Long id) {
        withQuestion(id) { Question question ->
            [question: question, answers: questionService.getAnswers(question,)]
        }
    }

    def edit(Long id) {
        withQuestion(id) { Question question ->
            [question: question]
        }
    }

    def save(Question question, Long id, Long version) {
        if (id) {
            question = withQuestion(id) { Question oldQuestion ->
                bindData(oldQuestion, question)
                oldQuestion
            } as Question
        }

        question = questionService.save(question, version)
        if (!question.hasErrors()) {
            redirect(action: 'show', id: question.id)
        } else {
            render(view: 'edit', model: [quesiton: question])
        }
    }

    def answer(Answer answer, Long id) {
        withQuestion(id) { Question question ->
            answer.question = question
            if (answer.save()) {
                render(template: 'answer', model: [answer: answer])
            } else {
                render(status: HttpServletResponse.SC_PRECONDITION_FAILED, model: [answer: answer])
            }
        }
    }

    def answers(Long id, Long offset) {
        def answers = questionService.getAnswers(id, offset)
        def displayedAnswersCount = offset + answers.size()
        if (answers.totalCount > displayedAnswersCount) {
            response.addIntHeader('more-answers-offset', displayedAnswersCount.toInteger())
        }

        render(template: 'answers', model: [answers: answers])
    }

    private def withQuestion = { Long id, Closure action ->
        def question = Question.get(id)
        if (question) {
            action.call question
        } else {
            if (request.getHeader('X-Requested-With') == 'XMLHttpRequest') {
                response.sendError(HttpServletResponse.SC_NOT_FOUND)
            } else {
                throw new NotFoundException("Question with id $id has not been found")
            }
        }
    }
}
