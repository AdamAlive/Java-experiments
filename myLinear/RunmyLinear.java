package myLinear;

public class RunmyLinear {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("��ȡ��һ���ļ�...");
		myLinear calc1 = new myLinear("D:/JavaProject/Experiments/src/myLinear/LR_ex0.txt");
		System.out.println("\n��ȡ�ڶ����ļ�...");
		myLinear calc2 = new myLinear("D:/JavaProject/Experiments/src/myLinear/LR_ex1.txt");
		System.out.println("������ɡ������ǽ����ʾ��");
		calc1.print();
		calc2.print();
		
	}

}
