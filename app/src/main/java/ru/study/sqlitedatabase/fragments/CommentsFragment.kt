package ru.study.sqlitedatabase.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.study.sqlitedatabase.R
import ru.study.sqlitedatabase.WorkWithDatabase
import ru.study.sqlitedatabase.adapters.CommentsAdapter
import ru.study.sqlitedatabase.models.Post

class CommentsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_comments, container, false)
    }

    lateinit var commentsRecyclerView: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        commentsRecyclerView = view.findViewById(R.id.commentsRecyclerView)

        val q = WorkWithDatabase(view.context)
        q.create()
        val bundle = this.arguments
        val postId = bundle?.getInt("postId")
        postId?.let {
            commentsRecyclerView.layoutManager = LinearLayoutManager(view.context)
            commentsRecyclerView.adapter = CommentsAdapter(q.getCommentsList(postId.toString()))
        }
    }
}
