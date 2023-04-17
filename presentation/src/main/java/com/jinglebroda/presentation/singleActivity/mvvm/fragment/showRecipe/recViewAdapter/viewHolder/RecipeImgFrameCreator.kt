package com.jinglebroda.presentation.singleActivity.mvvm.fragment.showRecipe.recViewAdapter.viewHolder

import android.content.Context
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.ImageView

abstract class RecipeImgFrameCreator(protected val contextFrameCreator: Context) {
    abstract fun createFrame(img:ImageView): ViewGroup.LayoutParams


    class Base(
        context: Context
    ):RecipeImgFrameCreator(context){
        override fun createFrame(img: ImageView):ViewGroup.LayoutParams {
            //получаем размер экрана
            val displayMetrics = contextFrameCreator.resources.displayMetrics
            //узнаем число пикселей в 32 dp под устройство
            val boarderDp = 32F
            val boarderPx = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                boarderDp,
                contextFrameCreator.resources.displayMetrics
            ).toInt()
            //получаем ширину экрана в пикселях, вычитаем отступы по сторонам и получаем размер для картинки.
            val widthPixels = displayMetrics.widthPixels
            val newWidthImg = ((widthPixels-boarderPx)/3)*2
            //собираем новый layoutParams
            val layoutParams = img.layoutParams
            layoutParams.width = newWidthImg
            layoutParams.height = newWidthImg

            return layoutParams
        }
    }
}