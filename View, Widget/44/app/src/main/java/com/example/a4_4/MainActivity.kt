package com.example.a4_4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var edit1: EditText
    lateinit var edit2: EditText
    lateinit var btnAdd: Button
    lateinit var btnSub: Button
    lateinit var btnMul: Button
    lateinit var btnDiv: Button
    lateinit var textResult: TextView
    var num1: Int = 0
    var num2: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edit1 = findViewById(R.id.Edit1)
        edit2 = findViewById(R.id.Edit2)
        btnAdd = findViewById(R.id.BtnAdd)
        btnSub = findViewById(R.id.BtnSub)
        btnMul = findViewById(R.id.BtnMul)
        btnDiv = findViewById(R.id.BtnDiv)
        textResult = findViewById(R.id.TextResult)

        btnAdd.setOnClickListener {
            calculate('+')
        }

        btnSub.setOnClickListener {
            calculate('-')
        }

        btnMul.setOnClickListener {
            calculate('*')
        }

        btnDiv.setOnClickListener {
            calculate('/')
        }
    }

    private fun calculate(operator: Char) {
        val input1 = edit1.text.toString()
        val input2 = edit2.text.toString()

        if (input1.isEmpty() || input2.isEmpty()) {
            Toast.makeText(this, "두 개의 숫자를 입력하세요.", Toast.LENGTH_SHORT).show()
            return
        }

        num1 = input1.toInt()
        num2 = input2.toInt()

        val result = when (operator) {
            '+' -> num1 + num2
            '-' -> num1 - num2
            '*' -> num1 * num2
            '/' -> {
                if (num2 == 0) {
                    Toast.makeText(this, "0으로 나눌 수 없습니다.", Toast.LENGTH_SHORT).show()
                    return
                }
                num1 / num2
            }
            else -> throw IllegalArgumentException("올바르지 않은 연산자입니다.")
        }

        textResult.text = "결과: $result"
        edit1.setText(result.toString())
        edit2.setText("")
    }
}
