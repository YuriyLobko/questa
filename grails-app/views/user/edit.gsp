<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main" />
    <title><g:message code="user.edit.title" args="[user.fullName]"/></title>
</head>
<body>
    <tmpl:infoForm user="${user}"/>
    <tmpl:passwordForm user="${user}"/>
</body>
</html>