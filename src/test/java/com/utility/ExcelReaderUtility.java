package com.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.ui.pojos.User;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReaderUtility {

    public static Iterator<User> readExcelFile(String fileName) {
        List<User> userList = new ArrayList<>();
        File file = new File(System.getProperty("user.dir") + "//testdata//" + fileName);

        try (FileInputStream fis = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet("LoginTestData");
            Iterator<Row> rowIterator = sheet.iterator();

            if (rowIterator.hasNext()) rowIterator.next(); // skip header

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (row == null) continue;

                Cell usernameCell = row.getCell(0);
                Cell passwordCell = row.getCell(1);

                if (usernameCell == null || usernameCell.toString().trim().isEmpty()
                        || passwordCell == null || passwordCell.toString().trim().isEmpty()) {
                    continue;
                }

                String username = usernameCell.toString().trim();
                String password = passwordCell.toString().trim();
                userList.add(new User(username, password));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return userList.iterator();
    }
}
