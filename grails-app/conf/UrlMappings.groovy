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
            constraints {
                id matches: /\d*/
            }
        }

        "/$page?"(controller: 'question') {
            constraints {
                page matches: /\d*/
            }
        }

        '/not-found'(view: 'notFound')
    }
}
