package com.mai.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mai.vo.ActRankVO;
import com.mai.vo.ExpData;
import com.mai.vo.MixRankVO;
import com.mai.vo.SocRankVO;
import com.mai.vo.UserTemp;

/**
 * 操作Excel表格的功能类
 */
public class ExcelReader {
	
	public static  List<UserTemp> getListFromExcel(String file){
		 List<UserTemp> list = new ArrayList<UserTemp>();
		 
		 if(file.endsWith("xls")){
			 //读取03的xlsx
			 readByXls(file, list);
		 }else if(file.endsWith("xlsx")){
			 //读取07以后的xlsx
			 readByXlsx(file, list);
		 }
		 
		
		return list;
		
	}
	
	/**
	 * 读取03以后的excel
	 * @param file
	 * @param list
	 */
	private static void readByXls(String file, List<UserTemp> list) {
		HSSFWorkbook xwb = null;
		  try {
			  
			   InputStream is = new FileInputStream(file);
			   xwb = new HSSFWorkbook(is);
		  } catch (IOException e) {
			  e.printStackTrace();
		  }
		  // 读取第一章表格内容
		  HSSFSheet sheet = xwb.getSheetAt(0);
		  // 定义 row、cell
		  HSSFRow row;
		  String cell = null;
		  // 循环输出表格中的内容
		  for (int i = sheet.getFirstRowNum(); i < sheet.getPhysicalNumberOfRows(); i++) {
			  if(i==0){
				  row = sheet.getRow(i);
				  for (int j = row.getFirstCellNum(); j < row.getPhysicalNumberOfCells(); j++) {
					  cell = row.getCell(j).toString();
					  System.out.print(cell + "\t");
				  }
			  }else{
				   row = sheet.getRow(i);
//				   System.out.println("iii----"+i);
				   UserTemp uv = new UserTemp();
				  	DecimalFormat df = new DecimalFormat("#");
				   for (int j = row.getFirstCellNum(); j < row.getPhysicalNumberOfCells(); j++) {
				    // 通过 row.getCell(j).toString() 获取单元格内容，
				    HSSFCell _cell = row.getCell(j);
				    if(_cell!=null){
				    	Integer type = _cell.getCellType();
					    if(type!=null){
						   switch (_cell.getCellType()) {
							   case HSSFCell.CELL_TYPE_NUMERIC: // 数字
								   cell = df.format(_cell.getNumericCellValue());
								   break;
							   case HSSFCell.CELL_TYPE_STRING: // 字符串
								   cell = _cell.getStringCellValue();
								   break;
							   default:
								   break;
						   }
					    }
				    }
				    
					if(j==0){
				    	cell = getStringValue(row.getCell(j));
				    	System.out.println(cell);
				    	uv.setPhoneNum(cell);
				    }else if(j==1){
				    	cell = row.getCell(j).getStringCellValue();
				    	uv.setName(cell);
				    }else if(j==2){
				    	cell = row.getCell(j).getStringCellValue();				    	
				    	uv.setRoleName(cell);
				    }else if(j==3){
				    	cell = row.getCell(j).getStringCellValue();
				    	uv.setSchoolName(cell);
				    }else if(j==4){
				    	cell = row.getCell(j).getStringCellValue();
				    	uv.setSocietyName(cell);
				    }
				    System.out.print(cell + "\t");
				   }
				   list.add(uv);
			  }
		 
		   System.out.println("");
		  }
	}
	

	/**
	 * 读取07以后的excel
	 * @param file
	 * @param list
	 */
	private static void readByXlsx(String file, List<UserTemp> list) {
		XSSFWorkbook xwb = null;
		  try {
		   xwb = new XSSFWorkbook(file);
		  } catch (IOException e) {
		   e.printStackTrace();
		  }
		  // 读取第一章表格内容
		  XSSFSheet sheet = xwb.getSheetAt(0);
		  // 定义 row、cell
		  XSSFRow row;
		  String cell = null;
		  // 循环输出表格中的内容
		  for (int i = sheet.getFirstRowNum(); i < sheet.getPhysicalNumberOfRows(); i++) {
			  if(i==0){
				  row = sheet.getRow(i);
				  for (int j = row.getFirstCellNum(); j < row.getPhysicalNumberOfCells(); j++) {
					  cell = row.getCell(j).toString();
					  System.out.print(cell + "\t");
				  }
			  }else{
				   row = sheet.getRow(i);
//				   System.out.println("iii----"+i);
				   UserTemp uv = new UserTemp();
				  	DecimalFormat df = new DecimalFormat("#");
				   for (int j = row.getFirstCellNum(); j < row.getPhysicalNumberOfCells(); j++) {
				    // 通过 row.getCell(j).toString() 获取单元格内容，
				    XSSFCell _cell = row.getCell(j);
				    if(_cell!=null){
				    	Integer type = _cell.getCellType();
					    if(type!=null){
						   switch (_cell.getCellType()) {
							   case XSSFCell.CELL_TYPE_NUMERIC: // 数字
								   cell = df.format(_cell.getNumericCellValue());
								   break;
							   case XSSFCell.CELL_TYPE_STRING: // 字符串
								   cell = _cell.getStringCellValue();
								   break;
							   default:
								   break;
						   }
					    }
				    }
				    
					if(j==0){
				    	cell = getStringValue(row.getCell(j));
				    	System.out.println(cell);
				    	uv.setPhoneNum(cell);
				    }else if(j==1){
				    	cell = row.getCell(j).getStringCellValue();
				    	uv.setName(cell);
				    }else if(j==2){
				    	cell = row.getCell(j).getStringCellValue();				    	
				    	uv.setRoleName(cell);
				    }else if(j==3){
				    	cell = row.getCell(j).getStringCellValue();
				    	uv.setSchoolName(cell);
				    }else if(j==4){
				    	cell = row.getCell(j).getStringCellValue();
				    	uv.setSocietyName(cell);
				    }
				    System.out.print(cell + "\t");
				   }
				   list.add(uv);
			  }
		 
		   System.out.println("");
		  }
	}
	
	/**
	 * 获得单元格的字符串值
	 * @return String
	 */
	public static String getStringValue(XSSFCell xssfCell){
		String result = null;
		
		if(null != xssfCell){
			// 设置编码
//			cell.setEncoding(ENCODING);
			// 判断类型
			int type = xssfCell.getCellType();
			
			switch (type) {
			// 类型为空
			case HSSFCell.CELL_TYPE_BLANK:
				break;
			// 类型为布尔
			case HSSFCell.CELL_TYPE_BOOLEAN:
				result = Boolean.toString(xssfCell.getBooleanCellValue());
				break;
			// 公式活函数类型
			case HSSFCell.CELL_TYPE_FORMULA:
				result = xssfCell.getCellFormula();
				break;
			// 数字类型
			case HSSFCell.CELL_TYPE_NUMERIC:
				double d = xssfCell.getNumericCellValue();
				NumberFormat nf = NumberFormat.getNumberInstance();
				nf.setGroupingUsed(false);
				nf.setMaximumIntegerDigits(309);
				result = nf.format(d);
				break;
			// string类型
			case HSSFCell.CELL_TYPE_STRING:
				result = xssfCell.getStringCellValue();
				break;
			// 错误类型
			case HSSFCell.CELL_TYPE_ERROR:
				result = "error:"+xssfCell.getErrorCellValue();
				break;
			}
		}
		return result;
	}
	
	/**
	 * 获得单元格的字符串值
	 * @return String
	 */
	public static String getStringValue(HSSFCell xssfCell){
		String result = null;
		
		if(null != xssfCell){
			// 设置编码
//			cell.setEncoding(ENCODING);
			// 判断类型
			int type = xssfCell.getCellType();
			
			switch (type) {
			// 类型为空
			case HSSFCell.CELL_TYPE_BLANK:
				break;
			// 类型为布尔
			case HSSFCell.CELL_TYPE_BOOLEAN:
				result = Boolean.toString(xssfCell.getBooleanCellValue());
				break;
			// 公式活函数类型
			case HSSFCell.CELL_TYPE_FORMULA:
				result = xssfCell.getCellFormula();
				break;
			// 数字类型
			case HSSFCell.CELL_TYPE_NUMERIC:
				double d = xssfCell.getNumericCellValue();
				NumberFormat nf = NumberFormat.getNumberInstance();
				nf.setGroupingUsed(false);
				nf.setMaximumIntegerDigits(309);
				result = nf.format(d);
				break;
			// string类型
			case HSSFCell.CELL_TYPE_STRING:
				result = xssfCell.getStringCellValue();
				break;
			// 错误类型
			case HSSFCell.CELL_TYPE_ERROR:
				result = "error:"+xssfCell.getErrorCellValue();
				break;
			}
		}
		return result;
	}
	
	
    public static void main(String[] args) { 
    	String strPath = "D://test.xlsx";// 构造 XSSFWorkbook 对象，strPath 传入文件路径
    	getListFromExcel(strPath);
    }
    
    public static HSSFWorkbook export(List<ExpData> list) {    
    	String[] excelHeader = { "手机号", "姓名", "身份","所属学校","所属社团","注册时间","上次操作时间","用户状态"};    
        HSSFWorkbook wb = new HSSFWorkbook();    
        HSSFSheet sheet = wb.createSheet("导出信息");   
        //行宽6000
        for (int i = 0; i < excelHeader.length; i++) {
			
        	sheet.setColumnWidth(i,2000);
		}
        HSSFRow row = sheet.createRow((int) 0);    
        HSSFCellStyle style = wb.createCellStyle();    
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);    
    
        for (int i = 0; i < excelHeader.length; i++) {    
            HSSFCell cell = row.createCell(i);    
            cell.setCellValue(excelHeader[i]);    
            cell.setCellStyle(style);    
            sheet.autoSizeColumn(i);    
        }    
    
        for (int i = 0; i < list.size(); i++) {    
            row = sheet.createRow(i + 1);    
            ExpData u = list.get(i);  
            row.createCell(0).setCellValue(u.getPhone());    
            row.createCell(1).setCellValue(u.getName());    
            row.createCell(2).setCellValue(u.getRoleName());    
            row.createCell(3).setCellValue(u.getSchoolName());    
            row.createCell(4).setCellValue(u.getSocietyName());    
            row.createCell(5).setCellValue(u.getCreateTime());   
            row.createCell(6).setCellValue(u.getOperateTime()); 
            row.createCell(7).setCellValue(u.getStatus());    
        }    
        return wb;    
    }

	public static HSSFWorkbook expRank(List<MixRankVO> list1, List<SocRankVO> list2, List<ActRankVO> list3) {
		// TODO Auto-generated method stub
		
		String[] excelHeader1 = { "学校", "参战社团数", "社团成员人数","社团活动数","活动参与人数","点赞数","关注数"};    
		String[] excelHeader2 = { "社团名称","学校","社团成员人数","社团活动数	","活动参与人数","关注数","点赞数"};    
		String[] excelHeader3 = { "活动名称","社团名称","学校","活动参与人数","关注数"};    
        HSSFWorkbook wb = new HSSFWorkbook();    
        HSSFSheet sheet1 = wb.createSheet("社联排名");
        HSSFSheet sheet2 = wb.createSheet("社团排名");   
        HSSFSheet sheet3 = wb.createSheet("活动排名");   
        
        //第一个-----------------------------------------------
        HSSFRow row = sheet1.createRow((int) 0);    
        HSSFCellStyle style = wb.createCellStyle();    
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);    
    
        for (int i = 0; i < excelHeader1.length; i++) {    
            HSSFCell cell = row.createCell(i);    
            cell.setCellValue(excelHeader1[i]);    
            cell.setCellStyle(style);    
            sheet1.autoSizeColumn(i);    
        }    
    
        for (int i = 0; i < list1.size(); i++) {    
            row = sheet1.createRow(i + 1);    
            MixRankVO u = list1.get(i);  
            row.createCell(0).setCellValue(u.getSchoolName());    
            row.createCell(1).setCellValue(u.getSocNum());    
            row.createCell(2).setCellValue(u.getSmNum());    
            row.createCell(3).setCellValue(u.getActNum());    
            row.createCell(4).setCellValue(u.getActmNum());    
            row.createCell(5).setCellValue(u.getpNum());   
            row.createCell(6).setCellValue(u.getFollowNum()); 
        }    
      //第一个-----------------------------------------------
        
      //第2个-----------------------------------------------
        HSSFRow row2 = sheet2.createRow((int) 0);    
        HSSFCellStyle style2 = wb.createCellStyle();    
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);    
    
        for (int i = 0; i < excelHeader2.length; i++) {    
            HSSFCell cell = row2.createCell(i);    
            cell.setCellValue(excelHeader2[i]);    
            cell.setCellStyle(style2);    
            sheet2.autoSizeColumn(i);    
        }    
    
        for (int i = 0; i < list2.size(); i++) {    
            row2 = sheet2.createRow(i + 1);    
            SocRankVO u = list2.get(i);  
            row2.createCell(0).setCellValue(u.getSocietyName());    
            row2.createCell(1).setCellValue(u.getSchoolName());    
            row2.createCell(2).setCellValue(u.getJoinPersonNum());    
            row2.createCell(3).setCellValue(u.getActNum());    
            row2.createCell(4).setCellValue(u.getActmNum());    
            row2.createCell(5).setCellValue(u.getFollowNum());   
            row2.createCell(6).setCellValue(u.getPraiseNum()); 
        }    
      //第2个-----------------------------------------------
        
      //第3个-----------------------------------------------
        HSSFRow row3 = sheet3.createRow((int) 0);    
        HSSFCellStyle style3 = wb.createCellStyle();    
        style3.setAlignment(HSSFCellStyle.ALIGN_CENTER);    
    
        for (int i = 0; i < excelHeader3.length; i++) {    
            HSSFCell cell = row3.createCell(i);    
            cell.setCellValue(excelHeader3[i]);    
            cell.setCellStyle(style3);    
            sheet3.autoSizeColumn(i);    
        }    
    
        for (int i = 0; i < list3.size(); i++) {    
            row3 = sheet3.createRow(i + 1);    
            ActRankVO u = list3.get(i);  
            row3.createCell(0).setCellValue(u.getActivityTitle());    
            row3.createCell(1).setCellValue(u.getSocietyName());    
            row3.createCell(2).setCellValue(u.getSchoolName());    
            row3.createCell(3).setCellValue(u.getJoinPersonNum());    
            row3.createCell(4).setCellValue(u.getFollowNum());    
        }    
      //第3个-----------------------------------------------
        return wb;    
	}    
}
