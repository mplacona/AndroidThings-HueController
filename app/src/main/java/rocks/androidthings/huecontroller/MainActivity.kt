package rocks.androidthings.huecontroller

import android.app.Activity
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import com.google.android.things.contrib.driver.button.Button
import com.google.android.things.pio.PeripheralManagerService
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback


class MainActivity : Activity() {

    lateinit var redButton: Button
    lateinit var greenButton: Button
    lateinit var blueButton: Button
    lateinit var greyButton: Button
    private val hueApi: RestApi = RestApi()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        redButton = Button("BCM26", Button.LogicState.PRESSED_WHEN_LOW)
        greenButton = Button("BCM19", Button.LogicState.PRESSED_WHEN_LOW)
        blueButton = Button("BCM13", Button.LogicState.PRESSED_WHEN_LOW)
        greyButton = Button("BCM6", Button.LogicState.PRESSED_WHEN_LOW)
        val pmService = PeripheralManagerService()

        // Event Listener
        redButton.setOnButtonEventListener( { _, _ -> val call = hueApi.makeRed(); call.enqueue(callbackHue) })
        greenButton.setOnButtonEventListener( { _, _ -> val call = hueApi.makeGreen(); call.enqueue(callbackHue) })
        blueButton.setOnButtonEventListener( { _, _ -> val call = hueApi.makeBlue(); call.enqueue(callbackHue) })
        greyButton.setOnButtonEventListener( { _, _ -> val call = hueApi.turnOff(); call.enqueue(callbackHue) })
    }

    private val callbackHue: Callback<ResponseBody> = object : Callback<ResponseBody> {
        override fun onResponse(call: Call<ResponseBody>, response: retrofit2.Response<ResponseBody>) {
            Log.d(TAG, response?.body().toString())
        }

        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
            Log.d("ERROR", "Failure " + t.message)
        }
    }
}
