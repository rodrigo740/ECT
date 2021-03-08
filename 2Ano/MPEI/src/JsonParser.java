import java.io.*;
import java.util.Scanner;

import org.json.simple.JSONObject; 
import org.json.simple.parser.*; 

public class JsonParser {

	public JsonParser () throws ParseException, IOException {
		parse();
	}
	public void parse() throws ParseException, IOException{
		
		String files[] = new String[11];
		files[0] = "Arma_3.jsonlines";
		files[1] = "Counter_Strike.jsonlines";
		files[2] = "Counter_Strike_Global_Offensive.jsonlines";
		files[3] = "Dota_2.jsonlines";
		files[4] = "Football_Manager_2015.jsonlines";
		files[5] = "Garrys_Mod.jsonlines";
		files[6] = "Grand_Theft_Auto_V.jsonlines";
		files[7] = "Sid_Meiers_Civilization_5.jsonlines";
		files[8] = "Team_Fortress_2.jsonlines";
		files[9] = "The_Elder_Scrolls_V.jsonlines";
		files[10] = "Warframe.jsonlines";
		
		
		for (int i = 0; i < files.length; i++) {
			File f = new File("Full_Reviews/" + files[i]);
			Scanner fin = new Scanner(f);
			
			String p[] = files[i].split("\\.");
			
			PrintWriter pwf = new PrintWriter("reviews/" + p[0]);

			while(fin.hasNextLine()) {
				Object obj = new JSONParser().parse(fin.nextLine());
				JSONObject jo = (JSONObject) obj;
				
				String username = (String) jo.get("username"); 
		        String review = (String) jo.get("review"); 
				
		        pwf.println(username + ";-;" + review);
			}
			fin.close();
			pwf.close();
			//if (i == 10) {
				//System.out.println("100% concluido\n");
			//}
			//else {
				System.out.println((i)*10 + "% concluido\n");
			//}
		}
		
	}
}
