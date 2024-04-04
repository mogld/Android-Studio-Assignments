package com.example.a20240327

import android.view.MotionEvent


import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {
    lateinit var btnChanger : Button
    lateinit var linLayer : LinearLayout

    var colors = arrayOf(Color.RED, Color.GREEN, Color.BLUE)
    var colorIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "예제 1"

        // XML 레이아웃 요소 초기화
        btnChanger = findViewById(R.id.ChangeBtn)
        linLayer = findViewById(R.id.LinLay)

        // 버튼 터치 리스너 설정
        btnChanger.setOnTouchListener { _, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                colorIndex = (colorIndex + 1) % colors.size
                linLayer.setBackgroundColor(colors[colorIndex])
            }
            false

        }
    }
}
