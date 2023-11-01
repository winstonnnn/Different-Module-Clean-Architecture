package com.fortune.data.api

import android.content.Context
import android.util.Log
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okio.Buffer
import okio.BufferedSource
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

/*
class HttpConfigInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        var request = chain.request()

        request = request.newBuilder()
            .header("token", sharedPrefModule.getString(SharePrefKey.tokenId)?:"")
            .header("dev", "2")
            .header("version", BuildConfig.VERSION_NAME)
            .header("frond-host", request.url.toString())
            .header("Content-Type", "application/json;charset=UTF-8")
            .build()

        //change url dynamically when you change the sharedPref
        val httpUrl =  sharedPrefModule.getString(SharePrefKey.myBaseUrl)?.toHttpUrlOrNull()
        if (request.url.scheme+"://"+request.url.host == "http://www.baidu.com" && httpUrl != null) {
            val newUrl =
                request.url.newBuilder()
                    .scheme(httpUrl.scheme)
                    .host(httpUrl.host)
                    .port(httpUrl.port)
                    .build()
            val token = if(newUrl.toUrl().toString().contains("init") || newUrl.toUrl().toString().contains("checkUpdate")){
                ""
            }else{
                sharedPrefModule.getString(SharePrefKey.tokenId)?:""
            }
            request = request.newBuilder()
                .url(newUrl)
                .header("token", token)
                .header("dev", "2")
                .header("version", BuildConfig.VERSION_NAME)
                .header("frond-host", newUrl.toUrl().toString())
                .header("Content-Type", "application/json;charset=UTF-8")
                .build()
        }
        val oldRequestBody = request.body
        if ("POST" == request.method) {
            val mediaType: MediaType? = oldRequestBody?.contentType()
            val requestBuffer = Buffer()
            oldRequestBody?.writeTo(requestBuffer)
            val oldBodyStr = requestBuffer.readUtf8()
            requestBuffer.close()
            val str = oldBodyStr.replace(Constants.Ampersand.toRegex(), "&")
            Log.d("NetworkModuleOldBody", str)
            val newBody = str.toRequestBody(mediaType)

            //构造新的request
            request = request.newBuilder()
                .method(request.method, newBody)
                .build()
        }
        Log.d("NetworkToken ", sharedPrefModule.getString(SharePrefKey.tokenId)?:"")
        Log.d("NetworkModuleUrl ", request.url.toString())
        Log.d("NetworkModuleRequest ", request.toString())

        var response: Response = chain.proceed(request)
        if(response.code == 200){
            val responseBody = response.body
            if(responseBody != null){
                try {
                    val source: BufferedSource = responseBody.source()
                    source.request(Long.MAX_VALUE)
                    val buffer = source.buffer
                    var charset = Charset.forName("UTF-8")
                    val contentType: MediaType? = responseBody.contentType()
                    if (contentType != null) {
                        charset = contentType.charset(charset)
                    }
                    val bodyString = buffer.clone().readString(charset!!)
                    val newResponseBody =
                        bodyString.trim { it <= ' ' }.toResponseBody(contentType)
                    //response
                    response = response.newBuilder().body(newResponseBody).build()

                    val responseBodyString = response.body?.source()?.buffer?.clone()?.readString(
                        Charset.forName("UTF-8"))

                    if(responseBodyString != null){
                        val baseModel = JSONObject(responseBodyString)
                        if(baseModel.getString("code").toInt() == 401){//Session expired code
                            Log.d("NetworkResponse401", "401")
                            //delete all data
                            sharedPrefModule.logOut()
                            AppDatabase.getDatabase(context).deleteAllDB()
                            isLogin.postValue(false)
                            hasNewMessage.postValue(false)

                            EventBus.getDefault().post(MainEvent.LOGOUT)

                            //toast the 401 session expired message
//                            Handler(Looper.getMainLooper()).post {
//                                context.toastLong(baseModel.getString("msg"))
//                            }
                        }
                    }
                    Log.d("NetworkModuleResponse", request.url.toString() +" :"+responseBodyString.toString()+"\n")

                } catch (e: IOException) {
                    Log.d("NetworkModuleResponse", e.toString())
                } catch (e: Exception) {
                    Log.d("NetworkModuleResponse", e.toString())
                    return response
                }
            }

        }
        return response
    }
}*/
