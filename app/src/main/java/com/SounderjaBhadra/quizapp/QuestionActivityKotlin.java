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

public class QuestionActivityKotlin extends AppCompatActivity {

    int flag=0;
    int marks=0;
    public static int correct=0;
    int wrong=0;
    String[] questions={
            "Which keyword is used to declare a read-only variable in Kotlin?",
            "What will be the output of the following code?fun main() {\n" +
                    "    var a: String? = \"Hello\"\n" +
                    "    println(a?.length)\n" +
                    "}\n",
            "Which of the following is true about Kotlin?",
            "Which of the following is used to start a coroutine in Kotlin?",
            "What will be printed by this Kotlin code?fun main() {\n" +
                    "    val list = listOf(1, 2, 3)\n" +
                    "    println(list[1])\n" +
                    "}\n"
    };
    String[] options={
            "let","var","val","constant",
            "5","null","compilation error","runtime exception",
            "Kotlin runs only on JVM.","Kotlin supports both object-oriented and functional programming."," Kotlin does not support null safety.","Kotlin cannot be used for Android development.",
            "Thread.start()","CoroutineScope.launch { }","Runnable.run()","Coroutine.begin()",
            "1","2","3","compilation error"
    };
    String[] answers={
            "val",
            "5",
            "Kotlin supports both object-oriented and functional programming.",
            "CoroutineScope.launch { }",
            "2"
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
        setContentView(R.layout.activity_question_kotlin);
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
                    Toast.makeText(QuestionActivityKotlin.this, "Please select an option", Toast.LENGTH_SHORT).show();
                    return;
                }
                RadioButton uAnswer=findViewById(radio_g.getCheckedRadioButtonId());
                String ansText=uAnswer.getText().toString();

                if(ansText.equals(answers[flag])){
                    correct++;
                    Toast.makeText(QuestionActivityKotlin.this, "it is correct!", Toast.LENGTH_SHORT).show();

                }else{
                    wrong++;
                    Toast.makeText(QuestionActivityKotlin.this, "Oops! it was incorrect", Toast.LENGTH_SHORT).show();

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
                        Intent intent=new Intent(QuestionActivityKotlin.this,ResultActivity.class);
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
                Intent intent=(new Intent(QuestionActivityKotlin.this,ResultActivity.class));
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

