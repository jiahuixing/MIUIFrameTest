package com.miui.video;

import android.support.test.uiautomator.By;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_00000000_AddFavouriteVideo extends InstrumentationTestCase {
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

    public void testAddFavouriteVideo() throws Exception {
        mm.launchActivity(HOME_ACTIVITY_NAME);

        String newFavouriteTitle = Lib_VideoUtil.addFavouriteVideo(mm);
        
        cc.assertTrue("Can not find favourite video", newFavouriteTitle != null);
        mm.log("Step 4 : Check My Favourite is added.");
        
        mm.click((By.clazz(android.widget.TextView.class).text("我的收藏")));
        cc.assertTextExist(newFavouriteTitle);
        cc.setTestrailResult("c1122587", true);
    }

}