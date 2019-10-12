package gong.team.githubclean.ui.follow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import gong.team.githubclean.R
import gong.team.githubclean.databinding.FragmentFollowBinding
import gong.team.githubclean.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class FollowFragment :BaseFragment<FragmentFollowBinding>(R.layout.fragment_follow) {

    private val followViewModel: FollowViewModel by viewModel<FollowViewModel>{
        parametersOf(arguments?.getBoolean("KEY_IS_FOLLOWING") , arguments?.getString("KEY_NAME"))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding {
            followerVM = followViewModel
            recyclerFollow.adapter = FollowAdapter()
            recyclerFollow.layoutManager = LinearLayoutManager(context)
        }
    }

    companion object {
        fun newInstance(bundle: Bundle? = null): FollowFragment = FollowFragment().apply {
            arguments = bundle
        }
    }
}