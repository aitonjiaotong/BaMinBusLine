package com.example.administrator.busline_aition_fragment;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextOptions;
import com.baidu.mapapi.model.LatLng;
import com.example.administrator.busline_aiton.BusLineInfoActivity;
import com.example.administrator.busline_aiton.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class NearbyFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private float downY;// 按下时的点
    private float viewYdown;// 按下时View的位置
    private boolean lastSlidePull = false;// 最后一次滑动的方向
    private RelativeLayout mWoyaohua;
    private RelativeLayout mHehe_rela;
    private int mMHehe_relaMeasuredHeight;
    private RelativeLayout mTouch_rela;
    private int mTouch_relaHeight;

    private MapView mBmapView;
    private View mInflate;
    private BaiduMap mBaiduMap;
    //定位相关
    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();
    private boolean isFirstIn = true;
    private double mLatitude;
    private double mLongitude;
    private String addressStr;
    //自定义定位图标
    private BitmapDescriptor mIconLocation;
    private ListView mNearby_listView;

    public NearbyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mInflate == null) {
            mInflate = inflater.inflate(R.layout.fragment_fragment_nearby, container, false);
            initUI();
            initLocation();
            setListener();
        }
        return mInflate;
    }

    private void setListener() {
        mInflate.findViewById(R.id.bus_station_back).setOnClickListener(this);
        mNearby_listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                Log.e("onScrollStateChanged ", "scrollState "+scrollState);

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                Log.e("onScroll ", "firstVisibleItem "+firstVisibleItem);
                if (firstVisibleItem == 0) {
                    //已滚动到最顶部
                    mTouch_rela.setVisibility(View.VISIBLE);
                } else {
                    mTouch_rela.setVisibility(View.GONE);
                }
            }
        });
        mTouch_rela.setOnTouchListener(new View.OnTouchListener() {
            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float touchY = event.getY();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        downY = touchY;
                        viewYdown = mWoyaohua.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float offset = (event.getY() - downY);// 滑动距离
                        float posY = viewYdown + offset;// 计算可能的位置
                        if (mMHehe_relaMeasuredHeight < posY && posY < mTouch_relaHeight * 2 / 3 + mMHehe_relaMeasuredHeight) {

                            mWoyaohua.setY(posY);
                        }
                        if (posY>(mTouch_relaHeight * 2 / 3 + mMHehe_relaMeasuredHeight)/2){
                            mHehe_rela.setVisibility(View.INVISIBLE);
                        }
                        if (posY<(mTouch_relaHeight * 2 / 3 + mMHehe_relaMeasuredHeight)/2){
                            mHehe_rela.setVisibility(View.VISIBLE);

                        }

                        if (offset > 0) {// pull to hide
                            lastSlidePull = true;

                        } else {// push to show
                            lastSlidePull = false;
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        // 使用动画滑动到指定位置
                        if (lastSlidePull) {
                            mHehe_rela.setVisibility(View.INVISIBLE);
                            mTouch_rela.setVisibility(View.VISIBLE);

                            mWoyaohua.setY(mTouch_relaHeight * 2 / 3 + mMHehe_relaMeasuredHeight);
                            mTouch_rela.setY(mTouch_relaHeight * 2 / 3 + mMHehe_relaMeasuredHeight);
//                    slideToHide();
                        } else {
//                    slideToShow();
                            mHehe_rela.setVisibility(View.VISIBLE);

                            mTouch_rela.setVisibility(View.GONE);
                            mWoyaohua.setY(0 + mMHehe_relaMeasuredHeight);
                            mTouch_rela.setY(0 + mMHehe_relaMeasuredHeight);
                        }
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    private void initLocation(){
        mLocationClient = new LocationClient(getActivity().getApplicationContext());     //声明LocationClient类
        mLocationClient.registerLocationListener( myListener );    //注册监听函数
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span=5000;
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
        //初始化定位的图标
        mIconLocation = BitmapDescriptorFactory.fromResource(R.mipmap.ico_location_big_highlight_map);
    }

    private void initUI() {
        mBmapView = (MapView) mInflate.findViewById(R.id.bmapView);
        mBaiduMap = mBmapView.getMap();
        //构造一个更新地图的msu对象，然后设置该对象为缩放等级(比例尺)，最后设置地图状态。
        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(18.0f);
        mBaiduMap.setMapStatus(msu);
        mNearby_listView = (ListView) mInflate.findViewById(R.id.nearby_listView);
        View nearby_listview_head = getLayoutInflater(getArguments()).inflate(R.layout.nearby_listview_head, null);
        View nearby_listview_small_head = getLayoutInflater(getArguments()).inflate(R.layout.nearby_listview_small_head, null);
        mNearby_listView.addHeaderView(nearby_listview_small_head);
        mNearby_listView.addHeaderView(nearby_listview_head);
        mNearby_listView.setAdapter(new MyAdapter());
        mNearby_listView.setOnItemClickListener(this);


        mTouch_rela = (RelativeLayout) mInflate.findViewById(R.id.touch_rela);
        mWoyaohua = (RelativeLayout) mInflate.findViewById(R.id.woyaohua);
        mHehe_rela = (RelativeLayout) mInflate.findViewById(R.id.hehe_rela);
        //测量标题的高度
        mHehe_rela.measure(0, 0);
        mMHehe_relaMeasuredHeight = mHehe_rela.getMeasuredHeight();

        //隐藏标题
        mHehe_rela.setVisibility(View.INVISIBLE);
        //测量覆盖布局的高度
        ViewTreeObserver vto = mTouch_rela.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onGlobalLayout() {
                mTouch_rela.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                mTouch_relaHeight = mTouch_rela.getHeight();
                //初始化布局的位置
                mWoyaohua.setY(mTouch_relaHeight * 2 / 3 + mMHehe_relaMeasuredHeight);
                mTouch_rela.setY(mTouch_relaHeight * 2 / 3 + mMHehe_relaMeasuredHeight);
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bus_station_back:
                mWoyaohua.setY(mTouch_relaHeight * 2 / 3 + mMHehe_relaMeasuredHeight);
                mTouch_rela.setY(mTouch_relaHeight * 2 / 3 + mMHehe_relaMeasuredHeight);
                mTouch_rela.setVisibility(View.VISIBLE);
                mHehe_rela.setVisibility(View.INVISIBLE);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent= new Intent();
        intent.setClass(getActivity(), BusLineInfoActivity.class);
        startActivity(intent);
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 30;
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
            View inflate = getLayoutInflater(getArguments()).inflate(R.layout.nearby_listview_item, null);
            return inflate;
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        //开启定位
        if (!mLocationClient.isStarted()) {
            mLocationClient.start();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        //停止定位图层
        mBaiduMap.setMyLocationEnabled(false);
        //停止定位
        mLocationClient.stop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mBmapView.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mBmapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mBmapView.onPause();
    }

    //定位的监听回调
    private class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            // 构造定位数据
            MyLocationData data = new MyLocationData.Builder()
                    .accuracy(bdLocation.getRadius())//获得半径
                    .latitude(bdLocation.getLatitude())//获得经度
                    .longitude(bdLocation.getLongitude())//获得纬度
                    .build();
            //设置定位数据
            mBaiduMap.setMyLocationData(data);

            //设置自定义图标
            MyLocationConfiguration config = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL, true, mIconLocation);
            mBaiduMap.setMyLocationConfigeration(config);

            //初始化经纬度
            mLatitude = bdLocation.getLatitude();
            mLongitude = bdLocation.getLongitude();

            //第一次进入，定位到所在位置
            if (isFirstIn) {
                LatLng latLng = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());
                MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
                mBaiduMap.animateMapStatus(msu);
                isFirstIn = false;
                addressStr = bdLocation.getAddrStr();

                LatLng llText = new LatLng(mLatitude, mLongitude);
                //构建文字Option对象，用于在地图上添加文字
                OverlayOptions textOption = new TextOptions()
                        .fontSize(24)
                        .fontColor(0xFF000000)
//                        .text(addressStr)
                        .position(llText);
                //在地图上添加该文字对象并显示
                mBaiduMap.addOverlay(textOption);

            }
        }
    }

}

