package rocks.androidthings.huecontroller

import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit

/**
 * Created by mplacona on 04/06/2017.
 */

class RestApi {
    private val hueApi: Hue
    init {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder().addInterceptor(logging).build()

        val retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.0.2")
                .client(httpClient)
                .build()

        hueApi = retrofit.create(Hue::class.java)
    }

    fun makeRed(): Call<ResponseBody> = hueApi.ControlLight(convertRequestBody("""{"on":true, "hue":0}"""))
    fun  makeGreen(): Call<ResponseBody> = hueApi.ControlLight(convertRequestBody("""{"on":true, "hue":25500}"""))
    fun  makeBlue(): Call<ResponseBody> = hueApi.ControlLight(convertRequestBody("""{"on":true, "hue":46920}"""))
    fun turnOff(): Call<ResponseBody> = hueApi.ControlLight(convertRequestBody("""{"on":false}"""))

    private fun convertRequestBody(body: String): RequestBody = RequestBody.create(MediaType.parse("text/plain"), body)
}