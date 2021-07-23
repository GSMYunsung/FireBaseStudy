package com.example.firebasesimpleapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.firebasesimpleapp.databinding.ActivityBoardListBinding
import com.example.firebasesimpleapp.databinding.ActivityMainBinding

class BoardListActivity : AppCompatActivity() {

    private lateinit var binding : ActivityBoardListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_list)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_board_list)

        binding.writeBtn.setOnClickListener {
            startActivity(Intent(this,BoardWriteActivity::class.java))
        }
    }
}