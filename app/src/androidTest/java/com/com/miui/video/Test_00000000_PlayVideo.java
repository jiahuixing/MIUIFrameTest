package com.com.miui.video;

import android.support.test.uiautomator.By;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_00000000_PlayVideo extends InstrumentationTestCase {
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


    public void testPlayVideo() throws Exception {
        mm.launchActivity(HOME_ACTIVITY_NAME);

        mm.click(By.clazz(android.widget.ImageView.class).res(
                PACKAGE_NAME + ":id/user_icon"));

        mm.click(By.text("本地视频"));
        mm.click((By.res(PACKAGE_NAME + ":id/poster")));

        mm.waitFor(5);

        cc.assertUiObejctExist(By.res(PACKAGE_NAME + ":id/video_player_container"));

        mm.pressBack(2);
    }
}
