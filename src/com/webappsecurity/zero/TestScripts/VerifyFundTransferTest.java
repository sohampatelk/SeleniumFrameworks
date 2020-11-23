package com.webappsecurity.zero.TestScripts;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.webappsecurity.zero.Pages.*;

import utils.GenericMethods;

public class VerifyFundTransferTest extends Base {

	@Test
	public void verifyFundTransfer() throws IOException {
		Login lp = new Login(driver);
		AccountSummary acc = new AccountSummary(driver);
		TransferFunds tf = new TransferFunds(driver);
		TransferFundsVerify tfv = new TransferFundsVerify(driver);
		TransferFundsConfirmation tfc = new TransferFundsConfirmation(driver);

		String[][] data = GenericMethods
			.getData("C:\\Users\\soham\\Desktop\\Selenium\\Selenium_Workspace\\TestData.xlsx", "Sheet1");

		for (int i = 0; i < data.length; i++) {
			lp.applicationLogin(data[i][0], data[i][1]);
			acc.clickFundTransfer();
			tf.transferFunds(data[i][2], data[i][3]);
			tfv.clickSubmit();
			String actualMsg = tfc.getConfText();
			Assert.assertEquals(actualMsg, data[i][4]);

			tfc.logout();
			driver.navigate().to("http://zero.webappsecurity.com/login.html");
		}
	}
}
