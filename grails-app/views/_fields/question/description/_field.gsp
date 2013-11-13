<div class="form-group ${hasErrors(bean: bean, field: property, 'error')}">
    <label class="control-label" for="${prefix}${property}">${label}</label>
    <g:hasErrors bean="${bean}" field="${property}">
        <div class="alert alert-danger"><g:message error="${bean.errors.getFieldErrors(property)[0]}" /></div>
    </g:hasErrors>
    <g:textArea class="form-control" rows="3" name="${prefix}${property}" cols="1">${value ?: ''}</g:textArea>
</div>