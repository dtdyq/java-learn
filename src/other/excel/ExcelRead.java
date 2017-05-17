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
 *  ��ȡָ����Excel�ļ������������ض����к��ж��������µ�Excel�ļ�
 */
public class ExcelRead {
    @SuppressWarnings("unused")
	public void readExcel(){
        jxl.Workbook readwb = null;
        Sheet readsheet=null;
        try {
            //����Workbook����, ֻ��Workbook����
            //ֱ�Ӵӱ����ļ�����Workbook
            InputStream instream = new FileInputStream("file/other/shampoo.xls");
            readwb = Workbook.getWorkbook(instream);

            //Sheet���±��Ǵ�0��ʼ
            //��ȡ��һ��Sheet��
            readsheet = readwb.getSheet(0);

            //��ȡSheet������������������
            int rsColumns = readsheet.getColumns();

            //��ȡSheet������������������
            int rsRows = readsheet.getRows();
            /*
            //��ȡָ����Ԫ��Ķ�������
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
        //��������ļ���ʱ��Ͳ�Ʒ������������µ�Excel�ĵ���
        try {
            //����������
            WritableWorkbook workbook = Workbook.createWorkbook(
                    new FileOutputStream("file/other/Tempshampoo.xls"));
            //�����µ�һҳ
            WritableSheet sheet = workbook.createSheet("First Sheet", 0);
            //����Ҫ��ʾ������,����һ����Ԫ�񣬵�һ������Ϊ�����꣬�ڶ�������Ϊ�����꣬����������Ϊ����
            Label time = new Label(0, 0, "ѧУ");
            sheet.addCell(time);
            Label name = new Label(1, 0, "רҵ");
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
