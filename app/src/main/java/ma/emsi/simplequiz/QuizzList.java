package ma.emsi.simplequiz;

import java.util.ArrayList;
import java.util.List;

public class QuizzList {

  static Questionaire quest_1 = new Questionaire(R.drawable.monalisa,
            "who painted the Mona-Lisa?",
            "Raphael","Léonard de Vinci",
            false,true),
    quest_2 = new Questionaire(R.drawable.thelastsupper,
            "What is the name of this painting?",
            "The Last Supper",     "The Last Dinner",
            true, false),
    quest_3 =  new Questionaire(R.drawable.thestarrynight,
            "What is the name of this painting?",
            "The starry night","the blue night",
            true,false),
    quest_4 = new Questionaire(R.drawable.girlwithapearlearring,
            "When was Girl With a Pearl Earring published?",
            "1665","1594",
            false,true),
    quest_5 = new Questionaire(R.drawable.lasmeninas,
            "What is the name of this painting?",
            "Puellae","Las Meninas",
            false,true);


    static List<Questionaire> LIST_QUESTIONAIRES = new ArrayList<Questionaire>(){{
        LIST_QUESTIONAIRES.add(quest_1);
        LIST_QUESTIONAIRES.add(quest_2);
        LIST_QUESTIONAIRES.add(quest_3);
        LIST_QUESTIONAIRES.add(quest_4);
        LIST_QUESTIONAIRES.add(quest_5);
    }};


}
