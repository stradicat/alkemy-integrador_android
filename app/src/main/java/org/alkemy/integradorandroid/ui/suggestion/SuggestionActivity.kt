package org.alkemy.integradorandroid.ui.suggestion

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.alkemy.integradorandroid.R
import org.alkemy.integradorandroid.api.BoredAPI
import org.alkemy.integradorandroid.api.BoredResponse
import org.alkemy.integradorandroid.databinding.ActivitySuggestionBinding
import org.alkemy.integradorandroid.ui.activitieslist.ListActivity.Companion.ACTIVITY_TYPE
import org.alkemy.integradorandroid.ui.activitieslist.ListActivity.Companion.PARTICIPANTS
import org.alkemy.integradorandroid.utils.Utils
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import org.alkemy.integradorandroid.api.BoredAPIaccess.Companion as BoredAPIaccess

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
        recuestApi(type?.let { "?type=$it&" + participants?.let { "participants=$it" } } ?: ("?" + participants?.let { "participants=$it" }),
            type.let { it } ?:"Random")

        binding.backBtn.setOnClickListener{
            onBackPressed()
        }

        binding.tryAnother.setOnClickListener {
            recuestApi(type?.let { "?type=$it&" + participants?.let { "participants=$it" }  } ?: ("?" + participants?.let { "participants=$it" }),
                type.let { it } ?:"Random")
        }
    }

    private fun recuestApi(query: String, activities :String){
        CoroutineScope(Dispatchers.IO).launch {
            val restApi= getBoredAP()
                .create(BoredAPI::class.java)
                .getBoredFromAPI(query)
            val bodyApi : BoredResponse? = restApi.body()



            when{
                restApi.isSuccessful && activities == "Random" -> {
                    runOnUiThread {
                        binding.participantsNumber.text = bodyApi?.participants.toString()
                        binding.priceLevel.text =   bodyApi?.price?.let { calculate(it) }
                        binding.randomActivity.text  =   bodyApi?.type.toString()
                        binding.title.text  =   "Random"
                        binding.activity.text = bodyApi?.activity.toString()
                        binding.imageActivities.visibility = View.VISIBLE
                        binding.randomActivity.visibility = View.VISIBLE
                    }
                }
                restApi.isSuccessful -> {
                    runOnUiThread {
                        if (bodyApi?.activity.toString().isEmpty() || bodyApi?.activity == null) {

                            utils.snackBar(
                                binding.root,
                                getString(R.string.suggestion_snack_bar_error)
                            )
                            Handler(Looper.getMainLooper()).postDelayed(
                                {
                                    onBackPressed()
                                },
                                2000 // value in milliseconds
                            )

                        } else {
                            binding.participantsNumber.text = bodyApi?.participants.toString()
                            binding.priceLevel.text = bodyApi?.price?.let { calculate(it) }
                            binding.activity.text = bodyApi?.activity.toString()
                            binding.title.text = bodyApi?.type.toString()
                        }

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
    private fun getBoredAP(): Retrofit {
        println(BoredAPIaccess.BASE_URL)
        return Retrofit.Builder()
            .baseUrl("${BoredAPIaccess.BASE_URL}/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}