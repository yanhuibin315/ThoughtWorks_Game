package dadaGogo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
/**
 * �����õ��࣬û����
 * @author Bingo
 *
 */
public class Reminder {
	public void readFile(String filename){
		ArrayList<String> time_related = new ArrayList<String>(); //���ڱ���
		ArrayList<String> dist_related = new ArrayList<String>(); //���ﱣ��
		ArrayList<String> write_off = new ArrayList<String>();	  //����
		//File file = new File(filename);
		//InputStream is = new InputStream(file);
		//InputStreamReader isr = new InputStreamReader(FileInputStream(file),"utf-8");
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(filename)));
			String str = br.readLine(); 
			String[] s = str.split(":");
			String time = s[1]; //��¼��ǰʱ��
			String[] now_time = time.split("/"); //now_time[0]:year,now_time[1]:month,now_time[2]:date
			
			while((str = br.readLine())!=null){
				String[] attr = str.split("|"); //ID��Data��Car��Distance��Have_Big
				String[] buy_time =  attr[1].split("/");
				/*
				 * �ж��Ƿ��Ѿ�����
				 */
				if(attr[4].equals("T")){
					//�й����޵�
					
				}else{
					//û�й����޵�
				}
				/*
				 * �ж��Ƿ�������ָ��
				 */
				if(Integer.parseInt(attr[3])>=9500){
					dist_related.add(str);
					
				}
				/*
				 * �ж��Ƿ���ʱ�乻ָ��
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
