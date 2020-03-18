package com.github.appintro.example.ui.mainTabs.intro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.github.appintro.ISlidePolicy
import com.github.appintro.appintroexample.R

class CustomTextFragment : Fragment(), ISlidePolicy {

    private val editText : EditText? by lazy {
        view?.findViewById<EditText>(R.id.password)
    }

    override val isPolicyRespected: Boolean
        get() = editText?.text.toString().contentEquals("1234")

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.custom_text_layout, container, false)


    override fun onUserIllegallyRequestedNextPage() {
        Toast.makeText(requireContext(), "Wrong password", Toast.LENGTH_SHORT).show()
    }

    companion object {
        @JvmStatic
        fun newInstance(): CustomTextFragment {
            return CustomTextFragment()
        }
    }
}