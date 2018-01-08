package com.rytass.geeyang.kotlin06.api

import android.os.AsyncTask
import com.rytass.geeyang.kotlin06.MainActivity
import com.rytass.geeyang.kotlin06.entity.Hot
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.IOException

/**
 * Created by yangjiru on 2018/1/8.
 */

class FetchHots(val mainActivity: MainActivity) : AsyncTask<Object, Void, MyResponse>() {
    override fun onPreExecute() {
        super.onPreExecute()
    }

    override fun doInBackground(vararg p0: Object?): MyResponse {
        try {
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
        } catch (e: IOException) {
            return MyResponse(400, "IOException")
        }
    }

    override fun onPostExecute(result: MyResponse?) {
        super.onPostExecute(result)
        val (statusCode, body) = result!!

        when (statusCode) {
            200 -> {
                mainActivity?.setData(body as Set<Hot>)
            }
        }
    }


    private fun parse(body: String?): Set<Hot>? {
        var hots: MutableList<Hot>? = mutableListOf()

        if (body != null) {
            val bodyObj = JSONObject(body)
            val listArr = bodyObj.optJSONArray("list")
            for (i in 0..(listArr.length() - 1)) {
                val hotObj = listArr.getJSONObject(i)

                val imgList = mutableListOf<String>()
                val imgArr = hotObj.getJSONArray("img_list")
                for (j in 0..(imgArr.length() - 1)) {
                    imgList.add(imgArr.getString(j))
                }

                val hot = Hot(
                        hotObj.getString("hot_num"),
                        hotObj.getString("bi"),
                        hotObj.getString("ti"),
                        hotObj.getInt("ai"),
                        hotObj.getString("author"),
                        hotObj.getString("title"),
                        hotObj.getString("board_name"),
                        hotObj.getString("desc"),
                        imgList.toList(),
                        hotObj.getString("url")
                )

                hots!!.add(hot)
            }
        }

        return hots!!.toSet()
    }
}