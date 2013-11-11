package com.questa.core

import com.questa.cred.User

class Answer {

    String content

    static belongsTo = [author: User, question: Question]

    static constraints = {
        content blank: false, minSize: 30
    }

    static mapping = {
        content type: 'text'
    }
}
