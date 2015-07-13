package com.miui.marmot.lib;

import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Environment;
import android.os.RemoteException;
import android.os.StatFs;
import android.os.SystemClock;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.BySelector;
import android.support.test.uiautomator.Direction;
import android.support.test.uiautomator.UiCollection;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.test.InstrumentationTestCase;
import android.util.Log;
import android.view.KeyEvent;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Y.M. on 2015/5/21.
 */
public class Marmot {
    private UiDevice mDevice = null;
    private Context mContext = null;
    protected String mCasePath = null;
    protected String mFailedImgName = null;
    protected String mStandardImgName = null;
    private final int MOVE_STEPS = 6;
    private final String rootPath = "/sdcard/MIUI/autotest/";
    protected static final String LOG_TAG = "MIUIAUTOTEST";

    public Marmot(InstrumentationTestCase testCase) {
        Instrumentation instrumentation = testCase.getInstrumentation();
        mDevice = UiDevice.getInstance(instrumentation);
        mContext = instrumentation.getContext();

        mCasePath = rootPath + getCaller() + "/";
        mFailedImgName = mCasePath + getFailedImgName();
        mStandardImgName = mCasePath + "standard.png";

        File caseFolder = new File(mCasePath);
        this.deleteDirs(caseFolder);
        caseFolder.mkdirs();
    }

    /**
     * 获得当前设备
     * @return 设备
     */
    public UiDevice getUiDevice() {
        return mDevice;
    }

    /**
     * 获得设备包的上下文
     * @return 上下文
     */
    public Context getContext() {
        return mContext;
    }

    /**
     * 点击屏幕坐标为(x,y)的位置1次
     * @param x x坐标
     * @param y y坐标
     */
    public void click(int x, int y) {
        mDevice.click(x, y);
        waitFor(0.5);
    }

    /**
     * 连续点击屏幕坐标为(x,y)的位置times次
     * @param x x坐标
     * @param y y坐标
     * @param times 点击次数
     */
    public void click(int x, int y, int times) {
        for (int iterator = 0; iterator < times; iterator++) {
            mDevice.click(x, y);
        }
    }

    /**
     * 连续点击屏幕坐标为(x,y)的位置times次，每次间隔sleepSeconds的秒
     * @param x x坐标
     * @param y y坐标
     * @param times 点击次数
     * @param sleepSeconds 间隔时间
     */
    public void click(int x, int y, int times, double sleepSeconds) {
        for (int iterator = 0; iterator < times; iterator++) {
            mDevice.click(x,y);
            waitFor(sleepSeconds);
        }
    }

    /**
     * 长按选择器bySelector选择的ui元素
     * @param bySelector 选择器
     */
    public void longClick(BySelector bySelector) {
        Rect bounds = getUiObject(bySelector).getVisibleBounds();
        drag(bounds.centerX(), bounds.centerY(), bounds.centerX(), bounds.centerY(), MOVE_STEPS);
    }

    /**
     * 从(startX,startY)滑动到(endX,endY)，滑动的步数为steps
     * @param startX 起始x坐标
     * @param startY 起始y坐标
     * @param endX 结束x坐标
     * @param endY 结束y坐标
     * @param steps 滑动步数
     * @return 是否成功
     */
    public boolean drag (int startX, int startY, int endX, int endY, int steps){
        return mDevice.drag(startX, startY, endX, endY, steps);
    }

    /**
     * 向direction方向移动
     * @param direction 方向，UP,DOWN,LEFT,RIGHT
     */
    public void move(Direction direction) {
        int x = (int)(getDisplayWidth() / 2);
        int y = getDisplayHeight() - 5;
        int x2 = getDisplayWidth() - 5;
        int y2 = (int)(getDisplayHeight() / 2);

        switch(direction){
            case UP:
                drag(x, y, x, 5, MOVE_STEPS);
                break;
            case DOWN:
                drag(x, 5, x, y, MOVE_STEPS);
                break;
            case LEFT:
                drag(x2, y2, 5, y2, MOVE_STEPS);
                break;
            case RIGHT:
                drag(5, y2, x2, y2, MOVE_STEPS);
                break;
        default:
            break;
        }
    }

    /**
     * 点击Home按钮
     */
    public void pressHome(){
        mDevice.pressHome();
        waitFor(0.5);
    }

    /**
     * 点击Home按钮times次
     * @param times 点击次数
     */
    public void pressHome(int times){
        int i = 0;
        while( i++ < times ){
            pressHome();
        }
    }

    /**
     * 点击回退Back按钮
     */
    public void pressBack(){
        mDevice.pressBack();
        waitFor(0.5);
    }

    /**
     * 点击回退Back按钮times次
     * @param times 点击次数
     */
    public void pressBack(int times){
        int i = 0;
        while( i++ < times ){
            pressBack();
        }
    }

    /**
     * 点击菜单Menu按钮
     */
    public void pressMenu(){
        mDevice.pressMenu();
        waitFor(0.5);
    }

    /**
     * 点击菜单Menu按钮times次
     * @param times 点击次数
     */
    public void pressMenu(int times){
        int i = 0;

        while( i++ < times ){
            pressMenu();
        }

    }

    /**
     * 点击删除Delete按钮
     */
    public void pressDelete(){
        mDevice.pressDelete();
        waitFor(0.5);
    }

    /**
     * 点击删除Delete按钮times次
     * @param times 点击次数
     */
    public void pressDelete(int times){
        int i = 0;

        while( i++ < times ){
            pressDelete();
        }

    }

    /**
     * 点击搜索Search按钮
     */
    public void pressSearch(){
        mDevice.pressSearch();
    }

    /**
     * 根据选择器bySelector获得对应的Ui元素
     * @param bySelector 选择器
     * @return Ui元素
     */
    public UiObject2 getUiObject(BySelector bySelector) {
        return mDevice.findObject(bySelector);
    }

    /**
     * 判断选择器bySelector选择的Ui元素是否存在
     * @param bySelector 选择器
     * @return 是否存在
     */
    public boolean exist(BySelector bySelector) {
        return mDevice.findObject(bySelector) == null ? false : true;
    }

    /**
     * 点击选择器bySelector选择的Ui元素
     * @param bySelector 选择器
     */
    public void click(BySelector bySelector) {
        mDevice.findObject(bySelector).click();
        waitFor(2);
    }

    /**
     * 滑动到list Ui元素底端
     */
    public void scrollListViewToEnd() throws UiObjectNotFoundException {
        new UiScrollable(new UiSelector().className("android.widget.ListView")).scrollToEnd(10);
    }

    /**
     * 设置可编辑对象的文本内容设置为text
     * @param bySelector 选择器
     * @param text 文本内容
     * @return 是否成功
     */
    public boolean setText(BySelector bySelector, String text) {
        UiObject2 editText = mDevice.findObject(bySelector);
        if(editText == null) {
        	throw new NullPointerException();
        }
        //check object class
        if(editText.getClassName().contains("EditText")) {
            //clear
            for(int iteration = 0; iteration < 3; iteration++) {
                editText.click();
                pressDelete(editText.getText().length());
                waitFor(1);
            }
            //set
            editText.setText(text);
            waitFor(2);
            if(editText.getText().equals(text)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 在path路径下创建文件
     * @param 文件路径
     * @return 是否成功
     */
    public boolean createFile(String path){
        File file = new File(path);

        if (!fileExist(path)){
            if (file.isFile()){
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else{
                file.mkdirs();
            }
        }

        if (fileExist(path) == true){
            log("Create " + path);
            return true;
        }

        log("Create " + path + " failed.");

        return false;
    }

    /**
     * 删除文件dir
     * @param dir 文件路径
     * @return 是否成功
     */
    public boolean deleteDirs(File dir){
        if (dir == null || !dir.exists() || !dir.isDirectory()){
            return false;
        }

        for (File file : dir.listFiles()) {
            if (file.isFile()){
                file.delete();
            }
            else if (file.isDirectory()){
                deleteDirs(file);
            }
        }
        dir.delete();

        if(dir.exists() == true){
            return false;
        }

        return true;
    }

    /**
     * 判断path文件是否存在
     * @return 是否存在
     */
    public boolean fileExist(String path){
        if(new File(path).exists()){
            return true;
        }

        return false;
    }

    /**
     * 获取当前设备的旋转
     * @return 旋转方向
     */
    public int getDisplayRotation (){
        return mDevice.getDisplayRotation();
    }

    /**
     * 判断当前屏幕是否为亮
     * @return 是否亮
     */
    public boolean isScreenOn(){
        boolean result = false;

        try {
            result = mDevice.isScreenOn();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 唤醒设备
     */
    public void wakeUp(){
        try {
            mDevice.wakeUp();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取设备显示屏的高度
     * @return 高度
     */
    public int getDisplayHeight(){
        return mDevice.getDisplayHeight();
    }

    /**
     * 获取设备显示屏的宽度
     * @return 宽度
     */
    public int getDisplayWidth(){
        return mDevice.getDisplayWidth();
    }

    /**
     * 获取设备当前包的包名
     * @return 包名
     */
    public String getCurrentPackageName(){
        return mDevice.getCurrentPackageName();
    }

    /**
     * 获得系统的时间
     * @return 时间的格式为yyyy-MM-dd HH:mm:ss
     */
    public String getSystemTime(){
        SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        return formatter.format(curDate);
    }

    /**
     * 获得设备的名字
     * @return 设备名
     */
    public String getProductName(){
        return mDevice.getProductName();
    }

    /**启动activity名为actiName的activity
     * @param activityName
     */
    public void launchActivity(String activityName) {
        Intent intent = new Intent();
        String pkgName = activityName.split("/")[0];
        String atyName = activityName.split("/")[1];

        intent.setClassName(pkgName, atyName.startsWith(".") ? (pkgName + atyName) : atyName);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
        waitFor(5);
    }

    /**
     * 打开最近应用面板
     * @return 是否成功
     */
    public boolean launchRecentPanel(){
        boolean result = false;

        try {
            result = mDevice.pressRecentApps();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 打开通知中心
     * @return 是否成功
     */
    public boolean openNotification (){
        return mDevice.openNotification();
    }

    /**
     * 保存截图，截图名为imgName
     * @param imgName 图片名字
     */
    public void saveScreenshot(String imgName){
        mDevice.takeScreenshot(new File(mCasePath + "//" + imgName));
        waitFor(3);
    }

    /**
     * 对屏幕截图，保存在storePath，图片的规模为scale，质量为quality
     * @param scale 默认为1.0
     * @param quality 默认为90%
     * @return 是否成功
     */
    public boolean takeScreenshot (File storePath, float scale, int quality){
        boolean result = false;

        result = mDevice.takeScreenshot(storePath, scale, quality);
        mDevice.waitForIdle(1);

        return result;
    }

    /**
     * 等待seconds秒
     */
    public void waitFor(double seconds) {
        SystemClock.sleep((long) (seconds * 1000));
    }

    /**
     * 记录message为日志
     */
    public void log(String message){
        Log.i(LOG_TAG, message);
    }

    @Deprecated
    public void call(String telNumber) throws Exception {
        String desc;
        mDevice.pressKeyCode(KeyEvent.KEYCODE_CALL);
        waitFor(3);
        for (char number : telNumber.toCharArray()) {
            switch (number) {
                case '0':
                    desc = "零";
                    break;
                case '1':
                    desc = "一";
                    break;
                case '2':
                    desc = "二";
                    break;
                case '3':
                    desc = "三";
                    break;
                case '4':
                    desc = "四";
                    break;
                case '5':
                    desc = "五";
                    break;
                case '6':
                    desc = "六";
                    break;
                case '7':
                    desc = "七";
                    break;
                case '8':
                    desc = "八";
                    break;
                case '9':
                    desc = "九";
                    break;
                default:
                    throw new Exception("telNumber format invalid.");
            }
            mDevice.findObject(By.desc(desc)).click();
        }
        waitFor(3);
        mDevice.pressKeyCode(KeyEvent.KEYCODE_CALL);
        log("Call tel: " + telNumber);
        waitFor(3);
    }

    @Deprecated
    public void clickOnButton(int instance) throws UiObjectNotFoundException {
        mDevice.findObject(new UiSelector().className("android.widget.Button").instance(instance)).click();
    }

    @Deprecated
    public void clickOnButton(String text) throws UiObjectNotFoundException{
        mDevice.findObject(new UiSelector().className("android.widget.Button").text(text)).click();
    }

    @Deprecated
    public void clickOnCheckBox(int instance) throws UiObjectNotFoundException{
        mDevice.findObject(new UiSelector().className("android.widget.CheckBox").instance(instance)).click();
    }


    @Deprecated
    public void clickOnEditText(int instance) throws UiObjectNotFoundException{
        mDevice.findObject(new UiSelector().className("android.widget.EditText").instance(instance)).click();
    }

    @Deprecated
    public void clickOnEditText(String text) throws UiObjectNotFoundException{
        mDevice.findObject(new UiSelector().className("android.widget.EditText").text(text)).click();
    }

    @Deprecated
    public void clickOnImage(int instance) throws UiObjectNotFoundException{
        mDevice.findObject(new UiSelector().className("android.widget.ImageView").instance(instance)).click();
    }

    @Deprecated
    public void clickOnImageButton(int instance) throws UiObjectNotFoundException{
        mDevice.findObject(new UiSelector().className("android.widget.ImageButton").instance(instance)).click();
    }

    @Deprecated
    public void clickOnImageButton(String desc) throws UiObjectNotFoundException{
        mDevice.findObject(new UiSelector().className("android.widget.ImageButton").description(desc)).click();
    }

    @Deprecated
    public void clickOnRadioButton(int instance) throws UiObjectNotFoundException{
        mDevice.findObject(new UiSelector().className("android.widget.RadioButton").instance(instance)).click();
    }

    @Deprecated
    public void clickOnToggleButton(int instance) throws UiObjectNotFoundException{
        mDevice.findObject(new UiSelector().className("android.widget.ToggleButton").instance(instance)).click();
    }

    @Deprecated
    public void clickOnText(String text) throws UiObjectNotFoundException{
        mDevice.findObject(By.text(text)).click();
    }

    @Deprecated
    public void clickOnDescription(String desc) throws Exception{
        mDevice.findObject(By.descContains(desc)).click();
    }

    @Deprecated
    public void clickOnTextInList(String text) throws UiObjectNotFoundException{
        new UiScrollable(new UiSelector().className("android.widget.ListView")).scrollTextIntoView(text);
        mDevice.findObject(By.text(text)).click();

    }

    @Deprecated
    public void finish() {
    }

    @Deprecated
    public float  getAvailableSDcardSize(){
        float size = -1;

        try{
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            size = statFs.getBlockSize() *  statFs.getAvailableBlocks() / 1024;
        }catch(Exception e){
            log("Get available SD card size failed.");
        }

        return size;
    }

    @Deprecated
    public float getAvailableSystemSize(){
        float size = -1;

        try{
            StatFs statFs = new StatFs(Environment.getRootDirectory().getPath());
            size = statFs.getBlockSize() *  statFs.getAvailableBlocks() / 1024;
        }catch(Exception e){
            log("Get available SD card size failed.");
        }

        return size;
    }

    @Deprecated
    public UiObject getChildObject(UiSelector container, UiSelector child) throws UiObjectNotFoundException{
        UiObject object =null;

        object = new UiCollection(container).getChild(child);

        return object.exists() ? object : null;
    }

    @Deprecated
    public UiObject getChildObjectOfList(UiSelector child) throws UiObjectNotFoundException{
        UiScrollable scrollable  = null;
        UiObject object = null;

        scrollable = new UiScrollable(new UiSelector().className("android.widget.ListView"));

        if(scrollable.scrollIntoView(child)){
            object = new UiCollection(new UiSelector().className("android.widget.ListView")).getChild(child);

            waitFor(1);
            if (object.exists()){
                return object;
            }
        }

        return null;
    }

    @Deprecated
    public UiObject getObjectByText(String text){
        return new UiObject(new UiSelector().text(text));
    }

    @Deprecated
    public UiObject getObjectByText(String text, String className){
        return new UiObject(new UiSelector().className(className).text(text));
    }

    @Deprecated
    public UiObject getObjectByTextContain(String text){
        return new UiObject(new UiSelector().textContains(text));
    }

    @Deprecated
    public UiObject getObjectByTextContain(String text, String className){
        return new UiObject(new UiSelector().className(className).textContains(text));
    }

    @Deprecated
    public UiObject getObjectByClass(String className){
        return new UiObject(new UiSelector().className(className));
    }

    @Deprecated
    public UiObject getObjectByClass(String className, int instance){
        return new UiObject(new UiSelector().className(className).instance(instance));
    }

    @Deprecated
    public UiObject getObjectByDescription(String description, String className){
        return new UiObject(new UiSelector().className(className).description(description));
    }

    @Deprecated
    public UiObject getObjectByDescription(String description){
        return new UiObject(new UiSelector().description(description));
    }

    @Deprecated
    public void move(int startX, int startY, int endX, int endY){
        mDevice.swipe(startX, startY, endX, endY, MOVE_STEPS);
        waitFor(1);
    }

    /**
     * 获取失败截图的命名。
     * @return 图片名称
     */
    private String getFailedImgName(){
        return "failed_" + getSystemTime().replaceAll("-|\\s|:", "") + ".png";
    }

    /**
     * 获取测试CASE的类名
     * @return 测试case的类名
     */
    private String getCaller(){
        StackTraceElement stack[] = Thread.currentThread().getStackTrace();
        for (StackTraceElement ste:stack){
            if (ste.getMethodName().contains("setUp")){
                return ste.getFileName().replace(".java", "");
            }
        }

        return null;
    }

}
