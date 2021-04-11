package ma.emsi.simplequiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import ma.emsi.simplequiz.controllers.QuizController;
import ma.emsi.simplequiz.entities.Answer;
import ma.emsi.simplequiz.entities.Question;
import ma.emsi.simplequiz.entities.Quiz;

public class QuizView extends AppCompatActivity {

    Question currentQuestion; QuizController quizController;
    ImageView imgQuestion;
    TextView tvQuestion;
    RadioGroup rgAnswers; RadioButton rbAnswer1, rbAnswer2;
    Button btNext;
    String imgPath, question, choice1, choice2;
    /*boolean bchoice1 = false ,bchoice2 = false, isSelected = false;
         Questionaire quest_1 = new Questionaire(R.drawable.monalisa,
                "Who painted the Mona-Lisa?",
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
                        true,false),
                quest_5 = new Questionaire(R.drawable.lasmeninas,
                        "What is the name of this painting?",
                        "Puellae","Las Meninas",
                        false,true);*/

    /*private static boolean checkAnswersWithChoice(RadioButton rb, boolean choice){
        return (rb.isSelected() == choice);
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        getActivityViews();
        ////////////////////

        ArrayList questions = new ArrayList<Question>(), answers = new ArrayList<Answer>();
        answers.add(new Answer("TEST CORRECT", true));
        answers.add(new Answer("TEST WRONG", false));
        questions.add(new Question("Test Question ???", 4, answers,"https://firebasestorage.googleapis.com/v0/b/quizapp-e1ae2.appspot.com/o/QuizQuestionsImages%2Ftestscreen.png?alt=media&token=f1024394-de67-4907-875d-551dab2138a3"));
        answers.clear();
        answers.add(new Answer("TEST 2 CORRECT", true));
        answers.add(new Answer("TEST 2 WRONG", false));
        questions.add(new Question("Test Question 2 ???", 4, answers,"https://firebasestorage.googleapis.com/v0/b/quizapp-e1ae2.appspot.com/o/QuizQuestionsImages%2Fquestion1.jpg?alt=media&token=c8637cce-d4d6-4dd2-896c-3025ff60d5d2"));

        Quiz q = new Quiz(questions,"MERZAK");

        ////////////////////
        quizController = new QuizController(q, this);
        quizController.nextQuestion();


        btNext.setOnClickListener(v -> {
            int checkedRadioButtonId = rgAnswers.getCheckedRadioButtonId();
            if(checkedRadioButtonId != -1){
           String answer = ((RadioButton) findViewById(checkedRadioButtonId)).getText().toString();
           quizController.submitAnswer(currentQuestion, answer);
           if(!quizController.nextQuestion()){
               Intent intent = new Intent(v.getContext(),Score.class);
               intent.putExtra("QuizScore", quizController.percentageScore());
               startActivity(intent);
               finish();
           }
            }else{
                Toast.makeText(v.getContext(),"Please select an answer first !",Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void setQuestion(Question quest){
        getActivityViews();
        currentQuestion = quest;
        Glide.with(this).load(currentQuestion.getImageUrl()).into(imgQuestion);
        tvQuestion.setText(currentQuestion.getValue());
        rbAnswer1.setText(currentQuestion.getAnswers().get(0).getValue());
        rbAnswer2.setText(currentQuestion.getAnswers().get(1).getValue());
    }

    private void getActivityViews(){
        imgQuestion = (ImageView) findViewById(R.id.ivQuiz);
        tvQuestion = (TextView) findViewById(R.id.tvQuestion);
        rgAnswers = (RadioGroup) findViewById(R.id.rgAnswers);
        rbAnswer1 = (RadioButton) findViewById(R.id.rbAnswer1);
        rbAnswer2 = (RadioButton) findViewById(R.id.rbAnswer2);
        btNext = (Button) findViewById(R.id.btNext);
    }

    private void clearChecks(){
        rbAnswer1.setChecked(false);
        rbAnswer2.setChecked(false);
    }



}