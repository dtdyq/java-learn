package jvm.gc;
/**
 * 
 * @author gc��־����⣺
 * 
 *  0.382: [GC (System.gc()) [PSYoungGen: 3891K->648K(35840K)] 3891K->656K(117760K),
 *  0.0029518 secs] [Times: user=0.00 sys=0.02, real=0.01 secs] 
 *  
 *  0.387: [Full GC (System.gc()) [PSYoungGen: 648K->0K(35840K)] [ParOldGen: 
 *  8K->537K(81920K)] 656K->537K(117760K), [Metaspace: 2645K->2645K(1056768K)], 
 *  0.0157405 secs] [Times: user=0.06 sys=0.00, real=0.02 secs] 
 *	
 * ��ǰ�������0.382��0.0157405����gc������ʱ�䣬�Ǵ�java���������ʱ�俪ʼ����������
 * 
 * ��ͷ��GC ��Full GC������������ռ���ͣ�����ͣ������full��˵��gc�Ƿ�����stop-the-
 * world�ġ���System.gc()����ʾϵͳ��������System.gc()
 * 
 * ��������PSYoungGen��ParOldGen��Metaspace������gc������������ʾ���������ƺ�ʹ�õ�
 * gc�ռ��������кܴ��ϵ��PSYoungGen��ʾʹ��parallel scavenge�ռ�����DefNew(default
 * new generation)ʹ��serial�ռ��������ʹ��ParNew�ռ����������ֻ��ΪParNew�������
 * ������ʽ��ͬ��
 * 
 *  �������е�3891K->648K(35840K)��ָ��GCǰ���ڴ�������ʹ������->GC����ڴ���ʹ������
 *  (���ڴ�����������)����������֮���3891K->656K(117760K)��ʾ��gcǰjava����ʹ������
 *  ->gc��java��ʹ������(java��������) ���������ʱ���ʾgc�ڸ��ڴ�������ռ�õ�����
 *  
 *  [Times: user=0.06 sys=0.00, real=0.02 secs] �Ǹ���ϸ��ʱ�����ݣ��ֱ��ʾ�û�̬
 *  ����CPUʱ�䣬�ں�̬����CPUʱ��Ͳ����ӿ�ʼ��������ǽ��ʱ��(Wall Clock Time)��
 *  CPUʱ���ǽ��ʱ��������ǣ����߰������ַ�����ĵȴ���ʱ�������ȴ�IO�͵ȴ��߳�����
 *  
 */
public class GC_Log {

}
