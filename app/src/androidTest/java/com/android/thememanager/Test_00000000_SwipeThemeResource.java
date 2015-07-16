package com.android.thememanager;

import android.support.test.uiautomator.UiObjectNotFoundException;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_00000000_SwipeThemeResource extends InstrumentationTestCase {
   
    public Marmot mm;
	public Checker cc;

    @Override
    protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this);
		cc = new Checker(mm);
	}
//   滑动浏览
    public void testScrollThemeResource() throws UiObjectNotFoundException {
    	mm.log("Step 1 : 打开主题客户端");
        mm.pressHome();
        mm.waitFor(5);
        mm.launchActivity("com.android.thememanager/com.android.thememanager.ThemeResourceTabActivity");
		mm.waitFor(10);
		mm.log("Step 2 : 滑动列表");
		mm.scrollListViewToEnd();	
        mm.waitFor(10);
        mm.saveScreenshot("scrolllist.png");
        mm.waitFor(10);
        mm.pressBack();
        mm.pressHome();
    }
    @Override
    protected void tearDown() throws Exception {
        mm.pressBack();
        mm.pressBack();
        mm.pressHome();
        super.tearDown();
    }


}
