<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main" />
    <title><g:message code="user.register.title"/></title>
</head>
<body>
<h3><g:message code="user.register.header"/></h3>
<g:form action="register" class="form-horizontal" role="form" method="POST">
    <tmpl:regForm user="${user}"/>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <g:submitButton name="create" value="${g.message(code: 'user.register.button.label')}" class="btn btn-success"/>
        </div>
    </div>
</g:form>
</body>
</html>