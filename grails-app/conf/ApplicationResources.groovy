modules = {
    application {
        dependsOn 'bootstrap-css, bootstrap-js'

        resource url: '/css/application.css'
    }

    dialog {
        resource url: '/css/dialog.css'
    }
}