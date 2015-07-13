package com.miui.fm;

/**
 * Project name : marmot-cases2
 * Package name : com.miui.fm
 * Created by jiahuixing
 * Created on 2015-07-13
 * Created at 14:25
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

import java.util.Date;

public class Test_00000001_FMTimeLineTest extends InstrumentationTestCase {

	public Marmot marmot;
	public Checker checker;
	public UiDevice uiDevice;
	public Context context;

	@Override
	public void setUp() throws Exception {
		super.setUp();
		marmot = new Marmot(this);
		checker = new Checker(marmot);
		context = marmot.getContext();
		Lib_Frame_Utils.unLock(marmot);
	}

	public void test_00000001_FMTimeLineTest() throws Exception {
		int testStep = 0;
		testStep += 1;
		marmot.log(String.format("%s. launch fm.", testStep));
		marmot.launchActivity(Lib_Frame_Constants.ACTIVITY_NAME_FM);
		// Lib_Frame_Utils.launchActivityNoHistory(context,
		// Lib_Frame_Constants.ACTIVITY_NAME_FM);
		// marmot.waitFor(2);
		checker.assertTrue(
				"launch",
				marmot.getCurrentPackageName().equals(
						Lib_Frame_Constants.PACKAGE_NAME_FM));
		UiObject2 stationList, progressBar, cancel;
		UiObject2 immersionMenu, immersionMenuList, quit;
		stationList = marmot.getUiObject(By.clazz("android.widget.ImageButton")
				.res("com.miui.fm:id/btn_stations_list"));
		testStep += 1;
		// marmot.log("15. quit fm.");
		marmot.log(String.format("%s. station list.", testStep));
		stationList.click();
		marmot.waitFor(2);
		long startTime, endTime;
		startTime = new Date().getTime();
		while (true) {
			progressBar = marmot.getUiObject(By
					.clazz("android.widget.ProgressBar"));
			if (progressBar != null) {
				cancel = marmot.getUiObject(By.clazz("android.widget.Button")
						.text("取消"));
				endTime = new Date().getTime();
				if ((endTime - startTime) >= 10) {
					cancel.click();
					break;
				}
				marmot.waitFor(3);
			} else
				break;
		}
		UiObject2 newStation, scanStation;
		newStation = marmot.getUiObject(By.clazz("android.widget.Button").text(
				"新建电台"));
		scanStation = marmot.getUiObject(By.clazz("android.widget.Button")
				.text("扫描电台"));
		if (newStation != null && scanStation != null) {
			marmot.pressBack();
			marmot.waitFor(2);
		}
		immersionMenu = marmot.getUiObject(By.clazz(
				"android.widget.ImageButton").res("com.miui.fm:id/btn_menu"));
		immersionMenu.click();
		marmot.waitFor(2);
		immersionMenuList = marmot.getUiObject(By
				.clazz("android.widget.ListView"));
		checker.assertTrue("immersionMenuList", immersionMenuList != null);
		quit = marmot.getUiObject(By.clazz("android.widget.TextView")
				.text("退出"));
		testStep += 1;
		// marmot.log("15. quit fm.");
		marmot.log(String.format("%s. quit fm.", testStep));
		quit.click();
		marmot.waitFor(2);
	}

	@Override
	public void tearDown() throws Exception {
		Lib_Frame_Utils.backHome(marmot);
		super.tearDown();
	}

}
