package com.android.thememanager;

import android.support.test.uiautomator.By;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;


public class Test_00000000_CollectTheme extends InstrumentationTestCase {
   
    public Marmot mm;
	public Checker cc;
	

    @Override
    protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this);
		cc = new Checker(mm);
    }
		
//  收藏主题
    public void testDownlaadTheme() throws Exception {
    	mm.log("Step 1 : 打开主题客户端.");
        mm.pressHome();
        mm.waitFor(2);
        mm.launchActivity("com.android.thememanager/com.android.thememanager.ThemeResourceTabActivity");
        mm.waitFor(10);
        mm.log("Step 2 : 进入排行页.");
        mm.click(By.clazz("android.widget.TextView").text("排行"));
        mm.waitFor(15);
        mm.log("Step 3 : 进入主题详情页.");
        mm.click(By.clazz("android.widget.ImageView").res("com.android.thememanager:id/thumbnail"));
        mm.saveScreenshot("CollectTheme1.png");
        mm.log("Step 4 : 点击收藏按钮.");
        mm.click(By.clazz("android.widget.ImageView").res("com.android.thememanager:id/rightBottomBtn"));
        mm.waitFor(5);
        mm.pressBack();
        mm.pressBack();
        mm.log("Step 5 : 进入我的帐户页.");
        mm.click(By.clazz("android.widget.ImageView").res("com.android.thememanager:id/icon_account"));
        mm.waitFor(5);
        mm.log("Step 6 : 进入我的收藏页.");
        mm.click(By.text("我的收藏"));
        mm.waitFor(5);
        mm.saveScreenshot("CollectTheme2.png");
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


