package com.ui.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.pages.AddNewCategoryDialog;
import com.ui.pages.CategoryPage;
import com.ui.pages.Dashboard;

@Listeners({com.ui.listeners.TestListener.class})

public class AddNewCategoryTest extends TestBase {
	
	private Dashboard dashboardPage;
	private CategoryPage categoryPage;
	private AddNewCategoryDialog addNewCategoryDialog;
	
	@BeforeMethod(description = "Valid user logs into the application")
	public Dashboard setup() {
		dashboardPage = loginPage.doLoginWith("nikesh.agarwal@agbeindia.com", "Grest@124");

		return dashboardPage;
	}
    
	@Test(description="Verify whether the new category is being created successfully")
	public void addNewCategory()
	{
		//navigate to categories from dashboard
		categoryPage = dashboardPage.goToCategoryPage();
		//opens the add new category dialog
		categoryPage.openAddNewCategoryDialog();
	    // 🔧 Initialize the dialog after it's opened
	    addNewCategoryDialog = new AddNewCategoryDialog(categoryPage.getDriver());
		addNewCategoryDialog.saveCategory("/Users/saurabhchhimwal/Desktop/PHONE images/apple-logo-macbook.jpg");
		
	}
	
}
