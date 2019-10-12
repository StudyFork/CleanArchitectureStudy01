package gong.team.githubclean.wrapper

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

object GlideWrapper {
    fun load(
        context: Context ,
        url: String ,
        target: ImageView
    ) {
        Glide.with(context)
            .load(url)
            .into(target)
    }
}