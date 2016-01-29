package com.example.administrator.busline_aiton;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BusLineInfoActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView mListView2;
    private MyAdapter mAdapter;
    private int isOpen=-1;
    private List<String> satationInfo = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_line_info);
        initUI();
        setListener();
        initDate();
    }

    private void initDate() {
        satationInfo.add("红星美凯龙总站");
        satationInfo.add("钟宅红星美凯龙");
        satationInfo.add("钟宅");
        satationInfo.add("BRT湖里大道");
        satationInfo.add("双十中学枋湖校区");
        satationInfo.add("车管所");
        satationInfo.add("枋湖路");
        satationInfo.add("梧桐");
        satationInfo.add("枋湖村");
        satationInfo.add("枋湖西路");
        satationInfo.add("枋湖客运中心");
        satationInfo.add("禾山路");
        satationInfo.add("薛岭");
        satationInfo.add("白果山");
        satationInfo.add("武警支队");
        satationInfo.add("仙岳花园");
        satationInfo.add("莲岳路北");
        satationInfo.add("槟榔路");
        satationInfo.add("槟榔新村");
        satationInfo.add("凤屿路口");
        satationInfo.add("新村");
        satationInfo.add("闽南大厦");
        satationInfo.add("湖滨中路");
        satationInfo.add("太湖新城");
        satationInfo.add("豆仔尾路口");
        satationInfo.add("嘉美花园");
        satationInfo.add("思北路口");
        satationInfo.add("开河路口");
        satationInfo.add("鹭江道");
        satationInfo.add("轮渡邮局");
        satationInfo.add("海滨大厦");
        satationInfo.add("轮渡公交场");
    }

    private void setListener() {
        mListView2.setOnItemClickListener(this);
    }

    private void initUI() {
        mListView2 = (ListView) findViewById(R.id.listView2);
        mAdapter = new MyAdapter();
        mListView2.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        isOpen=position;
        mAdapter.notifyDataSetChanged();
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return satationInfo.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View inflate=null;
            if (position==0){
                inflate = getLayoutInflater().inflate(R.layout.busline_listitem_head, null);
                TextView busStationHead = (TextView) inflate.findViewById(R.id.busStationHead);
                busStationHead.setText(satationInfo.get(0));
            }else if (position==satationInfo.size()-1){
                inflate = getLayoutInflater().inflate(R.layout.busline_listitem_foot, null);
                TextView busStationHead = (TextView) inflate.findViewById(R.id.busStationFoot);
                busStationHead.setText(satationInfo.get(satationInfo.size()-1));
            }else{
                inflate = getLayoutInflater().inflate(R.layout.busline_listitem, null);
                TextView busStation = (TextView) inflate.findViewById(R.id.busStation);
                busStation.setText(satationInfo.get(position));
                LinearLayout hideView = (LinearLayout) inflate.findViewById(R.id.hideView);
                RelativeLayout location = (RelativeLayout) inflate.findViewById(R.id.location);

                if (position==isOpen){
                    hideView.setVisibility(View.VISIBLE);
                    location.setVisibility(View.VISIBLE);
                }else{
                    hideView.setVisibility(View.GONE);
                    location.setVisibility(View.GONE);
                }
            }

            return inflate;
        }
    }
}
