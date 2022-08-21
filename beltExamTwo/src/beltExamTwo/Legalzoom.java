package beltExamTwo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Legalzoom {

	public WebDriver driver;
	// Elements locators
	public String personalSelector = "a#nav-heading-personal";
	public String livingTrustXPath = "//a[@title='Living trust'][contains(text(),'Living trust')]";
	public String startButtonXPath = "//*[@id='hero915-cta-button']";
	public String getStartedAbsoluteXPath = "/html/body/div[1]/div/main/div/div/article/div/div[3]/div[1]/div[2]/div/div[5]/div/div/a";
	public String saveXPath = "//input[@onclick='pushEpOutboundSaleInfo();']";
	public String meOnlySelector = "input#chkctlgrantor_selfonly_CB.checkbox";

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("Before Suite");
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("After Suite");
	}

	@BeforeTest
	public void beforeTest() {
		System.out.println("Before Test");
	}

	@AfterTest
	public void afterTest() {
		System.out.println("After Test");
	}

	@BeforeClass
	public void beforeClass() {
		System.out.println("Before Class");
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\white\\Desktop\\QA\\Auto\\chromedriver14\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.navigate().to("https://www.legalzoom.com/");
	}

	@AfterClass
	public void afterClass() {
		System.out.println("After Class");
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("Before Method");
	}

	@Test(priority = 0)
	public void test1() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		//// `Home` Page ////
		WebElement personalElement = driver.findElement(By.cssSelector(personalSelector));
		WebElement livingTrustlElement = driver.findElement(By.xpath(livingTrustXPath));
		personalElement.click();
		Thread.sleep(2000);
		livingTrustlElement.click();
		Thread.sleep(2000);

		//// `living trust overview` Page ////
		WebElement startButtonElement = driver.findElement(By.xpath(startButtonXPath));
		startButtonElement.click();
		Thread.sleep(2000);

		//// `living trust pricing` Page ////
		WebElement getStartedElement = driver.findElement(By.xpath(getStartedAbsoluteXPath));
		// Scroll the page till the element is found
		js.executeScript("arguments[0].scrollIntoView();", getStartedElement);
		getStartedElement.click();
		Thread.sleep(2000);

		//// `What to expect in this questionnaire` Page ////
		// Scroll the web page till end.
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		WebElement saveButtonElement = driver.findElement(By.xpath(saveXPath));
		saveButtonElement.click();
		Thread.sleep(2000);

		//// `Who is making this trust` Page ////
		WebElement onlyMeInputElement = driver.findElement(By.cssSelector(meOnlySelector));
		WebElement secondSaveButtonElement = driver.findElement(By.xpath(saveXPath));
		onlyMeInputElement.click();
		Thread.sleep(2000);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		secondSaveButtonElement.click();
		Thread.sleep(2000);

		//// `Tell us about yourself` Page ////
		// Handling Multiple WebElements
		String[] personalData = { "Sami", "Ahmed", "AlMalki", "Sam" };
		int indeX = 0;
		List<WebElement> inputElement = driver.findElements(By.cssSelector("div.textboxStandard>div>input"));
		for (WebElement eachInput : inputElement) {
			eachInput.sendKeys(personalData[indeX]);
			indeX++;
			Thread.sleep(1000);
			js.executeScript("window.scrollBy(0,50)");
			if (indeX == 4) {
				String script = "document.querySelector('#phone_number').setAttribute('value', 8008557654);";
				js.executeScript(script);
				Thread.sleep(1000);
				break;
			}
		}
		js.executeScript("window.scrollBy(0,100)");

		// Selecting Items in a Multiple SELECT elements
		List<WebElement> selectElements = driver.findElements(By.tagName("select"));
		for (WebElement eachSelect : selectElements) {
			Select select = new Select(eachSelect);
			select.selectByVisibleText("No");
			js.executeScript("window.scrollBy(0,80)");
			Thread.sleep(2000);

		}
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

		WebElement thirdSaveButtonElement = driver.findElement(By.xpath(saveXPath));
		thirdSaveButtonElement.click();

		// `Where do you live?` Page
		String[] streetData = { "Street", "City", "County", "11111" };
		int indEX = 0;
		List<WebElement> inputEle = driver.findElements(By.cssSelector("input.textbox"));
		for (WebElement eachInput : inputEle) {
			eachInput.sendKeys(streetData[indEX]);
			indEX++;
			js.executeScript("window.scrollBy(0,80)");
			Thread.sleep(1000);
		}
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

		Select drpState = new Select(driver.findElement(By.name("grantor_state|290264")));
		drpState.selectByIndex(1);
		Thread.sleep(1000);

		WebElement fourthSaveButtonElement = driver.findElement(By.xpath(saveXPath));
		fourthSaveButtonElement.click();

		//// `Overview of how property is placed in a trust.` Page ////
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(2000);

		WebElement fifthSaveButtonElement = driver.findElement(By.xpath(saveXPath));
		fifthSaveButtonElement.click();

		//// `What types of property will be put into trust?` Page ////
		List<WebElement> checkboxEle = driver.findElements(By.cssSelector("label>input"));
		for (WebElement checkbox : checkboxEle) {
			if (checkbox.isSelected()) {
				js.executeScript("window.scrollBy(0,80)");
				break;
			} else {
				checkbox.click();
				js.executeScript("window.scrollBy(0,80)");
				Thread.sleep(1000);
				break;
			}
		}
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

		WebElement sixthSaveButtonElement = driver.findElement(By.xpath(saveXPath));
		sixthSaveButtonElement.click();

		//// `Real estate details.` Page ////
		String[] realEstateData = { "Street", "City", "11111" };
		int num = 0;
		List<WebElement> realEstateDataEle = driver.findElements(By.cssSelector("input.textbox"));
		for (WebElement eachInput : realEstateDataEle) {
			eachInput.sendKeys(realEstateData[num]);
			num++;
			js.executeScript("window.scrollBy(0,80)");
			Thread.sleep(1000);
		}

		Select stateSelect = new Select(driver.findElement(By.id("property_realestate_info_state_1")));
		Select typeSelect = new Select(driver.findElement(By.id("property_realestate_info_type_1")));
		Select deedSelect = new Select(driver.findElement(By.id("property_realestate_info_deed_1")));

		stateSelect.selectByIndex(1);
		js.executeScript("window.scrollBy(0,80)");
		Thread.sleep(1000);

		typeSelect.selectByIndex(1);
		js.executeScript("window.scrollBy(0,80)");
		Thread.sleep(1000);

		deedSelect.selectByIndex(1);
		js.executeScript("window.scrollBy(0,80)");
		Thread.sleep(1000);

		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

		WebElement seventhSaveButtonElement = driver.findElement(By.xpath(saveXPath));
		seventhSaveButtonElement.click();

		//// `Real Estate Deed Preparation Agreement` Page ////
		WebElement checkboxAccept = driver.findElement(By.id("chkctldeed_tos_CB"));

		if (checkboxAccept.isSelected()) {
			js.executeScript("window.scrollBy(0,80)");
		} else {
			checkboxAccept.click();
			js.executeScript("window.scrollBy(0,80)");
			Thread.sleep(1000);
		}

		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

		WebElement eightSaveButtonElement = driver.findElement(By.xpath(saveXPath));
		eightSaveButtonElement.click();

		//// `Overview of how gifts are handled in a trust.` Page ////
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

		WebElement nineSaveButtonElement = driver.findElement(By.xpath(saveXPath));
		nineSaveButtonElement.click();

		//// `Who do you want to receive the trust property?` Page ////
		String[] generalGiftData = { "100", "wardah", "ahmed" };
		int inum = 0;
		List<WebElement> generalGiftDataEle = driver.findElements(By.cssSelector("input.textbox"));
		for (WebElement eachInput : generalGiftDataEle) {
			eachInput.sendKeys(generalGiftData[inum]);
			inum++;
			js.executeScript("window.scrollBy(0,80)");
			if (inum == 3) {
				break;
			}
			Thread.sleep(1000);
		}

		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(1000);

		WebElement tenthSaveButtonElement = driver.findElement(By.xpath(saveXPath));
		tenthSaveButtonElement.click();

		//// `Do you want to give any specific and charitable gifts?` Page ////
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

		WebElement elevenSaveButtonElement = driver.findElement(By.xpath(saveXPath));
		elevenSaveButtonElement.click();

		//// `Do you want any gifts held in a subtrust?` Page ////
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

		WebElement twelveSaveButtonElement = driver.findElement(By.xpath(saveXPath));
		twelveSaveButtonElement.click();

		//// `Do you want a pour-over will?` Page ////
		Select pourOverSelect = new Select(driver.findElement(By.id("pow_MC")));
		pourOverSelect.selectByVisibleText("No");

		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

		WebElement thirteenSaveButtonElement = driver.findElement(By.xpath(saveXPath));
		thirteenSaveButtonElement.click();

		//// `Overview of representatives named in trust documents.` Page ////
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

		WebElement forteenSaveButtonElement = driver.findElement(By.xpath(saveXPath));
		forteenSaveButtonElement.click();

		//// `Do you want to appoint a co-trustee?` Page ////
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

		WebElement fifteenSaveButtonElement = driver.findElement(By.xpath(saveXPath));
		fifteenSaveButtonElement.click();

		//// `Who are the successor trustees?` Page ////
		String[] trusteesData = { "Reem", "Ahmed" };
		int numb = 0;
		List<WebElement> trusteesDataEle = driver.findElements(By.cssSelector("input.textbox"));
		for (WebElement eachInput : trusteesDataEle) {
			eachInput.sendKeys(trusteesData[numb]);
			numb++;
			js.executeScript("window.scrollBy(0,80)");
			if (numb == 2) {
				break;
			}
			Thread.sleep(1000);
		}

		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

		WebElement sixteenSaveButtonElement = driver.findElement(By.xpath(saveXPath));
		sixteenSaveButtonElement.click();

		//// `Do you want to include additional instructions for trustees?` Page ////
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

		WebElement seventeenthSaveButtonElement = driver.findElement(By.xpath(saveXPath));
		seventeenthSaveButtonElement.click();

		//// `Would you like to name the trust?` Page ////
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

		WebElement eighteenSaveButtonElement = driver.findElement(By.xpath(saveXPath));
		eighteenSaveButtonElement.click();

		//// `Other estate planning options.` Page ////
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		driver.findElement(By.xpath(saveXPath)).click();

		//// `We Recommend Our Comprehensive Package` Page ////
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		driver.findElement(By.xpath("//input[@onclick='guestPass_disable_modal_handler();']")).click();

		//// `Choose your package` Page ////
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		driver.findElement(By.xpath("//input[@onclick='guestPass_disable_modal_handler();']")).click();

		//// `Secure Checkout` Page ////
		driver.findElement(By.xpath("//img[@src='/assets/images/customize-package/buttonContinue.jpg']")).click();

		//// `Complete your order` Page ////
		String[] contactAndPaymentInfo = { "wardah", "AlMalki", "whitelight43@hotmail.com", "", "Address Line 1",
				"Address Line 2", "11111", "city", "", "Wardah", "AlMalki", "1234567890543", "11111", "222" };
		int indexNumb = 0;
		List<WebElement> contactAndPaymentInfoEle = driver
				.findElements(By.cssSelector("input[id*='ctl00_cphMainContent_txt']"));
		for (WebElement eachInput : contactAndPaymentInfoEle) {
			eachInput.sendKeys(contactAndPaymentInfo[indexNumb]);
			indexNumb++;
			js.executeScript("window.scrollBy(0,40)");
			if (indexNumb == 3) {
				String jScript = "document.querySelector(\"#ctl00_cphMainContent_txt_ContactInfo_PhoneNumber\").value = 8008557654;";
				js.executeScript(jScript);
			}
			if (indexNumb == 8) {
				String jScript = "document.querySelector(\"#ctl00_cphMainContent_txt_mobile_number\").value = 8008557654;";
				js.executeScript(jScript);
				Thread.sleep(1000);
				Select stateEle = new Select(driver.findElement(By.id("ctl00_cphMainContent_ddl_ContactInfo_State")));
				stateEle.selectByIndex(1);
			}
			if (indexNumb == 12) {
				Select yearEle = new Select(driver.findElement(By.id("ctl00_cphMainContent_ddl_CC_ExpirationYear")));
				yearEle.selectByIndex(3);
				Thread.sleep(1000);
				Select monthEle = new Select(driver.findElement(By.id("ctl00_cphMainContent_ddl_CC_ExpirationMonth")));
				monthEle.selectByIndex(1);
			}
			if (indexNumb == 14) {
				driver.findElement(
						By.xpath("//label[contains(text(), 'I understand that the Legal Assist Plan automatically')]"))
						.click();
				break;
			}
			Thread.sleep(1000);
		}
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(1000);

		driver.findElement(By.xpath("//*[@id=\"ctl00_cphMainContent_imgbtn_CheckOut\"]")).click();
		Thread.sleep(1000);

		WebElement spnEle = driver.findElement(By.id("ctl00_cphMainContent_cv_txt_CC_Number"));
		js.executeScript("arguments[0].scrollIntoView();", spnEle);

		String result = spnEle.getText();
		Assert.assertEquals(result, "Invalid Credit Card Number");
		System.out.println(result);

		Thread.sleep(4000);

	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("After Method");
		driver.quit();
	}
}
