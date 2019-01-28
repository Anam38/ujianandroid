package com.example.root.ujianandroid.UI.Networking

import com.example.root.ujianandroid.UI.Utils.Contans
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ConfigNetwork
{
        fun GetInterceptor(): OkHttpClient {


            val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            return client
        }

        fun Retrofit(): Retrofit {

            var retrofit = Retrofit.Builder()
                .baseUrl(Contans.url)
                .client(GetInterceptor())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit
        }

    fun getservice():getdataService {

        return Retrofit().create(getdataService::class.java)
    }
}