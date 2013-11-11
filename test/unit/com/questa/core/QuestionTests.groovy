package com.questa.core

import com.questa.cred.User
import grails.test.mixin.Mock
import grails.test.mixin.TestFor

@TestFor(Question)
@Mock([User])
class QuestionTests {

    private def question = { Map attrs ->
        new Question(
                title: attrs?.containsKey('title') ? attrs.title : 'How to do smth.?',
                description: attrs?.containsKey('description') ? attrs.description : 'This detailed description of the question',
                author: (attrs?.containsKey('author') ? attrs.author : mockDomain(User)) as User,
                tags: (attrs?.containsKey('tags') ? attrs.tags : [mockDomain(Tag)]) as Set<Tag>
        )
    }

    void testConstraints() {
        def longTitle = (0..150).collect { 'a' }.join('')
        def shortTitle = 'LessThan15Chrs'
        def validQuestion = question()
        def emptyQuestionNull = question(title: null, description: null, author: null)
        def emptyQuestionBlank = question(title: '', description: '')
        def longTitleQuestion = question(title: longTitle)
        def shortTitleQuestion = question(title: shortTitle)
        def questionWithoutTags = question(tags: [])
        mockForConstraintsTests(Question, [
                validQuestion,
                emptyQuestionBlank,
                emptyQuestionNull,
                longTitleQuestion,
                shortTitleQuestion,
                questionWithoutTags
        ])

        def duplicateQuestion = question()

        assertFalse 'Validation for question with null title, description or author should fail', emptyQuestionNull.validate()
        assertEquals 'Title shouldn\'t be nullable', 'nullable', emptyQuestionNull.errors['title']
        assertEquals 'Description shouldn\'t be nullable', 'nullable', emptyQuestionNull.errors['description']
        assertEquals 'Author shouldn\'t be nullable', 'nullable', emptyQuestionNull.errors['author']

        assertFalse 'Validation for question with blank title or description should fail', emptyQuestionBlank.validate()
        assertEquals 'Title shouldn\'t be blank', 'blank', emptyQuestionBlank.errors['title']
        assertEquals 'Description shouldn\'t be nullable', 'blank', emptyQuestionBlank.errors['description']

        assertFalse 'Validation should fail if question with the same title already exists', duplicateQuestion.validate()
        assertEquals 'Title should be unique', 'unique', duplicateQuestion.errors['title']

        assertFalse 'Validation should fail if question is too short', shortTitleQuestion.validate()
        assertEquals 'Title should contain 15 characters or more', 'minSize', shortTitleQuestion.errors['title']

        assertFalse 'Validation should fail if question is too long', longTitleQuestion.validate()
        assertEquals 'Title should contain 150 characters or less', 'maxSize', longTitleQuestion.errors['title']

        assertFalse 'Validation should fail if question doesn\'t have tags', questionWithoutTags.validate()
        assertEquals 'Question should contain at least one tag', 'minSize', questionWithoutTags.errors['tags']
    }
}
