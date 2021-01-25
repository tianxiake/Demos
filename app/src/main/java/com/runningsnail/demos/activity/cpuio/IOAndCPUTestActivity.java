package com.runningsnail.demos.activity.cpuio;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.runningsnail.demos.MainHandler;
import com.runningsnail.demos.R;
import com.runningsnail.demos.common.utils.HiLogger;
import com.runningsnail.demos.entity.Page;

import java.io.InputStreamReader;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * IO计算和CPU计算的测试
 */
public class IOAndCPUTestActivity extends AppCompatActivity {

	private static final String TAG = "IOAndCPUTestActivity";

	@BindView(R.id.tv_io_test_text)
	TextView textView;

	private String page = "{\n" +
			"  \"status\": \"200\" ,\n" +
			"  \"title\": \"全部类型第一排\" ,\n" +
			"  \"code\": \"biz_95170349\" ,\n" +
			"  \"height\": \"370\" ,\n" +
			"  \"defaultFocus\": \"1\" ,\n" +
			"  \"areaDatas\": [\n" +
			"    {\n" +
			"      \"areaCode\": \"1\" ,\n" +
			"      \"areaType\": \"image\" ,\n" +
			"      \"areaRect\": \"70,156,364,204\" ,\n" +
			"      \"scaleSize\": \"1.1\" ,\n" +
			"      \"areaTitle\": \"移动3.0_更多_全部类型第一排_横图推荐位1\" ,\n" +
			"      \"areaIcon\": \"\" ,\n" +
			"      \"defaultImgType\": \"\" ,\n" +
			"      \"dataLink\": \"\" ,\n" +
			"      \"items\": [\n" +
			"        {\n" +
			"          \"isPlayNotReal\": \"false\" ,\n" +
			"          \"poscode\": \"BizPosition_9289\" ,\n" +
			"          \"itemTitle\": \"#南传移动EPG#动态模板#推荐#2018推荐专题#更多排行榜#电视剧\" ,\n" +
			"          \"itemSubTitle\": \"\" ,\n" +
			"          \"itemCode\": \"biz_52309684\" ,\n" +
			"          \"itemType\": \"biz\" ,\n" +
			"          \"itemIcon\": \"http://192.168.220.127:58187/epg/resource/position/202003/09/2020030910310274002379.png\" ,\n" +
			"          \"itemIcon2\": \"\" ,\n" +
			"          \"showFlag\": \"\" ,\n" +
			"          \"opShowFlag\": \"\" ,\n" +
			"          \"hImg\": \"\" ,\n" +
			"          \"vImg\": \"\" ,\n" +
			"          \"opimg1\": \"\" ,\n" +
			"          \"opimg2\": \"\" ,\n" +
			"          \"linkType\": \"cn.gd.snm.template.cinema.CinemaActivity\" ,\n" +
			"          \"dataLink\": \"http://192.168.220.127:58187/epg/api/page/biz_52309684.json\"\n" +
			"        }\n" +
			"      ]\n" +
			"    } ,\n" +
			"    {\n" +
			"      \"areaCode\": \"2\" ,\n" +
			"      \"areaType\": \"image\" ,\n" +
			"      \"areaRect\": \"458,156,364,204\" ,\n" +
			"      \"scaleSize\": \"1.1\" ,\n" +
			"      \"areaTitle\": \"移动3.0_更多_全部类型第一排_横图推荐位2\" ,\n" +
			"      \"areaIcon\": \"\" ,\n" +
			"      \"defaultImgType\": \"\" ,\n" +
			"      \"dataLink\": \"\" ,\n" +
			"      \"items\": [\n" +
			"        {\n" +
			"          \"isPlayNotReal\": \"false\" ,\n" +
			"          \"poscode\": \"BizPosition_9291\" ,\n" +
			"          \"itemTitle\": \"#南传移动EPG#动态模板#推荐#专题集合#最新上线\" ,\n" +
			"          \"itemSubTitle\": \"\" ,\n" +
			"          \"itemCode\": \"biz_86858576\" ,\n" +
			"          \"itemType\": \"biz\" ,\n" +
			"          \"itemIcon\": \"http://192.168.220.127:58187/epg/resource/position/202003/09/2020030910305422197551.png\" ,\n" +
			"          \"itemIcon2\": \"\" ,\n" +
			"          \"showFlag\": \"\" ,\n" +
			"          \"opShowFlag\": \"\" ,\n" +
			"          \"hImg\": \"\" ,\n" +
			"          \"vImg\": \"\" ,\n" +
			"          \"opimg1\": \"\" ,\n" +
			"          \"opimg2\": \"\" ,\n" +
			"          \"linkType\": \"cn.gd.snm.list.SeriesListActivity\" ,\n" +
			"          \"dataLink\": \"http://192.168.220.127:58187/epg/api/page/biz_86858576.json\"\n" +
			"        }\n" +
			"      ]\n" +
			"    } ,\n" +
			"    {\n" +
			"      \"areaCode\": \"3\" ,\n" +
			"      \"areaType\": \"image\" ,\n" +
			"      \"areaRect\": \"846,156,364,204\" ,\n" +
			"      \"scaleSize\": \"1.1\" ,\n" +
			"      \"areaTitle\": \"移动3.0_更多_全部类型第一排_横图推荐位3\" ,\n" +
			"      \"areaIcon\": \"\" ,\n" +
			"      \"defaultImgType\": \"\" ,\n" +
			"      \"dataLink\": \"\" ,\n" +
			"      \"items\": [\n" +
			"        {\n" +
			"          \"isPlayNotReal\": \"false\" ,\n" +
			"          \"poscode\": \"BizPosition_9293\" ,\n" +
			"          \"itemTitle\": \"应用商城\" ,\n" +
			"          \"itemSubTitle\": \"\" ,\n" +
			"          \"itemCode\": \"link_39894232\" ,\n" +
			"          \"itemType\": \"link\" ,\n" +
			"          \"itemIcon\": \"http://192.168.220.127:58187/epg/resource/position/202003/09/2020030910304615874099.png\" ,\n" +
			"          \"itemIcon2\": \"\" ,\n" +
			"          \"showFlag\": \"\" ,\n" +
			"          \"opShowFlag\": \"\" ,\n" +
			"          \"hImg\": \"\" ,\n" +
			"          \"vImg\": \"\" ,\n" +
			"          \"opimg1\": \"\" ,\n" +
			"          \"opimg2\": \"\" ,\n" +
			"          \"linkType\": \"\" ,\n" +
			"          \"dataLink\": \"http://192.168.220.127:58187/epg/api/link/link_39894232.json\"\n" +
			"        }\n" +
			"      ]\n" +
			"    } ,\n" +
			"    {\n" +
			"      \"areaCode\": \"1\" ,\n" +
			"      \"areaType\": \"image\" ,\n" +
			"      \"areaRect\": \"70,156,364,204\" ,\n" +
			"      \"scaleSize\": \"1.1\" ,\n" +
			"      \"areaTitle\": \"移动3.0_更多_全部类型第一排_横图推荐位1\" ,\n" +
			"      \"areaIcon\": \"\" ,\n" +
			"      \"defaultImgType\": \"\" ,\n" +
			"      \"dataLink\": \"\" ,\n" +
			"      \"items\": [\n" +
			"        {\n" +
			"          \"isPlayNotReal\": \"false\" ,\n" +
			"          \"poscode\": \"BizPosition_9289\" ,\n" +
			"          \"itemTitle\": \"#南传移动EPG#动态模板#推荐#2018推荐专题#更多排行榜#电视剧\" ,\n" +
			"          \"itemSubTitle\": \"\" ,\n" +
			"          \"itemCode\": \"biz_52309684\" ,\n" +
			"          \"itemType\": \"biz\" ,\n" +
			"          \"itemIcon\": \"http://192.168.220.127:58187/epg/resource/position/202003/09/2020030910310274002379.png\" ,\n" +
			"          \"itemIcon2\": \"\" ,\n" +
			"          \"showFlag\": \"\" ,\n" +
			"          \"opShowFlag\": \"\" ,\n" +
			"          \"hImg\": \"\" ,\n" +
			"          \"vImg\": \"\" ,\n" +
			"          \"opimg1\": \"\" ,\n" +
			"          \"opimg2\": \"\" ,\n" +
			"          \"linkType\": \"cn.gd.snm.template.cinema.CinemaActivity\" ,\n" +
			"          \"dataLink\": \"http://192.168.220.127:58187/epg/api/page/biz_52309684.json\"\n" +
			"        }\n" +
			"      ]\n" +
			"    } ,\n" +
			"    {\n" +
			"      \"areaCode\": \"2\" ,\n" +
			"      \"areaType\": \"image\" ,\n" +
			"      \"areaRect\": \"458,156,364,204\" ,\n" +
			"      \"scaleSize\": \"1.1\" ,\n" +
			"      \"areaTitle\": \"移动3.0_更多_全部类型第一排_横图推荐位2\" ,\n" +
			"      \"areaIcon\": \"\" ,\n" +
			"      \"defaultImgType\": \"\" ,\n" +
			"      \"dataLink\": \"\" ,\n" +
			"      \"items\": [\n" +
			"        {\n" +
			"          \"isPlayNotReal\": \"false\" ,\n" +
			"          \"poscode\": \"BizPosition_9291\" ,\n" +
			"          \"itemTitle\": \"#南传移动EPG#动态模板#推荐#专题集合#最新上线\" ,\n" +
			"          \"itemSubTitle\": \"\" ,\n" +
			"          \"itemCode\": \"biz_86858576\" ,\n" +
			"          \"itemType\": \"biz\" ,\n" +
			"          \"itemIcon\": \"http://192.168.220.127:58187/epg/resource/position/202003/09/2020030910305422197551.png\" ,\n" +
			"          \"itemIcon2\": \"\" ,\n" +
			"          \"showFlag\": \"\" ,\n" +
			"          \"opShowFlag\": \"\" ,\n" +
			"          \"hImg\": \"\" ,\n" +
			"          \"vImg\": \"\" ,\n" +
			"          \"opimg1\": \"\" ,\n" +
			"          \"opimg2\": \"\" ,\n" +
			"          \"linkType\": \"cn.gd.snm.list.SeriesListActivity\" ,\n" +
			"          \"dataLink\": \"http://192.168.220.127:58187/epg/api/page/biz_86858576.json\"\n" +
			"        }\n" +
			"      ]\n" +
			"    } ,\n" +
			"    {\n" +
			"      \"areaCode\": \"3\" ,\n" +
			"      \"areaType\": \"image\" ,\n" +
			"      \"areaRect\": \"846,156,364,204\" ,\n" +
			"      \"scaleSize\": \"1.1\" ,\n" +
			"      \"areaTitle\": \"移动3.0_更多_全部类型第一排_横图推荐位3\" ,\n" +
			"      \"areaIcon\": \"\" ,\n" +
			"      \"defaultImgType\": \"\" ,\n" +
			"      \"dataLink\": \"\" ,\n" +
			"      \"items\": [\n" +
			"        {\n" +
			"          \"isPlayNotReal\": \"false\" ,\n" +
			"          \"poscode\": \"BizPosition_9293\" ,\n" +
			"          \"itemTitle\": \"应用商城\" ,\n" +
			"          \"itemSubTitle\": \"\" ,\n" +
			"          \"itemCode\": \"link_39894232\" ,\n" +
			"          \"itemType\": \"link\" ,\n" +
			"          \"itemIcon\": \"http://192.168.220.127:58187/epg/resource/position/202003/09/2020030910304615874099.png\" ,\n" +
			"          \"itemIcon2\": \"\" ,\n" +
			"          \"showFlag\": \"\" ,\n" +
			"          \"opShowFlag\": \"\" ,\n" +
			"          \"hImg\": \"\" ,\n" +
			"          \"vImg\": \"\" ,\n" +
			"          \"opimg1\": \"\" ,\n" +
			"          \"opimg2\": \"\" ,\n" +
			"          \"linkType\": \"\" ,\n" +
			"          \"dataLink\": \"http://192.168.220.127:58187/epg/api/link/link_39894232.json\"\n" +
			"        }\n" +
			"      ]\n" +
			"    } ,\n" +
			"    {\n" +
			"      \"areaCode\": \"1\" ,\n" +
			"      \"areaType\": \"image\" ,\n" +
			"      \"areaRect\": \"70,156,364,204\" ,\n" +
			"      \"scaleSize\": \"1.1\" ,\n" +
			"      \"areaTitle\": \"移动3.0_更多_全部类型第一排_横图推荐位1\" ,\n" +
			"      \"areaIcon\": \"\" ,\n" +
			"      \"defaultImgType\": \"\" ,\n" +
			"      \"dataLink\": \"\" ,\n" +
			"      \"items\": [\n" +
			"        {\n" +
			"          \"isPlayNotReal\": \"false\" ,\n" +
			"          \"poscode\": \"BizPosition_9289\" ,\n" +
			"          \"itemTitle\": \"#南传移动EPG#动态模板#推荐#2018推荐专题#更多排行榜#电视剧\" ,\n" +
			"          \"itemSubTitle\": \"\" ,\n" +
			"          \"itemCode\": \"biz_52309684\" ,\n" +
			"          \"itemType\": \"biz\" ,\n" +
			"          \"itemIcon\": \"http://192.168.220.127:58187/epg/resource/position/202003/09/2020030910310274002379.png\" ,\n" +
			"          \"itemIcon2\": \"\" ,\n" +
			"          \"showFlag\": \"\" ,\n" +
			"          \"opShowFlag\": \"\" ,\n" +
			"          \"hImg\": \"\" ,\n" +
			"          \"vImg\": \"\" ,\n" +
			"          \"opimg1\": \"\" ,\n" +
			"          \"opimg2\": \"\" ,\n" +
			"          \"linkType\": \"cn.gd.snm.template.cinema.CinemaActivity\" ,\n" +
			"          \"dataLink\": \"http://192.168.220.127:58187/epg/api/page/biz_52309684.json\"\n" +
			"        }\n" +
			"      ]\n" +
			"    } ,\n" +
			"    {\n" +
			"      \"areaCode\": \"2\" ,\n" +
			"      \"areaType\": \"image\" ,\n" +
			"      \"areaRect\": \"458,156,364,204\" ,\n" +
			"      \"scaleSize\": \"1.1\" ,\n" +
			"      \"areaTitle\": \"移动3.0_更多_全部类型第一排_横图推荐位2\" ,\n" +
			"      \"areaIcon\": \"\" ,\n" +
			"      \"defaultImgType\": \"\" ,\n" +
			"      \"dataLink\": \"\" ,\n" +
			"      \"items\": [\n" +
			"        {\n" +
			"          \"isPlayNotReal\": \"false\" ,\n" +
			"          \"poscode\": \"BizPosition_9291\" ,\n" +
			"          \"itemTitle\": \"#南传移动EPG#动态模板#推荐#专题集合#最新上线\" ,\n" +
			"          \"itemSubTitle\": \"\" ,\n" +
			"          \"itemCode\": \"biz_86858576\" ,\n" +
			"          \"itemType\": \"biz\" ,\n" +
			"          \"itemIcon\": \"http://192.168.220.127:58187/epg/resource/position/202003/09/2020030910305422197551.png\" ,\n" +
			"          \"itemIcon2\": \"\" ,\n" +
			"          \"showFlag\": \"\" ,\n" +
			"          \"opShowFlag\": \"\" ,\n" +
			"          \"hImg\": \"\" ,\n" +
			"          \"vImg\": \"\" ,\n" +
			"          \"opimg1\": \"\" ,\n" +
			"          \"opimg2\": \"\" ,\n" +
			"          \"linkType\": \"cn.gd.snm.list.SeriesListActivity\" ,\n" +
			"          \"dataLink\": \"http://192.168.220.127:58187/epg/api/page/biz_86858576.json\"\n" +
			"        }\n" +
			"      ]\n" +
			"    } ,\n" +
			"    {\n" +
			"      \"areaCode\": \"3\" ,\n" +
			"      \"areaType\": \"image\" ,\n" +
			"      \"areaRect\": \"846,156,364,204\" ,\n" +
			"      \"scaleSize\": \"1.1\" ,\n" +
			"      \"areaTitle\": \"移动3.0_更多_全部类型第一排_横图推荐位3\" ,\n" +
			"      \"areaIcon\": \"\" ,\n" +
			"      \"defaultImgType\": \"\" ,\n" +
			"      \"dataLink\": \"\" ,\n" +
			"      \"items\": [\n" +
			"        {\n" +
			"          \"isPlayNotReal\": \"false\" ,\n" +
			"          \"poscode\": \"BizPosition_9293\" ,\n" +
			"          \"itemTitle\": \"应用商城\" ,\n" +
			"          \"itemSubTitle\": \"\" ,\n" +
			"          \"itemCode\": \"link_39894232\" ,\n" +
			"          \"itemType\": \"link\" ,\n" +
			"          \"itemIcon\": \"http://192.168.220.127:58187/epg/resource/position/202003/09/2020030910304615874099.png\" ,\n" +
			"          \"itemIcon2\": \"\" ,\n" +
			"          \"showFlag\": \"\" ,\n" +
			"          \"opShowFlag\": \"\" ,\n" +
			"          \"hImg\": \"\" ,\n" +
			"          \"vImg\": \"\" ,\n" +
			"          \"opimg1\": \"\" ,\n" +
			"          \"opimg2\": \"\" ,\n" +
			"          \"linkType\": \"\" ,\n" +
			"          \"dataLink\": \"http://192.168.220.127:58187/epg/api/link/link_39894232.json\"\n" +
			"        }\n" +
			"      ]\n" +
			"    } ,\n" +
			"    {\n" +
			"      \"areaCode\": \"1\" ,\n" +
			"      \"areaType\": \"image\" ,\n" +
			"      \"areaRect\": \"70,156,364,204\" ,\n" +
			"      \"scaleSize\": \"1.1\" ,\n" +
			"      \"areaTitle\": \"移动3.0_更多_全部类型第一排_横图推荐位1\" ,\n" +
			"      \"areaIcon\": \"\" ,\n" +
			"      \"defaultImgType\": \"\" ,\n" +
			"      \"dataLink\": \"\" ,\n" +
			"      \"items\": [\n" +
			"        {\n" +
			"          \"isPlayNotReal\": \"false\" ,\n" +
			"          \"poscode\": \"BizPosition_9289\" ,\n" +
			"          \"itemTitle\": \"#南传移动EPG#动态模板#推荐#2018推荐专题#更多排行榜#电视剧\" ,\n" +
			"          \"itemSubTitle\": \"\" ,\n" +
			"          \"itemCode\": \"biz_52309684\" ,\n" +
			"          \"itemType\": \"biz\" ,\n" +
			"          \"itemIcon\": \"http://192.168.220.127:58187/epg/resource/position/202003/09/2020030910310274002379.png\" ,\n" +
			"          \"itemIcon2\": \"\" ,\n" +
			"          \"showFlag\": \"\" ,\n" +
			"          \"opShowFlag\": \"\" ,\n" +
			"          \"hImg\": \"\" ,\n" +
			"          \"vImg\": \"\" ,\n" +
			"          \"opimg1\": \"\" ,\n" +
			"          \"opimg2\": \"\" ,\n" +
			"          \"linkType\": \"cn.gd.snm.template.cinema.CinemaActivity\" ,\n" +
			"          \"dataLink\": \"http://192.168.220.127:58187/epg/api/page/biz_52309684.json\"\n" +
			"        }\n" +
			"      ]\n" +
			"    } ,\n" +
			"    {\n" +
			"      \"areaCode\": \"2\" ,\n" +
			"      \"areaType\": \"image\" ,\n" +
			"      \"areaRect\": \"458,156,364,204\" ,\n" +
			"      \"scaleSize\": \"1.1\" ,\n" +
			"      \"areaTitle\": \"移动3.0_更多_全部类型第一排_横图推荐位2\" ,\n" +
			"      \"areaIcon\": \"\" ,\n" +
			"      \"defaultImgType\": \"\" ,\n" +
			"      \"dataLink\": \"\" ,\n" +
			"      \"items\": [\n" +
			"        {\n" +
			"          \"isPlayNotReal\": \"false\" ,\n" +
			"          \"poscode\": \"BizPosition_9291\" ,\n" +
			"          \"itemTitle\": \"#南传移动EPG#动态模板#推荐#专题集合#最新上线\" ,\n" +
			"          \"itemSubTitle\": \"\" ,\n" +
			"          \"itemCode\": \"biz_86858576\" ,\n" +
			"          \"itemType\": \"biz\" ,\n" +
			"          \"itemIcon\": \"http://192.168.220.127:58187/epg/resource/position/202003/09/2020030910305422197551.png\" ,\n" +
			"          \"itemIcon2\": \"\" ,\n" +
			"          \"showFlag\": \"\" ,\n" +
			"          \"opShowFlag\": \"\" ,\n" +
			"          \"hImg\": \"\" ,\n" +
			"          \"vImg\": \"\" ,\n" +
			"          \"opimg1\": \"\" ,\n" +
			"          \"opimg2\": \"\" ,\n" +
			"          \"linkType\": \"cn.gd.snm.list.SeriesListActivity\" ,\n" +
			"          \"dataLink\": \"http://192.168.220.127:58187/epg/api/page/biz_86858576.json\"\n" +
			"        }\n" +
			"      ]\n" +
			"    } ,\n" +
			"    {\n" +
			"      \"areaCode\": \"3\" ,\n" +
			"      \"areaType\": \"image\" ,\n" +
			"      \"areaRect\": \"846,156,364,204\" ,\n" +
			"      \"scaleSize\": \"1.1\" ,\n" +
			"      \"areaTitle\": \"移动3.0_更多_全部类型第一排_横图推荐位3\" ,\n" +
			"      \"areaIcon\": \"\" ,\n" +
			"      \"defaultImgType\": \"\" ,\n" +
			"      \"dataLink\": \"\" ,\n" +
			"      \"items\": [\n" +
			"        {\n" +
			"          \"isPlayNotReal\": \"false\" ,\n" +
			"          \"poscode\": \"BizPosition_9293\" ,\n" +
			"          \"itemTitle\": \"应用商城\" ,\n" +
			"          \"itemSubTitle\": \"\" ,\n" +
			"          \"itemCode\": \"link_39894232\" ,\n" +
			"          \"itemType\": \"link\" ,\n" +
			"          \"itemIcon\": \"http://192.168.220.127:58187/epg/resource/position/202003/09/2020030910304615874099.png\" ,\n" +
			"          \"itemIcon2\": \"\" ,\n" +
			"          \"showFlag\": \"\" ,\n" +
			"          \"opShowFlag\": \"\" ,\n" +
			"          \"hImg\": \"\" ,\n" +
			"          \"vImg\": \"\" ,\n" +
			"          \"opimg1\": \"\" ,\n" +
			"          \"opimg2\": \"\" ,\n" +
			"          \"linkType\": \"\" ,\n" +
			"          \"dataLink\": \"http://192.168.220.127:58187/epg/api/link/link_39894232.json\"\n" +
			"        }\n" +
			"      ]\n" +
			"    } ,\n" +
			"    {\n" +
			"      \"areaCode\": \"1\" ,\n" +
			"      \"areaType\": \"image\" ,\n" +
			"      \"areaRect\": \"70,156,364,204\" ,\n" +
			"      \"scaleSize\": \"1.1\" ,\n" +
			"      \"areaTitle\": \"移动3.0_更多_全部类型第一排_横图推荐位1\" ,\n" +
			"      \"areaIcon\": \"\" ,\n" +
			"      \"defaultImgType\": \"\" ,\n" +
			"      \"dataLink\": \"\" ,\n" +
			"      \"items\": [\n" +
			"        {\n" +
			"          \"isPlayNotReal\": \"false\" ,\n" +
			"          \"poscode\": \"BizPosition_9289\" ,\n" +
			"          \"itemTitle\": \"#南传移动EPG#动态模板#推荐#2018推荐专题#更多排行榜#电视剧\" ,\n" +
			"          \"itemSubTitle\": \"\" ,\n" +
			"          \"itemCode\": \"biz_52309684\" ,\n" +
			"          \"itemType\": \"biz\" ,\n" +
			"          \"itemIcon\": \"http://192.168.220.127:58187/epg/resource/position/202003/09/2020030910310274002379.png\" ,\n" +
			"          \"itemIcon2\": \"\" ,\n" +
			"          \"showFlag\": \"\" ,\n" +
			"          \"opShowFlag\": \"\" ,\n" +
			"          \"hImg\": \"\" ,\n" +
			"          \"vImg\": \"\" ,\n" +
			"          \"opimg1\": \"\" ,\n" +
			"          \"opimg2\": \"\" ,\n" +
			"          \"linkType\": \"cn.gd.snm.template.cinema.CinemaActivity\" ,\n" +
			"          \"dataLink\": \"http://192.168.220.127:58187/epg/api/page/biz_52309684.json\"\n" +
			"        }\n" +
			"      ]\n" +
			"    } ,\n" +
			"    {\n" +
			"      \"areaCode\": \"2\" ,\n" +
			"      \"areaType\": \"image\" ,\n" +
			"      \"areaRect\": \"458,156,364,204\" ,\n" +
			"      \"scaleSize\": \"1.1\" ,\n" +
			"      \"areaTitle\": \"移动3.0_更多_全部类型第一排_横图推荐位2\" ,\n" +
			"      \"areaIcon\": \"\" ,\n" +
			"      \"defaultImgType\": \"\" ,\n" +
			"      \"dataLink\": \"\" ,\n" +
			"      \"items\": [\n" +
			"        {\n" +
			"          \"isPlayNotReal\": \"false\" ,\n" +
			"          \"poscode\": \"BizPosition_9291\" ,\n" +
			"          \"itemTitle\": \"#南传移动EPG#动态模板#推荐#专题集合#最新上线\" ,\n" +
			"          \"itemSubTitle\": \"\" ,\n" +
			"          \"itemCode\": \"biz_86858576\" ,\n" +
			"          \"itemType\": \"biz\" ,\n" +
			"          \"itemIcon\": \"http://192.168.220.127:58187/epg/resource/position/202003/09/2020030910305422197551.png\" ,\n" +
			"          \"itemIcon2\": \"\" ,\n" +
			"          \"showFlag\": \"\" ,\n" +
			"          \"opShowFlag\": \"\" ,\n" +
			"          \"hImg\": \"\" ,\n" +
			"          \"vImg\": \"\" ,\n" +
			"          \"opimg1\": \"\" ,\n" +
			"          \"opimg2\": \"\" ,\n" +
			"          \"linkType\": \"cn.gd.snm.list.SeriesListActivity\" ,\n" +
			"          \"dataLink\": \"http://192.168.220.127:58187/epg/api/page/biz_86858576.json\"\n" +
			"        }\n" +
			"      ]\n" +
			"    } ,\n" +
			"    {\n" +
			"      \"areaCode\": \"3\" ,\n" +
			"      \"areaType\": \"image\" ,\n" +
			"      \"areaRect\": \"846,156,364,204\" ,\n" +
			"      \"scaleSize\": \"1.1\" ,\n" +
			"      \"areaTitle\": \"移动3.0_更多_全部类型第一排_横图推荐位3\" ,\n" +
			"      \"areaIcon\": \"\" ,\n" +
			"      \"defaultImgType\": \"\" ,\n" +
			"      \"dataLink\": \"\" ,\n" +
			"      \"items\": [\n" +
			"        {\n" +
			"          \"isPlayNotReal\": \"false\" ,\n" +
			"          \"poscode\": \"BizPosition_9293\" ,\n" +
			"          \"itemTitle\": \"应用商城\" ,\n" +
			"          \"itemSubTitle\": \"\" ,\n" +
			"          \"itemCode\": \"link_39894232\" ,\n" +
			"          \"itemType\": \"link\" ,\n" +
			"          \"itemIcon\": \"http://192.168.220.127:58187/epg/resource/position/202003/09/2020030910304615874099.png\" ,\n" +
			"          \"itemIcon2\": \"\" ,\n" +
			"          \"showFlag\": \"\" ,\n" +
			"          \"opShowFlag\": \"\" ,\n" +
			"          \"hImg\": \"\" ,\n" +
			"          \"vImg\": \"\" ,\n" +
			"          \"opimg1\": \"\" ,\n" +
			"          \"opimg2\": \"\" ,\n" +
			"          \"linkType\": \"\" ,\n" +
			"          \"dataLink\": \"http://192.168.220.127:58187/epg/api/link/link_39894232.json\"\n" +
			"        }\n" +
			"      ]\n" +
			"    } ,\n" +
			"    {\n" +
			"      \"areaCode\": \"1\" ,\n" +
			"      \"areaType\": \"image\" ,\n" +
			"      \"areaRect\": \"70,156,364,204\" ,\n" +
			"      \"scaleSize\": \"1.1\" ,\n" +
			"      \"areaTitle\": \"移动3.0_更多_全部类型第一排_横图推荐位1\" ,\n" +
			"      \"areaIcon\": \"\" ,\n" +
			"      \"defaultImgType\": \"\" ,\n" +
			"      \"dataLink\": \"\" ,\n" +
			"      \"items\": [\n" +
			"        {\n" +
			"          \"isPlayNotReal\": \"false\" ,\n" +
			"          \"poscode\": \"BizPosition_9289\" ,\n" +
			"          \"itemTitle\": \"#南传移动EPG#动态模板#推荐#2018推荐专题#更多排行榜#电视剧\" ,\n" +
			"          \"itemSubTitle\": \"\" ,\n" +
			"          \"itemCode\": \"biz_52309684\" ,\n" +
			"          \"itemType\": \"biz\" ,\n" +
			"          \"itemIcon\": \"http://192.168.220.127:58187/epg/resource/position/202003/09/2020030910310274002379.png\" ,\n" +
			"          \"itemIcon2\": \"\" ,\n" +
			"          \"showFlag\": \"\" ,\n" +
			"          \"opShowFlag\": \"\" ,\n" +
			"          \"hImg\": \"\" ,\n" +
			"          \"vImg\": \"\" ,\n" +
			"          \"opimg1\": \"\" ,\n" +
			"          \"opimg2\": \"\" ,\n" +
			"          \"linkType\": \"cn.gd.snm.template.cinema.CinemaActivity\" ,\n" +
			"          \"dataLink\": \"http://192.168.220.127:58187/epg/api/page/biz_52309684.json\"\n" +
			"        }\n" +
			"      ]\n" +
			"    } ,\n" +
			"    {\n" +
			"      \"areaCode\": \"2\" ,\n" +
			"      \"areaType\": \"image\" ,\n" +
			"      \"areaRect\": \"458,156,364,204\" ,\n" +
			"      \"scaleSize\": \"1.1\" ,\n" +
			"      \"areaTitle\": \"移动3.0_更多_全部类型第一排_横图推荐位2\" ,\n" +
			"      \"areaIcon\": \"\" ,\n" +
			"      \"defaultImgType\": \"\" ,\n" +
			"      \"dataLink\": \"\" ,\n" +
			"      \"items\": [\n" +
			"        {\n" +
			"          \"isPlayNotReal\": \"false\" ,\n" +
			"          \"poscode\": \"BizPosition_9291\" ,\n" +
			"          \"itemTitle\": \"#南传移动EPG#动态模板#推荐#专题集合#最新上线\" ,\n" +
			"          \"itemSubTitle\": \"\" ,\n" +
			"          \"itemCode\": \"biz_86858576\" ,\n" +
			"          \"itemType\": \"biz\" ,\n" +
			"          \"itemIcon\": \"http://192.168.220.127:58187/epg/resource/position/202003/09/2020030910305422197551.png\" ,\n" +
			"          \"itemIcon2\": \"\" ,\n" +
			"          \"showFlag\": \"\" ,\n" +
			"          \"opShowFlag\": \"\" ,\n" +
			"          \"hImg\": \"\" ,\n" +
			"          \"vImg\": \"\" ,\n" +
			"          \"opimg1\": \"\" ,\n" +
			"          \"opimg2\": \"\" ,\n" +
			"          \"linkType\": \"cn.gd.snm.list.SeriesListActivity\" ,\n" +
			"          \"dataLink\": \"http://192.168.220.127:58187/epg/api/page/biz_86858576.json\"\n" +
			"        }\n" +
			"      ]\n" +
			"    } ,\n" +
			"    {\n" +
			"      \"areaCode\": \"3\" ,\n" +
			"      \"areaType\": \"image\" ,\n" +
			"      \"areaRect\": \"846,156,364,204\" ,\n" +
			"      \"scaleSize\": \"1.1\" ,\n" +
			"      \"areaTitle\": \"移动3.0_更多_全部类型第一排_横图推荐位3\" ,\n" +
			"      \"areaIcon\": \"\" ,\n" +
			"      \"defaultImgType\": \"\" ,\n" +
			"      \"dataLink\": \"\" ,\n" +
			"      \"items\": [\n" +
			"        {\n" +
			"          \"isPlayNotReal\": \"false\" ,\n" +
			"          \"poscode\": \"BizPosition_9293\" ,\n" +
			"          \"itemTitle\": \"应用商城\" ,\n" +
			"          \"itemSubTitle\": \"\" ,\n" +
			"          \"itemCode\": \"link_39894232\" ,\n" +
			"          \"itemType\": \"link\" ,\n" +
			"          \"itemIcon\": \"http://192.168.220.127:58187/epg/resource/position/202003/09/2020030910304615874099.png\" ,\n" +
			"          \"itemIcon2\": \"\" ,\n" +
			"          \"showFlag\": \"\" ,\n" +
			"          \"opShowFlag\": \"\" ,\n" +
			"          \"hImg\": \"\" ,\n" +
			"          \"vImg\": \"\" ,\n" +
			"          \"opimg1\": \"\" ,\n" +
			"          \"opimg2\": \"\" ,\n" +
			"          \"linkType\": \"\" ,\n" +
			"          \"dataLink\": \"http://192.168.220.127:58187/epg/api/link/link_39894232.json\"\n" +
			"        }\n" +
			"      ]\n" +
			"    } ,\n" +
			"    {\n" +
			"      \"areaCode\": \"1\" ,\n" +
			"      \"areaType\": \"image\" ,\n" +
			"      \"areaRect\": \"70,156,364,204\" ,\n" +
			"      \"scaleSize\": \"1.1\" ,\n" +
			"      \"areaTitle\": \"移动3.0_更多_全部类型第一排_横图推荐位1\" ,\n" +
			"      \"areaIcon\": \"\" ,\n" +
			"      \"defaultImgType\": \"\" ,\n" +
			"      \"dataLink\": \"\" ,\n" +
			"      \"items\": [\n" +
			"        {\n" +
			"          \"isPlayNotReal\": \"false\" ,\n" +
			"          \"poscode\": \"BizPosition_9289\" ,\n" +
			"          \"itemTitle\": \"#南传移动EPG#动态模板#推荐#2018推荐专题#更多排行榜#电视剧\" ,\n" +
			"          \"itemSubTitle\": \"\" ,\n" +
			"          \"itemCode\": \"biz_52309684\" ,\n" +
			"          \"itemType\": \"biz\" ,\n" +
			"          \"itemIcon\": \"http://192.168.220.127:58187/epg/resource/position/202003/09/2020030910310274002379.png\" ,\n" +
			"          \"itemIcon2\": \"\" ,\n" +
			"          \"showFlag\": \"\" ,\n" +
			"          \"opShowFlag\": \"\" ,\n" +
			"          \"hImg\": \"\" ,\n" +
			"          \"vImg\": \"\" ,\n" +
			"          \"opimg1\": \"\" ,\n" +
			"          \"opimg2\": \"\" ,\n" +
			"          \"linkType\": \"cn.gd.snm.template.cinema.CinemaActivity\" ,\n" +
			"          \"dataLink\": \"http://192.168.220.127:58187/epg/api/page/biz_52309684.json\"\n" +
			"        }\n" +
			"      ]\n" +
			"    } ,\n" +
			"    {\n" +
			"      \"areaCode\": \"2\" ,\n" +
			"      \"areaType\": \"image\" ,\n" +
			"      \"areaRect\": \"458,156,364,204\" ,\n" +
			"      \"scaleSize\": \"1.1\" ,\n" +
			"      \"areaTitle\": \"移动3.0_更多_全部类型第一排_横图推荐位2\" ,\n" +
			"      \"areaIcon\": \"\" ,\n" +
			"      \"defaultImgType\": \"\" ,\n" +
			"      \"dataLink\": \"\" ,\n" +
			"      \"items\": [\n" +
			"        {\n" +
			"          \"isPlayNotReal\": \"false\" ,\n" +
			"          \"poscode\": \"BizPosition_9291\" ,\n" +
			"          \"itemTitle\": \"#南传移动EPG#动态模板#推荐#专题集合#最新上线\" ,\n" +
			"          \"itemSubTitle\": \"\" ,\n" +
			"          \"itemCode\": \"biz_86858576\" ,\n" +
			"          \"itemType\": \"biz\" ,\n" +
			"          \"itemIcon\": \"http://192.168.220.127:58187/epg/resource/position/202003/09/2020030910305422197551.png\" ,\n" +
			"          \"itemIcon2\": \"\" ,\n" +
			"          \"showFlag\": \"\" ,\n" +
			"          \"opShowFlag\": \"\" ,\n" +
			"          \"hImg\": \"\" ,\n" +
			"          \"vImg\": \"\" ,\n" +
			"          \"opimg1\": \"\" ,\n" +
			"          \"opimg2\": \"\" ,\n" +
			"          \"linkType\": \"cn.gd.snm.list.SeriesListActivity\" ,\n" +
			"          \"dataLink\": \"http://192.168.220.127:58187/epg/api/page/biz_86858576.json\"\n" +
			"        }\n" +
			"      ]\n" +
			"    } ,\n" +
			"    {\n" +
			"      \"areaCode\": \"3\" ,\n" +
			"      \"areaType\": \"image\" ,\n" +
			"      \"areaRect\": \"846,156,364,204\" ,\n" +
			"      \"scaleSize\": \"1.1\" ,\n" +
			"      \"areaTitle\": \"移动3.0_更多_全部类型第一排_横图推荐位3\" ,\n" +
			"      \"areaIcon\": \"\" ,\n" +
			"      \"defaultImgType\": \"\" ,\n" +
			"      \"dataLink\": \"\" ,\n" +
			"      \"items\": [\n" +
			"        {\n" +
			"          \"isPlayNotReal\": \"false\" ,\n" +
			"          \"poscode\": \"BizPosition_9293\" ,\n" +
			"          \"itemTitle\": \"应用商城\" ,\n" +
			"          \"itemSubTitle\": \"\" ,\n" +
			"          \"itemCode\": \"link_39894232\" ,\n" +
			"          \"itemType\": \"link\" ,\n" +
			"          \"itemIcon\": \"http://192.168.220.127:58187/epg/resource/position/202003/09/2020030910304615874099.png\" ,\n" +
			"          \"itemIcon2\": \"\" ,\n" +
			"          \"showFlag\": \"\" ,\n" +
			"          \"opShowFlag\": \"\" ,\n" +
			"          \"hImg\": \"\" ,\n" +
			"          \"vImg\": \"\" ,\n" +
			"          \"opimg1\": \"\" ,\n" +
			"          \"opimg2\": \"\" ,\n" +
			"          \"linkType\": \"\" ,\n" +
			"          \"dataLink\": \"http://192.168.220.127:58187/epg/api/link/link_39894232.json\"\n" +
			"        }\n" +
			"      ]\n" +
			"    }\n" +
			"  ]\n" +
			"}";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_i_o_and_c_p_u_test);
		ButterKnife.bind(this);
		startAnim();
		MainHandler.getInstance().postDelayed(new Runnable() {
			@Override
			public void run() {
				startTask();
			}
		}, 2000);

	}

	private void startTask() {
		for (int i = 0; i < 10; i++) {
			Observable.create(new ObservableOnSubscribe<Page>() {
				@Override
				public void subscribe(@NonNull ObservableEmitter<Page> emitter) throws Exception {
					HiLogger.i(TAG, "开始序列化");
					Page page = new Gson().fromJson(new JsonReader(new InputStreamReader(IOAndCPUTestActivity.this.getResources().openRawResource(R.raw.page))), Page.class);
					HiLogger.i(TAG, "完成序列化");
					emitter.onNext(page);
					emitter.onComplete();
				}
			}).subscribeOn(Schedulers.computation())
					.subscribe(new Consumer<Page>() {
						@Override
						public void accept(Page page) throws Exception {
//							HiLogger.i(TAG, "onNext");
						}
					}, new Consumer<Throwable>() {
						@Override
						public void accept(Throwable throwable) throws Exception {
//							HiLogger.i(TAG, "onError");
						}
					});
		}
	}

	private void startAnim() {
		RotateAnimation rotateAnimation = new RotateAnimation(0, 360);
		rotateAnimation.setRepeatCount(Animation.INFINITE);
		rotateAnimation.setRepeatMode(Animation.REVERSE);
		rotateAnimation.setDuration(5000);
		textView.setAnimation(rotateAnimation);
		rotateAnimation.start();
	}
}