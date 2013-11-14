<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main" />
    <title>${question.title}</title>
</head>
<body>
<div class="row">
    <h1 class="media-heading">${question.title}</h1>
    <div class="media">
        <a class="pull-left" href="#">
            <img class="media-object" src="http://placekitten.com/${63 + question.authorId}/${63 + question.authorId}" alt="${question.author.firstName}">
        </a>
        <div class="media-body">
            <div class="pull-right"><tmpl:/templates/tagsInline tags="${question.tags}"/></div>
            <h4 class="media-heading">${question.author.fullName}</h4>
            <p>${question.description.replaceAll('/\r\n|\r|\n|\n\r/', '<br />')}</p>
        </div>
    </div>
    <hr/>
</div>

<div class="answers row">
    <tmpl:answer/list answers="${answers}"/>
    <tmpl:answer/more id="${question.id}"/>
    <hr/>
</div>
<div class="row">
    <tmpl:answer/form id="${question.id}"/>
</div>
</body>
</html>