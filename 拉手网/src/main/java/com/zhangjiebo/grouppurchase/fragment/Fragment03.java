package com.zhangjiebo.grouppurchase.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhangjiebo.grouppurchase.EnterActivity;
import com.zhangjiebo.grouppurchase.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment03 extends Fragment implements View.OnClickListener {

    private View mInflate;

    public Fragment03() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (mInflate==null){
            mInflate = inflater.inflate(R.layout.fragment_fragment03,null);
            mInflate.findViewById(R.id.enter_btn).setOnClickListener(this);





        }
        return mInflate;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.enter_btn:
                startActivity(new Intent(getActivity(), EnterActivity.class));
                break;
        }
    }
}
