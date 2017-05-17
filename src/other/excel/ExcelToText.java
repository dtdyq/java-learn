package other.excel;

import jxl.Sheet;
import jxl.Workbook;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;

/**
 * Created by dtdyq on 2017/3/10.
 */
public class ExcelToText{
    public static void  main(String[] args) {
        try {
            Workbook book = Workbook.getWorkbook(new FileInputStream("file/other/shampoo.xls"));
            FileWriter fw=new FileWriter("file/other/shampootxt.txt");
            BufferedWriter br=new BufferedWriter(fw);
            Sheet sh=book.getSheet(0);
            int columns=sh.getColumns();
            int rows=sh.getRows();
            StringBuilder sf=new StringBuilder();
            for(int i=0;i<rows;i++){
                for(int j=0;j<columns;j++){
                    sf.append(sh.getCell(j,i).getContents()+"  ");
                }
                br.write(sf.toString());
                br.newLine();
                if(i%100==0){
                    br.flush();
                }
                sf.delete(0,sf.length());
            }
            br.flush();
            book.close();
            br.close();
            fw.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
