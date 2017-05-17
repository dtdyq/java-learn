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
        //计算来自伦敦的艺术家的人数：
        int count=0;
        for(Artist a:arts){
            if(a.isFrom("london")){
                count++;
            }
        }
        System.out.println(count);

        //使用Stream处理;
        long c=arts.stream().filter(a->a.isFrom("london")).count();
        System.out.println(c);

        //惰性求值和及早求值：
        //不会输出艺术家：
        arts.stream().filter(a->{
            System.out.println(a);
            return a.isFrom("london");
        });
        //输出艺术家：
        long ct=arts.stream().filter(a->{
            System.out.println(a);
            return a.isFrom("london");
        }).count();
        System.out.println(ct);
    }
}
//艺术家类：
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