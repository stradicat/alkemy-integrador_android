package org.alkemy.integradorandroid.ui.suggestion

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.alkemy.integradorandroid.databinding.ActivitySuggestionBinding
import org.alkemy.integradorandroid.ui.activitieslist.ListActivity.Companion.ACTIVITY_TYPE
import org.alkemy.integradorandroid.ui.activitieslist.ListActivity.Companion.PARTICIPANTS
import org.alkemy.integradorandroid.utils.Utils

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


        binding.participantsNumber.text = participants ?: "0"
        binding.title.text = type ?: "Random"
        binding.randomActivity.text = type ?: ""

        utils.snackBar(binding.root, participants.toString() + " " + type.toString())
        binding.backBtn.setOnClickListener{
            onBackPressed()
        }
    }
}