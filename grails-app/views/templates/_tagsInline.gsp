<g:each in="${tags.sort {a, b -> a.name <=> b.name}}" var="tag">
    <span class="label label-primary">${tag.name}</span>&nbsp;
</g:each>