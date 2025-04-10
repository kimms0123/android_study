package com.example.practice1

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Timer
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // val:
        // var:

        // val tv: TextView = findViewById(R.id.tv_hello) // tv_hello라는 이름을 가진 위젯을 변수 tv로 선언
        // tv.text = "안녕" // 위젯 내용을 '안녕'으로 변경함

//        val btn: Button = findViewById(R.id.btn_kor) // btn_kor라는 이름을 가진 위젯을 변수 btn으로 선언
//        btn.setOnClickListener { // 버튼을 누르면
//            tv.text = "안녕" // hello -> '안녕'으로 변경
//        }


        /*
        textview(hello)변경 방법

        val textView: TextView = findViewById(R.id.android_text) as TextView
        textView.setOnClickListener { // 클릭이 되었을때
            textView.text = getString(R.string.name) // 해당 동작을 해라
        }
        */

        // 변수 선언
//        var timerTask: Timer? = null // ? =null: nullable => null 을 허용하는 문자열
//        var sec : Int = 0 // 초(sec) 변수
//        val tv: TextView = findViewById(R.id.tv_hello) // text 변수
//        val btn: Button = findViewById(R.id.btn_kor) // btn 변수
//        var isRunning = false
//
//        btn.setOnClickListener { // 버튼을 누르면
//            isRunning = !isRunning // isRunning = true -> 초 진행
//            if(isRunning == true) {
//                timerTask = timer(period = 1000) { // 타이머(시간관련) 기능 1000밀리초 = 1초
//                    sec++ // sec 안에 있는 0이 1씩 늘어남
//                    runOnUiThread { // 실시간 화면 처리
//                        tv.text = sec.toString()
//                    }
//                }
//            }
//            else {
//                timerTask?.cancel() // ?.: null 이면 그냥 넘어 가고, 아니면 cancel() 함수 실행
//            }
//        }

        var timerTask: Timer? = null // ? =null: nullable => null 을 허용하는 문자열
        var time: Int = 0 // 초(sec) 변수
        val tv: TextView = findViewById(R.id.tv_random) // text 변수
        val btn: Button = findViewById(R.id.btn_kor) // 리셋 변수
        val start: Button = findViewById(R.id.btn_start) // 시작 변수 선언
        val sectxt: TextView = findViewById(R.id.sectxt) // 0.1초 변수
        val millitxt: TextView = findViewById(R.id.millitxt) // 0.001초 변수
        var isRunning = false

        start.setOnClickListener {
            isRunning = !isRunning
            if(isRunning == true) {
                // 타이머 진행 부분
                timerTask = timer(period = 10) {
                    time++
                    val sec = time / 100	// time/100, 나눗셈의 몫 (초 부분)
                    val milli = (time % 100) / 10	// time%100, 나눗셈의 나머지 (0.1초 부분)
                    val milli2 = time % 10 // 0.001초 부분

                    // 화면 실시간 진행부분
                    runOnUiThread {
                        tv.text = sec.toString()
                        sectxt.text = milli.toString()
                        millitxt.text = milli2.toString()
                    }
                }
                // 버튼 변경
                start.text = "일시정지" // 스타트 클릭 후 일시정지로 변경
            } else {
                // 일시 정지 부분
                timerTask?.cancel()
                // 버튼 변경
                start.text = "스타트" // 일시정지 클릭 후 스타트로 변경
            }
        }

        // 초기화 부분
        btn.setOnClickListener{
            timerTask?.cancel()
            time = 0 // 초 변수 초기화
            isRunning = false // 현재 진행중인지 판별을 위한 false
            tv.text = "0" // text 변수 초기화
            sectxt.text = "0"
            millitxt.text = "0"
        }
    }
}