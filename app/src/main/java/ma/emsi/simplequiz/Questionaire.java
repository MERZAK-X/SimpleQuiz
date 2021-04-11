package ma.emsi.simplequiz;

public class Questionaire {
    int imagePath;
    String question, choice1, choice2;
    boolean bChoice1, bChoice2;

    Questionaire(int imagePath, String question, String choice1, String choice2, boolean bChoice1, boolean bChoice2){
        this.imagePath = imagePath;
        this.question = question;
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.bChoice1 = bChoice1;
        this.bChoice2 = bChoice2;
    }

    public int getImagePath() {
        return imagePath;
    }

    public void setImagePath(int imagePath) {
        this.imagePath = imagePath;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getChoice1() {
        return choice1;
    }

    public void setChoice1(String choice1) {
        this.choice1 = choice1;
    }

    public String getChoice2() {
        return choice2;
    }

    public void setChoice2(String choice2) {
        this.choice2 = choice2;
    }

    public boolean isbChoice1() {
        return bChoice1;
    }

    public void setbChoice1(boolean bChoice1) {
        this.bChoice1 = bChoice1;
    }

    public boolean isbChoice2() {
        return bChoice2;
    }

    public void setbChoice2(boolean bChoice2) {
        this.bChoice2 = bChoice2;
    }
}
