package com.com.miui.video;

import android.support.test.uiautomator.By;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_00000000_QuickLink extends InstrumentationTestCase {
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

    public void testQuickLink() throws Exception {
        mm.launchActivity(HOME_ACTIVITY_NAME);

        mm.click(By.text("播放历史"));
        cc.assertTextExist("播放历史");
        mm.pressBack();

        mm.click(By.text("我的收藏"));
        cc.assertTextExist("我的收藏");
        mm.pressBack();

        mm.click(By.text("我的离线"));
        cc.assertTextExist("我的离线");
        mm.pressBack();
    }
}