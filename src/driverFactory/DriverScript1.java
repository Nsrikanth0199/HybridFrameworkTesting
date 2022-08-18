package driverFactory;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunctions.FunctionLibrary;
import constant.AppUtil;
import utilities.ExcelFileUtil;

public class DriverScript1 extends AppUtil {
	private static final Throwable Info = null;
	String inputpath ="C:\\SRIKANTH\\eclipse\\RajuTesting\\Hybrid_FrameWork\\TestInput\\DataEngine.xlsx";
	String outputpath ="C:\\SRIKANTH\\eclipse\\RajuTesting\\Hybrid_FrameWork\\TestOutput\\HybridResults.xlsx";
	String TCSheet ="MasterTCS";
    String TSSheet ="TestSteps";
    ExtentReports reports;
    ExtentTest test;
	private ExtentReports report;
	private LogStatus logStatus;
    @Test
    public void starttest() throws Throwable {
    	report=new ExtentReports("./Reports/Hybridtest.html");
    	boolean res =false;
    	String tcres ="";
    	ExcelFileUtil xl=new ExcelFileUtil(inputpath);
    	//count no of rows in sheets
    	int TCCount =xl.rowcount(TCSheet);
    	int TSCount = xl.rowcount(TSSheet);
    	Reporter.log("No of rows in TCSheet::"+TCCount+"  "+"No of rows in TSSheet::"+TCCount,true);
    	for(int i=1;i<=TCCount;i++) {
    		String Modulename =xl.getcellData(TCSheet, i, 1);
    		test=report.startTest(Modulename);
    	}
    	//count no of cells in TCSheet,TSSheet
    	int TCCell =xl.cellCount(TCSheet);
    	int TSCell =xl.cellCount(TSSheet);
    	Reporter.log(TCCount+"  "+TSCount+"  "+TCCell+"   "+TSCell,true);
    	//itearate all rows in TCSheet
    	for(int i=1;i<=TCCount;i++) {
    	String executionmode = xl.getcellData(TCSheet, i, 2);
    	if(executionmode.equalsIgnoreCase("Y")) {
    		String tcid = xl.getcellData(TCSheet, i, 0);
    		String Modulename =xl.getcellData(TCSheet, i,1);
    		//iterate all rows in TSSheet
    		for(int j=1;j<=TSCount;j++) {
    			String Description=xl.getcellData(TSSheet, j, 2);
    			String tsid=xl.getcellData(TSSheet, j,0);
    		if(tcid.equalsIgnoreCase(tsid)) {
    		String keyword = xl.getcellData(TSSheet, j,3);
    		if(keyword.equalsIgnoreCase("AdminLogin")) {
    		String para1 = xl.getcellData(TSSheet, j,5);
    		String para2 = xl.getcellData(TSSheet, j,6);
    		res =FunctionLibrary.VerifyLogin(para1, para2);
    		test.log(logStatus.INFO, Description);
    		}
    		else if(keyword.equalsIgnoreCase("Branch Creation"))
    		{
    	String para1 = xl.getcellData(TSSheet, j,5);	
    	String para2 = xl.getcellData(TSSheet, j,6);
    	String para3 = xl.getcellData(TSSheet, j,7);
    	String para4 = xl.getcellData(TSSheet, j,8);
    	String para5 = xl.getcellData(TSSheet, j,9);
    	String para6 = xl.getcellData(TSSheet, j,10);
    	String para7 = xl.getcellData(TSSheet, j,11);
    	String para8 = xl.getcellData(TSSheet, j,12);
    	String para9 = xl.getcellData(TSSheet, j,13);
    	FunctionLibrary.clickbranches();
    	res = FunctionLibrary.verifyBranch(para1, para2, para3, para4, para5, para6, para7, para8, para9);
    	test.log(logStatus.INFO, Description);
    		}
    		else if(keyword.equalsIgnoreCase("BranchUpdate"))
    		{
    			String para1 = xl.getcellData(TSSheet, j,5);
    			String para2 = xl.getcellData(TSSheet, j,6);
    			String para3 = xl.getcellData(TSSheet, j,9);
    			String para4 = xl.getcellData(TSSheet, j,10);
    			FunctionLibrary.clickbranches();
    			res = FunctionLibrary.verifyBranchUpdate(para1, para2, para3, para4);
    			test.log(logStatus.INFO, Description);
    			
    		}
    		else if(keyword.equalsIgnoreCase("AdminLogout"))
    		{
    			res = FunctionLibrary.verifyLogout();
    			test.log(logStatus.INFO, Description);
    		}
    		String tsres ="";
    		if(res)
    		{
    			//write as pass into status cell
    			tsres ="pass";
    			xl.setcellData(TSSheet,j,4,tsres,outputpath);
    			test.log(logStatus.PASS, Description);
    		}
    		else
    		{
    			tsres ="fail";
    			xl.setcellData(TSSheet, j, 4, tsres,outputpath);
    			test.log(logStatus.FAIL, Description);
    		}
    		tcres=tsres;
    		}
    		report.endTest(test);
        	report.flush();
        	
    		}
    		xl.setcellData(TCSheet, i,3,tcres,outputpath);
    	}
    	else
    	{
    		//write as blocked which test case flag to N
    		xl.setcellData(TCSheet, i,3,"Blocked",outputpath);
    	}
   
    	
    	}
    }
}
