package com.reactnativenavigation.playground

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_start.*

class StartFragment : Fragment() {

    companion object {
        val TAG = StartFragment::class.java.simpleName
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startFragmentButton.setOnClickListener {
            val ft = requireActivity().supportFragmentManager.beginTransaction()
            ft.replace(R.id.fragmentPlaceHolder, NavigationFragment(), NavigationFragment.TAG)
            ft.commit()
        }
    }
}
