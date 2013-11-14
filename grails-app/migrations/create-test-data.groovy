import com.questa.cred.*
import com.questa.core.Tag
import com.questa.core.Question
import com.questa.core.Answer

databaseChangeLog = {
    changeSet(author: "Admin (manual)", id: "1384467758206-1") {
        grailsChange {
            change {
                //generate roles
                def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
                def admin = new User(email: 'admin@questa.com', firstName: 'Lobko', lastName: 'Yuriy', password: 'admin').save(flush: true)
                UserRole.create(admin, adminRole, true)
                def user = new User(email: 'user@questa.com', firstName: 'John', lastName: 'Doe', password: 'user').save(flush: true)

                // generate tags
                def tags = ['groovy', 'grails', 'scala', 'spring', 'java', 'hibernate', 'mysql', 'mongodb'].collect {
                    new Tag(name: it).save(flush: true)
                }

                def text ="Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
                // generate questions
                (0..100).each {
                    def idx = new Random().nextInt(tags.size())
                    String someString = "${tags.get(idx).name} - Generated question $it"
                    def question = new Question(title: someString, description: someString, author: admin, tags: tags[idx..(idx < tags.size()-4 ? idx+4 : tags.size()-1)].collect()).save(flush: true)
                    (0..(new Random().nextInt(20)+1)).each {
                        new Answer(content: text, author: user, question: question).save(flush: true)
                    }
                }
            }
        }
    }
}