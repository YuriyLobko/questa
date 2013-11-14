package com.questa.core

import com.questa.cred.User

class UserAccessService {
    def springSecurityService

    def hasAccessToQuestion(Question question, String action) {
        def access = true;
        User currentUser = springSecurityService.currentUser as User
        if (action in ['edit', 'save']) {
            access = currentUser.authorities?.any { it.authority == 'ROLE_ADMIN' } || currentUser?.id == question.authorId
        }

        access
    }
}
