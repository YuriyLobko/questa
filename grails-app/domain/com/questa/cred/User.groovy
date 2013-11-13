package com.questa.cred

import com.questa.core.Question
import com.questa.core.Answer

class User {

    transient springSecurityService

    String email
    String password
    String firstName
    String lastName
    boolean enabled = true
    boolean accountExpired = false
    boolean accountLocked = false
    boolean passwordExpired = false

    def String getFullName() {
        firstName + ' ' + lastName
    }

    static transients = ['springSecurityService', 'fullName']

    static hasMany = [questions: Question, answers: Answer]

    static constraints = {
        firstName blank: false
        lastName blank: false
        email blank: false, unique: true
        password blank: false
    }

    static mapping = {
        password column: '`password`'
        answers cascade: 'delete-orphan'
        questions cascade: 'delete-orphan'
    }

    Set<Role> getAuthorities() {
        UserRole.findAllByUser(this).collect { it.role } as Set
    }

    def beforeInsert() {
        encodePassword()
    }

    def beforeUpdate() {
        if (isDirty('password')) {
            encodePassword()
        }
    }

    protected void encodePassword() {
        password = springSecurityService.encodePassword(password)
    }
}
