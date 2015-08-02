package com.miui.video;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.Direction;
import android.support.test.uiautomator.UiObject2;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

import java.util.List;

public class Test_00000000_BrowseTVPlayer extends InstrumentationTestCase {

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

    public void testBrowseTVPlayer() throws Exception {
        mm.launchActivity(Lib_VideoConst.HOME_ACTIVITY_NAME);
        mm.log("Step 1 : Open video.");
        mm.waitFor(3);
        mm.click(By.clazz(android.widget.TextView.class).text("电视直播"));
        mm.log("Step 2 : Enter TVPlay.");
        cc.setTestrailResult("c1122618", true);
        mm.waitFor(5);
        cc.assertUiObejctExist(By.res(Lib_VideoConst.PACKAGE_NAME
                + ":id/title_top_name"));

        UiObject2 listView = mm.getUiObject(By.res(Lib_VideoConst.PACKAGE_NAME
                + ":id/recycler_layout"));

        String lastTitle = null;
        mm.log("Step 3 : Play all TV channel and save picture.");
        while (true) {
            UiObject2 firstItemTextView = mm.getUiObject(By.clazz(
                    android.widget.TextView.class).res(
                    Lib_VideoConst.PACKAGE_NAME + ":id/table_item_text"));

            if (lastTitle != null
                    && lastTitle.equals(firstItemTextView.getText())) {
                break;
            }

            lastTitle = firstItemTextView.getText();

            List<UiObject2> imageList = mm.getUiDevice().findObjects(
                    By.clazz(android.widget.ImageView.class).res(
                            Lib_VideoConst.PACKAGE_NAME
                                    + ":id/media_view_image_poster"));

            int index = 1;
            for (UiObject2 object : imageList) {
                object.click();

                // mm.waitFor(2);

                // UiObject2 titleView = mm.getUiObject(By.res(PACKAGE_NAME +
                // ":id/vp_top_title"));
                // String title = titleView.getText();

                mm.waitFor(30);
                mm.saveScreenshot("ScreenShot_" + index + ".png");
                index++;

                mm.pressBack(2);

                mm.waitFor(2);

            }

            listView.scroll(Direction.DOWN, 1);
        }
        cc.setTestrailResult("c1122621", true);
        cc.setTestrailResult("c1122622", true);
    }

}
