<g:if test="${response.getHeader('more-answers-offset')}">
    <g:formRemote name="moreAnswers" url="[controller: 'question', action: 'answers', id: id]" method="GET"
                  before="var moreForm = \$(this); \$(this).children('input[type=submit]').addClass('disabled').attr('disabled', 'disabled')"
                  after="\$(this).children('input[type=submit]').removeClass('disabled').removeAttr('disabled')"
                  onSuccess="moreSuccessProcess(data, moreForm)"
                  onComplete="moreCompleteProcess(XMLHttpRequest.getResponseHeader('more-answers-offset'), moreForm)">
        <g:hiddenField name="offset" value="${response.getHeader('more-answers-offset')}"/>
        <g:submitButton class="btn btn-default col-xs-12" name="loadAnswers" value="${g.message(code: 'answer.more.button.label')}"/>
    </g:formRemote>
</g:if>
<r:script>
    var moreSuccessProcess = function(data, form) {
        form.before(data);
    }
    var moreCompleteProcess = function(offsetHeader, form) {
        if (parseInt(offsetHeader) > 0) {
            form.children('input[name=offset]').val(offsetHeader)
        } else {
            form.remove();
        }
    }
</r:script>