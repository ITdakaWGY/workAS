package com.as.occupationaldseases.utils;

import com.alibaba.fastjson.JSON;
import jxl.read.biff.BiffException;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ExcelUtils {


	/**
	 *
	 * @param in  字节输入流
	 * @param type Excel的后缀
	 * @param tResponse  实体类 对象
	 * @param <T>
	 * @return    当前类的对象集合
	 */
	private static <T> List<T> doImport(InputStream in,String type,T tResponse){
		List<Object[]> objects = Excel(in, type);
		List<T> list = new ArrayList<>();
		for (int i1 = 0; i1 < objects.size(); i1++) {
			Map<String,Object> map = new HashMap<>();
			for (int i2 = 0; i2 < objects.get(i1).length; i2++) {
				Field[] fields = tResponse.getClass().getDeclaredFields();
				map.put(fields[i2].getName(),objects.get(i1)[i2]);
			}
			T t = (T) JSON.parseObject(JSON.toJSONString(map), tResponse.getClass());
			list.add(t);
		}
		return list;
	}
	
	
	/**
	 * 
	 * @param in    
	 * @param type  Excel的后缀
	 * @return
	 * @throws IOException
	 * @throws BiffException
	 */
	public static List<Object[]> Excel(InputStream in,String type){
		List<Object[]> list = new ArrayList<>();
		switch (type) {
		case "xlsx":
			try {
				list = doxlsx(in);
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case "xls":
			try {
				list = doxls(in);
			} catch (BiffException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
		return list;
		
	}
	/**
	 * 解析.xlsx文件
	 * @param in
	 * @throws IOException
	 */
	@SuppressWarnings({ "resource" })
	public static List<Object[]> doxlsx(InputStream in) throws IOException{
		List<Object[]> list = new ArrayList<>();
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(in);
		XSSFSheet sheetAt = xssfWorkbook.getSheetAt(0);//获取excel表
		for (int i = 1; i < sheetAt.getLastRowNum()+1; i++) {//不包含第一行
			Object value = "";
			XSSFRow xssfRow = sheetAt.getRow(i);//获取行
			Object[] beanArr = new Object[xssfRow.getLastCellNum()+1];//声明一个长度等于列数的数组
			for (int j = 0; j < xssfRow.getLastCellNum(); j++) {//遍历一行中的列
				XSSFCell cell = xssfRow.getCell(j);//获取元素
				if(cell==null){
					beanArr[j] = null;
					continue;
				}
				switch (xssfRow.getCell(j).getCellType()) {
			      case XSSFCell.CELL_TYPE_NUMERIC: // 数字
			          //如果为时间格式的内容
			          if (HSSFDateUtil.isCellDateFormatted(cell)) {      
			             //注：format格式 yyyy-MM-dd hh:mm:ss 中小时为12小时制，若要24小时制，则把小h变为H即可，yyyy-MM-dd HH:mm:ss
			             SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
			             value=sdf.format(HSSFDateUtil.getJavaDate(cell.
			             getNumericCellValue())).toString();                                 
			               break;
			           }else 
			           value = new DecimalFormat("0").format(cell.getNumericCellValue());
			          break;
			      case XSSFCell.CELL_TYPE_STRING: // 字符串
			          value = cell.getStringCellValue();
			          break;
			      case XSSFCell.CELL_TYPE_BOOLEAN: // Boolean
			          value = cell.getBooleanCellValue() + "";
			          break;
			      case XSSFCell.CELL_TYPE_FORMULA: // 公式
			          value = cell.getCellFormula() + "";
			          break;
			      case XSSFCell.CELL_TYPE_BLANK: // 空值
			          value = "";
			          break;
			      case XSSFCell.CELL_TYPE_ERROR: // 故障
			          value = "非法字符";
			          break;
			      default:
			          value = "未知类型";
			          break;
			 }
				beanArr[j] = value;
			}
			//把一行的信息存入集合
			list.add(beanArr);
			//清空数值
			beanArr = null;
		}
		return list;
	 
	}
	
	/**
	 * 解析.xls文件
	 * @throws BiffException
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	public static List<Object[]> doxls(InputStream in) throws BiffException, IOException{
		List<Object[]> list = new ArrayList<>();
		 HSSFWorkbook hssfWorkbook = new HSSFWorkbook(in);//获得excel文件对象
		HSSFSheet s = hssfWorkbook.getSheetAt(0);//获取文件的指定工作表
		for(int i=1;i<s.getLastRowNum()+1;i++)   //第一行不要
		{
			
			HSSFRow hssfRow = s.getRow(i);//获取第一行数据
			Object value = "";
			Object[] beanArr = new Object[hssfRow.getLastCellNum()+1];
			for (int j = 0; j < hssfRow.getLastCellNum(); j++) {
				HSSFCell cell = hssfRow.getCell(j);
				if(cell==null){
					beanArr[j] = null;
					continue;
				}
				switch (hssfRow.getCell(j).getCellType()) {
			      case HSSFCell.CELL_TYPE_NUMERIC: // 数字
			          //如果为时间格式的内容
			          if (HSSFDateUtil.isCellDateFormatted(cell)) {      
			             //注：format格式 yyyy-MM-dd hh:mm:ss 中小时为12小时制，若要24小时制，则把小h变为H即可，yyyy-MM-dd HH:mm:ss
			             SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
			             value=sdf.format(HSSFDateUtil.getJavaDate(cell.
			             getNumericCellValue())).toString();                                 
			               break;
			           } else {
			               value = new DecimalFormat("0").format(cell.getNumericCellValue());
			           }
			          break;
			      case HSSFCell.CELL_TYPE_STRING: // 字符串
			          value = cell.getStringCellValue();
			          break;
			      case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
			          value = cell.getBooleanCellValue() + "";
			          break;
			      case HSSFCell.CELL_TYPE_FORMULA: // 公式
			          value = cell.getCellFormula() + "";
			          break;
			      case HSSFCell.CELL_TYPE_BLANK: // 空值
			          value = "";
			          break;
			      case HSSFCell.CELL_TYPE_ERROR: // 故障
			          value = "非法字符";
			          break;
			      default:
			          value = "未知类型";
			          break;
			 }
				beanArr[j] = value;
			}
			 list.add(beanArr);	
			 beanArr = null;
			  
		}
		return list;
	}

}
