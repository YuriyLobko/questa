<div class="form-group ${hasErrors(bean: bean, field: property, 'error')}">
    <label class="control-label" for="${prefix}${property}">${label}</label>
    <g:hasErrors bean="${bean}" field="${property}">
        <div class="alert alert-danger"><g:message error="${bean.errors.getFieldErrors(property)[0]}" /></div>
    </g:hasErrors>
    <div>Here will be input field for tags</div>
</div>