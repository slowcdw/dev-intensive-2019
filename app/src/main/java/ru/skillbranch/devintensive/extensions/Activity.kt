package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import ru.skillbranch.devintensive.MainActivity

fun Activity.hideKeyboard() {

    val imm: InputMethodManager =
        this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager;
    //Find the currently focused view, so we can grab the correct window token from it.
    var view = this.currentFocus;
    //If no view currently has focus, create a new one, just so we can grab a window token from it
    if (view == null) {
        view = View(this);
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0);
}

fun Activity.isKeyboardOpen(): Boolean{
    val rootView = findViewById<View>(android.R.id.content)
    val rect = Rect()
    rootView.getWindowVisibleDisplayFrame(rect)
    val heightDiff = rootView.height - rect.height()
//    val err = this.dpToPx(20F)
    val err = TypedValue.applyDimension (TypedValue.COMPLEX_UNIT_DIP , 20F, this.resources.displayMetrics)
    return heightDiff > err
}

fun Activity.isKeyboardClosed(): Boolean = !this.isKeyboardOpen()
