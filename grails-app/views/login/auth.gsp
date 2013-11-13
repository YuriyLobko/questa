<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="sign.in.title"/></title>
</head>

<body>
<r:require module="dialog"/>
<g:form url="${postUrl}" method="POST" class="form-dialog">
    <h2 class="form-dialog-heading"><g:message code="sign.in.header"/></h2>
    <g:textField name="j_username" class="form-control"
                 placeholder="${g.message(code: 'sign.in.email.field.placeholder')}" required="" autofocus=""/>
    <g:passwordField name="j_password" class="form-control"
                     placeholder="${g.message(code: 'sign.in.password.field.placeholder')}" required=""/>
    <label class="checkbox">
        <input type="checkbox" name="${rememberMeParameter}" value="remember-me"/> <g:message code='sign.in.remember.me.label'/>
    </label>
    <g:submitButton class="btn btn-lg btn-primary btn-block" name="signIn"
                    value="${g.message(code: 'sign.in.submit.button.label')}"/>
</g:form>
</body>
</html>