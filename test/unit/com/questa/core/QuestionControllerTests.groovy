package com.questa.core

import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import grails.plugin.springsecurity.SpringSecurityService
import com.questa.cred.User
import grails.test.MockUtils

@TestFor(QuestionController)
@Mock([QuestionService, Question, SpringSecurityService, User])
class QuestionControllerTests {

    def question

    void setupClass() {
        question = new Question()
        MockUtils.mockDomain(Question, [question])
    }

    void testListFirstPage() {
        def questionService = mockFor(QuestionService)
        def mockQuestionList = (0..20).collect { mockFor(Question) }
        def mockTotalCount = 234
        questionService.demand.getPage { Long page -> mockQuestionList}
        questionService.demand.count { -> mockTotalCount }
        controller.questionService = questionService.createMock()

        def model = controller.list(null)

        assertEquals(model.questions, mockQuestionList)
        assert model.total == mockTotalCount
        assert model.page == 1
    }

    void testList() {
        def questionService = mockFor(QuestionService)
        def mockQuestionList = (0..20).collect { mockFor(Question) }
        def mockTotalCount = 234
        questionService.demand.getPage { Long page -> mockQuestionList}
        questionService.demand.count { -> mockTotalCount }
        controller.questionService = questionService.createMock()

        def model = controller.list(3)

        assertEquals(model.questions, mockQuestionList)
        assert model.total == mockTotalCount
        assert model.page == 3

    }

    void testCreate() {
        def model = controller.create()

        assertNull(model.question.id)
        assertNull(model.question.version)
        assertNull(model.question.title)
        assertNull(model.question.description)
        assertNull(model.question.author)
        assertNull(model.question.tags)
    }

    void testEdit() {
        controller.edit(1)

        assertEquals(question, model.question)
    }

    void testEditNotFound() {
        controller.edit(2)

        assertEquals('/not-found', response.redirectedUrl)
    }

    void testShow() {
        controller.show(1)

        assertEquals(question, model.question)
    }

    void testShowNotFound() {
        controller.show(2)

        assertEquals('/not-found', response.redirectedUrl)
    }
}
