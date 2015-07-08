package com.android.calculator;

/**
 * Project name : marmot-cases2
 * Package name : com.android.calculator
 * Created by jiahuixing
 * Created on 2015-07-06
 * Created at 13:48
 */

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.Direction;
import android.support.test.uiautomator.UiCollection;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiSelector;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

import java.util.Random;

public class Test_00000000_Calculator extends InstrumentationTestCase {

    public static final String PACKAGE_NAME_CALCULATOR = "com.android.calculator2";
    public static final String ACTIVITY_NAME_CALCULATOR = "com.android.calculator2/.Calculator";
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

    public void test_Calculator() throws Exception {
        marmot.log("launch calculator");
        marmot.launchActivity(ACTIVITY_NAME_CALCULATOR);
        marmot.waitFor(2);
        checker.assertTrue("calculator not launched", marmot
                .getCurrentPackageName().equals(PACKAGE_NAME_CALCULATOR));
        UiObject2 switcher;
        switcher = marmot.getUiObject(By.clazz("android.widget.ImageView").res(
                "com.android.calculator2:id/switch_btn"));
        // UiObject2 simpleButtonContainer, scientificButtonContainer, button;
        UiCollection simpleButtonContainer, scientificButtonContainer;
        UiObject button;
        simpleButtonContainer = new UiCollection(new UiSelector().className(
                "android.widget.LinearLayout").resourceId(
                "com.android.calculator2:id/simple_pad"));
        scientificButtonContainer = new UiCollection(new UiSelector()
                .className("android.widget.LinearLayout").resourceId(
                        "com.android.calculator2:id/scientific_pad"));
        // simpleButtonContainer = marmot.getUiObject(By.clazz(
        // "android.widget.LinearLayout").res(
        // "com.android.calculator2:id/simple_pad"));
        // scientificButtonContainer = marmot.getUiObject(By.clazz(
        // "android.widget.LinearLayout").res(
        // "com.android.calculator2:id/scientific_pad"));
        Random random = new Random();
        int buttonCount, rnd;
        for (int i = 0; i < 2; i++) {
            if (simpleButtonContainer.exists()) {
                marmot.log(String.format("simpleButtonContainer: %s", i));
                marmot.saveScreenshot("simpleButtonContainer" + IMAGE_EXTENSION);
                buttonCount = simpleButtonContainer
                        .getChildCount(new UiSelector()
                                .className("android.widget.Button"));
                for (int j = 0; j < buttonCount; j++) {
                    rnd = random.nextInt(buttonCount);
                    button = simpleButtonContainer.getChild(new UiSelector()
                            .className("android.widget.Button").instance(rnd));
                    marmot.log(String
                            .format("----------------\nbutton: %s\ngetContentDescription: %s\ngetText: %s",
                                    j, button.getContentDescription(),
                                    button.getText()));
                    button.click();
                    marmot.waitFor(0.5);
                }
                switcher.click();
                marmot.waitFor(1);
                i++;
            }
            if (scientificButtonContainer.exists()) {
                marmot.log(String.format("scientificButtonContainer: %s", i));
                marmot.saveScreenshot("scientificButtonContainer"
                        + IMAGE_EXTENSION);
                buttonCount = scientificButtonContainer
                        .getChildCount(new UiSelector()
                                .className("android.widget.Button"));
                for (int j = 0; j < buttonCount; j++) {
                    rnd = random.nextInt(buttonCount);
                    button = scientificButtonContainer
                            .getChild(new UiSelector().className(
                                    "android.widget.Button").instance(rnd));
                    marmot.log(String
                            .format("----------------\nbutton: %s\ngetContentDescription: %s\ngetText: %s",
                                    j, button.getContentDescription(),
                                    button.getText()));
                    button.click();
                    marmot.waitFor(0.5);
                }
                switcher.click();
                marmot.waitFor(1);
                i++;
            }
        }
        // if (simpleButtonContainer != null) {
        // marmot.log("simple calculator");
        // buttonCount = simpleButtonContainer.getChildCount();
        // marmot.log("simpleButtonContainer = " + buttonCount);
        // for (int i = 0; i < buttonCount; i++) {
        // rnd = random.nextInt(buttonCount);
        // button = simpleButtonContainer.getChildren().get(rnd);
        // button.click();
        // marmot.waitFor(0.5);
        // }
        // switcher.click();
        // marmot.waitFor(2);
        // }
        // if (scientificButtonContainer != null) {
        // marmot.log("scientific calculator");
        // buttonCount = scientificButtonContainer.getChildCount();
        // marmot.log("scientificButtonContainer = " + buttonCount);
        // for (int i = 0; i < buttonCount; i++) {
        // rnd = random.nextInt(buttonCount);
        // button = scientificButtonContainer.getChildren().get(rnd);
        // button.click();
        // marmot.waitFor(0.5);
        // }
        // switcher.click();
        // marmot.waitFor(2);
        // }
    }

    @Override
    public void tearDown() throws Exception {
        marmot.pressHome();
        super.tearDown();
    }
}
