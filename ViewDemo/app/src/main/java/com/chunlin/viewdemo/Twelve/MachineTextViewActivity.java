package com.chunlin.viewdemo.Twelve;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import butterknife.Bind;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.YAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.gson.Gson;
import com.chunlin.viewdemo.MyMarkerView;
import com.chunlin.viewdemo.R;
import com.chunlin.viewdemo.Third.BaseActivity;
import com.chunlin.viewdemo.entity.DetailTimeMachineEntity;
import com.chunlin.viewdemo.tools.ObjectUtil;
import com.chunlin.viewdemo.tools.TimeUtil;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MachineTextViewActivity extends BaseActivity {
    @Bind(R.id.tv)
    TextView mTextView;
    @Bind(R.id.chart0)
    LineChart timeMachineLintChart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mTextView.setMovementMethod(new ScrollingMovementMethod());
        //mTextView.setText("aaaaaa\nbbbbbbbb\nccccccccccc\ndddd\neeeeee\nffffff\nggggg");
        reQ();
    }

    List<DetailTimeMachineEntity.DataBean.PriceArrBean> price_arrList = new ArrayList<>();

    private void reQ() {
        String response = "{\n" +
                "\t\"error\": 0,\n" +
                "\t\"code\": 200,\n" +
                "\t\"message\": \"\\u6210\\u529f\\u8fd4\\u56de\",\n" +
                "\t\"data\": {\n" +
                "\t\t\"price_arr\": [{\n" +
                "\t\t\t\"date\": 20170709,\n" +
                "\t\t\t\"data\": [{\n" +
                "\t\t\t\t\"id\": \"2284955\",\n" +
                "\t\t\t\t\"gov_id\": \"2315825\",\n" +
                "\t\t\t\t\"source\": \"62\",\n" +
                "\t\t\t\t\"company_id\": \"62\",\n" +
                "\t\t\t\t\"now_created\": \"1499951152\",\n" +
                "\t\t\t\t\"pre_created\": \"1499951152\",\n" +
                "\t\t\t\t\"source_url\": \"http:\\/\\/wx.55555558.com\\/Weixin\\/fanginfo.html?t=2sf&hid=2DDD35EB-F6C4-47F9-ACCC-E15C3CBC0794\",\n" +
                "\t\t\t\t\"pre_house_price\": \"52.00\",\n" +
                "\t\t\t\t\"now_house_price\": \"52.00\",\n" +
                "\t\t\t\t\"house_area\": \"81.00\",\n" +
                "\t\t\t\t\"house_room\": \"2\",\n" +
                "\t\t\t\t\"house_floor\": \"4\",\n" +
                "\t\t\t\t\"borough_id\": \"1000787\",\n" +
                "\t\t\t\t\"borough_name\": \"\\u5b8f\\u56fe\\u5c0f\\u533a\",\n" +
                "\t\t\t\t\"cityarea_id\": \"2\",\n" +
                "\t\t\t\t\"change_type\": \"0\",\n" +
                "\t\t\t\t\"change_range\": \"0.0000\",\n" +
                "\t\t\t\t\"change_value\": \"0.0000\",\n" +
                "\t\t\t\t\"type\": \"1\",\n" +
                "\t\t\t\t\"cityarea2_id\": \"240\",\n" +
                "\t\t\t\t\"source_name\": \"\\u94fe\\u5bb6\",\n" +
                "\t\t\t\t\"desc\": \"\\u9a84\\u9633\\u4e0a\\u67b6\\uff0c\\u62a5\\u4ef7\\uff1a52.00\"\n" +
                "\t\t\t}]\n" +
                "\t\t},{\n" +
                "\t\t\t\"date\": 20170709,\n" +
                "\t\t\t\"data\": [{\n" +
                "\t\t\t\t\"id\": \"2284955\",\n" +
                "\t\t\t\t\"gov_id\": \"2315825\",\n" +
                "\t\t\t\t\"source\": \"62\",\n" +
                "\t\t\t\t\"company_id\": \"62\",\n" +
                "\t\t\t\t\"now_created\": \"1499951152\",\n" +
                "\t\t\t\t\"pre_created\": \"1499951152\",\n" +
                "\t\t\t\t\"source_url\": \"http:\\/\\/wx.55555558.com\\/Weixin\\/fanginfo.html?t=2sf&hid=2DDD35EB-F6C4-47F9-ACCC-E15C3CBC0794\",\n" +
                "\t\t\t\t\"pre_house_price\": \"53.00\",\n" +
                "\t\t\t\t\"now_house_price\": \"53.00\",\n" +
                "\t\t\t\t\"house_area\": \"81.00\",\n" +
                "\t\t\t\t\"house_room\": \"2\",\n" +
                "\t\t\t\t\"house_floor\": \"4\",\n" +
                "\t\t\t\t\"borough_id\": \"1000787\",\n" +
                "\t\t\t\t\"borough_name\": \"\\u5b8f\\u56fe\\u5c0f\\u533a\",\n" +
                "\t\t\t\t\"cityarea_id\": \"2\",\n" +
                "\t\t\t\t\"change_type\": \"0\",\n" +
                "\t\t\t\t\"change_range\": \"0.0000\",\n" +
                "\t\t\t\t\"change_value\": \"0.0000\",\n" +
                "\t\t\t\t\"type\": \"1\",\n" +
                "\t\t\t\t\"cityarea2_id\": \"240\",\n" +
                "\t\t\t\t\"source_name\": \"\\u9a84\\u9633\",\n" +
                "\t\t\t\t\"desc\": \"\\u9a84\\u9633\\u4e0a\\u67b6\\uff0c\\u62a5\\u4ef7\\uff1a52.00\"\n" +
                "\t\t\t}]\n" +
                "\t\t},{\n" +
                "\t\t\t\"date\": 20170709,\n" +
                "\t\t\t\"data\": [{\n" +
                "\t\t\t\t\"id\": \"2284955\",\n" +
                "\t\t\t\t\"gov_id\": \"2315825\",\n" +
                "\t\t\t\t\"source\": \"62\",\n" +
                "\t\t\t\t\"company_id\": \"62\",\n" +
                "\t\t\t\t\"now_created\": \"1499951152\",\n" +
                "\t\t\t\t\"pre_created\": \"1499951152\",\n" +
                "\t\t\t\t\"source_url\": \"http:\\/\\/wx.55555558.com\\/Weixin\\/fanginfo.html?t=2sf&hid=2DDD35EB-F6C4-47F9-ACCC-E15C3CBC0794\",\n" +
                "\t\t\t\t\"pre_house_price\": \"54.00\",\n" +
                "\t\t\t\t\"now_house_price\": \"54.00\",\n" +
                "\t\t\t\t\"house_area\": \"81.00\",\n" +
                "\t\t\t\t\"house_room\": \"2\",\n" +
                "\t\t\t\t\"house_floor\": \"4\",\n" +
                "\t\t\t\t\"borough_id\": \"1000787\",\n" +
                "\t\t\t\t\"borough_name\": \"\\u5b8f\\u56fe\\u5c0f\\u533a\",\n" +
                "\t\t\t\t\"cityarea_id\": \"2\",\n" +
                "\t\t\t\t\"change_type\": \"0\",\n" +
                "\t\t\t\t\"change_range\": \"0.0000\",\n" +
                "\t\t\t\t\"change_value\": \"0.0000\",\n" +
                "\t\t\t\t\"type\": \"1\",\n" +
                "\t\t\t\t\"cityarea2_id\": \"240\",\n" +
                "\t\t\t\t\"source_name\": \"\\u5927\\u76db\\u5730\\u4ea7\",\n" +
                "\t\t\t\t\"desc\": \"\\u9a84\\u9633\\u4e0a\\u67b6\\uff0c\\u62a5\\u4ef7\\uff1a52.00\"\n" +
                "\t\t\t}]\n" +
                "\t\t},{\n" +
                "\t\t\t\"date\": 20170709,\n" +
                "\t\t\t\"data\": [{\n" +
                "\t\t\t\t\"id\": \"2284955\",\n" +
                "\t\t\t\t\"gov_id\": \"2315825\",\n" +
                "\t\t\t\t\"source\": \"62\",\n" +
                "\t\t\t\t\"company_id\": \"62\",\n" +
                "\t\t\t\t\"now_created\": \"1499951152\",\n" +
                "\t\t\t\t\"pre_created\": \"1499951152\",\n" +
                "\t\t\t\t\"source_url\": \"http:\\/\\/wx.55555558.com\\/Weixin\\/fanginfo.html?t=2sf&hid=2DDD35EB-F6C4-47F9-ACCC-E15C3CBC0794\",\n" +
                "\t\t\t\t\"pre_house_price\": \"55.00\",\n" +
                "\t\t\t\t\"now_house_price\": \"55.00\",\n" +
                "\t\t\t\t\"house_area\": \"81.00\",\n" +
                "\t\t\t\t\"house_room\": \"2\",\n" +
                "\t\t\t\t\"house_floor\": \"4\",\n" +
                "\t\t\t\t\"borough_id\": \"1000787\",\n" +
                "\t\t\t\t\"borough_name\": \"\\u5b8f\\u56fe\\u5c0f\\u533a\",\n" +
                "\t\t\t\t\"cityarea_id\": \"2\",\n" +
                "\t\t\t\t\"change_type\": \"0\",\n" +
                "\t\t\t\t\"change_range\": \"0.0000\",\n" +
                "\t\t\t\t\"change_value\": \"0.0000\",\n" +
                "\t\t\t\t\"type\": \"1\",\n" +
                "\t\t\t\t\"cityarea2_id\": \"240\",\n" +
                "\t\t\t\t\"source_name\": \"\\u5b59\\u609f\\u7a7a\",\n" +
                "\t\t\t\t\"desc\": \"\\u9a84\\u9633\\u4e0a\\u67b6\\uff0c\\u62a5\\u4ef7\\uff1a52.00\"\n" +
                "\t\t\t}]\n" +
                "\t\t},{\n" +
                "\t\t\t\"date\": 20170709,\n" +
                "\t\t\t\"data\": [{\n" +
                "\t\t\t\t\"id\": \"2284955\",\n" +
                "\t\t\t\t\"gov_id\": \"2315825\",\n" +
                "\t\t\t\t\"source\": \"62\",\n" +
                "\t\t\t\t\"company_id\": \"62\",\n" +
                "\t\t\t\t\"now_created\": \"1499951152\",\n" +
                "\t\t\t\t\"pre_created\": \"1499951152\",\n" +
                "\t\t\t\t\"source_url\": \"http:\\/\\/wx.55555558.com\\/Weixin\\/fanginfo.html?t=2sf&hid=2DDD35EB-F6C4-47F9-ACCC-E15C3CBC0794\",\n" +
                "\t\t\t\t\"pre_house_price\": \"56.00\",\n" +
                "\t\t\t\t\"now_house_price\": \"56.00\",\n" +
                "\t\t\t\t\"house_area\": \"81.00\",\n" +
                "\t\t\t\t\"house_room\": \"2\",\n" +
                "\t\t\t\t\"house_floor\": \"4\",\n" +
                "\t\t\t\t\"borough_id\": \"1000787\",\n" +
                "\t\t\t\t\"borough_name\": \"\\u5b8f\\u56fe\\u5c0f\\u533a\",\n" +
                "\t\t\t\t\"cityarea_id\": \"2\",\n" +
                "\t\t\t\t\"change_type\": \"0\",\n" +
                "\t\t\t\t\"change_range\": \"0.0000\",\n" +
                "\t\t\t\t\"change_value\": \"0.0000\",\n" +
                "\t\t\t\t\"type\": \"1\",\n" +
                "\t\t\t\t\"cityarea2_id\": \"240\",\n" +
                "\t\t\t\t\"source_name\": \"\\u732a\\u516b\\u6212\",\n" +
                "\t\t\t\t\"desc\": \"\\u9a84\\u9633\\u4e0a\\u67b6\\uff0c\\u62a5\\u4ef7\\uff1a52.00\"\n" +
                "\t\t\t}]\n" +
                "\t\t},{\n" +
                "\t\t\t\"date\": 20170714,\n" +
                "\t\t\t\"data\": [{\n" +
                "\t\t\t\t\"id\": \"2284955\",\n" +
                "\t\t\t\t\"gov_id\": \"2315825\",\n" +
                "\t\t\t\t\"source\": \"62\",\n" +
                "\t\t\t\t\"company_id\": \"62\",\n" +
                "\t\t\t\t\"now_created\": \"1499951152\",\n" +
                "\t\t\t\t\"pre_created\": \"1499951152\",\n" +
                "\t\t\t\t\"source_url\": \"http:\\/\\/wx.55555558.com\\/Weixin\\/fanginfo.html?t=2sf&hid=2DDD35EB-F6C4-47F9-ACCC-E15C3CBC0794\",\n" +
                "\t\t\t\t\"pre_house_price\": \"52.00\",\n" +
                "\t\t\t\t\"now_house_price\": \"52.00\",\n" +
                "\t\t\t\t\"house_area\": \"81.00\",\n" +
                "\t\t\t\t\"house_room\": \"2\",\n" +
                "\t\t\t\t\"house_floor\": \"4\",\n" +
                "\t\t\t\t\"borough_id\": \"1000787\",\n" +
                "\t\t\t\t\"borough_name\": \"\\u5b8f\\u56fe\\u5c0f\\u533a\",\n" +
                "\t\t\t\t\"cityarea_id\": \"2\",\n" +
                "\t\t\t\t\"change_type\": \"0\",\n" +
                "\t\t\t\t\"change_range\": \"0.0000\",\n" +
                "\t\t\t\t\"change_value\": \"0.0000\",\n" +
                "\t\t\t\t\"type\": \"1\",\n" +
                "\t\t\t\t\"cityarea2_id\": \"240\",\n" +
                "\t\t\t\t\"source_name\": \"\\u9a84\\u9633\",\n" +
                "\t\t\t\t\"desc\": \"\\u9a84\\u9633\\u4e0a\\u67b6\\uff0c\\u62a5\\u4ef7\\uff1a52.00\"\n" +
                "\t\t\t}]\n" +
                "\t\t},{\n" +
                "\t\t\t\"date\": 20170715,\n" +
                "\t\t\t\"data\": [{\n" +
                "\t\t\t\t\"id\": \"2284955\",\n" +
                "\t\t\t\t\"gov_id\": \"2315825\",\n" +
                "\t\t\t\t\"source\": \"62\",\n" +
                "\t\t\t\t\"company_id\": \"62\",\n" +
                "\t\t\t\t\"now_created\": \"1499951152\",\n" +
                "\t\t\t\t\"pre_created\": \"1499951152\",\n" +
                "\t\t\t\t\"source_url\": \"http:\\/\\/wx.55555558.com\\/Weixin\\/fanginfo.html?t=2sf&hid=2DDD35EB-F6C4-47F9-ACCC-E15C3CBC0794\",\n" +
                "\t\t\t\t\"pre_house_price\": \"52.00\",\n" +
                "\t\t\t\t\"now_house_price\": \"52.00\",\n" +
                "\t\t\t\t\"house_area\": \"81.00\",\n" +
                "\t\t\t\t\"house_room\": \"2\",\n" +
                "\t\t\t\t\"house_floor\": \"4\",\n" +
                "\t\t\t\t\"borough_id\": \"1000787\",\n" +
                "\t\t\t\t\"borough_name\": \"\\u5b8f\\u56fe\\u5c0f\\u533a\",\n" +
                "\t\t\t\t\"cityarea_id\": \"2\",\n" +
                "\t\t\t\t\"change_type\": \"0\",\n" +
                "\t\t\t\t\"change_range\": \"0.0000\",\n" +
                "\t\t\t\t\"change_value\": \"0.0000\",\n" +
                "\t\t\t\t\"type\": \"1\",\n" +
                "\t\t\t\t\"cityarea2_id\": \"240\",\n" +
                "\t\t\t\t\"source_name\": \"\\u9a84\\u9633\",\n" +
                "\t\t\t\t\"desc\": \"\\u9a84\\u9633\\u4e0a\\u67b6\\uff0c\\u62a5\\u4ef7\\uff1a52.00\"\n" +
                "\t\t\t}]\n" +
                "\t\t}, {\n" +
                "\t\t\t\"date\": 20170916,\n" +
                "\t\t\t\"data\": [{\n" +
                "\t\t\t\t\"id\": \"2779235\",\n" +
                "\t\t\t\t\"gov_id\": \"2768782\",\n" +
                "\t\t\t\t\"source\": \"76\",\n" +
                "\t\t\t\t\"company_id\": \"76\",\n" +
                "\t\t\t\t\"now_created\": \"1505397967\",\n" +
                "\t\t\t\t\"pre_created\": \"1505397967\",\n" +
                "\t\t\t\t\"source_url\": \"_www.hmjwang.com\\/FY-15-22337\",\n" +
                "\t\t\t\t\"pre_house_price\": \"52.80\",\n" +
                "\t\t\t\t\"now_house_price\": \"52.80\",\n" +
                "\t\t\t\t\"house_area\": \"81\",\n" +
                "\t\t\t\t\"house_room\": \"2\",\n" +
                "\t\t\t\t\"house_floor\": \"4\",\n" +
                "\t\t\t\t\"borough_id\": \"1000787\",\n" +
                "\t\t\t\t\"borough_name\": \"\\u5b8f\\u56fe\\u5c0f\\u533a\",\n" +
                "\t\t\t\t\"cityarea_id\": \"2\",\n" +
                "\t\t\t\t\"change_type\": \"0\",\n" +
                "\t\t\t\t\"change_range\": \"0.0000\",\n" +
                "\t\t\t\t\"change_value\": \"0.0000\",\n" +
                "\t\t\t\t\"type\": \"1\",\n" +
                "\t\t\t\t\"cityarea2_id\": \"240\",\n" +
                "\t\t\t\t\"source_name\": \"\\u548c\\u7f8e\\u5bb6\",\n" +
                "\t\t\t\t\"desc\": \"\\u548c\\u7f8e\\u5bb6\\u4e0a\\u67b6\\uff0c\\u62a5\\u4ef7\\uff1a52.80\"\n" +
                "\t\t\t}]\n" +
                "\t\t}, {\n" +
                "\t\t\t\"date\": \"20180309\",\n" +
                "\t\t\t\"data\": [{\n" +
                "\t\t\t\t\"house_floor\": \"4\",\n" +
                "\t\t\t\t\"source_name\": \"\\u9a84\\u9633\",\n" +
                "\t\t\t\t\"gov_id\": 2315825,\n" +
                "\t\t\t\t\"house_price\": 52,\n" +
                "\t\t\t\t\"tag\": \"#\",\n" +
                "\t\t\t\t\"service_phone\": \"\",\n" +
                "\t\t\t\t\"house_built_year\": \"\",\n" +
                "\t\t\t\t\"price_change_value\": 0,\n" +
                "\t\t\t\t\"id\": 2315825,\n" +
                "\t\t\t\t\"source\": 62,\n" +
                "\t\t\t\t\"house_toward\": 9,\n" +
                "\t\t\t\t\"price_updated\": 0,\n" +
                "\t\t\t\t\"owner_phone\": \"13654569092\",\n" +
                "\t\t\t\t\"updated\": 1503502152,\n" +
                "\t\t\t\t\"owner_name\": \"\\u90d1\\u4f1f\\u9f99\",\n" +
                "\t\t\t\t\"source_url\": \"http:\\/\\/wx.55555558.com\\/Weixin\\/fanginfo.html?t=2sf&hid=2DDD35EB-F6C4-47F9-ACCC-E15C3CBC0794\",\n" +
                "\t\t\t\t\"public_time\": 0,\n" +
                "\t\t\t\t\"house_title\": \"\\u5b8f\\u56fe\\u5c0f\\u533a 2\\u5ba41\\u53851\\u536b \\u4e2d\\u88c5\\u4fee\",\n" +
                "\t\t\t\t\"house_desc\": \"\\u6211\\u662f\\u9a84\\u9633\\u623f\\u5730\\u4ea7\\u7684\\u4e00\\u540d\\u7ecf\\u7eaa\\u4eba\\uff0c\\u7aed\\u8bda\\u4e3a\\u60a8\\u670d\\u52a1\\uff0c\\u8fd9\\u5957\\u623f\\u5b50\\u5728\\u5b8f\\u56fe\\u5c0f\\u533a\\u3002\\u6211\\u4fdd\\u8bc1\\u4fe1\\u606f\\u7684\\u771f\\u5b9e\\uff0c\\u5e76\\u671f\\u5f85\\u60a8\\u548c\\u6211\\u8054\\u7cfb!\",\n" +
                "\t\t\t\t\"created\": \"1499951694\",\n" +
                "\t\t\t\t\"price_change\": 0,\n" +
                "\t\t\t\t\"house_fitment\": 3,\n" +
                "\t\t\t\t\"broker_num\": 0,\n" +
                "\t\t\t\t\"source_logo\": \"http:\\/\\/file.chunlin.com\\/jiaoyang_80_80.png\",\n" +
                "\t\t\t\t\"desc\": \"\\u9a84\\u9633\\uff1a\\u62a5\\u4ef752\",\n" +
                "\t\t\t\t\"now_house_price\": 52\n" +
                "\t\t\t}, {\n" +
                "\t\t\t\t\"house_floor\": \"4\",\n" +
                "\t\t\t\t\"source_name\": \"\\u548c\\u7f8e\\u5bb6\",\n" +
                "\t\t\t\t\"gov_id\": 2768782,\n" +
                "\t\t\t\t\"house_price\": 52.8,\n" +
                "\t\t\t\t\"tag\": \"#\",\n" +
                "\t\t\t\t\"service_phone\": \"\",\n" +
                "\t\t\t\t\"house_built_year\": \"1998\",\n" +
                "\t\t\t\t\"price_change_value\": 0,\n" +
                "\t\t\t\t\"id\": 2315825,\n" +
                "\t\t\t\t\"source\": 76,\n" +
                "\t\t\t\t\"house_toward\": 9,\n" +
                "\t\t\t\t\"price_updated\": 0,\n" +
                "\t\t\t\t\"owner_phone\": \"13845970411\",\n" +
                "\t\t\t\t\"updated\": 1505708042,\n" +
                "\t\t\t\t\"owner_name\": \"\\u5f20\\u5f2f\",\n" +
                "\t\t\t\t\"source_url\": \"_www.hmjwang.com\\/FY-15-22337\",\n" +
                "\t\t\t\t\"public_time\": 0,\n" +
                "\t\t\t\t\"house_title\": \"\\u5b8f\\u56fe\\u5c0f\\u533a2\\u5ba41\\u538552.8\\u4e07\\u597d\\u623f\",\n" +
                "\t\t\t\t\"house_desc\": \"\\u6211\\u662f\\u548c\\u7f8e\\u5bb6\\u7684\\u4e00\\u540d\\u7ecf\\u7eaa\\u4eba\\uff0c\\u7aed\\u8bda\\u4e3a\\u60a8\\u670d\\u52a1\\uff0c\\u8fd9\\u5957\\u623f\\u5b50\\u5728\\u5b8f\\u56fe\\u5c0f\\u533a\\u3002\\u6211\\u4fdd\\u8bc1\\u4fe1\\u606f\\u7684\\u771f\\u5b9e\\uff0c\\u5e76\\u671f\\u5f85\\u60a8\\u548c\\u6211\\u8054\\u7cfb!\",\n" +
                "\t\t\t\t\"created\": \"1505397999\",\n" +
                "\t\t\t\t\"price_change\": 0,\n" +
                "\t\t\t\t\"house_fitment\": 2,\n" +
                "\t\t\t\t\"broker_num\": 0,\n" +
                "\t\t\t\t\"source_logo\": \"http:\\/\\/file.chunlin.com\\/hemeijia_80_80.png\",\n" +
                "\t\t\t\t\"desc\": \"\\u548c\\u7f8e\\u5bb6\\uff1a\\u62a5\\u4ef752.8\",\n" +
                "\t\t\t\t\"now_house_price\": 52.8\n" +
                "\t\t\t}]\n" +
                "\t\t}]\n" +
                "\t},\n" +
                "\t\"page\": [],\n" +
                "\t\"totalRunTime\": 3.7\n" +
                "}";

        DetailTimeMachineEntity entity = new Gson()
                .fromJson(response, DetailTimeMachineEntity.class);
        if (entity != null && entity.getCode() == 200 && entity.getError() == 0) {
            DetailTimeMachineEntity.DataBean dataBean = entity.getData();
            if (dataBean != null) {
                Object price_arrObj = dataBean.getPrice_arr();
                List<DetailTimeMachineEntity.DataBean.PriceArrBean> priceArrList = ObjectUtil
                        .changeToList(price_arrObj, DetailTimeMachineEntity.DataBean.PriceArrBean[].class);
                if (priceArrList != null && !priceArrList.isEmpty()) {
                    price_arrList.clear();
                    price_arrList.addAll(priceArrList);
                    initTimeMachineChartView(generateNewDataLineTimeMachine(priceArrList));//绘制折线图
                }
            }
        }
    }

    /*  第一个折线图价格分析图第二次设置*/


    protected LineData generateNewDataLineTimeMachine(List<DetailTimeMachineEntity.DataBean.PriceArrBean> listSourceData) {
        List<String> dateList = new ArrayList<>();
        Map<String, List<Entry>> map = new HashMap<>();
        int size = listSourceData.size();

        for (int i = 0; i < size; i++) {
            DetailTimeMachineEntity.DataBean.PriceArrBean priceArrBean = listSourceData.get(i);
            long date = priceArrBean.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat sd1 = new SimpleDateFormat("MM/dd");
            try {
                Date date1 = sdf.parse(date + "");
                String dateText = sd1.format(date1);
                dateList.add(dateText);
            } catch (ParseException e) {
                e.printStackTrace();
                dateList.add(date + "");
            }

            Object dataObj = priceArrBean.getData();
            List<DetailTimeMachineEntity.DataBean.PriceArrBean.DataBeanTimeMachine> priceArrDataBeans = ObjectUtil
                    .changeToList(dataObj, DetailTimeMachineEntity.DataBean.PriceArrBean.DataBeanTimeMachine[].class);
            if (priceArrDataBeans != null && !priceArrDataBeans.isEmpty()) {

                for (DetailTimeMachineEntity.DataBean.PriceArrBean.DataBeanTimeMachine bean : priceArrDataBeans) {
                    List<Entry> entries = map.get(bean.getSource_name());
                    if (entries == null) {
                        entries = new ArrayList<>();
                        map.put(bean.getSource_name(), entries);
                    }
                    String now_house_price = bean.getNow_house_price();
                    float v = 0;
                    try {
                        v = Float.parseFloat(now_house_price);
                        if (v > 0) {
                            //当同一家经济公司一天价格多次变化时 删除前面的价格
                            Iterator<Entry> iterator = entries.iterator();
                            while (iterator.hasNext()) {
                                Entry next = iterator.next();
                                if (next.getXIndex() == i) {
                                    iterator.remove();
                                }
                            }
                            entries.add(new Entry(v, i));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        ArrayList<ILineDataSet> sets = new ArrayList<>();
        Set<String> strings = map.keySet();
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()) {
            String source_name = iterator.next();
            List<Entry> yVals = map.get(source_name);
            if (yVals == null || yVals.isEmpty()) {
                break;
            }
            List<Entry> datayVals = new ArrayList<>();
            int lastIndex = yVals.size() - 1;
            int index = dateList.size();
            for (int i = dateList.size() - 1; i >= 0; i--) {
                Entry entry1 = yVals.get(lastIndex);
                int xIndex = entry1.getXIndex();

                while (xIndex < index) {
                    datayVals.add(new Entry(entry1.getVal(), xIndex));
                    xIndex++;
                }
                index = entry1.getXIndex();
                lastIndex--;
                if (lastIndex < 0) {
                    break;
                }
            }
            Collections.sort(datayVals, new Comparator<Entry>() {
                @Override
                public int compare(Entry lhs, Entry rhs) {
                    return lhs.getXIndex() - rhs.getXIndex();
                }
            });
            for (Entry e : datayVals) {
                Log.d(TAG, e.getXIndex() + "," + e.getVal());
            }
            LineDataSet d = new LineDataSet(datayVals, source_name);
            d.setLineWidth(2.5f);
            d.setCircleRadius(4.5f);
            d.setDrawValues(false);
            d.setDrawHorizontalHighlightIndicator(false);
            d.setHighLightColor(Color.parseColor("#878787"));
            d.setColor(Color.parseColor("#ACCB3C"));
            d.setCircleColor(Color.parseColor("#ACCB3C"));
            sets.add(d);
        }
        LineData cd = new LineData(dateList, sets);
        return cd;
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_machine_text_view;
    }


    /*第一个折线图*/
    protected void initTimeMachineChartView(final LineData lineData) {
        if (lineData == null) {
            return;
        }

        timeMachineLintChart.setDescription("");
        timeMachineLintChart.setTouchEnabled(true);
        // enable scaling andl
        timeMachineLintChart.setDragEnabled(false);// 是否可以拖拽dragging
        timeMachineLintChart.setScaleEnabled(false);// 是否可以缩放
        timeMachineLintChart.setHighlightPerTapEnabled(true);
        // if disabled, scaling can be done on x- and y-axis separately
        timeMachineLintChart.setPinchZoom(false);//
        //final MyMarkerView mv = new MyMarkerView(this, R.layout.time_machine_marker_view);
        final MyMarkerView mv = new MyMarkerView(this, R.layout.marker_view_rv);
        mv.setLineData(lineData);
        mv.setUnit("万");
        mv.setChartView(timeMachineLintChart);
        mv.setVerticalScrollBarEnabled(true);

        timeMachineLintChart.setMarkerView(mv);
        timeMachineLintChart.setDrawMarkerViews(true);
        XAxis xAxis = timeMachineLintChart.getXAxis();

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setYOffset(10);
        xAxis.setGridColor(Color.parseColor("#e7e7e7"));
        xAxis.setAxisLineColor(Color.parseColor("#e7e7e7"));
        int skip = lineData.getXVals().size() / 8;
        xAxis.setLabelsToSkip(skip);
        xAxis.setAvoidFirstLastClipping(true);
        xAxis.setTextColor(Color.parseColor("#707070"));
        xAxis.setTextColor(Color.parseColor("#ffffff"));//这样让x轴不显示
        YAxis leftAxis = timeMachineLintChart.getAxisLeft();
        leftAxis.enableGridDashedLine(10, 10, 0);
        leftAxis.setValueFormatter(new YAxisValueFormatter() {
            @Override
            public String getFormattedValue(float v, YAxis yAxis) {
                DecimalFormat decimalFormat = new DecimalFormat("###");
                if (v == 0) {
                    return "0";
                }
                return decimalFormat.format(v) + "万";
            }
        });
        leftAxis.setLabelCount(3, true);
        leftAxis.setGridColor(Color.parseColor("#e7e7e7"));
        leftAxis.setAxisLineColor(Color.parseColor("#e7e7e7"));

        int yMax = (int) lineData.getYMax();
        int yMin = (int) lineData.getYMin();
        //        int max = yMax + (yMax - yMin);
        //        int min = yMin - (yMax - yMin);
        //        if (max == min || min < 0) {
        //            leftAxis.setAxisMinValue(0);
        //        } else {
        //            leftAxis.setAxisMinValue(min);
        //        }
        if (yMax == 0) {
            yMax = 1;
        }
        //        float max = (float) (yMax * 1.1);
        //        float min = (float) (yMin * 0.9);
        leftAxis.setAxisMaxValue(TimeUtil.num2Max(yMax, true));
        leftAxis.setAxisMinValue(TimeUtil.num2Min(yMin, true));
        leftAxis.setTextColor(Color.parseColor("#707070"));
        YAxis rightAxis = timeMachineLintChart.getAxisRight();
        rightAxis.setEnabled(false);

        Legend legend = timeMachineLintChart.getLegend();
        //        legend.setYOffset(-5);
        legend.setTextSize(14);
        legend.setXEntrySpace(15);
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);
        legend.setWordWrapEnabled(true);
        timeMachineLintChart.setData(lineData);
        timeMachineLintChart.highlightValue(lineData.getXValCount() - 1, 0);
        timeMachineLintChart.getData().setHighlightEnabled(true);
        timeMachineLintChart.animateX(500);

    }
}
