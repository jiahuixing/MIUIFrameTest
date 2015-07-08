package com.miui.fm;

/**
 * Project name : marmot-cases2
 * Package name : com.miui.fm
 * Created by jiahuixing
 * Created on 2015-07-06
 * Created at 13:47
 */

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.Direction;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

import java.util.Date;

public class Test_00000000_FM extends InstrumentationTestCase {

    public static final String PACKAGE_NAME_FM = "com.miui.fm";
    public static final String ACTIVITY_NAME_FM = "com.miui.fm/com.miui.fmradio.FmActivity";
    public static final String PACKAGE_NAME_SOUND_RECORDER = "com.android.soundrecorder";
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

    public void test_FM() throws Exception {
        marmot.log("launch fm.");
        marmot.launchActivity(ACTIVITY_NAME_FM);
        marmot.waitFor(5);
        assertTrue("fm not launched.",
                marmot.getCurrentPackageName().equals(PACKAGE_NAME_FM));
        marmot.log("go to station list.");
        UiObject2 stationList;
        stationList = marmot.getUiObject(By.clazz("android.widget.ImageButton")
                .res("com.miui.fm:id/btn_stations_list"));
        stationList.click();
        marmot.waitFor(2);
        UiObject2 progressBar, cancel;
        long startTime, endTime;
        startTime = new Date().getTime();
        while (true) {
            progressBar = marmot.getUiObject(By
                    .clazz("android.widget.ProgressBar"));
            if (progressBar != null) {
                cancel = marmot.getUiObject(By.clazz("android.widget.Button")
                        .text("取消"));
                endTime = new Date().getTime();
                if ((endTime - startTime) >= 10) {
                    cancel.click();
                    break;
                }
                marmot.waitFor(3);
            } else
                break;
        }
        UiObject2 newStation, scanStation, alertDialog, confirm;
        newStation = marmot.getUiObject(By.clazz("android.widget.Button").text(
                "新建电台"));
        scanStation = marmot.getUiObject(By.clazz("android.widget.Button")
                .text("扫描电台"));
        if (newStation != null && scanStation != null) {
            scanStation.click();
            marmot.waitFor(2);
            alertDialog = marmot
                    .getUiObject(By.clazz("android.widget.TextView").res(
                            "miui:id/alertTitle"));
            if (alertDialog != null) {
                marmot.saveScreenshot("alertDialog" + IMAGE_EXTENSION);
                confirm = marmot.getUiObject(By.clazz("android.widget.Button")
                        .text("确定"));
                confirm.click();
                marmot.waitFor(2);
            }
            marmot.saveScreenshot("ProgressBar" + IMAGE_EXTENSION);
            while (true) {
                progressBar = marmot.getUiObject(By
                        .clazz("android.widget.ProgressBar"));
                if (progressBar != null) {
                    cancel = marmot.getUiObject(By.clazz(
                            "android.widget.Button").text("取消"));
                    endTime = new Date().getTime();
                    if ((endTime - startTime) >= 10) {
                        cancel.click();
                        break;
                    }
                    marmot.waitFor(3);
                } else
                    break;
            }
            marmot.waitFor(2);
            newStation.click();
            marmot.waitFor(2);
            marmot.saveScreenshot("newStation" + IMAGE_EXTENSION);
            UiObject2 stationFreq;
            stationFreq = marmot.getUiObject(By
                    .clazz("android.widget.EditText").res(
                            "com.miui.fm:id/station_freq"));
            stationFreq.setText("90.0");
            marmot.waitFor(1);
            marmot.saveScreenshot("stationFreq" + IMAGE_EXTENSION);
            confirm = marmot.getUiObject(By.clazz("android.widget.Button")
                    .text("确定"));
            confirm.click();
            marmot.waitFor(2);
            marmot.pressBack();
            marmot.waitFor(2);
        }
        marmot.log("immersion menu.");
        UiObject2 immersionMenu, immersionMenuList;
        immersionMenu = marmot.getUiObject(By.clazz(
                "android.widget.ImageButton").res("com.miui.fm:id/btn_menu"));
        immersionMenu.click();
        marmot.waitFor(2);
        marmot.saveScreenshot("immersionMenu" + IMAGE_EXTENSION);
        immersionMenuList = marmot.getUiObject(By
                .clazz("android.widget.ListView"));
        checker.assertTrue("immersionMenuList", immersionMenuList != null);
        marmot.log("fm record list.");
        UiObject2 fmRecordList;
        fmRecordList = marmot.getUiObject(By.clazz("android.widget.TextView")
                .text("录音列表"));
        fmRecordList.click();
        marmot.waitFor(2);
        marmot.saveScreenshot("fmRecordList" + IMAGE_EXTENSION);
        assertTrue("fail to go to fm record list.", marmot
                .getCurrentPackageName().equals(PACKAGE_NAME_SOUND_RECORDER));
        marmot.pressBack();
        marmot.waitFor(2);
        checker.assertTrue("fail to go back to fm.", marmot
                .getCurrentPackageName().equals(PACKAGE_NAME_FM));
        UiObject2 powerOff;
        powerOff = marmot.getUiObject(By.clazz("android.widget.ImageButton")
                .res("com.miui.fm:id/btn_power"));
        powerOff.click();
        marmot.waitFor(2);
        marmot.saveScreenshot("powerOff" + IMAGE_EXTENSION);
        UiObject2 powerOn;
        powerOn = marmot.getUiObject(By.clazz("android.widget.ImageButton")
                .res("com.miui.fm:id/btn_power_large"));
        powerOn.click();
        marmot.waitFor(2);
        marmot.saveScreenshot("powerOn" + IMAGE_EXTENSION);
        immersionMenu.click();
        marmot.waitFor(2);
        checker.assertTrue("immersionMenuList", immersionMenuList != null);
        UiObject2 quit;
        quit = marmot.getUiObject(By.clazz("android.widget.TextView")
                .text("退出"));
        quit.click();
        marmot.waitFor(2);
    }

    @Override
    public void tearDown() throws Exception {
        marmot.pressHome();
        super.tearDown();
    }

}
