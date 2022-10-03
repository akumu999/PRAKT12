package com.example.prakt11

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExpensesRVAdapter(context: Context?, val data:MutableList<Expenses>) : RecyclerView.Adapter<ExpensesRVAdapter.ExpenseViewHolder>() {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val view: View = layoutInflater.inflate(R.layout.activity_main3, parent, false)
        return ExpenseViewHolder(view)
    }

    private var iClickListener: ItemClickListener? = null

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val item = data[position]
        holder.name.text = item.name
        holder.cost.text = item.cost.toString()
    }

    override fun getItemCount(): Int = data.size

    inner class ExpenseViewHolder(item: View) : RecyclerView.ViewHolder(item), View.OnClickListener{
        var name: TextView = item.findViewById(R.id.tvName)
        var cost: TextView = item.findViewById(R.id.tvCost)
        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(view: View?){
            iClickListener?.onItemClick(view, adapterPosition)
        }
    }

    fun setClickListener(itemClickListener: ItemClickListener?){
        iClickListener = itemClickListener
    }
    interface ItemClickListener{
        fun onItemClick(view: View?, position: Int)
    }
}
