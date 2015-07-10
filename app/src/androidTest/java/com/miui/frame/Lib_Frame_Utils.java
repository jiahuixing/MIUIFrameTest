package com.miui.frame;

/**
 * Project name : marmot-cases2
 * Package name : com.miui.frame
 * Created by jiahuixing
 * Created on 2015-07-09
 * Created at 10:44
 */

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.Direction;
import android.support.test.uiautomator.UiCollection;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;

import com.miui.marmot.lib.Marmot;

import java.util.Random;

public class Lib_Frame_Utils {

    public static void unLock(Marmot marmot) {
        if (!marmot.isScreenOn()) {
            marmot.log("wake up phone.");
            marmot.wakeUp();
            marmot.waitFor(1);
        }
        if (marmot.getCurrentPackageName().equals(
                Lib_Frame_Constants.PACKAGE_NAME_KEY_GUARD)) {
            marmot.log("unlock phone.");
            marmot.move(Direction.UP);
            marmot.waitFor(1);
        }
    }

    public static boolean openWlan(Marmot marmot) {
        marmot.log("launch settings.");
        marmot.launchActivity(Lib_Frame_Constants.ACTIVITY_NAME_SETTINGS);
        marmot.waitFor(2);
        UiObject2 wlanSettings;
        wlanSettings = marmot.getUiObject(By.clazz("").text(""));
        wlanSettings.click();
        marmot.waitFor(2);
        UiObject2 switcher;
        switcher = marmot.getUiObject(By.clazz("").res(""));
        if (!switcher.isChecked()) {
            marmot.log("switcher not checked.");
            switcher.click();
            marmot.waitFor(1);
        }
        return false;
    }

    public static boolean closeWlan(Marmot marmot) {
        marmot.log("launch settings.");
        marmot.launchActivity(Lib_Frame_Constants.ACTIVITY_NAME_SETTINGS);
        marmot.waitFor(2);
        UiObject2 wlanSettings;
        wlanSettings = marmot.getUiObject(By.clazz("").text(""));
        wlanSettings.click();
        marmot.waitFor(2);
        UiObject2 switcher;
        switcher = marmot.getUiObject(By.clazz("").res(""));
        if (switcher.isChecked()) {
            marmot.log("switcher checked.");
            switcher.click();
            marmot.waitFor(1);
        }
        return false;
    }

    public static boolean exist(UiObject2 uiObject2) {
        return uiObject2 != null;
    }

    public static int getRandomInt(int max, int min) {
        int randomInt;
        Random random = new Random();
        if ((max > min)) {
            randomInt = random.nextInt(max) % (max - min + 1) + min;
        } else {
            randomInt = random.nextInt(min) % (min - max + 1) + max;
        }
        return randomInt;
    }

    public static void calculateNumber(Marmot marmot, int firstNumber,
                                       int secondNumber, String operation) {
        marmot.log(String.format("calculateNumber: %s %s %s = ?", firstNumber,
                operation, secondNumber));
        UiObject2 first, second, operate, equal;
        first = marmot.getUiObject(By.clazz("android.widget.Button").res(
                Lib_Frame_Constants.CALCULATOR_NUMBER_RES.get(firstNumber)));
        second = marmot.getUiObject(By.clazz("android.widget.Button").res(
                Lib_Frame_Constants.CALCULATOR_NUMBER_RES.get(secondNumber)));
        operate = marmot.getUiObject(By.clazz("android.widget.Button").res(
                Lib_Frame_Constants.CALCULATOR_OPERATION_RES.get(operation)));
        equal = marmot.getUiObject(By.clazz("android.widget.Button").res(
                Lib_Frame_Constants.CALCULATOR_OPERATION_RES
                        .get(Lib_Frame_Constants.OPERATION_EQUAL)));
        first.click();
        marmot.waitFor(2);
        operate.click();
        marmot.waitFor(2);
        second.click();
        marmot.waitFor(2);
        equal.click();
        marmot.waitFor(2);
    }

    public static void randomCalculator(Marmot marmot)
            throws UiObjectNotFoundException {
        // UiObject2 simpleButtonContainer, scientificButtonContainer, button;
        UiObject2 switcher;
        UiCollection simpleButtonContainer, scientificButtonContainer;
        UiObject button;
        switcher = marmot.getUiObject(By.clazz("android.widget.ImageView").res(
                "com.android.calculator2:id/switch_btn"));
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
                marmot.saveScreenshot("simpleButtonContainer"
                        + Lib_Frame_Constants.IMAGE_EXTENSION);
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
                        + Lib_Frame_Constants.IMAGE_EXTENSION);
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

    public static boolean dialNumber(Marmot marmot, String dialNumber) {
        UiObject2 dialNumberButton;
        char temp;
        int number;
        for (int i = 0; i < dialNumber.length(); i++) {
            temp = dialNumber.charAt(i);
            if (Character.isDigit(temp)) {
                number = Integer.parseInt(String.valueOf(temp));
                dialNumberButton = marmot.getUiObject(By.clazz("android.widget.ImageView").res(Lib_Frame_Constants.DIAL_RES.get(number)));
                dialNumberButton.click();
                marmot.waitFor(1);
            } else {
                return false;
            }
        }
        return true;
    }

}
