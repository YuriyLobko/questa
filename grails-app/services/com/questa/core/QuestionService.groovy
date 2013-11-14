package com.questa.core

import com.questa.PaginationUtil
import com.questa.cred.User

class QuestionService {
    def grailsApplication
    def springSecurityService

    def getPage(Integer page, String searchTag, String query) {
        PaginationUtil.create(grailsApplication.config.grails.pagination.questionsPerPage.toInteger()) { Map params ->
            Question.createCriteria().list(params) {
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
        }.getPage(page)
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
}
