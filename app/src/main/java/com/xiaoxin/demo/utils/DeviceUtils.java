package com.xiaoxin.demo.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.view.ViewCompat;
import android.telephony.TelephonyManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.xiaoxin.demo.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.Context.TELEPHONY_SERVICE;

/**
 * Created by yuanlili on 2016/2/24.
 */
public class DeviceUtils {
    public static String getVersionName(Context context) {
        try {
            String packageName = context.getPackageName();
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            return packageInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static float getDisplayDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    public static int getPx(Context context, int dp) {
        return (int) getDisplayDensity(context) * dp;
    }

    public static int getWindowHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static int getWindowWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static String setContent(Context context, String content){
        int maxLen = 0;
        if(getWindowWidth(context) <= 1080)
            maxLen = 120;
        else
            maxLen = 240;
        content = content.length() > maxLen ? content.substring(0,maxLen) +"..." : content;
        return content;
    }

    public static void hideKeyBoard(Context context, EditText editText) {
        if (editText == null) {
            return;
        }
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    public static void showKeyBoard(View view) {
        if (view == null) {
            return;
        }
        InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public static void hideInputMethodManager(InputMethodManager inputMethedManager, View view) {
        if (inputMethedManager != null && inputMethedManager.isActive() && view != null) {
            inputMethedManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static String getDateFormed(String date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        Date curDate = new Date();
        String dateStr = formatter.format(curDate);
        return dateStr;
    }

    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static boolean ifAvailable(Context context){
        if(isNetWorkConnected(context))
            return true;
        else {
            Toast.makeText(context, context.getResources().getString(R.string.network_error), Toast.LENGTH_LONG).show();
            return false;
        }
    }

    /**
     * 检测网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean isNetWorkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }

        return false;
    }

    /**
     * 判断当前是否wifi状态
     * @param mContext
     * @return
     */
    public static boolean isWifi(Context mContext) {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }

    // 获取设备唯一标识符，并储存
    @SuppressLint("HardwareIds")
    public static String getDeviceId(Context context){

        String deviceId = null;
        try{
            TelephonyManager TelephonyMgr = (TelephonyManager)context.getSystemService(TELEPHONY_SERVICE);
            deviceId = TelephonyMgr.getDeviceId();
        }catch (SecurityException e){ // android系统6.0，target
            e.printStackTrace();
        }
        return deviceId;
    }

    /**
     * 检查相机权限
     * @param mContext
     * @return
     */
//    public static boolean checkCameraLimits(final Activity mContext) {
//        Camera theCamera = OpenCameraInterface.open();
//        if(theCamera == null){
//            // camera error
//            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
//            builder.setTitle(mContext.getResources().getString(R.string.app_name));
//            builder.setMessage("相机打开出错，请检查权限或退出重试");
//            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    //mContext.finish();
//                    dialog.dismiss();
//                }
//
//            });
//            builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
//
//                @Override
//                public void onCancel(DialogInterface dialog) {
//                    //mContext.finish();
//                    dialog.dismiss();
//                }
//            });
//            builder.show();
//
//            return false;
//        }
//        return true;
//    }

    /**
     * 兼容api4.4一下的版本，view 的 isAttachedToWindow在4.4以上才增加的
     *
     * @param view
     * @return
     */
    public static boolean isAttachedToWindow(View view){
        return ViewCompat.isAttachedToWindow(view);
    }


    public static boolean isTouchPointInView(View view, MotionEvent event) {
        int[] location = new int[2];

        view.getLocationOnScreen(location);
        int left = location[0];
        int top = location[1];
        int right = left + view.getMeasuredWidth();
        int bottom = top + view.getMeasuredHeight();
        int x = (int) event.getRawX();    //android 坐标系
        int y = (int) event.getRawY();

        boolean res = false;
        if (
//                view.isClickable() &&
                y >= top && y <= bottom && x >= left && x <= right) {
            res = true;
        }
        return res;
    }
}
