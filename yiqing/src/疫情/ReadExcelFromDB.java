package 疫情;
import java.io.FileOutputStream;
import java.sql.*;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ReadExcelFromDB {

    public final static String outputFile="D://数据.xlsx";

    public final static String url="jdbc:sqlserver://localhost:1433;DatabaseName=Student";

    public final static String user="sa";

    public final static String password="chenke010919chan";
   

    public static void main(String[] args) {
        try {
        	 Connection conn;  
        	 Statement sql;  
        	 ResultSet rs;  
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url, user, password);
            sql = conn.createStatement();
            ResultSet resultSet = sql.executeQuery("select * from message;");
			XSSFWorkbook workbook=new XSSFWorkbook();
            XSSFSheet sheet=workbook.createSheet("数据");
            XSSFRow row = sheet.createRow((short)0);
            XSSFCell cell=null;
            cell=row.createCell((short)0);
            cell.setCellValue("学号");
            cell=row.createCell((short)1);
            cell.setCellValue("姓名");
            cell=row.createCell((short)2);
            cell.setCellValue("最近是否接触确诊者");
            cell=row.createCell((short)3);
            cell.setCellValue("是否确诊");
            cell=row.createCell((short)4);
            cell.setCellValue("是否在武汉");
            cell=row.createCell((short)5);
            cell.setCellValue("日期");
            int i=1;
            while(resultSet.next())
            {
                row=sheet.createRow(i);
                cell=row.createCell(0);
                cell.setCellValue(resultSet.getString("学号"));
                cell=row.createCell(1);
                cell.setCellValue(resultSet.getString("姓名"));
                cell=row.createCell(2);
                cell.setCellValue(resultSet.getString("最近是否接触确诊者"));
                cell=row.createCell(3);
                cell.setCellValue(resultSet.getString("是否确诊"));
                cell=row.createCell(4);
                cell.setCellValue(resultSet.getString("是否在武汉"));
                cell=row.createCell(5);
                cell.setCellValue(resultSet.getString("日期"));
                i++;
             }
            FileOutputStream FOut = new FileOutputStream(outputFile);
            workbook.write(FOut);
            FOut.flush();
            FOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}