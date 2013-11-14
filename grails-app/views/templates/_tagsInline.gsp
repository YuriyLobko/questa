<g:each in="${tags.sort {a, b -> a.name <=> b.name}}" var="tag">
    &nbsp;<g:link controller="question" action="list" params="[tag: tag.name]" class="label label-primary">${tag.name}</g:link>
</g:each>