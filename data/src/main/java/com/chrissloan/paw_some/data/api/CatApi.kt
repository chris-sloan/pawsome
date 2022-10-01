package com.chrissloan.paw_some.data.api

import com.chrissloan.paw_some.data.entity.BreedApiEntity
import retrofit2.http.GET
import retrofit2.http.Headers

interface CatApi {

    @Headers(
        "x-api-key: live_DBkIEFu5v3f3BEKSfnCi3RwOeqbRxRSLghSOmgMb5QBvam7ssCDs2smNIBahwceO"
    )
    @GET("v1/breeds")
    suspend fun getBreeds(): List<BreedApiEntity>

}
