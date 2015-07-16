package com.miui.livetalk;

/**
 * Project name : marmot-cases2
 * Package name : com.miui.livetalk
 * Created by jiahuixing
 * Created on 2015-07-14
 * Created at 14:06
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

import java.util.Random;

public class Test_00000000_LiveTalk extends InstrumentationTestCase {

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

	public void test_LiveTalk() throws Exception {
		int testStep = 0;
		testStep += 1;
		marmot.log(String.format("%s. launch contacts.", testStep));
		marmot.launchActivity(Lib_Frame_Constants.ACTIVITY_NAME_CONTACTS_DIALER);
		checker.assertTrue(
				"launch",
				marmot.getCurrentPackageName().equals(
						Lib_Frame_Constants.PACKAGE_NAME_CONTACTS));
		UiObject2 more;
		more = marmot.getUiObject(By.clazz("").res(""));
		if (more != null) {
			testStep += 1;
			marmot.log(String.format("%s. more.", testStep));
			more.click();
			marmot.waitFor(2);
			UiObject2 immersionList, enableLiveTalk, defaultPhone, defaultLiveTalk, liveTalkRecharge;
			immersionList = marmot.getUiObject(By.clazz(""));
			checker.assertTrue("list", immersionList != null);
			enableLiveTalk = marmot.getUiObject(By.clazz("").text(""));
			if (enableLiveTalk != null) {
				testStep += 1;
				marmot.log(String.format("%s. enable LiveTalk.", testStep));
				enableLiveTalk.click();
				marmot.waitFor(2);
			} else {
				defaultPhone = marmot.getUiObject(By.clazz("").text(""));
				defaultLiveTalk = marmot.getUiObject(By.clazz("").text(""));
				defaultPhone.click();
				marmot.waitFor(2);
				defaultLiveTalk.click();
				marmot.waitFor(2);
				liveTalkRecharge = marmot.getUiObject(By.clazz("").text(""));
				liveTalkRecharge.click();
				marmot.waitFor(2);
				UiObject2 productList, selectedProduct;
				productList = marmot.getUiObject(By.clazz("").res(""));
				Random random = new Random();
				int productCount, randomInt;
				productCount = productList.getChildCount();
				randomInt = random.nextInt(productCount);
				selectedProduct = productList.getChildren().get(randomInt);
				selectedProduct.click();
				marmot.waitFor(2);
			}
		}
	}

	@Override
	public void tearDown() throws Exception {
		Lib_Frame_Utils.backToPackage(marmot,
				Lib_Frame_Constants.PACKAGE_NAME_HOME);
		super.tearDown();
	}
}
