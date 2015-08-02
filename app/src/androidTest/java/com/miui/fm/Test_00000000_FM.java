package com.miui.fm;

/**
 * Project name : marmot-cases2
 * Package name : com.miui.fm
 * Created by jiahuixing
 * Created on 2015-07-06
 * Created at 13:47
 */

//import android.content.Context;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.test.InstrumentationTestCase;

import com.miui.frame.Lib_Frame_Constants;
import com.miui.frame.Lib_Frame_Utils;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

import java.util.Date;

//import android.view.inputmethod.InputMethodManager;

public class Test_00000000_FM extends InstrumentationTestCase {

    // public Context context;
    public Marmot marmot;
    public Checker checker;
    public UiDevice uiDevice;

    public static int testStep = 0;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        // context = getInstrumentation().getContext();
        marmot = new Marmot(this);
        checker = new Checker(marmot);
        uiDevice = marmot.getUiDevice();
        Lib_Frame_Utils.unLock(marmot);
    }

    public void test_FM() throws Exception {
        testStep += 1;
        // marmot.log("1. launch fm.");
        marmot.log(String.format("%s. launch fm.", testStep));
        marmot.launchActivity(Lib_Frame_Constants.ACTIVITY_NAME_FM);
        marmot.waitFor(5);
        checker.assertTrue("fm launched", marmot.getCurrentPackageName()
                .equals(Lib_Frame_Constants.PACKAGE_NAME_FM));
        UiObject2 fmFreq;
        fmFreq = marmot.getUiObject(By.clazz("android.widget.TextView").res(
                "com.miui.fm:id/txt_frequency"));
        String currentFreqString;
        currentFreqString = fmFreq.getText();
        marmot.log(String.format("currentFreqString = %s", currentFreqString));
        int currentFreqInt;
        currentFreqInt = (int) Float.parseFloat(currentFreqString);
        marmot.log(String.format("currentFreqInt = %s", currentFreqInt));
        int startX, startY, endX, endY;
        if (currentFreqInt <= (88 + 108) / 2) {
            startX = fmFreq.getVisibleBounds().right;
            startY = fmFreq.getVisibleBounds().centerY();
            endX = fmFreq.getVisibleBounds().left;
            endY = fmFreq.getVisibleBounds().centerY();
        } else {
            startX = fmFreq.getVisibleBounds().left;
            startY = fmFreq.getVisibleBounds().centerY();
            endX = fmFreq.getVisibleBounds().right;
            endY = fmFreq.getVisibleBounds().centerY();
        }
        checker.assertTrue("fm frequency", Lib_Frame_Utils.exist(fmFreq));
        checker.setTestrailResult("C483766", true);
        checker.setTestrailResult("C483767", true);
        checker.setTestrailResult("C483768", true);
        marmot.saveScreenshot("fmMain" + Lib_Frame_Constants.IMAGE_EXTENSION);
        testStep += 1;
        // marmot.log("2. turn off fm.");
        marmot.log(String.format("%s. turn off fm.", testStep));
        UiObject2 powerOff;
        powerOff = marmot.getUiObject(By.clazz("android.widget.ImageButton")
                .res("com.miui.fm:id/btn_power"));
        powerOff.click();
        marmot.waitFor(3);
        marmot.saveScreenshot("powerOff" + Lib_Frame_Constants.IMAGE_EXTENSION);
        testStep += 1;
        // marmot.log("3. turn on fm.");
        marmot.log(String.format("%s. turn on fm.", testStep));
        UiObject2 powerOn;
        powerOn = marmot.getUiObject(By.clazz("android.widget.ImageButton")
                .res("com.miui.fm:id/btn_power_large"));
        powerOn.click();
        marmot.waitFor(3);
        marmot.saveScreenshot("powerOn" + Lib_Frame_Constants.IMAGE_EXTENSION);
        checker.setTestrailResult("C483770", true);
        testStep += 1;
        // marmot.log("4. seek freq back and forward.");
        marmot.log(String.format("%s. seek freq back and forward.", testStep));
        UiObject2 seekBack, seekForward;
        seekBack = marmot.getUiObject(By.clazz("android.widget.ImageButton")
                .res("com.miui.fm:id/btn_seekback"));
        seekForward = marmot.getUiObject(By.clazz("android.widget.ImageButton")
                .res("com.miui.fm:id/btn_seekforward"));
        seekBack.click();
        marmot.waitFor(2);
        seekForward.click();
        marmot.waitFor(2);
        checker.setTestrailResult("C483777", true);
        testStep += 1;
        // marmot.log("5. tuning fm frequency.");
        marmot.log(String.format("%s. tuning fm frequency.", testStep));
        fmFreq.click();
        marmot.waitFor(0.5);
        marmot.drag(startX, startY, endX, endY, Lib_Frame_Constants.DRAG_STEPS);
        marmot.waitFor(4);
        currentFreqString = fmFreq.getText();
        marmot.log(String.format("after currentFreqString = %s",
                currentFreqString));
        currentFreqInt = (int) Float.parseFloat(currentFreqString);
        marmot.log(String.format("after currentFreqInt = %s", currentFreqInt));
        checker.setTestrailResult("C483771", true);
        testStep += 1;
        // marmot.log("6. immersion menu.");
        marmot.log(String.format("%s. immersion menu.", testStep));
        UiObject2 immersionMenu, immersionMenuList;
        immersionMenu = marmot.getUiObject(By.clazz(
                "android.widget.ImageButton").res("com.miui.fm:id/btn_menu"));
        immersionMenu.click();
        marmot.waitFor(2);
        marmot.saveScreenshot("immersionMenu"
                + Lib_Frame_Constants.IMAGE_EXTENSION);
        immersionMenuList = marmot.getUiObject(By
                .clazz("android.widget.ListView"));
        checker.assertTrue("immersionMenuList", immersionMenuList != null);
        checker.setTestrailResult("C2191447", true);
        UiObject2 closeImmersionMenu, speakerMode, headPhoneMode, sleepMode, cancelSleepMode, addToStationList, addToFavorList, startRecord, endRecord, fmRecordList, quit;
        UiObject2 progressBar, confirm, cancel;
        // UiObject2 progressBar, alertDialog, confirm, cancel;
        speakerMode = marmot.getUiObject(By.clazz("android.widget.TextView")
                .text("外放模式"));
        if (speakerMode != null) {
            testStep += 1;
            // marmot.log("7. speaker mode.");
            marmot.log(String.format("%s. speaker mode.", testStep));
            speakerMode.click();
            marmot.waitFor(2);
            checker.setTestrailResult("C483769", true);
            immersionMenu.click();
            marmot.waitFor(2);
            headPhoneMode = marmot.getUiObject(By.clazz(
                    "android.widget.TextView").text("耳机模式"));
            if (headPhoneMode != null) {
                testStep += 1;
                // marmot.log("7. headPhone mode.");
                marmot.log(String.format("%s. headPhone mode.", testStep));
                headPhoneMode.click();
                marmot.waitFor(2);
                checker.setTestrailResult("C2191448", true);
            } else {
                marmot.log("no headPhoneMode");
                closeImmersionMenu = marmot.getUiObject(By.clazz(
                        "android.widget.Button").res("miui:id/close"));
                closeImmersionMenu.click();
                marmot.waitFor(2);
            }
        } else {
            marmot.log("no speakerMode");
            closeImmersionMenu = marmot.getUiObject(By.clazz(
                    "android.widget.Button").res("miui:id/close"));
            closeImmersionMenu.click();
            marmot.waitFor(2);
        }
        testStep += 1;
        // marmot.log("7. sleep mode.");
        marmot.log(String.format("%s. sleep mode.", testStep));
        immersionMenu.click();
        marmot.waitFor(2);
        sleepMode = marmot.getUiObject(By.clazz("android.widget.TextView")
                .text("睡眠模式"));
        sleepMode.click();
        marmot.waitFor(2);
        UiObject2 sleepModeChoiceList;
        sleepModeChoiceList = marmot.getUiObject(By.clazz(
                "android.widget.ListView")
                .res("miui:id/select_dialog_listview"));
        int sleepModeChoiceCount;
        sleepModeChoiceCount = sleepModeChoiceList.getChildCount();
        marmot.log(String.format("sleepModeChoiceCount = %s",
                sleepModeChoiceCount));
        UiObject2 sleepModeChoice;
        sleepModeChoice = sleepModeChoiceList.getChildren().get(
                Lib_Frame_Utils.getRandomInt(sleepModeChoiceCount, 0));
        sleepModeChoice.click();
        marmot.waitFor(2);
        UiObject2 sleepModeStatus;
        sleepModeStatus = marmot.getUiObject(By
                .clazz("android.widget.TextView").res(
                        "com.miui.fm:id/txt_sleep_mode"));
        checker.assertTrue("sleepModeStatus", sleepModeStatus != null);
        checker.setTestrailResult("C483779", true);
        testStep += 1;
        // marmot.log("8. cancel sleep mode.");
        marmot.log(String.format("%s. cancel sleep mode.", testStep));
        immersionMenu.click();
        marmot.waitFor(2);
        cancelSleepMode = marmot.getUiObject(By
                .clazz("android.widget.TextView").text("取消睡眠模式"));
        cancelSleepMode.click();
        marmot.waitFor(2);
        sleepModeStatus = marmot.getUiObject(By
                .clazz("android.widget.TextView").res(
                        "com.miui.fm:id/txt_sleep_mode"));
        checker.assertTrue("sleepModeStatus", sleepModeStatus == null);
        checker.setTestrailResult("C2191449", true);
        testStep += 1;
        // marmot.log("8. add to station list.");
        marmot.log(String.format("%s. add to station list.", testStep));
        immersionMenu.click();
        marmot.waitFor(2);
        addToStationList = marmot.getUiObject(By.clazz(
                "android.widget.TextView").text("加入列表"));
        if (addToStationList != null) {
            marmot.log("addToStationList");
            addToStationList.click();
            marmot.waitFor(2);
            UiObject2 favor;
            favor = marmot.getUiObject(By.clazz("android.widget.CheckBox").res(
                    "com.miui.fm:id/station_favor_check"));
            favor.click();
            marmot.waitFor(2);
            confirm = marmot.getUiObject(By.clazz("android.widget.Button")
                    .text("确定"));
            confirm.click();
            marmot.waitFor(2);
            checker.setTestrailResult("C483788", true);
        } else {
            marmot.log("no addToStationList");
            closeImmersionMenu = marmot.getUiObject(By.clazz(
                    "android.widget.Button").res("miui:id/close"));
            closeImmersionMenu.click();
            marmot.waitFor(2);
        }
        immersionMenu.click();
        marmot.waitFor(2);
        addToFavorList = marmot.getUiObject(By.clazz("android.widget.TextView")
                .text("加入收藏"));
        if (addToFavorList != null) {
            testStep += 1;
            // marmot.log("9. add to favor list.");
            marmot.log(String.format("%s. add to favor list.", testStep));
            addToFavorList.click();
            marmot.waitFor(2);
            confirm = marmot.getUiObject(By.clazz("android.widget.Button")
                    .text("确定"));
            cancel = marmot.getUiObject(By.clazz("android.widget.Button").text(
                    "取消"));
            if (confirm.isEnabled()) {
                confirm.click();
            } else {
                cancel.click();
            }
            marmot.waitFor(2);
            checker.setTestrailResult("C2191450", true);
        } else {
            marmot.log("no addToFavorList");
            closeImmersionMenu = marmot.getUiObject(By.clazz(
                    "android.widget.Button").res("miui:id/close"));
            closeImmersionMenu.click();
            marmot.waitFor(2);
        }
        testStep += 1;
        // marmot.log("9. start record.");
        marmot.log(String.format("%s. start record.", testStep));
        immersionMenu.click();
        marmot.waitFor(2);
        startRecord = marmot.getUiObject(By.clazz("android.widget.TextView")
                .text("开始录音"));
        startRecord.click();
        marmot.waitFor(2);
        UiObject2 recordStatus;
        recordStatus = marmot.getUiObject(By
                .clazz("android.widget.Chronometer").res(
                        "com.miui.fm:id/tv_recording_status"));
        checker.assertTrue("recordStatus", recordStatus != null);
        checker.setTestrailResult("C483778", true);
        testStep += 1;
        // marmot.log("10. end record.");
        marmot.log(String.format("%s. end record.", testStep));
        immersionMenu.click();
        marmot.waitFor(2);
        endRecord = marmot.getUiObject(By.clazz("android.widget.TextView")
                .text("停止录音"));
        endRecord.click();
        marmot.waitFor(2);
        recordStatus = marmot.getUiObject(By
                .clazz("android.widget.Chronometer").res(
                        "com.miui.fm:id/tv_recording_status"));
        checker.assertTrue("recordStatus", recordStatus == null);
        checker.setTestrailResult("C2191440", true);
        testStep += 1;
        // marmot.log("11. fm record list.");
        marmot.log(String.format("%s. fm record list.", testStep));
        immersionMenu.click();
        marmot.waitFor(2);
        fmRecordList = marmot.getUiObject(By.clazz("android.widget.TextView")
                .text("录音列表"));
        fmRecordList.click();
        marmot.waitFor(2);
        marmot.saveScreenshot("fmRecordList"
                + Lib_Frame_Constants.IMAGE_EXTENSION);
        checker.assertTrue("fm record list.", marmot.getCurrentPackageName()
                .equals(Lib_Frame_Constants.PACKAGE_NAME_SOUND_RECORDER));
        checker.setTestrailResult("C483780", true);
        marmot.pressBack();
        marmot.waitFor(2);
        checker.assertTrue(
                "back fm.",
                marmot.getCurrentPackageName().equals(
                        Lib_Frame_Constants.PACKAGE_NAME_FM));
        testStep += 1;
        // marmot.log("12. station list.");
        marmot.log(String.format("%s. station list.", testStep));
        UiObject2 stationList;
        stationList = marmot.getUiObject(By.clazz("android.widget.ImageButton")
                .res("com.miui.fm:id/btn_stations_list"));
        stationList.click();
        marmot.waitFor(2);
        checker.setTestrailResult("C483772", true);
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
        UiObject2 newStation, scanStation;
        newStation = marmot.getUiObject(By.clazz("android.widget.Button").text(
                "新建电台"));
        scanStation = marmot.getUiObject(By.clazz("android.widget.Button")
                .text("扫描电台"));
        if (newStation != null && scanStation != null) {
            testStep += 1;
            // marmot.log("13. scan fm station.");
            marmot.log(String.format("%s. scan fm station.", testStep));
            scanStation.click();
            marmot.waitFor(2);
            // alertDialog = marmot
            // .getUiObject(By.clazz("android.widget.TextView").res(
            // "miui:id/alertTitle"));
            // if (alertDialog != null) {
            confirm = marmot.getUiObject(By.clazz("android.widget.Button")
                    .text("确定"));
            if (confirm != null) {
                marmot.saveScreenshot("alertDialog"
                        + Lib_Frame_Constants.IMAGE_EXTENSION);
                confirm.click();
            }
            marmot.waitFor(2);
            // }
            marmot.saveScreenshot("ProgressBar"
                    + Lib_Frame_Constants.IMAGE_EXTENSION);
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
            checker.setTestrailResult("C483776", true);
            testStep += 1;
            // marmot.log("14. create a new fm station.");
            marmot.log(String.format("%s. create a new fm station.", testStep));
            newStation.click();
            marmot.waitFor(2);
            // InputMethodManager inputMethodManager = (InputMethodManager)
            // context
            // .getSystemService(Context.INPUT_METHOD_SERVICE);
            marmot.saveScreenshot("newStation"
                    + Lib_Frame_Constants.IMAGE_EXTENSION);
            UiObject2 stationFreq, stationFreqLabel;
            stationFreq = marmot.getUiObject(By
                    .clazz("android.widget.EditText").res(
                            "com.miui.fm:id/station_freq"));
            stationFreq.click();
            marmot.waitFor(2);
            int setFreqInt;
            String setFreqString;
            setFreqInt = Lib_Frame_Utils.getRandomInt(
                    Lib_Frame_Constants.MAX_FREQ, Lib_Frame_Constants.MIN_FREQ);
            marmot.log(String.format("setFreqInt == %s", setFreqInt));
            setFreqString = Integer.toString(setFreqInt);
            marmot.log(String.format("setFreqString == %s", setFreqString));
            stationFreq.setText(setFreqString);
            marmot.waitFor(2);
            stationFreqLabel = marmot.getUiObject(By.clazz(
                    "android.widget.EditText").res(
                    "com.miui.fm:id/station_label"));
            stationFreqLabel.click();
            marmot.waitFor(2);
            // if (inputMethodManager.isActive()) {
            // marmot.log("inputMethodManager.isActive()");
            // marmot.pressBack();
            // marmot.waitFor(2);
            // }
            marmot.saveScreenshot("stationFreq"
                    + Lib_Frame_Constants.IMAGE_EXTENSION);
            confirm = marmot.getUiObject(By.clazz("android.widget.Button")
                    .text("确定"));
            cancel = marmot.getUiObject(By.clazz("android.widget.Button").text(
                    "取消"));
            if (confirm.isEnabled()) {
                confirm.click();
                marmot.log("confirm enable.");
            } else {
                cancel.click();
                marmot.log("confirm not enable.");
            }
            marmot.waitFor(2);
            checker.setTestrailResult("C2191439", true);
            marmot.pressBack();
            marmot.waitFor(2);
        }
        immersionMenu.click();
        marmot.waitFor(2);
        quit = marmot.getUiObject(By.clazz("android.widget.TextView")
                .text("退出"));
        testStep += 1;
        // marmot.log("15. quit fm.");
        marmot.log(String.format("%s. quit fm.", testStep));
        quit.click();
        marmot.waitFor(2);
        checker.setTestrailResult("C483795", true);
    }

    @Override
    public void tearDown() throws Exception {
        Lib_Frame_Utils.backToPackage(marmot,
                Lib_Frame_Constants.PACKAGE_NAME_HOME);
        super.tearDown();
    }

}
