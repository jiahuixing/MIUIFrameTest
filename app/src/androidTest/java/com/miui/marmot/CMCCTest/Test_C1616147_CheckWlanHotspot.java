package com.miui.marmot.CMCCTest;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObject2;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;
public class Test_C1616147_CheckWlanHotspot extends InstrumentationTestCase{
	public Marmot mm;
    public Checker cc;
    
    protected void setUp() throws Exception{
        super.setUp();
        mm = new Marmot(this);
        cc = new Checker(mm);
    }
	public void test_C1616147_CheckWlanHotspot() throws Exception {
		mm.log("判断是否关闭数据流量，如未关闭，则关闭");
		mm.log("打开设置");
    	mm.pressHome();
    	mm.launchActivity("com.android.settings/com.android.settings.MiuiSettings"); 
        mm.waitFor(3);
        if(mm.exist(By.text("双卡和移动网络"))){
        	mm.click(By.text("双卡和移动网络"));
        }else if(mm.exist(By.text("移动网络"))){
        	mm.click(By.text("移动网络"));
        }
        mm.waitFor(1);
        UiObject2 switcher = this.mm.getUiDevice().findObject(By.text("启用数据网络")).getParent().getParent().getChildren().get(1).getChildren().get(0);
        if (switcher.isChecked()) {
        	mm.click(By.text("启用数据网络"));
        }
        mm.pressBack(1);
        mm.waitFor(1);
        mm.log("打开便携式WLAN热点");
        mm.click(By.text("其他连接方式"));
        mm.waitFor(1);
        mm.click(By.text("网络共享"));
        mm.waitFor(1);
        mm.click(By.text("便携式 WLAN 热点"));
        mm.log("检查是否弹出开启数据流量提示");
        mm.waitFor(2);
        cc.assertTextExist("是否同时开启数据网络？");
        mm.click(By.text("确定"));
        mm.waitFor(3);
        mm.pressBack(2);
        if(mm.exist(By.text("双卡和移动网络"))){
        	mm.click(By.text("双卡和移动网络"));
        }else if(mm.exist(By.text("移动网络"))){
        	mm.click(By.text("移动网络"));
        }
        mm.waitFor(1);
        mm.log("检查是否成功开启数据网络");
        int X;
        UiObject2 switcher1 = this.mm.getUiDevice().findObject(By.text("启用数据网络")).getParent().getParent().getChildren().get(1).getChildren().get(0);
        if (switcher1.isChecked()) {
        	mm.log("成功同时开启数据网络");
        	X=1;
        }else{
        	mm.log("打开数据网络失败");
        	mm.click(By.text("启用数据网络"));
        	X=0;
        }
        mm.pressBack(1);
        mm.waitFor(2);
        mm.click(By.text("其他连接方式"));
        mm.waitFor(1);
        mm.click(By.text("网络共享"));
        mm.waitFor(1);
        mm.click(By.text("便携式 WLAN 热点"));
        mm.waitFor(1);
        mm.pressBack(3);
        if (X==0){
        	cc.assertTextExist("此处只为返回一个错误值");
        }
    }

    protected void tearDown() throws Exception {
        mm.pressBack(3);
        super.tearDown();
    }

}
