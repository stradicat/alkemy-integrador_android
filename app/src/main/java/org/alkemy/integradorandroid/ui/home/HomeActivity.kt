package org.alkemy.integradorandroid.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.google.android.material.snackbar.Snackbar
import org.alkemy.integradorandroid.R
import org.alkemy.integradorandroid.databinding.ActivityHomeBinding
import org.alkemy.integradorandroid.utils.Utils

private var participants: String? = null

class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding
    //private lateinit var adapter : DogAdapter
    private var dogList = mutableListOf<String>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //adapter = DogAdapter(dogList)
        //with(binding){
        binding.editText.addTextChangedListener { charSequence ->
            participants = if (Utils().validateInput(charSequence.toString())) {
                charSequence.toString()
            } else {
                Snackbar.make(
                    binding.root,
                    getString(R.string.home_snack_bar_error),
                    Snackbar.LENGTH_LONG
                ).show()
                null
            }
        }
    }
}