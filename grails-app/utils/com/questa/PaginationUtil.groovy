package com.questa

import grails.orm.PagedResultList

class PaginationUtil {
    final Closure<PagedResultList> query
    final Integer perPage

    PaginationUtil(Closure<PagedResultList> query, Integer perPage) {
        this.query = query
        this.perPage = perPage
    }

    static PaginationUtil create(Integer perPage, Closure<PagedResultList> query) {
        new PaginationUtil(query, perPage)
    }

    Map getPage(Integer page) {
        page = page ?: 1
        PagedResultList resultList = query(getPaginationParameters(page, perPage))

        [list: resultList, total: getTotalPages(resultList.totalCount, perPage), page: page]
    }

    protected Integer getTotalPages(Integer total, Integer perPage) {
        (int)Math.ceil((double)total/perPage)
    }

    protected Map getPaginationParameters(Integer page, Integer perPage) {
        [
                offset: perPage * (page - 1),
                max: perPage
        ]
    }
}
