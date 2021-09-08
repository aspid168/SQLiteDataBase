package ru.study.sqlitedatabase.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.study.sqlitedatabase.R
import ru.study.sqlitedatabase.models.Post

class PostsAdapter(private val posts: List<Post>, private val listener: (Int, Post) -> Unit ): RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

    class ViewHolder(
        view: View,
        private val listener: (Int, Post) -> Unit
    ): RecyclerView.ViewHolder(view) {
        private val title = view.findViewById<TextView>(R.id.title)
        private val email = view.findViewById<TextView>(R.id.email)
        private val description = view.findViewById<TextView>(R.id.description)

        fun bind(post: Post, position: Int) {
            title.text = post.title
            email.text = post.email
            description.text = post.description
            itemView.setOnClickListener {
                listener(position + 1, post)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.post_adapter_element, parent, false)
        return ViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(posts[position], position)
    }

    override fun getItemCount(): Int {
        return posts.size
    }
}
