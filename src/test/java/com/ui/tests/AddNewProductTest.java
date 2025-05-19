package com.ui.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.pages.CreateNewProductPage;
import com.ui.pages.Dashboard;
import com.ui.pages.Products;

@Listeners({com.ui.listeners.TestListener.class})
public class AddNewProductTest extends TestBase {
	
	private Dashboard dashboardPage;
	Products product;
	private CreateNewProductPage createNewProductPage;
//	private CreateProduct createProduct;
	
	@BeforeMethod(description = "Valid user logs into the application")
	public Dashboard setup() {
		dashboardPage = loginPage.doLoginWith("nikesh.agarwal@agbeindia.com", "Grest@124");

		return dashboardPage;
	}


	@Test(description = "Verify if the product is created successfully", groups = {"e2e", "sanity"})
	public void createNewProduct()
	{
		dashboardPage.clickOnProducts();
		System.out.println("successfully navigates to product page..");
		
		this.product = new Products(dashboardPage.getDriver());
		
		createNewProductPage=this.product.clickOnCreateProductsButton();
		
		createNewProductPage.saveProduct();
	}

}
