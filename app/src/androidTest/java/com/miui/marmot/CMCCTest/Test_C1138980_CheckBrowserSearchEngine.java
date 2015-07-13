package com.miui.marmot.CMCCTest;

import android.support.test.uiautomator.By;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_C1138980_CheckBrowserSearchEngine extends InstrumentationTestCase {
	public Marmot mm;
    public Checker cc;
    
    protected void setUp() throws Exception{
        super.setUp();
        mm = new Marmot(this);
        cc = new Checker(mm);
    }
    
    public void test_C1138980_CheckBrowserSearchEngine() throws Exception {
    	mm.log("打开浏览器");
    	mm.pressHome();
    	mm.launchActivity("com.android.browser/com.android.browser.BrowserActivity");
    	
    	mm.log("选择设置地址栏搜索引擎");
    	mm.click(By.res("com.android.browser", "action_more"));
    	mm.click(By.text("设置"));
    	mm.click(By.text("设置地址栏搜索引擎"));
     	
    	mm.log("检查是否变更搜索引擎为移动要求");
    	cc.assertTextNotExist("谷歌");
    	cc.assertTextExist("139");
    	    	  	
    }    
    
    
    protected void tearDown() throws Exception {
        mm.pressBack(3);
        super.tearDown();
    }

}
