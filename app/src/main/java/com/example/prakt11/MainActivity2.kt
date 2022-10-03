package com.example.prakt11

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity2 : AppCompatActivity() {
        private var List: MutableList<Expenses> = mutableListOf()
        private lateinit var rv: RecyclerView
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main2)
            getExpense()
            val adapter = ExpensesRVAdapter(this, List)
            val rvListener = object : ExpensesRVAdapter.ItemClickListener{
                override fun onItemClick(view: View?, position: Int) {
                    val intent = Intent(this@MainActivity2, MainActivity::class.java)
                    intent.putExtra("pos", position)
                    var indexChanged = position
                    startActivity(intent)
                    Toast.makeText(this@MainActivity2, "position: $position", Toast.LENGTH_SHORT).show()
                }
            }
            adapter.setClickListener(rvListener)
            rv = findViewById(R.id.rv)
            rv.adapter = adapter
            rv.layoutManager = LinearLayoutManager(this)
        }
        private fun getExpense() {
            val preferences = getSharedPreferences("pref", MODE_PRIVATE)
            var json: String = ""
            if (!preferences.contains("json"))
            {
                return
            } else {
                json = preferences.getString("json", "NOT_JSON").toString()
            }
            val temp = Gson().fromJson<List<Expenses>>(json, object: TypeToken<List<Expenses>>(){}.type)
            List.addAll(temp)
        }
    }