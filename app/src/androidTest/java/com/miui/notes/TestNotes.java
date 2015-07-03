package com.miui.notes;

/**
 * Project name: MIUIFrameTest
 * Package name: com.miui.notes
 * Created by jiahuixing
 * Created at 2015--07-03 22:40
 */

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObject2;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class TestNotes extends InstrumentationTestCase {

    public static final String PACKAGE_NAME_NOTES = "";
    public static final String ACTIVITY_NAME_NOTES = "";
    public static final String PACKAGE_NAME_INPUT_METHOD_BAIDU = "";
    public static final String PACKAGE_NAME_CAMERA = "";
    public Marmot marmot;
    public Checker checker;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        marmot = new Marmot(this);
        checker = new Checker(marmot);
    }

    public void test_Notes() throws Exception {
        marmot.log("launch notes.");
        marmot.launchActivity(ACTIVITY_NAME_NOTES);
        marmot.waitFor(2);
        assertTrue("notes not launched.", marmot.getCurrentPackageName().equals(PACKAGE_NAME_NOTES));
        UiObject2 alertDialog;
        UiObject2 confirm, cancel;
        alertDialog = marmot.getUiObject(By.clazz("").res(""));
        if (alertDialog != null) {
            marmot.log("alertDialog.");
            marmot.saveScreenshot("alertDialog");
            cancel = marmot.getUiObject(By.clazz("").text(""));
            cancel.click();
            marmot.waitFor(2);
        }
        if (marmot.getCurrentPackageName().equals(PACKAGE_NAME_INPUT_METHOD_BAIDU)) {
            marmot.log("baidu input.");
            marmot.saveScreenshot("baiduInput");
            confirm = marmot.getUiObject(By.clazz("").text(""));
            confirm.click();
        }
        UiObject2 newNote, richEditor;
        newNote = marmot.getUiObject(By.clazz("").text(""));
        richEditor = marmot.getUiObject(By.clazz("").res(""));
        if (newNote != null) {
            marmot.log("newNote.");
            marmot.saveScreenshot("newNote");
            newNote.click();
            marmot.waitFor(2);
        }
        if (richEditor != null) {
            marmot.log("richEditor.");
            marmot.saveScreenshot("richEditor");
            UiObject2 photo;
            photo = marmot.getUiObject(By.clazz("").res(""));
            photo.click();
            marmot.waitFor(2);
            checker.assertTrue("photo failed.", marmot.getCurrentPackageName().equals(PACKAGE_NAME_CAMERA));
            alertDialog = marmot.getUiObject(By.clazz("").res(""));
            if (alertDialog != null) {
                marmot.log("alertDialog.");
                marmot.saveScreenshot("alertDialog");
                confirm = marmot.getUiObject(By.clazz("").text(""));
                confirm.click();
                marmot.waitFor(2);
            }
            UiObject2 shoot;
            shoot = marmot.getUiObject(By.clazz("").res(""));
            shoot.click();
            marmot.waitFor(3);
            UiObject2 done;
            done = marmot.getUiObject(By.clazz("").res(""));
            done.click();
            marmot.waitFor(2);
            checker.assertTrue("shoot failed.", marmot.getCurrentPackageName().equals(PACKAGE_NAME_NOTES));
        }
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }
}
