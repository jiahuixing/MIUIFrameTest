package com.xiaomi.market;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.Direction;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;
public class Test_00000000_Scrolllist extends InstrumentationTestCase {
public Marmot mm;
public Checker cc;
@Override
protected void setUp() throws Exception {
super.setUp();
mm = new Marmot(this);
cc = new Checker(mm);
}
public void test_Scrolllist() throws Exception {
mm.log("Step 1 : Launch Market Activity.");
mm.pressHome();
mm.launchActivity("com.xiaomi.market/com.xiaomi.market.ui.MarketTabActivity");
mm.saveScreenshot("精品列表。png");
mm.waitFor(5);
mm.log("Step 2 : Scroll and Change Tab.");
mm.scrollListViewToEnd();
mm.waitFor(5);
mm.click(By.text("排行"));
mm.waitFor(5);
mm.drag(300, 1000, 300, 300, 6);
mm.waitFor(5);
mm.click(By.text("分类"));
mm.waitFor(5);
mm.click(By.text("实用工具"));
mm.waitFor(5);
mm.scrollListViewToEnd();
mm.waitFor(5);
mm.click(By.text("排行"));
mm.waitFor(5);
mm.move(Direction.LEFT);
mm.saveScreenshot("精品列表。png");
cc.setTestrailResult("c512888", true);
mm.log("Step 3 : Quit.");
mm.pressBack();
mm.pressHome();
}
@Override
protected void tearDown() throws Exception {
mm.pressBack(3);
super.tearDown();
}
}