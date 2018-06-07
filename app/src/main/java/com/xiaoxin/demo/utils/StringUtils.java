package com.xiaoxin.demo.utils;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import java.util.regex.Pattern;

/**
 * Created by yuanlili on 2016/2/24.
 */
public class StringUtils {
    public static final String EMPTY = "";
    /**
     * Checks if a String is empty ("") or null.
     * StringUtils.isEmpty(null)      = true
     * StringUtils.isEmpty("")        = true
     * StringUtils.isEmpty(" ")       = false
     * StringUtils.isEmpty("bob")     = false
     * StringUtils.isEmpty("  bob  ") = false
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0 || str.equals("null");
    }

    public static boolean isNotEmpty(String str) {
        return !StringUtils.isEmpty(str);
    }

    /**
     * Checks if a String is whitespace, empty ("") or null.
     * StringUtils.isBlank(null)      = true
     * StringUtils.isBlank("")        = true
     * StringUtils.isBlank(" ")       = true
     * StringUtils.isBlank("bob")     = false
     * StringUtils.isBlank("  bob  ") = false
     */
    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotBlank(String str) {
        return !StringUtils.isBlank(str);
    }

    public static String passwordToStar(String str) {
        if (StringUtils.isEmpty(str)) {
            return "";
        }
        return StringUtils.substring(str, 0, 1) + "********"
                + StringUtils.substring(str, str.length() - 1, str.length());
    }

    public static String substring(String str, int start, int end) {
        if (str == null) {
            return null;
        }

        // handle negatives
        if (end < 0) {
            end = str.length() + end; // remember end is negative
        }
        if (start < 0) {
            start = str.length() + start; // remember start is negative
        }

        // check length next
        if (end > str.length()) {
            end = str.length();
        }

        // if start is greater than end, return ""
        if (start > end) {
            return EMPTY;
        }

        if (start < 0) {
            start = 0;
        }
        if (end < 0) {
            end = 0;
        }

        return str.substring(start, end);
    }

    /**
     * 规范语音显示时长格式
     * @param audioDuration
     * @return
     */
    public static String formatAudioDuration(int audioDuration){
        String duration = null;
        if (audioDuration <= 60) {
            duration = audioDuration + "\"";
        } else {
            duration = audioDuration / 60 + "' " + audioDuration % 60 + "\"";
        }
        return duration;
    }

    public static String replace(String text, String searchString, String replacement) {
        return replace(text, searchString, replacement, -1);
    }

    public static String replace(String text, String searchString, String replacement, int max) {
        if (isEmpty(text) || isEmpty(searchString) || replacement == null || max == 0) {
            return text;
        }
        int start = 0;
        int end = text.indexOf(searchString, start);
        if (end == -1) {
            return text;
        }
        int replLength = searchString.length();
        int increase = replacement.length() - replLength;
        increase = (increase < 0 ? 0 : increase);
        increase *= (max < 0 ? 16 : (max > 64 ? 64 : max));
        StringBuffer buf = new StringBuffer(text.length() + increase);
        while (end != -1) {
            buf.append(text.substring(start, end)).append(replacement);
            start = end + replLength;
            if (--max == 0) {
                break;
            }
            end = text.indexOf(searchString, start);
        }
        buf.append(text.substring(start));
        return buf.toString();
    }

    /**
     * 改变部分文字的颜色
     * @param tv
     * @param str
     * @param color
     * @param start
     * @param end
     */
    public static void changeTextColor(TextView tv, String str, int color, int start, int end){
        SpannableStringBuilder builder = new SpannableStringBuilder(str);
        //ForegroundColorSpan 为文字前景色，BackgroundColorSpan为文字背景色
        ForegroundColorSpan redSpan = new ForegroundColorSpan(color);
        builder.setSpan(redSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(builder);
    }

    /**
     * string转int
     * @param text
     * @return
     */
    public static int string2int(String text){
        try {
            int value = Integer.parseInt(text);
            return value;
        }catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * int转string
     * @param value
     * @return
     */
    public static String int2String(int value){
        try {
            String text = String.valueOf(value);
            return text;
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 判断是否是数字
     */
    public static  boolean checkInteger(String text){
        Pattern pattern = Pattern.compile("^[-+]?[0-9]");
        if(pattern.matcher(text).matches())
            return true;
        else
            return false;
    }

    public static String addZeroToInt(int k) {
        if (k / 10 == 0) {
            return "0" + k;
        }
        return String.valueOf(k);
    }
}
