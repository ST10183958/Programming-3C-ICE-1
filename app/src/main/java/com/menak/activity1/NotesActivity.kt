package com.menak.activity1
import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import com.menak.activity1.R.id.txtCountry


class NotesActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var adapter: CountryAdapter
    private val countryList = mutableListOf<String>(

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.notes_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)



           // val textdisplay = findViewById<TextView>(R.id.textView2)
            val noteinput = findViewById<EditText>(R.id.NoteInputField)

            val AddNoteBTN = findViewById<Button>(R.id.SaveNoteBTN)

            listView = findViewById(R.id.listView)

            adapter = CountryAdapter(countryList)
            listView.adapter = adapter

            val noteInput = findViewById<EditText>(R.id.NoteInputField)
            val addNoteBTN = findViewById<Button>(R.id.SaveNoteBTN)

            addNoteBTN.setOnClickListener {

                val text = noteInput.text.toString().trim()

                if (text.isNotEmpty()) {
                    countryList.add(text)      // Append to list
                    adapter.notifyDataSetChanged() // Refresh list
                    noteInput.text.clear()     // Clear input field
                }
            }

            insets
        }
    }

    inner class CountryAdapter(private val countries: MutableList<String>) :
        ArrayAdapter<String>(this, 0, countries) {

        @SuppressLint("MissingInflatedId")
        override fun getView(
            position: Int,
            convertView: android.view.View?,
            parent: android.view.ViewGroup
        ): android.view.View {

            val view = layoutInflater.inflate(R.layout.item_note, parent, false)

            val countryText = view.findViewById<TextView>(txtCountry)
            val deleteButton = view.findViewById<Button>(R.id.btnDelete)

            countryText.text = countries[position]

            deleteButton.setOnClickListener {
                countries.removeAt(position)
                notifyDataSetChanged()
            }

            return view
        }

    }
}