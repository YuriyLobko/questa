package com.questa

class QuestaTagLib {
    static namespace = "qa"

    /**
     * @attr name REQUIRED name of the field
     * @attr remote REQUIRED url for typeahead source, e.g. https://twitter.com/accounts?q=%QUERY
     * @attr class OPTIONAL css classes
     * @attr tagLimit OPTIONAL limit for number of tags
     * @attr typeaheadLimit OPTIONAL number of items in the typeahead list
     * @attr container OPTIONAL container id where should be placed inputted tags
     * @attr typeaheadKey OPTIONAL the key used to access the value of the datum in the datum object
     * @attr values OPTIONAL populates the initial tag values. List of string values
     */
    def tagField = { attrs ->
        def name = attrs.remove('name');
        def values = attrs.remove('values');
        def attributes = [
                name: name + 'TagField',
                'data-remote': attrs.remove('remote'),
                'class': attrs.remove('class'),
                'data-tag-limit': attrs.remove('tagLimit'),
                'data-typeahead-limit': attrs.remove('typeaheadLimit'),
                'data-container': attrs.remove('container'),
                'data-key': attrs.remove('typeaheadKey'),
                'data-hidden-tag-list-name': name,
                'data-prefilled': (values instanceof Collection) ? values.join(',') : values,
                'data-name': name,
                'rel': 'tag-field'
        ].findAll { it.value != null }

        out << render(template: '/taglib/tagField', model: [attrs: attributes])
    }

    /**
     * @attr total REQUIRED
     * @attr current REQUIRED
     * @attr controller REQUIRED
     * @attr action REQUIRED
     */
    def pagination = {attrs ->
        Long current = attrs.remove('current')
        Long total = attrs.remove('total')
        def pageItems = ((current-5..current+5).toList() << (long)1 << total).findAll { it > 0 && it <= total}.toSet().sort()

        out << render(template: '/taglib/pagination', model: [items: pageItems, current: current, total: total,
                action: attrs.action, controller: attrs.controller])
    }
}
