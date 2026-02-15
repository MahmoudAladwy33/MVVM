package com.example.lazylist

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONObject

class MyWorker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {
    override fun doWork(): Result {

        return try {

            val client = OkHttpClient()
            val request = Request.Builder()
                .url("https://dummyjson.com/products")
                .build()

            val response = client.newCall(request).execute()

            if (response.isSuccessful) {
                val data = response.body?.string() ?: ""
                val root = JSONObject(data)
                val products = root.getJSONArray("products")
                val smallArray = JSONArray()
                for (i in 0 until minOf(12, products.length())) {
                    val item = products.getJSONObject(i)
                    val obj = JSONObject()
                    obj.put("title", item.getString("title"))
                    obj.put("price", item.getDouble("price"))
                    obj.put("thumbnail", item.getString("thumbnail"))
                    smallArray.put(obj)
                }

                val output = workDataOf("response" to smallArray.toString())
                Result.success(output)

            } else {
                Result.retry()
            }

        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure()
        }
    }

    }

