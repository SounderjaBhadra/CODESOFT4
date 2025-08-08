package com.SounderjaBhadra.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class QuestionActivitySql extends AppCompatActivity {

    int flag=0;
    int marks=0;
    public static int correct=0;
    int wrong=0;
    String[] questions={
            "Which SQL keyword is used to sort the result set?",
            "What does the DISTINCT keyword do in SQL?",
            "Which SQL clause is used to filter rows after an aggregation (GROUP BY)?",
            "What type of join returns only matching rows from both tables?",
            "Which SQL function returns the total number of rows in a table?"
    };
    String[] options={
            "SORT BY","ORDER","ORDER BY","SORT",
            "Removes duplicate rows from the result set","Sorts data in ascending order","Displays only numeric values","Deletes duplicate rows permanently",
            "WHERE","HAVING","FILTER","AFTER",
            "LEFT JOIN","INNER JOIN","FULL OUTER JOIN","CROSS JOIN",
            "SUM()","COUNT()","TOTAL()","ROWCOUNT()"
    };
    String[] answers={
            "ORDER BY",
            "Removes duplicate rows from the result set",
            "HAVING",
            "INNER JOIN",
            "COUNT()"
    };

    TextView quitBtn,dispNo,score,question;
    Button next;
    RadioGroup radio_g;
    RadioButton rb1,rb2,rb3,rb4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        correct=0;
        wrong=0;
        flag=0;
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_question_sql);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        quitBtn=findViewById(R.id.quitBtn);
        question=findViewById(R.id.question);
        score=findViewById(R.id.score);
        dispNo=findViewById(R.id.dispNo);
        next=findViewById(R.id.nextBtn);
        radio_g=findViewById(R.id.answerGroup);
        rb1=findViewById(R.id.radioBtn1);
        rb2=findViewById(R.id.radioBtn2);
        rb3=findViewById(R.id.radioBtn3);
        rb4=findViewById(R.id.radioBtn4);


        question.setText(questions[flag]);
        rb1.setText(options[0]);
        rb2.setText(options[1]);
        rb3.setText(options[2]);
        rb4.setText(options[3]);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(radio_g.getCheckedRadioButtonId() == -1){
                    Toast.makeText(QuestionActivitySql.this, "Please select an option", Toast.LENGTH_SHORT).show();
                    return;
                }
                RadioButton uAnswer=findViewById(radio_g.getCheckedRadioButtonId());
                String ansText=uAnswer.getText().toString();

                if(ansText.equals(answers[flag])){
                    correct++;
                    Toast.makeText(QuestionActivitySql.this, "it is correct!", Toast.LENGTH_SHORT).show();

                }else{
                    wrong++;
                    Toast.makeText(QuestionActivitySql.this, "Oops! it was incorrect", Toast.LENGTH_SHORT).show();

                }
                flag++;
                if(score!=null){
                    score.setText(""+ correct);
                    if(flag < questions.length){
                        question.setText(questions[flag]);
                        rb1.setText(options[flag*4]);
                        rb2.setText(options[flag*4 + 1]);
                        rb3.setText(options[flag*4 + 2]);
                        rb4.setText(options[flag*4 + 3]);

                        dispNo.setText((flag + 1) + "/" + questions.length) ;

                    }else{
                        marks=correct;
                        Intent intent=new Intent(QuestionActivitySql.this,ResultActivity.class);
                        intent.putExtra("attempted",flag);
                        intent.putExtra("correct",correct);
                        intent.putExtra("wrong",wrong);
                        startActivity(intent);
                        finish();

                    }
                    radio_g.clearCheck();
                }
            }
        });

        quitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=(new Intent(QuestionActivitySql.this,ResultActivity.class));
                intent.putExtra("attempted",flag);
                intent.putExtra("correct",correct);
                intent.putExtra("wrong",wrong);
                startActivity(intent);
                finish();

                finish();
            }
        });



    }
}