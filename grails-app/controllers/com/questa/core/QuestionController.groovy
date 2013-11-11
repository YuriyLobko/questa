package com.questa.core

class QuestionController {
    static defaultAction = "list"

    def questionService
    def springSecurityService

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

    def save() { }

    private def withQuestion = { Long id, Closure action ->
        def question = Question.get(id)
        if (question) {
            action.call question
        } else {
            redirect url: '/not-found'
        }
    }
}
