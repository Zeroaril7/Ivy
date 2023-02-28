package com.fakhril.ivy.onboarding.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import com.fakhril.ivy.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FirstBoarding.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstBoarding : Fragment() {

    private var title: String? = null
    private var description: String? = null
    private var imageResource = 0
    private lateinit var tvTitle: AppCompatTextView
    private lateinit var tvDescription: AppCompatTextView
    private lateinit var image: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            title = requireArguments().getString(ARG_PARAM1)
            description = requireArguments().getString(ARG_PARAM2)
            imageResource = requireArguments().getInt(ARG_PARAM3)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_first_boarding, container, false)
        tvTitle = view.findViewById(R.id.text_onboarding_title)
        tvDescription = view.findViewById(R.id.text_onboarding_description)
        image = view.findViewById(R.id.image_onboarding)
        tvTitle.text = title
        tvDescription.text = description
        image.setImageResource(imageResource)

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this.onDestroy()
    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"
        private const val ARG_PARAM3 = "param3"
        fun newInstance(
            title: String?,
            description: String?,
            imageResource: Int
        ): FirstBoarding {
            val fragment = FirstBoarding()
            val args = Bundle()
            args.putString(ARG_PARAM1, title)
            args.putString(ARG_PARAM2, description)
            args.putInt(ARG_PARAM3, imageResource)
            fragment.arguments = args
            return fragment
        }
    }
}