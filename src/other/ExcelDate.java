package other;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.*;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
/**
 * Created by dtdyq on 2017/3/7.
 */
public class ExcelDate {
    public static void modify(){
        try{
            BufferedInputStream bis=new BufferedInputStream(
                    new FileInputStream("file/other/shampooDelete.xls")
            );
            BufferedOutputStream bos=new BufferedOutputStream(
                    new FileOutputStream("file/other/newshapooDelete.xls")
            );

            Workbook srcExcel = Workbook.getWorkbook(bis);
            WritableWorkbook destExcel=Workbook.createWorkbook(bos);

            Sheet readsheet = srcExcel.getSheet(0);
            WritableSheet writesheet = destExcel.createSheet("frist sheet", 0);

            int rows=readsheet.getRows();
            for(int i=1;i<rows;i++){
                String time=readsheet.getCell(0,i).getContents();
                String s="20".concat(time.substring(0,2)).concat("/").concat(time.substring(2)).concat("/00");
                writesheet.addCell(new Label(0,i,s));
                for(int j=1;j<8;j++){
                    String str=readsheet.getCell(j,i).getContents();
                    writesheet.addCell(new Label(j,i,str));
                }
            }
            destExcel.write();
            destExcel.close();
            srcExcel.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args)throws Exception{
        modify();
    }
}
