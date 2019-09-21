package gong.team.githubclean.ext

import android.view.View
import androidx.databinding.BindingAdapter


@BindingAdapter("android:visibility")
fun View.setVisible(visible: Boolean){
    if (visible) visibility = View.VISIBLE else visibility = View.GONE
}