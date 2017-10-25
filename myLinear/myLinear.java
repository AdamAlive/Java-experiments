package myLinear;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

public class myLinear {
	String filename;
	double[] x = new double [1000];
	double[] y = new double [1000];
	double x_ = 0;
	double y_ = 0;
	double A = 0;
	double B = 0;
	
	int count = 0;
	
	
	public myLinear(String filename){
		this.filename = filename;
		getWay();
	}
	
	public void getWay(){
		try{
			FileInputStream inway = new FileInputStream(new File (filename));
			Scanner scan = new Scanner(inway);
			
			while(scan.hasNextDouble()){
				scan.nextDouble();
				x[count] = scan.nextDouble();
				y[count++] = scan.nextDouble();
			}
			System.out.println("��ȡ��" + count + "�����ݡ�");
			scan.close();
		}catch(Exception error){
			System.out.println("�޷���ȡ�ļ���������һ�Ρ�");
			
		}
	}
	public void calc(){
		double totalX = 0;
		double totalY = 0;
		double totalX_Y = 0;
		double totalX_X = 0;
		
		for(int i = 0;i < count;++i){
			totalX = totalX + x[i];
			totalY = totalY + y[i];
			totalX_Y = totalX_Y + x[i]*y[i];
			totalX_X = totalX_X + x[i]*x[i];
			
		}
		
		x_ = totalX / count;
		y_ = totalY / count;
		A = (totalX_Y - count * x_ * y_) / (totalX_X - count * x_ * y_ );
		B = y_ - x_* A;
		
	}
	
	public void print(){
		calc();
		
		System.out.println("�ɼ���ã������");
		System.out.println("A = " + A);
		System.out.println("B = " + B);
	}

}
