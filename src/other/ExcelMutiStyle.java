package other;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Boolean;
import jxl.write.DateFormats;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;


/**
 * Created by dtdyq on 2017/3/7.
 *  ���ɸ��ӵ�Excel�ļ�
 */
public class ExcelMutiStyle {
    public void createExcel(OutputStream os) throws WriteException,IOException {
        //����������
        WritableWorkbook workbook = Workbook.createWorkbook(os);
        //�����µ�һҳ
        WritableSheet sheet = workbook.createSheet("First Sheet", 0);
        //�����ͷ
        sheet.mergeCells(0, 0, 4, 0);//��Ӻϲ���Ԫ�񣬵�һ����������ʼ�У��ڶ�����������ʼ�У���������������ֹ�У����ĸ���������ֹ��
        WritableFont bold = new WritableFont(WritableFont.ARIAL,10,WritableFont.BOLD);//������������ͺ�����ʾ,����ΪArial,�ֺŴ�СΪ10,���ú�����ʾ
        WritableCellFormat titleFormate = new WritableCellFormat(bold);//����һ����Ԫ����ʽ���ƶ���
        titleFormate.setAlignment(jxl.format.Alignment.CENTRE);//��Ԫ���е�����ˮƽ�������
        titleFormate.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//��Ԫ������ݴ�ֱ�������
        Label title = new Label(0,0,"JExcelApi֧������������ϸ˵��",titleFormate);
        sheet.setRowView(0, 600, false);//���õ�һ�еĸ߶�
        sheet.addCell(title);

        //����Ҫ��ʾ�ľ�������
        WritableFont color = new WritableFont(WritableFont.ARIAL);//ѡ������
        color.setColour(Colour.GOLD);//����������ɫΪ���ɫ
        WritableCellFormat colorFormat = new WritableCellFormat(color);
        Label formate = new Label(0,1,"���ݸ�ʽ",colorFormat);
        sheet.addCell(formate);
        Label floats = new Label(1,1,"������");
        sheet.addCell(floats);
        Label integers = new Label(2,1,"����");
        sheet.addCell(integers);
        Label booleans = new Label(3,1,"������");
        sheet.addCell(booleans);
        Label dates = new Label(4,1,"���ڸ�ʽ");
        sheet.addCell(dates);

        Label example = new Label(0,2,"����ʾ��",colorFormat);
        sheet.addCell(example);
        //��������
        //�����»���
        WritableFont underline= new WritableFont(WritableFont.ARIAL,WritableFont.DEFAULT_POINT_SIZE,WritableFont.NO_BOLD,false,UnderlineStyle.SINGLE);
        WritableCellFormat greyBackground = new WritableCellFormat(underline);
        greyBackground.setBackground(Colour.GRAY_25);//���ñ�����ɫΪ��ɫ
        Number number = new Number(1,2,3.1415926535,greyBackground);
        sheet.addCell(number);
        //��������
        WritableFont boldNumber = new WritableFont(WritableFont.ARIAL,10,WritableFont.BOLD);//����
        WritableCellFormat boldNumberFormate = new WritableCellFormat(boldNumber);
        Number ints = new Number(2,2,15042699,boldNumberFormate);
        sheet.addCell(ints);
        //����������
        Boolean bools = new Boolean(3,2,true);
        sheet.addCell(bools);
        //����������
        //���ú�����»���
        WritableFont boldDate = new WritableFont(WritableFont.ARIAL,WritableFont.DEFAULT_POINT_SIZE,WritableFont.BOLD,false,UnderlineStyle.SINGLE);
        WritableCellFormat boldDateFormate = new WritableCellFormat(boldDate,DateFormats.FORMAT1);
        Calendar c = Calendar.getInstance();
        Date date = c.getTime();
        DateTime dt = new DateTime(4,2,date,boldDateFormate);
        sheet.addCell(dt);
        //�Ѵ���������д�뵽������У����ر������
        workbook.write();
        workbook.close();
        os.close();
    }
    public static void main(String[] args) throws Exception{
        new ExcelMutiStyle().createExcel(
                new FileOutputStream("file/other/MultiLayoutExcel.xls")
        );
    }
}
