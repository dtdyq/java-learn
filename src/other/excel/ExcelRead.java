package other.excel;

import java.io.*;
import java.io.InputStream;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * Created by dtdyq on 2017/3/7.
 *  读取指定的Excel文件，并将其中特定的行和列读出生成新的Excel文件
 */
public class ExcelRead {
    @SuppressWarnings("unused")
	public void readExcel(){
        jxl.Workbook readwb = null;
        Sheet readsheet=null;
        try {
            //构建Workbook对象, 只读Workbook对象
            //直接从本地文件创建Workbook
            InputStream instream = new FileInputStream("file/other/shampoo.xls");
            readwb = Workbook.getWorkbook(instream);

            //Sheet的下标是从0开始
            //获取第一张Sheet表
            readsheet = readwb.getSheet(0);

            //获取Sheet表中所包含的总列数
            int rsColumns = readsheet.getColumns();

            //获取Sheet表中所包含的总行数
            int rsRows = readsheet.getRows();
            /*
            //获取指定单元格的对象引用
            for (int i = 0; i < rsRows; i++) {
                for (int j = 0; j < rsColumns; j++) {

                    Cell cell = readsheet.getCell(j, i);
                    System.out.print(cell.getContents() + " ");
                }
                System.out.println();
            }
            */
        }catch(Exception e){
            e.printStackTrace();
        }
        //将上面的文件中时间和产品名称两列组成新的Excel文档：
        try {
            //创建工作薄
            WritableWorkbook workbook = Workbook.createWorkbook(
                    new FileOutputStream("file/other/Tempshampoo.xls"));
            //创建新的一页
            WritableSheet sheet = workbook.createSheet("First Sheet", 0);
            //创建要显示的内容,创建一个单元格，第一个参数为列坐标，第二个参数为行坐标，第三个参数为内容
            Label time = new Label(0, 0, "学校");
            sheet.addCell(time);
            Label name = new Label(1, 0, "专业");
            sheet.addCell(name);

            int rows=readsheet.getRows();
            for(int i=0;i<rows;i++){
                String zero=readsheet.getCell(0,i).getContents();
                String four=readsheet.getCell(4,i).getContents();
                Label label1=new Label(0,i,zero);
                sheet.addCell(label1);
                Label label2=new Label(1,i,four);
                sheet.addCell(label2);
            }
            workbook.write();
            workbook.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ExcelRead().readExcel();
        System.out.println("write success");
    }

}
