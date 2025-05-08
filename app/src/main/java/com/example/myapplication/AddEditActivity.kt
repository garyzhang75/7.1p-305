package com.example.myapplication

import android.os.Bundle
import android.widget.RadioButton
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityAddEditBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddEditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddEditBinding
    private val viewModel: LostFoundViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val itemId = intent.getIntExtra("item_id", -1)
        var editingItem: LostFoundItem? = null

        if (itemId != -1) {
            CoroutineScope(Dispatchers.Main).launch {
                editingItem = viewModel.getItemById(itemId)
                editingItem?.let { item ->
                    binding.editTextName.setText(item.name)
                    binding.editTextPhone.setText(item.phone)
                    binding.editTextDescription.setText(item.description)
                    binding.editTextDate.setText(item.date)
                    binding.editTextLocation.setText(item.location)
                    if (item.type == "Lost") {
                        binding.radioLost.isChecked = true
                    } else {
                        binding.radioFound.isChecked = true
                    }
                }
            }
        }

        binding.btnSave.setOnClickListener {
            val name = binding.editTextName.text.toString()
            val phone = binding.editTextPhone.text.toString()
            val description = binding.editTextDescription.text.toString()
            val date = binding.editTextDate.text.toString()
            val location = binding.editTextLocation.text.toString()
            val type = findViewById<RadioButton>(binding.radioGroupPostType.checkedRadioButtonId).text.toString()

            val item = LostFoundItem(
                id = editingItem?.id ?: 0,
                name = name,
                phone = phone,
                description = description,
                date = date,
                location = location,
                type = type
            )

            CoroutineScope(Dispatchers.IO).launch {
                if (editingItem == null) {
                    viewModel.insert(item)
                } else {
                    viewModel.update(item)
                }
                finish()
            }
        }
    }
}
