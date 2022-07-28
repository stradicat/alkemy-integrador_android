package org.alkemy.integradorandroid.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import org.alkemy.integradorandroid.databinding.PopupTermsAndConditionsBinding

class TermsAndConditionPopUp: DialogFragment() {

    private var _binding: PopupTermsAndConditionsBinding? = null
    private val binding get() = _binding!!

    // Show Terms & Conditions pop-up
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = PopupTermsAndConditionsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.close.setOnClickListener {
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}