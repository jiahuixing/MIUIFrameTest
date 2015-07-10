package com.com.miui.notes;

/**
 * Project name : marmot-cases2
 * Package name : com.miui.notes
 * Created by jiahuixing
 * Created on 2015-07-03
 * Created at 13:53
 */

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.test.InstrumentationTestCase;

import com.miui.frame.Lib_Frame_Constants;
import com.miui.frame.Lib_Frame_Utils;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_00000000_Notes extends InstrumentationTestCase {

    public Marmot marmot;
    public Checker checker;
    public UiDevice uiDevice;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        marmot = new Marmot(this);
        checker = new Checker(marmot);
        uiDevice = marmot.getUiDevice();
        Lib_Frame_Utils.unLock(marmot);
    }

    public void test_Notes() throws Exception {
        marmot.log("launch notes.");
        marmot.launchActivity(Lib_Frame_Constants.ACTIVITY_NAME_NOTES);
        marmot.waitFor(2);
        assertTrue("notes not launched.", marmot.getCurrentPackageName()
                .equals(Lib_Frame_Constants.PACKAGE_NAME_NOTES));
        UiObject2 alertDialog;
        UiObject2 confirm, cancel;
        alertDialog = marmot.getUiObject(By.clazz("android.widget.TextView")
                .res("miui:id/alertTitle"));
        if (alertDialog != null) {
            marmot.log("alertDialog.");
            marmot.saveScreenshot("alertDialog"
                    + Lib_Frame_Constants.IMAGE_EXTENSION);
            cancel = marmot.getUiObject(By.clazz("android.widget.Button").text(
                    "暂不"));
            cancel.click();
            marmot.waitFor(2);
        }
        if (marmot.getCurrentPackageName().equals(
                Lib_Frame_Constants.PACKAGE_NAME_INPUT_METHOD_BAIDU)) {
            marmot.log("baidu input.");
            marmot.saveScreenshot("baiduInput"
                    + Lib_Frame_Constants.IMAGE_EXTENSION);
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
            marmot.saveScreenshot("newNote"
                    + Lib_Frame_Constants.IMAGE_EXTENSION);
            newNote.click();
            marmot.waitFor(2);
        }
        if (richEditor != null) {
            marmot.log("richEditor.");
            marmot.saveScreenshot("richEditor"
                    + Lib_Frame_Constants.IMAGE_EXTENSION);
            UiObject2 photo;
            photo = marmot.getUiObject(By.clazz("android.widget.ImageView")
                    .res("com.miui.notes:id/photo"));
            photo.click();
            marmot.waitFor(2);
            checker.assertTrue("photo failed.", marmot.getCurrentPackageName()
                    .equals(Lib_Frame_Constants.PACKAGE_NAME_CAMERA));
            alertDialog = marmot
                    .getUiObject(By.clazz("android.widget.TextView").res(
                            "miui:id/alertTitle"));
            if (alertDialog != null) {
                marmot.log("alertDialog.");
                marmot.saveScreenshot("alertDialog"
                        + Lib_Frame_Constants.IMAGE_EXTENSION);
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
                    .equals(Lib_Frame_Constants.PACKAGE_NAME_NOTES));
        }
    }

    @Override
    public void tearDown() throws Exception {
        marmot.pressHome();
        super.tearDown();
    }
}
