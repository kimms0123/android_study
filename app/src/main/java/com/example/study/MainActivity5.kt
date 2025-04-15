package com.example.study

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Timer
import kotlin.concurrent.timer
import kotlin.math.abs


class MainActivity5 : AppCompatActivity() {
    // 명 수 제한
    var p_num = 1
    var k = 1
    val point_list = mutableListOf<Float>()

    // 스타트 화면
    fun start(){
        setContentView(R.layout.activity_start)
        val tv_pnum : TextView = findViewById(R.id.tv_pnum)
        val btn_plus : Button = findViewById(R.id.btn_plus)
        val btn_mius : Button = findViewById(R.id.btn_mius)
        val btn_start : Button = findViewById(R.id.btn_start)

        tv_pnum.text = p_num.toString() // 인원 표시

        btn_mius.setOnClickListener{
            p_num--
            if(p_num == 0) { // 최소 인원 수 설정
                p_num = 1 // 최수 인원수: 1
            }
            tv_pnum.text = p_num.toString() // 인원 표시
        }
        btn_plus.setOnClickListener{
            p_num++
            tv_pnum.text = p_num.toString() // 인원 표시
        }
        btn_start.setOnClickListener{
            main()
        }

    }

    fun main() {
        setContentView(R.layout.activity_main5)

        // 변수 선언
        var timerTask: Timer? = null // ? =null: nullable => null 을 허용하는 문자열
        var stage = 1
        var sec : Int = 0 // 초(sec) 변수
        val tv: TextView = findViewById(R.id.tv_hello) // text 변수
        val tv_t: TextView = findViewById(R.id.tv_timer) // text 변수
        val tv_p: TextView = findViewById(R.id.tv_point) // text 변수
        val tv_people: TextView = findViewById(R.id.tv_people) // text 변수
        val btn: Button = findViewById(R.id.btn_main) // btn 변수
        val random_box = java.util.Random() // Random(): 수를 랜덤으로 불러옴
        val num = random_box.nextInt(1001) // 0 ~ 1000 사이 수 반환

        tv.text = ((num.toFloat())/100).toString()

        btn.text = "시작"
        tv_people.text = "참가자 $k"

        // 타이머
        btn.setOnClickListener { // 버튼을 누르면
            stage++
            if(stage == 2) {
                timerTask = timer(period = 10) { // 타이머(시간관련) 기능 1000밀리초 = 1초
                    sec++ // sec 안에 있는 0이 1씩 늘어남
                    runOnUiThread { // 실시간 화면 처리
                        tv_t.text = (sec.toFloat()/100).toString()
                    }
                }
                btn.text = "정지"
            }
            else if(stage == 3) {
                timerTask?.cancel() // ?.: null 이면 그냥 넘어 가고, 아니면 cancel() 함수 실행
                val point = abs(sec-num).toFloat()/100
                point_list.add(point)

                tv_p.text = point.toString()
                btn.text = "다음"
                stage = 0
            } else if (stage == 1){
                if(k < p_num) {
                    k++
                    main() // 재귀함수
                } else {
                    end()
                }
            }
        }
    }

    fun end() {
        setContentView(R.layout.activity_end)

        val tv_last : TextView = findViewById(R.id.tv_last)
        val tv_lpoint : TextView = findViewById(R.id.tv_lpoint)
        val btn_init : Button = findViewById(R.id.btn_init)

        tv_lpoint.text = (point_list.maxOrNull()).toString() // list 최대값 maxOrNull:
        var index_last = point_list.indexOf(point_list.maxOrNull()) // 인덱스 번째 사람 표시
        tv_last.text = "참가자 " + (index_last + 1).toString()

        btn_init.setOnClickListener{
            // 초기화
            point_list.clear()
            k = 1
            p_num = 1
            start()

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        start()
    }
}