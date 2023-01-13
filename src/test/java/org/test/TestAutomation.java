package org.test;

import java.util.Date;
import java.util.List;

import org.objects.AccountsPage;
import org.objects.ContactPage;
import org.objects.LoginPage;
import org.objects.SignOffPage;
import org.objects.TransactionPage;
import org.objects.TransferPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utility.Utilities;

public class TestAutomation extends Utilities {
	@BeforeClass
	private void setup() {
		openBrowser("Chrome");
		openUrl(getPropertyValue("webAppurl"));
		implicitWait(20);

	}

	@BeforeMethod
	private void beforeMethod() {
		System.out.println("Test Method Starts at.." + new Date());

	}

	@AfterMethod
	private void afterMethod(ITestResult r) {
		System.out.println("Test Method Ends at.." + new Date());
		screenShot(r.getName());

	}

	@AfterClass
	private void tearDown() {
		closeBrowser();
	}

	@Test(priority = 1)
	private void verifyLogin() {
		LoginPage login = new LoginPage();
		click(login.getSignIn());

		// Validate the Invalid login
		sendkeys(login.getUsername(), getPropertyValue("inValidUser"));
		sendkeys(login.getPassword(), getPropertyValue("inValidPass"));
		click(login.getLoginButton());

		// Validate the Valid login
		sendkeys(login.getUsername(), getPropertyValue("validUser"));
		sendkeys(login.getPassword(), getPropertyValue("validPass"));
		click(login.getLoginButton());

		Assert.assertTrue(login.getProfileName().getText().contains("Hello Admin User"),
				"Verify the Login Valid Credentials");
		System.out.println("Login Verification Passed");

	}

	@Test(priority = 2)
	private void verifyAvailableBalance() {
		AccountsPage account = new AccountsPage();
		click(account.getAccountSummary());
		selectByValue(account.getListAccount(), getPropertyValue("acc2"));
		click(account.getGoButton());
		System.out.println(account.getStartBalance().getText());
		System.out.println(account.getCloseBalance().getText());
		Assert.assertTrue(getText(account.getStartBalance()).equals(getText(account.getCloseBalance())),
				"Check the Balance Available");

	}

	@Test(priority = 3)
	private void verifyFund() {
		TransferPage transfer = new TransferPage();
		click(transfer.getTransferFunds());
		selectByValue(transfer.getFromAccount(), getPropertyValue("acc1"));
		selectByValue(transfer.getToAccount(), getPropertyValue("acc2"));
		sendkeys(transfer.getTxtAmount(), getPropertyValue("amt_value"));
		String value = getAttribute(transfer.getTxtAmount());
		click(transfer.getBtnTransfer());
		Assert.assertTrue(getText(transfer.getTransferMessage()).contains(getAttribute(transfer.getTxtAmount())),
				"Transfer Amount verication");
		System.out.println(getText(transfer.getTransferMessage()));

	}

	@Test(priority = 4)
	private void verifyTranscationFund() {
		TransactionPage trans = new TransactionPage();
		click(trans.getViewTransaction());
		List<WebElement> trows = trans.getTransactionTable().findElements(By.tagName("tr"));
		List<WebElement> tdata1 = trows.get(1).findElements(By.tagName("td"));
		String dep = tdata1.get(4).getText();
		List<WebElement> tdata2 = trows.get(2).findElements(By.tagName("td"));
		String draw = tdata2.get(4).getText().replace("-", "");
		Assert.assertTrue(dep.equals(draw), "Verify Transactions");
		System.out.println("The transaction History Verified");

	}

	@Test(priority = 5)
	private void verifyContactUs() {
		ContactPage con = new ContactPage();
		click(con.getContactUslink());
		click(con.getOnlineForm());
		sendkeys(con.getTxtemail(), getPropertyValue("emailId"));
		sendkeys(con.getTxtSubject(), getPropertyValue("content"));
		sendkeys(con.getTxtComments(), getPropertyValue("message"));
		click(con.getBtnSubmit());
		System.out.println("Feedback Submitted");
		Assert.assertTrue(getText(con.getMessage()).equals("Thank You"), "Verify the feedback");

	}

	@Test(priority = 6)
	private void VerifySignOff() {
		SignOffPage sign = new SignOffPage();
		click(sign.getSignOffButton());
		Assert.assertTrue(getText(sign.getSignOffButton()).equals("Sign In"), "Sign Off Page Verification");
		System.out.println("Sign off Successfully");

	}
}
