package com.example.tracetogether.onboarding

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tracetogether.MainActivity
import com.example.tracetogether.Preference
import com.example.tracetogether.R
import com.example.tracetogether.logging.CentralLog

/*
    Fragment for the onboarding complete screen
 */
class SetupCompleteFragment : OnboardingFragmentInterface() {

    private var listener: OnFragmentInteractionListener? = null
    private val TAG: String = "SetupCompleteFragment"
    private lateinit var mainContext: Context

    override fun becomesVisible() {}

    override fun onButtonClick(view: View) {
        CentralLog.d(TAG, "OnButtonClick 2")
        Preference.putCheckpoint(view.context, 0)
        Preference.putIsOnBoarded(view.context, true)
        var intent = Intent(context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        context?.startActivity(intent)
        (context as OnboardingActivity?)?.finish()
    }

    override fun onBackButtonClick(view: View) {}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_setup_complete, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setButtonText(getString(R.string.finish_button))
        setButtonIcon(R.drawable.ic_checkmark_white)
    }

    override fun onUpdatePhoneNumber(num: String) {}

    override fun onError(error: String) {}

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainContext = context;
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }
}
