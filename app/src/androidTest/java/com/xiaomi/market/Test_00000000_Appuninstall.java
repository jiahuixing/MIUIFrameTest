package com.xiaomi.market;

import android.support.test.uiautomator.By;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_00000000_Appuninstall extends InstrumentationTestCase {
public Marmot mm;
public Checker cc;
@Override
protected void setUp() throws Exception {
super.setUp();
mm = new Marmot(this);
cc = new Checker(mm);
}
public void test_uninstall() throws Exception {
mm.log("Step 1 : Launch Market Activity and Enter uninstall list.");
mm.pressHome();
mm.launchActivity("com.xiaomi.market/com.xiaomi.market.ui.MarketTabActivity");
mm.saveScreenshot("精品列表.png");
mm.waitFor(5);
mm.click(By.clazz("android.widget.TextView").text("我的"));
mm.waitFor(3);
mm.click(545, 450);
mm.saveScreenshot("应用卸载界面.png");
mm.log("Step 2 : click one app.");
mm.click(545, 450);
mm.waitFor(3);
mm.saveScreenshot("卸载应用信息界面。png");
mm.log("Step 3 : back.");
mm.pressBack();
mm.waitFor(3);
mm.log("Step 4 : longpress one app and delete.");
mm.longClick(By.clazz("android.widget.TextView").text("大众点评"));
mm.waitFor(3);
mm.click(By.text("删除"));
mm.waitFor(3);
mm.log("Step 5 : cancel delete and delete.");
mm.click(By.text("取消"));
mm.waitFor(3);
mm.click(By.text("删除"));
mm.waitFor(3);
mm.click(By.text("删除选中应用"));
mm.waitFor(10);
cc.assertTextNotExist("大众点评");
mm.waitFor(10);
mm.saveScreenshot("应用卸载后列表界面。png");
cc.setTestrailResult("c512908", true);
mm.log("Step 6 : Quit.");
mm.pressBack();
mm.pressHome();
}
@Override
protected void tearDown() throws Exception {
mm.pressBack(3);
super.tearDown();
}
}