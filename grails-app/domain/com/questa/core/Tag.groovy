package com.questa.core

class Tag {

    String name

    void setName(String name) {
        this.name = name.toLowerCase()
    }

    static constraints = {
        name blank: false, unique: true, matches: /[0-9a-z\-\#\.]+/
    }
}
