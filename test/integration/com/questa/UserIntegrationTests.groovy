package com.questa

import com.questa.cred.User
import com.questa.core.Question
import com.questa.core.Answer
import com.questa.core.Tag

class UserIntegrationTests extends GroovyTestCase {
    User authorQuestion
    User authorAnswer

    Question question
    Answer answer
    Tag tag

    void setUp() {
        authorAnswer = User.build(email: 'email1')
        authorQuestion = User.build(email: 'email2')
        tag = Tag.build()
        question = Question.build(author: authorQuestion)
        question.addToTags(tag)
        answer = Answer.build(question: question, author: authorAnswer)

        assertEquals "Should be created 2 users", 2, User.count
        assertEquals "Should be created 1 tag", 1, Tag.count
        assertEquals "Should be created 1 question", 1, Question.count
        assertEquals "Should be created 1 answer", 1, Answer.count
    }

    void tearDown() {
        tag.delete()
        answer.delete()
        question.delete()
        authorQuestion.delete()
        authorAnswer.delete()
    }

    void testAuthorQuestionDeletion() {
        authorQuestion.delete()

        assertEquals "One user should be deleted", 1, User.count
        assertEquals "Tag should not be deleted", 1, Tag.count
        assertEquals "Question should be deleted", 0, Question.count
        assertEquals "Answer to removed question should be deleted", 0, Answer.count
    }

    void testAuthorAnswerDeletion() {
        authorAnswer.delete()

        assertEquals "One user should be deleted", 1, User.count
        assertEquals "Tag should not be deleted", 1, Tag.count
        assertEquals "Question should not be deleted", 1, Question.count
        assertEquals "Answer should be deleted", 0, Answer.count
    }
}
