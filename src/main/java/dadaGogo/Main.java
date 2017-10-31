package dadaGogo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
/**
 * 运行main：用Junit Test
 * @author Bingo
 *
 */
public class Main {
	private List<String> input = new ArrayList<String>();
	private List<String> write_off = new ArrayList<String>();
	private List<String> dist_related = new ArrayList<String>();
	private List<String> time_related = new ArrayList<String>();
	
	private String[] ID;
	private String now_Date;
	private String[] Date;
	private String[] car_type;
	private String[] dist;
	private boolean[] big_change;
	
	public void readFile(String filename){
		/*
		 * 读取文件
		 */
		File file = new File(filename);
		FileReader reader = null;
		try {
			reader = new FileReader(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        BufferedReader br = new BufferedReader(reader);
        System.out.println("read file sucess...");
        String str = null;
        /*
         * 获取当前时间
         */
        String first_Line = null;
		try {
			str = br.readLine();
			first_Line = str;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String[] first = first_Line.split(":");
        now_Date = first[1];
        String[] now_date = now_Date.split("/");
		System.out.println("Now date:"+now_date.toString());

        /*
         * buffer:1:ID,2:Date,3:Car_type,4:distance,5:ifBigChanged
         */
        String[] buffer;
        /*
         * 按行读入数据
         */
        String[] buy_date;
        try {
			while((str = br.readLine())!=null){
				//System.out.println(str.toString());
				buffer = null;
				input.add(str);
				buffer = str.split("\\|");
				//System.out.println(buffer[0].toString());
				buy_date = buffer[1].split("/");
				//System.out.println(buy_date[0]);
				int buy_years = Integer.parseInt(buy_date[0])-Integer.parseInt(now_date[0]);
				int buy_months = Integer.parseInt(buy_date[1])-Integer.parseInt(now_date[1]);
				
				//System.out.println("buy date:"+buy_date.toString());
				//System.out.println("Write-off?...");
				/*
				 * 判断是否报废
				 */
				if(buffer[4].equals("F")){
					if((buy_years>=3)||((buy_years>=2)&&(buy_months>=11))){
						write_off.add(str);
						continue;
					}
				}else{
					if(((Integer.parseInt(buy_date[0])-Integer.parseInt(now_date[0]))>=6)
							||(((Integer.parseInt(buy_date[0])-Integer.parseInt(now_date[0]))>=5))
							&&(Integer.parseInt(buy_date[1])-Integer.parseInt(now_date[1]))>=11){
						write_off.add(str);
						continue;
					}
				}
				/*
				 * 判断是否1万公里保养
				 */
				int dist = Integer.parseInt(buffer[3]);
				//System.out.println("distance="+dist);
				if(dist%10000==0){
					dist_related.add(str);
					continue;
				}
				if(dist%10000>=9000 && dist%1000>=500){
					dist_related.add(str);
					continue;
				}
				/*
				 * 判断是否定期保养
				 */
				// int buy_years = Integer.parseInt(buy_date[0])-Integer.parseInt(now_date[0]);
				// int buy_months = Integer.parseInt(buy_date[1])-Integer.parseInt(now_date[1]);
				//System.out.println("Buy "+buy_years+"years "+buy_months+"months ");
				if(buy_months<=0){
					buy_months = buy_months+12;
				}
				if(buffer[4].equals("F")){
					if(buy_months%3>=2){
						time_related.add(str);
						continue;
					}
				}else{
					if(buy_years<3){
						if(buy_months%12>=11){
							time_related.add(str);
							continue;
						}
					}else{
						if(buy_months%3>=2){
							time_related.add(str);
							continue;
						}
					}
				}
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void printOutput(String filename){
		FileWriter fw = null;
		try {
		//如果文件存在，则追加内容；如果文件不存在，则创建文件
			File f=new File(filename);
			fw = new FileWriter(f, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] buffer = new String[5];
		PrintWriter pw = new PrintWriter(fw);
		pw.println("Reminder");
		pw.println("=============");
		pw.println();
		pw.println("* Time-related maintenance coming soon...");
		for(String ls : time_related){
			buffer = ls.split("\\|");
			pw.println(buffer[2]+":"+" 1 "+buffer[0]);
		}
		pw.println();
		pw.println("* Distance-related maintenance coming soon...");
		for(String ls : dist_related){
			buffer = ls.split("\\|");
			pw.println(buffer[2]+":"+" 1 "+buffer[0]);
		}
		pw.println();
		pw.println("* Write-off coming soon...");
		for(String ls : write_off){
			buffer = ls.split("\\|");
			pw.println(buffer[2]+":"+" 1 "+buffer[0]);
		}
		pw.flush();
		try {
			fw.flush();
			pw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void main() throws Exception{
		String readfile = "E:/Eclipse/workspace/dadaGogo/src/main/java/dadaGogo/Test1.txt";
		readFile(readfile);
		String writefile = "E:/Eclipse/workspace/dadaGogo/src/main/java/dadaGogo/Output1.txt";
		printOutput(writefile);
		
	}
	

}
