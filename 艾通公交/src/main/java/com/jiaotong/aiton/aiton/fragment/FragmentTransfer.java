package com.jiaotong.aiton.aiton.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiaotong.aiton.aiton.InputLocActivity;
import com.jiaotong.aiton.aiton.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTransfer extends Fragment implements View.OnClickListener {


    private View mInflate;

    private String[] loc01 = {"我的位置","梅阳花园"};
    private String[] loc02 = {"我的位置","干休二所"};
    private RelativeLayout mMylocation_rela;
    private RelativeLayout mInput_end_rela;

    public FragmentTransfer() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (mInflate==null){
            mInflate = inflater.inflate(R.layout.fragment_fragment_transfer, container, false);
            initUI();
            setListener();
        }
        return mInflate;
    }

    private void setListener() {
        mMylocation_rela.setOnClickListener(this);
        mInput_end_rela.setOnClickListener(this);
    }

    private void initUI() {
        mMylocation_rela = (RelativeLayout) mInflate.findViewById(R.id.mylocation_rela);
        mInput_end_rela = (RelativeLayout) mInflate.findViewById(R.id.input_end_rela);
        ListView transfer_listView = (ListView) mInflate.findViewById(R.id.transfer_listView);
        View transfer_foot = getLayoutInflater(getArguments()).inflate(R.layout.transfer_foot, null);
        transfer_listView.addFooterView(transfer_foot);
        transfer_listView.setAdapter(new MyAdapter());
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.mylocation_rela:
                intent.setClass(getActivity(), InputLocActivity.class);
                intent.putExtra("inputType","myLoc");
                startActivity(intent);
                break;
            case R.id.input_end_rela:
                intent.setClass(getActivity(), InputLocActivity.class);
                intent.putExtra("inputType","end");
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return loc01.length;
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
            View inflate = getLayoutInflater(getArguments()).inflate(R.layout.transfer_listitem, null);
            TextView loc01_tv = (TextView) inflate.findViewById(R.id.loc01_tv);
            TextView loc02_tv = (TextView) inflate.findViewById(R.id.loc02_tv);
            loc01_tv.setText(loc01[position]);
            loc02_tv.setText(loc02[position]);
            return inflate;
        }
    }

}
