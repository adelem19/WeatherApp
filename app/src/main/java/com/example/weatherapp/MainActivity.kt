package com.example.weatherapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.weatherapp.databinding.ActivityMainBinding
import org.json.JSONObject

const val API_KEY = "f8bcc0b15ade6c4622a844d0737cfd21"
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bGet.setOnClickListener {
            val city = binding.ptCity.text
            getTemp(city.toString())
        }
    }

    private fun getTemp(name: String){
        val url = "https://api.openweathermap.org/data/2.5/weather?q=$name&appid=$API_KEY&units=metric"
        val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(Request.Method.GET,
        url,
            { response->
                val obj = JSONObject(response)
                val temp = obj.getJSONObject("main")
                val temp_c = temp.getString("temp")
                binding.tvCel.text = temp_c
                Log.d("MyLog","Response: ${temp.getString("temp")}")
        },
            {
                Toast.makeText(this,"Error", Toast.LENGTH_SHORT).show()
            }
        )
        queue.add(stringRequest)
    }
}