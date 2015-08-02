package com.android.calculator;

/**
 * Project name : marmot-cases2
 * Package name : com.android.calculator
 * Created by jiahuixing
 * Created on 2015-07-06
 * Created at 13:48
 */

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiCollection;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
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

    public static int testStep = 0;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        marmot = new Marmot(this);
        checker = new Checker(marmot);
        uiDevice = marmot.getUiDevice();
        Lib_Frame_Utils.unLock(marmot);
    }

    public void test_Calculator() throws Exception {
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
        UiObject2 clear, equal, switcher;
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

        if (getCalculatorResultText().equals("0")) {
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
        calculateNumber(0, 0, Lib_Frame_Constants.OPERATION_DIV);
        divisionResultZero = getCalculatorResultText();
        // marmot.log(String.format("divisionResultZero = %s",
        // divisionResultZero));
        clear.click();
        marmot.waitFor(2);
        // 1 / 0
        calculateNumber(1, 0, Lib_Frame_Constants.OPERATION_DIV);
        divisionResultPos = getCalculatorResultText();
        // marmot.log(String.format("divisionResultPos = %s",
        // divisionResultPos));
        clear.click();
        marmot.waitFor(2);
        // -1 / 0
        calculateNumber(0, 1, Lib_Frame_Constants.OPERATION_SUB);
        division.click();
        marmot.waitFor(2);
        zero.click();
        marmot.waitFor(2);
        equal.click();
        marmot.waitFor(2);
        divisionResultNeg = getCalculatorResultText();
        // marmot.log(String.format("divisionResultNeg = %s",
        // divisionResultNeg));
        marmot.log(String
                .format("divisionResultZero = %s, divisionResultPos: %s, divisionResultNeg: %s",
                        divisionResultZero, divisionResultPos,
                        divisionResultNeg));
        if (divisionResultZero.equals("NaN") && divisionResultPos.equals("∞")
                && divisionResultNeg.equals("减∞")) {
            checker.setTestrailResult("C510619", true);
        } else {
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
        randomCalculator();
        checker.assertTrue(
                "random",
                marmot.getCurrentPackageName().equals(
                        Lib_Frame_Constants.PACKAGE_NAME_CALCULATOR));
        checker.setTestrailResult("C510621", true);
        testStep += 1;
        marmot.log(String.format("%s. + - * /.", testStep));
        Random random = new Random();
        int firstRandomInt, secondRandomInt, numberCount = 10;
        int resultAddInt, resultSubInt, resultMulInt, resultDivInt;
        String resultAddString, resultSubString, resultMulString, resultDivString;
        clear.click();
        marmot.waitFor(2);
        firstRandomInt = random.nextInt(numberCount);
        secondRandomInt = random.nextInt(numberCount);
        calculateNumber(firstRandomInt, secondRandomInt,
                Lib_Frame_Constants.OPERATION_ADD);
        resultAddInt = firstRandomInt + secondRandomInt;
        resultAddString = getCalculatorResultText();
        clear.click();
        marmot.waitFor(2);
        firstRandomInt = random.nextInt(numberCount);
        secondRandomInt = random.nextInt(numberCount);
        calculateNumber(firstRandomInt, secondRandomInt,
                Lib_Frame_Constants.OPERATION_SUB);
        resultSubInt = firstRandomInt - secondRandomInt;
        resultSubString = getCalculatorResultText();
        clear.click();
        marmot.waitFor(2);
        firstRandomInt = random.nextInt(numberCount);
        secondRandomInt = random.nextInt(numberCount);
        calculateNumber(firstRandomInt, secondRandomInt,
                Lib_Frame_Constants.OPERATION_MUL);
        resultMulInt = firstRandomInt * secondRandomInt;
        resultMulString = getCalculatorResultText();
        clear.click();
        marmot.waitFor(2);
        firstRandomInt = random.nextInt(numberCount);
        secondRandomInt = random.nextInt(numberCount);
        calculateNumber(firstRandomInt, secondRandomInt,
                Lib_Frame_Constants.OPERATION_DIV);
        resultDivInt = firstRandomInt / secondRandomInt;
        resultDivString = getCalculatorResultText();
        marmot.log(String.format("resultAddString: %s, resultAddInt: %s",
                resultAddString, resultAddInt));
        marmot.log(String.format("resultSubString: %s, resultSubInt: %s",
                resultSubString, resultSubInt));
        marmot.log(String.format("resultMulString: %s, resultMulInt: %s",
                resultMulString, resultMulInt));
        marmot.log(String.format("resultDivString: %s, resultDivInt: %s",
                resultDivString, resultDivInt));
        if (resultAddString.equals(Integer.toString(resultAddInt))
                && resultSubString.equals(Integer.toString(resultSubInt))
                && resultMulString.equals(Integer.toString(resultMulInt))
                && resultDivString.equals(Integer.toString(resultDivInt))) {
            checker.setTestrailResult("C510622", true);
        } else {
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
        resultString = getCalculatorResultText();
        marmot.log(String.format("resultInt = %s, resultString = %s",
                resultInt, resultString));
        if (resultString.equals(Integer.toString(resultInt))) {
            checker.setTestrailResult("C510622", true);
        } else {
            checker.setTestrailResult("C510622", false);
        }
        testStep += 1;
        marmot.log(String.format("%s. quit calculator.", testStep));
        Lib_Frame_Utils.backToPackage(marmot,
                Lib_Frame_Constants.PACKAGE_NAME_HOME);
    }

    public void calculateNumber(int firstNumber, int secondNumber,
            String operation) {
        marmot.log(String.format("calculateNumber: %s %s %s = ?", firstNumber,
                operation, secondNumber));
        UiObject2 firstNumberButton, secondNumberButton, operate, equal;
        firstNumberButton = marmot.getUiObject(By
                .clazz("android.widget.Button").res(
                        Lib_Frame_Constants.CALCULATOR_NUMBER_RES
                                .get(firstNumber)));
        secondNumberButton = marmot.getUiObject(By.clazz(
                "android.widget.Button").res(
                Lib_Frame_Constants.CALCULATOR_NUMBER_RES.get(secondNumber)));
        operate = marmot.getUiObject(By.clazz("android.widget.Button").res(
                Lib_Frame_Constants.CALCULATOR_OPERATION_RES.get(operation)));
        equal = marmot.getUiObject(By.clazz("android.widget.Button").res(
                Lib_Frame_Constants.CALCULATOR_OPERATION_RES
                        .get(Lib_Frame_Constants.OPERATION_EQUAL)));
        firstNumberButton.click();
        marmot.waitFor(2);
        operate.click();
        marmot.waitFor(2);
        secondNumberButton.click();
        marmot.waitFor(2);
        equal.click();
        marmot.waitFor(2);
    }

    private String getCalculatorResultText() {
        String calculatorResultText;
        UiObject2 calculatorResult;
        calculatorResult = marmot.getUiObject(
                By.clazz("android.widget.ViewSwitcher").res(
                        "com.android.calculator2:id/display")).findObject(
                By.clazz("android.widget.EditText"));
        calculatorResultText = calculatorResult.getText().trim();
        return calculatorResultText;
    }

    public void randomCalculator() throws UiObjectNotFoundException {
        // UiObject2 simpleButtonContainer, scientificButtonContainer, button;
        UiObject2 switcher;
        UiCollection simpleButtonContainer, scientificButtonContainer;
        UiObject button;
        switcher = marmot.getUiObject(By.clazz("android.widget.ImageView").res(
                "com.android.calculator2:id/switch_btn"));
        simpleButtonContainer = new UiCollection(new UiSelector().className(
                "android.widget.LinearLayout").resourceId(
                "com.android.calculator2:id/simple_pad"));
        scientificButtonContainer = new UiCollection(new UiSelector()
                .className("android.widget.LinearLayout").resourceId(
                        "com.android.calculator2:id/scientific_pad"));
        // simpleButtonContainer = marmot.getUiObject(By.clazz(
        // "android.widget.LinearLayout").res(
        // "com.android.calculator2:id/simple_pad"));
        // scientificButtonContainer = marmot.getUiObject(By.clazz(
        // "android.widget.LinearLayout").res(
        // "com.android.calculator2:id/scientific_pad"));
        Random random = new Random();
        int buttonCount, rnd;
        for (int i = 0; i < 2; i++) {
            if (simpleButtonContainer.exists()) {
                marmot.log(String.format("simpleButtonContainer: %s", i));
                marmot.saveScreenshot("simpleButtonContainer"
                        + Lib_Frame_Constants.IMAGE_EXTENSION);
                buttonCount = simpleButtonContainer
                        .getChildCount(new UiSelector()
                                .className("android.widget.Button"));
                for (int j = 0; j < buttonCount; j++) {
                    rnd = random.nextInt(buttonCount);
                    button = simpleButtonContainer.getChild(new UiSelector()
                            .className("android.widget.Button").instance(rnd));
                    // marmot.log(String
                    // .format("----------------\nbutton: %s\ngetContentDescription: %s\ngetText: %s",
                    // j, button.getContentDescription(),
                    // button.getText()));
                    button.click();
                    marmot.waitFor(0.5);
                }
                switcher.click();
                marmot.waitFor(1);
                i++;
            }
            if (scientificButtonContainer.exists()) {
                marmot.log(String.format("scientificButtonContainer: %s", i));
                marmot.saveScreenshot("scientificButtonContainer"
                        + Lib_Frame_Constants.IMAGE_EXTENSION);
                buttonCount = scientificButtonContainer
                        .getChildCount(new UiSelector()
                                .className("android.widget.Button"));
                for (int j = 0; j < buttonCount; j++) {
                    rnd = random.nextInt(buttonCount);
                    button = scientificButtonContainer
                            .getChild(new UiSelector().className(
                                    "android.widget.Button").instance(rnd));
                    // marmot.log(String
                    // .format("----------------\nbutton: %s\ngetContentDescription: %s\ngetText: %s",
                    // j, button.getContentDescription(),
                    // button.getText()));
                    button.click();
                    marmot.waitFor(0.5);
                }
                switcher.click();
                marmot.waitFor(1);
                i++;
            }
        }
        // if (simpleButtonContainer != null) {
        // marmot.log("simple calculator");
        // buttonCount = simpleButtonContainer.getChildCount();
        // marmot.log("simpleButtonContainer = " + buttonCount);
        // for (int i = 0; i < buttonCount; i++) {
        // rnd = random.nextInt(buttonCount);
        // button = simpleButtonContainer.getChildren().get(rnd);
        // button.click();
        // marmot.waitFor(0.5);
        // }
        // switcher.click();
        // marmot.waitFor(2);
        // }
        // if (scientificButtonContainer != null) {
        // marmot.log("scientific calculator");
        // buttonCount = scientificButtonContainer.getChildCount();
        // marmot.log("scientificButtonContainer = " + buttonCount);
        // for (int i = 0; i < buttonCount; i++) {
        // rnd = random.nextInt(buttonCount);
        // button = scientificButtonContainer.getChildren().get(rnd);
        // button.click();
        // marmot.waitFor(0.5);
        // }
        // switcher.click();
        // marmot.waitFor(2);
        // }
    }

    @Override
    public void tearDown() throws Exception {
        Lib_Frame_Utils.backToPackage(marmot,
                Lib_Frame_Constants.PACKAGE_NAME_HOME);
        super.tearDown();
    }
}
