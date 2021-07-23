package com.example.firebasesimpleapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.firebasesimpleapp.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

//오늘 안 사실 : 파이어베이스에서는 비밀번호 6자 이상부터 비밀번호를 허용한다.

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    //파이어베이스 사용자 인증하기
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = Firebase.auth

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        //사용자의 계정을 회원가입시키는 방법
        Toast.makeText(this,auth.currentUser?.uid.toString(),Toast.LENGTH_SHORT).show()

        //생성을 함과 동시에 사용자의 고유한 UID 값이 생성된다. 그리고 그 UID 값으로 사용자의 정보를 확인 가능하다.
        binding.signupBtn.setOnClickListener {

            auth.createUserWithEmailAndPassword(binding.emailEdit.text.toString(),binding.passwordEidt.text.toString())
                .addOnCompleteListener(this) { task ->

                    if (task.isSuccessful) {
                        //성공시
                        Toast.makeText(this,"sign up successful",Toast.LENGTH_SHORT).show()
                    } else {
                        //실패시
                        Toast.makeText(this,"sign up fail",Toast.LENGTH_SHORT).show()
                    }
                }
        }

        //로그아웃
        binding.logoutBtn.setOnClickListener {
            auth.signOut()

            //현재 UID 값을 받아와보기
            Toast.makeText(this,auth.currentUser?.uid.toString(),Toast.LENGTH_SHORT).show()
        }

        //로그인
        binding.loginBtn.setOnClickListener {

            auth.signInWithEmailAndPassword(binding.emailEdit.text.toString(), binding.passwordEidt.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this,"sign in successful",Toast.LENGTH_SHORT).show()
                        Toast.makeText(this,auth.currentUser?.uid.toString(),Toast.LENGTH_SHORT).show()

                        startActivity(Intent(this,BoardListActivity::class.java))
                    } else {
                        Toast.makeText(this,"sign in fail",Toast.LENGTH_SHORT).show()
                    }
                }
        }

    }
}