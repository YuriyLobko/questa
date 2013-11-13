<div class="form-group ${hasErrors(bean: bean, field: property, 'error')}">
    <label class="control-label" for="${prefix}${property}">${label}</label>
    <g:hasErrors bean="${bean}" field="${property}">
        <div class="alert alert-danger"><g:message error="${bean.errors.getFieldErrors(property)[0]}" /></div>
    </g:hasErrors>
    <g:textField class="form-control" name="${prefix}${property}" value="${value ?: ''}"/>
</div>