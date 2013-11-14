<div class="media">
    <a class="pull-left" href="#">
        <img class="media-object" src="http://placekitten.com/${63 + answer.authorId}/${63 + answer.authorId}" alt="${answer.author.firstName}">
    </a>
    <div class="media-body">
        <h4 class="media-heading">${answer.author.fullName}</h4>
        ${answer.content.replaceAll("(\r\n|\r|\n|\n\r)", '<br />')}
    </div>
</div>