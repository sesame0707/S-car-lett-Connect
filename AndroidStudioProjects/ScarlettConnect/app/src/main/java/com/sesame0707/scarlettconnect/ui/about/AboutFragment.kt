package com.sesame0707.scarlettconnect.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.sesame0707.scarlettconnect.BuildConfig
import com.sesame0707.scarlettconnect.R
import com.sesame0707.scarlettconnect.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {

    private var _binding: FragmentAboutBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var textAppVersionContent: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        val root: View = binding.root

        textAppVersionContent = root.findViewById(R.id.textViewAppVersionContent)
        textAppVersionContent.text = BuildConfig.VERSION_NAME

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}