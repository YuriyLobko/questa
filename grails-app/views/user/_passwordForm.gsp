<g:form action="changePassword" class="form-horizontal" method="POST">
    <fieldset>
        <legend><g:message code="password.change.fieldset.legend"/></legend>
        <g:hiddenField name="id" bean="${user}"/>
        <g:hiddenField name="version" bean="${user}"/>
        <f:field property="password" bean="${user}"/>
        <div class="form-group">
            <label for="password2" class="col-sm-2 control-label"></label>
            <div class="col-sm-3">
                <g:passwordField class="form-control" name="password2" placeholder="${g.message(code: 'user.password.repeat.field.placeholder')}"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <g:submitButton name="change" value="${g.message(code: 'password.button.change.label')}" class="btn btn-primary"/>
            </div>
        </div>
    </fieldset>
</g:form>