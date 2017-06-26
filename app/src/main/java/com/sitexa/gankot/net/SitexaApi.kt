package com.sitexa.gankot.net

import com.sitexa.gankot.repository.Media
import com.sitexa.gankot.repository.Sweet
import com.sitexa.gankot.repository.User
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.*

/**
 * Created by open on 11/06/2017.
 *
 */

interface SweetApi {
    @GET("/sweet/{type}/{count}/{page}") fun getData(@Path("type") type: String, @Path("count") count: Int, @Path("page") page: Int): Observable<List<Sweet>>
    @GET("/sweet/{id}") fun singleSweet(@Path("id") id: Int): Observable<Sweet>
    @GET("/sweet-component/{id}") fun sweetComponent(@Path("id") id: Int): Observable<ApiResult>
    @GET("/top/{count}/{page}") fun topSweet(@Path("count") count: Int, @Path("page") page: Int): Observable<List<Sweet>>
    @GET("/latest/{count}/{page}") fun latestSweet(@Path("count") count: Int, @Path("page") page: Int): Observable<List<Sweet>>
    @GET("/sweet-reply-count/{id}") fun countSweetReplies(@Path("id") id: Int): Observable<Int>
    @GET("/sweet-replies/{id}") fun getReplies(@Path("id") id: Int): Observable<List<Sweet>>
    @GET("/sweet-user/{user}") fun getUserSweets(@Path("user") user: String): Observable<List<Int>>
    @GET("/sweet-medias/{refId}") fun getMedias(@Path("refId") refId: Int): Observable<List<Media>>

    @FormUrlEncoded @POST("/sweet-new") fun createSweet(@Field("user") user: String, @Field("text") text: String, @Field("replyTo") replyTo: Int?): Observable<ApiResult>
    @GET("/sweet-del") fun deleteSweet(@Query("id") id: Int): Observable<ApiResult>
    @FormUrlEncoded @POST("/sweet-upd") fun updateSweet(@Field("id") id: Int, @Field("text") text: String): Observable<ApiResult>

    @FormUrlEncoded @POST("/media-new") fun createMedia(@Field("refId") refId: Int, @Field("fileName") fileName: String, @Field("fileType") fileType: String?, @Field("title") title: String?, @Field("sortOrder") sortOrder: Int?): Observable<ApiResult>
    @GET("/media-del") fun deleteMedia(@Query("id") id: Int): Observable<ApiResult>
    @GET("/media/{name}/{type}") fun viewMedia(@Path("name") name: String, @Path("type") type: String): Observable<ResponseBody>
    @GET("/media/{id}") fun getMedia(@Path("id") id: Int): Observable<Media>
    @GET("/mediasBySweet/{refId}") fun getMediasBySweet(@Path("refId") refId: Int): Observable<List<Int>>


    companion object Factory {
        fun create(): SweetApi {
            val retrofit = ApiService.retrofit
            return retrofit.create(SweetApi::class.java)
        }
    }

}

interface UserApi {
    @GET("/user-info/{userId}") fun userInfo(@Path("userId") userId: String): Observable<User>

    @GET("/user/{userId}")
    fun userPage(@Path("userId") userId: String): Observable<ResponseBody>

    @GET("/user/{userId}")
    fun userPage2(@Path("userId") userId: String): Observable<List<Sweet>>

    @FormUrlEncoded @POST("/login")
    fun login(@Field("userId") userId: String, @Field("password") password: String = ""): Observable<ResponseBody>

    @FormUrlEncoded @POST("/register")
    fun register(@Field("userId") userId: String,
                 @Field("password") password: String,
                 @Field("email") email: String,
                 @Field("mobile") mobile: String,
                 @Field("displayName") displayName: String): Observable<ResponseBody>

    @FormUrlEncoded @POST("/cpwd")
    fun changePassword(@Field("userId") userId: String,
                       @Field("password") password: String,
                       @Field("newPassword") newPassword: String): Observable<ApiResult>

    @GET("/vcode")
    fun sendVCode(@Query("mobile") mobile: String): Observable<ApiResult>

    @FormUrlEncoded @POST("/vcode")
    fun testVCode(@Field("vcode") vcode: String, @Field("date") date: Long, @Field("sign") sign: String): Observable<ApiResult>

    @GET("/userByMobile/{mobile}") fun getUserByMobile(@Path("mobile") mobile: String): Observable<User>
    @GET("/userByEmail/{email}") fun getUserByEmail(@Path("email") email: String): Observable<User>

    companion object Factory {
        fun create(): UserApi {
            val retrofit = ApiService.retrofit
            return retrofit.create(UserApi::class.java)
        }
    }
}


interface FileApi {

    @Multipart @POST("/upload")
    fun upload(@Part("refId") refId: RequestBody,
               @Part("title") title: RequestBody,
               @Part("sortOrder") sortOrder: RequestBody,
               @Part file: MultipartBody.Part): Observable<ApiResult>

    @GET("/download")
    fun download(@Query("id") id: Int): Observable<ResponseBody>

    companion object Factory {
        fun create(): FileApi {
            val retrofit = ApiService.retrofit
            return retrofit.create(FileApi::class.java)
        }
    }
}