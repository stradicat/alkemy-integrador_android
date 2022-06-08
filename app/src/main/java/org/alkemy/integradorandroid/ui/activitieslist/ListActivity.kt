package org.alkemy.integradorandroid.ui.activitieslist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.alkemy.integradorandroid.R
import org.alkemy.integradorandroid.databinding.ActivityListBinding

class ListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}