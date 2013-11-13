package com.questa.core

import com.questa.cred.User

class Answer {

    transient springSecurityService

    String content

    static belongsTo = [author: User, question: Question]

    def beforeInsert() {
        author = author ?: springSecurityService.currentUser as User
    }

    static constraints = {
        content blank: false, minSize: 30
    }

    static mapping = {
        content type: 'text'
        sort id: 'asc'
    }
}
