package org.selenium.search;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.search.entity.ProductDetails;

//import statements
public class WriteExcelDemo
{
	public static void main(String[] args)
	{
		//WriteExcelDemo.writeToXLS(null);
		String[] brandList = readXLS();
		for(String str: brandList)
		{
			System.out.println(str);
		}
	}
	
	public static String[] readXLS()
	{
		List<String> brandList = new ArrayList<String>();
		try {
		     
		    FileInputStream file = new FileInputStream(new File("C:\\test\\Brands.xlsx"));
		     
		    //Get the workbook instance for XLS file 
		    XSSFWorkbook workbook = new XSSFWorkbook(file);
		 
		    //Get first sheet from the workbook
		    XSSFSheet sheet = workbook.getSheetAt(0);
		     
		    //Iterate through each rows from first sheet
		    Iterator<Row> rowIterator = sheet.iterator();
		    while(rowIterator.hasNext()) {
		        Row row = rowIterator.next();
		         
		        //For each row, iterate through each columns
		        Iterator<Cell> cellIterator = row.cellIterator();
		        while(cellIterator.hasNext()) {
		             
		            Cell cell = cellIterator.next();
		             
		            switch(cell.getCellType()) {
		                case Cell.CELL_TYPE_BOOLEAN:
		                    System.out.print(cell.getBooleanCellValue() + "\t\t");
		                    break;
		                case Cell.CELL_TYPE_NUMERIC:
		                    System.out.print(cell.getNumericCellValue() + "\t\t");
		                    break;
		                case Cell.CELL_TYPE_STRING:
		                    //System.out.print(cell.getStringCellValue() + "\t\t");
		                    brandList.add(cell.getStringCellValue());
		                    break;
		            }
		        }
		        System.out.println("");
		    }
		    file.close();
		     
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		String[] arr = new String[brandList.size()];
		arr = brandList.toArray(arr);
		return  arr;
	}

	public static void writeToXLS(Map<String, List<ProductDetails>> productPricesData)
	{

		//productPricesData = getSampleData();
		// Blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook();

		// Create a blank sheet
		XSSFSheet sheet = workbook.createSheet("Product Prices Data");

		// Iterate over data and write to sheet
		Set<String> keyset = productPricesData.keySet();
		//int cellnum = 0;
		List<ProductDetails> productDetails = null;

		int rownum = 0;
		//create headers
		sheet.createRow(rownum).createCell(1).setCellValue("TITLE");
		sheet.getRow(rownum).createCell(2).setCellValue("MRP");
		sheet.getRow(rownum).createCell(3).setCellValue("PRICE");
		sheet.getRow(rownum).createCell(4).setCellValue("DISCOUNT");
		rownum++;
		for (String key : keyset)
		{
			sheet.createRow(++rownum).createCell(0).setCellValue(key);
			productDetails = productPricesData.get(key);

			for (ProductDetails detail : productDetails)
			{
				//sheet.createRow(0).createCell(0).setCellValue(84);

				sheet.createRow(++rownum).createCell(1).setCellValue(detail.getProductTitle());
				sheet.getRow(rownum).createCell(2).setCellValue(detail.getProductMrp());
				sheet.getRow(rownum).createCell(3).setCellValue(detail.getProductPrice());
				sheet.getRow(rownum).createCell(4).setCellValue(detail.getProductDiscount());

				//++rownum;
			}
			//++cellnum;
		}

		try
		{
			// Write the workbook in file system
			FileOutputStream out = new FileOutputStream(new File("c:/test/productprice/demo"+System.currentTimeMillis()+".xlsx"));
			workbook.write(out);
			out.close();
			System.out.println("productprice.xlsx successfully saved");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}
	
	public static Map<String, List<ProductDetails>> getSampleData()
	{
		Map<String, List<ProductDetails>> productPricesData1 = new HashMap<String, List<ProductDetails>>();
		List<ProductDetails> pds = new ArrayList<ProductDetails>();
		ProductDetails pd1 = new ProductDetails();
		pd1.setProductTitle("Nina Elixir Eau De Parfum 80Ml");
		pd1.setProductMrp("Rs.4950");
		pd1.setProductPrice("Rs.4950");
		pd1.setProductDiscount("50%");
		ProductDetails pd2 = new ProductDetails();
		pd2.setProductTitle("Ricci Ricci Mademoiselle Edp80Ml");
		pd2.setProductMrp("Rs.4950");
		pd2.setProductPrice("Rs.4950");
		pd2.setProductDiscount("50%");
		ProductDetails pd3 = new ProductDetails();
		pd3.setProductTitle("Ricci Ricci Eau De Parfum 80ml");
		pd3.setProductMrp("Rs.4950");
		pd3.setProductPrice("Rs.4950");
		pd3.setProductDiscount("50%");
		List<ProductDetails> pds1 = new ArrayList<ProductDetails>();
		ProductDetails pd4 = new ProductDetails();
		pd4.setProductTitle("Nina Elixir Eau De Parfum 80Ml");
		pd4.setProductMrp("Rs.4950");
		pd4.setProductPrice("Rs.4950");
		pd4.setProductDiscount("50%");
		ProductDetails pd5 = new ProductDetails();
		pd5.setProductTitle("Ricci Ricci Mademoiselle Edp80Ml");
		pd5.setProductMrp("Rs.4950");
		pd5.setProductPrice("Rs.4950");
		pd5.setProductDiscount("50%");
		ProductDetails pd6 = new ProductDetails();
		pd6.setProductTitle("Ricci Ricci Eau De Parfum 80ml");
		pd6.setProductMrp("Rs.4950");
		pd6.setProductPrice("Rs.4950");
		pd6.setProductDiscount("50%");
		
		pds.add(pd1);pds.add(pd2);pds.add(pd3);
		pds1.add(pd4);pds1.add(pd5);pds1.add(pd6);
		productPricesData1.put("NINA RICCI", pds);
		productPricesData1.put("BURBERRY TOUCH", pds);
		
		return productPricesData1;
	}
}
