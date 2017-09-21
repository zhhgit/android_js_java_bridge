package cn.zhanghao90.demo1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;

@SuppressLint("SetJavaScriptEnabled")
public class MainActivity extends Activity {

    private WebView webview1;
    private Button button1;
    private JSKit jsKit;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        webview1 = (WebView) findViewById(R.id.webview1);
        button1 = (Button) findViewById(R.id.button1);
        //实例化jsKit对象
        jsKit = new JSKit(this);

        //把jsKit绑定到全局的globalParams上，globalParams的作用域是全局的，初始化后可随处使用
        webview1.getSettings().setJavaScriptEnabled(true);
        webview1.addJavascriptInterface(jsKit, "globalParams");
        webview1.loadUrl("file:///android_asset/www/test.html");

        //内容的渲染需要webviewChromeClient去实现，设置webviewChromeClient基类，解决js中alert不弹出的问题和其他内容渲染问题
        webview1.setWebChromeClient(new WebChromeClient());
        //android调用html5中JS方法
        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        webview1.loadUrl("javascript:androidToHtml5()");
                    }
                });
            }
        });
    }
}
