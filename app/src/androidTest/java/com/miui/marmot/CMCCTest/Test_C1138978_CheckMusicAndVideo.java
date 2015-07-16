package com.miui.marmot.CMCCTest;

import android.support.test.uiautomator.By;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_C1138978_CheckMusicAndVideo extends InstrumentationTestCase{
	public Marmot mm;
    public Checker cc;
    
    protected void setUp() throws Exception{
        super.setUp();
        mm = new Marmot(this);
        cc = new Checker(mm);
    }
    
    public void test_C1138978_CheckMusicAndVideo() throws Exception {
    	mm.log("检查是否有在线音乐");
    	mm.pressHome();
    	mm.launchActivity("com.miui.player/com.miui.player.phone.ui.MusicMainActivity");
    	mm.waitFor(2);
    	cc.assertTextNotExist("音乐库");
    	cc.assertTextNotExist("我的音乐");
    	mm.pressHome(2);    	  	   	
    	mm.log("检查是否有在线视频");
    	mm.launchActivity("com.miui.video/com.miui.video.HomeActivity");
    	mm.waitFor(2);
    	if(mm.exist(By.text("声明与条款"))){
    		cc.assertTextExist("您将使用视频功能，在使用过程中可能会联网，您是否同意？");
        	mm.waitFor(1);
        	mm.click(By.text("确定"));
        	mm.waitFor(4);
    	}
    	cc.assertTextNotExist("热点");
    	cc.assertTextNotExist("排行");

    }

    protected void tearDown() throws Exception {
        mm.pressBack(3);
        super.tearDown();
    }

}
