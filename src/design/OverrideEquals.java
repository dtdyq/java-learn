package design;
/**
 * 
 * @author dtdyq
 * 
 * ��ȷ����дequals������
 * 	1��ʹ��==�ж��Ƿ���ͬһ������ʵ��
 *  2��ʹ��instanceof������Ƿ���ͬһ��
 *  3����������ת��
 *  4���Զ���Ĺؼ����Խ��бȽ�
 *  5�������Ƿ�����Է��ԡ������ԡ�һ���ԡ��־���
 *  
 *  ע�⣺��дequalsʱһ��Ҫ��дhashCode����
 *
 */
public class OverrideEquals {
	private String fstr;
	private Integer fint;
	public OverrideEquals(String fstr, Integer fint) {
		super();
		this.fstr = fstr;
		this.fint = fint;
	}
	@Override
	public boolean equals(Object o){
		if(this==o){
			return true;
		}
		if(o instanceof OverrideEquals){
			OverrideEquals oe = (OverrideEquals) o;
			return this.fstr.equals(oe.fstr) && Integer.compare(this.fint, oe.fint)==0;
		}
		return false;
	}
}






