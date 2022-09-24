package com.ayaz.csittimetableapp
import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.telephony.mbms.DownloadRequest
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import java.io.File
import java.lang.reflect.Type
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class DownloadFragment : Fragment() {
    private val filePath = File(Environment.getExternalStorageDirectory().toString() + "/Download/Timetable_v4.xlsx")
    private var param1: String? = null
    private var param2: String? = null
    val fileurl = "https://drive.google.com/uc?export=download&id=1QqpmImHtb6hMRAFaRqX5x3i-hUskJOJj"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_download, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Toast.makeText(requireContext(), "Internet Connection is needed to Download File", Toast.LENGTH_SHORT).show()
        val prograssbar = view.findViewById<ProgressBar>(R.id.progressBar)
        val mWebView = view.findViewById(R.id.webview) as WebView
        mWebView.loadUrl(fileurl)
        val webSettings = mWebView.getSettings()
        webSettings.setJavaScriptEnabled(true)
        mWebView.setWebViewClient(WebViewClient())
        mWebView.webViewClient = object : WebViewClient(){
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                prograssbar.visibility = View.GONE

            }
        }
        mWebView.setDownloadListener(DownloadListener { url, userAgent,
                                                        contentDescription,
                                                        mimetype,
                                                        contentLength ->
                prograssbar.visibility =View.VISIBLE
                mWebView.visibility = View.GONE
                val request = DownloadManager.Request(Uri.parse(url))
                val cookies = CookieManager.getInstance().getCookie(url)
                if (filePath.exists()) {
                    filePath.delete()
                }

                request.addRequestHeader("Cookie", cookies)
                request.addRequestHeader("User-Agent", userAgent)
                request.setDescription("Downloading requested file....")
                request.setMimeType(mimetype)
                request.allowScanningByMediaScanner()
                request.setNotificationVisibility(
                    DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED
                )
                val fileName = "Timetable_v4.xlsx"
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)
                request.setTitle("Latest Time Table File");
                request.setAllowedOverMetered(true)
                request.setAllowedOverRoaming(true)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    request.setRequiresCharging(false)
                    request.setRequiresDeviceIdle(false)
                }
                request.setVisibleInDownloadsUi(true)
                val dManager = activity?.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                 dManager.enqueue(request)


                val handler = Handler()
                handler.postDelayed({
                    val intent = Intent(requireContext(),MainActivity::class.java)
                    startActivity(intent)
                                    }, 2000)

        })
        val broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(p0: Context?, p1: Intent?) {

            }
        }
    }
}