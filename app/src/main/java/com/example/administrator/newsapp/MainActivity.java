package com.example.administrator.newsapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;
import com.example.administrator.newsapp.adapter.NewsAdapter;
import com.example.administrator.newsapp.finals.OtherFinals;
import com.example.administrator.newsapp.obj.Rows;
import com.example.administrator.newsapp.widget.CustomMeasureHeightListView;
import com.example.administrator.newsapp.widget.CustomProgressDialog;
import com.example.administrator.newsapp.widget.PullToRefreshView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
/***
 * spf
 * 2017.1.15
 */
public class MainActivity extends AppCompatActivity implements PullToRefreshView.OnHeaderRefreshListener,PullToRefreshView.OnFooterRefreshListener{

    //控件以及一些变量的声明
    private PullToRefreshView ptv;
    private CustomMeasureHeightListView mListView;
    private ArrayList<Rows> rowList=new ArrayList<>();
    private CustomProgressDialog dialog;
    //适配器的声明
    private NewsAdapter newsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//设置窗口无标题栏
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    /**
     * 初始化控件操作
     * */
    private void initView(){
        dialog=CustomProgressDialog.createDialog(this,"正在加载...");
        ptv=(PullToRefreshView)findViewById(R.id.ptv);
        ptv.setOnHeaderRefreshListener(this);
        ptv.setOnFooterRefreshListener(this);
        mListView = (CustomMeasureHeightListView)findViewById(R.id.clv);
        dialog.show();
    }

    /**
     * 初始化数据
     * */
    private void initData() {
        GetJsonData();
    }

    /**
     * 获取json数据
     * */
    public void GetJsonData(){
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(OtherFinals.JSON_URL,new JsonHttpResponseHandler(){
            //返回JSONObject对象|JSONOArray对象
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Gson g = new Gson();
                try {
                    //请求ActionBar需要显示的数据/
                    setTitle(response.getString("title"));
                    //请求listView的数据
                    JSONArray res=response.getJSONArray("rows");
                    Type type = new TypeToken<List<Rows>>(){}.getType();
                    rowList = g.fromJson(res.toString(), type);
                    newsAdapter=new NewsAdapter(MainActivity.this,rowList);
                    mListView.setAdapter(newsAdapter);
                    newsAdapter.notifyDataSetChanged();
                    dialog.dismiss();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                if(statusCode!=200){
                    //show user
                    Toast.makeText(MainActivity.this,"获取数据失败，稍后再试",Toast.LENGTH_LONG).show();
                    //show coder
                    Log.d("请求失败的原因",responseString.toString());
                }
            }
        });
    }
    //上拉
    @Override
    public void onHeaderRefresh(PullToRefreshView view) {
        GetJsonData();
        ptv.onHeaderRefreshComplete();
    }
    //下拉
    @Override
    public void onFooterRefresh(PullToRefreshView view) {
        GetJsonData();
        ptv.onFooterRefreshComplete();
    }

}
