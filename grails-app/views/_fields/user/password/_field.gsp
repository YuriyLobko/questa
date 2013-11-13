<div class="form-group">
    <label for="${prefix}${property}" class="col-sm-2 control-label">${label}</label>
    <div class="col-sm-3">
        <g:passwordField class="form-control" name="${prefix}${property}" placeholder="${label}"/>
    </div>
    <div class="col-sm-7">
        <g:hasErrors bean="${bean}" field="${property}">
            <span class="alert alert-danger"><g:message error="${bean.errors.getFieldErrors(property)[0]}" /></span>
        </g:hasErrors>
    </div>
</div>