package com.questa.core



import grails.test.mixin.*

import com.questa.core.QuestionService

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(QuestionService)
class QuestionServiceTests {

    void testPaginationParameters() {
        def page = 3
        def expectedParameters = [
                offset: grailsApplication.config.grails.pagination.questionsPerPage.toLong() * 2,
                max: grailsApplication.config.grails.pagination.questionsPerPage.toLong()
        ]

        def parameters = service.getPaginationParameters(page)

        assertEquals(expectedParameters.offset, parameters.offset)
        assertEquals(expectedParameters.max, parameters.max)
    }
}
