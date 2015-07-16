package com.android.deskclock;

import android.graphics.Rect;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.Direction;
import android.support.test.uiautomator.UiObject2;
import android.test.InstrumentationTestCase;
import android.view.KeyEvent;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

import java.util.List;

public class Test_Sanity01_SetDeleteAlarm extends InstrumentationTestCase {
	public Marmot mm;
	public Checker cc;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this);
		cc = new Checker(mm);
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		mm.pressHome();
	}

	public void test_Sanity01_SetDeleteAlarm() {
		// watcherEndClock();
		mm.log("Step 1 : Launch Deskclock.");
		mm.pressHome();
		mm.launchActivity("com.android.deskclock/.DeskClockTabActivity");

		mm.log("Step 2 ： Stored Alarm.");
		List<UiObject2> storedAlarms = mm.getUiDevice().findObjects(
				By.res("com.android.deskclock:id/digitalClock"));
		int alarmCount = storedAlarms.size();
		cc.assertTrue("3 stored alarm.", alarmCount == 3);
		storedAlarms.get(0).click();
		mm.waitFor(1);
		mm.click(By.text("取消"));

		mm.log("Step 3 : Set alarm.");
		mm.click(By.text("添加闹钟"));
		List<UiObject2> inputs1 = mm.getUiDevice().findObjects(
				By.clazz("android.widget.EditText"));
		// change time button
		List<UiObject2> btns = mm.getUiDevice().findObjects(
				By.clazz("android.widget.Button"));
		if (inputs1.get(inputs1.size() - 1).getText().equals("59")) {
			btns.get(btns.size() - 2).click();
			mm.waitFor(1);
		}
		btns.get(btns.size() - 1).click();
		mm.waitFor(1);
		// alarm time,控件发生改变，已过期，需要再次声明
		List<UiObject2> inputs2 = mm.getUiDevice().findObjects(
				By.clazz("android.widget.EditText"));
		String hour = inputs2.get(inputs2.size() - 2).getText();
		String minute = inputs2.get(inputs2.size() - 1).getText();
		mm.getUiObject(By.text("确定")).click();

		mm.log("Step 4 : Wait for alarm.");
		mm.getUiDevice().pressKeyCode(KeyEvent.KEYCODE_POWER);
		mm.waitFor(60);
		cc.assertTextExist("滑动关闭闹钟");
		mm.move(Direction.UP);
		mm.waitFor(2);
		cc.assertUiObejctExist(By.textEndsWith(hour + ":" + minute));

		mm.log("Step 5 : Delete the added alarm.");
		Rect bounds = mm.getUiObject(By.textEndsWith(hour + ":" + minute))
				.getVisibleBounds();
		mm.drag(bounds.centerX(), bounds.centerY(), bounds.centerX(),
				bounds.centerY(), 10);
		mm.waitFor(1);
		mm.click(By.text("删除闹钟"));
		mm.click(By.text("删除"));
		cc.assertUiObejctNotExist(By.textEndsWith(hour + ":" + minute));
	}
}
