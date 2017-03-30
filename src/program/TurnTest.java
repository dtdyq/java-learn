package program;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Admin on 2017/3/24.
 *
 */
public class TurnTest {
    int left;
    int right;
    int front;
    int back;
    int top;
    int bot;

    public TurnTest(int left, int right, int front, int back, int top, int bot) {
        this.left = left;
        this.right = right;
        this.front = front;
        this.back = back;
        this.top = top;
        this.bot = bot;
    }
    public void turnLeft(){
        int tempLeft=this.left;
        int tempright=this.right;
        int temptop=this.top;
        int tempbot=this.bot;
        this.left=temptop;
        this.right=tempbot;
        this.top=tempright;
        this.bot=tempLeft;
    }
    public void turnRight(){
        int tempLeft=this.left;
        int tempright=this.right;
        int temptop=this.top;
        int tempbot=this.bot;
        this.bot=tempright;
        this.top=tempLeft;
        this.left=tempbot;
        this.right=temptop;
    }
    public void turnFront(){
        int tempfront=this.front;
        int tempback=this.back;
        int temptop=this.top;
        int tempbot=this.bot;
        this.bot=tempfront;
        this.back=tempbot;
        this.top=tempback;
        this.front=temptop;
    }
    public void turnBack(){
        int tempfront=this.front;
        int tempback=this.back;
        int temptop=this.top;
        int tempbot=this.bot;
        this.top=tempfront;
        this.bot=tempback;
        this.front=tempbot;
        this.back=temptop;
    }
    public void turnA(){
        int tempLeft=this.left;
        int tempright=this.right;
        int tempfront=this.front;
        int tempback=this.back;
        this.left=tempback;
        this.right=tempfront;
        this.front=tempLeft;
        this.back=tempright;
    }
    public void turnC(){
        int tempLeft=this.left;
        int tempright=this.right;
        int tempfront=this.front;
        int tempback=this.back;
        this.front=tempright;
        this.back=tempLeft;
        this.left=tempfront;
        this.right=tempback;
    }
    public static void main(String[] args) throws Exception{
        TurnTest r=new TurnTest(1,2,3,4,5,6);

        BufferedReader br=new BufferedReader(
                new InputStreamReader(System.in)
        );
        String str=br.readLine();
        int len=str.length();
        int i=0;
        while(i<len){
            switch (str.charAt(i)){
                case 'L':r.turnLeft();break;
                case 'R':r.turnRight();break;
                case 'F':r.turnFront();break;
                case 'B':r.turnBack();break;
                case 'A':r.turnA();break;
                case 'C':r.turnC();break;
            }
        }
        System.out.println(r);
    }

    @Override
    public String toString() {
        return
                "" + left +
                "" + right +
                "" + front +
                "" + back +
                "" + top +
                "" + bot;
    }
}
