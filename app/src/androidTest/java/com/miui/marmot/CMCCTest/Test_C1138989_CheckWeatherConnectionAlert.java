package com.miui.marmot.CMCCTest;

import android.support.test.uiautomator.By;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;


public class Test_C1138989_CheckWeatherConnectionAlert extends InstrumentationTestCase {

	public Marmot mm;
    public Checker cc;
    
    protected void setUp() throws Exception{
        super.setUp();
        mm = new Marmot(this);
        cc = new Checker(mm);
    }
    
    public void test_C1138989_CheckWeatherConnectionAlert() throws Exception {
    	mm.log("打开天气");
    	mm.pressHome();
    	mm.launchActivity("com.miui.weather2/com.miui.weather2.ActivityWeatherMain");
    	mm.log("判断是否有联网提醒");
    	cc.assertTextExist("联网提示");
    	cc.assertTextExist("需要联网获取天气信息，有可能产生流量");
    	mm.click(By.res("android:id/button2"));
    }

    protected void tearDown() throws Exception {
        mm.pressBack(3);
        super.tearDown();
    }
}
