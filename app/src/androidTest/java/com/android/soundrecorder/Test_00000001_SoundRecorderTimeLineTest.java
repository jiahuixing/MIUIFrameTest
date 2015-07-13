package com.android.soundrecorder;

/**
 * Project name : marmot-cases2
 * Package name : com.android.soundrecorder
 * Created by jiahuixing
 * Created on 2015-07-13
 * Created at 14:21
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

public class Test_00000001_SoundRecorderTimeLineTest extends
		InstrumentationTestCase {

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

	public void test_00000001_SoundRecorderTimeLineTest() throws Exception {
		int testStep = 0;
		testStep += 1;
		marmot.log(String.format("%s. launch sound recorder.", testStep));
		marmot.launchActivity(Lib_Frame_Constants.ACTIVITY_NAME_SOUND_RECORDER);
		// Lib_Frame_Utils.launchActivityNoHistory(context,
		// Lib_Frame_Constants.ACTIVITY_NAME_SOUND_RECORDER);
		// marmot.waitFor(2);
		checker.assertTrue(
				"launch",
				marmot.getCurrentPackageName().equals(
						Lib_Frame_Constants.PACKAGE_NAME_SOUND_RECORDER));
		UiObject2 recordList;
		recordList = marmot.getUiObject(By.clazz("android.widget.ImageButton")
				.res("com.android.soundrecorder:id/btn_list"));
		testStep += 1;
		// marmot.log("15. quit fm.");
		marmot.log(String.format("%s. sound recorder record list.", testStep));
		recordList.click();
		marmot.waitFor(1);
		UiObject2 cloudRecord;
		cloudRecord = marmot.getUiObject(By.clazz("android.widget.TextView")
				.textContains("云录音"));
		if (cloudRecord != null) {
			testStep += 1;
			// marmot.log("15. quit fm.");
			marmot.log(String.format("%s. cloud record.", testStep));
			cloudRecord.click();
			marmot.waitFor(1);
			marmot.log("cloud download list.");
			UiObject2 cloudDownloadList;
			cloudDownloadList = marmot.getUiObject(By.clazz(
					"android.widget.ImageButton").res(
					"com.android.soundrecorder:id/btn_download_list"));
			testStep += 1;
			// marmot.log("15. quit fm.");
			marmot.log(String.format("%s. cloud download list.", testStep));
			cloudDownloadList.click();
			marmot.waitFor(1);
			marmot.pressBack();
			marmot.waitFor(1);
		}
		Lib_Frame_Utils.backHome(marmot);
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
		UiObject2 immersionMenu, immersionMenuList, fmRecordList, quit;
		immersionMenu = marmot.getUiObject(By.clazz(
				"android.widget.ImageButton").res("com.miui.fm:id/btn_menu"));
		testStep += 1;
		// marmot.log("15. quit fm.");
		marmot.log(String.format("%s. fm record list.", testStep));
		immersionMenu.click();
		marmot.waitFor(2);
		marmot.saveScreenshot("immersionMenu"
				+ Lib_Frame_Constants.IMAGE_EXTENSION);
		immersionMenuList = marmot.getUiObject(By
				.clazz("android.widget.ListView"));
		checker.assertTrue("immersionMenuList", immersionMenuList != null);
		fmRecordList = marmot.getUiObject(By.clazz("android.widget.TextView")
				.text("录音列表"));
		fmRecordList.click();
		marmot.waitFor(2);
		checker.assertTrue("fm record list.", marmot.getCurrentPackageName()
				.equals(Lib_Frame_Constants.PACKAGE_NAME_SOUND_RECORDER));
		String currentPackageName;
		while (true) {
			currentPackageName = marmot.getCurrentPackageName();
			if (currentPackageName.equals(Lib_Frame_Constants.PACKAGE_NAME_FM)) {
				break;
			} else {
				marmot.pressBack();
				marmot.waitFor(1);
			}
		}
		// Lib_Frame_Utils.launchActivityNoHistory(context,
		// Lib_Frame_Constants.PACKAGE_NAME_FM);
		marmot.waitFor(2);
		testStep += 1;
		// marmot.log("15. quit fm.");
		marmot.log(String.format("%s. quit fm.", testStep));
		immersionMenu.click();
		marmot.waitFor(2);
		quit = marmot.getUiObject(By.clazz("android.widget.TextView")
				.text("退出"));
		quit.click();
		marmot.waitFor(2);
	}

	@Override
	public void tearDown() throws Exception {
		Lib_Frame_Utils.backHome(marmot);
		super.tearDown();
	}
}
