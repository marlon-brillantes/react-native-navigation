package com.reactnativenavigation.playground;

import android.app.Application;

import com.facebook.react.PackageList;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.reactnativenavigation.NavigationApplication;
import com.reactnativenavigation.react.NavigationPackage;
import com.reactnativenavigation.react.NavigationReactNativeHost;
import com.reactnativenavigation.react.ReactGateway;
import com.reactnativenavigation.viewcontrollers.externalcomponent.ExternalComponentCreator;
import com.swmansion.reanimated.ReanimatedJSIModulePackage;
import com.facebook.react.bridge.JSIModulePackage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainApplication extends Application implements ReactApplication {

    private final ReactNativeHost mReactNativeHost =
            new ReactNativeHost(this) {
                @Override
                protected JSIModulePackage getJSIModulePackage() {
                    return new ReanimatedJSIModulePackage();
                }
                @Override
                protected String getJSMainModuleName() {
                    return "index";
                }

                @Override
                public boolean getUseDeveloperSupport() {
                    return BuildConfig.DEBUG;
                }

                @Override
                public List<ReactPackage> getPackages() {
                    ArrayList<ReactPackage> packages = new PackageList(this).getPackages();
                    packages.add(new WixNavigationPackage(mReactNativeHost));
                    return packages;
                }
            };

    private ReactGateway reactGateway;

    final Map<String, ExternalComponentCreator> externalComponents = new HashMap<>();

    @Override
    public void onCreate() {
        super.onCreate();
        reactGateway = new ReactGateway(getReactNativeHost());
        registerExternalComponent("RNNCustomComponent", new FragmentCreator());
    }

    @Override
    public ReactNativeHost getReactNativeHost() {
        return mReactNativeHost;
    }

    public ReactGateway getReactGateway() {
        return reactGateway;
    }

    /**
     * Register a native View which can be displayed using the given {@code name}
     * @param name Unique name used to register the native view
     * @param creator Used to create the view at runtime
     */
    @SuppressWarnings("unused")
    public void registerExternalComponent(String name, ExternalComponentCreator creator) {
        if (externalComponents.containsKey(name)) {
            throw new RuntimeException("A component has already been registered with this name: " + name);
        }
        externalComponents.put(name, creator);
    }

    public final Map<String, ExternalComponentCreator> getExternalComponents() {
        return externalComponents;
    }
}
