package com.android.thememanager;

import android.support.test.uiautomator.By;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;
public class Test_00000000_SwitchTab extends InstrumentationTestCase {
    public Marmot mm;
	public Checker cc;
    @Override
    protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this);
		cc = new Checker(mm);

	}
//  切换tab  
    public void testApplyTheme() throws Exception {
    	mm.log("Step 1 : 打开主题客户端.");
        mm.pressHome();
        mm.waitFor(2);
        mm.launchActivity("com.android.thememanager/com.android.thememanager.ThemeResourceTabActivity");
        mm.waitFor(5);
        mm.log("Step 2 : 进入排行页.");
        mm.click(By.clazz("android.widget.TextView").text("排行"));
        mm.waitFor(10);    
        mm.log("Step 3 : 切换到免费列表页.");
        mm.click(By.clazz("android.widget.TextView").text("免费"));
        mm.waitFor(10);   
        mm.log("Step 4 : 切换到新品列表页.");
        mm.click(By.clazz("android.widget.TextView").text("新品"));
        mm.waitFor(10);
        mm.saveScreenshot("newtheme.png");
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

