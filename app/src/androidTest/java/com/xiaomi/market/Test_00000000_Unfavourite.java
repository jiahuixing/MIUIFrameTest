package com.xiaomi.market;

import android.support.test.uiautomator.By;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;
public class Test_00000000_Unfavourite extends InstrumentationTestCase {
public Marmot mm;
public Checker cc;
@Override
protected void setUp() throws Exception{
super.setUp();
mm = new Marmot(this);
cc = new Checker(mm);
}
public void test_unfavourite() throws Exception {
mm.log("Step 1 : Launch Market Activity and Enter favourite list.");
mm.pressHome();
mm.launchActivity("com.xiaomi.market/com.xiaomi.market.ui.MarketTabActivity");
mm.saveScreenshot("精品列表.png");
mm.waitFor(5);
mm.click(By.clazz("android.widget.TextView").text("我的"));
mm.waitFor(3);
mm.click(545, 630);
mm.saveScreenshot("我的收藏界面.png");
mm.waitFor(5);
mm.log("Step 2 : click one app and unfavourite.");
mm.click(545, 450);
mm.waitFor(3);
mm.click(By.clazz("android.widget.ImageView").res("com.xiaomi.market:id/first_button"));
mm.waitFor(3);
mm.saveScreenshot("应用取消收藏详情界面.png");
mm.log("Step 3 : back to favourite list.");
mm.pressBack();
mm.waitFor(3);
mm.saveScreenshot("收藏列表界面.png");
cc.setTestrailResult("c512921", true);
mm.log("Step 4 : Quit.");
mm.pressBack();
mm.pressHome();
}
@Override
protected void tearDown() throws Exception {
mm.pressBack(3);
super.tearDown();
}
}