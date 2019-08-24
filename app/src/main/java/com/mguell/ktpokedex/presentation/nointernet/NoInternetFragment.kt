package com.mguell.ktpokedex.presentation.nointernet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat
import com.mguell.ktpokedex.R
import com.mguell.ktpokedex.presentation.BaseFragment
import kotlinx.android.synthetic.main.no_internet_fragment.*

class NoInternetFragment : BaseFragment() {

    override fun onCreateView(
        @NonNull inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.no_internet_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        tvReturnToBackpack.setOnTouchListener { _: View, event: MotionEvent ->
            retry(event)
        }
        mCallback.setSearchButtonVisibility(View.GONE)
    }

    /**
     * Action of touching the retry TextView, if the event is the start of the touch event,
     * changes the color of the TextView (in order to give some feedback to the user), if the
     * event is the finish, tries to connect to the API via the MainActivity.
     *
     * @param motionEvent press or stop pressing the TextView event.
     * @return true if the method is correctly finished, false otherwise.
     */
    private fun retry(motionEvent: MotionEvent): Boolean {
        if (motionEvent.action == MotionEvent.ACTION_DOWN) {
            tvReturnToBackpack.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.accent_dark
                )
            )
        } else if (motionEvent.action == MotionEvent.ACTION_UP) {
            mCallback.openBackpack()
        }
        return true
    }

    companion object {

        fun newInstance(): NoInternetFragment {
            return NoInternetFragment()
        }
    }
}