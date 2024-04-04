package com.example.a4_2

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.view.MotionEvent


class MainActivity : AppCompatActivity() {
    lateinit var btn1: Button
    lateinit var btn2: Button
    lateinit var btn3: Button

    var buttonIndex = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "예제 2번"

        btn1 = findViewById<Button>(R.id.Btn1)
        btn2 = findViewById<Button>(R.id.Btn2)
        btn3 = findViewById<Button>(R.id.Btn3)

        // 초기에는 첫 번째 버튼만 보이도록 설정
        btn1.visibility = View.VISIBLE

        btn1.setOnTouchListener { _, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                // 버튼 1을 누르면 버튼 2를 보이도록 설정
                btn1.visibility = View.INVISIBLE
                btn2.visibility = View.VISIBLE
            }
            false
        }

        btn2.setOnTouchListener { _, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                // 버튼 2를 누르면 버튼 3을 보이도록 설정
                btn2.visibility = View.INVISIBLE
                btn3.visibility = View.VISIBLE
            }
            false
        }

        btn3.setOnTouchListener { _, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                // 버튼 3을 누르면 다시 버튼 1을 보이도록 설정 (순환)
                btn3.visibility = View.INVISIBLE
                btn1.visibility = View.VISIBLE
            }
            false
        }
    }
}
