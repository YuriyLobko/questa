package com.questa.core

import grails.test.mixin.TestFor

@TestFor(Tag)
class TagTests {

    void testConstraints() {
        def uniqueValidName = 'grails-2.3#legal-characters'
        def illegalName = 'tag*with/illegal CharacterS!@#$%^&*()}{"[}]\';:?<>~'
        def validTag = new Tag(name: uniqueValidName)
        def invalidTag = new Tag(name: illegalName)
        def emptyTagNull = new Tag(name: null)
        def emptyTagBlank = new Tag(name: '')
        mockForConstraintsTests(Tag, [emptyTagBlank, emptyTagNull, validTag, invalidTag])

        def duplicateTag = new Tag(name: uniqueValidName)

        assertFalse 'Validation should fail if name is null', emptyTagNull.validate()
        assertEquals 'Tag should contain error if name is null', 'nullable', emptyTagNull.errors['name']

        assertFalse 'Validation should fail if name is blank', emptyTagBlank.validate()
        assertEquals 'Tag should contain error if name is blank', 'blank', emptyTagBlank.errors['name']

        assertFalse 'Validation should fail if tag with specified name already exists', duplicateTag.validate()
        assertEquals 'Tag should contain error if another tag with the same name already exists', 'unique', duplicateTag.errors['name']

        assertFalse 'Validation should fail if tag name contains not only [a-z 0-9 # - .] characters', invalidTag.validate()
        assertEquals 'Tag should contain error if tag name contains not only [a-z 0-9 # - .] characters', 'matches', invalidTag.errors['name']
    }

    void testNameSetter() {
        def name = 'UpperCaseTag'
        def tag = new Tag(name: name)

        assertEquals 'Tag name should transform into lower case', name.toLowerCase(), tag.name
    }
}
