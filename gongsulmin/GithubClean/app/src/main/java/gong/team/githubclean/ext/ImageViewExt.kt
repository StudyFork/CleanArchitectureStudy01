package gong.team.githubclean.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter(value = ["android:src"])
fun ImageView.setImage(imgUrl: String){
    Glide.with(this.context)
        .load(imgUrl)
        .into(this)
}