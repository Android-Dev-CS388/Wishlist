package com.example.wishlist

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.Integer.parseInt

class MainActivity : AppCompatActivity() {

    lateinit var items: MutableList<Item>
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        // Lookup the RecyclerView in activity layout
        val itemsRv = findViewById<RecyclerView>(R.id.listRv)
        // Fetch the list of emails
//        items = emptyList().toMutableList()
        items = mutableListOf()
        // Create adapter passing in the list of emails
        val adapter = ItemAdapter(items)
        // Attach the adapter to the RecyclerView to populate items
        itemsRv.adapter = adapter
        // Set layout manager to position the items
        itemsRv.layoutManager = LinearLayoutManager(this)

        findViewById<Button>(R.id.submitButton).setOnClickListener {

            val itemInput = findViewById<EditText>(R.id.itemInput)
            val priceInput = findViewById<EditText>(R.id.priceInput)
            val urlInput = findViewById<EditText>(R.id.storeInput)

            val itemName = itemInput.text.toString()
            val itemPrice = priceInput.text.toString().toInt()
            val itemUrl = urlInput.text.toString()
//            val newItem = Item(itemName, itemPrice, itemUrl)

            itemInput.setText("")
            priceInput.setText("")
            urlInput.setText("")

            var newItems : MutableList<Item> = ArrayList()
            val item = Item(itemName, itemPrice, itemUrl)
            newItems.add(item)
            items.addAll(newItems)

            adapter.notifyDataSetChanged()
        }
    }
}