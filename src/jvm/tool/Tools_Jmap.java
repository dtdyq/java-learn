package jvm.tool;

/**
 * 
 * @author dtdyq
 * 
 * jmap(Memory Map for Java)�����������ɶ�ת������ ������ͨ��-HeapDumpOnCtrlBreak����
 * ʹ��ctrl+break�������������dump�ļ�
 * 
 * �����Բ�ѯfinalizeִ�ж��У�java�Ѻ����ô�����ϸ��Ϣ����ռ�ʹ���ʡ���ǰ�õ���������
 * ����
 * 
 *  �����ʽ��
 *  jmap [option] vmid
 *  option:
 *  	-dump:����java��ת������
 *  	-finalizerinfo����ʾ��F-Queue�еȴ���Finalizer�߳�ִ��finalize�����Ķ���
 *  	-heap����ʾjava����ϸ��Ϣ����ʹ�����ֻ��������������á��ִ�״����
 *  	-histo����ʾ���ж���ͳ����Ϣ�������ࡢʵ���������ϼ�����
 *      -permstat:��ClassLoaderΪͳ�ƿھ�����ʾ���ô��ڴ�״̬��
 *      -F:ǿ������dump����
 * eg��jmap -dump:format=b,file=dump.txt 4812
 **/

public class Tools_Jmap {
	
}





