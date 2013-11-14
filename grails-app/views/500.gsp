<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="error.title"/></title>
</head>

<body>
<g:if env="development">
    <g:renderException exception="${exception}"/>
</g:if>
<g:else>
    <div style="background: url('/images/tlen.jpg'); min-height: 956px">
        <h3 class="text-center"><g:message code="error.message"/></h3>
    </div>
</g:else>
</body>
</html>