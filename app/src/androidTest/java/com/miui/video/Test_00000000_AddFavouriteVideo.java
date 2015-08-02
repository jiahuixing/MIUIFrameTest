package com.miui.video;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObject2;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

import java.util.List;

public class Test_00000000_AddFavouriteVideo extends InstrumentationTestCase {
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

    public void testAddFavouriteVideo() throws Exception {
        mm.launchActivity(Lib_VideoConst.HOME_ACTIVITY_NAME);

        mm.log("Step 1 : Open video.");

        mm.click(By.clazz(android.widget.TextView.class).text("电视剧"));
        mm.log("Step 2: Enter TVplays channel.");
        String newFavouriteTitle = null;

        List<UiObject2> objestList = mm.getUiDevice().findObjects(
                By.clazz(android.widget.ImageView.class).res(
                        Lib_VideoConst.PACKAGE_NAME + ":id/poster"));
        for (UiObject2 videoImage : objestList) {
            videoImage.click();
            mm.waitFor(2);

            UiObject2 addFavouriteButton = mm.getUiObject(By.clazz(
                    android.widget.TextView.class).text("收藏"));

            if (addFavouriteButton != null && !addFavouriteButton.isSelected()) {
                UiObject2 titleBar = mm.getUiObject(By.clazz(
                        android.widget.LinearLayout.class).res(
                        Lib_VideoConst.PACKAGE_NAME + ":id/title_top"));
                newFavouriteTitle = titleBar.findObject(
                        By.clazz(android.widget.TextView.class)).getText();
                mm.log("Step 3 : Add Favourite Video.");
                addFavouriteButton.click();
                mm.pressBack();

                break;
            }
            mm.pressBack();
        }
        mm.pressBack();

        cc.assertTrue("Can not find favourite video", newFavouriteTitle != null);
        mm.log("Step 4 : Check My Favourite is added.");

        mm.click((By.clazz(android.widget.TextView.class).text("我的收藏")));
        cc.assertTextExist(newFavouriteTitle);
        cc.setTestrailResult("c1122587", true);
    }

}