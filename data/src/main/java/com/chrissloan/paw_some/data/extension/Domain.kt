package com.chrissloan.paw_some.data.extension

import com.chrissloan.paw_some.domain.core.DomainResponse

suspend fun <T : Any> domainResponse(result: suspend () -> T) =
    try {
        DomainResponse.Content(result())
    } catch (e: Exception) {
        DomainResponse.Error(999, e)
    }
