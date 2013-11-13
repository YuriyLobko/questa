package com.questa.cred



import grails.test.mixin.*

import com.questa.cred.UserController
import grails.plugin.springsecurity.SpringSecurityService

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(UserController)
@Mock([User, Role])
class UserControllerTests {

    void setupClass() {
        mockDomain(User)
    }

    void testRegisterGET() {
        def model = controller.register(new User())

        assertNull model.user.id
        assertNull model.user.password
        assertNull model.user.email
        assertNull model.user.firstName
        assertNull model.user.lastName
        assertFalse model.user.hasErrors()
    }

    void testRegisterUserExists() {
        User.metaClass.encodePassword = { -> println('test') }

        def user = new User(email: 'existing.email@mail.com', firstName: 'John', lastName: 'Doe', password: 'pass').save(validate: false)
        params.password2 = 'one'

        request.method = "POST"

        def model = controller.register(new User(email: user.email, firstName: user.firstName, lastName: user.lastName, password: params.password2))

        assertTrue model.user.hasErrors()
        assertNotNull model.user.errors.getFieldErrors('email').find { 'user.email.unique.error' in it.codes }
        // should return model with previously inputted data
        assertEquals user.email, model.user.email
        assertEquals user.firstName, model.user.firstName
        assertEquals user.lastName, model.user.lastName
    }

    void testRegisterWithNonMatchingPasswords() {
        def user = new User(email: 'email@mail.com', firstName: 'John', lastName: 'Doe', password: 'one')

        params.password2 = 'two'

        request.method = "POST"

        def model = controller.register(user)

        assertNotNull model.user.errors.fieldErrors.find { it.code == 'user.password.repeat.missmatch.error' }
        // should return model with previously inputted data
        assertEquals model.user.email, user.email
        assertEquals model.user.firstName, user.firstName
        assertEquals model.user.lastName, user.lastName
    }
}
