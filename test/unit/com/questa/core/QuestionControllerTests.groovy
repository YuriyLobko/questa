package com.questa.core

import com.questa.cred.User
import grails.test.ControllerUnitTestCase
import grails.test.mixin.Mock
import javassist.NotFoundException
import grails.test.mixin.TestFor
import grails.gorm.PagedResultList
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletResponse

@TestFor(QuestionController)
@Mock([QuestionService, Question, User])
class QuestionControllerTests {
    void setUp() {
        def accessServiceMock = mockFor(UserAccessService, true)
        accessServiceMock.demand.hasAccessToQuestion(0..1) { Question q, String action -> true}
        controller.userAccessService = accessServiceMock.createMock()
    }

    void testListFirstPage() {
        def questionService = mockFor(QuestionService)
        def mockQuestionList = (0..20).collect { mockFor(Question) }
        def mockTotalCount = 234
        questionService.demand.getPage { Long page, String tag, String term -> mockQuestionList}
        questionService.demand.count {-> mockTotalCount }
        controller.questionService = questionService.createMock()

        def model = controller.list(null, null, null)

        assertEquals(model.questions, mockQuestionList)
        assert model.total == mockTotalCount
        assert model.page == 1
    }

    void testList() {
        def questionService = mockFor(QuestionService)
        def mockQuestionList = (0..20).collect { mockFor(Question) }
        def mockTotalCount = 234
        questionService.demand.getPage { Long page, String tag, String term -> mockQuestionList}
        questionService.demand.count {-> mockTotalCount }
        controller.questionService = questionService.createMock()

        def model = controller.list(3, null, null)

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
        def questionServiceMock = mockFor(QuestionService, true)
        questionServiceMock.demand.getAnswers(0..1) { Question question ->
            [list: [], total: 0]
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

        controller.edit(2)

        assertEquals HttpServletResponse.SC_NOT_FOUND, response.status
        questionMock.verify()
    }

    void testShowNotFound() {
        mockDomain(Question)
        def questionMock = mockFor(Question)
        questionMock.demand.static.get(1..1) { Long id ->
            null
        }

        controller.show(2)

        assertEquals HttpServletResponse.SC_NOT_FOUND, response.status
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
        questionServiceMock.demand.save(1..1) { Question q, Long version, String tags ->
            new Question()
        }

        controller.questionService = questionServiceMock.createMock()

        controller.save(new Question(), null, null)

        assertEquals("/question/show", response.redirectedUrl)
        questionServiceMock.verify()
    }
}
