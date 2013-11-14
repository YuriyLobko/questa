<sec:ifLoggedIn>
    <g:formRemote name="answerForm" url="[controller: 'question', action: 'answer', id: id]" method="POST"
                  before="var answerForm = \$(this); \$(this).children('input[type=submit]').addClass('disabled').attr('disabled', 'disabled')"
                  after="\$(this).children('input[type=submit]').removeClass('disabled').removeAttr('disabled')"
                  onSuccess="answerProcessSuccess(data, answerForm)"
                  onFailure="answerProcessFailure(XMLHttpRequest.status, XMLHttpRequest.responseText, answerForm)">
        <div class="alert alert-danger answer-error hidden"></div>

        <div class="form-group">
            <g:textArea class="form-control" rows="3" name="content" cols="1"></g:textArea>
        </div>
        <g:submitButton name="send" class="btn btn-primary" value="Send"/>
    </g:formRemote>

    <r:script>
var answerProcessFailure = function (statusCode, responseText, form) {
    if (statusCode == 404) {
        responseText = '${g.message(code: 'answer.save.question.not.found.error')}';
    }
    console.log($('.answer-error'))
    $('.answer-error').html(responseText).removeClass('hidden');
}

var answerProcessSuccess = function (data, form) {
    $('.answers').append(data);
    form.children('input[name=content]').val('');
    $('.answer-error').html(responseText).removeClass('hidden');
}
    </r:script>
</sec:ifLoggedIn>