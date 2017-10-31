package dadaGogo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
/**
 * 测试用的类，没用了
 * @author Bingo
 *
 */
public class readFile {
	public static void main(String[] args) {
		String filename = "E:/Eclipse/workspace/dadaGogo/src/main/java/dadaGogo/Test1.txt";
		File file = new File(filename);
		FileReader reader = null;
		try {
			reader = new FileReader(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        BufferedReader br = new BufferedReader(reader);
        String str = null;
	}

}
