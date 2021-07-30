package ru.study.sqlitedatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import ru.study.sqlitedatabase.fragments.CommentsFragment
import ru.study.sqlitedatabase.fragments.DetailsFragment
import ru.study.sqlitedatabase.fragments.PostsFragment
import ru.study.sqlitedatabase.models.Post

class MainActivity : AppCompatActivity(), Handler {

    private val postFragment = PostsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            val currentFragment = supportFragmentManager
                .getFragment(savedInstanceState, "currentFragment") as Fragment
            supportFragmentManager.beginTransaction()
                .replace(R.id.mainContainer, currentFragment)
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.mainContainer, postFragment)
                .commit()
        }
    }

    override fun goToDetailsFragment(postId: Int, post: Post) {
        val detailsFragment = DetailsFragment()
        val bundle = Bundle()
        bundle.putSerializable("post", post)
        bundle.putInt("postId", postId)
        detailsFragment.arguments = bundle
        supportFragmentManager.beginTransaction()
            .replace(R.id.mainContainer, detailsFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun goToCommentsFragment(postId: Int) {
        val commentsFragment = CommentsFragment()
        val bundle = Bundle()
        bundle.putInt("postId", postId)
        commentsFragment.arguments = bundle
        supportFragmentManager.beginTransaction()
            .replace(R.id.mainContainer, commentsFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        supportFragmentManager.findFragmentById(R.id.mainContainer)?.let {
            supportFragmentManager.putFragment(outState, "currentFragment", it)
        }
    }
}
