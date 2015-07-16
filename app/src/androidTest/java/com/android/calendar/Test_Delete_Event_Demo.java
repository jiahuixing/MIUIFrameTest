package com.android.calendar;

import android.support.test.uiautomator.By;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

/**
 * 
 * @author e2-21 该方法不是常规的case，只用来清除日历的所有日程，方便初始化
 */
public class Test_Delete_Event_Demo extends InstrumentationTestCase {
	public Marmot mm;
	public Checker cc;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this);
		cc = new Checker(mm);
	}

	public void test_Delete_Event_Demo() {
		mm.log("Step 1 : Launch Calendar.");
		mm.pressHome();
		mm.launchActivity("com.android.calendar/.AllInOneActivity");

		mm.log("Step 2 : Click More.");
		mm.click(By.text("更多"));

		mm.log("Step 3 : Click Events");
		mm.click(By.text("日程"));

		while (mm.exist(By.res("android:id/content"))) {
			mm.log("Step 4 : Click a evnet.");
			mm.click(By.res("android:id/content"));

			mm.log("Step 5 : Click Delete.");
			mm.click(By.text("删除"));

			mm.log("Step 6 : Click OK(if the event is repeat, click All evnets).");
			if (mm.exist(By.text("所有活动"))) {
				mm.click(By.text("所有活动"));
			}
			mm.click(By.text("确定"));
		}

	}

	@Override
	protected void tearDown() throws Exception {
		mm.pressBack(2);
		super.tearDown();
	}
}
