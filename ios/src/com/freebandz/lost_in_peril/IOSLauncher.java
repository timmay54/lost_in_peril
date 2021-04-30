package com.freebandz.lost_in_peril;

import org.robovm.apple.foundation.NSAutoreleasePool;
import org.robovm.apple.foundation.NSURL;
import org.robovm.apple.uikit.UIApplication;
import org.robovm.apple.uikit.UIApplicationLaunchOptions;
import org.robovm.apple.uikit.UIApplicationOpenURLOptions;
import org.robovm.pods.facebook.core.FBSDKAppEvents;
import org.robovm.pods.facebook.core.FBSDKApplicationDelegate;

import com.badlogic.gdx.backends.iosrobovm.IOSApplication;
import com.badlogic.gdx.backends.iosrobovm.IOSApplicationConfiguration;

import com.freebandz.lost_in_peril.Lost_in_Peril;

public class IOSLauncher extends IOSApplication.Delegate {
    @Override
    protected IOSApplication createApplication() {
        IOSApplicationConfiguration config = new IOSApplicationConfiguration();
        return new IOSApplication(new Lost_In_Peril(), config);
    }

    public static void main(String[] argv) {
        NSAutoreleasePool pool = new NSAutoreleasePool();
        UIApplication.main(argv, null, IOSLauncher.class);
        Lost_In_Peril.platformName = "ios";
        pool.close();
    }
}