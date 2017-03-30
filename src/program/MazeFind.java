package program;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.*;

/**
 * 
 * @author dtdyq
 *	ջ�����ã�
 *		�Թ����ң������Թ�����ڣ��ҳ��Թ��ĳ���
 */
public class MazeFind {
	//�Զ�ά�������ʽ�����Թ���ֵΪ0��ʾ����ͨ����ֵΪ1��ʾ����ͨ��
	private byte[][] maze;
	//���ڼ�¼·����ջ��
	private Stack<Position> stack;
	//��ǰ������λ�ã�
	private Position curp;
	//����λ�ã�
	private Position dest;
	//�ж��Ƿ��г��ڣ�
	private boolean hasPath;
	
	public MazeFind(byte[][] maze,Position src,Position dest){
		this.maze=maze;
		this.curp=src;
		this.dest=dest;
		stack=new Stack<>();
		//����·����
		hasPath=find();
	}
	private boolean find(){
		boolean flag=false;
		do{
			stack.push(curp);
			markPost(curp);
			if(isDest(curp)){
				flag=true;
				break;
			}
			else{
				Position temp=null;
				while((curp=nextPost(curp))==null){
					curp=stack.pop();
					temp=curp;
				}
				if(temp!=null)
					stack.push(temp);
			}
		}while(!stack.empty());
		return flag;
	}
	//�鿴��ǰ���鿴��λ���Ƿ��ͨ����
	private boolean canPass(Position p){
		return maze[p.x][p.y]==1;
	}
	//���߹���λ�����ò���ͨ�����Ѿ��߹���λ�ò����ٴη��ʣ�:
	private void markPost(Position p){
		maze[p.x][p.y]=0;
	}
	//�жϸ�����λ���Ƿ��ǳ���;
	private boolean isDest(Position p){
		return p.x==dest.x&&p.y==dest.y;
	}
	//��ȡ��ǰλ�õ���һ��λ�ã�
	private Position nextPost(Position p){
		Position result=null;
		if(canPass(result=new Position(p.x-1,p.y))){
			return result;
		}
		else if(canPass(result=new Position(p.x,p.y+1))){
			return result;
		}
		else if(canPass(result=new Position(p.x+1,p.y))){
			return result;
		}
		else if(canPass(result=new Position(p.x,p.y-1))){
			return result;
		}
		else{
			return null;
		}
	}
	public boolean hasPath(){
		return hasPath;
	}

	public List<Position> Path(){
		List<Position> path=new ArrayList<>();
		while(!stack.empty()){
			path.add(stack.pop());
		}
		return path;
	}
	public static void main(String[] args){
		System.out.println("maze find:");
		//��ʼ���Թ���
		byte[][] maze={
				{0,0,0,0,0,0,0,0,0,0},
				{0,1,1,0,1,1,1,0,1,0},
				{0,1,1,0,1,1,1,0,1,0},
				{0,1,1,1,1,0,0,1,1,0},
				{0,1,0,0,0,1,1,1,1,0},
				{0,1,1,1,0,1,1,1,1,0},
				{0,1,0,1,1,1,0,1,1,0},
				{0,1,0,0,0,1,0,0,1,0},
				{0,0,1,1,1,1,1,1,1,0},
				{0,0,0,0,0,0,0,0,0,0}
		};
		long t=System.currentTimeMillis();
		MazeFind find=new MazeFind(maze,new Position(1,1),new Position(8,8));
		List<Position> path=null;
		if(find.hasPath()){
			path=find.Path();
			Iterator<Position> it=path.iterator();
			while(it.hasNext()){
				System.out.println(it.next());
			}
		}
		else{
			System.out.println("not found.");
		}
		System.out.println("using time: "+(System.currentTimeMillis()-t));
	}
}
//��ʾ�Թ��е�λ�ã�
class Position{
	int x;
	int y;
	Position(int x,int y){
		this.x=x;
		this.y=y;
	}
	public String toString(){
		return "("+x+","+y+")";
	}
}








