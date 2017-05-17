package other.excel;
import java.io.*;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.*;

import java.util.*;
/**
 * Created by dtdyq on 2017/3/7.
 *  �б����ļ�shampoo.slx����¼��ϴ��ˮ���������
 *  Ҫ��ɾ���ļ��в�Ʒ�۳��������������µ�
 *  ʱ����1010��ʾ10��10��
 *
 */
public class ExcelOperator {
    //׼����ȡ�Ĵ����ļ�
    private Workbook srcExcel=null;
    //׼��д���Ŀ���ļ�
    private WritableWorkbook destExcel=null;
    //��Ҫ��ɾ���Ĳ�Ʒ��¼��
    HashSet<String> pdelete=new HashSet<>();
    //��Ʒ���ƺ����һ������ʱ��ļ�¼��
    HashMap<String,Integer> ptime=new HashMap<>();

    public ExcelOperator(String src,String dest){
        try {
            InputStream instream = new FileInputStream(src);
            srcExcel = Workbook.getWorkbook(instream);
            destExcel=Workbook.createWorkbook(new BufferedOutputStream(new FileOutputStream(dest)));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //ִ�о��������
    public void operat(){
        try {
            //Sheet���±��Ǵ�0��ʼ
            //��ȡ��һ��Sheet��
            Sheet readsheet = srcExcel.getSheet(0);
            WritableSheet writesheet = destExcel.createSheet("frist sheet", 0);

            int rows = readsheet.getRows();
            for (int i = 1; i < rows; i++) {
                String name = readsheet.getCell(4, i).getContents();
                int time = Integer.parseInt(readsheet.getCell(0, i).getContents());

                if (ptime.containsKey(name) && needDelete(name, time)) {

                    pdelete.add(name);
                }
                ptime.put(name, time);
            }
            writeLog("file/other/proAll.txt",ptime.keySet());
            ptime.clear();

            //һ�в�Ʒ��Ϣ��
            jxl.Cell[] cell;
            //��Ŀ������д��ָ���ļ���
            Label[] label=new Label[8];
            label[0]=new Label(0,0,"ʱ��");
            label[1]=new Label(1,0,"�����");
            label[2]=new Label(2,0,"�������");
            label[3]=new Label(3,0,"��Ʒ���");
            label[4]=new Label(4,0,"��Ʒ����");
            label[5]=new Label(5,0,"����");
            label[6]=new Label(6,0,"����");
            label[7]=new Label(7,0,"�ɱ�");
            for(int j=0;j<8;j++){
                writesheet.addCell(label[j]);
            }
            for (int i = 1, k = 1; i < rows; i++) {
                cell=readsheet.getRow(i);
                if (!pdelete.contains(cell[4].getContents())) {
                    writesheet.addCell(new jxl.write.Number(0,k,Integer.parseInt(cell[0].getContents())));
                    writesheet.addCell(new jxl.write.Number(1,k,Integer.parseInt(cell[1].getContents())));
                    writesheet.addCell(new jxl.write.Label(2,k,cell[2].getContents()));
                    writesheet.addCell(new jxl.write.Label(3,k,cell[3].getContents()));
                    writesheet.addCell(new jxl.write.Label(4,k,cell[4].getContents()));
                    writesheet.addCell(new jxl.write.Number(5,k,Integer.parseInt(cell[5].getContents())));
                    writesheet.addCell(new jxl.write.Number(6,k,Double.parseDouble(cell[6].getContents())));
                    writesheet.addCell(new jxl.write.Label(7,k,cell[7].getContents()));
                    k++;
                    if(k%1000==0) {
                        System.out.println("write..." + k);
                    }
                }
            }
            destExcel.write();
            srcExcel.close();
            destExcel.close();
            writeLog("file/other/proDelete.txt",pdelete);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //�ж��������Ĳ�Ʒ�Ƿ�Ӧ�ñ�ɾ����
    private boolean needDelete(String name,int time){
        int  old=ptime.get(name);
        return ((time/100-old/100)*12+time%100-old%100)>3;

    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
	private void writeLog(String file,Set set)throws Exception{
        BufferedWriter bo=new BufferedWriter(
                new FileWriter(file)
        );
        bo.write("delete number:   "+set.size());
        bo.newLine();
        Iterator<String> i=set.iterator();
        while(i.hasNext()){
            String str=i.next();
            bo.write(str);
            bo.newLine();
        }
        bo.close();
    }
    public static void main(String[] args){
        ExcelOperator operat=new ExcelOperator("file/other/shampoo.xls","file/other/shampooDest.xls");
        operat.operat();
        System.out.println("operator success");
    }
}
