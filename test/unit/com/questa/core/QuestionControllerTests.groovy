package com.questa.core

import com.questa.cred.User
import grails.test.ControllerUnitTestCase
import grails.test.mixin.Mock
import javassist.NotFoundException
import grails.test.mixin.TestFor
import grails.gorm.PagedResultList

@TestFor(QuestionController)
@Mock([QuestionService, Question, User])
class QuestionControllerTests {
    void testListFirstPage() {
        def questionService = mockFor(QuestionService)
        def mockQuestionList = (0..20).collect { mockFor(Question) }
        def mockTotalCount = 234
        questionService.demand.getPage { Long page -> mockQuestionList}
        questionService.demand.count {-> mockTotalCount }
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
        questionService.demand.count {-> mockTotalCount }
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
        def dummyQuestion = new Question()
        mockDomain(Question)
        def questionMock = mockFor(Question)
        questionMock.demand.static.get(1..1) { Long id ->
            dummyQuestion
        }

        def model = controller.edit(1)

        assertEquals(dummyQuestion, model.question)
        questionMock.verify()
    }

    void testShow() {
        def dummyQuestion = new Question()

        mockDomain(Question)
        def questionMock = mockFor(Question)
        questionMock.demand.static.get(1..1) { Long id ->
            dummyQuestion
        }
        def questionServiceMock = mockFor(QuestionService)
        questionServiceMock.demand.getAnswers(1..1) { Question question, Long offset ->
            null
        }
        controller.questionService = questionServiceMock.createMock()

        def model = controller.show(1)

        assertEquals(dummyQuestion, model.question)
        questionMock.verify()
        questionServiceMock.verify()
    }

    void testEditNotFound() {
        mockDomain(Question)
        def questionMock = mockFor(Question)
        questionMock.demand.static.get(1..1) { Long id ->
            null
        }

        shouldFail(NotFoundException) {
            controller.edit(2)
        }
        questionMock.verify()
    }

    void testShowNotFound() {
        mockDomain(Question)
        def questionMock = mockFor(Question)
        questionMock.demand.static.get(1..1) { Long id ->
            null
        }

        shouldFail(NotFoundException) {
            controller.show(2)
        }
        questionMock.verify()
    }

    void testSave() {
        def questionMock = mockFor(Question)
        questionMock.demand.hasErrors {
            false
        }
        questionMock.demand.getId {
            1
        }
        def questionServiceMock = mockFor(QuestionService)
        questionServiceMock.demand.save(1..1) { Question q, Long version ->
            new Question()
        }

        controller.questionService = questionServiceMock.createMock()

        controller.save(new Question(), null, null)

        assertEquals("/question/show", response.redirectedUrl)
        questionServiceMock.verify()
    }
}
