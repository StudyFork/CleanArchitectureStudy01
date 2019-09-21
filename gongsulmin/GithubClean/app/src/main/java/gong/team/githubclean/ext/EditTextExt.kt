package gong.team.githubclean.ext

import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.databinding.BindingAdapter
import io.reactivex.subjects.BehaviorSubject

@BindingAdapter("setKeyword")
fun EditText.setKeyword(keyword: BehaviorSubject<String>){
    addTextChangedListener {
        keyword.onNext(text.toString())
    }
}
