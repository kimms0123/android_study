package com.example.study

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Timer
import kotlin.concurrent.timer

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        // 변수 선언
        var timerTask: Timer? = null // ? =null: nullable => null 을 허용하는 문자열
        var sec : Int = 0 // 초(sec) 변수
        val tv: TextView = findViewById(R.id.tv_hello) // text 변수
        val btn: Button = findViewById(R.id.btn_kor) // btn 변수
        var isRunning = false

        btn.setOnClickListener { // 버튼을 누르면
            isRunning = !isRunning // isRunning = true -> 초 진행
            if(isRunning == true) {
                timerTask = timer(period = 1000) { // 타이머(시간관련) 기능 1000밀리초 = 1초
                    sec++ // sec 안에 있는 0이 1씩 늘어남
                    runOnUiThread { // 실시간 화면 처리
                        tv.text = sec.toString()
                    }
                }
            }
            else {
                timerTask?.cancel() // ?.: null 이면 그냥 넘어 가고, 아니면 함수 실행
            }
        }

    }
}