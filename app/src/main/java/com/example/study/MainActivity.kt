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

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // val:
        // var:

        val tv: TextView = findViewById(R.id.tv_hello) // tv_hello라는 이름을 가진 위젯을 변수 tv로 명명
        // tv.text = "안녕" // 위젯 내용을 '안녕'으로 변경함

        val btn: Button = findViewById(R.id.btn_kor) // btn_kor라는 이름을 가진 위젯을 변수 btn으로 명명
        btn.setOnClickListener { // 버튼을 누르면
            tv.text = "안녕" // hello -> '안녕'으로 변경.
        }


        /*
        textview(hello)변경 방법

        val textView: TextView = findViewById(R.id.android_text) as TextView
        textView.setOnClickListener { // 클릭이 되었을때
            textView.text = getString(R.string.name) // 해당 동작을 해라
        }
        */

    }
}