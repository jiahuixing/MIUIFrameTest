package com.miui.fm;

/**
 * Project name: MIUIFrameTest
 * Package name: com.miui.fm
 * Created by jiahuixing
 * Created at 2015--07-03 23:17
 */

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

import java.util.Date;

public class TestFM extends InstrumentationTestCase {

    public static final String PACKAGE_NAME_FM = "";
    public static final String ACTIVITY_NAME_FM = "";
    public static final String PACKAGE_NAME_SOUND_RECORDER = "";
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

    public void test_FM() throws Exception {
        marmot.log("launch fm.");
        marmot.launchActivity(ACTIVITY_NAME_FM);
        marmot.waitFor(2);
        assertTrue("fm not launched.", marmot.getCurrentPackageName().equals(PACKAGE_NAME_FM));
        marmot.log("go to station list.");
        UiObject2 stationList;
        stationList = marmot.getUiObject(By.clazz("").res(""));
        stationList.click();
        marmot.waitFor(2);
        UiObject2 progressBar;
        long startTime, endTime;
        startTime = new Date().getTime();
        while (true) {
            progressBar = marmot.getUiObject(By.clazz(""));
            if (progressBar != null) {
                endTime = new Date().getTime();
                if ((endTime - startTime) >= 10) {
                    UiObject2 cancel;
                    cancel = marmot.getUiObject(By.clazz("").text(""));
                    cancel.click();
                    break;
                }
                marmot.waitFor(3);
            } else
                break;
        }
        UiObject2 newStation;
        newStation = marmot.getUiObject(By.clazz("").text(""));
        if (newStation != null) {
            marmot.pressBack();
            marmot.waitFor(2);
        }
        marmot.log("immersion menu.");
        UiObject2 immersionMenu;
        immersionMenu = marmot.getUiObject(By.clazz("").res(""));
        immersionMenu.click();
        marmot.waitFor(2);
        marmot.log("fm record list.");
        UiObject2 fmRecordList;
        fmRecordList = marmot.getUiObject(By.clazz("").text(""));
        fmRecordList.click();
        marmot.waitFor(2);
        assertTrue("fail to go to fm record list.", marmot.getCurrentPackageName().equals(PACKAGE_NAME_SOUND_RECORDER));
        marmot.pressBack();
        marmot.waitFor(2);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

}
