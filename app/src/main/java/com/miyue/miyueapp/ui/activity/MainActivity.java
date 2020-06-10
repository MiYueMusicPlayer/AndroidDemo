package com.miyue.miyueapp.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gongw.remote.MessageEvent;
import com.gongw.remote.communication.host.CommandSender;
import com.miyue.miyueapp.ui.fragment.first.child.CollectSongFragment;
import com.miyue.miyueapp.ui.fragment.first.child.CollectSongListFragment;
import com.miyue.miyueapp.ui.fragment.first.child.RadioStationFragment;
import com.miyue.miyueapp.util.ToastUtils;
import com.miyue.sqj.miyue.R;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.SupportFragment;

;

/**
 * Created by Mason on 2018-10-25.
 */

public class MainActivity extends SupportActivity {
    @BindView(R.id.rg)
    RadioGroup mRg;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    @BindView(R.id.rb2)
    RadioButton rb2;
    @BindView(R.id.rb3)
    RadioButton rb3;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.rb1)
    RadioButton rb1;
    private int index = 0;

    private CollectSongFragment mCollectSongFragment;
    private CollectSongListFragment mCollectSongListFragment;
    private RadioStationFragment mRadioStationFragment;
    private List<SupportFragment> mFragmentList;
    private String[] mTilte;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_collect_home);
        ButterKnife.bind(this);
        initData();
        showInput();
    }

    public void initData() {
        mFragmentList = new ArrayList<>();
        mTilte = getResources().getStringArray(R.array.tab_song_Title);
        mCollectSongFragment = CollectSongFragment.getInstance();
        mCollectSongListFragment = CollectSongListFragment.getInstance();
        mRadioStationFragment = new RadioStationFragment();

        mFragmentList.add(mCollectSongFragment);
        mFragmentList.add(mCollectSongListFragment);
        mFragmentList.add(mRadioStationFragment);


        FragAdapter fragAdapter = new FragAdapter(getSupportFragmentManager(), mFragmentList);
        mViewPager.setAdapter(fragAdapter);
        mViewPager.setCurrentItem(0);
        mRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int id = radioGroup.getCheckedRadioButtonId();

                if (id == R.id.rb1) {
                    mViewPager.setCurrentItem(0);

                    index = 0;

                } else if (id == R.id.rb2) {
                    index = 1;
                    mViewPager.setCurrentItem(1);


                } else if (id == R.id.rb3) {
                    index = 2;
                    mViewPager.setCurrentItem(2);

                }
            }
        });
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                MessageEvent messageEvent = new MessageEvent();
                messageEvent.what = "changeImage";
                index = position;
                if (position == 0) {
                    mRg.check(R.id.rb1);
                    messageEvent.type = "0";

                } else if (position == 1) {

                    mRg.check(R.id.rb2);
                    messageEvent.type = "1";

                } else if (position == 2) {

                    mRg.check(R.id.rb3);
                    messageEvent.type = "2";
                }
                EventBus.getDefault().post(messageEvent);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
    /**
     * 一个输入框的 dialog
     */
    private void showInput() {
        final EditText editText = new EditText(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this).setTitle("请输入设备IP").setView(editText)
                .setPositiveButton("连接", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(editText.getText().toString().isEmpty()){
                            ToastUtils.showToast("请输入IP", Gravity.CENTER);
                            return;
                        }
                        String ip=editText.getText().toString();
                        new CommandSender().startAndReceive(ip,ip);
                        dialogInterface.dismiss();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.setCancelable(false);
        builder.create().show();

    }

    class FragAdapter extends FragmentPagerAdapter {

        private List<SupportFragment> mFragments;

        public FragAdapter(FragmentManager fm, List<SupportFragment> fragments) {
            super(fm);
            // TODO Auto-generated constructor stub
            mFragments = fragments;
        }

        @Override
        public Fragment getItem(int arg0) {
            // TODO Auto-generated method stub
            return mFragments.get(arg0);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return mFragments.size();
        }

        public CharSequence getPageTitle(int position) {
            return mTilte[position];
        }

    }

}
