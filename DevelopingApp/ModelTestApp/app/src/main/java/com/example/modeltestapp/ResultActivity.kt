package com.example.modeltestapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_result.*
import kotlinx.android.synthetic.main.list_item.*

class ResultActivity : AppCompatActivity() {

    //データを用意
    val names = listOf("あじさい", "蓮", "ネモフィラ", "バラ", "ふじ")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        //全画面へ戻る
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "検索結果"

        val adapter = PropertyListAdapter(this, properties = property)
        val listView = findViewById<ListView>(R.id.result_list_view)
        listView.adapter = adapter
    }


    data class PropertyData(val price: String)
    val property = List(names.size) { i -> PropertyData(price = names[i])}

    //ViewHolderの定義
    data class ViewHolder(val price_text_view: TextView)

    class PropertyListAdapter(context: Context, properties: List<PropertyData>): ArrayAdapter<PropertyData>(context, 0, properties) {
        private val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            var view = convertView
            var holder: ViewHolder

            if (view == null) {
                view = layoutInflater.inflate(R.layout.list_item, parent, false)
                val price_text_view = view.findViewById<TextView>(R.id.price_text_view)
                holder = ViewHolder(price_text_view = price_text_view)
                view.tag = holder
            } else {
                holder = view.tag as ViewHolder
            }

            val property = getItem(position) as PropertyData
            holder.price_text_view.text = property.price

            return view!!
        }
    }

    //前画面へ戻る
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }
}
