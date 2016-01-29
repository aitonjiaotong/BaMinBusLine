package com.zhangjiebo.grouppurchase.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhangjiebo.grouppurchase.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EnterFragment01 extends Fragment {


    private View mInflate;

    public EnterFragment01() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (mInflate==null){
            mInflate = inflater.inflate(R.layout.fragment_enter_fragment01, null);

        }
        return mInflate;
    }


}
