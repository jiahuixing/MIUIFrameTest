package com.miui.video;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObject2;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_00000000_PlayOffsetvideo extends InstrumentationTestCase {
    private static final String PACKAGE_NAME = "com.miui.video";
    private static final String HOME_ACTIVITY_NAME = PACKAGE_NAME
            + "/.HomeActivity";
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
        mm.pressBack(3);

        mm.pressHome();
        super.tearDown();
    }

    public void testPlayOffsetvideo() throws Exception {
        mm.launchActivity(HOME_ACTIVITY_NAME);
        mm.log("Step 1 : Open video.");

        mm.waitFor(1);
        mm.click(By.clazz(android.widget.TextView.class).text("爆点"));
        mm.log("Step 2 : Go to Offset tab.");
        mm.waitFor(1);

        mm.log("Step 3 : Play first offset video.");
        mm.click(By.clazz(android.widget.ImageView.class).res(
                PACKAGE_NAME + ":id/image_ads"));
        mm.waitFor(5);

        mm.click(mm.getDisplayWidth() / 2, mm.getDisplayHeight() / 2);
        mm.saveScreenshot("ScreenShot_offset1.png");

        mm.pressBack(2);
        mm.waitFor(3);

        UiObject2 object = mm.getUiObject(By.text("爆点"));
        cc.assertTrue("Tab is not selected", object.isSelected());

        mm.log("Step 4 : Play second offset video.");
        mm.click(By.clazz(android.widget.ImageView.class).res(
                PACKAGE_NAME + ":id/poster"));
        mm.waitFor(5);
        mm.click(mm.getDisplayWidth() / 2, mm.getDisplayHeight() / 2);
        mm.saveScreenshot("ScreenShot_offset2.png");

        mm.pressBack(2);
        cc.setTestrailResult("c1560959", true);
    }
}
