<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main" />
    <title><g:message code="user.list.title"/> <g:message code="page.number.label" args="[page]"/></title>
</head>
<body>
    <table class="table table-bordered">
        <thead>
            <g:sortableColumn action="list" defaultOrder="asc" property="firstName" titleKey="user.list.fullName.label"/>
            <g:sortableColumn action="list" property="email" titleKey="user.list.email.label"/>
            <th><g:message code="user.list.isAdmin.label"/></th>
            <th><g:message code="default.list.actions.label"/></th>
        </thead>
        <tbody>
            <g:each in="${users}" var="user">
                <tr>
                    <td>${user.fullName}</td>
                    <td>${user.email}</td>
                    <td><g:if test="${user.authorities.any { it.authority == 'ROLE_ADMIN'}}">
                        <span class="glyphicon glyphicon-ok"></span>
                    </g:if></td>
                    <td>
                        <g:form action="delete" id="${user.id}" class="form-inlines">
                        <g:link class="btn btn-primary" action="edit" id="${user.id}">
                            <g:message code="default.button.edit.label" />
                        </g:link>
                        <g:actionSubmit class="btn btn-danger"
                                        value="${message(code: 'default.button.delete.label', default: 'Delete')}" formnovalidate=""
                                        onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
                        </g:form>
                    </td>
                </tr>
            </g:each>
        </tbody>
        <tfooter>
            <tr>
                <td colspan="4" class="text-center">
                    <qa:pagination total="${total}" action="list" current="${page}" controller="user"/>
                </td>
            </tr>
        </tfooter>
    </table>
</body>
</html>