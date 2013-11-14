(function ( $ ) {
    $.fn.tagField = function() {
        return this.each(function() {
            var field = $(this);
            var tagManagerOptions = {};
            var typeaheadOptions = {};
            if(field.attr('data-container')) tagManagerOptions.tagsContainer = '#' + field.attr('data-container');
            if(field.attr('data-tag-limit')) tagManagerOptions.limit = field.attr('data-tag-limit');
            if(field.attr('data-hidden-tag-list-name')) tagManagerOptions.hiddenTagListName = field.attr('data-hidden-tag-list-name');
            if(field.attr('data-prefilled')) tagManagerOptions.prefilled = field.attr('data-prefilled');
            tagManagerOptions.delimiters = [44,9,13,32];

            if(field.attr('data-remote')) typeaheadOptions.remote = field.attr('data-remote');
            if(field.attr('data-name')) typeaheadOptions.name = field.attr('data-name');
            if(field.attr('data-key')) typeaheadOptions.valueKey = field.attr('data-key');
            if(field.attr('data-typeahead-limit')) typeaheadOptions.limit = field.attr('data-typeahead-limit');
            var tagApi = field.tagsManager(tagManagerOptions)
            field.typeahead(typeaheadOptions).on('typeahead:selected', function (e, d) {
                tagApi.tagsManager("pushTag", d.value);
            });
        });
    };

    $('[rel=tag-field]').tagField()
}( jQuery ));
