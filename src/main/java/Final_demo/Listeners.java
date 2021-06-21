package Final_demo;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener {
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("*********Test " + iTestResult.getName() + " Start Successfully ");

    }

    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("*********Test " + iTestResult.getName() + " Pass Successfully ");

    }

    public void onTestFailure(ITestResult iTestResult) {

    }

    public void onTestSkipped(ITestResult iTestResult) {

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }
    //Before tests Executing
    public void onStart(ITestContext iTestContext) {
        System.out.println("*********Tests Started " + iTestContext.getName());

    }

    //After all test executed
    public void onFinish(ITestContext iTestContext) {
        System.out.println("*********All Tests Completed " + iTestContext.getName());

    }
}
