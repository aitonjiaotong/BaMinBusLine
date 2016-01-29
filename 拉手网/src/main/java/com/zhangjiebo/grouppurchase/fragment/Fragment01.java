package com.zhangjiebo.grouppurchase.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.github.volley.HTTPUtils;
import com.github.volley.VolleyListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.xmbc.utils.GsonUtils;
import com.zhangjiebo.grouppurchase.ProductActivity;
import com.zhangjiebo.grouppurchase.R;
import com.zhangjiebo.grouppurchase.WebActivity;
import com.zhangjiebo.grouppurchase.bean.Advertisement02;
import com.zhangjiebo.grouppurchase.bean.FilmInfo;
import com.zhangjiebo.grouppurchase.bean.GuessYourLove;
import com.zhangjiebo.grouppurchase.constant.Cont;
import com.zhangjiebo.grouppurchase.costom.MyIndicator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment01 extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {

    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();

    private boolean isRefresh = false;

    private ArrayList<GuessYourLove.GoodlistEntity> infoArr = new ArrayList<>();
    private ArrayList<TextView> filmname = new ArrayList<>();
    private ArrayList<TextView> filmscore = new ArrayList<>();
    private ArrayList<ImageView> filmimg = new ArrayList<>();
    private ImageLoader imageLoader = ImageLoader.getInstance();


    DisplayImageOptions options;
    private int[] filmImg = {
            R.id.imageView25,
            R.id.imageView26,
            R.id.imageView27,
            R.id.imageView28,
            R.id.imageView29,
            R.id.imageView30,
            R.id.imageView37,
            R.id.imageView38,
            R.id.imageView39
    };
    private int[] filmName = {
            R.id.tv_num1,
            R.id.textView35,
            R.id.textView37,
            R.id.textView39,
            R.id.textView41,
            R.id.textView43,
            R.id.textView53,
            R.id.textView55,
            R.id.textView57
    };
    private int[] filmScore = {
            R.id.textView34,
            R.id.textView36,
            R.id.textView38,
            R.id.textView40,
            R.id.textView42,
            R.id.textView44,
            R.id.textView54,
            R.id.textView56,
            R.id.textView58
    };
    private MyIndicator mMyIndicator;
    private ViewPager mViewpager_head01;
    private View mInflate;
    private PullToRefreshListView listView_fg01;
    private MyListViewAdapter mAdapter;
    private View mHead04;
    private ImageView mAd01;
    private ImageView mAd02;
    private ImageView mAd03;
    private ImageView mAd04;
    private TextView mLocation_tv;
    private ListView mRefreshableView;
    private ImageView mIc_secking;
    private View mHead02;
    private View mHead03;

    public Fragment01() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (mInflate == null) {
            mInflate = inflater.inflate(R.layout.fragment_fragment01, null);
            //设置界面
            setView();
            //数据解析
            setAsync();
            //设置监听
            setListener();
            //设置图片缓存
            setOptions();
            //设置百度地图定位
            setLocation();
        }
        return mInflate;
    }

    private void setView() {
        View head00 = getLayoutInflater(getArguments()).inflate(R.layout.head00, null);
        View head01 = getLayoutInflater(getArguments()).inflate(R.layout.head01, null);
        mHead02 = getLayoutInflater(getArguments()).inflate(R.layout.head02, null);
        mHead03 = getLayoutInflater(getArguments()).inflate(R.layout.head03, null);
        mIc_secking = (ImageView) head00.findViewById(R.id.ic_secking);
        mHead04 = getLayoutInflater(getArguments()).inflate(R.layout.head04, null);
        listView_fg01 = (PullToRefreshListView) mInflate.findViewById(R.id.pull_refresh_list);
        View head05 = getLayoutInflater(getArguments()).inflate(R.layout.head05, null);
        mRefreshableView = listView_fg01.getRefreshableView();
        mRefreshableView.addHeaderView(head00);
        mRefreshableView.addHeaderView(head01);
        mRefreshableView.addHeaderView(mHead02);
        mRefreshableView.addHeaderView(mHead03);
        mRefreshableView.addHeaderView(mHead04);
        mRefreshableView.addHeaderView(head05);
        mViewpager_head01 = (ViewPager) head01.findViewById(R.id.viewpager_head01);
        for (int i = 0; i < filmName.length; i++) {
            filmname.add((TextView) mHead04.findViewById(filmName[i]));
            filmscore.add((TextView) mHead04.findViewById(filmScore[i]));
            filmimg.add((ImageView) mHead04.findViewById(filmImg[i]));
        }
        mAd01 = (ImageView) mHead02.findViewById(R.id.ad01);
        mAd02 = (ImageView) mHead02.findViewById(R.id.ad02);
        mAd03 = (ImageView) mHead02.findViewById(R.id.ad03);
        mAd04 = (ImageView) mHead02.findViewById(R.id.ad04);
        mLocation_tv = (TextView) mInflate.findViewById(R.id.location_tv);
        mMyIndicator = (MyIndicator) head01.findViewById(R.id.MyIndicator);
        mViewpager_head01.setAdapter(new MyFragmentViewpager(getActivity().getSupportFragmentManager()));
        mViewpager_head01.setCurrentItem(10000);
        mAdapter = new MyListViewAdapter();
        listView_fg01.setAdapter(mAdapter);
    }

    private void setAsync() {
        new MyAsyncGoodlist().execute(Cont.guessYourLove);
        new MyAsyncFilmInfo().execute(Cont.filmInfo);
        new MyAsyncAdvertisement02().execute(Cont.advertisement02);
    }

    private void setListener() {
        listView_fg01.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                isRefresh = true;
                new MyAsyncGoodlist().execute(Cont.guessYourLove);
            }
        });
        mViewpager_head01.addOnPageChangeListener(new MyFragmentViewpagerListener());
        mRefreshableView.setOnItemClickListener(this);
        mIc_secking.setOnClickListener(this);
        mAd01.setOnClickListener(this);
        mAd02.setOnClickListener(this);
        mAd03.setOnClickListener(this);
        mAd04.setOnClickListener(this);
        mHead03.findViewById(R.id.head0301_rela).setOnClickListener(this);
        mHead03.findViewById(R.id.head0302_rela).setOnClickListener(this);
        mHead03.findViewById(R.id.head0303_rela).setOnClickListener(this);
        mHead03.findViewById(R.id.head0304_rela).setOnClickListener(this);

    }

    private void setLocation() {
        mLocationClient = new LocationClient(getActivity().getApplicationContext());     //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);    //注册监听函数
        initLocation();
        mLocationClient.start();
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int headerViewsCount = mRefreshableView.getHeaderViewsCount();
        position = position - headerViewsCount;
        Intent intent = new Intent();
        intent.setClass(getActivity(), ProductActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.ic_secking:
                intent.setClass(getActivity(), WebActivity.class);
                intent.putExtra("WebUrl", "http://mp.suning.com/?utm_source=baidu&utm_medium=cpc&utm_campaign=%E5%90%8D%E5%93%81%E7%89%B9%E5%8D%96&utm_content=7biaoti&utm_term=u10317663.c0.g0.k19517860141.a6281306275.pb");
                startActivity(intent);
                break;
            case R.id.ad01:
                intent.setClass(getActivity(), WebActivity.class);
                intent.putExtra("WebUrl", "http://m.lashou.com/zhuanti/banner_zt.php?kid=150609092330");
                startActivity(intent);
                break;
            case R.id.ad02:
                intent.setClass(getActivity(), WebActivity.class);
                intent.putExtra("WebUrl", "http://m.lashou.com/zhuanti/banner_zt.php?kid=151120095526");
                startActivity(intent);
                break;
            case R.id.ad03:
                intent.setClass(getActivity(), WebActivity.class);
                intent.putExtra("WebUrl", "http://m.lashou.com/zhuanti/banner_zt.php?kid=151120095222");
                startActivity(intent);
                break;
            case R.id.ad04:
                intent.setClass(getActivity(), WebActivity.class);
                intent.putExtra("WebUrl", "http://m.lashou.com/zhuanti/banner_zt.php?kid=150917140849");
                startActivity(intent);
                break;
            case R.id.head0301_rela:
                intent.setClass(getActivity(), WebActivity.class);
                intent.putExtra("WebUrl", "http://m.lashou.com/zhuanti/banner_zt.php?kid=151029132610");
                startActivity(intent);
                break;
            case R.id.head0302_rela:
                intent.setClass(getActivity(), WebActivity.class);
                intent.putExtra("WebUrl", "http://m.lashou.com/zhuanti/banner_zt.php?kid=151029133838");
                startActivity(intent);
                break;
            case R.id.head0303_rela:
                intent.setClass(getActivity(), WebActivity.class);
                intent.putExtra("WebUrl", "http://m.lashou.com/zhuanti/banner_zt.php?kid=151119092522");
                startActivity(intent);
                break;
            case R.id.head0304_rela:
                intent.setClass(getActivity(), WebActivity.class);
                intent.putExtra("WebUrl", "http://m.lashou.com/zhuanti/banner_zt.php?kid=151119093130");
                startActivity(intent);
                break;
            case R.id.head0305_rela:
                intent.setClass(getActivity(), WebActivity.class);
                intent.putExtra("WebUrl", "http://m.lashou.com/zhuanti/banner_zt.php?kid=151029134204");
                startActivity(intent);
                break;
        }
    }

    /**
     * 解析电影信息
     */
    class MyAsyncFilmInfo extends AsyncTask<String, Void, ArrayList<FilmInfo>> {

        @Override
        protected void onPostExecute(ArrayList<FilmInfo> aVoid) {
            super.onPostExecute(aVoid);

        }


        @Override
        protected ArrayList<FilmInfo> doInBackground(String... params) {
//            Log.e("doInBackground", "filmInfoArr "+filmInfoArr.size());
            HTTPUtils.get(getActivity(), params[0], new VolleyListener() {
                @Override
                public void onResponse(String s) {
                    try {
                        JSONArray jsonArray = new JSONArray(s);
                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String filmName = jsonObject.getString("filmName");
                            String imageUrl = jsonObject.getString("imageUrl");
                            String grade = jsonObject.getString("grade");
//                            Log.e("filmName", "filmName "+filmName);
//                            filmInfoArr.add(new FilmInfo(filmName, imageUrl, grade));
                            filmname.get(i).setText(filmName);
                            filmscore.get(i).setText(grade);
                            imageLoader.displayImage(imageUrl, filmimg.get(i), options);
//                            Log.e("filmInfoArr", "doInBackground " + filmInfoArr.size());
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onErrorResponse(VolleyError volleyError) {
                }
            });
            return null;
        }
    }

    /***
     * 解析商品列表
     */
    class MyAsyncGoodlist extends AsyncTask<String, Void, Void> {

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            // Call onRefreshComplete when the list has been refreshed.
        }

        @Override
        protected Void doInBackground(String... params) {
            HTTPUtils.get(getActivity(), params[0], new VolleyListener() {
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
                        listView_fg01.onRefreshComplete();
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

    /***
     * 解析四格广告
     */
    class MyAsyncAdvertisement02 extends AsyncTask<String, Void, Void> {

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected Void doInBackground(String... params) {
            HTTPUtils.get(getActivity(), params[0], new VolleyListener() {
                @Override
                public void onResponse(String s) {
                    Advertisement02 advertisement02 = GsonUtils.parseJSON(s, Advertisement02.class);
                    List<Advertisement02.CenterBannerEntity> center_banner = advertisement02.getCenter_banner();

                    imageLoader.displayImage(center_banner.get(0).getImg_mid(), mAd01, options);
                    imageLoader.displayImage(center_banner.get(1).getImg_mid(), mAd02, options);
                    imageLoader.displayImage(center_banner.get(2).getImg_mid(), mAd03, options);
                    imageLoader.displayImage(center_banner.get(3).getImg_mid(), mAd04, options);
                }

                @Override
                public void onErrorResponse(VolleyError volleyError) {
                }
            });

            return null;
        }
    }

    class MyFragmentViewpagerListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            mMyIndicator.setOffost(position, positionOffset);
        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    class MyFragmentViewpager extends FragmentPagerAdapter {

        public MyFragmentViewpager(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            position = position % 2;
            if (position == 0) {
                Fragment0101 fragment0101 = new Fragment0101();
                return fragment0101;
            } else if (position == 1) {
                Fragment0102 fragment0102 = new Fragment0102();
                return fragment0102;
            }
            return null;
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }
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
            View inflate = getLayoutInflater(getArguments()).inflate(R.layout.tabs01_list_item, null);

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
            imageLoader.displayImage(images.get(0).getImage() + "", images01, options);
            return inflate;
        }
    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span = 1000;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认false，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        mLocationClient.setLocOption(option);
    }

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            final StringBuffer sb = new StringBuffer(256);
            sb.append(location.getCity());

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                    .setTitle("当前定位")
                    .setMessage(sb.toString())
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mLocation_tv.setText("中国");

                        }
                    })
                    .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mLocation_tv.setText(sb.toString());

                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
            mLocationClient.stop();
        }
    }
}
