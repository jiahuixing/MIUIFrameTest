package com.android.soundrecorder;

/**
 * Project name: MIUIFrameTest
 * Package name: com.android.soundrecorder
 * Created by jiahuixing
 * Created at 2015--07-05 12:58
 */


import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class TestSoundRecorder extends InstrumentationTestCase {

    public static final String PACKAGE_NAME_SOUND_RECORDER = "";
    public static final String ACTIVITY_NAME_SOUND_RECORDER = "";
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

    public void test_SoundRecorder() throws Exception {
        marmot.log("launch soundrecorder");
        marmot.launchActivity(ACTIVITY_NAME_SOUND_RECORDER);
        marmot.waitFor(2);
        checker.assertTrue("soundrecorder not launched", marmot.getCurrentPackageName().equals(PACKAGE_NAME_SOUND_RECORDER));
        UiObject2 recordList;
        recordList = marmot.getUiObject(By.clazz("").res(""));
        recordList.click();
        marmot.waitFor(1);
        UiObject2 cloudRecord;
        cloudRecord = marmot.getUiObject(By.text(""));
        if (cloudRecord != null) {
            marmot.log("cloud list.");
            cloudRecord.click();
            marmot.waitFor(1);
            marmot.log("cloud download list.");
            UiObject2 cloudDownloadList;
            cloudDownloadList = marmot.getUiObject(By.clazz("").res(""));
            cloudDownloadList.click();
            marmot.waitFor(1);
            marmot.pressBack();
            marmot.waitFor(1);
        }
        UiObject2 stop;
        stop = marmot.getUiObject(By.clazz("").res(""));
        if (stop != null) {
            marmot.log("stop record.");
            stop.click();
            marmot.waitFor(2);
        }
    }

    @Override
    public void tearDown() throws Exception {
        marmot.pressHome();
        super.tearDown();
    }
}
