package com.xiaoxin.demo.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xiaoxin.demo.R;
import com.xiaoxin.demo.widget.calendar.CalendarView;
import com.xiaoxin.demo.widget.calendar.CalendarViewBuilder;
import com.xiaoxin.demo.widget.calendar.CalendarViewPagerAdapter;
import com.xiaoxin.demo.widget.calendar.CalendarViewPagerListener;
import com.xiaoxin.demo.widget.calendar.CustomDate;
import com.xiaoxin.demo.widget.calendar.DateUtil;
import com.xiaoxin.demo.widget.calendar.OnCalenderListener;
import com.xiaoxin.demo.widget.calendar.RecordState;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CalendarActivity extends AppCompatActivity implements OnCalenderListener {


    TabLayout mTabLayout;
    TextView mTvYear;
    ViewPager mViewPager;
    private Context mContext;
    private CalendarView[] mMonthCalendarViews;
    private CalendarViewPagerListener pagerListener;
    private Handler mHandler = new Handler();
    private int cellHeight;
    private int rows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = CalendarActivity.this;
//        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mTvYear = (TextView) findViewById(R.id.tv_year);
        mTabLayout = (TabLayout) findViewById(R.id.tab_month);
        mViewPager = (ViewPager) findViewById(R.id.vp_month);
        mMonthCalendarViews = CalendarViewBuilder.createMonthCalendarViews(mContext, 5, this);
        setViewPager();

        String year = getYear();
        mTvYear.setText(year);
    }

    private String getYear() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy年");
        return format.format(date);
    }

    private void setViewPager() {

        CalendarViewPagerAdapter<CalendarView> viewPagerAdapter = new CalendarViewPagerAdapter<>(mMonthCalendarViews);
        mViewPager.setAdapter(viewPagerAdapter);

        mViewPager.setOffscreenPageLimit(1);
        mTabLayout.setBackgroundColor(getResources().getColor(R.color.title_bg));
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setupWithViewPager(mViewPager);

        pagerListener = new CalendarViewPagerListener(mViewPager, viewPagerAdapter);
        mViewPager.addOnPageChangeListener(pagerListener);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mViewPager.requestLayout();
            }
        }, 150);

        List<String> titleList = addTitle(viewPagerAdapter);

        for (int i = 0; i < viewPagerAdapter.getCount(); i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);//获得每一个tab
            if (tab != null) {
                tab.setCustomView(R.layout.tab_item2);//给每一个tab设置view
                TextView textView = (TextView) tab.getCustomView().findViewById(R.id.tab_text);
                textView.setText(titleList.get(i));//设置tab上的文字
            }
        }

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("M月");
        String title = format.format(date);
//        String title = "10月";
        String month;
        //如果是10月及以后，截取的位置不同
        if (title.substring(0, 2).equals("10") || title.substring(0, 2).equals("11") || title.substring(0, 2).equals("12")) {
            month = title.substring(0, 2);
        } else {
            month = title.substring(0, 1);
        }
        int item = 35 + Integer.parseInt(month);
        mViewPager.setCurrentItem(item);

        pagerListener.update(item);
        //拿到tab选中的对应年份和月份
//        SimpleDateFormat format1 = new SimpleDateFormat("yyyy年");
//        mYearTime = format1.format(date);
//        TextView textView = (TextView) mTabLayout.getTabAt(35 + Integer.parseInt(month)).getCustomView().findViewById(R.id.tab_text);
//        ConstantUtils.monthTime = textView.getText().toString().trim();
//        getMonthSchedule(mYearTime, ConstantUtils.monthTime);//获取月程事项
//        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @SuppressLint("DefaultLocale")
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                if (tab != null) {
//                    tab.getCustomView().findViewById(R.id.tab_text).setSelected(true);
//                    mViewPager.setCurrentItem(tab.getPosition());
//                    String year = getYear();
//                    String yearText = year.substring(0, 4);
//                    int yearNum = Integer.parseInt(yearText);
//                    int selectYear = yearNum;
//                    if (tab.getPosition() < 36 && tab.getPosition() >= 0) {//2017年以前
//                        switch (tab.getPosition() / 12) {
//                            case 0:
//                                selectYear = yearNum - 3;
//                                break;
//                            case 1:
//                                selectYear = yearNum - 2;
//                                break;
//                            case 2:
//                                selectYear = yearNum - 1;
//                                break;
//                        }
//                        mTvYear.setText(String.format("%d年", selectYear));
//                    } else if (tab.getPosition() > 47 && tab.getPosition() < 84) {//2017年之后
//                        switch (tab.getPosition() / 12) {
//                            case 4:
//                                selectYear = yearNum + 1;
//                                break;
//                            case 5:
//                                selectYear = yearNum + 2;
//                                break;
//                            case 6:
//                                selectYear = yearNum + 3;
//                                break;
//                        }
//                        mTvYear.setText(String.format("%d年", selectYear));
//                    } else {
//                        mTvYear.setText(year);
//                    }
//                }
//
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//                tab.getCustomView().findViewById(R.id.tab_text).setSelected(false);
////                setTabLayoutCanClick(false);
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });

    }

    public void setTabLayoutCanClick(boolean canClick) {
        LinearLayout tabStrip = (LinearLayout) mTabLayout.getChildAt(0);
        for (int i = 0; i < tabStrip.getChildCount(); i++) {
            View tabView = tabStrip.getChildAt(i);
            if (tabView != null) {
                tabView.setClickable(canClick);
            }
        }
    }

    @NonNull
    public List<String> addTitle(CalendarViewPagerAdapter<CalendarView> viewPagerAdapter) {
//        long start = System.nanoTime();
        //将对应页面数量的标题添加到集合
        List<String> titleList = new ArrayList<>();
//        int count = viewPagerAdapter.getCount() / 12;
        int i, j;
        for (i = 0; i < 7; i++) {
            for (j = 1; j < 13; j++) {
                titleList.add(" " + j + "月 ");
            }
        }
//        Log.d("tag", "time = " + (System.nanoTime() - start));
        return titleList;
    }


    @Override
    public void clickDate(CustomDate date) {

    }

    @Override
    public void onMeasureCellHeight(int cellSpace) {
        cellHeight = cellSpace;
        if (rows != 0) {
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mViewPager.getLayoutParams();
            lp.height = rows * cellHeight;
            mViewPager.setLayoutParams(lp);
        }
    }

    @Override
    public void changeRowCount(int rows) {
        this.rows = rows;
        if (cellHeight != 0) {
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mViewPager.getLayoutParams();
            lp.height = rows * cellHeight;
            mViewPager.setLayoutParams(lp);
        }
    }

    @Override
    public RecordState setSignDateStatus(CustomDate date) {


        if (DateUtil.isToday(date)) {
            return RecordState.Today;
        }
        return null;
    }
}
