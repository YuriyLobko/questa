class UrlMappings {

    static mappings = {
        "/$controller/$id?"(action: 'show') {
            constraints {
                id matches: /\d*/
            }
        }

        "/$controller/list/$page?"(action: 'list') {
            constraints {
                page matches: /\d*/
            }
        }

        "/$action/$controller/$id?" {

        }

        "/$page?"(controller: 'question') {
            constraints {
                page matches: /\d*/
            }
        }

        "/tag/$tag/$page?"(controller: 'question') {
            constraints {
                page matches: /\d*/
            }
        }

        name main: '/'(controller: 'question')

        '/not-found'(view: 'notFound')
    }
}
