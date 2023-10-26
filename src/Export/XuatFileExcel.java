package Export;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class XuatFileExcel {
	private XSSFWorkbook workbook;

	public XuatFileExcel() {
	}

	public void pt_XuatFileExcel(JTable table, File file,String titlesheet) {
		try {
			workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet(titlesheet);
			XSSFRow row = null;
			Cell cell=null;
			row = sheet.createRow(1);
			cell = row.createCell(1);
			String s = "Danh Sách Lương Phòng Ban: "+table.getValueAt(0, 2).toString();
			cell.setCellValue(s);
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 4));
			row = sheet.createRow(3);
			cell = row.createCell(0,CellType.STRING);
			cell.setCellValue("STT");
			int soCot=0;
			for (int i = 0; i < table.getColumnCount(); i++) {
				cell = row.createCell(i+1,CellType.STRING);
				cell.setCellValue(table.getColumnName(i));
				soCot++;
			}
			for (int i = 0; i <table.getRowCount(); i++) {
				row = sheet.createRow(i+4);
				
					for (int j = soCot-1; j >=0; j--) {
						cell = row.createCell(j+1,CellType.STRING);
						cell.setCellValue(table.getValueAt(i, j).toString());
						if (j==0) {
							cell = row.createCell(j,CellType.NUMERIC);
							cell.setCellValue(i+1);
						}
					}
			}
			try {
				FileOutputStream fos = new FileOutputStream(file);
				workbook.write(fos);
				fos.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			catch (IOException ex) {
				ex.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Xuất Dữ Liệu Ra File Excle Thành Công");
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
		
}
