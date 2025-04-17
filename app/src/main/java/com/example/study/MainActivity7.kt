package com.example.study

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import java.util.Timer
import kotlin.concurrent.timer
import kotlin.math.abs


class MainActivity7 : AppCompatActivity() {
    // 명 수 제한
    var p_num = 1
    var k = 1
    val point_list = mutableListOf<Float>()
    var isBlind = false

    // 스타트 화면
    fun start(){
        setContentView(R.layout.activity_start3)
        val tv_pnum : TextView = findViewById(R.id.tv_pnum)
        val btn_plus : Button = findViewById(R.id.btn_plus)
        val btn_mius : Button = findViewById(R.id.btn_mius)
        val btn_start : Button = findViewById(R.id.btn_start)
        val btn_blind : Button = findViewById(R.id.btn_blind)

        btn_blind.setOnClickListener{
            isBlind = !isBlind
            if(isBlind == true) {
                btn_blind.text = "Blind Mode On"
            } else {
                btn_blind.text = "Blind Mode Off"
            }
        }

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
        setContentView(R.layout.activity_main7)

        // 변수 선언
        var timerTask: Timer? = null // ? =null: nullable => null 을 허용하는 문자열
        var stage = 1
        var sec : Int = 0 // 초(sec) 변수
        val tv: TextView = findViewById(R.id.tv_hello) // text 변수
        val tv_t: TextView = findViewById(R.id.tv_timer) // text 변수
        val tv_p: TextView = findViewById(R.id.tv_point) // text 변수
        val tv_people: TextView = findViewById(R.id.tv_people) // text 변수
        val btn: Button = findViewById(R.id.btn_main) // btn 변수
        val btn_i: Button = findViewById(R.id.btn_i) // btn 변수
        val random_box = java.util.Random() // Random(): 수를 랜덤으로 불러옴
        val num = random_box.nextInt(1001) // 0 ~ 1000 사이 수 반환

        // 배경 화면 변경
        val bg_main : ConstraintLayout = findViewById(R.id.bg_main)
        val color_list = mutableListOf<String>("#F65959","#F69359","#F6E159","#76F659","#5998F6","#21317A","#7E61C8")

        var color_index = k % 7 - 1
        if(color_index == -1) {
            color_index = 6
        } // color_sel에서 k % 7 - 1로 인덱스에 있는 값을 꺼내올 경우 마지막 부분에서 7 % 7 - 1 = -1로 오류가 나서 위와 같은 경우를 추가해줌

        val color_sel = color_list.get(color_index)
        // 배경 화면 색상 설정
        bg_main.setBackgroundColor(Color.parseColor(color_sel))

        tv.text = ((num.toFloat())/100).toString()

        btn.text = "시작"
        tv_people.text = "참가자 $k"

        btn_i.setOnClickListener{
            point_list.clear()
            k = 1
            p_num = 1

            start()
        }

        // 타이머
        btn.setOnClickListener { // 버튼을 누르면
            stage++
            if(stage == 2) {
                timerTask = timer(period = 10) { // 타이머(시간관련) 기능 1000밀리초 = 1초
                    sec++ // sec 안에 있는 0이 1씩 늘어남
                    runOnUiThread { // 실시간 화면 처리
                        // 현재 시간 가림
                        if(isBlind == false && stage == 2){
                            tv_t.text = (sec.toFloat()/100).toString()
                        } else if(isBlind == true) {
                            tv_t.text = "???"
                        }

                    }
                }
                btn.text = "정지"
            }
            else if(stage == 3) {
                tv_t.text = (sec.toFloat()/100).toString() // 현재 시간 표시

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
        setContentView(R.layout.activity_end2)

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