package com.ui.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.pages.Dashboard;
import com.ui.pages.Products;

@Listeners({ com.ui.listeners.TestListener.class })
public class SearchProductTest extends TestBase {

	Dashboard dashboardPage;
	Products product;

	private static final String SEARCH_TERM = "Dell i7";

	@BeforeMethod(description = "Valid user logs into the application")
	public Dashboard setup() {
		dashboardPage = loginPage.doLoginWith("grestadmin", "Test@123");

		return dashboardPage;
	}

	@Test(description = "Verify whether the valid user is able to search for a product and correct products are displayed", groups = {
			"e2e", "smoke", "sanity" })
	public void verifyProductSearchTest() {
		dashboardPage.clickOnProducts();
		System.out.println("successfully navigates to product page..");
		this.product = new Products(dashboardPage.getDriver());
		String text = this.product.searchForProduct("Applexxx");

		boolean actualResult = this.product.checkSearchTermPresentInProductList(SEARCH_TERM);
		int pagination = Integer.parseInt(this.product.getPaginationNo());
////		assertTrue(pagination > 0, "Expected product to be found (pagination > 0), but got: " + pagination);
//
//		if (text.equalsIgnoreCase("Apple")) { // You can adapt this based on input param
//		    assertTrue(pagination > 0, "Expected product to be found (pagination > 0), but got: " + pagination);
//		} else {
//		    assertEquals(pagination, 0, "Expected no products found (pagination = 0), but got: " + pagination);
//		}
	assertTrue(pagination > 0, "Expected product to be found (pagination > 0), but got: " + pagination);

	if (text.equalsIgnoreCase("Apple")) { // You can adapt this based on input param
	    assertTrue(pagination > 0, "Expected product to be found (pagination > 0), but got: " + pagination);
	} else {
	    assertEquals(pagination, 0, "Expected no products found (pagination = 0), but got: " + pagination);
	}

	}

	@Test(description = "Verify whether the valid user is able to search for a product and matched products are displayed", groups = {
			"e2e", "smoke", "sanity" })
	public void verifyProductSearchTestMatch() {
		dashboardPage.clickOnProducts();
		System.out.println("successfully navigates to product page..");
		this.product = new Products(dashboardPage.getDriver());
//		String text = this.product.searchForProduct("Dell i7");

		boolean actualResult = this.product.checkSearchTermPresentInProductList(SEARCH_TERM);
//		int pagination = Integer.parseInt(this.product.getPaginationNo());
////		assertTrue(pagination > 0, "Expected product to be found (pagination > 0), but got: " + pagination);
//
//		if (text.equalsIgnoreCase("Apple")) { // You can adapt this based on input param
//		    assertTrue(pagination > 0, "Expected product to be found (pagination > 0), but got: " + pagination);
//		} else {
//		    assertEquals(pagination, 0, "Expected no products found (pagination = 0), but got: " + pagination);
//		}
		Assert.assertEquals(actualResult, true);

	}
}
