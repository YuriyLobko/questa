(function ( $ ) {
    $.fn.questaTypeahead = function() {
        return this.each(function() {
            var field = $(this);
            console.log(field);
            var typeaheadOptions = {};

            if(field.attr('data-remote')) typeaheadOptions.remote = field.attr('data-remote');
            if(field.attr('data-name')) typeaheadOptions.name = field.attr('data-name');
            if(field.attr('data-key')) typeaheadOptions.valueKey = field.attr('data-key');
            field.typeahead(typeaheadOptions);
        });
    };


}( jQuery ));

$(function() {
    $('input[rel=typeahead]').questaTypeahead();
    $('.typeahead.input-sm').siblings('input.tt-hint').addClass('hint-small');
    $('.typeahead.input-lg').siblings('input.tt-hint').addClass('hint-large');
});