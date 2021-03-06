<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main" />
    <title><g:message code="question.list.title"/> <g:message code="page.number.label" args="[page]"/></title>
</head>
<body>
    <g:each in="${questions}" var="question">
        <tmpl:/templates/questionItem question="${question}"/>
    </g:each>
    <div class="row">
        <div class="well well-sm text-center">
            <qa:pagination total="${total}" action="list" current="${page}" controller="question"/>
        </div>
    </div>
</body>
</html>