package java8.functional;

import java.util.ArrayList;

/**
 * Created by dtdyq on 2017/4/1.
 */
public class TestStream {
    public static void main(String[] args){
        ArrayList<Artist> arts=new ArrayList<>();
        arts.add(new Artist("tom","london",23));
        arts.add(new Artist("kily","beijing",65));
        arts.add(new Artist("liqi","london",76));
        arts.add(new Artist("dtd","changzhi",12));
        //���������׶ص������ҵ�������
        int count=0;
        for(Artist a:arts){
            if(a.isFrom("london")){
                count++;
            }
        }
        System.out.println(count);

        //ʹ��Stream����;
        long c=arts.stream().filter(a->a.isFrom("london")).count();
        System.out.println(c);

        //������ֵ�ͼ�����ֵ��
        //������������ң�
        arts.stream().filter(a->{
            System.out.println(a);
            return a.isFrom("london");
        });
        //��������ң�
        long ct=arts.stream().filter(a->{
            System.out.println(a);
            return a.isFrom("london");
        }).count();
        System.out.println(ct);
    }
}
//�������ࣺ
class Artist{
    private String name;
    private String locat;
    private int numbres;
    public int getNumbres() {
		return numbres;
	}
	public void setNumbres(int numbres) {
		this.numbres = numbres;
	}
	public Artist(String name,String locat,int numbers){
        this.name=name;
        this.locat=locat;
        this.numbres=numbers;
    }
    public boolean isFrom(String locat){
        return this.locat.equals(locat);
    }
    public String getName(){
        return name;
    }
    @Override
    public String toString() {
        return "Artist{" +
                "name='" + name + '\'' +
                ", locat='" + locat + '\'' +
                '}';
    }
}