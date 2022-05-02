package com.reactnativenavigation.playground

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.reactnativenavigation.viewcontrollers.child.ChildControllersRegistry
import com.reactnativenavigation.viewcontrollers.modal.ModalStack
import com.reactnativenavigation.viewcontrollers.navigator.Navigator
import com.reactnativenavigation.viewcontrollers.overlay.OverlayManager
import com.reactnativenavigation.viewcontrollers.viewcontroller.RootPresenter

abstract class WixBaseFragment : Fragment() {
    lateinit var navigator: Navigator

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigator = Navigator(
            requireActivity(),
            ChildControllersRegistry(),
            ModalStack(requireContext()),
            OverlayManager(),
            RootPresenter()
        )
        navigator.bindViews()
    }
}