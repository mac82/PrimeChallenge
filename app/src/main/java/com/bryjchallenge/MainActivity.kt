package com.bryjchallenge

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.bryjchallenge.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var webView:WebView
    private var isMenuOpened = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        initViews()
    }

    private fun initViews(){
        initTopBar()
        initBottomBar()
        initWebView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView(){
        webView = mBinding.webview
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = BaseWebViewClient()
        // Init Home Page
        webView.loadUrl(URL_HOME)
    }


    private fun initTopBar(){
        mBinding.ivMenu.setOnClickListener {
            if (!isMenuOpened) {
                webView.loadUrl(menuHandler(isMenuOpened))
                isMenuOpened = true
                Log.d(TAG, "Open Hamburger!")
            } else {
                webView.loadUrl(menuHandler(isMenuOpened))
                Log.d(TAG, "Close Hamburger!")
                isMenuOpened = false
            }
        }
    }

    private fun initBottomBar(){
        mBinding.navView.setOnItemSelectedListener {
            mBinding.progressBar.visibility = View.VISIBLE
            when (it.itemId) {
                R.id.navigation_home -> {
                    Log.d(TAG, "navigation_home pressed!")
                    loadUrl(URL_HOME)
                    true
                }
                R.id.navigation_shopping-> {
                    Log.d(TAG, "navigation_shopping pressed!")
                    loadUrl(URL_SHOP)
                    true
                }
                R.id.navigation_account -> {
                    Log.d(TAG, "navigation_account pressed!")
                    loadUrl(URL_ACCOUNT)
                    true
                }
                R.id.navigation_bag -> {
                    Log.d(TAG, "navigation_bag pressed!")
                    loadUrl(URL_BAG)
                    true
                }
                else -> { false }
            }
        }
    }

    private fun loadUrl(url: String){
        webView.loadUrl(url)
    }

    /**
     *  Method used to return a scrit used to load the webview with hamburger menu opened or closed
     *
     *  @param openMenu is a boolean with the state of the menu
     *      values - True (menu is opened) or False (Menu is Closed). Those values are triggered by the
     *               hamburger menu button on the app toolbar
     *
     *  @return scrit used to open or close the hamburger menu
     */
    private fun menuHandler(openMenu: Boolean): String {
        return when (openMenu){
            false -> {
                   """javascript:(function f() {
                    var els = document.getElementsByClassName("navbar-toggler");
                    for(var i = 0, x = els.length; i < x; i++) {
                        els[i].click();
                        break;
                    }
            })()"""
            }
            true -> {
                     """javascript:(function f() {
                    var els = document.getElementsByClassName("close-button");
                    for(var i = 0, x = els.length; i < x; i++) {
                        els[i].click();
                        break;
                    }
            })()"""
            }
        }
    }

    /**
     * Class used to hide the loading view after the webpage is fully loaded
     */
    inner class BaseWebViewClient : WebViewClient() {
        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            mBinding.progressBar.visibility = View.GONE
        }
    }

    companion object {
        var TAG = "BRYJ"

        var URL_HOME = "https://www.thebay.com/"
        var URL_SHOP = "https://www.thebay.com/c/men"
        var URL_ACCOUNT = "https://www.thebay.com/account/login"
        var URL_BAG = "https://www.thebay.com/cart"
    }

}