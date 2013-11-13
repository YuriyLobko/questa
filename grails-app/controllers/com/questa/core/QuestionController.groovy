package com.questa.core

import javassist.NotFoundException

class QuestionController {
    static defaultAction = "list"

    def questionService

    def list(Long page) {
        [questions: questionService.getPage(page), total: questionService.count(), page: page ?: 1]
    }

    def create() {
        [question: Question.newInstance(params)]
    }

    def show(Long id) {
        withQuestion(id) { Question question ->
            [question: question]
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

    private def withQuestion = { Long id, Closure action ->
        def question = Question.get(id)
        if (question) {
            action.call question
        } else {
            throw new NotFoundException("Question with id $id has not been found")
        }
    }
}
