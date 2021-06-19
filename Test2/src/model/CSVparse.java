package model;

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
		DBCon dbc = new DBCon();
		dbc.connect();
        FileReader fr = new FileReader("/home/ntut/Exercise/books.csv");
        BufferedReader br = new BufferedReader(fr);
        List<String> ls = new ArrayList<>();
        String sql, data;
        sql = "insert into Book (ISBN13,title,authors,average_rating,num_page,ratings_count,publication) values ";
        int z=0;
        while (br.ready()) 
        {
            String str = br.readLine();
            ls = new ArrayList<>(Arrays.asList(str.split(",")));
            if(z++ == 0) continue;
            if(z>2000) break;
            data = "(\"" + ls.get(4) + "\","
            		+ "\"" + ls.get(1) + "\","
            		+ "\"" + ls.get(2) + "\","
            		+ ls.get(3) + ","
            		+ ls.get(7) + ","
            		+ ls.get(8) + ","
            		+ "\"" + ls.get(11) + "\""
            		+ ")";
            dbc.update(sql+data);
            ls.clear();
        }
        fr.close();
		System.out.println("End!!!");
		//DBCon db = new DBCon();
		
	}
}
