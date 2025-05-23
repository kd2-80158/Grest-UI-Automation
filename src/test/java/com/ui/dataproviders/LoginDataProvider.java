package com.ui.dataproviders;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.google.gson.Gson;
import com.ui.pojos.TestData;
import com.ui.pojos.User;
import com.utility.CSVReaderUtility;
import com.utility.ExcelReaderUtility;

public class LoginDataProvider {
	
	@DataProvider(name="LoginTestDataProvider", parallel=true)
	public Iterator<Object[]> loginDataProvider() throws FileNotFoundException
	{
		Gson gson = new Gson();
		File testDataFile = new File(System.getProperty("user.dir")+"//testData///logindata.json");
	    FileReader fileReader = new FileReader(testDataFile);
	    TestData data=gson.fromJson(fileReader, TestData.class);
	    
	    List<Object[]> dataToReturn = new ArrayList<Object[]>();
	    for(User user:data.getData())
	    {
	    	dataToReturn.add(new Object[] {user});
	    }
	    return dataToReturn.iterator();
	}
	
	@DataProvider(name="LoginTestCSVDataProvider",parallel=true)
	public Iterator<User> loginCSVDataProvider()
	{
		return CSVReaderUtility.readCSVFile("logindata.csv");
	}
	
	@DataProvider(name="LoginTestExcelDataProvider",parallel=true)
	public Iterator<User> loginExcelDataProvider()
	{
		return ExcelReaderUtility.readExcelFile("LoginData.xlsx");
	}

}
