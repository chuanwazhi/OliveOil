package tools.drop;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * get
 * 
 * @author chuan
 * 
 */
public class Drop {
	private String[] paramKey = null;
	private int eachRowCount = 3;
	private int POSITION_X = 0;
	private int POSITION_Y = 0;
	private int TEMPLATE_WIDTH = 0;
	private int TEMPLATE_HEIGHT = 0;
	public Drop(){
		String rowCountStr = PropertiesUtil.getProperties("eachRowCount");
		if(!"".equals(rowCountStr)){
			eachRowCount = Integer.valueOf(rowCountStr);
		}
	}
	public void copyTemplateSheetToObjectSheet(HSSFWorkbook hssfWorkbook) {
		HSSFSheet templateSheet = hssfWorkbook.getSheet("template");
		HSSFSheet dataSheet = hssfWorkbook.getSheet("data");
		HSSFSheet resultSheet = null;
		if(templateSheet == null || dataSheet == null){
			System.out.println("请在workbook中新建名为data和template的sheet");
			return;
		}
		if(hssfWorkbook.getSheetIndex("result") != -1){
			hssfWorkbook.removeSheetAt(hssfWorkbook.getSheetIndex("result"));
		}
		resultSheet = hssfWorkbook.createSheet("result");
		hssfWorkbook.setSheetOrder("result", 0);
		// 打印设置
		setPrintSetting(resultSheet);

		// 数据Sheet的第一行为参数的key
		getParamkey(dataSheet.getRow(0));
		// 组装模板和数据
		assembleTemplateAndData(resultSheet, dataSheet, templateSheet);
	}

	public void writeWorkBook(String filePath, HSSFWorkbook hssfWorkbook) {
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(filePath);
			hssfWorkbook.write(out);
		} catch (FileNotFoundException e) {
			System.out.println("文件路径不存在filePath="+filePath);
		} catch (IOException e) {
			System.out.println("文件写入异常filePath="+filePath);
		} finally {
			try {
				hssfWorkbook.close();
				out.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	private void assembleTemplateAndData(HSSFSheet hssfTargetSheet,	HSSFSheet hssfDataSheet, HSSFSheet hssfSourceSheet) {
		HSSFRow hssfSourceRow = null;
		HSSFRow hssfTargetRow = null;
		// 第一行为变量KEY，所以要从第二行开始
		for (int dataIndex = 1; dataIndex <= hssfDataSheet.getLastRowNum(); dataIndex++) {
			// 设置偏移量，第几行的第几个实体对象
			setOffSet(dataIndex);

			// 合并单元格复制
			copyMergedRegions(hssfTargetSheet, hssfSourceSheet);
			// 计算行号
			TEMPLATE_HEIGHT = hssfSourceSheet.getLastRowNum() - hssfSourceSheet.getFirstRowNum();

			// 循环模板的行
			for (int rowIndex = hssfSourceSheet.getFirstRowNum(); rowIndex <= hssfSourceSheet.getLastRowNum(); rowIndex++) {
				// hssfSourceRow,hssfTargetRow初始化
				hssfSourceRow = hssfSourceSheet.getRow(rowIndex);
				if (hssfSourceRow == null) {
					continue;
				}
				if (hssfTargetSheet.getRow(rowIndex + getYOffSet()) == null) {
					hssfTargetRow = hssfTargetSheet.createRow(rowIndex + getYOffSet());
					hssfTargetRow.setHeight(hssfSourceRow.getHeight());
				} else {
					hssfTargetRow = hssfTargetSheet.getRow(rowIndex + getYOffSet());
				}
				// 计算最多有多少列
				int numOfCell = hssfSourceRow.getLastCellNum()
						- hssfSourceRow.getFirstCellNum();
				TEMPLATE_WIDTH = numOfCell > TEMPLATE_WIDTH ? numOfCell
						: TEMPLATE_WIDTH;
				System.out.println("TEMPLATE_WIDTH=" + TEMPLATE_WIDTH);
				// 循环模板行中的每一列
				for (int colIndex = hssfSourceRow.getFirstCellNum(); colIndex < hssfSourceRow
						.getLastCellNum(); colIndex++) {
					// 第一行要设置列宽
					if (POSITION_Y == 0 && rowIndex == 0) {
						hssfTargetSheet.setColumnWidth(colIndex + getXOffSet(), hssfSourceSheet.getColumnWidth(colIndex));
					}
					// 设置单元格属性
					setTargetCell(hssfTargetRow.createCell(colIndex + getXOffSet()), hssfSourceRow.getCell(colIndex), hssfDataSheet.getRow(dataIndex));
				}
			}
			//特殊处理，设置一行中不同实体之间的间隔列
			hssfTargetSheet.setColumnWidth(TEMPLATE_WIDTH + getXOffSet(), hssfSourceSheet.getColumnWidth(TEMPLATE_WIDTH));
		}

	}

	private int getXOffSet() {
		return POSITION_X * (TEMPLATE_WIDTH + 1);
	}

	private int getYOffSet() {
		return POSITION_Y * (TEMPLATE_HEIGHT + 1);
	}

	private void setTargetCell(HSSFCell hssfTargetCell,
			HSSFCell hssfSourceCell, HSSFRow hssfDataRow) {
		// 设置单元格格式
		hssfTargetCell.setCellStyle(hssfSourceCell.getCellStyle());
		if (hssfSourceCell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
			hssfTargetCell.setCellValue(hssfSourceCell.getNumericCellValue());
		} else if (hssfSourceCell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
			hssfTargetCell.setCellValue(replaceParam(
					hssfSourceCell.getStringCellValue(), hssfDataRow));
		}
	}

	private String replaceParam(String cellValueStr, HSSFRow hssfDataRow) {
		for (int paramKeyIndex = 0; paramKeyIndex < paramKey.length; paramKeyIndex++) {
			if (cellValueStr.indexOf(paramKey[paramKeyIndex]) >= 0) {
				cellValueStr = cellValueStr
						.replace(paramKey[paramKeyIndex],
								hssfDataRow.getCell(paramKeyIndex)
										.getStringCellValue());
			}
		}
		return cellValueStr;
	}

	private void copyMergedRegions(HSSFSheet hssfTargetSheet,
			HSSFSheet hssfSourceSheet) {
		CellRangeAddress oldRegion = null;
		CellRangeAddress newRegion = null;
		for (int i = 0; i < hssfSourceSheet.getNumMergedRegions(); i++) {
			oldRegion = hssfSourceSheet.getMergedRegion(i);
			newRegion = oldRegion.copy();
			newRegion.setFirstColumn(oldRegion.getFirstColumn() + getXOffSet());
			newRegion.setFirstRow(oldRegion.getFirstRow() + getYOffSet());
			newRegion.setLastColumn(oldRegion.getLastColumn() + getXOffSet());
			newRegion.setLastRow(oldRegion.getLastRow() + getYOffSet());
			hssfTargetSheet.addMergedRegion(newRegion);
		}
	}

	private void setOffSet(int dataIndex) {
		POSITION_X = (dataIndex - 1) % eachRowCount;
		POSITION_Y = (dataIndex - 1) / eachRowCount;
		System.out.println("x=" + POSITION_X + ";y=" + POSITION_Y);
	}

	private void setPrintSetting(HSSFSheet hssfTargetSheet) {
		HSSFPrintSetup printSet = hssfTargetSheet.getPrintSetup();
		printSet.setLandscape(true); // 打印方向，true:横向，false:纵向
		printSet.setPaperSize(HSSFPrintSetup.A4_PAPERSIZE); // 纸张
		hssfTargetSheet.setMargin(HSSFSheet.BottomMargin, 0);// 页边距（下）
		hssfTargetSheet.setMargin(HSSFSheet.LeftMargin, 0);// 页边距（左）
		hssfTargetSheet.setMargin(HSSFSheet.RightMargin, 0);// 页边距（右）
		hssfTargetSheet.setMargin(HSSFSheet.TopMargin, 0);// 页边距（上）
		hssfTargetSheet.setMargin(HSSFSheet.HeaderMargin, 0);// 页眉边距
		hssfTargetSheet.setMargin(HSSFSheet.FooterMargin, 0);// 页脚边距
	}

	private void getParamkey(HSSFRow hssfDataTitleRow) {
		if (hssfDataTitleRow == null) {
			System.out.println("请把变量的KEY放在datasheet中的第一行");
			System.exit(0);
		}
		paramKey = new String[hssfDataTitleRow.getLastCellNum()
				- hssfDataTitleRow.getFirstCellNum()];
		String valueStr = "";
		HSSFCell hssfDataCell = null;
		// 如果是空也会在PARAM_KEY有一个元素 在取key的值的时候可以直接用角标，
		for (int colIndex = 0; colIndex < hssfDataTitleRow.getLastCellNum(); colIndex++) {
			hssfDataCell = hssfDataTitleRow.getCell(colIndex);
			if (hssfDataCell != null) {
				if (hssfDataCell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
					valueStr = hssfDataCell.getStringCellValue();
				} else {
					System.out.println("变量名请使用字符串");
					return;
				}
			}
			paramKey[colIndex] = ConstValue.KEY_FLAG + valueStr + ConstValue.KEY_FLAG;
		}
	}

}
