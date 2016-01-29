package com.example.administrator.busline_aition_fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.busline_aition_utils.MyConstants;
import com.example.administrator.busline_aiton.ChooseCityActivity;
import com.example.administrator.busline_aiton.R;
import com.example.administrator.busline_aiton_model.CorporationInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends Fragment implements View.OnClickListener
{

    private List<CorporationInfo> mListViewData = new ArrayList<CorporationInfo>();
    private View mLayout;
    private LinearLayout mChooseCity;
    private TextView mChoosedCity_Show;
    private int mPosition = 1 ;

    public MeFragment ()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState)
    {
        mLayout = inflater.inflate(R.layout.fragment_me, null);

        initData();
        initUI();
        setOnClick();
        return mLayout;
    }

    private void initData ()
    {
        mListViewData.add(new CorporationInfo("深圳", "合作方：\n深圳交通运输委员会\n深圳易行网交通科技有限公司\n数据试运营中，正在完善，敬请期待"));
        mListViewData.add(new CorporationInfo("厦门", "合作方：\n厦门公交集团有限公司\n数据试运营中，正在完善，敬请期待"));
        mListViewData.add(new CorporationInfo("东莞", "合作方：\n东莞市交通运输局\n数据试运营中，正在完善，敬请期待"));
    }

    private void initUI ()
    {
        mChooseCity = (LinearLayout) mLayout.findViewById(R.id.ll_choose_city);
        mChoosedCity_Show = (TextView)mLayout.findViewById(R.id.tv_me_show_city);
        mChoosedCity_Show.setText(mListViewData.get(mPosition).getTitle());
    }

    private void setOnClick ()
    {
        mChooseCity.setOnClickListener(this);
    }


    @Override
    public void onClick (View v)
    {

        switch (v.getId())
        {
            case R.id.ll_choose_city:
                Intent intent = new Intent(getActivity(), ChooseCityActivity.class);
                intent.putExtra(MyConstants.IntentKey.CHOOSE_CITY_KEY,mPosition);
                startActivityForResult(intent, MyConstants.RequestAndResultCode.ME_FRAGMENT_CHOOSE_CITY_REQUEST);
                break;

            default:
                break;
        }
    }

    @Override
    public void onActivityResult (int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(data != null)
        {
            int position = data.getIntExtra(MyConstants.IntentKey.CHOOSE_CITY_KEY, -1);
            mPosition = position;
            mChoosedCity_Show.setText(mListViewData.get(mPosition).getTitle());
        }
    }
}
