package com.awe.apitest1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.awe.apitest1.databinding.ActivityMainBinding
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    lateinit var bindings : ActivityMainBinding
    var cards : Cards? = null
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: CardsAdapter
    lateinit var textToSearch : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindings = ActivityMainBinding.inflate(layoutInflater)
        val view = bindings.root
        setContentView(view)

        recyclerView = findViewById(R.id.cardRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        adapter = CardsAdapter(cards,this@MainActivity)
        recyclerView.adapter = adapter

        bindings.buttonSearch.setOnClickListener {
            textToSearch = bindings.textEntered.text.toString()
            getCards(textToSearch)
        }
    }

    fun getCards(text : String)
    {
        val url = "https://api.pokemontcg.io/v2/cards?q=name:\"*${text}*\""
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            var mainHandler = Handler(this@MainActivity.getMainLooper())
            override fun onResponse(call: Call, response: Response) {
                mainHandler.post {
                    val body = response.body?.string()
                    if (body == null) return@post

                    val gson = GsonBuilder().create()
                    cards = gson.fromJson(body, Cards::class.java)
                    println(cards?.count)
                    adapter = CardsAdapter(cards,this@MainActivity)
                    recyclerView.adapter = adapter

                    adapter.onItemClick = {
                        val intent = Intent(this@MainActivity, CardDetailsActivity::class.java)
                        var pos : Int = adapter.pos
                        intent.putExtra("name", cards?.data?.get(pos)?.name)
                        intent.putExtra("set", cards?.data?.get(pos)?.set?.name)
                        intent.putExtra("rarity", cards?.data?.get(pos)?.rarity)
                        intent.putExtra("updatedAt", cards?.data?.get(pos)?.tcgplayer?.updatedAt)
                        intent.putExtra("marketNormal", cards?.data?.get(pos)?.tcgplayer?.prices?.normal?.market)
                        intent.putExtra("marketHolo", cards?.data?.get(pos)?.tcgplayer?.prices?.holofoil?.market)
                        intent.putExtra("marketReverseHolo", cards?.data?.get(pos)?.tcgplayer?.prices?.reverseHolofoil?.market)
                        intent.putExtra("image", cards?.data?.get(pos)?.images?.small)
                        startActivity(intent)
                    }
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                println("API execute failed")
            }

        })
    }
}