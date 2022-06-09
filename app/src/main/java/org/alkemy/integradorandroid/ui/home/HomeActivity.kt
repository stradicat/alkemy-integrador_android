package org.alkemy.integradorandroid.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import org.alkemy.integradorandroid.R
import org.alkemy.integradorandroid.databinding.ActivityHomeBinding
import org.alkemy.integradorandroid.ui.activitieslist.ListActivity
import org.alkemy.integradorandroid.utils.Utils

class HomeActivity : AppCompatActivity() {

    private var participants: String? = null

    //private lateinit var adapter : DogAdapter
    private var dogList = mutableListOf<String>()
    private val utils = Utils()
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.editText.addTextChangedListener { s ->
            when {
                s.toString() == "0" -> {
                    binding.startBtn.isEnabled = false
                    utils.snackBar(binding.root, getString(R.string.home_snack_bar_error))
                }
                s.toString().isEmpty() -> {
                    binding.startBtn.isEnabled = true
                    participants = null
                }
                else -> {
                    participants = s.toString()
                    binding.startBtn.isEnabled = true
                }
            }
        }

        binding.termsAndConditions.setOnClickListener {
            val dialogPopUp = TermsAndConditionPopUp()
            dialogPopUp.setStyle(
                DialogFragment.STYLE_NORMAL,
                android.R.style.Theme_DeviceDefault_NoActionBar_Fullscreen
            )
            dialogPopUp.show(supportFragmentManager, "termsAndConditions")
        }

        binding.startBtn.setOnClickListener {
            val intent = Intent(this, ListActivity::class.java).apply {
                putExtra(PARTICIPANTS, participants)
            }
            startActivity(intent)
        }
    }

    companion object{
        const val PARTICIPANTS = "participants"
    }
}