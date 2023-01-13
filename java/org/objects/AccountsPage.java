package org.objects;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.utility.Utilities;

public class AccountsPage extends Utilities {

	public AccountsPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "MenuHyperLink1")
	private WebElement accountSummary;

	@FindBy(id = "listAccounts")
	private WebElement listAccounts;

	@FindBy(id = "btnGetAccount")
	private WebElement goButton;

	@FindBy(xpath = "//td[contains(text(),'Available balance')]//following-sibling::td")
	private WebElement startBalance;

	@FindBy(xpath = "//td[contains(text(),'Ending balance')]//following-sibling::td")
	private WebElement closeBalance;

	public WebElement getAccountSummary() {
		return accountSummary;
	}

	public WebElement getListAccount() {
		return listAccounts;
	}

	public WebElement getGoButton() {
		return goButton;
	}

	public WebElement getStartBalance() {
		return startBalance;
	}

	public WebElement getCloseBalance() {
		return closeBalance;
	}

	

	public boolean verifyAVailableBalance() {
		String avial = getText(getStartBalance());
		String ending = getText(getCloseBalance());
		if (avial.equals(ending)) {
			return true;
		}
		return false;
	}

}
