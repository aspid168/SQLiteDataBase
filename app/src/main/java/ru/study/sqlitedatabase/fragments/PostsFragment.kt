package ru.study.sqlitedatabase.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.study.sqlitedatabase.Handler
import ru.study.sqlitedatabase.R
import ru.study.sqlitedatabase.WorkWithDatabase
import ru.study.sqlitedatabase.adapters.PostsAdapter

class PostsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_posts, container, false)
    }

    lateinit var postsRecyclerView: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val q = WorkWithDatabase(view.context)
        q.create()

        postsRecyclerView = view.findViewById(R.id.postsRecyclerView)
        postsRecyclerView.layoutManager = LinearLayoutManager(view.context)
        postsRecyclerView.adapter = PostsAdapter(q.getPostsList()) { post, postId->
            val act = activity
            if (act is Handler) {
                act.goToDetailsFragment(post, postId)
            }
        }
    }

}
