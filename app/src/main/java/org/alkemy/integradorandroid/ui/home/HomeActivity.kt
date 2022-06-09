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

private var participants: String? = null

private val utils = Utils()

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.editText.addTextChangedListener { charSequence ->
            participants = if (utils.validateInput(charSequence.toString())) {
                charSequence.toString()
            } else {
                binding.startBtn.isFocusable = false
                utils.snackBar(binding.root, getString(R.string.home_snack_bar_error))
                null
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
            val intent = Intent(this, ListActivity::class.java)
                .apply { putExtra("participants",binding.editText.text.toString())}
            startActivity(intent)


        }
    }
}