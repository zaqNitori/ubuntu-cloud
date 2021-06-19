package week3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVparse 
{
	public static void main(String[] args) throws IOException
	{
		System.out.println("Start!!!!!!!!!!!!!!!!");
        /*FileReader fr = new FileReader("/home/ntut/Exercise/books.csv");
        BufferedReader br = new BufferedReader(fr);
        List<String> ls = new ArrayList<>();
        List<String> tmp = new ArrayList<>();
        int z=0;
        while (br.ready()) 
        {
            String str = br.readLine();
            ls = new ArrayList<>(Arrays.asList(str.split(",")));
            tmp.add(ls.get(1));
            tmp.add(ls.get(2));
            tmp.add(ls.get(3));
            tmp.add(ls.get(5));
            tmp.add(ls.get(7));
            tmp.add(ls.get(8));
            tmp.add(ls.get(11));
            System.out.println(tmp.toString());
            tmp.clear();
            ls.clear();
            if(z++ > 5) break;
        }
        fr.close();*/
		
		DBCon db = new DBCon();
		
	}
}
