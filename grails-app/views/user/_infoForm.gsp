<g:form action="save" class="form-horizontal" method="POST">
    <fieldset>
        <legend><g:message code="user.info.fieldset.legend"/></legend>
        <f:with bean="${user}">
            <g:hiddenField name="id" bean="${user}"/>
            <g:hiddenField name="version" bean="${user}"/>
            <f:field property="email"/>
            <f:field property="firstName"/>
            <f:field property="lastName"/>
        </f:with>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <g:submitButton name="change" value="${g.message(code: 'default.button.update.label')}" class="btn btn-primary"/>
            </div>
        </div>

    </fieldset>
</g:form>