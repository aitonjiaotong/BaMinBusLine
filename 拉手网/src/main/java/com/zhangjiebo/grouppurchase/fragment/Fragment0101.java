package com.zhangjiebo.grouppurchase.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhangjiebo.grouppurchase.DetailActivity;
import com.zhangjiebo.grouppurchase.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment0101 extends Fragment implements View.OnClickListener {


    private View mInflate;

    public Fragment0101() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (mInflate == null) {
            mInflate = inflater.inflate(R.layout.head0101, null);
            initView();
        }
        return mInflate;
    }

    private void initView() {
        mInflate.findViewById(R.id.head0101_rela).setOnClickListener(this);
        mInflate.findViewById(R.id.head0102_rela).setOnClickListener(this);
        mInflate.findViewById(R.id.head0103_rela).setOnClickListener(this);
        mInflate.findViewById(R.id.head0104_rela).setOnClickListener(this);
        mInflate.findViewById(R.id.head0105_rela).setOnClickListener(this);
        mInflate.findViewById(R.id.head0106_rela).setOnClickListener(this);
        mInflate.findViewById(R.id.head0107_rela).setOnClickListener(this);
        mInflate.findViewById(R.id.head0108_rela).setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        switch (v.getId()) {
            case R.id.head0101_rela:
                intent.setClass(getActivity(), DetailActivity.class);
                break;
            case R.id.head0102_rela:
                intent.setClass(getActivity(), DetailActivity.class);

                break;
            case R.id.head0103_rela:
                intent.setClass(getActivity(), DetailActivity.class);

                break;
            case R.id.head0104_rela:
                intent.setClass(getActivity(), DetailActivity.class);

                break;
            case R.id.head0105_rela:
                intent.setClass(getActivity(), DetailActivity.class);

                break;
            case R.id.head0106_rela:
                intent.setClass(getActivity(), DetailActivity.class);

                break;
            case R.id.head0107_rela:
                intent.setClass(getActivity(), DetailActivity.class);

                break;
            case R.id.head0108_rela:

                break;
        }
        startActivity(intent);
    }
}
