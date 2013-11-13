<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main" />
    <title><g:message code="question.create.title"/></title>
</head>
<body>
<h3><g:message code="question.create.header"/></h3>
<g:form action="save">
<tmpl:form question="${question}"/>
<g:submitButton name="create" value="${g.message(code: 'question.create.button.label')}" class="btn btn-success"/>
</g:form>
</body>
</html>