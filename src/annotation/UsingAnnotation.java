package annotation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * 
 * @author dtdyq
 * 
 * 使用annotation:有問題
 *
 */
public class UsingAnnotation {
	private JFrame main = new JFrame("annotation test");
	@Actionlisten(listener=OKListener.class)
	private JButton ok = new JButton("OK");
	@Actionlisten(listener=CCListener.class)
	private JButton cancel = new JButton("cancel");
	
	private JTextArea  ta = new JTextArea();
	public void init(){
		main.setBounds(500, 300, 300, 300);
		JPanel jp = new JPanel();
		jp.add(ok);
		jp.add(cancel);
		ok.addActionListener(new OKListener());
		ActionProxy.actionInit(this);
		main.add(ta);
		main.add(jp,"South");
		main.setVisible(true);
	}
	
	public class OKListener implements ActionListener{
		public OKListener() {
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			ta.append("touch the ok button....\n");
		}
	}
	public class CCListener implements ActionListener{
		public CCListener() {
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			ta.append("touch the cancel button....\n");
		}
	}
	public static void main(String[] args){
		new UsingAnnotation().init();
	}
}
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface Actionlisten{
	Class<? extends ActionListener> listener();
}
class ActionProxy{
	public static void actionInit(Object o){
		try{
			Class<?> co = o.getClass();
			for(Field f:co.getDeclaredFields()){
				f.setAccessible(true);
				Actionlisten  al = (Actionlisten)
						f.getAnnotation(Actionlisten.class);
				//获取成员变量f的值：
				Object fo = f.get(o);
				if(al != null && fo != null && fo instanceof AbstractButton){
					//获取元数据并获得实例：
					 Class<? extends ActionListener> clazz = al.listener();
					 ActionListener lis =clazz.newInstance();
					 AbstractButton ab = (AbstractButton) fo;
					 ab.addActionListener(lis);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}









