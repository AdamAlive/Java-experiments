package K_Means;
import java.io.*;
import java.util.Scanner;

public class K_Means {
	private String fileName ="";
	private int k =0;
	private int n = 0;
	private point[] arr;
	private int[] marks;
	private point[] centers;
	
	class point{
		public double x;
		public double y;
		
		public point(double x,double y){
			this.x = x;
			this.y = y;
		}
		
		public point(point a){
			this.x = a.x;
			this.y = a.y;
		}
		
		public void show(){
			System.out.println("["+x+","+y+"]");
		}
	}
	
	public K_Means(int k,String fileName){
		this.k = k;
		this.fileName = fileName;
		arr = new point[65535];
		marks = new int [65535];
		centers = new point [k];
		getInput();
	}
	
	public void getInput(){
		try {
			FileInputStream in = new FileInputStream(new File(fileName)); 
            Scanner s = new Scanner(in); 
            while(s.hasNextDouble()){
            	arr[n++] = new point(s.nextDouble(),s.nextDouble());  
            }
            System.out.println("读取样本"+n);
            s.close();
		} catch (FileNotFoundException e) {
			System.out.println("出现异常!");
			e.printStackTrace();
		}catch (Exception e) {
			System.out.println("出现异常!");
			e.printStackTrace();
			System.out.println(e.toString());
		}
		
	}
	
	public double distance(point a,point b){
		return Math.sqrt( (a.x-b.x)*(a.x-b.x) + (a.y-b.y)*(a.y-b.y) );
	}
	
	
	public void run(){
		
		
		for (int i=0;i<k;i++){
			centers[i] = new point(Math.random()*10-5,Math.random()*10-5);
		}
		initCenters();
		
		int count = 0;
		do{
			count++;
			//第一步，重分配各个点
			for (int i=0;i<n;i++){
				marks[i] = 0;
				for (int j=1;j<k;j++){
					if (distance(arr[i],centers[marks[i]]) > distance(arr[i],centers[j])){
						marks[i] = j;
					}
				}
			}
			//第二步,重算质心
			point[] new_centers = new point[k];
			for (int i=0;i<k;i++){
				double totalX=0;
				double totalY=0;
				double num=0;
				for (int j=0;j<n;j++){
					if(marks[j] == i){
						totalX += arr[j].x;
						totalY += arr[j].y;
						num++;
					}
				
				}
				new_centers[i] = new point(totalX / num, totalY / num);
			}
			//第三步，退出循环条件:簇不发生变化或达到最大迭代次
			boolean changed = false;
			for (int i=0;i<k;i++){
				if(new_centers[i].x != centers[i].x || new_centers[i].y != centers[i].y){
					changed = true;
					centers[i].x = new_centers[i].x;
					centers[i].y = new_centers[i].y;
				}
			}
			if (count >= 1000 || changed == false){
				break;
			}
		}while(true);
		System.out.println("运算结束，循环次数：" + count);
		System.out.println(k + "个簇的质心如下：");
		for(int i = 0;i<k;i++){
			centers[i].show();
		}
	}
	private void initCenters() {
		int[] choose = new int [k];
		
		for (int i=0;i<k;i++){
			boolean ok;
			int temp;	
			do{
				temp = (int) Math.floor(Math.random()*n);
				ok = true;
				
				for (int j=0;j<i;j++){
					if(choose[j] == temp){
						ok = false;
						break;
					}
				}
			}while(!ok);	
			choose[i] = temp;
		}
		for (int i=0;i<k;i++){
			centers[i] = new point(arr[ choose[i] ]);
		}
	}
}