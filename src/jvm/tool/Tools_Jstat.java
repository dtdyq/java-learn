package jvm.tool;

import java.util.concurrent.TimeUnit;

/**
 * @author dtdyq
 * 
 * jstat(jvm statistics monitoring tool):���ڼ����������������״̬��Ϣ�������й���
 * ������ʾ���ػ�Զ�̵�����������е���װ�ء��ڴ桢�����ռ���jit��������в���
 * �����ʽ��
 * jstat [option vmid [interval[s|ms] [count]] ]
 * vmid����ڱ��صĻ������lvmid��ͬ�������Զ�����������ʽΪ��
 * 	[protocol:][//]lvmid[@hostname[:port]/servername]
 * 
 * internal �� count�����ѯ����ʹ���������ʡ�ԣ�˵��ֻ��ѯһ��
 * option:
 *     -class:������װ��ж���������ܿռ��Լ�װ���������ĵ�ʱ��
 *     -gc������java��״��������Eden��������Survivor����������������Եȵ�����������
 *          �ռ䣬gcʱ��ϼƵ���Ϣ
 *     -gccapacity:����������-gc������ͬ���������Ҫ��עjava�Ѹ�������ʹ�õ������
 *     		С�ռ�
 *     -gcutil������������-gc������ͬ���������Ҫ��ע��ʹ�õĿռ�ռ�ܿռ�İٷֱ�
 *     -gccause:������Ĺ���һ�������ǻ������������ϴ�gc����
 *	   -gcnew������������gc״��
 *	   -gcnewcapacity:����������-gcnew������ͬ�������Ҫ��עʹ�õ������С�ռ�
 *	   -gcold�����������gc״��
 *	   -gcoldcapacity:������ļ������ݻ�����ͬ�������Ҫ��עʹ�õ���󡣴�С�ռ�
 *	   -gcpermcapacity:������ô�ʹ�õ��������С�ռ�
 *	   -compiler:���jit������������ķ�������ʱ����Ϣ
 *	   -printcompilation:����ѱ�jit����ķ���
 *	   
 */

public class Tools_Jstat {
	public static void main(String[] args) throws Exception{
		while(true){
			Class.forName("jvm.tool.Tools_Jstat");
			TimeUnit.SECONDS.sleep(10);
			Class.forName("jvm.tool.Tools_Jps");
		}
	}
}
