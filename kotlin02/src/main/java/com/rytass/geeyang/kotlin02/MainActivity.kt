package com.rytass.geeyang.kotlin02

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {
    var price: Float? = null
        set(value) {
            field = when {
                value == null -> null
                value!! < 0f -> 0f
                else -> value
            }

            updatePercentage()
            updatePrice()
        }

    var percentage: Int = 0
        set(value) {
            field = when {
                value < 0 -> 0
                value > 100 -> 100
                else -> value
            }

            updatePercentage()
            updatePrice()
        }

    private fun updatePrice() {
        discount.text = String.format("%.2f", (price?.times(100 - percentage)?.div(100f)))
        priceLabel.text = String.format("%.2f", (price?.times(percentage)?.div(100f)))
    }

    private fun updatePercentage() {
        discountHint.text = "打折 ($percentage%)"
        priceHint.text = "打折後價格"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initial()
        setListeners()
    }

    fun initial() {
        percentage = 0
    }

    fun setListeners() {
        input.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                val value = p0.toString()?.toFloatOrNull()

                price = value
            }
        })

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }

            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                percentage = p1
            }
        })

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
