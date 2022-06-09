package org.alkemy.integradorandroid.ui.suggestion

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.alkemy.integradorandroid.api.BoredAPI
import org.alkemy.integradorandroid.api.BoredAPIaccess
import org.alkemy.integradorandroid.api.BoredResponse
import org.alkemy.integradorandroid.databinding.ActivitySuggestionBinding
import org.alkemy.integradorandroid.ui.activitieslist.ListActivity.Companion.ACTIVITY_TYPE
import org.alkemy.integradorandroid.ui.activitieslist.ListActivity.Companion.PARTICIPANTS
import org.alkemy.integradorandroid.utils.Utils
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SuggestionActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySuggestionBinding
    private val utils = Utils()
    private var participants : String? = null
    private var type : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySuggestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        participants = intent.getStringExtra(PARTICIPANTS)
        type = intent.getStringExtra(ACTIVITY_TYPE)

        if(type == "Random"){
            type = null
        }

        api(type?.let { "?type=$it&" } ?: ("?" + participants?.let { "participants=$it" }),
            type ?:"Random")

        binding.backBtn.setOnClickListener{
            onBackPressed()
        }

        binding.tryAnother.setOnClickListener {
            api(type?.let { "?type=$it&" } ?: ("?" + participants?.let { "participants=$it" }),
                type ?:"Random")
        }

/*
        binding.participantsNumber.text = participants ?: "0"
        binding.title.text = type ?: "Random"
        binding.randomActivity.text = type ?: ""

        utils.snackBar(binding.root, participants.toString() + " " + type.toString())*/

    }

    private fun api(query: String,activities :String){
        CoroutineScope(Dispatchers.IO).launch {
            val apiRest= getBoredAPRandom()
                .create(BoredAPI::class.java)
                .getBoredFromAPI("$query")
            val apicue : BoredResponse? = apiRest.body()
            when{
                apiRest.isSuccessful && activities == "Random" -> {
                    runOnUiThread {
                        binding.participantsNumber.text = apicue?.participants.toString()
                        binding.priceLevel.text =   apicue?.price?.let { calculate(it) }
                        binding.randomActivity.text  =   apicue?.activity.toString()
                        binding.title.text  =   "Random"
                        binding.activity.text = apicue?.type.toString()
                        binding.imageActivities.visibility = View.VISIBLE
                        binding.activity.visibility = View.VISIBLE
                    }
                }
                apiRest.isSuccessful -> {
                    runOnUiThread {
                        binding.participantsNumber.text = apicue?.participants.toString()
                        binding.priceLevel.text =   apicue?.price?.let { calculate(it) }
                        binding.randomActivity.text  =   apicue?.activity.toString()
                        binding.title.text  =   apicue?.type.toString()
                    }
                }
            }
        }
    }

    private fun calculate(digits: Float) : String{
        return when (digits) {
            0.0f -> "Free"
            in 0.0f..0.3f -> "Low"
            in 0.3f..0.6f -> "Medium"
            in 0.6f..1.0f -> "High"
            else -> ""
        }
    }
    // Access API on the 'random activities' endpoint:
    private fun getBoredAPRandom(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("${BoredAPIaccess.BASE_URL}/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}