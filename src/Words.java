import java.io.*;
import java.util.*;

public class Words {

    int count;
    int pos;

    Random random;

    String[] wordList ={"Blunt", "Flask", "Gloom", "Hatch", "Inept", "Jolly"
            , "Kiosk", "Lunar", "Mirth", "Noble", "Onset", "Pique", "Quell", "Risky"
            , "Scarf", "Thick", "Unzip", "Vapid", "Wagon", "Xerox", "Yacht", "Zesty"
            , "Abide", "Caper", "Dodge"};

    public String getWords(){
        random =new Random();
        String word = (wordList[random.nextInt(wordList.length)].toLowerCase());
        return word;
    }

}
