<div class="row">
    <div class="well">
        <h3><g:link action="show" id="${question.id}">${question.title}</g:link></h3>
        <div class="pull-right">${question.author.fullName}</div>
        <tmpl:/templates/tagsInline tags="${question.tags}"/>
    </div>
</div>