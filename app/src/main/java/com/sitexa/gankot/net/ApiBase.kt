package com.sitexa.gankot.net

import com.bumptech.glide.load.model.LazyHeaders
import com.github.salomonbrys.kotson.obj
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.sitexa.gankot.app.AppId
import com.sitexa.gankot.app.AppKey
import com.sitexa.gankot.app.sitexaApiBaseUrl
import com.sitexa.gankot.common.ApiCode
import com.sitexa.gankot.common.JodaGsonAdapter
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import org.joda.time.DateTime
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.Serializable
import java.lang.reflect.Type

/**
 * Created by open on 06/05/2017.
 * data 1 : String
 * data 2 : Class -> Json
 * data 3 : Map<String,String> -> Json, value -> Json. 如何系列化，就如何反系列化。
 */

data class ApiResult(private var code: Int = 0, private var desc: String = "", private var data: String = "") : Serializable {

    constructor(json: String) : this() {
        code = JsonParser().parse(json).obj["code"].asInt
        desc = JsonParser().parse(json).obj["desc"].asString
        data = JsonParser().parse(json).obj["data"].asString
    }

    fun code() = this.code
    fun desc() = this.desc
    fun data() = this.data

    fun <T> data(aClass: Class<T>): T? {
        if (code == ApiCode.ERROR) return null
        val gson = GsonBuilder()
                .registerTypeAdapter(DateTime::class.java, JodaGsonAdapter())
                .setLenient()
                .create()
        try {
            return gson.fromJson<T>(data, aClass)
        } catch (e: Exception) {
            println(e.stackTrace)
            return null
        }
    }

    fun <T> data(type: Type): T? {
        if (code == ApiCode.ERROR) return null
        val gson = GsonBuilder()
                .registerTypeAdapter(DateTime::class.java, JodaGsonAdapter())
                .setLenient()
                .create()
        try {
            return gson.fromJson<T>(data, type)
        } catch(e: Exception) {
            println(e.stackTrace)
            return null
        }
    }
}


val HEADERS_JSON = Headers.Builder().add("Accept", "application/json").build()

val HEADERS_MULTIPART = Headers.Builder().add("Content-Type", "application/octet-stream").build()

val headerJsonInterceptor = Interceptor { chain -> chain.proceed(chain.request().newBuilder().headers(HEADERS_JSON).build()) }

val headerMultipartInterceptor = Interceptor { chain -> chain.proceed(chain.request().newBuilder().headers(HEADERS_MULTIPART).build()) }

val loggingInterceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

class HeaderInterceptor(val name: String = "Accept", val value: String = "application/json") : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().addHeader(name, value).build()
        return chain.proceed(request)
    }
}

val authenticator = Authenticator { _, response ->
    fun responseCount(response: Response): Int {
        var result = 1
        while ((response.priorResponse()) != null) result++
        return result
    }

    if (responseCount(response) >= 3) return@Authenticator null

    val credential = Credentials.basic(AppId, AppKey)
    response.request().newBuilder().header("Authorization", credential).build()
}

object ApiService {

    val gson = GsonBuilder()
            .registerTypeAdapter(DateTime::class.java, JodaGsonAdapter())
            .setPrettyPrinting()
            .create()

    val okClient = OkHttpClient().newBuilder()
            .authenticator(authenticator)
            .addInterceptor(HeaderInterceptor())
            .addInterceptor(loggingInterceptor)
            .build()

    val retrofit = Retrofit.Builder()
            .baseUrl(sitexaApiBaseUrl)
            .client(okClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
}

val glideHeader: LazyHeaders = LazyHeaders.Builder().addHeader("Authorization", Credentials.basic(AppId, AppKey)).build()
