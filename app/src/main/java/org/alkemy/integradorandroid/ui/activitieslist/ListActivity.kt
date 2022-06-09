package org.alkemy.integradorandroid.ui.activitieslist

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.alkemy.integradorandroid.R
import org.alkemy.integradorandroid.databinding.ActivityListBinding
import org.alkemy.integradorandroid.ui.activitiessuggestions.ViewSuggestions

class ListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListBinding

    private lateinit var participants : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        participants = intent.getStringExtra("Hola").toString()
        println(participants)

        binding.btnRandom.setOnClickListener {
            val intent = Intent(this, ViewSuggestions::class.java)
                .apply { putExtra("Activities2","Random")
                putExtra("participants",participants)}
                startActivity(intent)
        }

        binding.education.setOnClickListener {
            val intent = Intent(this, ViewSuggestions::class.java)
                .apply { putExtra("Activities2",binding.education.text.toString())
                    putExtra("participants",participants)}
            startActivity(intent)
        }
        binding.recreational.setOnClickListener {
            val intent = Intent(this, ViewSuggestions::class.java)
                .apply { putExtra("Activities2",binding.recreational.text.toString())
                    putExtra("participants",participants)}
            startActivity(intent)
        }
        binding.Social.setOnClickListener {
            val intent = Intent(this, ViewSuggestions::class.java)
                .apply { putExtra("Activities2",binding.Social.text.toString())
                    putExtra("participants",participants)}
            startActivity(intent)
        }
        binding.Diy.setOnClickListener {
            val intent = Intent(this, ViewSuggestions::class.java)
                .apply { putExtra("Activities2",binding.Diy.text.toString())
                    putExtra("participants",participants)}
            startActivity(intent)
        }
        binding.Charity.setOnClickListener {
            val intent = Intent(this, ViewSuggestions::class.java)
                .apply { putExtra("Activities2",binding.Charity.text.toString())
                    putExtra("participants",participants)}
            startActivity(intent)
        }
        binding.Cooking.setOnClickListener {
            val intent = Intent(this, ViewSuggestions::class.java)
                .apply { putExtra("Activities2",binding.Cooking.text.toString())
                        putExtra("participants",participants)}
            startActivity(intent)
        }
        binding.Music.setOnClickListener {
            val intent = Intent(this, ViewSuggestions::class.java)
                .apply { putExtra("Activities2",binding.Music.text.toString())
                    putExtra("participants",participants)}
            startActivity(intent)
        }
        binding.Busywork.setOnClickListener {
            val intent = Intent(this, ViewSuggestions::class.java)
                .apply { putExtra("Activities2",binding.Busywork.text.toString())
                    putExtra("participants",participants)}
            startActivity(intent)
        }
    }
}