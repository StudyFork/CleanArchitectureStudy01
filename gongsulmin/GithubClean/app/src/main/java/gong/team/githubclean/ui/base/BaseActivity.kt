package gong.team.githubclean.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<B: ViewDataBinding>(private val layoutRes: Int): AppCompatActivity(){

    protected lateinit var viewDataBinding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.inflate<B>(
            layoutInflater ,
            layoutRes ,
            null ,
            false
        )
        setContentView(viewDataBinding.root)
        viewDataBinding.lifecycleOwner = this
    }

    protected fun binding(action: B.() -> Unit){

        viewDataBinding.run(action)
    }

}