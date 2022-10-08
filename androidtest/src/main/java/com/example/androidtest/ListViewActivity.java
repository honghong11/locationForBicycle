package com.example.androidtest;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidtest.model.MusicItemInfo;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {
    List<MusicItemInfo> musicItemInfos = new ArrayList<>();
    private MyAdapter myAdapter;
    private ListView listView;
    private Context myContext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myContext = this;
        setContentView(R.layout.activity_listview);
        initData();// 初始化数据
        initView();// 初始化页面
    }


    public void initData(){
        MusicItemInfo musicItemInfo= new MusicItemInfo();
        MusicItemInfo musicItemInfo1= new MusicItemInfo();
        musicItemInfo.setImage(String.valueOf(R.drawable.tree));
        musicItemInfo.setName("归途有风");
        musicItemInfo.setInfo("王菲-万里归途主题曲");
        musicItemInfos.add(musicItemInfo);
        musicItemInfo1.setImage(String.valueOf(R.drawable.light));
        musicItemInfo1.setName("雨夜曼切斯特");
        musicItemInfo1.setInfo("盘尼西林乐队");
        musicItemInfos.add(musicItemInfo1);
    }

    public void initView(){
        myAdapter = new MyAdapter(myContext,musicItemInfos);
        listView = findViewById(R.id.list_view);
        listView.setAdapter(myAdapter);
    }
}

class MyAdapter extends BaseAdapter{
    private List<MusicItemInfo> musicItemInfos = new ArrayList<>();
    private Context myContext;
    public MyAdapter(Context context, List<MusicItemInfo> myMusicItemInfo){
        musicItemInfos = myMusicItemInfo;
        myContext = context;
    }

    @Override
    public int getCount() {
        return musicItemInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return musicItemInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if(convertView==null){
            convertView = View.inflate(myContext,R.layout.list_item,null);
            view = convertView;
            TextView nameText = view.findViewById(R.id.name);
            TextView infoText = view.findViewById(R.id.info);
            ImageView imageView = view.findViewById(R.id.image_src);

            nameText.setText(musicItemInfos.get(position).getName());
            infoText.setText(musicItemInfos.get(position).getInfo());
            imageView.setImageResource(Integer.valueOf(musicItemInfos.get(position).getImage()));


        }else{
            view = convertView;
        }
        return view;
    }
}
