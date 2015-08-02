package com.miui.phone;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.Direction;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_00000004_Phone extends InstrumentationTestCase {
	public static final String PACKAGE_NAME_Settings = "com.android.settings";
	public static final String ACTIVITY_NAME_Settings = "com.android.settings/.MiuiSettings";
	public static final String PACKAGE_NAME_KEY_GUARD = "com.android.keyguard";
	public static final String IMAGE_EXTENSION = ".png";
	public Marmot marmot;
	public Checker checker;
	public UiDevice uiDevice;

	@Override
	public void setUp() throws Exception {
		super.setUp();
		marmot = new Marmot(this);
		checker = new Checker(marmot);
		uiDevice = marmot.getUiDevice();

		marmot.log("Step 1 : Screen unlock.");
		if (!marmot.isScreenOn()) {
			marmot.wakeUp();
			marmot.waitFor(1);
		}
		if (marmot.getCurrentPackageName().equals(PACKAGE_NAME_KEY_GUARD)) {
			marmot.move(Direction.UP);
			marmot.waitFor(1);
		}
	}

	public void test_00000004_Phone() throws Exception {
		marmot.log("Step 2 : launch settings Activity.");
		marmot.launchActivity(ACTIVITY_NAME_Settings);
		marmot.waitFor(2);

		marmot.log("Step 3 :into settings page.");
		checker.assertTrue("settings",
				marmot.getCurrentPackageName().equals(PACKAGE_NAME_Settings));

		marmot.log("Step 3 :click.");
		UiObject2 othermode;
		othermode = marmot.getUiObject(By.text("其他连接方式"));
		othermode.click();
		marmot.waitFor(2);

		marmot.saveScreenshot("Flyclosed" + IMAGE_EXTENSION);
		marmot.log("Step 4 :flyopen.");
		UiObject2 flyopen;
		flyopen = marmot.getUiObject(By.clazz("android.widget.CheckBox").res(
				"android:id/checkbox"));
		flyopen.click();
		marmot.waitFor(2);

		marmot.log("Step 5:checkflyopen.");
		if (!flyopen.isChecked()) {
			marmot.saveScreenshot("Worng" + IMAGE_EXTENSION);
		} else {
			marmot.saveScreenshot("Right" + IMAGE_EXTENSION);
		}
		checker.setTestrailResult("C513190", true);
	}

	@Override
	public void tearDown() throws Exception {
		super.tearDown();
	}
}
// C513190 飞行模式-01：开启飞行模式
