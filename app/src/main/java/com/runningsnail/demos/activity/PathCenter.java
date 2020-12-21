package com.runningsnail.demos.activity;

import com.runningsnail.demos.activity.bottomnavigation.BottomWayOneActivity;
import com.runningsnail.demos.activity.bottomnavigation.BottomWayThreeActivity;
import com.runningsnail.demos.activity.bottomnavigation.BottomWayTwoActivity;
import com.runningsnail.demos.activity.dialog.AlertDialogActivity;
import com.runningsnail.demos.activity.dialog.DialogFragmentActivity;
import com.runningsnail.demos.activity.other.KillProcessActivity;
import com.runningsnail.demos.activity.retrofit.RetrofitDemoActivity;
import com.runningsnail.demos.activity.surface.VideoViewActivity;
import com.runningsnail.demos.activity.text.TextEffectActivity;
import com.runningsnail.demos.activity.tv.ClipPaddingAndChildTestActivity;
import com.runningsnail.demos.activity.tv.FinderFocusActivity;
import com.runningsnail.demos.activity.tv.FlyEffectActivity;
import com.runningsnail.demos.activity.tv.FocusFinderTestActivity;
import com.runningsnail.demos.activity.tv.FocusSearchActivity;
import com.runningsnail.demos.activity.tv.FunnyPropertyActivity;
import com.runningsnail.demos.activity.tv.KeyEventTestActivity;
import com.runningsnail.demos.activity.tv.LinearLayoutTVActivity;
import com.runningsnail.demos.activity.tv.MockActivity;
import com.runningsnail.demos.activity.tv.RecyclerViewActivity;
import com.runningsnail.demos.activity.tv.RuntimeExecuteActivity;
import com.runningsnail.demos.activity.tv.ScreenAdapterActivity;
import com.runningsnail.demos.activity.tv.ScrollViewActivity;
import com.runningsnail.demos.activity.tv.ShadowEffectActivity;
import com.runningsnail.demos.activity.tv.ViewVisibleActivity;
import com.runningsnail.demos.activity.viewanimation.AlphaAnimationActivity;
import com.runningsnail.demos.activity.viewanimation.DoubleTabScaleActivity;
import com.runningsnail.demos.activity.viewanimation.FrameAnimActivity;
import com.runningsnail.demos.activity.viewanimation.LoaddingActivity;
import com.runningsnail.demos.activity.viewanimation.RotateAnimationActivity;
import com.runningsnail.demos.activity.viewanimation.ScaleAnimationActivity;
import com.runningsnail.demos.activity.viewanimation.ScanActivity;
import com.runningsnail.demos.activity.viewanimation.SetAnimationActivity;
import com.runningsnail.demos.activity.viewanimation.SplashActivity;
import com.runningsnail.demos.activity.viewanimation.TranslateAnimationActivity;
import com.runningsnail.demos.activity.webview.H5OpenAppTestActivity;
import com.runningsnail.demos.activity.webview.WebViewTestOneActivity;
import com.runningsnail.demos.activity.webview.WebViewTestTwoActivity;
import com.runningsnail.demos.activity.widget.CustomSeekBarActivity;
import com.runningsnail.demos.activity.widget.CustomToastActivity;
import com.runningsnail.demos.activity.widget.SelectionActivity;

import java.util.List;

/**
 * @author yongjie created on 2020/5/12.
 */
public class PathCenter {

	public static MainData getMainData() {
		MainData mainData = new MainData();
		List<MainData.ContentBean> content = mainData.getContent();
		content.add(buildTV("===TV开发==="));
		content.add(buildText("===文字处理==="));
		content.add(buildDialog("===对话框系列==="));
		content.add(buildLocation("===位置信息==="));
		content.add(buildWebView("===webView测试==="));
		content.add(buildBottomNav("===底部导航栏==="));
		content.add(buildAnimate("===补间动画系列==="));
		content.add(buildWidget("===widget==="));
		content.add(buildNetwork("===网络处理==="));
		content.add(buildOther("===其他==="));
		return mainData;
	}

	private static MainData.ContentBean buildOther(String title) {
		MainData.ContentBean tvBean = new MainData.ContentBean();
		tvBean.setTitle(title);
		List<MainData.ContentBean.SubBean> sub = tvBean.getSub();
		sub.add(new MainData.ContentBean.SubBean("Android杀进程方式", KillProcessActivity.class.getName()));
		return tvBean;
	}

	private static MainData.ContentBean buildNetwork(String title) {
		MainData.ContentBean tvBean = new MainData.ContentBean();
		tvBean.setTitle(title);
		List<MainData.ContentBean.SubBean> sub = tvBean.getSub();
		sub.add(new MainData.ContentBean.SubBean("Retrofit", RetrofitDemoActivity.class.getName()));
		return tvBean;
	}

	private static MainData.ContentBean buildWidget(String title) {
		MainData.ContentBean tvBean = new MainData.ContentBean();
		tvBean.setTitle(title);
		List<MainData.ContentBean.SubBean> sub = tvBean.getSub();
		sub.add(new MainData.ContentBean.SubBean("自定义Toast测试页面", CustomToastActivity.class.getName()));
		sub.add(new MainData.ContentBean.SubBean("轮动组件测试", SelectionActivity.class.getName()));
		sub.add(new MainData.ContentBean.SubBean("自定义SeekBar", CustomSeekBarActivity.class.getName()));
		return tvBean;
	}

	private static MainData.ContentBean buildTV(String title) {
		MainData.ContentBean tvBean = new MainData.ContentBean();
		tvBean.setTitle(title);
		List<MainData.ContentBean.SubBean> sub = tvBean.getSub();
		sub.add(new MainData.ContentBean.SubBean("KeyEvent测试", KeyEventTestActivity.class.getName()));
		sub.add(new MainData.ContentBean.SubBean("阴影效果实现方案", ShadowEffectActivity.class.getName()));
		sub.add(new MainData.ContentBean.SubBean("测试本地命令", RuntimeExecuteActivity.class.getName()));
		sub.add(new MainData.ContentBean.SubBean("模拟三方应用", MockActivity.class.getName()));
		sub.add(new MainData.ContentBean.SubBean("有趣的属性", FunnyPropertyActivity.class.getName()));
		sub.add(new MainData.ContentBean.SubBean("ScrollView", ScrollViewActivity.class.getName()));
		sub.add(new MainData.ContentBean.SubBean("光影效果", FlyEffectActivity.class.getName()));
		sub.add(new MainData.ContentBean.SubBean("线性布局测试滑动", LinearLayoutTVActivity.class.getName()));
		sub.add(new MainData.ContentBean.SubBean("屏幕适配", ScreenAdapterActivity.class.getName()));
		sub.add(new MainData.ContentBean.SubBean("View可见性测试", ViewVisibleActivity.class.getName()));
		sub.add(new MainData.ContentBean.SubBean("焦点查找测试", FocusSearchActivity.class.getName()));
		sub.add(new MainData.ContentBean.SubBean("ClipTest", ClipPaddingAndChildTestActivity.class.getName()));
		sub.add(new MainData.ContentBean.SubBean("FocusFinder", FocusFinderTestActivity.class.getName()));
		sub.add(new MainData.ContentBean.SubBean("RecyclerView", RecyclerViewActivity.class.getName()));
		sub.add(new MainData.ContentBean.SubBean("VideoView测试", VideoViewActivity.class.getName()));
		sub.add(new MainData.ContentBean.SubBean("FocusHandle", FinderFocusActivity.class.getName()));
		return tvBean;
	}

	private static MainData.ContentBean buildText(String title) {
		MainData.ContentBean tvBean = new MainData.ContentBean();
		tvBean.setTitle(title);
		List<MainData.ContentBean.SubBean> sub = tvBean.getSub();
		sub.add(new MainData.ContentBean.SubBean("SpannableString基本使用", TextEffectActivity.class.getName()));
		return tvBean;
	}

	private static MainData.ContentBean buildDialog(String title) {
		MainData.ContentBean tvBean = new MainData.ContentBean();
		tvBean.setTitle(title);
		List<MainData.ContentBean.SubBean> sub = tvBean.getSub();
		sub.add(new MainData.ContentBean.SubBean("对话框实现之AlertDialog", AlertDialogActivity.class.getName()));
		sub.add(new MainData.ContentBean.SubBean("DialogFragment实现Dialog", DialogFragmentActivity.class.getName()));
		return tvBean;
	}

	private static MainData.ContentBean buildLocation(String title) {
		MainData.ContentBean tvBean = new MainData.ContentBean();
		tvBean.setTitle(title);
		List<MainData.ContentBean.SubBean> sub = tvBean.getSub();
		sub.add(new MainData.ContentBean.SubBean("位置信息测试", LocationActivity.class.getName()));
		return tvBean;
	}

	private static MainData.ContentBean buildWebView(String title) {
		MainData.ContentBean tvBean = new MainData.ContentBean();
		tvBean.setTitle(title);
		List<MainData.ContentBean.SubBean> sub = tvBean.getSub();
		sub.add(new MainData.ContentBean.SubBean("WebView测试One", WebViewTestOneActivity.class.getName()));
		sub.add(new MainData.ContentBean.SubBean("WebView测试Two", WebViewTestTwoActivity.class.getName()));
		sub.add(new MainData.ContentBean.SubBean("打开机顶盒浏览器测试", H5OpenAppTestActivity.class.getName()));
		return tvBean;
	}

	private static MainData.ContentBean buildBottomNav(String title) {
		MainData.ContentBean tvBean = new MainData.ContentBean();
		tvBean.setTitle(title);
		List<MainData.ContentBean.SubBean> sub = tvBean.getSub();
		sub.add(new MainData.ContentBean.SubBean("LinearLayout+TextView实现底部导航栏一", BottomWayOneActivity.class.getName()));
		sub.add(new MainData.ContentBean.SubBean("RadioGroup+RadioButton实现底部导航栏二", BottomWayTwoActivity.class.getName()));
		sub.add(new MainData.ContentBean.SubBean("第三方框架NavigationBarView实现底部导航栏三(比较好的实现方案)", BottomWayThreeActivity.class.getName()));
		return tvBean;
	}

	private static MainData.ContentBean buildAnimate(String title) {
		MainData.ContentBean tvBean = new MainData.ContentBean();
		tvBean.setTitle(title);
		List<MainData.ContentBean.SubBean> sub = tvBean.getSub();
		sub.add(new MainData.ContentBean.SubBean("ViewAnimation之Scale动画", ScaleAnimationActivity.class.getName()));
		sub.add(new MainData.ContentBean.SubBean("ViewAnimation之Alpha动画", AlphaAnimationActivity.class.getName()));
		sub.add(new MainData.ContentBean.SubBean("ViewAnimation之Rotate动画", RotateAnimationActivity.class.getName()));
		sub.add(new MainData.ContentBean.SubBean("ViewAnimation之Translate动画", TranslateAnimationActivity.class.getName()));
		sub.add(new MainData.ContentBean.SubBean("ViewAnimation之Set动画集", SetAnimationActivity.class.getName()));
		sub.add(new MainData.ContentBean.SubBean("ViewAnimation之镜头由远到近的效果", SplashActivity.class.getName()));
		sub.add(new MainData.ContentBean.SubBean("ViewAnimation之双击放大效果", DoubleTabScaleActivity.class.getName()));
		sub.add(new MainData.ContentBean.SubBean("ViewAnimation之加载框效果", LoaddingActivity.class.getName()));
		sub.add(new MainData.ContentBean.SubBean("ViewAnimation之扫描效果", ScanActivity.class.getName()));
		sub.add(new MainData.ContentBean.SubBean("ViewAnimation之帧动画", FrameAnimActivity.class.getName()));
		return tvBean;
	}

}
