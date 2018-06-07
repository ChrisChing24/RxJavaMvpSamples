package com.xiaoxin.demo.widget.calendar;


import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

public class CalendarViewPagerListener implements OnPageChangeListener {

    private SlideDirection mDirection = SlideDirection.NO_SILDE;
    public int mCurrIndex;
    private CalendarView[] mShowViews;
    private CalendarViewPagerAdapter<CalendarView> mPagerAdapter;
    private ViewPager mPager;
//    private int index = 0;
//    private boolean isFirst;

    public CalendarViewPagerListener(ViewPager viewPager, CalendarViewPagerAdapter<CalendarView> viewAdapter) {
        this.mShowViews = viewAdapter.getAllItems();
        this.mPager = viewPager;
        this.mPagerAdapter = viewAdapter;
//        this.isFirst = isFirstSelect;
    }

    @Override
    public void onPageSelected(int position) {
//        if (isFirst){
//            mCurrIndex = position;
//            isFirst = false;
//        }
        measureDirection(position);
        updateCalendarView(position);
    }

    private void updateCalendarView(int position) {
        CalendarView calendar = mShowViews[position % mShowViews.length];
        if (mDirection == SlideDirection.RIGHT) {//向后选择月份
            calendar.rightSlide(position, mCurrIndex);
        } else if (mDirection == SlideDirection.LEFT) {//向前选择月份
            calendar.leftSlide(position, mCurrIndex);
        }
        mDirection = SlideDirection.NO_SILDE;
    }


    private void measureDirection(int position) {

        if (position > mCurrIndex) {
            mDirection = SlideDirection.RIGHT;

        } else if (position < mCurrIndex) {
            mDirection = SlideDirection.LEFT;
        }else {
            mDirection = SlideDirection.NO_SILDE;
        }
        mCurrIndex = position;
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
    }


    public int getCalendarRow() {
        CalendarView calendar = mShowViews[mPager.getCurrentItem() % mShowViews.length];
        return calendar.getClickRow();

    }

    public CalendarView getCurrentCalendar() {
        return mShowViews[mPager.getCurrentItem() % mShowViews.length];

    }


    public void updateCalendar() {
        CalendarView calendar = mShowViews[mPager.getCurrentItem() % mShowViews.length];
        calendar.update();
    }

    public void backTaody() {
        CalendarView calendar = mShowViews[mPager.getCurrentItem() % mShowViews.length];
        calendar.backToday();
    }

    public void update(int item) {
        this.mCurrIndex = item;
    }


    enum SlideDirection {
        RIGHT, LEFT, NO_SILDE;
    }
}