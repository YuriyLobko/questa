import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import com.questa.core.QuestionController
import com.questa.core.AnswerController
import grails.test.mixin.web.UrlMappingsUnitTestMixin

@TestFor(UrlMappings)
@Mock([QuestionController, AnswerController])
class UrlMappingsTests extends UrlMappingsUnitTestMixin {
    void testQuestionMappings() {
        assertForwardUrlMapping('/2', controller: 'question', action: 'list') {
            page = '2'
        }
        assertForwardUrlMapping('/', controller: 'question', action: 'list') {
            page = null
        }
        assertForwardUrlMapping('/create/question', controller: 'question', action: 'create')
        assertForwardUrlMapping('/edit/question/3', controller: 'question', action: 'edit') {
            id = '3'
        }
        assertForwardUrlMapping('/question/2', controller: 'question', action: 'show') {
            id = '2'
        }
        assertForwardUrlMapping('/save/question', controller: 'question', action: 'save')
    }

    void testSystemMappings() {
        // not found url
        assertForwardUrlMapping('/not-found', view: 'notFound')
    }
}
