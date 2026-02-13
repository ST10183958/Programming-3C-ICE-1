package com.menak.activity1
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Spinner
import android.widget.Toast
import android.widget.ArrayAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.ListView


class LinksActivity : AppCompatActivity() {

    data class LinkItem(
        val title: String,
        val url: String,
        val category: String
    )
    private val linkList = mutableListOf<LinkItem>()
    private lateinit var adapter: LinkAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.links_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)

            val SpinnerId = findViewById<Spinner>(R.id.spinner)
            val titleInput = findViewById<EditText>(R.id.TitleInputField)
            val urlInput = findViewById<EditText>(R.id.URLInputField)
            val addBtn = findViewById<Button>(R.id.AddLinkBTN) // reuse button
            val listView = findViewById<ListView>(R.id.linksListView)

            val SpinnerOptions = arrayOf("C#", "Java", "Kotlin","React")
            val arrayAdp = ArrayAdapter(this@LinksActivity, android.R.layout.simple_spinner_item, SpinnerOptions)
            SpinnerId.adapter = arrayAdp


            adapter = LinkAdapter(linkList)
            listView.adapter = adapter



            addBtn.setOnClickListener {

                val title = titleInput.text.toString().trim()
                val url = urlInput.text.toString().trim()
                val category = SpinnerId.selectedItem.toString()

                if (title.isNotEmpty() && url.isNotEmpty()) {

                    linkList.add(LinkItem(title, url, category))
                    adapter.notifyDataSetChanged()

                    titleInput.text.clear()
                    urlInput.text.clear()

                } else {
                    Toast.makeText(this, "Enter title and URL", Toast.LENGTH_SHORT).show()
                }
            }


            insets
        }
    }
    inner class LinkAdapter(private val items: MutableList<LinkItem>) :
        ArrayAdapter<LinkItem>(this@LinksActivity, 0, items) {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

            val view = convertView ?: layoutInflater.inflate(R.layout.item_link, parent, false)

            val titleText = view.findViewById<TextView>(R.id.titleText)
            val urlText = view.findViewById<TextView>(R.id.urlText)
            val categoryText = view.findViewById<TextView>(R.id.categoryText)
            val deleteBtn = view.findViewById<Button>(R.id.deleteBtn)

            val item = items[position]

            titleText.text = item.title
            urlText.text = item.url
            categoryText.text = item.category

            deleteBtn.setOnClickListener {
                items.removeAt(position)
                notifyDataSetChanged()
            }

            return view
        }
    }
}