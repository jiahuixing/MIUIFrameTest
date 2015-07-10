package com.com.android.calculator;

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
                Lib_Frame_Constants.OPERATE_EQUAL));
        addition = marmot.getUiObject(By.clazz("android.widget.Button").res(
                Lib_Frame_Constants.OPERATE_ADD));
        subtraction = marmot.getUiObject(By.clazz("android.widget.Button").res(
                Lib_Frame_Constants.OPERATE_SUB));
        multiplication = marmot.getUiObject(By.clazz("android.widget.Button")
                .res(Lib_Frame_Constants.OPERATE_MUL));
        division = marmot.getUiObject(By.clazz("android.widget.Button").res(
                Lib_Frame_Constants.OPERATE_DIV));
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
        Lib_Frame_Utils.calculateNumber(marmot, 0, 0,
                Lib_Frame_Constants.OPERATE_SUB);
        divisionResultZero = calculatorResult.getText();
        Lib_Frame_Utils.calculateNumber(marmot, 1, 0,
                Lib_Frame_Constants.OPERATE_SUB);
        divisionResultPos = calculatorResult.getText();
        clear.click();
        marmot.waitFor(2);
        Lib_Frame_Utils.calculateNumber(marmot, 0, 1,
                Lib_Frame_Constants.OPERATE_SUB);
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
            checker.setTestrailResult("C510619", false);
            marmot.log(String.format("divisionResultZero: %s",
                    divisionResultZero));
            marmot.log(String
                    .format("divisionResultPos: %s", divisionResultPos));
            marmot.log(String
                    .format("divisionResultNeg: %s", divisionResultNeg));
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
        int firstRandomInt, secondRandomInt, maxInt = 9;
        int resultAddInt, resultSubInt, resultMulInt, resultDivInt;
        String resultAddString, resultSubString, resultMulString, resultDivString;
        clear.click();
        marmot.waitFor(2);
        firstRandomInt = random.nextInt(maxInt);
        secondRandomInt = random.nextInt(maxInt);
        Lib_Frame_Utils.calculateNumber(marmot, firstRandomInt,
                secondRandomInt, Lib_Frame_Constants.OPERATE_ADD);
        resultAddInt = firstRandomInt + secondRandomInt;
        resultAddString = calculatorResult.getText();
        clear.click();
        marmot.waitFor(2);
        firstRandomInt = random.nextInt(maxInt);
        secondRandomInt = random.nextInt(maxInt);
        Lib_Frame_Utils.calculateNumber(marmot, firstRandomInt,
                secondRandomInt, Lib_Frame_Constants.OPERATE_SUB);
        resultSubInt = firstRandomInt - secondRandomInt;
        resultSubString = calculatorResult.getText();
        clear.click();
        marmot.waitFor(2);
        firstRandomInt = random.nextInt(maxInt);
        secondRandomInt = random.nextInt(maxInt);
        Lib_Frame_Utils.calculateNumber(marmot, firstRandomInt,
                secondRandomInt, Lib_Frame_Constants.OPERATE_MUL);
        resultMulInt = firstRandomInt * secondRandomInt;
        resultMulString = calculatorResult.getText();
        clear.click();
        marmot.waitFor(2);
        firstRandomInt = random.nextInt(maxInt);
        secondRandomInt = random.nextInt(maxInt);
        Lib_Frame_Utils.calculateNumber(marmot, firstRandomInt,
                secondRandomInt, Lib_Frame_Constants.OPERATE_DIV);
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
        int calculateTimes, clickNumber;
        UiObject2 button;
        calculateTimes = random.nextInt(maxInt);
        for (int i = 0; i < calculateTimes; i++) {
            clickNumber = random.nextInt(maxInt);
            button = marmot.getUiObject(By.clazz("").res(""));
            button.click();
            marmot.waitFor(2);

        }
    }

    @Override
    public void tearDown() throws Exception {
        marmot.pressHome();
        super.tearDown();
    }
}
