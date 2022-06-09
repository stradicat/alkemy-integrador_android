package org.alkemy.integradorandroid.ui.activitieslist

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.alkemy.integradorandroid.R
import org.alkemy.integradorandroid.databinding.ActivityListBinding
import org.alkemy.integradorandroid.ui.suggestion.SuggestionActivity

class ListActivity : AppCompatActivity() {
    private var participants : String? = null
    private lateinit var binding: ActivityListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the Intent that started this activity and extract the string
        participants = intent.getStringExtra(PARTICIPANTS)

        binding.randomBtn.setOnClickListener { goToSuggestionActivity("Random") }
        binding.education.setOnClickListener { goToSuggestionActivity(binding.education.text.toString())  }
        binding.recreational.setOnClickListener { goToSuggestionActivity(binding.recreational.text.toString()) }
        binding.Social.setOnClickListener { goToSuggestionActivity(binding.Social.text.toString()) }
        binding.diy.setOnClickListener { goToSuggestionActivity(binding.diy.text.toString()) }
        binding.Charity.setOnClickListener { goToSuggestionActivity(binding.Charity.text.toString()) }
        binding.Cooking.setOnClickListener { goToSuggestionActivity(binding.Cooking.text.toString()) }
        binding.Relaxation.setOnClickListener { goToSuggestionActivity(binding.Relaxation.text.toString()) }
        binding.Music.setOnClickListener { goToSuggestionActivity(binding.Music.text.toString()) }
        binding.Busywork.setOnClickListener { goToSuggestionActivity(binding.Busywork.text.toString()) }

    }

    private fun goToSuggestionActivity(type: String?){
        val intent = Intent(this, SuggestionActivity::class.java).apply {
            putExtra(PARTICIPANTS, participants)
            putExtra(ACTIVITY_TYPE, type.toString())
        }
        startActivity(intent)
    }

    companion object {
        const val PARTICIPANTS = "participants"
        const val ACTIVITY_TYPE = "activity_type"
    }
}