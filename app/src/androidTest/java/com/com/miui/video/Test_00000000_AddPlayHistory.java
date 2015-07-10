package com.com.miui.video;


import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObject2;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_00000000_AddPlayHistory extends InstrumentationTestCase {
    private static final String PACKAGE_NAME = "com.miui.video";
    private static final String HOME_ACTIVITY_NAME = PACKAGE_NAME + "/.HomeActivity";
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

    public void testAddPlayHistory() throws Exception {
        mm.launchActivity(HOME_ACTIVITY_NAME);

        mm.click(By.clazz(android.widget.TextView.class).text("综艺"));
        mm.waitFor(3);

        String newPlayTitle = null;

        mm.click(By.clazz(android.widget.ImageView.class)
                .res(PACKAGE_NAME + ":id/poster"));

        UiObject2 titleBar = mm.getUiObject(By.clazz(
                android.widget.LinearLayout.class).res(
                PACKAGE_NAME + ":id/title_top"));
        newPlayTitle = titleBar.findObject(
                By.clazz(android.widget.TextView.class)).getText();

        mm.click(By.clazz(android.widget.TextView.class).text("播放"));

        mm.waitFor(80);

        mm.pressBack(2);
        //mm.pressBack();

        mm.waitFor(3);

        mm.pressBack();

        cc.assertTrue("Can not find playable video", newPlayTitle != null);

        mm.pressBack();

        mm.click(By.clazz(android.widget.TextView.class).text("播放历史"));

        cc.assertUiObejctExist(By.textStartsWith(newPlayTitle));
    }
}
