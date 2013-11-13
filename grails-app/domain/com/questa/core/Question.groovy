package com.questa.core

import com.questa.cred.User

class Question {

    transient springSecurityService

    String title
    String description
    static belongsTo = [author: User]

    static hasMany = [tags: Tag, answers: Answer]

    def beforeInsert() {
        author = author ?: springSecurityService.currentUser as User
    }

    static constraints = {
        title blank: false, unique: true, minSize: 15, maxSize: 150
        description blank: false
        tags minSize: 1
    }

    static mapping = {
        description type: 'text'
        answers cascade: 'delete'
        sort id: 'desc'
    }
}
