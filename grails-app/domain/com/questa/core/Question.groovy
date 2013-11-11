package com.questa.core

import com.questa.cred.User

class Question {

    String title
    String description
    static belongsTo = [author: User]

    static hasMany = [tags: Tag]

    static constraints = {
        title blank: false, unique: true, minSize: 15, maxSize: 150
        description blank: false
        tags minSize: 1
    }
}
