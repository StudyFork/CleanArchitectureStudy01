package com.beok.fileviewer.presenter


import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.beok.common.base.BaseFragment
import com.beok.fileviewer.R
import com.beok.fileviewer.databinding.FragmentFileViewerBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FileViewerFragment : BaseFragment<FragmentFileViewerBinding, FileViewerViewModel>(
    R.layout.fragment_file_viewer
) {

    override val viewModel: FileViewerViewModel by viewModel()
    private val args: FileViewerFragmentArgs by navArgs()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initWebView()
    }

    override fun initBinding() {
        // NO OP
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() {
        binding.wvFileViewer.run {
            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ): Boolean {
                    loadUrl(request?.url.toString())
                    return true
                }
            }
            settings.javaScriptEnabled = true
            post {
                loadUrl(args.downloadUrl)
            }
        }
    }
}
