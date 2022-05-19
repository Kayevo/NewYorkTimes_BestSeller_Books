package com.natankayevo.nybestsellerbooks.data.services

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import com.natankayevo.nybestsellerbooks.data.response.BookBodyResponse as BookBodyResponse


interface NYTServices {
    @GET("lists.json")
    fun getBooks(
        @Query("api-key") apiKey: String = "8n6slkfLfvPcGlzSrZn7oAVnnT2R1Rvn",
        @Query("list") list: String = "hardcover-fiction"
    ): Call<BookBodyResponse>
}