package ExcelToJson;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.junit.Assert;


public class ExcelToJson {
	private Workbook workbook;
	private Sheet sheet;
	private InputStream inputStream;
	private String filepath = "F:\\Java Projects 2024\\ExcelToJson_Java\\Test.xlsx";
	public String expectedJson="[[\"ID\",\"Name\",\"Age\"],"+
								"[\"1.0\",\"Vedant\",\"23.0\"],"+
								"[\"2.0\",\"Aryan\",\"17.0\"]]";
	
	public void ExcelToJson() {
		JSONArray jsonArray = new JSONArray();
		try {
			inputStream = new FileInputStream(filepath);
		    workbook = new XSSFWorkbook(inputStream);
		    sheet = workbook.getSheetAt(0);

		    Row headerRow = sheet.getRow(0);
		    List<String> headers = new ArrayList<>();
		    for (Cell cell : headerRow) {
		        headers.add(cell.toString());
		    }
		    jsonArray.put(headers);
		    
		    
		    for(int i=1;i<=sheet.getLastRowNum();i++) {
		    	Row row = sheet.getRow(i);
		    	List<String> rowData = new ArrayList<>();
		    	for(Cell cell : row) {
		    		rowData.add(cell.toString());
		    	}
		    	jsonArray.put(rowData);
		    }
		    
//		    To Check Json string and jsonArray.toString() is equal
		    Assert.assertEquals(expectedJson, jsonArray.toString());
		    
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
