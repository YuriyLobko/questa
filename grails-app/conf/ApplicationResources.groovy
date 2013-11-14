modules = {
    application {
        dependsOn 'bootstrap-css, bootstrap-js, typeahead'

        resource url: '/css/application.css'
    }

    dialog {
        resource url: '/css/dialog.css'
    }

    tagfield {
        dependsOn 'bootstrap, typeahead'

        resource url: '/tagmanager-master/tagmanager.css'
        resource url: '/tagmanager-master/tagmanager.js'
        resource url: '/js/tag.field.js'
    }

    typeahead {
        dependsOn 'bootstrap'

        resource url: '/css/typeahead-bootstrap.css'
        resource url: '/js/typeahead.min.js'
        resource url: '/js/typeahead.field.js'
    }
}