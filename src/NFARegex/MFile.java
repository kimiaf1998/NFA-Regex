package NFARegex;

import java.io.*;
import java.util.ArrayList;

public class MFile {

    public static ArrayList<String> getWordsFromFile(File file){
        ArrayList<String> totalWords=new ArrayList<>();

        try {

            FileReader reader=new FileReader(file);
            BufferedReader bf=new BufferedReader(reader);
            String []words={" "};
            String str="";

            str=bf.readLine();
            do{
                words=str.split(" ");
                if(words!=null)
                    for(int i=0;i<words.length ; i++)
                        totalWords.add(words[i]);
//               for(int i=0;i<words.length ;i++)
//                    System.out.println(words[i]);
            }while((str=bf.readLine())!=null);
            reader.close();
            return totalWords;


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }
}
