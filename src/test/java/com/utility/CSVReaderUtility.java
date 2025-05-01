package com.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.ui.pojos.User;

public class CSVReaderUtility {
	
	public static Iterator<User> readCSVFile(String fileName)
	{
		File file = new File(System.getProperty("user.dir") + "/testdata/"+fileName);

		FileReader fileReader = null;
		CSVReader csvReader;
		List<User> userList = new ArrayList<User>();
		User userData;
		String [] line; //it gives the whole line in array of string format
		try {
			fileReader = new FileReader(file);
			csvReader = new CSVReader(fileReader);
			line=csvReader.readNext();
//			System.out.println(Arrays.toString(data));

			while((line=csvReader.readNext())!=null)
			{
				userData = new User(line[0],line[1]);
				userList.add(userData);
			}
		} catch (CsvValidationException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userList.iterator();
	}

}
