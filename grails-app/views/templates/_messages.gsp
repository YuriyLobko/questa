<g:each in="['success', 'info', 'danger', 'warning']" var="level">
    <g:each in="${flash.get(level, []) ?: []}" var="code">
        <div class="alert alert-${level}">
            <g:message code="${code}"/>
        </div>
    </g:each>
</g:each>
