package com.xiaoxin.demo.widget.calendar;


import android.content.Context;
import android.util.AttributeSet;


public class CalendarMonthView extends CalendarView {

    private static final int TOTAL_COL = 7;
    private static final int TOTAL_ROW = 6;

    public CustomDate mShowDate;
    public CustomDate mClickDate;
    private OnCalenderListener mCallBack;
//    private int month;
//    private int year;
    private int mClickRow;
//    private CustomDate mCustomDate;


    public CalendarMonthView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public CalendarMonthView(Context context, OnCalenderListener callBack) {
        super(context);
        this.mCallBack = callBack;
        initDate();

    }

    public CalendarMonthView(Context context) {
        super(context);
        initDate();

    }

    @Override
    protected void initDate() {
        mShowDate = new CustomDate();
//        mCustomDate.month = mShowDate.month;
//        mCustomDate.year = mShowDate.year;
        fillDate();
    }


    @Override
    public void measureClickCell(int col, int row) {
        if (col >= TOTAL_COL || row >= TOTAL_ROW)
            return;
        Cell cell = rows[row].cells[col];
        if (cell == null || cell.state == State.PAST_MONTH_DAY || cell.state == State.NEXT_MONTH_DAY) {
            return;
        }
        CustomDate date = cell.date;
        mClickDate = date;
        fillMonthDate(false);
        invalidate();
    }


    private void fillDate() {
        fillMonthDate(true);
    }


    private void fillMonthDate(boolean isChangeClick) {

        int monthDay = DateUtil.getCurrentMonthDay(); // 今天
        int lastMonthDays = DateUtil.getMonthDays(mShowDate.year, mShowDate.month - 1);// 上个月的天数
        int currentMonthDays = DateUtil.getMonthDays(mShowDate.year, mShowDate.month); // 当前月的天数
        int firstDayWeek = DateUtil.getWeekDayFromDate(mShowDate.year, mShowDate.month);
        boolean isCurrentMonth = false;
        if (DateUtil.isCurrentMonth(mShowDate)) {
            isCurrentMonth = true;
            if (isChangeClick)
                mClickDate = CustomDate.modifiDayForObject(mShowDate, monthDay, 0);
        } else {
            if (isChangeClick)
                mClickDate = CustomDate.modifiDayForObject(mShowDate, 1, 0);
        }
        int day = 0;
        for (int j = 0; j < TOTAL_ROW; j++) {
            rows[j] = new Row(j);
            for (int i = 0; i < TOTAL_COL; i++) {
                int position = i + j * TOTAL_COL;// 单元格位置
//                CustomDate date;
                // 这个月的
                if (position >= firstDayWeek && position < firstDayWeek + currentMonthDays) {
                    day++;
                    if (day == currentMonthDays) {
                        int maxRow = j + 1;
                        if (mCallBack != null)
                            mCallBack.changeRowCount(maxRow);
                    }
                    mShowDate = CustomDate.modifiDayForObject(mShowDate, day, i);
                    RecordState state = getRecordDateState(mShowDate);
//                    if (date.isSameDay(mClickDate)) {
//                        //mClickDate.setWeek(i);
////                        if (mCallBack != null)
////                            //mCallBack.clickDate(date);
//                        mClickRow = j;
//                        updateCellData(j, i, date, State.CLICK_DAY, state);
//                        continue;
//                    }
                    // 今天
//                    if (isCurrentMonth && day == monthDay) {
//                        updateCellData(j, i, date, State.TODAY, state);
//                        continue;
//                    }
                    updateCellData(j, i, mShowDate, State.CURRENT_MONTH_DAY, state);
                    // 过去一个月
                } else if (position < firstDayWeek) {
                    mShowDate = new CustomDate(mShowDate.year, mShowDate.month - 1, lastMonthDays - (firstDayWeek - position - 1), i);
                    updateCellData(j, i, mShowDate, State.PAST_MONTH_DAY, RecordState.Unknown);
                    // 下个月
                } else if (position >= firstDayWeek + currentMonthDays) {
                    mShowDate = new CustomDate(mShowDate.year, mShowDate.month + 1, position - firstDayWeek - currentMonthDays + 1, i);
                    updateCellData(j, i, mShowDate, State.NEXT_MONTH_DAY, RecordState.Unknown);
                }
            }
        }
    }


    @Override
    public void update() {
        fillMonthDate(false);

        invalidate();
    }

    @Override
    public void backToday() {
        initDate();
        invalidate();
    }

    @Override
    public void update(CustomDate showDate, CustomDate clickDate) {

        mShowDate = showDate;
        mClickDate = clickDate;
        fillMonthDate(false);
        invalidate();
    }

    @Override
    public CustomDate getClickDate() {
        return mClickDate;
    }

    @Override
    public CustomDate getShowDate() {

        return rows[mClickRow].cells[TOTAL_COL - 1].date;
    }

    @Override
    public RecordState getRecordDateState(CustomDate date) {
        RecordState state = null;
        if (mCallBack != null) {
            state = mCallBack.setSignDateStatus(date);
        }
        return state != null ? state : RecordState.Unknown;
    }

    @Override
    public int getClickRow() {
        return mClickRow;
    }


    public void rightSlide(int position, int currIndex) {
        int monthCount = position - currIndex;
        switch (mShowDate.month) {
            case 12:
                setMonth(monthCount, 0);
                break;
            case 11:
                setMonth(monthCount, 1);
                break;
            case 10:
                setMonth(monthCount, 2);
                break;
            case 9:
                setMonth(monthCount, 3);
                break;
            case 8:
                setMonth(monthCount, 4);
                break;
            case 7:
                setMonth(monthCount, 5);
                break;
            case 6:
                setMonth(monthCount, 6);
                break;
            case 5:
                setMonth(monthCount, 7);
                break;
            case 4:
                setMonth(monthCount, 8);
                break;
            case 3:
                setMonth(monthCount, 9);
                break;
            case 2:
                setMonth(monthCount, 10);
                break;
            case 1:
                setMonth(monthCount, 11);
                break;
        }

        fillDate();
        invalidate();
    }

    private void setMonth(int monthCount, int i) {
        if (monthCount <= i) {//今年
            mShowDate.month += monthCount;
        } else if (monthCount >= (i + 1) && monthCount <= (i + 12)) {//明年
            mShowDate.month = monthCount - i;
            mShowDate.year += 1;
        } else if (monthCount >= (i + 13) && monthCount <= (i + 24)) {//后年
            mShowDate.month = (monthCount - 12) - i;
            mShowDate.year += 2;
        } else {//大后年
            mShowDate.month = (monthCount - 24) - i;
            mShowDate.year += 3;
        }
        mShowDate.setMonth(mShowDate.month);
        mShowDate.setYear(mShowDate.year);
    }

    private void setMonth2(int monthCount, int i) {
        if (monthCount < i) {//今年
            mShowDate.month -= monthCount;
        } else if (monthCount >= i && monthCount <= (i + 11)) {//去年
            mShowDate.month = (i + 12) - monthCount;
            mShowDate.year -= 1;
        } else if (monthCount >= (i + 12) && monthCount <= (i + 23)) {//前年
            mShowDate.month = (i + 24) - monthCount;
            mShowDate.year -= 2;
        } else {//大前年
            mShowDate.month = (i + 36) - monthCount;
            mShowDate.year -= 3;
        }
        mShowDate.setMonth(mShowDate.month);
        mShowDate.setYear(mShowDate.year);
    }

    public void leftSlide(int position, int currIndex) {
        int monthCount = currIndex - position;
        switch (mShowDate.month) {
            case 1:
                setMonth2(monthCount, 1);
                break;
            case 2:
                setMonth2(monthCount, 2);
                break;
            case 3:
                setMonth2(monthCount, 3);
                break;
            case 4:
                setMonth2(monthCount, 4);
                break;
            case 5:
                setMonth2(monthCount, 5);
                break;
            case 6:
                setMonth2(monthCount, 6);
                break;
            case 7:
                setMonth2(monthCount, 7);
                break;
            case 8:
                setMonth2(monthCount, 8);
                break;
            case 9:
                setMonth2(monthCount, 9);
                break;
            case 10:
                setMonth2(monthCount, 10);
                break;
            case 11:
                setMonth2(monthCount, 11);
                break;
            case 12:
                setMonth2(monthCount, 12);
                break;
        }

        fillDate();
        invalidate();
    }


    public void setOnCalenderListener(OnCalenderListener callBack) {
        this.mCallBack = callBack;
    }

    @Override
    public OnCalenderListener getOnCalenderListener() {
        return mCallBack;
    }


}
