package gong.team.githubclean.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import gong.team.githubclean.wrapper.GlideWrapper

@BindingAdapter(value = ["android:src"])
fun ImageView.setImage(imgUrl: String?){
    imgUrl?.let{
        GlideWrapper
            .load(
                context ,
                imgUrl ,
                this
            )
    }
}