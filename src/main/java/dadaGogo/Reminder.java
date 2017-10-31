package dadaGogo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
/**
 * 测试用的类，没用了
 * @author Bingo
 *
 */
public class Reminder {
	public void readFile(String filename){
		ArrayList<String> time_related = new ArrayList<String>(); //定期保养
		ArrayList<String> dist_related = new ArrayList<String>(); //公里保养
		ArrayList<String> write_off = new ArrayList<String>();	  //报废
		//File file = new File(filename);
		//InputStream is = new InputStream(file);
		//InputStreamReader isr = new InputStreamReader(FileInputStream(file),"utf-8");
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(filename)));
			String str = br.readLine(); 
			String[] s = str.split(":");
			String time = s[1]; //记录当前时间
			String[] now_time = time.split("/"); //now_time[0]:year,now_time[1]:month,now_time[2]:date
			
			while((str = br.readLine())!=null){
				String[] attr = str.split("|"); //ID、Data、Car、Distance、Have_Big
				String[] buy_time =  attr[1].split("/");
				/*
				 * 判断是否已经报废
				 */
				if(attr[4].equals("T")){
					//有过大修的
					
				}else{
					//没有过大修的
				}
				/*
				 * 判断是否公里数够指标
				 */
				if(Integer.parseInt(attr[3])>=9500){
					dist_related.add(str);
					
				}
				/*
				 * 判断是否购买时间够指标
				 */
				else if(attr[4].equals("T")){
					
				}
				
			}
			
			
			br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			//br.close();
		}
		
	}

}
