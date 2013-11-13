<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
            <span class="sr-only"><g:message code="nav.toggle.button.label"/></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <g:link mapping="main" class="navbar-brand"><g:message code="application.title"/></g:link>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav">
            <li class="${params.controller == 'question' && (params.action in ['list', 'show', 'create', 'edit', 'save'] || !params.action) ? 'active' : ''}">
                <g:link mapping="main">
                    <g:message code="nav.question.list.link.label"/>
                </g:link>
            </li>
            <sec:ifNotLoggedIn>
                <li>
                    <g:link controller="user" action="register" class="${params.controller == 'user' && params.action in ['register'] ? 'active' : ''}">
                        <g:message code="nav.register.button.label"/>
                    </g:link>
                </li>
                <li>
                    <g:link controller="login" action="auth" class="${params.controller == 'login' && params.action in ['auth'] ? 'active' : ''}">
                        <g:message code="nav.sign.in.button.label"/>
                    </g:link>
                </li>
            </sec:ifNotLoggedIn>
        </ul>
        <sec:ifLoggedIn>
            <g:link controller="question" action="create" class="btn btn-info navbar-btn navbar-right">
                <g:message code="nav.ask.button.label"/>
            </g:link>
        </sec:ifLoggedIn>
        <g:form class="navbar-form navbar-right" role="search" controller="question" action="list" method="get">
            <div class="form-group">
                <g:textField name="q" class="form-control" placeholder="${g.message(code: 'nav.search.field.placeholder')}"/>
            </div>
            <button type="submit" class="btn btn-default">${g.message(code: 'nav.search.button.label')}</button>
        </g:form>
    </div>
    </div>
</nav>