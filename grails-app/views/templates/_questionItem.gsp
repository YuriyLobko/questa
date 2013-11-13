<div class="well">
    <h3><g:link action="show" id="${question.id}">${question.title}</g:link></h3>
    <div class="right">${question.author.fullName}</div>
    <g:each in="${question.tags}" var="tag"><tmpl:/templates/tag tag="${tag}"/></g:each>
</div>