package other;
import jxl.*;
import jxl.format.UnderlineStyle;
import jxl.write.*;
import jxl.write.Number;
import jxl.write.Boolean;
import jxl.Cell;
import java.io.*;
/**
 * Created by dtdyq on 2017/3/7.
 */
public class ExcelModify {
    public ExcelModify()
    {
    }

    /***��ȡExcel*/

    public static void readExcel(String filePath)
    {
        try
        {

            InputStream is = new FileInputStream(filePath);
            Workbook rwb = Workbook.getWorkbook(is);
            //���������ַ�����ȡsheet��:���ֺ��±꣨��0��ʼ��
            //Sheet st = rwb.getSheet("original");
            Sheet st = rwb.getSheet(0);

            /**
             //��õ�һ�е�һ�е�Ԫ��ֵ
             Cell c00 = st.getCell(0,0);
             //ͨ�õĻ�ȡcellֵ�ķ�ʽ,�����ַ���
             String strc00 = c00.getContents();

             //���cell��������ֵ�ķ�ʽ
             if(c00.getType() == CellType.LABEL)
             {
             LabelCell labelc00 = (LabelCell)c00;
             strc00 = labelc00.getString();
             }

             //���
             System.out.println(strc00);*/
            //Sheet���±��Ǵ�0��ʼ
            //��ȡ��һ��Sheet��
            Sheet rst = rwb.getSheet(0);

            //��ȡSheet������������������
            int rsColumns = rst.getColumns();

            //��ȡSheet������������������
            int rsRows = rst.getRows();

            //��ȡָ����Ԫ��Ķ�������
            for (int i = 0; i < rsRows; i++)
            {
                for (int j = 0; j < rsColumns; j++)
                {

                    Cell cell = rst.getCell(j, i);
                    System.out.print(cell.getContents() + " ");
                }
                System.out.println();
            }
            //�ر�
            rwb.close();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /**���Excel*/
    public static void writeExcel(OutputStream os)
    {
        try
        {
            /** ֻ��ͨ��API�ṩ�� ��������������Workbook��
             * ������ʹ��WritableWorkbook�Ĺ��캯������Ϊ��WritableWorkbook
             * �Ĺ��캯��Ϊ protected���ͣ�����һ��ֱ�Ӵ�Ŀ���ļ��ж�ȡ
             * WritableWorkbook wwb = Workbook.createWorkbook(new File(targetfile));
             *
             * ���� ��������ʵ����ʾ ��WritableWorkbookֱ��д�뵽�����*/
            WritableWorkbook wwb = Workbook.createWorkbook(os);

            //����Excel������ ָ�����ƺ�λ��
            WritableSheet ws = wwb.createSheet("Test Sheet 1",0);

            /**************�����������������*****************/
            //1.���Label����
            Label label = new Label(0,0,"����");
            ws.addCell(label);
            //��Ӵ�������Formatting����
            WritableFont wf = new WritableFont(WritableFont.TIMES,18,WritableFont.BOLD,true);
            WritableCellFormat wcf = new WritableCellFormat(wf);
            Label labelcf = new Label(1,0,"this is a label test",wcf);
            ws.addCell(labelcf);

            //��Ӵ���������ɫ��Formatting����
            WritableFont wfc = new WritableFont(WritableFont.ARIAL,10,WritableFont.NO_BOLD,false,

                    UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.DARK_YELLOW);

            WritableCellFormat wcfFC = new WritableCellFormat(wfc);
            Label labelCF = new Label(1,0,"Ok",wcfFC);
            ws.addCell(labelCF);



            //2.���Number����
            Number labelN = new Number(0,1,3.1415926);
            ws.addCell(labelN);
            //��Ӵ���formatting��Number����
            NumberFormat nf = new NumberFormat("#.##");
            WritableCellFormat wcfN = new WritableCellFormat(nf);
            Number labelNF = new jxl.write.Number(1,1,3.1415926,wcfN);
            ws.addCell(labelNF);

            //3.���Boolean����
            Boolean labelB = new jxl.write.Boolean(0,2,true);
            ws.addCell(labelB);
            Boolean labelB1 = new jxl.write.Boolean(1,2,false);
            ws.addCell(labelB1);
            //4.���DateTime����
            jxl.write.DateTime labelDT = new jxl.write.DateTime(0,3,new java.util.Date());
            ws.addCell(labelDT);

            //5.��Ӵ���formatting��DateFormat����
            DateFormat df = new DateFormat("dd MM yyyy hh:mm:ss");
            WritableCellFormat wcfDF = new WritableCellFormat(df);
            DateTime labelDTF = new DateTime(1,3,new java.util.Date(),wcfDF);
            ws.addCell(labelDTF);

            //6.���ͼƬ����,jxlֻ֧��png��ʽͼƬ
            File image = new File("f:\\1.png");

            WritableImage wimage = new WritableImage(0,4,6,17,image);
            ws.addImage(wimage);

            //7.д�빤����
            wwb.write();
            wwb.close();

        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /** ��file1������,�����޸Ĳ������������file2
     * ��Ԫ��ԭ�еĸ�ʽ�����β���ȥ�������Կɽ��µĵ�Ԫ�����μ���ȥ��
     * ��ʹ��Ԫ��������Բ�ͬ����ʽ����
     */
    public static void modifyExcel(File file1,File file2)
    {
        try
        {
            Workbook rwb = Workbook.getWorkbook(file1);

            WritableWorkbook wwb = Workbook.createWorkbook(file2,rwb);//copy

            WritableFont wfc = new WritableFont(WritableFont.ARIAL,10,WritableFont.NO_BOLD,false,

                    UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLUE);

            WritableCellFormat wcfFC = new WritableCellFormat(wfc);

            WritableSheet ws = wwb.getSheet(0);

            WritableCell wc = ws.getWritableCell(0,0);

            //�жϵ�Ԫ�������,������Ӧ��ת��
            if(wc.getType() == CellType.LABEL)
            {
                Label labelCF =new Label(0,0,"����£�",wcfFC);
                ws.addCell(labelCF);
                //Label label = (Label)wc;
                //label.setString("���޸�");
            }
            wwb.write();
            wwb.close();
            rwb.close();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    //����
    public static void main(String args[])
    {
        try{

            //��EXCEL
            ExcelModify.readExcel("file/other/TestExcel.xls");

            //���EXCEL
            File filewrite=new File("file/other/TestExcel.xls");
            filewrite.createNewFile();

            OutputStream os=new FileOutputStream(filewrite);
            ExcelModify.writeExcel(os);

            //�޸�EXCEL
            ExcelModify.modifyExcel(new File("file/other/TestExcel.xls"), new File("F:/��¥����3.xls"));
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
