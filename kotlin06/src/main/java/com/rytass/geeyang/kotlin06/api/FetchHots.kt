package com.rytass.geeyang.kotlin06.api

import android.os.AsyncTask
import com.rytass.geeyang.kotlin06.entity.Hot
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * Created by yangjiru on 2018/1/8.
 */

class FetchHots : AsyncTask<Object, Void, MyResponse>() {
    override fun onPreExecute() {
        super.onPreExecute()
    }

    override fun doInBackground(vararg p0: Object?): MyResponse {
        val client = OkHttpClient.Builder()
                .hostnameVerifier({ _, _ ->
                    true
                })
                .build()

        val url = HttpUrl.parse("http://disp.cc/api/hot_text.json")!!.newBuilder()
                .build()
                .url()

        val request = Request.Builder().url(url).build()
        val response = client.newCall(request).execute()

        val statusCode = response.code()
        val body = response.body()?.string()

        println("statusCode: $statusCode")
        println("$body")

        val hots = parse(body)

        return MyResponse(statusCode, hots)
    }

    override fun onPostExecute(result: MyResponse?) {
        super.onPostExecute(result)
    }


    private fun parse(body: String?): Set<Hot>? {
        var hots: Set<Hot>? = null

        return hots
    }
}