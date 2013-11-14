package com.questa.core

import com.questa.cred.User
import com.questa.PaginationUtil

class UserService {
    def springSecurityService
    def grailsApplication

    def hasAccessToQuestion(Question question, String action) {
        def hasAccess = true;
        User currentUser = springSecurityService.currentUser as User
        if (action in ['edit', 'save']) {
            hasAccess = currentUser.authorities?.any { it.authority == 'ROLE_ADMIN' } || currentUser?.id == question.authorId
        }

        hasAccess
    }
    
    def hasAccessToUser(User user, String action) {
        def hasAccess = true;
        User currentUser = springSecurityService.currentUser as User
        if (action in ['edit', 'save']) {
            hasAccess = currentUser.authorities?.any { it.authority == 'ROLE_ADMIN' } || currentUser?.id == user.id
        }

        hasAccess
    }

    def getPage(Integer page, String query, String sort, String direction) {
        sort = sort ?: 'firstName'
        direction = direction ?: 'asc'
        PaginationUtil.create(grailsApplication.config.grails.pagination.usersPerPage.toInteger()) { Map params ->
            User.createCriteria().list(params) {
                if (query) {
                    ilike('email', "%$query%")
                    ilike('firstName', "%$query%")
                    ilike('lastName', "%$query%")
                }
                order(sort, direction)
            }
        }.getPage(page)
    }
}
