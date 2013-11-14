<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main" />
    <title><g:message code="question.edit.title"/></title>
</head>
<body>
<h3><g:message code="question.edit.header"/></h3>
<g:form action="save" id="${question?.id}">
    <g:hiddenField name="version" value="${question?.version}"/>
    <tmpl:form question="${question}"/>
    <g:submitButton name="update" value="${g.message(code: 'question.create.button.label')}" class="btn btn-success"/>
</g:form>
</body>
</html>