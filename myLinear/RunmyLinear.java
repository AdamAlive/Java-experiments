package myLinear;

public class RunmyLinear {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("读取第一个文件...");
		myLinear calc1 = new myLinear("D:/JavaProject/Experiments/src/myLinear/LR_ex0.txt");
		System.out.println("\n读取第二个文件...");
		myLinear calc2 = new myLinear("D:/JavaProject/Experiments/src/myLinear/LR_ex1.txt");
		System.out.println("计算完成。下面是结果显示：");
		calc1.print();
		calc2.print();
		
	}

}
