package m.tech.baseclean.util

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PrefUtil
@Inject
constructor(
    private val sharedPreferences: SharedPreferences,
    private val editor: SharedPreferences.Editor
) {

    var token: String?
        get() = sharedPreferences.getString("CachedToken", null)
        set(value) {
            editor.putString("CachedToken", value).commit()
        }


//    var borrowedBook: ArrayList<Book>
//        get() = Gson().fromJson(
//            sharedPreferences.getString("ListBorrowBook", ""),
//            object : TypeToken<List<Book>>() {}.type
//        ) ?: arrayListOf()
//        set(value) {
//            editor.putString("ListBorrowBook", Gson().toJson(value)).commit()
//        }

}