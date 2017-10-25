package myKmeans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Kmeans {
	
	class point{
		public double x;
		public double y;
		
		point(double x, double y){
			this.x = x;
			this.y = y;
		}
		
		point(point a){
			this.x = a.x;
			this.y = a.y;
		}
		
		
	}
	
	public double distance(point a, point b){
		return Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y)) ;
	}
	
	public void inputWay(){
		try{
			FileInputStream way = new FileInputStream(new File("fileName"));
			Scanner s = new Scanner(way);
			do{
				array[n++] = new point(s.nextDouble(), s.nextDouble());
			}while(s.hasNextDouble());
			System.out.println("读取了" + n + "个数据；");
			s.close();
		}catch(FileNotFoundException err){
			System.out.println("无法找到文件，请确认文件路径是否有效！");
			err.printStackTrace();
		}catch(Exception err){
			System.out.println("发生错误！");
			err.printStackTrace();
		}
	}
	
	String filename = "";
	int k = 0;
	int n = 0;
	int[] marks;
	point[] array;
	point[] centers;
	
	private void initCenters(){
		int[] ranCenter = new int [k];
		
		for(int i = 0; i < k; i ++){
			boolean t;
			int temp;
			do{
				temp = (int)Math.floor(Math.random()*n);
				t = true;
				
				for(int j = 0; j < i; ++j){
					if(ranCenter[j] == temp){
						t = false;
						break;
					}
				}
			}while(!t);
			ranCenter[i] = temp;
		}
		for(int i = 0; i < k; ++i){
			centers[i] = new point(array[ ranCenter[i]]);
		}
	}
	
	public Kmeans(int k, String filename){
		this.k = k;
		this.filename = filename;
		array = new point[10000];
		marks = new int[1000];
		centers = new point[1000];
		inputWay();
		
		
	}
	
	public void runKmeans(){
		for(int i = 0; i < k; ++i){
			centers[i] = new point(Math.random()*100 - 50, (Math.random()*100 - 50));
			//初始化横纵坐标 在-50至+50之间的点
		}
		initCenters();
		
		int count = 0;
		do{
			count++;
			
			for(int i = 0; i < n; i++){
				marks[i] = 0;
				for(int j = 1;j < k; j++){
					if(distance(array[i],centers[marks[i]]) > distance(array[i],centers[j])){
						marks[i] = j;
					}
				}
			}
			
			point[] new_centers = new point[k];
			for( int i = 0;i < k; i++){
				double totalX = 0;
				double totalY = 0;
				double num = 0;
				for(int j = 0;j < n; j++){
					totalX = totalX + array[j].x;
					totalY = totalY + array[j].y;
					num++;
				}
			
			
			new_centers[i] = new point(totalX / num, totalY / num);
			}
			
			boolean tr1 = false;
			for(int i = 0;i < k;i++){
				if(new_centers[i].x != centers[i].x || new_centers[i].y != centers[i].y){
					tr1 = true;
					centers[i].x = new_centers[i].x;
					centers[i].y = new_centers[i].y;
				}
			}
			
			if(count >= 1000 || tr1 == false){
				break;
			}
		}while(true);
		
		System.out.println("循环了" + "次运算");
		System.out.println(k + "个簇的质心为：");
		for(int i = 0;i < k; i++){
			
		}
	}

}
