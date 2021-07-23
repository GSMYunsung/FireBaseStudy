package com.example.firebasesimpleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firebasesimpleapp.databinding.ActivityBoardListBinding
import com.example.firebasesimpleapp.databinding.ActivityBoardWriteBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class BoardWriteActivity : AppCompatActivity() {

    private lateinit var binding : ActivityBoardWriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_write)

        binding.writeBtn.setOnClickListener {
            // Write a message to the database
            val database = Firebase.database
            //저장되는 경로
            val myRef = database.getReference("board")

            //데이터 하나로 업데이트
            //myRef.setValue(binding.writeEdit.text.toString())

            //서로별도의 데이터 넣기
            //문서의 고유한 키값과 데이터값이 들어간다.
            //myRef.push().setValue(binding.writeEdit.text.toString())

            //데이터 모델 형태로 데이터 넣기
            myRef.push().setValue(
                DataModel(binding.writeEdit.text.toString())
            )
        }
    }
}