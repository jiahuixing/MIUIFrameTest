package com.miui.notes;

/**
 * Project name : marmot-cases2
 * Package name : com.miui.notes
 * Created by jiahuixing
 * Created on 2015-07-13
 * Created at 14:26
 */

import android.content.Context;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.test.InstrumentationTestCase;

import com.miui.frame.Lib_Frame_Constants;
import com.miui.frame.Lib_Frame_Utils;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_00000001_NotesTimeLineTest extends InstrumentationTestCase {

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

	public void test_NotesTimeLineTest() throws Exception {
		for (int i = 0; i < Lib_Frame_Constants.TEST_LOOPS; i++) {
			testStep += 1;
			marmot.log(String.format("%s. launch notes.", testStep));
			marmot.launchActivity(Lib_Frame_Constants.ACTIVITY_NAME_NOTES);
			// Lib_Frame_Utils.launchActivityNoHistory(context,
			// Lib_Frame_Constants.ACTIVITY_NAME_NOTES);
			// marmot.waitFor(2);
			checker.assertTrue(
					"launch",
					marmot.getCurrentPackageName().equals(
							Lib_Frame_Constants.PACKAGE_NAME_NOTES));
			UiObject2 newNote, richEditor;
			UiObject2 photo, immersionMenu, immersionMenuList;
			UiObject2 alertDialog, confirm, shoot, done;
			UiObject2 sendToSinaWeibo, publish, progressBar;
			newNote = marmot.getUiObject(By.clazz("android.widget.Button")
					.text("新建便签"));
			richEditor = marmot.getUiObject(By.clazz("android.widget.EditText")
					.res("com.miui.notes:id/rich_editor"));
			if (newNote != null) {
				testStep += 1;
				marmot.log(String.format("%s. new notes.", testStep));
				newNote.click();
				marmot.waitFor(2);
			}
			if (richEditor != null) {
				testStep += 1;
				marmot.log(String.format("%s. take phone.", testStep));
				photo = marmot.getUiObject(By.clazz("android.widget.ImageView")
						.res("com.miui.notes:id/photo"));
				photo.click();
				marmot.waitFor(4);
				checker.assertTrue("camera", marmot.getCurrentPackageName()
						.equals(Lib_Frame_Constants.PACKAGE_NAME_CAMERA));
				alertDialog = marmot.getUiObject(By.clazz(
						"android.widget.TextView").res("miui:id/alertTitle"));
				if (alertDialog != null) {
					testStep += 1;
					marmot.log(String.format("%s. alert dialog.", testStep));
					confirm = marmot.getUiObject(By.clazz(
							"android.widget.Button").text("开始拍照"));
					confirm.click();
					marmot.waitFor(2);
				}
				shoot = marmot
						.getUiObject(By
								.clazz("android.widget.ImageView")
								.res("com.android.camera:id/v6_shutter_button_internal"));
				shoot.click();
				marmot.waitFor(5);
				done = marmot.getUiObject(By.clazz("android.widget.ImageView")
						.res("com.android.camera:id/v6_btn_done"));
				done.click();
				marmot.waitFor(5);
				testStep += 1;
				marmot.log(String.format("%s. send to sina weibo.", testStep));
				immersionMenu = marmot.getUiObject(By.clazz(
						"android.widget.ImageView").res(
						"com.miui.notes:id/more"));
				immersionMenu.click();
				marmot.waitFor(2);
				marmot.saveScreenshot("immersionMenu"
						+ Lib_Frame_Constants.IMAGE_EXTENSION);
				immersionMenuList = marmot.getUiObject(By
						.clazz("android.widget.ListView"));
				checker.assertTrue("immersionMenuList",
						immersionMenuList != null);
				sendToSinaWeibo = marmot.getUiObject(By.clazz(
						"android.widget.TextView").text("发送到新浪微博"));
				sendToSinaWeibo.click();
				marmot.waitFor(2);
				confirm = marmot.getUiObject(By.clazz("android.widget.Button")
						.text("确定"));
				confirm.click();
				marmot.waitFor(2);
				publish = marmot.getUiObject(By.clazz("android.widget.Button")
						.text("发布"));
				publish.click();
				marmot.waitFor(2);
				while (true) {
					progressBar = marmot.getUiObject(By
							.clazz("android.widget.ProgressBar"));
					if (progressBar != null) {
						marmot.waitFor(3);
					} else {
						break;
					}
				}
				Lib_Frame_Utils.backToPackage(marmot,
						Lib_Frame_Constants.PACKAGE_NAME_HOME);
				testStep += 1;
				// marmot.log("15. quit fm.");
				marmot.log(String.format("%s. notes settings.", testStep));
				marmot.launchActivity(Lib_Frame_Constants.ACTIVITY_NAME_SETTINGS);
				checker.assertTrue("settings.", marmot.getCurrentPackageName()
						.equals(Lib_Frame_Constants.PACKAGE_NAME_SETTINGS));
				UiScrollable settingsList;
				settingsList = new UiScrollable(
						new UiSelector().className("android.widget.ListView"));
				UiObject notesSettings;
				notesSettings = settingsList.getChildByText(
						new UiSelector().className("android.widget.TextView"),
						"便签", true);
				notesSettings.click();
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
