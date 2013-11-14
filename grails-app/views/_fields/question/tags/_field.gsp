<div class="form-group ${hasErrors(bean: bean, field: property, 'error')}">
    <label class="control-label" for="tagNames">${label}</label>
    <g:hasErrors bean="${bean}" field="${property}">
        <div class="alert alert-danger"><g:message error="${bean.errors.getFieldErrors(property)[0]}" /></div>
    </g:hasErrors>
    <div id="tagContainer"></div>
    <qa:tagField name="tagNames" remote="/typeahead/tag/%QUERY" container="tagContainer" typeaheadKey="name" class="form-control"
                 autocomplete="off" values="${value*.name}"/>
</div>