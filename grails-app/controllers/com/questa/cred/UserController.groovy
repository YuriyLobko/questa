package com.questa.cred

class UserController {

    static allowedMethods = [register: ['GET', 'POST']]

    def register(User user) {
        if (request.method == 'POST') {
            if (params.password2 != user.password) {
                user.errors.rejectValue('password', 'user.password.repeat.missmatch.error')
            } else {
                if (user.save()) {
                    redirect(url: '/')
                    return
                }
            }
        } else {
            user.clearErrors()
        }

        [user: user]
    }
}
