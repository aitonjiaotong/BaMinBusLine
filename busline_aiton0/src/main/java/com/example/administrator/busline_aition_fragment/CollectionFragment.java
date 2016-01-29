package com.example.administrator.busline_aition_fragment;


import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsoluteLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.administrator.busline_aiton.CollectionAddActivity;
import com.example.administrator.busline_aiton.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollectionFragment extends Fragment implements View.OnClickListener
{

    private View mLayout;
    private ImageView mCollectionAdd;
    private TextView mBtnCollectionAdd;
    private ImageView mAlert;
    private LayoutInflater mInflater;
    private TextView mBtn_add_traffic_routes;
    private PopupWindow mPopupWindow;

    public CollectionFragment ()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState)
    {
        this.mInflater = inflater;
        mLayout = inflater.inflate(R.layout.fragment_collection, null);

        initUI();

        return mLayout;
    }

    private void initUI ()
    {
        mCollectionAdd = (ImageView) mLayout.findViewById(R.id.iv_collection_add);
        mCollectionAdd.setOnClickListener(this);
        mBtnCollectionAdd = (TextView) mLayout.findViewById(R.id.btn_collection_add);
        mBtnCollectionAdd.setOnClickListener(this);
        mAlert = (ImageView) mLayout.findViewById(R.id.iv_collection_alert);
        mAlert.setOnClickListener(this);
    }

    private void initPopuWindow()
    {
        View view = mInflater.inflate(R.layout.popwindow_collection_alert_layout, null);
        mBtn_add_traffic_routes = (TextView)view.findViewById(R.id.btn_collection_add_from_popu);
        mBtn_add_traffic_routes.setOnClickListener(this);
        mPopupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        BitmapDrawable drawable = new BitmapDrawable();
        mPopupWindow.setBackgroundDrawable(drawable);
        mPopupWindow.setContentView(view);
        mPopupWindow.setOutsideTouchable(false);
        //设置外围背景变暗
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 0.6f;
        getActivity().getWindow().setAttributes(lp);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener()
        {
            @Override
            public void onDismiss ()
            {
                WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                lp.alpha = 1f;
                getActivity().getWindow().setAttributes(lp);
            }
        });
    }


    @Override
    public void onClick (View v)
    {
        switch (v.getId())
        {
            case R.id.iv_collection_add:
                Intent intent_iv = new Intent(getActivity(), CollectionAddActivity.class);
                startActivity(intent_iv);
                break;
            case R.id.btn_collection_add:
                Intent intent_btn = new Intent(getActivity(), CollectionAddActivity.class);
                startActivity(intent_btn);
                break;

            case R.id.iv_collection_alert:
                initPopuWindow();
                mPopupWindow.showAsDropDown(mLayout.findViewById(R.id.iv_collection_alert), 0, -5);
                break;

            case R.id.btn_collection_add_from_popu:
                Intent intent_btn_popu = new Intent(getActivity(), CollectionAddActivity.class);
                startActivity(intent_btn_popu);
                mPopupWindow.dismiss();
                break;

            default:
                break;
        }
    }
}
