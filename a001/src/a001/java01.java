package a001;

public class java01 {

	public static void main(String[] args) {
		
		
	}
	static void main2 (p1 anyobj){
		anyobj.m1();
		if(anyobj instanceof p2){
			((p2) anyobj).m2();
		}else if( anyobj instanceof p3){
			((p3) anyobj).m3();
		}
	}
	
	
	interface p1{
		void m1();
	}
	class p2 implements p1  {
		public void m1(){System.out.println("ok");}
		void m2(){};
	}
	class p3 implements p1{
		public void m1(){System.out.println("ok2");}
		void m3(){}
	}
}
