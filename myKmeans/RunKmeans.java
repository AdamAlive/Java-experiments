package myKmeans;
import java.util.Scanner;

import K_Means.K_Means;

public class RunKmeans {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("输入循环次数：");
		Scanner input = new Scanner(System.in);
		int k = input.nextInt();
		input.close();
		System.out.println("第一组数据：");
		K_Means temp1 = new K_Means(k,"D:/JavaProject/Experiments/bin/myKmeans/KMeans_Set.txt");
		temp1.run();
		System.out.println();
		System.out.println("第二组数据：");
		K_Means temp2 = new K_Means(k,"D:/JavaProject/Experiments/bin/myKmeans/KMeans_Set2.txt");
		temp2.run();
	}

}
