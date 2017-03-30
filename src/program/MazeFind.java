package program;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.*;

/**
 * 
 * @author dtdyq
 *	栈的运用：
 *		迷宫查找：给定迷宫的入口，找出迷宫的出口
 */
public class MazeFind {
	//以二维数组的形式定义迷宫，值为0表示不能通过，值为1表示可以通过
	private byte[][] maze;
	//用于记录路径的栈：
	private Stack<Position> stack;
	//当前欲查找位置：
	private Position curp;
	//出口位置：
	private Position dest;
	//判断是否有出口：
	private boolean hasPath;
	
	public MazeFind(byte[][] maze,Position src,Position dest){
		this.maze=maze;
		this.curp=src;
		this.dest=dest;
		stack=new Stack<>();
		//查找路径：
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
	//查看当前欲查看的位置是否可通过：
	private boolean canPass(Position p){
		return maze[p.x][p.y]==1;
	}
	//将走过的位置设置不可通过（已经走过的位置不能再次访问）:
	private void markPost(Position p){
		maze[p.x][p.y]=0;
	}
	//判断给定的位置是否是出口;
	private boolean isDest(Position p){
		return p.x==dest.x&&p.y==dest.y;
	}
	//获取当前位置的下一个位置：
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
		//初始化迷宫：
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
//表示迷宫中的位置：
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








