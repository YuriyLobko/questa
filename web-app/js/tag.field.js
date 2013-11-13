(function ( $ ) {
    $.fn.tagField = function() {
        return this.each(function() {
            var field = $(this);
            var tagManagerOptions = {
                container: field.attr('data-container'),
                hiddenTagListId: field.attr('data-hidden-tag-list-id'),
                limit: field.attr('data-tag-limit')
            };
            var typeaheadOptions = {
                remote: field.attr('data-remote'),
                name: field.attr('data-name'),
                valueKey: field.attr('data-key'),
                limit: field.attr('data-typeahead-limit')
            };
            field.tagsManager(tagManagerOptions).typeahead(typeaheadOptions);
        });
    };

    $('[rel=tag-field]').tagField()
}( jQuery ));
