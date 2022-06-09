package org.alkemy.integradorandroid.ui.activitiessuggestions

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.alkemy.integradorandroid.R
import org.alkemy.integradorandroid.api.BoredAPI
import org.alkemy.integradorandroid.api.BoredAPIaccess
import org.alkemy.integradorandroid.api.BoredResponse
import org.alkemy.integradorandroid.databinding.ActivitySuggestionBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ViewSuggestions : AppCompatActivity() {

    private lateinit var binding : ActivitySuggestionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_suggestion)
        binding = ActivitySuggestionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var activities1 = intent.getStringExtra("Activities2")
        val partipans  = intent.getStringExtra("participants")
        if(activities1 == "Random"){
            activities1 = null
        }
        api(activities1?.let {"?type=$it&" } ?: "?" + partipans?.let { "participants=$it" },activities1?.let { it }?:"Random")

        binding.tryanother.setOnClickListener {
            api(activities1?.let {"?type=$it&" } ?: "?" + partipans?.let { "participants=$it" },activities1?.let { it }?:"Random")
        }
    }

    private fun api(query: String,activities :String){
        CoroutineScope(Dispatchers.IO).launch {
            val apirest= getBoredAPI_random()
                .create(BoredAPI::class.java)
                .getBoredFromAPI("$query")
            val apicue : BoredResponse? = apirest.body()
            when{
                apirest.isSuccessful && activities == "Random" -> {
                    runOnUiThread {
                        binding.participantsNumber.text = apicue?.participants.toString()
                        binding.priceLevel.text =   apicue?.price?.let { calculate(it) }
                        binding.textView3.text  =   apicue?.activity.toString()
                        binding.textView2.text  =   "Random"
                        binding.activity.text = apicue?.type.toString()
                        binding.imageActivities.visibility = View.VISIBLE
                        binding.activity.visibility = View.VISIBLE
                    }
                }
                apirest.isSuccessful -> {
                    runOnUiThread {
                        binding.participantsNumber.text = apicue?.participants.toString()
                        binding.priceLevel.text =   apicue?.price?.let { calculate(it) }
                        binding.textView3.text  =   apicue?.activity.toString()
                        binding.textView2.text  =   apicue?.type.toString()
                    }
                }
            }
        }
    }

    fun calculate(digits: Float) : String{
        return when (digits) {
            0.0f -> "Free"
            in 0.0f..0.3f -> "Low"
            in 0.3f..0.6f -> "Medium"
            in 0.6f..1.0f -> "High"
            else -> ""
        }
    }
    // Access API on the 'random activities' endpoint:
    fun getBoredAPI_random(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("${BoredAPIaccess.BASE_URL}/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


}