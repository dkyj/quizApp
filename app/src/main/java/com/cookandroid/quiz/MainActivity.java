package com.cookandroid.quiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView countLabel;
    TextView questionLabel;
    Button answerBtn1;
    Button answerBtn2;
    Button answerBtn3;
    Button answerBtn4;

    String rightAnswer;
    int rightAnswerCount = 0;
    int quizCount  = 1;
    static final private int QUIZ_COUNT = 5;

    // 질문 배열 생성
    ArrayList<ArrayList<String>> quizArray = new ArrayList<>();

    String quizData [][] = {
            // 질문 정답 오답 오답 오답
            {"하늘에서 애 낳으면?", "하이애나", "하이마트", "하이웨이", "옴메 나죽어"},
            {"세상 사람들의 머리카락 수를 모두 곱하면?", "0", "계산 불가", "무한대", "불계"},
            {"정말 멋진 신사가 자기 소개하는 것은?", "신사임당", "신사정장", "신사참배", "신사도"},
            {"거꾸로 매달린 집에 천문 만호(천 개의 문과 만 개의 방)가 무엇인가?", "벌집", "백악관", "63빌딩", "청와대"},
            {"젊은 여자가 남탕에 들어가면 무슨 죄인가?", "방화죄", "친고죄", "무죄", "풍기물란죄"},
            {"재수 없는데 재수 있다고 하는 것은?", "대입낙방", "군대 입대", "급여 삭감", "승진 누락"},
            {"세상에서 가장 먼저 자는 가수는?", "이미자", "심수봉", "조용필", "토끼소녀"},
            {"사람의 몸무게가 가장 많이 나갈 때는?", "철 들 때", "밥 먹을 때", "나이 먹을 때", "공부 할 때"},
            {"속이 끓어오르는 사람이 쓴 글은?", "부글부글", "싱글벙글", "방글방글", "이글이글"},
            {"거지가 싫어하는 색은?", "인색", "살색", "검정색", "흰색"},
            {"입으로 먹지 않고 귀로 먹는 것은?", "욕", "칭찬", "귀약", "빵"},
            {"허수아비의 아들 이름은?", "허수", "모른다", "참새", "엿장수 맘"},
            {"징그러운 꼬리를 가진 것은?", "G", "J", "H", "I"},
            {"오지 말라고 해도 오고, 가지 말라고 해도 가는 것은?", "세월", "편지", "눈물", "여친"},
            {"콩이 바쁘면?", "콩비지", "콩나물", "킹콩", "콩국수"}
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countLabel = (TextView)findViewById(R.id.countLabel);
        questionLabel = (TextView)findViewById(R.id.questionLabel);
        answerBtn1 = (Button)findViewById(R.id.answerBtn1);
        answerBtn2 = (Button)findViewById(R.id.answerBtn2);
        answerBtn3 = (Button)findViewById(R.id.answerBtn3);
        answerBtn4 = (Button)findViewById(R.id.answerBtn4);

        for (int i = 0; i < quizData.length; i++) { // quizData 으로부터 quizArray 생성
            ArrayList<String> tmpArray = new ArrayList<>();
            tmpArray.add(quizData[i][0]);
            tmpArray.add(quizData[i][1]);
            tmpArray.add(quizData[i][2]);
            tmpArray.add(quizData[i][3]);
            tmpArray.add(quizData[i][4]);

            quizArray.add(tmpArray); // quizArray에 tmpArray 추가
        }

        showNextQuiz();

    }

    // 15개 퀴즈 중 랜덤으로 5개
    public void showNextQuiz() {
        countLabel.setText("Q" + quizCount); //quizCountLabel 업데이트

        Random random = new Random(); // 0~14 사이에 랜덤 숫자 생성(quizArray 의 크기 - 1)
        int randomNum = random.nextInt(quizArray.size());

        ArrayList<String> quiz = quizArray.get(randomNum); //퀴즈 하나 선택

        questionLabel.setText(quiz.get(0)); // 질문과 정답 설정, 배열 포맷
        rightAnswer = quiz.get(1);

        // 퀴즈 지우고 셔플로 다른 퀴즈 선택
        quiz.remove(0);
        Collections.shuffle(quiz);

        //정답 선택 설정
        answerBtn1.setText(quiz.get(0));
        answerBtn2.setText(quiz.get(1));
        answerBtn3.setText(quiz.get(2));
        answerBtn4.setText(quiz.get(3));

        // quizArray 로 부터 quiz 삭제
        quizArray.remove(randomNum);
    }

    // 정답 확인
    public void checkAnswer(View view) {
        // 버튼을 누름
        Button answerBtn = (Button)findViewById(view.getId());
        String btnText = answerBtn.getText().toString();

        String alertTitle;

        if (btnText.equals(rightAnswer)) {
            //정답
            alertTitle = "정답입니다";
            rightAnswerCount++;
        } else {
            //오답
            alertTitle = "틀렸습니다";
        }

        // 다이얼로그 창을 생성하여 정답 확인
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(alertTitle);
        builder.setMessage("정답 : " + rightAnswer);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (quizCount == QUIZ_COUNT) {
                    // 결과 보여줌
                    Intent intent = new Intent(getApplicationContext(), Result.class);
                    intent.putExtra("RIGHT_ANSWER_COUNT", rightAnswerCount);
                    startActivity(intent);

                } else {
                    quizCount++;
                    showNextQuiz();
                }
            }
        });
        builder.setCancelable(false);
        builder.show();
    }
}