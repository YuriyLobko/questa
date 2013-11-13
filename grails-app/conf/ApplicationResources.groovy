modules = {
    application {
        dependsOn 'bootstrap-css, bootstrap-js'

        resource url: '/css/application.css'
    }

    dialog {
        resource url: '/css/dialog.css'
    }

    tagfield {
        dependsOn 'bootstrap'

        resource url: '/tagmanager-master/tagmanager.css'
        resource url: '/tagmanager-master/tagmanager.js'
        resource url: '/js/typeahead.min.js'
        resource url: '/js/tag.field.js'
    }
}