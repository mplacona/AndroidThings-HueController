package rocks.androidthings.huecontroller

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.PUT
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.Path

/**
 * Created by mplacona on 04/06/2017.
 */
interface Hue {
    @PUT("/api/{apiKey}/lights/3/state")
    fun ControlLight(@Path(value = "your-hue-api-key") apiKey: String, @Body params: RequestBody): Call<ResponseBody>
}