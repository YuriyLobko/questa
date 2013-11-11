package com.questa.core

import grails.test.mixin.*
import org.junit.*

@TestFor(Tag)
class TagTests {

    void testConstraints() {
        def uniqueValidName = 'grails-2.3#legal-characters'
        def illegalName = 'tag*with/illegal CharacterS!@#$%^&*()}{"[}]\';:?<>~'
        def emptyTagNull = new Tag(name: null)
        def emptyTagBlank = new Tag(name: '')
        def validTag = new Tag(name: uniqueName)
        def invalidTag = new Tag(name: illegalName)
        mockForConstraintsTests(Tag, [emptyTagBlank, emptyTagNull, validTag, invalidTag])

        // validation should fail if name is null ot blank
        assertFalse emptyTagNull.validate()
        assertFalse emptyTagBlank.validate()
        assertEquals 'nullable', emptyTagNull.errors['name']
        assertEquals 'blank', emptyTagBlank.errors['name']

        // validation should pass if name is specified and valid
        assertTrue validTag.validate()

        def duplicateTag = new Tag(name: uniqueValidName)

        // validation should fail if tag with specified name already exists
        assertFalse duplicateTag.validate()
        assertEquals 'unique', duplicateTag.errors['name']

        // validation should fail if tag name contains not only [a-z 0-9 # - .] characters
        assertFalse invalidTag.validate()
        assertEquals 'matches', invalidTag.errors['name']
    }

    void testNameSetter() {
        def name = 'UpperCaseTag'
        def tag = new Tag(name: name)

        // tag name should transform into lower case
        assertEquals name.toLowerCase(), tag.name
    }
}
