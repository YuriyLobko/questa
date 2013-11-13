package com.questa.core

class QuestionService {
    def grailsApplication

    def getPage(Long page = 1, List<String> tagList = null) {
        Question.createCriteria().list(getPaginationParameters(page)) {
            tags {
                'in'('name', tagList)
            }
        }
    }

    def count() {

    }

    Question save(Question question, Long version) {
        println('own service method')
        if (question.version > version) {
            question.errors.rejectValue("version", "default.optimistic.locking.failure")
        }

        question.save()

        question
    }

    protected Map getPaginationParameters(Long page) {
        [
                offset: grailsApplication.config.grails.pagination.questionsPerPage.toLong() * (page - 1),
                max: grailsApplication.config.grails.pagination.questionsPerPage.toLong()
        ]
    }
}
