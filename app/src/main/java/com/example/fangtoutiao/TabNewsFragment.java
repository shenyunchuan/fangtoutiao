package com.example.fangtoutiao;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fangtoutiao.adapter.NewsListAdapter;
import com.example.fangtoutiao.dialog.CommonDialog;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class TabNewsFragment extends Fragment {
    private String url="http://v.juhe.cn/toutiao/index?key=6e5458cac5dfe08bf3618f92974112a7&type=";
    private View rootView;
    private RecyclerView recyclerView;
    private NewsListAdapter mNewsListAdapter;

    private Handler mHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what==100){
                String data = (String) msg.obj;
                NewsInfo newsInfo = new Gson().fromJson(data, NewsInfo.class);
                if (newsInfo!=null && newsInfo.getError_code()==0){
                    if (null!=mNewsListAdapter){
                        mNewsListAdapter.setListData(newsInfo.getResult().getData());
                    }
                }else {
                    Toast.makeText(getActivity(), "获取数据失败，请稍后重试", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };


    private static final String ARG_PARAM = "title";
    private String title;


    public TabNewsFragment() {

    }


    public static TabNewsFragment newInstance(String param) {
        TabNewsFragment fragment = new TabNewsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM, param);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(ARG_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView =  inflater.inflate(R.layout.fragment_tab_news, container, false);
        //初始化控件
        recyclerView = rootView.findViewById(R.id.recyclerView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化适配器
        mNewsListAdapter = new NewsListAdapter(getActivity());
        //设置adapter
        recyclerView.setAdapter(mNewsListAdapter);
        //recyclerView列表点击事件
        mNewsListAdapter.setmOnItemClickListener(new NewsListAdapter.onItemClickListener() {
            @Override
            public void onItemClick(NewsInfo.ResultDTO.DataDTO dataDTO, int position) {
//                //跳转到详情页
//                Intent intent = new Intent(getActivity(),NewsDetailsActivity.class);
//                //传递对象的时候该类要实现Serializable
//                intent.putExtra("dataDTO",dataDTO);
//                startActivity(intent);
                CommonDialog dialog = new CommonDialog(getContext());
                dialog.setOnCommonClickListener(new CommonDialog.OnCommonClickListener() {
                    @Override
                    public void onClick() {
                        Toast.makeText(getContext(), "dialog Click", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show();
            }
        });

        //获取数据
        getHttpData();

    }

    private void getHttpData() {
        //创建OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //构构造Request对象
        Request request = new Request.Builder()
                .url(url+title)
                .get()
                .build();
        //通过OkHttpClient和Request对象来构建Call对象
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d("----","onFailure:"+e.toString());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String data = response.body().string();
               // Log.d("---------","onResponse:"+data);


                Message message = new Message();
                message.what=100;
                message.obj = data;
                //发送
                mHandler.sendMessage(message);

            }
        });
    }


}
