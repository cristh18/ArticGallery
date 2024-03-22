package com.tolodev.artic_gallery.utils

import okhttp3.ResponseBody
import okio.Buffer
import okio.BufferedSource
import java.nio.charset.Charset

fun getHttpErrorMessage(throwable: Throwable): String {
//    return if (throwable is HttpException) {
//        val copyException = HttpException(throwable.response())
//        val responseBody = copyException.response()?.errorBody()
//        getBody(responseBody)?.let {
//            val moshi = Moshi.Builder()
//                .add(KotlinJsonAdapterFactory())
//                .build()
//            val jsonAdapter: JsonAdapter<ShorteningLinkResponseError> =
//                moshi.adapter(ShorteningLinkResponseError::class.java)
//
//            val shorteningLinkResponseError = jsonAdapter.fromJson(it)
//            shorteningLinkResponseError?.error ?: "Has occurred an error"
//        } ?: "Has occurred an error"
//    } else {
//        "Has occurred an error"
//    }
    return ""
}

private fun getBody(response: ResponseBody?): String? {
    val source: BufferedSource? = response?.source()
    source?.request(Long.MAX_VALUE)
    val buffer: Buffer? = source?.buffer()
    return buffer?.clone()?.readString(Charset.forName("UTF-8"))
}