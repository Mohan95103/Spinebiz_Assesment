package org.objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.Utilities;

public class SignOffPage extends Utilities{

	public SignOffPage() {
	PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="LoginLink")
	private WebElement signOffButton;

	public WebElement getSignOffButton() {
		return signOffButton;
	}
	
	
	

}
