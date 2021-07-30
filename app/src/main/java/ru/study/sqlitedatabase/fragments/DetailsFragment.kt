package ru.study.sqlitedatabase.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.study.sqlitedatabase.Handler
import ru.study.sqlitedatabase.R
import ru.study.sqlitedatabase.models.Post

class DetailsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    lateinit var title: TextView
    lateinit var email: TextView
    lateinit var authorFullName: TextView
    lateinit var description: TextView
    lateinit var comments: FloatingActionButton

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        title = view.findViewById(R.id.title)
        email = view.findViewById(R.id.email)
        authorFullName = view.findViewById(R.id.authorFullName)
        description = view.findViewById(R.id.description)
        comments = view.findViewById(R.id.comments)
        val bundle = this.arguments
        val post: Post = bundle?.getSerializable("post") as Post
        val postId= bundle.getInt("postId")
        title.text = post.title
        email.text = post.email
        authorFullName.text = post.authorFullName
        description.text = post.description
        comments.setOnClickListener{
            val act = activity
            if (act is Handler) {
                act.goToCommentsFragment(postId)
            }
        }
    }
}
