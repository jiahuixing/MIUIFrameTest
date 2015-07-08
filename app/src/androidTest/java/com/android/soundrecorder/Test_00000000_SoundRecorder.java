package com.android.soundrecorder;

/**
 * Project name : marmot-cases2
 * Package name : com.android.soundrecorder
 * Created by jiahuixing
 * Created on 2015-07-06
 * Created at 13:55
 */

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.Direction;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_00000000_SoundRecorder extends InstrumentationTestCase {

    public static final String PACKAGE_NAME_SOUND_RECORDER = "com.android.soundrecorder";
    public static final String ACTIVITY_NAME_SOUND_RECORDER = "com.android.soundrecorder/.SoundRecorder";
    public static final String PACKAGE_NAME_INPUT_METHOD_BAIDU = "com.baidu.input_mi";
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

    public void test_SoundRecorder() throws Exception {
        marmot.log("launch soundrecorder");
        marmot.launchActivity(ACTIVITY_NAME_SOUND_RECORDER);
        marmot.waitFor(2);
        checker.assertTrue("soundrecorder launch", marmot
                .getCurrentPackageName().equals(PACKAGE_NAME_SOUND_RECORDER));
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
            UiObject2 alertDialog, confirm;
            alertDialog = marmot
                    .getUiObject(By.clazz("android.widget.TextView").res(
                            "miui:id/alertTitle"));
            if (alertDialog != null) {
                marmot.log("alertDialog.");
                marmot.saveScreenshot("alertDialog" + IMAGE_EXTENSION);
                confirm = marmot.getUiObject(By.clazz("android.widget.Button")
                        .text("确定"));
                confirm.click();
                marmot.waitFor(2);
            }
            if (marmot.getCurrentPackageName().equals(
                    PACKAGE_NAME_INPUT_METHOD_BAIDU)) {
                marmot.log("baidu input.");
                marmot.saveScreenshot("baiduInput" + IMAGE_EXTENSION);
                confirm = marmot.getUiObject(By.clazz("android.widget.Button")
                        .text("开始输入"));
                confirm.click();
            }
        }
    }

    @Override
    public void tearDown() throws Exception {
        marmot.pressHome();
        super.tearDown();
    }
}
