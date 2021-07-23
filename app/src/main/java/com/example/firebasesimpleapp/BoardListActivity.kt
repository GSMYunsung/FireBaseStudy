package com.example.firebasesimpleapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.firebasesimpleapp.databinding.ActivityBoardListBinding
import com.example.firebasesimpleapp.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class BoardListActivity : AppCompatActivity() {

    lateinit var ListViewAdapter : ListViewAdapter
    val list = mutableListOf<DataModel>()

    private lateinit var binding : ActivityBoardListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_list)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_board_list)

        binding.writeBtn.setOnClickListener {
            startActivity(Intent(this,BoardWriteActivity::class.java))
        }

        ListViewAdapter = ListViewAdapter(list)
        binding.listview.adapter = ListViewAdapter

        getData()
    }

    fun getData(){
        val database = Firebase.database
        //저장되는 경로
        val myRef = database.getReference("board")

        val postListener = object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                for(dataModel in dataSnapshot.children){
                    val item = dataModel.getValue(DataModel::class.java)
                    list.add(item!!)
                }
                ListViewAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("BoardWriteActivity", "loadPost:onCancelled", databaseError.toException())
            }
        }
        myRef.addValueEventListener(postListener)
    }
}