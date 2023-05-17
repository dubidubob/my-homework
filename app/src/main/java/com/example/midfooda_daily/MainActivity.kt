package com.example.midfooda_daily

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dailytimeline)
        val textDate = findViewById<View>(R.id.textDate) as TextView
        val btnBack = findViewById<View>(R.id.gotoBack) as ImageButton
        btnBack.setOnClickListener {
            //이곳에 버튼을 눌렀을 때 동작 설정
            //달력의 날짜 -- 되고, 정수로 구하기
            textDate.text = "2023-03-02"
            //해당 날짜의 data 가져오는 함수
        }
        val btnFront = findViewById<View>(R.id.gotoFront) as ImageButton
        btnFront.setOnClickListener {
            //이곳에 버튼을 눌렀을 때 동작 설정
            //달력의 날짜 ++ 되고
            textDate.text = "2023-03-03"
            //해당 날짜의 data 가져오는 함수
    }
}
}