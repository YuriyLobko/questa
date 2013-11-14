package com.questa.core

import com.questa.cred.User

class UserAccessService {
    def springSecurityService

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
}
