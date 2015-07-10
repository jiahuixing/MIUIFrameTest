#!/usr/bin/env python
#coding:utf-8
import os
import sys
import re
import string
import csv
import time

print "======================================================================="
print "If you find any bug, please contact: wangle@xiaomi.com"
print "======================================================================="

gLaunchPtn = r".*Activity_launch_request time:(\d*)"
gReadyPtn = r".*App_transition_ready time:(\d*)"
gVisiblePtn = r".*Activity_windows_visible id: ActivityRecord{.* (.*) t\d*} time:(\d*)"
gStoppedPtn = r".*App_transition_stopped time:(\d*)"

gStart = 0
gLaunchTime = 0
gReadyTime = 0
gVisibleTime = 0
gStoppedTime = 0
gActivityName = ""


gSavingFileName = "result_%i.csv"%time.time()
def writeToCSVFile(activity, launchTime, wihteScreenTime):
    with open(gSavingFileName, "a+") as csvfile:
        sw = csv.writer(csvfile, dialect='excel')
        sw.writerow([activity, launchTime, wihteScreenTime])
    csvfile.close()


def checkAndPrintResult():

    global gStart
    global gLaunchTime
    global gReadyTime
    global gVisibleTime
    global gStoppedTime
    global gActivityName

    if(gStart and gLaunchTime and gReadyTime and gVisibleTime and gStoppedTime and gActivityName):
        print "\n\n---------------------------RESULT--------------------------------"

        print "Activity Name :" + gActivityName

        launchTime = (max(gVisibleTime, gStoppedTime) - gLaunchTime)
        print "Activity Launch Time: %i" %launchTime + "ms"

        whiteScreenTime = gVisibleTime - gStoppedTime
        if whiteScreenTime < 0 :
            whiteScreenTime = 0
        print "Activity White Screen Time: %i" % whiteScreenTime + "ms"

        writeToCSVFile(gActivityName, launchTime, whiteScreenTime)

        gStart = 0
        gLaunchTime = 0
        gReadyTime = 0
        gVisibleTime = 0
        gStoppedTime = 0
        gActivityName = ""

        print "============================================================================================\n\n\n\n"





def printAndCheckNewLine(newline):

    global gStart
    global gLaunchTime
    global gReadyTime
    global gVisibleTime
    global gStoppedTime
    global gActivityName


    # check launch
    matchValues = re.match(gLaunchPtn, newline, re.M|re.I)
    if  not gStart and matchValues:
        gLaunchTime = string .atoi(matchValues.group(1))
        gStart = 1;
        print "\n\n\n\n\n\n\n\n============================================================================================"
    if gStart:
        # check ready
        matchValues = re.match(gReadyPtn, newline, re.M|re.I)
        if matchValues:
            gReadyTime = string .atoi(matchValues.group(1))
        # check visible
        matchValues = re.match(gVisiblePtn, newline, re.M|re.I)
        if matchValues:
            gActivityName = matchValues.group(1)
            gVisibleTime = string .atoi(matchValues.group(2))
        # check stop
        matchValues = re.match(gStoppedPtn, newline, re.M|re.I)
        if matchValues:
            gStoppedTime = string .atoi(matchValues.group(1))

    print newline

    checkAndPrintResult()


command = "adb logcat -s Timeline"
pipe = os.popen(command)

while(1):
    printAndCheckNewLine(pipe.readline().strip());
