package HomeWork2;
/**
 * @Author Said Ebrar Çankıran 20170808018
 *
 * @Since 03.09.2019
 *
 * @Version 1.0
 * */
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Config {

    static int height = 100;
    static int width = 100;
    static int numberOfPeople = 100;
    static int numberOf3mTitan = 10;
    static int numberOf5mTitan = 10;
    static int numberOf15mTitan = 10;
    static int numberOfObstacle = 10;
    static int initialScoreOf3mTitan = 20;
    static int initialScoreOf5mTitan = 50;
    static int initialScoreOf15mTitan = 100;

    public Config() throws IOException {
        String fileName = "/Users/mac/IdeaProjects/Okul_Java/DataStructure/HomeWork2/Config.txt";
        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;

        List<String> variables = new ArrayList<>();

        while((line = br.readLine()) != null){
            //process the line
            variables.add(line);
        }

        height = giveVariable(variables.get(0));
        System.out.println(height);
        width =  giveVariable(variables.get(1));
        numberOfPeople = giveVariable(variables.get(2));
        numberOf3mTitan = giveVariable(variables.get(3));
        numberOf5mTitan = giveVariable(variables.get(4));
        numberOf15mTitan = giveVariable(variables.get(5));
        numberOfObstacle = giveVariable(variables.get(6));
        initialScoreOf3mTitan = giveVariable(variables.get(7));
        initialScoreOf5mTitan = giveVariable(variables.get(8));
        initialScoreOf15mTitan = giveVariable(variables.get(9));


    }

    public static int giveVariable(String s){
        for (int i = 0; i < s.length() ; i++) {
            if (s.charAt(i) == ':'){
                System.out.println(s);
                return Integer.parseInt(s.substring(i + 2,s.length()));
            }
        }

        return 0;
    }


}
