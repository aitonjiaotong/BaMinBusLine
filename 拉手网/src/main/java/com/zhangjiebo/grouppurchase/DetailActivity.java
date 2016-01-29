package com.zhangjiebo.grouppurchase;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.github.volley.HTTPUtils;
import com.github.volley.VolleyListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.xmbc.utils.GsonUtils;
import com.zhangjiebo.grouppurchase.bean.GuessYourLove;
import com.zhangjiebo.grouppurchase.constant.Cont;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends Activity implements View.OnClickListener {
    private boolean isRefresh = false;
    private ArrayList<GuessYourLove.GoodlistEntity> infoArr = new ArrayList<>();
    private ImageLoader imageLoader = ImageLoader.getInstance();
    DisplayImageOptions options;
    private MyListViewAdapter mAdapter;
    private PullToRefreshListView mPull_refresh_list;
    private ImageView mBaide_map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        new MyAsyncGoodlist().execute(Cont.guessYourLove);
        mPull_refresh_list = (PullToRefreshListView)findViewById(R.id.pull_refresh_list02);

        mBaide_map = (ImageView)findViewById(R.id.baide_map);
        mBaide_map.setOnClickListener(this);


        mAdapter = new MyListViewAdapter();
        mPull_refresh_list.setAdapter(mAdapter);
        mPull_refresh_list.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                isRefresh = true;
                new MyAsyncGoodlist().execute(Cont.guessYourLove);

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });
        setOptions();
    }
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.baide_map:
                startActivity(new Intent(DetailActivity.this, MapActivity.class));
                break;
        }
    }



    private void setOptions() {
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_stub)
                .showImageForEmptyUri(R.drawable.ic_empty)
                .showImageOnFail(R.drawable.ic_error)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .displayer(new RoundedBitmapDisplayer(20))
                .build();
    }
    class MyListViewAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return infoArr.size();
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
            View inflate = getLayoutInflater().inflate(R.layout.tabs01_list_item, null);
            GuessYourLove.GoodlistEntity goodlistEntity = infoArr.get(position);
            List<GuessYourLove.GoodlistEntity.ImagesEntity> images = goodlistEntity.getImages();

            TextView product = (TextView) inflate.findViewById(R.id.product);
            TextView short_title = (TextView) inflate.findViewById(R.id.short_title);
            TextView price = (TextView) inflate.findViewById(R.id.price);
            TextView bought = (TextView) inflate.findViewById(R.id.bought);
            TextView value = (TextView) inflate.findViewById(R.id.value);



            value.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            ImageView images01 = (ImageView) inflate.findViewById(R.id.images);
            product.setText(goodlistEntity.getProduct());
            short_title.setText(goodlistEntity.getShort_title());
            price.setText(goodlistEntity.getPrice());
            bought.setText("已售" + goodlistEntity.getBought());
            value.setText("¥" + goodlistEntity.getValue());
//            Log.e("images.get(3)", "images.get(3)*****"+images.get(3));
            imageLoader.displayImage(images.get(0).getImage() + "", images01, options);
            return inflate;
        }


    }

    /***
     * 解析商品列表
     */
    class MyAsyncGoodlist extends AsyncTask<String, Void, Void> {

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        }
        @Override
        protected Void doInBackground(String... params) {
            HTTPUtils.get(DetailActivity.this, params[0], new VolleyListener() {
                @Override
                public void onResponse(String s) {
                    infoArr.clear();
                    GuessYourLove goodlist = GsonUtils.parseJSON(s, GuessYourLove.class);
                    List<GuessYourLove.GoodlistEntity> goodlistInfo = goodlist.getGoodlist();
                    for (int i = 0; i < goodlistInfo.size(); i++) {
                        infoArr.add(goodlistInfo.get(i));
                    }
                    mAdapter.notifyDataSetChanged();
                    if (isRefresh) {

                        // Call onRefreshComplete when the list has been refreshed.
                        mPull_refresh_list.onRefreshComplete();
                        isRefresh = false;
                    }
                }

                @Override
                public void onErrorResponse(VolleyError volleyError) {
                }
            });

            return null;
        }
    }
}
