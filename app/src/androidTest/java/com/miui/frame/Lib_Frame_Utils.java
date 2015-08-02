package com.miui.frame;

/**
 * Project name : marmot-cases2
 * Package name : com.miui.frame
 * Created by jiahuixing
 * Created on 2015-07-09
 * Created at 10:44
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.Direction;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
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

    public static void backToPackage(Marmot marmot, String packageName) {
        String currentPackageName;
        while (true) {
            currentPackageName = marmot.getCurrentPackageName();
            if (currentPackageName.equals(packageName)) {
                break;
            }
            marmot.pressBack();
            marmot.waitFor(1);
        }
    }

    public static void launchActivityNoHistory(Context context,
            String activityName) {
        Intent intent = new Intent();
        String pkgName = activityName.split("/")[0];
        String atyName = activityName.split("/")[1];
        intent.setClassName(pkgName,
                atyName.startsWith(".") ? (pkgName + atyName) : atyName);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_NO_HISTORY);
        context.startActivity(intent);
    }

    public static boolean openWlan(Marmot marmot)
            throws UiObjectNotFoundException {
        marmot.log("launch settings.");
        marmot.launchActivity(Lib_Frame_Constants.ACTIVITY_NAME_SETTINGS);
        UiObject2 wlanSettings;
        wlanSettings = marmot.getUiObject(By.clazz("android.widget.TextView")
                .text("WLAN"));
        wlanSettings.click();
        marmot.waitFor(2);
        UiObject2 switcher;
        switcher = marmot
                .getUiObject(By.clazz("android.widget.TextView").res("开启WLAN"))
                .getParent().getParent()
                .findObject(By.clazz("android.widget.CheckBox"));
        if (!switcher.isChecked()) {
            marmot.log("switcher not checked.");
            switcher.click();
            marmot.waitFor(2);
        }
        marmot.waitFor(3);
        String wlanName, wlanName5G, wlanPassword;
        wlanName = "Frame_Privacy";
        wlanName5G = "Frame_Privacy_5G";
        wlanPassword = "jiahuixing";
        UiScrollable wlanList;
        wlanList = new UiScrollable(
                new UiSelector().className("android.widget.ListView"));
        UiObject wlan;
        wlan = wlanList.getChildByText(
                new UiSelector().className("android.widget.CheckedTextView"),
                wlanName5G, true);
        if (wlan == null) {
            wlan = wlanList.getChildByText(new UiSelector()
                    .className("android.widget.CheckedTextView"), wlanName,
                    true);
        }
        wlan.click();
        marmot.waitFor(2);
        UiObject2 passwordEditor;
        passwordEditor = marmot.getUiObject(By.clazz("android.widget.EditText")
                .res("com.android.settings:id/password"));
        if (passwordEditor != null) {
            passwordEditor.setText(wlanPassword);
            marmot.waitFor(2);
            UiObject2 connect;
            connect = marmot.getUiObject(By.clazz("android.widget.Button")
                    .text("连接"));
            connect.click();
            marmot.waitFor(2);
        }
        return true;
    }

    public static boolean closeWlan(Marmot marmot) {
        marmot.log("launch settings.");
        marmot.launchActivity(Lib_Frame_Constants.ACTIVITY_NAME_SETTINGS);
        marmot.waitFor(2);
        UiObject2 wlanSettings;
        wlanSettings = marmot.getUiObject(By.clazz("android.widget.TextView")
                .text("WLAN"));
        wlanSettings.click();
        marmot.waitFor(2);
        UiObject2 switcher;
        switcher = marmot
                .getUiObject(By.clazz("android.widget.TextView").res("开启WLAN"))
                .getParent().getParent()
                .findObject(By.clazz("android.widget.CheckBox"));
        if (switcher.isChecked()) {
            marmot.log("switcher checked.");
            switcher.click();
            marmot.waitFor(1);
        }
        return !switcher.isChecked();
    }

    public static void longClick(Marmot marmot, UiObject2 uiObject2) {
        Rect bounds = uiObject2.getVisibleBounds();
        marmot.drag(bounds.centerX(), bounds.centerY(), bounds.centerX(),
                bounds.centerY(), Lib_Frame_Constants.DRAG_STEPS);
        marmot.waitFor(2);
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

    public static boolean dialNumber(Marmot marmot, String dialNumber) {
        UiObject2 dialNumberButton;
        char temp;
        int number;
        String clazzNameImageButton, clazzNameImageView;
        clazzNameImageView = "android.widget.ImageView";
        clazzNameImageButton = "android.widget.ImageButton";
        for (int i = 0; i < dialNumber.length(); i++) {
            temp = dialNumber.charAt(i);
            if (Character.isDigit(temp)) {
                number = Integer.parseInt(String.valueOf(temp));
                // clazz name difference in alpha and dev
                dialNumberButton = marmot.getUiObject(By.clazz(
                        clazzNameImageView).res(
                        Lib_Frame_Constants.DIAL_RES.get(number)));
                if (dialNumberButton == null) {
                    dialNumberButton = marmot.getUiObject(By.clazz(
                            clazzNameImageButton).res(
                            Lib_Frame_Constants.DIAL_RES.get(number)));
                }
                dialNumberButton.click();
                marmot.waitFor(1);
            } else {
                marmot.log("dialNumber: not a valid number.");
                return false;
            }
        }
        return true;
    }

    public static boolean isFMAvailable(Marmot marmot) {
        String productName = marmot.getProductName();
        for (int i = 0; i < Lib_Frame_Constants.PRODUCT_NAME_NO_FM.size(); i++) {
            if (productName.equals(Lib_Frame_Constants.PRODUCT_NAME_NO_FM
                    .get(i))) {
                return false;
            }
        }
        return true;
    }

    public static void debug_msg(String msg) {
        System.out.println(msg);
    }

}
