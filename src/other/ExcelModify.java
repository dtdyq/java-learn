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

    /***读取Excel*/

    public static void readExcel(String filePath)
    {
        try
        {

            InputStream is = new FileInputStream(filePath);
            Workbook rwb = Workbook.getWorkbook(is);
            //这里有两种方法获取sheet表:名字和下标（从0开始）
            //Sheet st = rwb.getSheet("original");
            Sheet st = rwb.getSheet(0);

            /**
             //获得第一行第一列单元的值
             Cell c00 = st.getCell(0,0);
             //通用的获取cell值的方式,返回字符串
             String strc00 = c00.getContents();

             //获得cell具体类型值的方式
             if(c00.getType() == CellType.LABEL)
             {
             LabelCell labelc00 = (LabelCell)c00;
             strc00 = labelc00.getString();
             }

             //输出
             System.out.println(strc00);*/
            //Sheet的下标是从0开始
            //获取第一张Sheet表
            Sheet rst = rwb.getSheet(0);

            //获取Sheet表中所包含的总列数
            int rsColumns = rst.getColumns();

            //获取Sheet表中所包含的总行数
            int rsRows = rst.getRows();

            //获取指定单元格的对象引用
            for (int i = 0; i < rsRows; i++)
            {
                for (int j = 0; j < rsColumns; j++)
                {

                    Cell cell = rst.getCell(j, i);
                    System.out.print(cell.getContents() + " ");
                }
                System.out.println();
            }
            //关闭
            rwb.close();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /**输出Excel*/
    public static void writeExcel(OutputStream os)
    {
        try
        {
            /** 只能通过API提供的 工厂方法来创建Workbook，
             * 而不能使用WritableWorkbook的构造函数，因为类WritableWorkbook
             * 的构造函数为 protected类型：方法一：直接从目标文件中读取
             * WritableWorkbook wwb = Workbook.createWorkbook(new File(targetfile));
             *
             * 方法 二：如下实例所示 将WritableWorkbook直接写入到输出流*/
            WritableWorkbook wwb = Workbook.createWorkbook(os);

            //创建Excel工作表 指定名称和位置
            WritableSheet ws = wwb.createSheet("Test Sheet 1",0);

            /**************往工作表中添加数据*****************/
            //1.添加Label对象
            Label label = new Label(0,0,"测试");
            ws.addCell(label);
            //添加带有字型Formatting对象
            WritableFont wf = new WritableFont(WritableFont.TIMES,18,WritableFont.BOLD,true);
            WritableCellFormat wcf = new WritableCellFormat(wf);
            Label labelcf = new Label(1,0,"this is a label test",wcf);
            ws.addCell(labelcf);

            //添加带有字体颜色的Formatting对象
            WritableFont wfc = new WritableFont(WritableFont.ARIAL,10,WritableFont.NO_BOLD,false,

                    UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.DARK_YELLOW);

            WritableCellFormat wcfFC = new WritableCellFormat(wfc);
            Label labelCF = new Label(1,0,"Ok",wcfFC);
            ws.addCell(labelCF);



            //2.添加Number对象
            Number labelN = new Number(0,1,3.1415926);
            ws.addCell(labelN);
            //添加带有formatting的Number对象
            NumberFormat nf = new NumberFormat("#.##");
            WritableCellFormat wcfN = new WritableCellFormat(nf);
            Number labelNF = new jxl.write.Number(1,1,3.1415926,wcfN);
            ws.addCell(labelNF);

            //3.添加Boolean对象
            Boolean labelB = new jxl.write.Boolean(0,2,true);
            ws.addCell(labelB);
            Boolean labelB1 = new jxl.write.Boolean(1,2,false);
            ws.addCell(labelB1);
            //4.添加DateTime对象
            jxl.write.DateTime labelDT = new jxl.write.DateTime(0,3,new java.util.Date());
            ws.addCell(labelDT);

            //5.添加带有formatting的DateFormat对象
            DateFormat df = new DateFormat("dd MM yyyy hh:mm:ss");
            WritableCellFormat wcfDF = new WritableCellFormat(df);
            DateTime labelDTF = new DateTime(1,3,new java.util.Date(),wcfDF);
            ws.addCell(labelDTF);

            //6.添加图片对象,jxl只支持png格式图片
            File image = new File("f:\\1.png");

            WritableImage wimage = new WritableImage(0,4,6,17,image);
            ws.addImage(wimage);

            //7.写入工作表
            wwb.write();
            wwb.close();

        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /** 将file1拷贝后,进行修改并创建输出对象file2
     * 单元格原有的格式化修饰不能去掉，但仍可将新的单元格修饰加上去，
     * 以使单元格的内容以不同的形式表现
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

            //判断单元格的类型,做出相应的转换
            if(wc.getType() == CellType.LABEL)
            {
                Label labelCF =new Label(0,0,"人物（新）",wcfFC);
                ws.addCell(labelCF);
                //Label label = (Label)wc;
                //label.setString("被修改");
            }
            wwb.write();
            wwb.close();
            rwb.close();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    //测试
    public static void main(String args[])
    {
        try{

            //读EXCEL
            ExcelModify.readExcel("file/other/TestExcel.xls");

            //输出EXCEL
            File filewrite=new File("file/other/TestExcel.xls");
            filewrite.createNewFile();

            OutputStream os=new FileOutputStream(filewrite);
            ExcelModify.writeExcel(os);

            //修改EXCEL
            ExcelModify.modifyExcel(new File("file/other/TestExcel.xls"), new File("F:/红楼人物3.xls"));
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
