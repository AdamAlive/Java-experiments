package md5search;

import java.io.*;
import java.util.*;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;

public class DuplicateFile {
	private int dcount=0,fcount=0;
	private Map<String,Vector<String>> map=new HashMap<String,Vector<String>>();
	private StringBuffer ans = new StringBuffer("");
	
	public void fileList(File file,int level){
		if (!file.isDirectory()){
			System.out.println("输入格式错误,请输入一个目录文件");
			return;
		}
		
		StringBuffer preStr = new StringBuffer();
		for (int i=0;i<level;i++){
			preStr.append("\t");
		}
		File[] childs = file.listFiles();
		
		A:for (int i=0;i<childs.length;i++){
			if(childs[i].isDirectory()){
				dcount++;
				fileList(childs[i],level+1);
			}else{	
				fcount++;
				try{
					 FileInputStream fis = new FileInputStream(childs[i].getAbsolutePath());
					 String md5 = DigestUtils.md5Hex(IOUtils.toByteArray(fis));
					 IOUtils.closeQuietly(fis);
					 
					 if (map.containsKey(md5)){
						 System.out.println("已存在md5 "+md5+" ,正在进一步比较:");
						 
						 Vector<String> vct = map.get(md5);
						 for (int i1=0; i1<vct.size();i1++){
							 if (compareFile(childs[i].getAbsolutePath(),vct.get(i1))){
								 System.out.println("两文件相同: "+childs[i].getAbsolutePath()+" == "+vct.get(i1));
								 ans.append("两文件相同: "+childs[i].getAbsolutePath()+" == "+vct.get(i1)+"\r\n");
								 continue A;
							 }
						 }
						 //md5相同,但文件不相同
						 vct.addElement(childs[i].getAbsolutePath());
					 }
					 
					 Vector<String> vct= new Vector<String>();
					 vct.add(childs[i].getAbsolutePath());
					 map.put(md5,vct);
					 System.out.println("记录md5-文件 : "+md5+" s "+ childs[i].getName());
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		System.out.println("["+file.getName()+"]目录ok");
		if (level ==0){
			System.out.println("-------------------------------------------");
			System.out.println("扫描结束 : 共扫描"+dcount+"个目录文件, "+fcount+"个非目录文件");
			System.out.println("查询结果:");
			System.out.println(ans);
			if (ans.equals("")){
				System.out.println("无相同文件");
			}
		}
	}
	
	private boolean compareFile(String file1,String file2) {
        try{
        	BufferedInputStream inFile1 = new BufferedInputStream(new FileInputStream(file1));
            BufferedInputStream inFile2 = new BufferedInputStream(new FileInputStream(file2));
            
            //比较文件的长度是否一样
            if(inFile1.available() != inFile2.available()){
            	inFile1.close();
            	inFile2.close();
            	return false;
            }
          //比较文件的具体内容是否一样
            while(inFile1.read() != -1 && inFile2.read() != -1){
                if(inFile1.read() != inFile2.read()){
                	inFile1.close();
                	inFile2.close();
                    return false;
                }
            }
        	inFile1.close();
        	inFile2.close();
            return true; 
        }catch (FileNotFoundException e){
            e.printStackTrace();
            return false;
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }
}
