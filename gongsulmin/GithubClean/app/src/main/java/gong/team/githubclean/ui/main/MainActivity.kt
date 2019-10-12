package gong.team.githubclean.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import gong.team.githubclean.R
import gong.team.githubclean.adapter.MainAdapter
import gong.team.githubclean.databinding.ActivityMainBinding
import gong.team.githubclean.ui.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    val mainViewModel: MainViewModel by viewModel()
    val TAG = "MAINACTIVITY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding {
            mainVM = mainViewModel
            recyclerview.adapter = MainAdapter()
            recyclerview.layoutManager = LinearLayoutManager(this@MainActivity)
        }

        val uri = intent.data
        if (uri == null) {
            // 이것도 하나의 usecase 인데 ??
            // code 가져오는것도 usecase
            startActivity(Intent(Intent.ACTION_VIEW , Uri.parse("https://github.com/login/oauth/authorize?client_id=95afcb4b4c096edece62&scope=repo&redirect_uri=gong://callback")))
        }else if (uri.toString().contains("gong://callback")){

//            GithubRepositoryImpl(
//                GithubSearchRemoteDatasourceImpl(
//                    GithubApiFactory.providerGithubApi<GithubApi>(GithubApi.BASE_URL) ,
//                    GithubApiFactory.providerGithubApi<GithubLoginApi>(GithubLoginApi.BASE_URL)
//                ) ,
//                GithubSearchItemMapper(GithubOwnerMapper())
//            ).getAccessToken(
//                clientId = "95afcb4b4c096edece62" ,
//                clientSecret = "ef428364a8a1013ece84970eb7ece4e3f47cadbb" ,
//                code = uri.getQueryParameter("code")!!
//            ).subscribeBy (
//
//                onSuccess =  {
//                    Log.e("성공", " ${it} ")
//                } ,
//
//                onError =  {
//                    Log.e("실패" , " ERROR ")
//                    it.printStackTrace()
//                }
//            ).addTo(CompositeDisposable())

//            startActivity(Intent(Intent.ACTION_VIEW , Uri.parse("https://github.com/login/oauth/access_token?client_id=95afcb4b4c096edece62&client_secret=ef428364a8a1013ece84970eb7ece4e3f47cadbb&redirect_uri=gong://tokencallback&code="+"wwwww")))
            // 이코드를 통해서 access token 값을 얻어옴
        }

    }
}


