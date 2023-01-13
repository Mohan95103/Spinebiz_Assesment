package org.objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.utility.Utilities;

public class LoginPage extends Utilities {

	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "LoginLink")
	private WebElement signIn;

	@FindBy(id = "uid")
	private WebElement username;

	@FindBy(id = "passw")
	private WebElement password;

	@FindBy(name = "btnSubmit")
	private WebElement loginButton;

	@FindBy(xpath = "//h1[contains(text(),'Hello Admin')]")
	private WebElement profileName;

	public WebElement getSignIn() {
		return signIn;
	}

	public WebElement getUsername() {
		return username;
	}

	public WebElement getPassword() {
		return password;
	}

	public WebElement getLoginButton() {
		return loginButton;
	}

	public WebElement getProfileName() {
		return profileName;
	}

	

	

	public boolean verifyLoginAction() {
		String text = getProfileName().getText();
		boolean res = false;
		if (text.contains("Hello Admin User")) {
			res = true;
		}
		return res;

	}

	
}
