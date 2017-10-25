package myStudentScore;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class myStudentScore {
	String filename;
	int count = 0;
	int[] scores = new int[1000];
	int lowest = 999;
	int highest = -999;
	double aver = 0;
	int S_60_69 = 0;
	int S_70_79 = 0;
	int S_80_89 = 0;
	int S_90_100 = 0;
	
	void getInput(){
		try{
			FileInputStream input = new FileInputStream(new File(filename));
			Scanner scan = new Scanner(input);
			scan.useDelimiter("\\n|\\r|;");
			//以换行和或者回车符为分割读取内容
			while(scan.hasNextInt()){
				scan.nextInt();
				scores[count++] = scan.nextInt();
			}
			System.out.println("总共读取了" + count + "个学生的分数");
			scan.close();
		}catch(Exception error){
			System.out.println("读取出错，请重新检查路径有效性。");
		}
	}
	
	public myStudentScore(String filename){
		this.filename = filename;
		getInput();
	}
	
	public void calc(){
		int totalS = 0;
		for(int i = 0;i < count;++i){
			totalS = totalS + scores[i];
			if(scores[i] > highest){
				highest = scores[i];
			}
			else if(scores[i] < lowest){
				lowest = scores[i];
			}
			//跟新最高分和最低分
			
			if (scores[i]>=60 && scores[i]<=69){
				S_60_69++;
			}else if (scores[i]>=70 && scores[i]<=79){
				S_70_79++;
			}else if (scores[i]>=80 && scores[i]<=89){
				S_80_89++;
			}else if (scores[i]>=90 && scores[i]<=100){
				S_90_100++;
			}
		}
		aver = totalS / count;
	}
	
	public void output(){
		FileOutputStream output;
		try{
			output = new FileOutputStream(new File("输出结果.txt"));
			OutputStreamWriter out = new OutputStreamWriter(output, "UTF-8");
			out.append("最高分：" + highest + "\r\n");
			out.append("最高分：" + highest + "\r\n");
			out.append("平均分: " + aver + "\r\n");
			out.append("总成绩段统计：  " + "\r\n");
			out.append("60~69: " + S_60_69 + "\r\n");
			out.append("60~69: " + S_70_79 + "\r\n");
			out.append("60~69: " + S_80_89 + "\r\n");
			out.append("60~69: " + S_90_100 + "\r\n");
			out.close();
		}catch(FileNotFoundException e){
			System.out.print("出错，请重试。");
		}catch(UnsupportedEncodingException e){
			System.out.print("出错，请重试。");
		}catch(IOException e){
			System.out.print("出错，请重试。");
		}
	}
	
	
}
