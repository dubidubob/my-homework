package com.example.midfooda_daily;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;

public class demo extends Activity {

    DatePickerDialog datePickerDialog;//이건 무조건 outer에 있어야 한다.
    Boolean flagFinal=false;
    AlertDialog dialog;
    LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dailytimeline);

    //textDate--
        TextView textDate=(TextView)findViewById(R.id.textDate);
        ImageButton btnBack= (ImageButton) findViewById(R.id.gotoBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //이곳에 버튼을 눌렀을 때 동작 설정
                //달력의 날짜 -- 되고, 정수로 구하기
                textDate.setText("2023-3-2");
                //해당 날짜의 data 가져오는 함수
            }
        });
    //textDate++
        ImageButton btnFront= (ImageButton) findViewById(R.id.gotoFront);
        btnFront.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //이곳에 버튼을 눌렀을 때 동작 설정
                //달력의 날짜 ++ 되고
                textDate.setText("2023-3-3");
                //해당 날짜의 data 가져오는 함수
            }
        });
    //textDate reset
        ImageButton btnCalendar=(ImageButton) findViewById(R.id.calendarPopup);
        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //오늘 날짜(년, 월, 일) 변수에 담기
                Calendar calendar= Calendar.getInstance();
                int pYear=calendar.get(Calendar.YEAR);
                int pMonth=calendar.get(Calendar.MONTH);
                int pDay=calendar.get(Calendar.DAY_OF_MONTH);

                datePickerDialog=new DatePickerDialog(demo.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        //1월은 0부터 시작하기 때문에 +1을 해준다.
                        month=month+1;
                        String date=year+"-"+month+"-"+day;
                        textDate.setText(date);
                    }
                }, pYear, pMonth, pDay);
                datePickerDialog.show();

                //해당 날짜의 data 가져오는 함수
            }//onClick
        });

    //finalreview button gone
        Button btnFinal=(Button) findViewById(R.id.finalReviewButton);
        TextView textFinal=(TextView) findViewById(R.id.realFinalReview);
        btnFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //처음에는 버튼 +, 누르면 총평 작성 페이지로 넘어간다.
                //그리고 final은 없앤다.
                btnFinal.setVisibility(View.GONE);
                textFinal.performClick();
            }
        });
    //finalreview view come
        textFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flagFinal==false){
                    //flag true라면 해당 DB에 있는 내용 text를 조금만 보여준다(text 변수 따로 만들 것.)
                    textFinal.setText("밤은 다시 길고 깊어졌네\n" +
                            "나는 점점 너로 잠 못 들게 돼...");
                    flagFinal=true;
                }else{
                    //flag true라면 해당 DB에 있는 내용 text를 보여준다.
                    //아래는 예시
                    textFinal.setText("밤은 다시 길고 깊어졌네\n" +
                            "나는 점점 너로 잠 못 들게 돼\n" +
                            "글로 적어내긴 어려운 이 기분을\n" +
                            "너도 느꼈으면 좋겠는데\n" +
                            "너는 아무 생각 없이 몇 번\n" +
                            "나를 지나가며 웃은 거라지만\n" +
                            "나의 하얀 옷에 너의 잉크가 묻어\n" +
                            "닦아낼 수 없을 만큼 번졌네\n" +
                            "달콤한 색감이 물들어 조금씩\n" +
                            "정신을 차렸을 땐 알아볼 수도 없지\n" +
                            "가득 찬 마음이 여물다 못해\n" +
                            "터지고 있어\n" +
                            "내일은 말을 걸어봐야지");
                    flagFinal=false;
                }
            }
        });

    //adding new daily
        ImageButton addNew=(ImageButton) findViewById(R.id.addDaily);
        layout=(LinearLayout) findViewById(R.id.container);

        buildDialog();
        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });


    }//onCreate

    private void buildDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        View view=getLayoutInflater().inflate(R.layout.dailydialog, null);

        EditText ex=(EditText) view.findViewById(R.id.exEdit);

        builder.setView(view);
        builder.setTitle("Enter Title")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        addCard(ex.getText().toString());
                    }
                })
                .setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {

                    }
                });
        dialog=builder.create();
    }

    private void addCard(String ex) {
        View view=getLayoutInflater().inflate(R.layout.dailyfeedcard, null);

        TextView ex2=(TextView) view.findViewById(R.id.titleday);
        ex2.setText(ex);

        layout.addView(view);

    }

    ;
}//demo
