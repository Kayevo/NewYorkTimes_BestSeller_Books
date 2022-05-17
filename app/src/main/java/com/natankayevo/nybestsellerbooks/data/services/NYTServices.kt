package com.natankayevo.nybestsellerbooks.data.services

import android.telecom.Call
import com.natankayevo.nybestsellerbooks.data.response.BookBodyResponse
import retrofit2.http.*


interface NYTServices {
    @GET("lists.json")
    fun getBooks(
        @Query("api-key") apiKey: String = "8n6slkfLfvPcGlzSrZn7oAVnnT2R1Rvn",
        @Query("list") list: String = "hardcover-fiction"
    ): BookBodyResponse
}