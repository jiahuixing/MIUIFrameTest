package com.miui.marmot.CMCCTest;

import android.support.test.uiautomator.By;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_C1138992_CheckAddMmsSize extends InstrumentationTestCase{
	public Marmot mm;
    public Checker cc;
    
    protected void setUp() throws Exception{
        super.setUp();
        mm = new Marmot(this);
        cc = new Checker(mm);
    }
    public void test_C1138992_CheckAddMmsSize() throws Exception {
        mm.log("打开短信");
    	mm.pressHome();
    	mm.waitFor(2);
    	mm.click(By.text("短信"));
        mm.waitFor(1);
    	mm.log("写彩信");
    	mm.click(By.text("写短信"));
    	mm.click(By.desc("添加附件"));
    	mm.click(By.text("拍照"));
    	mm.waitFor(5);
    	if(mm.exist(By.text("保存地理位置信息"))){
    		mm.click(By.text("开始拍照"));
        	mm.waitFor(1);
    	}
    	mm.click(By.desc("Shutter button"));
    	mm.waitFor(5);
    	mm.click(By.res("com.android.camera", "v6_btn_done"));
    	mm.waitFor(2);
    	mm.log("检查是否有彩信容量显示");
    	cc.assertUiObejctExist(By.res("com.android.mms", "text_counter"));
      	cc.assertUiObejctExist(By.res("com.android.mms", "mms_size"));
    	mm.pressHome();

    }

    protected void tearDown() throws Exception {
        mm.pressBack(3);
        super.tearDown();
    }
}
