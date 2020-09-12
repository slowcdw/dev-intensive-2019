package ru.skillbranch.devintensive.ui.custom

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import androidx.annotation.ColorRes
import androidx.annotation.Dimension
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import ru.skillbranch.devintensive.App

class CircleImageView  @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr){

    companion object {
        private const val DEFAULT_BORDER_WIDTH = 2
        private const val DEFAULT_BORDER_COLOR = Color.WHITE
    }

    private var borderWidth: Int = DEFAULT_BORDER_WIDTH
    private var borderColor = DEFAULT_BORDER_COLOR

    fun getBorderWidth():Int{
        return borderWidth
    }

    fun setBorderWidth(@Dimension dp:Int) {
        borderWidth = dp
    }
    fun getBorderColor():Int{
        return borderColor
    }
    fun setBorderColor(hex:String){
        borderColor = Color.parseColor(hex)
    }
    fun setBorderColor(@ColorRes colorId: Int){
        borderColor = ContextCompat.getColor(App.applicationContext(), colorId)
    }
}