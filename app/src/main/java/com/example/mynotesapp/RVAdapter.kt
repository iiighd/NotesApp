package com.example.mynotesapp

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotesapp.databinding.ItemRowBinding

class RVAdapter(private val activity: MainActivity ):RecyclerView.Adapter<RVAdapter.ItemViewHolder>() {
    private var noteList = emptyList<Notes>()
    lateinit var editbtn : ImageButton
    lateinit var removebtn : ImageButton
    lateinit var dbH : DBhelper


    class ItemViewHolder(val binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val Notetext = noteList[position]

        holder.binding.apply {
            var noteData = "- ${Notetext.data}"
            notesTV.setText("$noteData")

            //edit setup
            btRemove.setOnClickListener {
                activity.selectedNotes = Notetext
                activity.uploadText()
                dbH.deletData(Notes(noteList.indexOf(Notetext) , Notetext.toString()))
                Toast.makeText(activity, "$Notetext is removed", Toast.LENGTH_LONG).show()
                dbH.updateData()
            }

        }

    }

    override fun getItemCount() = noteList.size

    fun update(dataList: ArrayList<Notes>){
        this.noteList = dataList
        notifyDataSetChanged()
    }
}