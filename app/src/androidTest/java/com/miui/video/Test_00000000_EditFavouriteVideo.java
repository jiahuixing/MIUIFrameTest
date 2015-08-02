package com.miui.video;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObject2;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_00000000_EditFavouriteVideo extends InstrumentationTestCase {
    private Marmot mm;
    private Checker cc;

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

    public void testEditFavouriteVideo() throws Exception {
        mm.launchActivity(Lib_VideoConst.HOME_ACTIVITY_NAME);

        Lib_VideoUtil.addFavouriteVideo(mm);

        mm.log("Step 1 : Open video.");
        mm.waitFor(1);
        mm.click(By.clazz(android.widget.TextView.class).text("我的收藏"));
        mm.waitFor(2);
        mm.log("Step 2 : Enter My Favourite view.");
        String deleteTitle = null;
        mm.log("Step 3 : Delete one favourite video.");
        mm.click(By.clazz(android.widget.Button.class).res(
                Lib_VideoConst.PACKAGE_NAME + ":id/channel_edit_btn"));
        mm.waitFor(1);
        mm.click(By.clazz(android.widget.ImageView.class).res(
                Lib_VideoConst.PACKAGE_NAME + ":id/poster"));
        mm.waitFor(5);
        cc.setTestrailResult("c1122589", true);

        UiObject2 titleBar = mm.getUiObject(By.clazz(
                android.widget.TextView.class).res(
                Lib_VideoConst.PACKAGE_NAME + ":id/media_title"));
        deleteTitle = titleBar.getText();
        mm.log("Step 4 : Delete all favourite video.");
        mm.click(By.clazz(android.widget.Button.class).res(
                Lib_VideoConst.PACKAGE_NAME + ":id/delete"));
        mm.waitFor(2);
        cc.assertTextNotExist(deleteTitle);

        mm.click(By.clazz(android.widget.Button.class).res(
                Lib_VideoConst.PACKAGE_NAME + ":id/channel_edit_btn"));
        mm.waitFor(1);
        mm.click(By.clazz(android.widget.Button.class).res(
                Lib_VideoConst.PACKAGE_NAME + ":id/select_all"));
        mm.waitFor(1);
        mm.click(By.clazz(android.widget.Button.class).res(
                Lib_VideoConst.PACKAGE_NAME + ":id/delete"));
        mm.waitFor(2);

        cc.assertUiObejctExist(By.clazz(android.widget.TextView.class).res(
                Lib_VideoConst.PACKAGE_NAME + ":id/empty_title"));
        cc.setTestrailResult("c1122586", true);
    }

}
