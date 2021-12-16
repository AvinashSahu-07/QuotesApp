package com.example.quotesapp

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

class MainViewModel (val context: Context): ViewModel() {

    //declare variable quoteList which is of array type, and it is an empty array for now
    private var quoteList: Array<Quote> = emptyArray()
    private var index = 0

    //initialize quoteList to a function which returns Array of Quote implementing below
    init {
        quoteList = loadQuoteFromAssets()
    }

    private fun loadQuoteFromAssets(): Array<Quote> {

        //read quotes from assets folder.Any information we pass in mainViewModel, we need factory so we need to pass context of our app.

        val inputStream = context.assets.open("quotes")    //It Opens file which is in asset folder by using context
        val size: Int = inputStream.available()                    //It defines our file size by using inputStream
        val buffer = ByteArray(size)                               //create buffer which stores our filesize which is of byteArray type
        inputStream.read(buffer)                                   //read file
        inputStream.close()

        val json = String(buffer, Charsets.UTF_8)                   //Coverts Bytearray of our file into Strings
        val gson = Gson()
        return gson.fromJson(json, Array<Quote>::class.java)
    }

    fun getQuote() = quoteList[index]                              //Return quotes on the basis of Current index from Array of Quotes

    fun nextQuote() = quoteList[index++ % quoteList.size]         //

    fun previousQuote() = quoteList[(index-- + quoteList.size) % quoteList.size] //
}