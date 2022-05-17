package com.natankayevo.nybestsellerbooks.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BookResponse(
    @Json(name = "book_details")
    val bookDetails: List<BookDetailsResponse>
    )