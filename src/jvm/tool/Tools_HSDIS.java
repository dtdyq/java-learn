package jvm.tool;
/**
 * 
 * @author dtdyq
 * jit生成代码反汇编
 * HSDIS是HotSpot虚拟机JIT编译代码的反汇编插件，可以通过使用-XX:PrintAssembly
 * 和-XX:UnlockDiagnosticVMOptions参数，来把动态生成的本地代码还原为汇编代码
 * 输出，还生成了大量有价值的注释，这样就可以通过输出的代码来分析问题
 *
 */
public class Tools_HSDIS {
	int a = 1;
	static int b = 2;
	public int sum(int c){
		return a+b+c;
	}
	public static void main(String[] args){
		new Tools_HSDIS().sum(3);
	}
}
/**
 * 执行以上代码：
 * 参数： -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly 
 *   -Xcomp -XX:CompileCommand=dontinline,*Tools_HSDIS.sum
 *   -XX:CompileCommand=compileonly,*Tools_HSDIS.sum
 *  
 *  得到的代码：(摘自周志明老师(深入理解jvm虚拟机))
 *  [Disassembling for mach='i386']
    [Entry Point]
    [Constants]
      # {method} 'sum' '(I)I' in 'test/Bar'
  	  # this:     ecx       = 'test/Bar'
      # parm0:    edx       = int
      #           [sp+0x20]  (sp of caller)
      ……
      0x01cac407: cmp    0x4(%ecx),%eax
      0x01cac40a: jne    0x01c6b050         ;   {runtime_call}
      [Verified Entry Point]
      0x01cac410: mov    %eax,-0x8000(%esp)
      0x01cac417: push   %ebp
      0x01cac418: sub    $0x18,%esp         ;*aload_0
                                        ; - test.Bar::sum@0 (line  8)
      ;;  block B0 [0, 10]

      0x01cac41b: mov    0x8(%ecx),%eax     ;*getfield a
                                        ; - test.Bar::sum@1 (line 8)
      0x01cac41e: mov    $0x3d2fad8,%esi    ;   {oop(a 
    'java/lang/Class' = 'test/Bar')}
      0x01cac423: mov    0x68(%esi),%esi    ;*getstatic b
                                        ; - test.Bar::sum@4 (line 8)
	  0x01cac426: add    %esi,%eax
	  0x01cac428: add    %edx,%eax
	  0x01cac42a: add    $0x18,%esp
	  0x01cac42d: pop    %ebp
	  0x01cac42e: test   %eax,0x2b0100      ;   {poll_return}
	  0x01cac434: ret   
	  
	  
 */


















