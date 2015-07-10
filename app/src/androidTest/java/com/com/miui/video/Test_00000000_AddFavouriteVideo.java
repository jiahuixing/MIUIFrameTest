package com.com.miui.video;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObject2;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

import java.util.List;

public class Test_00000000_AddFavouriteVideo extends InstrumentationTestCase {
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

    public void testAddFavouriteVideo() throws Exception {
        mm.launchActivity(HOME_ACTIVITY_NAME);

        mm.click(By.clazz(android.widget.TextView.class).text("电视剧"));

        String newFavouriteTitle = null;

        List<UiObject2> objestList = mm.getUiDevice().findObjects(
                By.clazz(android.widget.ImageView.class).res(
                        PACKAGE_NAME + ":id/poster"));
        for (UiObject2 videoImage : objestList) {
            videoImage.click();
            mm.waitFor(2);

            UiObject2 addFavouriteButton = mm.getUiObject(By.clazz(android.widget.TextView.class).text("收藏"));

            if (addFavouriteButton != null && !addFavouriteButton.isSelected()) {
                UiObject2 titleBar = mm.getUiObject(By.clazz(android.widget.LinearLayout.class).res(
                        PACKAGE_NAME + ":id/title_top"));
                newFavouriteTitle = titleBar.findObject(By.clazz(android.widget.TextView.class)).getText();
                addFavouriteButton.click();
                mm.pressBack();

                break;
            }
            mm.pressBack();
        }

        cc.assertTrue("Can not find favourite video", newFavouriteTitle != null);
        mm.pressBack();

        mm.click((By.clazz(android.widget.TextView.class).text("我的收藏")));
        cc.assertTextExist(newFavouriteTitle);
    }

}