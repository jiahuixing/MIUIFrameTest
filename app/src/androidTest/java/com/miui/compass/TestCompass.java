package com.miui.compass;

/**
 * Project name: MIUIFrameTest
 * Package name: com.miui.compass
 * Created by jiahuixing
 * Created at 2015--07-03 21:55
 */

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.Direction;
import android.support.test.uiautomator.UiObject2;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class TestCompass extends InstrumentationTestCase {

    public static final String PACKAGE_NAME_COMPASS = "";
    public static final String ACTIVITY_NAME_COMPASS = "";
    public Marmot marmot;
    public Checker checker;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        marmot = new Marmot(this);
        checker = new Checker(marmot);
    }

    public void test_Compass() throws Exception {
        marmot.log("launch compass.");
        marmot.launchActivity(ACTIVITY_NAME_COMPASS);
        marmot.waitFor(2);
        checker.assertTrue("compass not launched.", marmot.getCurrentPackageName().equals(PACKAGE_NAME_COMPASS));
        UiObject2 alertDialog;
        UiObject2 confirm;
        alertDialog = marmot.getUiObject(By.clazz("").res(""));
        if (alertDialog != null) {
            marmot.log("deal with alert dialog.");
            marmot.saveScreenshot("alertDialog");
            confirm = marmot.getUiObject(By.clazz("").text(""));
            confirm.click();
            marmot.waitFor(2);
        }
        UiObject2 calibratePressure;
        calibratePressure = marmot.getUiObject(By.clazz("").text(""));
        if (calibratePressure != null) {
            marmot.log("calibratePressure.");
            marmot.saveScreenshot("calibratePressure");
            calibratePressure.click();
            marmot.waitFor(2);
            confirm = marmot.getUiObject(By.clazz("").text(""));
            if (confirm != null) {
                marmot.log("not connected.");
                marmot.saveScreenshot("noNet");
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
