package com.android.thememanager;

import android.support.test.uiautomator.By;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;
public class Test_00000000_BrowseThemeDetail extends InstrumentationTestCase {
    public Marmot mm;
	public Checker cc;
    @Override
    protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this);
		cc = new Checker(mm);

	}
//  应用主题   
    public void testApplyTheme() throws Exception {
    	mm.log("Step 1 : 打开主题客户端.");
        mm.pressHome();
        mm.waitFor(2);
        mm.launchActivity("com.android.thememanager/com.android.thememanager.ThemeResourceTabActivity");
        mm.waitFor(5);
        mm.log("Step 2 : 进入本地列表页.");
        mm.click(By.clazz("android.widget.TextView").text("本地"));
        mm.waitFor(10);
        mm.log("Step 3 : 进入默认主题详情页.");
        mm.click(By.clazz("android.widget.FrameLayout").res("com.android.thememanager:id/root_flag"));
        mm.waitFor(10);   
        mm.log("Step 4 : 滑动浏览预览图.");
        for(int i=0; i<8;i++)
        {
        	
         mm.drag(1000, 1000, 10, 1000, 6);
        }
        
        mm.saveScreenshot("themedetail.png");
        mm.waitFor(2);
        mm.pressBack();
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


