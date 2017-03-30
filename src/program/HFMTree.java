package program;

import java.io.PrintStream;
import java.util.*;

public class HFMTree {
	private class HTree implements Comparable{
		String ch;
		int val;
		HTree left;
		HTree right;
		HTree parent;
		HTree(String ch,int val,HTree left,HTree right,HTree parent){
			this.ch=ch;
			this.val=val;
			this.left=left;
			this.right=right;
			this.parent=parent;
		}
		@Override
		public int compareTo(Object o) {
			HTree temp=(HTree)o;
			return this.val>temp.val?1:this.val==temp.val?0:-1;
		}
		public String toString(){
			return ch+" : "+val;
		}
		
	}
	private List<HTree> collect;
	private int count;
	private HTree root;
	HFMTree(String[] chs,int[] vals){
		this.count=chs.length;
		collect=new ArrayList<>();
		for(int i=0;i<count;i++){
			collect.add(new HTree(chs[i],vals[i],null,null,null));
		}
	}
	@SuppressWarnings("unchecked")
	void generate(){
		while(collect.size()!=1){
			HTree temp1=Collections.min(collect);
			collect.remove(temp1);
			HTree temp2=Collections.min(collect);
			collect.remove(temp2);
			HTree temp=new HTree(" ",temp1.val+temp2.val,temp2,temp1,null);
			temp1.parent=temp;
			temp2.parent=temp;
			collect.add(temp); 
		}
		root=collect.remove(0);
	}
	private void getZero(HTree temp){

		if(temp!=null){
			getZero(temp.left);
			temp.val=0;
			getZero(temp.right);
		}
	}
	public HashMap<String,String> getCode(){
		HashMap<String,String> result=new HashMap();
		Queue<String> cd=new ArrayDeque<>();
		String tep=null;
		HTree temp=root;
		getZero(temp);
		HTree pointer=root;
		while(pointer!=null){
			if(pointer.val==0){
				pointer.val=1;
				if(pointer.left!=null){
					pointer=pointer.left;
					cd.add("0");
				}
				else if(pointer.right==null){
					Iterator<String> it=cd.iterator();
					while(it.hasNext()){
						String t=it.next();
						if(t!=null)
							tep+=t;
						tep+=" ";
					}
					result.put(pointer.ch,tep);
					tep=null;
				}
			}
			else if(pointer.val==1){
				pointer.val=2;
				if(pointer.right!=null){
					pointer=pointer.right;
					cd.add("1");
				}
			}else{
				pointer.val=0;
				pointer=pointer.parent;
				cd.poll();
			}
		}
		return result;
	}
	public static void main(String[] args) {
		String ch[]={"d","t","h","y","k"};
		int val[]={76,24,65,12,99};
		HFMTree tree=new HFMTree(ch,val);
		tree.generate();
		PrintStream ps=new PrintStream(System.out);
		HashMap<String,String> code=tree.getCode();
		code.forEach((key,value)->{
			ps.println(key+"-->"+value);
		});
	}
}








