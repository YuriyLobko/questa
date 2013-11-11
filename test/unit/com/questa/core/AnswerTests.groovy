package com.questa.core

import com.questa.cred.User
import grails.test.mixin.Mock
import grails.test.mixin.TestFor

@TestFor(Answer)
@Mock([User, Question])
class AnswerTests {

    private def answer = { Map attrs ->
        new Answer(
                content: attrs?.containsKey('content') ? attrs.content : 'Lorem ipsum dolor sit amet, consectetur adipisicing elit.',
                author: (attrs?.containsKey('author') ? attrs.author : mockDomain(User)) as User,
                question: (attrs?.containsKey('question') ? attrs.question : mockDomain(Question)) as Question,
        )
    }

    void testConstraints() {
        def shortAnswerContent = 'Answer with less than 30chars'
        def validAnswer = answer()
        def emptyAnswerNull = answer(content: null, author: null, question: null)
        def emptyAnswerBlank = answer(content: '')
        def shortAnswer = answer(content: shortAnswerContent)
        mockForConstraintsTests(Answer, [
                validAnswer,
                emptyAnswerBlank,
                emptyAnswerNull,
                shortAnswer
        ])

        assertFalse 'Validation for answer with null content or author should fail', emptyAnswerNull.validate()
        assertEquals 'Content shouldn\'t be nullable', 'nullable', emptyAnswerNull.errors['content']
        assertEquals 'Author shouldn\'t be nullable', 'nullable', emptyAnswerNull.errors['author']
        assertEquals 'Question shouldn\'t be nullable', 'nullable', emptyAnswerNull.errors['question']

        assertFalse 'Validation for answer with blank content should fail', emptyAnswerBlank.validate()
        assertEquals 'Content shouldn\'t be blank', 'blank', emptyAnswerBlank.errors['content']

        assertFalse 'Validation should fail if content is too short', shortAnswer.validate()
        assertEquals 'Content should contain 30 characters or more', 'minSize', shortAnswer.errors['content']
    }
}
