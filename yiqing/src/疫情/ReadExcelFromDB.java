package ����;
import java.io.FileOutputStream;
import java.sql.*;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ReadExcelFromDB {

    public final static String outputFile="D://����.xlsx";

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
            XSSFSheet sheet=workbook.createSheet("����");
            XSSFRow row = sheet.createRow((short)0);
            XSSFCell cell=null;
            cell=row.createCell((short)0);
            cell.setCellValue("ѧ��");
            cell=row.createCell((short)1);
            cell.setCellValue("����");
            cell=row.createCell((short)2);
            cell.setCellValue("����Ƿ�Ӵ�ȷ����");
            cell=row.createCell((short)3);
            cell.setCellValue("�Ƿ�ȷ��");
            cell=row.createCell((short)4);
            cell.setCellValue("�Ƿ����人");
            cell=row.createCell((short)5);
            cell.setCellValue("����");
            int i=1;
            while(resultSet.next())
            {
                row=sheet.createRow(i);
                cell=row.createCell(0);
                cell.setCellValue(resultSet.getString("ѧ��"));
                cell=row.createCell(1);
                cell.setCellValue(resultSet.getString("����"));
                cell=row.createCell(2);
                cell.setCellValue(resultSet.getString("����Ƿ�Ӵ�ȷ����"));
                cell=row.createCell(3);
                cell.setCellValue(resultSet.getString("�Ƿ�ȷ��"));
                cell=row.createCell(4);
                cell.setCellValue(resultSet.getString("�Ƿ����人"));
                cell=row.createCell(5);
                cell.setCellValue(resultSet.getString("����"));
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