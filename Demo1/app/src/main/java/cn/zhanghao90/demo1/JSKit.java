package cn.zhanghao90.demo1;

import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class JSKit {
    private MainActivity ma;
    public JSKit(MainActivity context) {
        this.ma = context;
    }

    @JavascriptInterface
    public void showMsg(String msg) {
        Toast.makeText(ma, msg, Toast.LENGTH_SHORT).show();
    }
}
