package com.questa.core

import com.questa.cred.User
import grails.plugin.cache.CacheEvict
import grails.plugin.cache.Cacheable

class QuestionService {
    def grailsApplication
    def springSecurityService

    def getPage(Long page, String searchTag, String query) {
        def questions = Question.createCriteria().list(getPaginationParameters(page)) {
            if (query) {
                ilike('title', "%$query%")
                ilike('description', "%$query%")
            }
            if (searchTag) {
                'tags' {
                    ilike('name', searchTag)
                }
            }
        }

        [list: questions, total: (long)Math.ceil((double)questions.totalCount/grailsApplication.config.grails.pagination.questionsPerPage.toLong())]
    }

    Question save(Question question, Long version, String tags) {
        if (question.version > version) {
            question.errors.rejectValue("version", "default.optimistic.locking.failure")
        }

        question.author = question.author ?: springSecurityService.currentUser as User
        question = bindTags(question, tags)
        question.save(flush: true)

        question
    }

    Question bindTags(Question question, String tagList) {
        question.tags?.collect()?.each {
            question.removeFromTags(it)
        }
        tagList?.split(',')?.each {
            if (it) {
                question.addToTags(Tag.findByName(it.toLowerCase()) ?: new Tag(name: it))
            }
        }

        question
    }

    Answer addAnswer(Question question, Answer answer) {
        answer.author = answer.author ?: springSecurityService.currentUser as User
        answer.question = question
        answer.save()

        answer
    }

    def getAnswers(Long id, Long offset) {
        getAnswers(Question.load(id), offset)
    }

    def getAnswers(Question question, Long offset = 0) {
        def max = grailsApplication.config.grails.pagination.answersPerPage.toLong()
        def answers = Answer.createCriteria().list(offset: offset, max: max) {
            eq('question', question)
        }

        [list: answers.toList(), total: answers.totalCount]
    }

    protected Map getPaginationParameters(Long page) {
        page = page ?: 1
        [
                offset: grailsApplication.config.grails.pagination.questionsPerPage.toLong() * (page - 1),
                max: grailsApplication.config.grails.pagination.questionsPerPage.toLong()
        ]
    }
}
