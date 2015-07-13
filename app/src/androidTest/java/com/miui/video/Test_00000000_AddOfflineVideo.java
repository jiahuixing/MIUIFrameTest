package com.miui.video;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObject2;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

import java.util.List;

public class Test_00000000_AddOfflineVideo extends InstrumentationTestCase {
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

    public void testAddOfflineVideo() throws Exception {
        mm.launchActivity(HOME_ACTIVITY_NAME);
        mm.log("Step 1 : Open video.");

        mm.click(By.clazz(android.widget.TextView.class).text("电影"));
        mm.log("Step 2 : Enter movie channel.");
        mm.waitFor(3);

        mm.click(By.res(PACKAGE_NAME + ":id/tv_tab_indicator").text("新剧"));
        mm.log("Step 3 : Enter New tab.");
        mm.waitFor(3);

        String newOfflineTitle = null;

        List<UiObject2> objestList = mm.getUiDevice().findObjects(
                By.clazz(android.widget.RelativeLayout.class).res(
                        PACKAGE_NAME + ":id/channel_rank_item_layout"));
        for (UiObject2 videoImage : objestList) {
            videoImage.click();
            mm.waitFor(2);

            UiObject2 offlineButton = mm.getUiObject(By.clazz(
                    android.widget.TextView.class).text("离线"));
            if (offlineButton != null && offlineButton.isEnabled()) {
                UiObject2 titleBar = mm.getUiObject(By.clazz(
                        android.widget.LinearLayout.class).res(
                        PACKAGE_NAME + ":id/title_top"));
                newOfflineTitle = titleBar.findObject(
                        By.clazz(android.widget.TextView.class)).getText();
                mm.log("Step 3: Add offline video.");
                offlineButton.click();
                mm.pressBack();

                break;
            }
            mm.pressBack();
        }

        cc.assertTrue("Can not find offline video", newOfflineTitle != null);

        mm.pressBack();
        mm.log("Step 4: Go to Offline view and check added result.");
        mm.click(By.clazz(android.widget.TextView.class).text("我的离线"));

        mm.waitFor(2);

        mm.click(By.clazz(android.widget.RelativeLayout.class).res(
                PACKAGE_NAME + ":id/offline_media_bar"));

        cc.assertTextExist(newOfflineTitle);
        cc.setTestrailResult("c1122592", true);
    }
}
