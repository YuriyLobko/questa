package com.questa.core

import grails.plugin.cache.Cacheable
import grails.plugin.cache.CacheEvict
import grails.gorm.PagedResultList

class QuestionService {
    def grailsApplication

    def getPage(Long page) {
        Question.list(getPaginationParameters(page))
    }

    @Cacheable('questionCount')
    def count() {
        Question.count
    }

    @CacheEvict(value='questionCount')
    Question save(Question question, Long version) {
        if (question.version > version) {
            question.errors.rejectValue("version", "default.optimistic.locking.failure")
        }

        question.save()

        question
    }

    PagedResultList getAnswers(Long id, Long offset) {
        getAnswers(Question.load(id), offset)
    }

    PagedResultList getAnswers(Question question, Long offset = 0) {
        Answer.createCriteria().list(offset: offset, max: grailsApplication.config.grails.pagination.answersPerPage.toLong()) {
            eq('question', question)
        }
    }

    protected Map getPaginationParameters(Long page) {
        page = page ?: 1
        [
                offset: grailsApplication.config.grails.pagination.questionsPerPage.toLong() * (page - 1),
                max: grailsApplication.config.grails.pagination.questionsPerPage.toLong()
        ]
    }
}
