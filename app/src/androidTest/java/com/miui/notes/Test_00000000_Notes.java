package com.miui.notes;

/**
 * Project name : marmot-cases2
 * Package name : com.miui.notes
 * Created by jiahuixing
 * Created on 2015-07-03
 * Created at 13:53
 */

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.Direction;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_00000000_Notes extends InstrumentationTestCase {

    public static final String PACKAGE_NAME_NOTES = "com.miui.notes";
    public static final String ACTIVITY_NAME_NOTES = "com.miui.notes/.ui.NotesListActivity";
    public static final String PACKAGE_NAME_INPUT_METHOD_BAIDU = "com.baidu.input_mi";
    public static final String PACKAGE_NAME_CAMERA = "com.android.camera";
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

    public void test_Notes() throws Exception {
        marmot.log("launch notes.");
        marmot.launchActivity(ACTIVITY_NAME_NOTES);
        marmot.waitFor(2);
        assertTrue("notes not launched.", marmot.getCurrentPackageName()
                .equals(PACKAGE_NAME_NOTES));
        UiObject2 alertDialog;
        UiObject2 confirm, cancel;
        alertDialog = marmot.getUiObject(By.clazz("android.widget.TextView")
                .res("miui:id/alertTitle"));
        if (alertDialog != null) {
            marmot.log("alertDialog.");
            marmot.saveScreenshot("alertDialog" + IMAGE_EXTENSION);
            cancel = marmot.getUiObject(By.clazz("android.widget.Button").text(
                    "暂不"));
            cancel.click();
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
        UiObject2 newNote, richEditor;
        newNote = marmot.getUiObject(By.clazz("").text(""));
        richEditor = marmot.getUiObject(By.clazz("android.widget.EditText")
                .res("com.miui.notes:id/rich_editor"));
        if (newNote != null) {
            marmot.log("newNote.");
            marmot.saveScreenshot("newNote" + IMAGE_EXTENSION);
            newNote.click();
            marmot.waitFor(2);
        }
        if (richEditor != null) {
            marmot.log("richEditor.");
            marmot.saveScreenshot("richEditor" + IMAGE_EXTENSION);
            UiObject2 photo;
            photo = marmot.getUiObject(By.clazz("android.widget.ImageView")
                    .res("com.miui.notes:id/photo"));
            photo.click();
            marmot.waitFor(2);
            checker.assertTrue("photo failed.", marmot.getCurrentPackageName()
                    .equals(PACKAGE_NAME_CAMERA));
            alertDialog = marmot
                    .getUiObject(By.clazz("android.widget.TextView").res(
                            "miui:id/alertTitle"));
            if (alertDialog != null) {
                marmot.log("alertDialog.");
                marmot.saveScreenshot("alertDialog" + IMAGE_EXTENSION);
                confirm = marmot.getUiObject(By.clazz("android.widget.Button")
                        .text("开始拍照"));
                confirm.click();
                marmot.waitFor(2);
            }
            UiObject2 shoot;
            shoot = marmot.getUiObject(By.clazz("android.widget.ImageView")
                    .res("com.android.camera:id/v6_shutter_button_internal"));
            shoot.click();
            marmot.waitFor(3);
            UiObject2 done;
            done = marmot.getUiObject(By.clazz("android.widget.ImageView").res(
                    "com.android.camera:id/v6_btn_done"));
            done.click();
            marmot.waitFor(2);
            checker.assertTrue("shoot failed.", marmot.getCurrentPackageName()
                    .equals(PACKAGE_NAME_NOTES));
        }
    }

    @Override
    public void tearDown() throws Exception {
        marmot.pressHome();
        super.tearDown();
    }
}
