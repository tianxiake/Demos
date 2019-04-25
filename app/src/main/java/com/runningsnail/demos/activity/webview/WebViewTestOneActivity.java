package com.runningsnail.demos.activity.webview;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import com.runningsnail.demos.R;
import com.runningsnail.demos.common.utils.HiLogger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WebViewTestOneActivity extends AppCompatActivity {

    public static final String TAG = "WebViewTestOneActivity";
    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.btn_invoke_js)
    Button btnLoadJs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_test_one);
        ButterKnife.bind(this);
        WebSettings webSettings = webview.getSettings();

        // 设置与Js交互的权限
        webSettings.setJavaScriptEnabled(true);
        // 设置允许JS弹窗
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        // 先载入JS代码
        // 格式规定为:file:///android_asset/文件名.html
        webview.loadUrl("http://192.168.222.146:8080/test/target.html");

        // 由于设置了弹窗检验调用结果,所以需要支持js对话框
        // webview只是载体，内容的渲染需要使用webviewChromClient类去实现
        // 通过设置WebChromeClient对象处理JavaScript的对话框
        //设置响应js 的Alert()函数
        webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
                HiLogger.d(TAG, "url: %s message: %s", url, message);
                AlertDialog.Builder b = new AlertDialog.Builder(WebViewTestOneActivity.this);
                b.setTitle("Alert");
                b.setMessage(message);
                b.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.confirm();
                    }
                });
                b.setCancelable(false);
                b.create().show();
                return true;
            }

        });


    }


    @OnClick({R.id.btn_invoke_js})
    public void onClick(View view) {
        int id = view.getId();
        HiLogger.d(TAG, "onClick id: %s", id);
        switch (id) {
            case R.id.btn_invoke_js:
                androidInvokeJs();
                break;
            default:
                break;
        }
    }

    private void androidInvokeJs() {
        webview.post(new Runnable() {
            @Override
            public void run() {
                // 注意调用的JS方法名要对应上
                // 调用javascript的callJS()方法
                webview.loadUrl("javascript:callJs()");
            }
        });
    }
}
