package com.xiaoxin.demo.widget.calendar;


public interface OnCalenderListener {

	 void clickDate(CustomDate date);

	 void onMeasureCellHeight(int cellSpace);

	
	 void changeRowCount(int rows);

	 RecordState setSignDateStatus(CustomDate date);

}
