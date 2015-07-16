package com.android.thememanager;

import android.support.test.uiautomator.By;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;


public class  Test_00000000_SearchTheme extends InstrumentationTestCase {
   
    public Marmot mm;
	public Checker cc;

    @Override
    protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this);
		cc = new Checker(mm);
	}
//  搜索主题
    public void testSearchTheme() throws Exception {
    	mm.log("Step 1 : 打开主题客户端");
    	mm.waitFor(2);
        mm.launchActivity("com.android.thememanager/com.android.thememanager.ThemeResourceTabActivity");
        mm.waitFor(5);
        mm.log("Step 2 : 点击搜索按钮");
        mm.click(By.clazz("android.widget.ImageView").res("com.android.thememanager:id/icon_search"));
        mm.waitFor(5);
        mm.log("Step 3 : 点击热搜词");
        mm.click(By.clazz("android.widget.TextView").res("com.android.thememanager:id/text"));
        mm.saveScreenshot("Searchresult.png");
        mm.waitFor(5);
        mm.pressBack();
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


