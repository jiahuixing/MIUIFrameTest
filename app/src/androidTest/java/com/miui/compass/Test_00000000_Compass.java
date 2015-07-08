package com.miui.compass;

/**
 * Project name : marmot-cases2
 * Package name : com.miui.compass
 * Created by jiahuixing
 * Created on 2015-07-03
 * Created at 11:32
 */

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.Direction;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_00000000_Compass extends InstrumentationTestCase {

    public static final String PACKAGE_NAME_COMPASS = "com.miui.compass";
    public static final String ACTIVITY_NAME_COMPASS = "com.miui.compass/.CompassActivity";
    public static final String PACKAGE_NAME_KEY_GUARD = "com.android.keyguard";

    public static final String IMAGE_EXTENSION = ".png";

    public Marmot marmot;
    public Checker checker;
    public UiDevice uiDevice;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        marmot = new Marmot(this);
        checker = new Checker(marmot);
        uiDevice = marmot.getUiDevice();
        if (!marmot.isScreenOn()) {
            marmot.wakeUp();
            marmot.waitFor(1);
        }
        if (marmot.getCurrentPackageName().equals(PACKAGE_NAME_KEY_GUARD)) {
            marmot.move(Direction.UP);
            marmot.waitFor(1);
        }
    }

    public void test_Compass() throws Exception {
        marmot.log("launch compass.");
        marmot.launchActivity(ACTIVITY_NAME_COMPASS);
        marmot.waitFor(2);
        checker.assertTrue("compass not launched.", marmot
                .getCurrentPackageName().equals(PACKAGE_NAME_COMPASS));
        UiObject2 alertDialog;
        UiObject2 confirm;
        alertDialog = marmot.getUiObject(By.clazz("android.widget.TextView")
                .res("miui:id/alertTitle"));
        if (alertDialog != null) {
            marmot.log("deal with alert dialog.");
            marmot.saveScreenshot("alertDialog" + IMAGE_EXTENSION);
            confirm = marmot.getUiObject(By.clazz("android.widget.Button")
                    .text("同意并继续"));
            confirm.click();
            marmot.waitFor(2);
        }
        UiObject2 calibratePressure;
        calibratePressure = marmot.getUiObject(By
                .clazz("android.widget.Button").text("校准海拔"));
        if (calibratePressure != null) {
            marmot.log("calibratePressure.");
            // marmot.log(String.format("calibratePressure: %s",
            // calibratePressure.getClassName()));
            marmot.saveScreenshot("calibratePressure" + IMAGE_EXTENSION);
            calibratePressure.click();
            marmot.waitFor(2);
            confirm = marmot.getUiObject(By.clazz("android.widget.Button")
                    .text("确定"));
            if (confirm != null) {
                marmot.log("not connected.");
                marmot.saveScreenshot("noNet" + IMAGE_EXTENSION);
                confirm.click();
                marmot.waitFor(1);
            }
        }
        for (int i = 0; i < 2; i++) {
            marmot.move(Direction.LEFT);
            marmot.waitFor(1);
        }
        for (int i = 0; i < 2; i++) {
            marmot.move(Direction.RIGHT);
            marmot.waitFor(1);
        }
    }

    @Override
    public void tearDown() throws Exception {
        marmot.pressHome();
        super.tearDown();
    }
}
