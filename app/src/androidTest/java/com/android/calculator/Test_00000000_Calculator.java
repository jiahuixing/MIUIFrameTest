package com.android.calculator;

/**
 * Project name : marmot-cases2
 * Package name : com.android.calculator
 * Created by jiahuixing
 * Created on 2015-07-06
 * Created at 13:48
 */

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.test.InstrumentationTestCase;

import com.miui.frame.Lib_Frame_Constants;
import com.miui.frame.Lib_Frame_Utils;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

import java.util.Random;

public class Test_00000000_Calculator extends InstrumentationTestCase {

	public Marmot marmot;
	public Checker checker;
	public UiDevice uiDevice;

	@Override
	public void setUp() throws Exception {
		super.setUp();
		marmot = new Marmot(this);
		checker = new Checker(marmot);
		uiDevice = marmot.getUiDevice();
		Lib_Frame_Utils.unLock(marmot);
	}

	public void test_Calculator() throws Exception {
		int testStep = 0;
		testStep += 1;
		// marmot.log("launch calculator");
		marmot.log(String.format("%s. launch calculator.", testStep));
		marmot.launchActivity(Lib_Frame_Constants.ACTIVITY_NAME_CALCULATOR);
		marmot.waitFor(2);
		checker.assertTrue(
				"calculator not launched",
				marmot.getCurrentPackageName().equals(
						Lib_Frame_Constants.PACKAGE_NAME_CALCULATOR));
		checker.setTestrailResult("C2191456", true);
		testStep += 1;
		marmot.log(String.format("%s. clear result.", testStep));
		UiObject2 calculatorResult, clear, equal, switcher;
		UiObject2 addition, subtraction, multiplication, division;
		clear = marmot.getUiObject(By.clazz("android.widget.Button").res(
				"com.android.calculator2:id/clear"));
		equal = marmot.getUiObject(By.clazz("android.widget.Button").res(
				Lib_Frame_Constants.CALCULATOR_OPERATION_RES
						.get(Lib_Frame_Constants.OPERATION_EQUAL)));
		addition = marmot.getUiObject(By.clazz("android.widget.Button").res(
				Lib_Frame_Constants.CALCULATOR_OPERATION_RES
						.get(Lib_Frame_Constants.OPERATION_ADD)));
		subtraction = marmot.getUiObject(By.clazz("android.widget.Button").res(
				Lib_Frame_Constants.CALCULATOR_OPERATION_RES
						.get(Lib_Frame_Constants.OPERATION_SUB)));
		multiplication = marmot.getUiObject(By.clazz("android.widget.Button")
				.res(Lib_Frame_Constants.CALCULATOR_OPERATION_RES
						.get(Lib_Frame_Constants.OPERATION_MUL)));
		division = marmot.getUiObject(By.clazz("android.widget.Button").res(
				Lib_Frame_Constants.CALCULATOR_OPERATION_RES
						.get(Lib_Frame_Constants.OPERATION_DIV)));
		clear.click();
		marmot.waitFor(2);
		calculatorResult = marmot.getUiObject(By
				.clazz("android.widget.EditText"));
		if (calculatorResult.getText().equals("0")) {
			checker.setTestrailResult("C2191455", true);
		}
		testStep += 1;
		marmot.log(String.format("%s. a number divide zero.", testStep));
		UiObject2 zero;
		String divisionResultZero, divisionResultPos, divisionResultNeg;
		zero = marmot.getUiObject(By.clazz("android.widget.Button").res(
				Lib_Frame_Constants.CALCULATOR_NUMBER_RES.get(0)));
		clear.click();
		marmot.waitFor(2);
		// 0 / 0
		Lib_Frame_Utils.calculateNumber(marmot, 0, 0,
				Lib_Frame_Constants.OPERATION_DIV);
		divisionResultZero = calculatorResult.getText();
		// 1 / 0
		Lib_Frame_Utils.calculateNumber(marmot, 1, 0,
				Lib_Frame_Constants.OPERATION_DIV);
		divisionResultPos = calculatorResult.getText();
		clear.click();
		marmot.waitFor(2);
		// -1 / 0
		Lib_Frame_Utils.calculateNumber(marmot, 0, 1,
				Lib_Frame_Constants.OPERATION_SUB);
		division.click();
		marmot.waitFor(2);
		zero.click();
		marmot.waitFor(2);
		divisionResultNeg = calculatorResult.getText();
		if (divisionResultZero.equals("       NaN")
				&& divisionResultPos.equals("  ∞")
				&& divisionResultNeg.equals(" 减∞")) {
			checker.setTestrailResult("C510619", true);
		} else {
			marmot.log(String.format("divisionResultZero: %s",
					divisionResultZero));
			marmot.log(String
					.format("divisionResultPos: %s", divisionResultPos));
			marmot.log(String
					.format("divisionResultNeg: %s", divisionResultNeg));
			checker.setTestrailResult("C510619", false);
		}
		testStep += 1;
		marmot.log(String.format("%s. switch simple and scientific.", testStep));
		clear.click();
		marmot.waitFor(2);
		switcher = marmot.getUiObject(By.clazz("android.widget.ImageView").res(
				"com.android.calculator2:id/switch_btn"));
		switcher.click();
		marmot.waitFor(2);
		switcher.click();
		marmot.waitFor(2);
		checker.assertTrue(
				"switch",
				marmot.getCurrentPackageName().equals(
						Lib_Frame_Constants.PACKAGE_NAME_CALCULATOR));
		checker.setTestrailResult("C510620", true);
		testStep += 1;
		marmot.log(String.format("%s. random click.", testStep));
		clear.click();
		marmot.waitFor(2);
		Lib_Frame_Utils.randomCalculator(marmot);
		checker.assertTrue(
				"random",
				marmot.getCurrentPackageName().equals(
						Lib_Frame_Constants.PACKAGE_NAME_CALCULATOR));
		checker.setTestrailResult("C510621", true);
		testStep += 1;
		marmot.log(String.format("%s. + - * /.", testStep));
		Random random = new Random();
		int firstRandomInt, secondRandomInt, numberCount = 9;
		int resultAddInt, resultSubInt, resultMulInt, resultDivInt;
		String resultAddString, resultSubString, resultMulString, resultDivString;
		clear.click();
		marmot.waitFor(2);
		firstRandomInt = random.nextInt(numberCount);
		secondRandomInt = random.nextInt(numberCount);
		Lib_Frame_Utils.calculateNumber(marmot, firstRandomInt,
				secondRandomInt, Lib_Frame_Constants.OPERATION_ADD);
		resultAddInt = firstRandomInt + secondRandomInt;
		resultAddString = calculatorResult.getText();
		clear.click();
		marmot.waitFor(2);
		firstRandomInt = random.nextInt(numberCount);
		secondRandomInt = random.nextInt(numberCount);
		Lib_Frame_Utils.calculateNumber(marmot, firstRandomInt,
				secondRandomInt, Lib_Frame_Constants.OPERATION_SUB);
		resultSubInt = firstRandomInt - secondRandomInt;
		resultSubString = calculatorResult.getText();
		clear.click();
		marmot.waitFor(2);
		firstRandomInt = random.nextInt(numberCount);
		secondRandomInt = random.nextInt(numberCount);
		Lib_Frame_Utils.calculateNumber(marmot, firstRandomInt,
				secondRandomInt, Lib_Frame_Constants.OPERATION_MUL);
		resultMulInt = firstRandomInt * secondRandomInt;
		resultMulString = calculatorResult.getText();
		clear.click();
		marmot.waitFor(2);
		firstRandomInt = random.nextInt(numberCount);
		secondRandomInt = random.nextInt(numberCount);
		Lib_Frame_Utils.calculateNumber(marmot, firstRandomInt,
				secondRandomInt, Lib_Frame_Constants.OPERATION_DIV);
		resultDivInt = firstRandomInt / secondRandomInt;
		resultDivString = calculatorResult.getText();
		if (resultAddString.equals(Integer.toString(resultAddInt))
				&& resultSubString.equals(Integer.toString(resultSubInt))
				&& resultMulString.equals(Integer.toString(resultMulInt))
				&& resultDivString.equals(Integer.toString(resultDivInt))) {
			checker.setTestrailResult("C510622", true);
		} else {
			marmot.log(String.format("resultAddString: %s, resultAddInt: %s",
					resultAddString, resultAddInt));
			marmot.log(String.format("resultSubString: %s, resultSubInt: %s",
					resultSubString, resultSubInt));
			marmot.log(String.format("resultMulString: %s, resultMulInt: %s",
					resultMulString, resultMulInt));
			marmot.log(String.format("resultDivString: %s, resultDivInt: %s",
					resultDivString, resultDivInt));
			checker.setTestrailResult("C510622", false);
		}
		testStep += 1;
		marmot.log(String.format("%s. continuous.", testStep));
		clear.click();
		marmot.waitFor(2);
		int operateCount = 4;
		int calculateTimes, randomNumber, randomOperate, resultInt = 0;
		String resultString;
		UiObject2 numberButton;
		calculateTimes = random.nextInt(numberCount);
		marmot.log(String.format("calculateTimes = %s", calculateTimes));
		for (int i = 0; i < calculateTimes; i++) {
			randomNumber = random.nextInt(numberCount);
			numberButton = marmot.getUiObject(By.clazz("android.widget.Button")
					.res(Lib_Frame_Constants.CALCULATOR_NUMBER_RES
							.get(randomNumber)));
			randomOperate = random.nextInt(operateCount);
			marmot.log(String.format("randomOperate: %s", randomOperate));
			switch (randomOperate) {
				case 0:
					resultInt += randomNumber;
					addition.click();
					marmot.waitFor(2);
					numberButton.click();
					marmot.waitFor(2);
					equal.click();
					marmot.waitFor(2);
					break;
				case 1:
					resultInt -= randomNumber;
					subtraction.click();
					marmot.waitFor(2);
					numberButton.click();
					marmot.waitFor(2);
					equal.click();
					marmot.waitFor(2);
					break;
				case 2:
					resultInt *= randomNumber;
					multiplication.click();
					marmot.waitFor(2);
					numberButton.click();
					marmot.waitFor(2);
					equal.click();
					marmot.waitFor(2);
					break;
				case 3:
					resultInt /= randomNumber;
					subtraction.click();
					marmot.waitFor(2);
					numberButton.click();
					marmot.waitFor(2);
					equal.click();
					marmot.waitFor(2);
					break;
			}
		}
		resultString = calculatorResult.getText();
		if (resultString.equals(Integer.toString(resultInt))) {
			checker.setTestrailResult("C510622", true);
		} else {
			marmot.log(String.format("resultInt = %s, resultString = %s",
					resultInt, resultString));
			checker.setTestrailResult("C510622", false);
		}
	}

	@Override
	public void tearDown() throws Exception {
		marmot.pressHome();
		super.tearDown();
	}
}
