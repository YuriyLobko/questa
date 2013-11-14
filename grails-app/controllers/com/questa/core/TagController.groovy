package com.questa.core

import grails.converters.JSON

class TagController {
    def grailsApplication

    def typeahead(String id) {
        def tags = Tag.findAllByNameIlike("%$id%", [max: grailsApplication.config.grails.typeahead.max])
        render tags as JSON
    }
}
