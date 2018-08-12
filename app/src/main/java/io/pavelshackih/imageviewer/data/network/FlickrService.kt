package io.pavelshackih.imageviewer.data.network

import io.pavelshackih.imageviewer.BuildConfig
import io.pavelshackih.imageviewer.model.Photos
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrService {

    @GET(GET_RECENT)
    fun getRecent(): Single<Photos>

    @GET(SEARCH)
    fun getSearch(@Query("text") text: String): Single<Photos>

    companion object {

        private const val SEARCH = "rest/?method=flickr.photos.search&per_page=5&nojsoncallback=1&extras=url_m,url_t,url_o"
        private const val GET_RECENT = "rest/?method=flickr.photos.getRecent&nojsoncallback=1&"

        private const val API_KEY_PARAM = "api_key"
        private const val FORMAT_PARAM = "format"
        private const val FORMAT_VALUE = "json"

        private const val BASE_URL = "https://api.flickr.com/services/"

        val api by lazy {
            val clientBuilder = OkHttpClient.Builder()

            if (BuildConfig.DEBUG) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                clientBuilder.addInterceptor(interceptor)
            }

            clientBuilder.addInterceptor { chain ->
                var request = chain.request()
                val url = request.url()
                        .newBuilder()
                        .addQueryParameter(API_KEY_PARAM, "a9a9f9f2b95d8104f01bbd3532ac0221")
                        .addQueryParameter(FORMAT_PARAM, FORMAT_VALUE)
                        .build()
                request = request.newBuilder().url(url).build()
                chain.proceed(request)
            }

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(clientBuilder.build())
                    .baseUrl(BASE_URL)
                    .build()

            retrofit.create(FlickrService::class.java)
        }
    }
}