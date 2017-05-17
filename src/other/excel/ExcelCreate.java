package other.excel;

/**
 * Created by dtdyq on 2017/3/7.
 * ����Excel�ļ�
 */
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import jxl.Workbook;
import jxl.write.*;

public class ExcelCreate {
    public void createExcel(OutputStream os) throws Exception{
        //����������
        WritableWorkbook workbook = Workbook.createWorkbook(os);
        //�����µ�һҳ
        WritableSheet sheet = workbook.createSheet("First Sheet",0);
        //����Ҫ��ʾ������,����һ����Ԫ�񣬵�һ������Ϊ�����꣬�ڶ�������Ϊ�����꣬����������Ϊ����
        Label xuexiao = new Label(0,0,"ѧУ");
        sheet.addCell(xuexiao);
        Label zhuanye = new Label(1,0,"רҵ");
        sheet.addCell(zhuanye);
        Label jingzhengli = new Label(2,0,"רҵ������");
        sheet.addCell(jingzhengli);

        Label qinghua = new Label(0,1,"�廪��ѧ");
        sheet.addCell(qinghua);
        Label jisuanji = new Label(1,1,"�����רҵ");
        sheet.addCell(jisuanji);
        Label gao = new Label(2,1,"��");
        sheet.addCell(gao);

        Label beida = new Label(0,2,"������ѧ");
        sheet.addCell(beida);
        Label falv = new Label(1,2,"����רҵ");
        sheet.addCell(falv);
        Label zhong = new Label(2,2,"��");
        sheet.addCell(zhong);

        Label ligong = new Label(0,3,"��������ѧ");
        sheet.addCell(ligong);
        Label hangkong = new Label(1,3,"����רҵ");
        sheet.addCell(hangkong);
        Label di = new Label(2,3,"��");
        sheet.addCell(di);

        SimpleDateFormat sp=new SimpleDateFormat("YYYY:MM");
        Date date = sp.parse("2014:10");
        DateTime labelNF = new jxl.write.DateTime(2,4,date);

        sheet.addCell(labelNF);
        //�Ѵ���������д�뵽������У����ر������
        workbook.write();
        workbook.close();
        os.close();
    }
    public static void main(String[] args)throws Exception{
        OutputStream os=new FileOutputStream("file/other/TestExcel.xls");
        new ExcelCreate().createExcel(os);
        System.out.println(Double.parseDouble("3.245456456"));
    }

}
