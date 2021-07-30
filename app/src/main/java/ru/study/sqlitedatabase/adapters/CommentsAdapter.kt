package ru.study.sqlitedatabase.adapters

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.study.sqlitedatabase.R
import ru.study.sqlitedatabase.models.Comments
import ru.study.sqlitedatabase.models.Post

class CommentsAdapter(private val comments: List<Comments>): RecyclerView.Adapter<CommentsAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val email = view.findViewById<TextView>(R.id.email)
        val comment = view.findViewById<TextView>(R.id.comment)
        fun bind(comment: Comments) {
            email.text = comment.email
            this.comment.text = comment.comment
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.comment_adapter_element, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(comments[position])
    }

    override fun getItemCount(): Int {
        return comments.size
    }
}
