package ru.study.sqlitedatabase

import ru.study.sqlitedatabase.models.Post

interface Handler {
    fun goToDetailsFragment(postId: Int, post: Post)
    fun goToCommentsFragment(postId: Int)
}
