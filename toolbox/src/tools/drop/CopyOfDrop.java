package tools.drop;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.poi.hssf.record.MergeCellsRecord;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFShapeTypes;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * get
 * @author chuan
 *
 */
public class CopyOfDrop {
	static String[] PARAM_KEY = null;
	static int EACH_ROW_COUNT = 3;
	static int POSITION_X = 0;
	static int POSITION_Y = 0;
	static int TEMPLATE_WIDTH = 0;
	static int TEMPLATE_HEIGHT = 0;
	public static void main(String[] args) throws Exception{
		  InputStream is = new FileInputStream("test.xls");
		  HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		  HSSFSheet hssfSourceSheet = null;
		  HSSFSheet hssfDataSheet = null;
		  HSSFSheet hssfTargetSheet = null;
		  int numberOfSheet = hssfWorkbook.getNumberOfSheets() ;
		  if (numberOfSheet  < 2 ){
			  System.out.println("缺少数据或者模板");
		  }else if (numberOfSheet  <3){
			  hssfTargetSheet = hssfWorkbook.createSheet("result");
		  }else if(numberOfSheet >= 3){
			  hssfTargetSheet = hssfWorkbook.getSheetAt(2);
		  }
		  hssfSourceSheet = hssfWorkbook.getSheetAt(0);
		  hssfDataSheet = hssfWorkbook.getSheetAt(1);
		//打印设置
		  setPrintSetting(hssfTargetSheet);
		  HSSFRow hssfSourceRow = null;
		  HSSFRow hssfTargetRow = null;
		  HSSFRow hssfDataTitleRow = hssfDataSheet.getRow(0);
		  HSSFRow hssfDataRow = null;
		  HSSFCell hssfSourceCell = null;
		  HSSFCell hssfTargetCell = null;
		  HSSFCell hssfDataCell = null;
		  short rowHeight = 0;
		  int cellWidth = 0;
		  //getLastCellNum，取的是共几列，getLastRowNum ，取的是最后一行的序号（不是excel的序号，而是数组的角标）
		  PARAM_KEY = new String[hssfDataTitleRow.getLastCellNum() - hssfDataTitleRow.getFirstCellNum()];
		  getParamkey(hssfDataSheet.getRow(0));
		  //循环数据条数，第一行为参数的key
		  for(int dataIndex = 1;dataIndex <= hssfDataSheet.getLastRowNum();dataIndex ++){
			  POSITION_X = (dataIndex-1)%EACH_ROW_COUNT;
			  POSITION_Y = (dataIndex-1)/EACH_ROW_COUNT;
			  System.out.println("x="+POSITION_X+";y="+POSITION_Y);
			  //合并单元格复制
			  copyMergedRegions(hssfTargetSheet,hssfSourceSheet);
			  
			  hssfDataRow = hssfDataSheet.getRow(dataIndex);
			  
			  //计算行号
			  TEMPLATE_HEIGHT = hssfSourceSheet.getLastRowNum() - hssfSourceSheet.getFirstRowNum();
			  System.out.println("TEMPLATE_HEIGHT="+TEMPLATE_HEIGHT);
			  
			  //循环模板的行
			  for(int rowIndex = hssfSourceSheet.getFirstRowNum() ; rowIndex <= hssfSourceSheet.getLastRowNum() ; rowIndex++){
				  hssfSourceRow = hssfSourceSheet.getRow(rowIndex);
				  if(hssfSourceRow == null ){
					  continue;
				  }
				  if(hssfTargetSheet.getRow(rowIndex+POSITION_Y*(TEMPLATE_HEIGHT+1)) == null){
					  hssfTargetRow = hssfTargetSheet.createRow(rowIndex+POSITION_Y*(TEMPLATE_HEIGHT+1));
				  }else{
					  hssfTargetRow = hssfTargetSheet.getRow(rowIndex+POSITION_Y*(TEMPLATE_HEIGHT+1));
				  }
				  int numOfCell = hssfSourceRow.getLastCellNum() - hssfSourceRow.getFirstCellNum();
				  TEMPLATE_WIDTH = numOfCell > TEMPLATE_WIDTH ? numOfCell : TEMPLATE_WIDTH;
				  System.out.println("TEMPLATE_WIDTH="+TEMPLATE_WIDTH);
				//循环模板行中的每一列
				  for(int colIndex = hssfSourceRow.getFirstCellNum(); colIndex < hssfSourceRow.getLastCellNum();colIndex++){
					  hssfSourceCell = hssfSourceRow.getCell(colIndex);
					  hssfTargetCell = hssfTargetRow.createCell(colIndex+POSITION_X*TEMPLATE_WIDTH);
					  //第一行要设置列宽
					  if(POSITION_Y == 0){
						  cellWidth = hssfSourceSheet.getColumnWidth(colIndex);
						  hssfTargetSheet.setColumnWidth(colIndex+POSITION_X*TEMPLATE_WIDTH, cellWidth);
					  }
					  
					  //设置单元格格式
					  hssfTargetCell.setCellStyle(hssfSourceCell.getCellStyle());
					 
					  //设置值
					  setTargetCell(hssfTargetCell,hssfSourceCell,hssfDataRow);
					 
				  }
			  }
			  //增加
			  
		  }
		  FileOutputStream  out = new FileOutputStream("test.xls");
		  hssfWorkbook.write(out);
		  hssfWorkbook.close();
          out.close();
		
	}

	private static void setPrintSetting(HSSFSheet hssfTargetSheet) {
		HSSFPrintSetup printSet = hssfTargetSheet.getPrintSetup();
		printSet.setLandscape(true); //打印方向，true:横向，false:纵向
		printSet.setPaperSize(HSSFPrintSetup.A4_PAPERSIZE); //纸张
	  hssfTargetSheet.setMargin(HSSFSheet.BottomMargin, 0);// 页边距（下） 
	  hssfTargetSheet.setMargin(HSSFSheet.LeftMargin, 0);// 页边距（左） 
	  hssfTargetSheet.setMargin(HSSFSheet.RightMargin, 0);// 页边距（右） 
	  hssfTargetSheet.setMargin(HSSFSheet.TopMargin, 0);// 页边距（上） 
	  hssfTargetSheet.setMargin(HSSFSheet.HeaderMargin, 0);// 页眉边距 
	  hssfTargetSheet.setMargin(HSSFSheet.FooterMargin, 0);// 页脚边距 
	}

	private static void copyMergedRegions(HSSFSheet hssfTargetSheet,
			HSSFSheet hssfSourceSheet) {
		  CellRangeAddress oldRegion = null;
		  CellRangeAddress newRegion = null ;
		  for(int i = 0;i < hssfSourceSheet.getNumMergedRegions() ; i++){
			  oldRegion = hssfSourceSheet.getMergedRegion(i);
			  newRegion = oldRegion.copy();
			  newRegion.setFirstColumn(oldRegion.getFirstColumn()+POSITION_X*TEMPLATE_WIDTH);
			  newRegion.setFirstRow(oldRegion.getFirstRow()+POSITION_Y*(TEMPLATE_HEIGHT+1));
			  newRegion.setLastColumn(oldRegion.getLastColumn()+POSITION_X*TEMPLATE_WIDTH);
			  newRegion.setLastRow(oldRegion.getLastRow()+POSITION_Y*(TEMPLATE_HEIGHT+1));
			  hssfTargetSheet.addMergedRegion(newRegion);
		  }		
	}

	private static void setTargetCell(HSSFCell hssfTargetCell,
			HSSFCell hssfSourceCell,HSSFRow hssfDataRow) {
		 if (hssfSourceCell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
			  hssfTargetCell.setCellValue(hssfSourceCell.getNumericCellValue());
		  }else if(hssfSourceCell.getCellType() == HSSFCell.CELL_TYPE_STRING){
			  String cellValueStr = hssfSourceCell.getStringCellValue();
			  cellValueStr = replaceParam(cellValueStr,hssfDataRow);
			  hssfTargetCell.setCellValue(cellValueStr);
		  }		
		
	}

	private static String replaceParam(String cellValueStr, HSSFRow hssfDataRow) {
		for(int paramKeyIndex = 0;paramKeyIndex < PARAM_KEY.length;paramKeyIndex++){
			if (cellValueStr.indexOf(PARAM_KEY[paramKeyIndex]) >= 0){
				cellValueStr = cellValueStr.replace(PARAM_KEY[paramKeyIndex],hssfDataRow.getCell(paramKeyIndex).getStringCellValue());
			}
		}
		return cellValueStr;
	}

	private static void  getParamkey(HSSFRow paramKeyRow) {
		String valueStr = "";
		//如果是空也会在PARAM_KEY有一个元素  在取key的值的时候可以直接用角标，
		for(int colIndex = 0;colIndex < paramKeyRow.getLastCellNum();colIndex++){
			if(paramKeyRow.getCell(colIndex) != null){
				valueStr = paramKeyRow.getCell(colIndex).getStringCellValue();
			}
			PARAM_KEY[colIndex] = "#"+valueStr+"#";
		}
	}
}
