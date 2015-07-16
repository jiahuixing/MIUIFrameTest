package com.miui.phone;

/**
 * Project name : marmot-cases2
 * Package name : com.miui.phone
 * Created by jiahuixing
 * Created on 2015-07-14
 * Created at 11:05
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

import java.util.Random;

public class Test_00000001_PhoneTimeLineTest extends InstrumentationTestCase {

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

	public void test_PhoneTimeLineTest() throws Exception {
		for (int i = 0; i < Lib_Frame_Constants.TEST_LOOPS; i++) {
			int simCardCount;
			testStep += 1;
			marmot.log(String.format("%s. launch contacts.", testStep));
			marmot.launchActivity(Lib_Frame_Constants.ACTIVITY_NAME_CONTACTS_DIALER);
			checker.assertTrue(
					"launch",
					marmot.getCurrentPackageName().equals(
							Lib_Frame_Constants.PACKAGE_NAME_CONTACTS));
			UiObject2 showDialPad, dialPad;
			dialPad = marmot.getUiObject(By
					.clazz("android.widget.LinearLayout").res(
							"com.android.contacts:id/dialpad"));
			if (dialPad == null) {
				showDialPad = marmot.getUiObject(By.clazz(
						"android.widget.Button").text("拨号"));
				showDialPad.click();
				marmot.waitFor(2);
			}
			checker.assertTrue("dialNumber",
					Lib_Frame_Utils.dialNumber(marmot, "112"));
			// Lib_Frame_Utils.dialNumber(marmot, "112");
			UiObject2 dialButtonSim1, dialButtonSim2;
			Random random = new Random();
			int randomInt;
			dialButtonSim1 = marmot.getUiObject(By.clazz(
					"android.widget.Button").res(
					"com.android.contacts:id/call_sim1"));
			dialButtonSim2 = marmot.getUiObject(By.clazz(
					"android.widget.Button").res(
					"com.android.contacts:id/call_sim2"));
			if (dialButtonSim1 == null || dialButtonSim2 == null) {
				simCardCount = 1;
				if (dialButtonSim1 != null) {
					dialButtonSim1.click();
					marmot.waitFor(2);
				}
				if (dialButtonSim2 != null) {
					dialButtonSim2.click();
					marmot.waitFor(2);
				}
			} else {
				simCardCount = 2;
				randomInt = random.nextInt(2);
				switch (randomInt) {
				case 0:
					dialButtonSim1.click();
					break;
				case 1:
					dialButtonSim2.click();
					break;
				}
				marmot.waitFor(2);
			}
			if (!marmot.getCurrentPackageName().equals(
					Lib_Frame_Constants.PACKAGE_NAME_IN_CALL_UI)) {
				testStep += 1;
				marmot.log(String.format("%s. no mobile net work.", testStep));
				UiObject2 message, confirm;
				message = marmot.getUiObject(By
						.clazz("android.widget.TextView")
						.res("miui:id/message").text("无法访问移动网络"));
				if (message != null) {
					confirm = marmot.getUiObject(By.clazz(
							"android.widget.Button").text("确定"));
					confirm.click();
					marmot.waitFor(2);
				}
			} else {
				randomInt = Lib_Frame_Utils.getRandomInt(10, 3);
				testStep += 1;
				marmot.log(String.format("%s. end this call after %s seconds.",
						testStep, randomInt));
				marmot.waitFor(randomInt);
				UiObject2 endThisCall;
				endThisCall = marmot.getUiObject(By.clazz(
						"android.widget.Button").res(
						"com.android.incallui:id/endButton"));
				if (endThisCall != null) {
					endThisCall.click();
					marmot.waitFor(2);
				}
			}
			Lib_Frame_Utils.backToPackage(marmot,
					Lib_Frame_Constants.PACKAGE_NAME_HOME);
			marmot.launchActivity(Lib_Frame_Constants.ACTIVITY_NAME_SETTINGS);
			checker.assertTrue("settings.", marmot.getCurrentPackageName()
					.equals(Lib_Frame_Constants.PACKAGE_NAME_SETTINGS));
			UiScrollable settingsList;
			settingsList = new UiScrollable(
					new UiSelector().className("android.widget.ListView"));
			UiObject mobileNetworkSettings, phoneSettings;
			String mobileNetworkSettingsText;
			if (simCardCount == 1) {
				mobileNetworkSettingsText = "移动网络";
			} else {
				mobileNetworkSettingsText = "双卡和移动网络";
			}
			mobileNetworkSettings = settingsList.getChildByText(
					new UiSelector().className("android.widget.TextView"),
					mobileNetworkSettingsText, true);
			if (mobileNetworkSettings.isEnabled()) {
				testStep += 1;
				marmot.log(String.format("%s. net work settings.", testStep));
				mobileNetworkSettings.click();
				marmot.waitFor(2);
				Lib_Frame_Utils.backToPackage(marmot,
						Lib_Frame_Constants.PACKAGE_NAME_SETTINGS);
			}
			testStep += 1;
			marmot.log(String.format("%s. phone settings.", testStep));
			phoneSettings = settingsList.getChildByText(
					new UiSelector().className("android.widget.TextView"),
					"电话", true);
			phoneSettings.click();
			marmot.waitFor(2);
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
