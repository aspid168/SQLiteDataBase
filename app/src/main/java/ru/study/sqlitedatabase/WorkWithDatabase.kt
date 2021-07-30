package ru.study.sqlitedatabase

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import org.w3c.dom.Comment
import ru.study.sqlitedatabase.models.Comments
import ru.study.sqlitedatabase.models.Post
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream


class WorkWithDatabase(private val context: Context) : SQLiteOpenHelper(
    context,
    DB_NAME,
    null,
    DB_VERSION
) {
    companion object {
        private const val DB_NAME = "lesson-23.db"
        private const val DB_VERSION = 1

        private const val POST_TABLE = "post"
        private const val USER_TABLE = "user"
        private const val COMMENT_TABLE = "comment"

        private const val ID = "id"
        private const val USER_ID = "userId"
        private const val USER_FIRST_NAME = "firstName"
        private const val USER_LAST_NAME = "lastName"
        private const val POST_ID = "postId"
        private const val USER_EMAIL = "email"
        private const val POST_TITLE = "title"
        private const val POST_BODY = "body"
        private const val COMMENT_TEXT = "text"
        //...........
    }

    fun getPostsList(): MutableList<Post> {
        val db = this.writableDatabase
        val data = mutableListOf<Post>()
        val cursor = db.rawQuery(
            "select $POST_TITLE, $POST_BODY, $USER_EMAIL, $USER_FIRST_NAME, $USER_LAST_NAME from $POST_TABLE join $USER_TABLE on $USER_TABLE.$ID = $POST_TABLE.$USER_ID",
            null
        )
        while (cursor.moveToNext()) {
            val email = cursor.getString(cursor.getColumnIndex(USER_EMAIL))
            val title = cursor.getString(cursor.getColumnIndex(POST_TITLE))
            val description = cursor.getString(cursor.getColumnIndex(POST_BODY))
            val authorFullName =
                cursor.getString(cursor.getColumnIndex(USER_FIRST_NAME)) + " " + cursor.getString(
                    cursor.getColumnIndex(
                        USER_LAST_NAME
                    )
                )
            data.add(Post(email, title, description, authorFullName))
        }
        cursor.close()
        db.close()
        return data
    }

    fun getCommentsList(postId: String): MutableList<Comments> { //todo postId
        val db = this.writableDatabase
        val comments = mutableListOf<Comments>()
        val cursor = db.rawQuery(
            "select $USER_EMAIL, $COMMENT_TEXT from $COMMENT_TABLE join $USER_TABLE on $USER_TABLE.$ID = $COMMENT_TABLE.$USER_ID and $postId = $COMMENT_TABLE.$POST_ID",
            null
        )
        while (cursor.moveToNext()) {
            val email = cursor.getString(cursor.getColumnIndex(USER_EMAIL))
            val comment = cursor.getString(cursor.getColumnIndex(COMMENT_TEXT))
            comments.add(Comments(email, comment))
        }
        cursor.close()
        db.close()
        return comments
    }

    //todo move to onCreate
    fun create() {
        val dbFile = context.getDatabasePath(DB_NAME)
        if (!dbFile.exists()) {
            this.writableDatabase
            this.close()
            val inputStream: InputStream = context.assets.open(DB_NAME)
            val outputStream: OutputStream = FileOutputStream(context.getDatabasePath(DB_NAME))
            val buffer = ByteArray(1024)
            var length: Int
            length = inputStream.read(buffer)
            while (length > 0) {
                outputStream.write(buffer, 0, length)
                length = inputStream.read(buffer)
            }
            outputStream.flush()
            outputStream.close()
            inputStream.close()
        }
    }


    override fun onCreate(db: SQLiteDatabase?) {

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}
