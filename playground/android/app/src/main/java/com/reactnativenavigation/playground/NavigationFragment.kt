package com.reactnativenavigation.playground

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.facebook.react.ReactInstanceManager
import com.facebook.react.ReactInstanceManager.ReactInstanceEventListener
import com.facebook.react.bridge.ReactContext
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler
import com.facebook.react.modules.core.PermissionListener
import com.reactnativenavigation.react.JsDevReloadHandler.ReloadListener
import com.reactnativenavigation.react.events.EventEmitter

class NavigationFragment : WixBaseFragment(), DefaultHardwareBackBtnHandler, ReloadListener,
    ReactInstanceEventListener {

    companion object {
        val TAG = NavigationFragment::class.java.simpleName
    }
    private val mPermissionListener: PermissionListener? = null
    private var reactInstanceManager: ReactInstanceManager? = null

    private var waitingForAppLaunchEvent = true
    private var isActivityReadyForUi = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        waitingForAppLaunchEvent = true

        val view = inflater.inflate(R.layout.fragment_screen, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigator.setContentLayout(view.findViewById(R.id.container))
        reactInstanceManager = (requireContext().applicationContext as MainApplication).reactNativeHost.reactInstanceManager
        reactInstanceManager?.addReactInstanceEventListener(this)
    }

    override fun onResume() {
        super.onResume()
        reactInstanceManager?.onHostResume(requireActivity())
        isActivityReadyForUi = true
        prepareReactApp()
    }

    override fun onPause() {
        super.onPause()
        isActivityReadyForUi = false
        reactInstanceManager?.onHostPause(requireActivity())
    }

    override fun onDestroy() {
        super.onDestroy()
        reactInstanceManager?.removeReactInstanceEventListener(this)
        reactInstanceManager?.onHostDestroy(requireActivity())
    }

    override fun invokeDefaultOnBackPressed() {
        println("invokeDefaultOnBackPressed")
    }

    override fun onReload() {
        println("onReload")
    }

    private fun emitAppLaunched(context: ReactContext) {
        if (!isActivityReadyForUi) return
        waitingForAppLaunchEvent = false
        EventEmitter(context).appLaunched()
    }

    private fun prepareReactApp() {
        if (shouldCreateContext()) {
            reactInstanceManager?.createReactContextInBackground()
        } else if (waitingForAppLaunchEvent) {
            if (reactInstanceManager?.currentReactContext != null) {
                emitAppLaunched(reactInstanceManager?.currentReactContext!!)
            }
        }
    }


    private fun shouldCreateContext(): Boolean {
        return reactInstanceManager?.hasStartedCreatingInitialContext() == false
    }

    override fun onReactContextInitialized(context: ReactContext) {
        emitAppLaunched(context)
    }
}
