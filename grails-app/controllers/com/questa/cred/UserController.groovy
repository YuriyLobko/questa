package com.questa.cred

import javax.servlet.http.HttpServletResponse
import grails.validation.Validateable
import grails.plugin.springsecurity.annotation.Secured

class UserController {

    static allowedMethods = [register: ['GET', 'POST'], edit: 'GET', save: 'POST', changePassword: ['GET', 'POST']]

    def userService
    def springSecurityService

    @Secured('isAnonymous()')
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

    def edit(Long id) {
        if (id) {
            withUser(id) { User user ->
                [user: user]
            }
        } else {
            [user: springSecurityService.currentUser as User]
        }
    }

    def save(User user, Long id, Long version) {
        withUser(id) { User oldUser ->
            if (oldUser.version > version) {
                oldUser.errors.rejectValue("version", "default.optimistic.locking.failure")
            }

            bindData(oldUser, user)

            if (oldUser.save(flush: true)) {
                flash.success = (flash.success ?: []) << 'user.save.success.message'
            }

            render(view: 'edit', model: [user: oldUser])
        }
    }

    @Secured('isFullyAuthenticated()')
    def changePassword(ChangePasswordCommand command) {
        if (request.method == 'POST') {
            if (!command.hasErrors()) {
                withUser(command.id) { User user ->
                    user.password = command.password
                    if (user.save()) {
                        flash.success = (flash.success ?: []) << 'password.change.success.message'
                    } else {
                        flash.danger = (flash.danger ?: []) << 'password.change.failure.message'
                    }
                }
            } else {
                flash.danger = (flash.remove('danger') ?: []) + (command.errors.allErrors.collect { it.code })
            }
        }

        redirect(action: 'edit', id: command.id)
    }

    @Secured('ROLE_ADMIN')
    def list(Integer page, String q) {
        def users = userService.getPage(page, q, params.sort, params.order)
        [users: users.list, total: users.total, page: users.page]
    }

    @Secured('ROLE_ADMIN')
    def delete(Long id) {
        withUser(id) { User user ->
            user.delete()
            flash.success = (flash.remove('success') ?: []) << 'user.delete.success.message'
            redirect(action: 'list')
        }
    }

    private def withUser = { Long id, Closure action ->
        def user = User.get(id)
        if (user) {
            if (userService.hasAccessToUser(user, params.action)) {
                action.call user
            } else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED)
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND)
        }
    }

    @Validateable
    class ChangePasswordCommand {
        Long id
        String password
        String repassword

        static constraints = {
            password validator: { val, obj ->
                if (val != obj.repassword) {
                    return 'user.password.repeat.missmatch.error'
                }
            }
        }
    }
}
