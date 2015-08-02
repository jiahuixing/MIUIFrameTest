package com.android.soundrecorder;

/**
 * Project name : marmot-cases2
 * Package name : com.android.soundrecorder
 * Created by jiahuixing
 * Created on 2015-07-06
 * Created at 13:55
 */

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.test.InstrumentationTestCase;

import com.miui.frame.Lib_Frame_Constants;
import com.miui.frame.Lib_Frame_Utils;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_00000000_SoundRecorder extends InstrumentationTestCase {

    public Marmot marmot;
    public Checker checker;
    public UiDevice uiDevice;

    public static int testStep = 0;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        marmot = new Marmot(this);
        checker = new Checker(marmot);
        uiDevice = marmot.getUiDevice();
        Lib_Frame_Utils.unLock(marmot);
    }

    public void test_SoundRecorder() throws Exception {
        marmot.log("launch soundrecorder");
        marmot.launchActivity(Lib_Frame_Constants.ACTIVITY_NAME_SOUND_RECORDER);
        marmot.waitFor(2);
        checker.assertTrue(
                "soundrecorder launch",
                marmot.getCurrentPackageName().equals(
                        Lib_Frame_Constants.PACKAGE_NAME_SOUND_RECORDER));
        UiObject2 recordList;
        recordList = marmot.getUiObject(By.clazz("android.widget.ImageButton")
                .res("com.android.soundrecorder:id/btn_list"));
        recordList.click();
        marmot.waitFor(1);
        UiObject2 cloudRecord;
        cloudRecord = marmot.getUiObject(By.clazz("android.widget.TextView")
                .textContains("云录音"));
        if (cloudRecord != null) {
            marmot.log("cloud list.");
            cloudRecord.click();
            marmot.waitFor(1);
            marmot.log("cloud download list.");
            UiObject2 cloudDownloadList;
            cloudDownloadList = marmot.getUiObject(By.clazz(
                    "android.widget.ImageButton").res(
                    "com.android.soundrecorder:id/btn_download_list"));
            cloudDownloadList.click();
            marmot.waitFor(1);
            marmot.pressBack();
            marmot.waitFor(1);
        }
        UiObject2 stop;
        stop = marmot.getUiObject(By.clazz("android.widget.ImageButton").res(
                "com.android.soundrecorder:id/btn_record_stop"));
        if (stop != null && stop.isEnabled()) {
            marmot.log("stop record.");
            stop.click();
            marmot.waitFor(2);
            UiObject2 confirm;
            // UiObject2 alertDialog, confirm;
            // alertDialog = marmot
            // .getUiObject(By.clazz("android.widget.TextView").res(
            // "miui:id/alertTitle"));
            confirm = marmot.getUiObject(By.clazz("android.widget.Button")
                    .text("确定"));
            if (confirm != null) {
                marmot.log("alertDialog.");
                marmot.saveScreenshot("alertDialog"
                        + Lib_Frame_Constants.IMAGE_EXTENSION);
                confirm.click();
            }
            marmot.waitFor(2);
            if (marmot.getCurrentPackageName().equals(
                    Lib_Frame_Constants.PACKAGE_NAME_INPUT_METHOD_BAIDU)) {
                marmot.log("baidu input.");
                marmot.saveScreenshot("baiduInput"
                        + Lib_Frame_Constants.IMAGE_EXTENSION);
                confirm = marmot.getUiObject(By.clazz("android.widget.Button")
                        .text("开始输入"));
                if (confirm != null) {
                    confirm.click();
                }
            }
        }
        Lib_Frame_Utils.backToPackage(marmot,
                Lib_Frame_Constants.PACKAGE_NAME_HOME);
        testStep += 1;
        // marmot.log("15. quit fm.");
        marmot.log(String.format("%s. soundRecorder settings.", testStep));
        marmot.launchActivity(Lib_Frame_Constants.ACTIVITY_NAME_SETTINGS);
        checker.assertTrue(
                "settings.",
                marmot.getCurrentPackageName().equals(
                        Lib_Frame_Constants.PACKAGE_NAME_SETTINGS));
        UiScrollable settingsList;
        settingsList = new UiScrollable(
                new UiSelector().className("android.widget.ListView"));
        UiObject systemAppSettings, soundRecorderSettings;
        soundRecorderSettings = settingsList.getChildByText(
                new UiSelector().className("android.widget.TextView"), "录音机",
                true);
        if (soundRecorderSettings == null) {
            systemAppSettings = settingsList.getChildByText(
                    new UiSelector().className("android.widget.TextView"),
                    "系统应用", true);
            systemAppSettings.click();
            marmot.waitFor(2);
            settingsList = new UiScrollable(
                    new UiSelector().className("android.widget.ListView"));
            soundRecorderSettings = settingsList.getChildByText(
                    new UiSelector().className("android.widget.TextView"),
                    "录音机", true);
        }
        soundRecorderSettings.click();
        marmot.waitFor(2);
        Lib_Frame_Utils.backToPackage(marmot,
                Lib_Frame_Constants.PACKAGE_NAME_HOME);
        testStep += 1;
        marmot.log(String.format("%s. quit sound recorder.", testStep));
        Lib_Frame_Utils.backToPackage(marmot,
                Lib_Frame_Constants.PACKAGE_NAME_HOME);
    }

    @Override
    public void tearDown() throws Exception {
        Lib_Frame_Utils.backToPackage(marmot,
                Lib_Frame_Constants.PACKAGE_NAME_HOME);
        super.tearDown();
    }
}
