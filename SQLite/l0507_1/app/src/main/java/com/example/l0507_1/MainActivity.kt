package com.example.l0507_1

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val databaseName = "people"
    private var database: SQLiteDatabase? = null
    private lateinit var outputTextView: TextView
    private val tableName = "student"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        outputTextView = findViewById(R.id.output1)

        findViewById<Button>(R.id.doButton4).setOnClickListener {
            createDatabase()
        }

        findViewById<Button>(R.id.doButton5).setOnClickListener {
            createTable()
        }

        findViewById<Button>(R.id.doButton6).setOnClickListener {
            addData()
        }

        findViewById<Button>(R.id.doButton2).setOnClickListener {
            queryData()
        }

        findViewById<Button>(R.id.doButton1).setOnClickListener {
            updateData()
        }

        findViewById<Button>(R.id.doButton3).setOnClickListener {
            deleteData()
        }
    }

    private fun createDatabase() {
        database = openOrCreateDatabase(databaseName, MODE_PRIVATE, null)
        outputTextView.append("데이터베이스 생성 또는 오픈함\n")
    }

    private fun checkDatabase(): Boolean {
        if (database == null) {
            outputTextView.append("데이터베이스를 먼저 오픈하세요.\n")
            return true
        }
        return false
    }

    private fun createTable() {
        val sql = "CREATE TABLE IF NOT EXISTS $tableName (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, age INTEGER, mobile TEXT)"
        if (checkDatabase()) return
        database?.execSQL(sql)
        outputTextView.append("테이블 생성함\n")
    }

    private fun addData() {
        val sql = "INSERT INTO $tableName (name, age, mobile) VALUES ('john', '20', '010-0000-0000')"
        if (checkDatabase()) return
        database?.execSQL(sql)
        outputTextView.append("데이터 추가함\n")
    }

    private fun queryData() {
        val sql = "SELECT _id, name, age, mobile FROM $tableName"
        if (checkDatabase()) return
        val cursor: Cursor? = database?.rawQuery(sql, null)
        if (cursor != null) {
            for (index in 0 until cursor.count) {
                cursor.moveToNext()
                val id = cursor.getInt(0)
                val name = cursor.getString(1)
                val age = cursor.getInt(2)
                val mobile = cursor.getString(3)
                outputTextView.append("레코드#$index : $id, $name, $age, $mobile\n")
            }
            cursor.close()
        }
        outputTextView.append("데이터 조회 결과\n")
    }

    private fun updateData() {
        val values = ContentValues().apply {
            put("age", 26)
        }
        val whereClause = "name=?"
        val whereArgs = arrayOf("john")
        if (checkDatabase()) return
        database?.update(tableName, values, whereClause, whereArgs)
        outputTextView.append("데이터 수정함\n")
    }

    private fun deleteData() {
        if (checkDatabase()) return
        val sql = "SELECT _id FROM $tableName"
        val cursor: Cursor? = database?.rawQuery(sql, null)
        if (cursor != null) {
            val count = cursor.count
            cursor.close()
            val deleteSql = "DELETE FROM $tableName WHERE _id=$count"
            database?.execSQL(deleteSql)
            outputTextView.append("데이터 삭제함\n")
        }
    }
}
