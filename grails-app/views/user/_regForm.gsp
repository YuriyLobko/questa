<f:with bean="${user}">
    <f:field property="email"/>
    <f:field property="firstName"/>
    <f:field property="lastName"/>
    <f:field property="password"/>
    <div class="form-group">
        <label for="password2" class="col-sm-2 control-label"></label>
        <div class="col-sm-3">
            <g:passwordField class="form-control" name="password2" placeholder="${g.message(code: 'user.password.repeat.field.placeholder')}"/>
        </div>
    </div>
</f:with>