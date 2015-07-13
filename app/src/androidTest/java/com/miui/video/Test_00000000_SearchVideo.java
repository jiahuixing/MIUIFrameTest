package com.miui.video;

import android.support.test.uiautomator.By;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_00000000_SearchVideo extends InstrumentationTestCase {
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

    public void testSearchVideo() throws Exception {
        mm.launchActivity(HOME_ACTIVITY_NAME);
        mm.log("Step 1 : Open video.");

        mm.click((By.clazz(android.widget.ImageView.class).res(PACKAGE_NAME
                + ":id/search_button")));
        mm.log("Step 2 : Open search view.");
        cc.assertUiObejctExist(By.clazz(android.widget.EditText.class).res(
                PACKAGE_NAME + ":id/search_name"));

        mm.log("Step 3 : Input text.");
        String searchText = "快乐大本营";

        mm.setText(
                By.clazz(android.widget.EditText.class).res(
                        PACKAGE_NAME + ":id/search_name"), searchText);

        mm.click(By.clazz(android.widget.TextView.class).res(
                PACKAGE_NAME + ":id/search_btn"));

        mm.waitFor(5);

        mm.log("Step 4 : Check search result.");
        cc.assertUiObejctExist(By.clazz(android.widget.ListView.class).res(
                PACKAGE_NAME + ":id/list_content"));
        cc.assertUiObejctExist(By.text(searchText));
        mm.saveScreenshot("ScreenShot_search.png");
        cc.setTestrailResult("c1122607", true);
    }

}
