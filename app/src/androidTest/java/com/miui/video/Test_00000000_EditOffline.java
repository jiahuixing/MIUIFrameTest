package com.miui.video;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObject2;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_00000000_EditOffline extends InstrumentationTestCase {
    private Marmot mm;
    private Checker cc;

    private static final String PACKAGE_NAME = "com.miui.video";
    private static final String HOME_ACTIVITY_NAME = PACKAGE_NAME
            + "/.HomeActivity";

    @Override
    public void setUp() throws Exception {
        super.setUp();
        mm = new Marmot(this);
        cc = new Checker(mm);
    }

    @Override
    public void tearDown() throws Exception {
        mm.pressBack();
        mm.pressBack();
        mm.pressBack();

        mm.pressHome();
        super.tearDown();
    }

    public void testEditOffline() throws Exception {
        mm.launchActivity(HOME_ACTIVITY_NAME);
        mm.log("Step 1 : Open video.");
        mm.waitFor(1);
        mm.click(By.clazz(android.widget.TextView.class).text("我的离线"));
        mm.log("Step 2 : Enter My Offline view.");
        mm.waitFor(2);

        mm.log("Step 3 : Delete one offline video.");
        String deleteTitle = null;
        mm.click(By.clazz(android.widget.Button.class).res(
                PACKAGE_NAME + ":id/channel_edit_btn"));
        mm.waitFor(1);
        mm.click(By.clazz(android.widget.ImageView.class).res(
                PACKAGE_NAME + ":id/poster"));
        mm.waitFor(5);

        UiObject2 titleBar = mm.getUiObject(By.clazz(
                android.widget.TextView.class).res(
                PACKAGE_NAME + ":id/media_title"));
        deleteTitle = titleBar.getText();

        mm.click(By.clazz(android.widget.Button.class).res(
                PACKAGE_NAME + ":id/delete"));
        mm.waitFor(2);
        cc.assertTextNotExist(deleteTitle);
        cc.setTestrailResult("c1122594", true);

        mm.log("Step 4 : Delete all offline video.");
        mm.click(By.clazz(android.widget.Button.class).res(
                PACKAGE_NAME + ":id/channel_edit_btn"));
        mm.waitFor(1);
        mm.click(By.clazz(android.widget.Button.class).res(
                PACKAGE_NAME + ":id/select_all"));
        mm.waitFor(1);
        mm.click(By.clazz(android.widget.Button.class).res(
                PACKAGE_NAME + ":id/delete"));
        mm.waitFor(2);

        cc.assertUiObejctNotExist(By.clazz(android.widget.ImageView.class).res(
                PACKAGE_NAME + ":id/poster"));
        cc.setTestrailResult("c1122591", true);
    }
}
