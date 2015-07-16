package com.miui.compass;

/**
 * Project name : marmot-cases2
 * Package name : com.miui.compass
 * Created by jiahuixing
 * Created on 2015-07-13
 * Created at 14:23
 */

import android.content.Context;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.test.InstrumentationTestCase;

import com.miui.frame.Lib_Frame_Constants;
import com.miui.frame.Lib_Frame_Utils;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_00000001_CompassTimeLineTest extends InstrumentationTestCase {

	public Marmot marmot;
	public Checker checker;
	public UiDevice uiDevice;
	public Context context;
	public static int testStep = 0;

	@Override
	public void setUp() throws Exception {
		super.setUp();
		marmot = new Marmot(this);
		checker = new Checker(marmot);
		context = marmot.getContext();
		Lib_Frame_Utils.unLock(marmot);
	}

	public void test_CompassTimeLineTest() throws Exception {
		for (int i = 0; i < Lib_Frame_Constants.TEST_LOOPS; i++) {
			testStep += 1;
			marmot.log(String.format("%s. launch compass.", testStep));
			marmot.launchActivity(Lib_Frame_Constants.ACTIVITY_NAME_COMPASS);
			// Lib_Frame_Utils.launchActivityNoHistory(context,
			// Lib_Frame_Constants.ACTIVITY_NAME_COMPASS);
			// marmot.waitFor(2);
			checker.assertTrue(
					"launch",
					marmot.getCurrentPackageName().equals(
							Lib_Frame_Constants.PACKAGE_NAME_COMPASS));
			UiObject2 alertDialog, confirm;
			alertDialog = marmot
					.getUiObject(By.clazz("android.widget.TextView").res(
							"miui:id/alertTitle"));
			if (alertDialog != null) {
				testStep += 1;
				marmot.log(String.format("%s. alert dialog.", testStep));
				marmot.saveScreenshot("alertDialog"
						+ Lib_Frame_Constants.IMAGE_EXTENSION);
				confirm = marmot.getUiObject(By.clazz("android.widget.Button")
						.text("同意并继续"));
				confirm.click();
				marmot.waitFor(2);
			}
			Lib_Frame_Utils.backToPackage(marmot,
					Lib_Frame_Constants.PACKAGE_NAME_HOME);
		}
	}

	@Override
	public void tearDown() throws Exception {
		Lib_Frame_Utils.backToPackage(marmot,
				Lib_Frame_Constants.PACKAGE_NAME_HOME);
		super.tearDown();
	}

}
