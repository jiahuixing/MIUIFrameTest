package com.android.calculator;

/**
 * Project name: MIUIFrameTest
 * Package name: com.android.calculator
 * Created by jiahuixing
 * Created at 2015--07-05 15:54
 */

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

import java.util.Random;

public class TestCalculator extends InstrumentationTestCase {


    public static final String PACKAGE_NAME_CALCULATOR = "";
    public static final String ACTIVITY_NAME_CALCULATOR = "";

    public Marmot marmot;
    public Checker checker;
    public UiDevice uiDevice;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        marmot = new Marmot(this);
        checker = new Checker(marmot);
        uiDevice = marmot.getUiDevice();
    }

    public void test_Calculator() throws Exception {
        marmot.log("launch calculator");
        marmot.launchActivity(ACTIVITY_NAME_CALCULATOR);
        marmot.waitFor(2);
        checker.assertTrue("calculator not launched", marmot.getCurrentPackageName().equals(PACKAGE_NAME_CALCULATOR));
        UiObject2 buttons, button;
        buttons = marmot.getUiObject(By.clazz("").res(""));
        Random random = new Random();
        int buttonCount, rnd;
        buttonCount = buttons.getChildCount();
        for (int i = 0; i < buttonCount; i++) {
            rnd = random.nextInt(buttonCount);
            button = buttons.getChildren().get(rnd);
            button.click();
            marmot.waitFor(0.5);
        }
    }

    @Override
    public void tearDown() throws Exception {
        marmot.pressHome();
        super.tearDown();
    }
}
