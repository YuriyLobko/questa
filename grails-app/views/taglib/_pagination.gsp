<ul class="pagination">
    <li class="${current > 1 ? '' : 'disabled'}">
        <g:link controller="${controller}" action="${action}" params="${params + [page: current - 1]}">&laquo;</g:link>
    </li>
    <g:each in="${items}">
        <li class="${it == current ? 'active' : ''}">
            <g:link controller="${controller}" action="${action}" params="${params + [page: it]}">${it}</g:link>
        </li>
    </g:each>
    <li class="${current < total ? '' : 'disabled'}">
        <g:link controller="${controller}" action="${action}" params="${params + [page: current + 1]}">&raquo;</g:link>
    </li>
</ul>