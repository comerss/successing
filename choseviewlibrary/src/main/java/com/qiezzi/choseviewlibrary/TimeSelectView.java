package com.qiezzi.choseviewlibrary;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qiezzi.choseviewlibrary.view.BasePickerView;
import com.qiezzi.choseviewlibrary.view.TimeSelectOptions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by code5 on 2016/12/8
 */
public class TimeSelectView<T> extends BasePickerView implements View.OnClickListener {

    private final ArrayList<String> timeNeed;
    TimeSelectOptions<String> wheelOptions;
    private View btnSubmit, btnCancel;
    private TextView tvTitle;
    private OnOptionsSelectListener optionsSelectListener;
    private static final String TAG_SUBMIT = "submit";
    private static final String TAG_CANCEL = "cancel";
    private final RelativeLayout mRlTitle;
    private final ArrayList<String> mTimes;
    int[] selectPostion = new int[4];

    public TimeSelectView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.pickerview_option, contentContainer);
        // -----确定和取消按钮
        mRlTitle = (RelativeLayout) findViewById(R.id.rlTitle);
        btnSubmit = findById(R.id.btnSubmit);
        btnSubmit.setTag(TAG_SUBMIT);
        btnCancel = findById(R.id.btnCancel);
        btnCancel.setTag(TAG_CANCEL);
        btnSubmit.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        //顶部标题
        tvTitle = (TextView) findById(R.id.tvTitle);
        // ----转轮
        final View optionspicker = findById(R.id.optionspicker);
        wheelOptions = new TimeSelectOptions(optionspicker);
        mTimes = new ArrayList(Arrays.asList(context.getResources().getStringArray(R.array.hours)));
        timeNeed = new ArrayList(Arrays.asList(context.getResources().getStringArray(R.array.minutes)));
        wheelOptions.setPicker(mTimes, timeNeed, mTimes, timeNeed, false);
        setCyclic(true);
        initTime();
    }

    /*
       * 初始化显示的时间
       * */
    private SimpleDateFormat mFormatter = new SimpleDateFormat("HH:mm");

    private void initTime() {
        wheelOptions.setCurrentItems(selectPostion[0], selectPostion[1], selectPostion[2], selectPostion[3]);
    }

    /**
     * 设置选中的item位置
     *
     * @param option1 位置
     */
    public void setCurrentItems(int option1) {
        wheelOptions.setCurrentItems(option1, 0, 0, 0);
    }

    public void setTitleVisible(boolean visible) {
        if (visible) {
            mRlTitle.setVisibility(View.VISIBLE);
        } else {
            mRlTitle.setVisibility(View.GONE);
        }
    }

    /**
     * 设置选中的item位置
     *
     * @param option1 位置
     * @param option2 位置
     */
    public void setCurrentItems(int option1, int option2) {
        wheelOptions.setCurrentItems(option1, option2, 0, 0);
    }

    /**
     * 设置选中的item位置
     *
     * @param option1 位置
     * @param option2 位置
     * @param option3 位置
     */
    public void setCurrentItems(int option1, int option2, int option3, int op4) {
        wheelOptions.setCurrentItems(option1, option2, option3, op4);
    }

    /**
     * 设置是否循环滚动
     *
     * @param cyclic 是否循环
     */
    public void setCyclic(boolean cyclic) {
        wheelOptions.setCyclic(cyclic);
    }

    public void setCyclic(boolean cyclic1, boolean cyclic2, boolean cyclic3) {
        wheelOptions.setCyclic(cyclic1, cyclic2, cyclic3);
    }


    @Override
    public void onClick(View v) {
        String tag = (String) v.getTag();
        if (tag.equals(TAG_CANCEL)) {
            if (mOnSelectListener != null) {
                mOnSelectListener.onCancle();
            }
            dismiss();
            return;
        } else {
            if (optionsSelectListener != null) {
                int[] optionsCurrentItems = wheelOptions.getCurrentItems();
                optionsSelectListener.onOptionsSelect(optionsCurrentItems[0], optionsCurrentItems[1], optionsCurrentItems[2]);
            }
            if (mOnSelectListener != null) {
                int[] optionsCurrentItems = wheelOptions.getCurrentItems();
                selectPostion = optionsCurrentItems;
                String hours1 = mTimes.get(optionsCurrentItems[0]).toString();
                String minutes1 = timeNeed.get(optionsCurrentItems[1]).toString();
                String hours2 = mTimes.get(optionsCurrentItems[2]).toString();
                String minutes2 = timeNeed.get(optionsCurrentItems[3]).toString();
                String startTime = hours1.substring(0, 2) + ":" + minutes1.substring(0, 2);
                String endTime = hours2.substring(0, 2) + ":" + minutes2.substring(0, 2);
                mOnSelectListener.onSelectTimeSelect(startTime, endTime);
            }
            dismiss();
            return;
        }
    }

    public void setTime(String time) {
        if (TextUtils.isEmpty(time)){
            return;
        }
        if(time.equals("暂未设置")){
            return;
        }
        String[] split = time.split("-");
        String[] startTime = split[0].split(":");
        String[] endTime = split[1].split(":");
        String startHour = startTime[0];
        String endHour = endTime[0];
        String startMinute = startTime[1];
        String endMinute = endTime[1];
        for(int i=0;i<mTimes.size();i++){
            if(mTimes.get(i).contains(startHour)){
                selectPostion[0]=i;
            }
            if(mTimes.get(i).contains(endHour)){
                selectPostion[2]=i;
            }
        }
        for(int j=0;j<timeNeed.size();j++){
            if(timeNeed.get(j).contains(startMinute)){
                selectPostion[1]=j;
            }
            if(timeNeed.get(j).contains(endMinute)){
                selectPostion[3]=j;
            }
        }
        initTime();
    }

    public interface OnOptionsSelectListener {
        void onOptionsSelect(int options1, int option2, int options3);
    }

    public void setOnoptionsSelectListener(
            OnOptionsSelectListener optionsSelectListener) {
        this.optionsSelectListener = optionsSelectListener;
    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    public interface OnSelectListener {
        void onSelectTimeSelect(String startTime, String endTime);

        void onCancle();
    }

    private OnSelectListener mOnSelectListener;

    public void setOnSelectedListener(
            OnSelectListener onSelectListener) {
        this.mOnSelectListener = onSelectListener;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            dismiss();
            Log.i("MineTimePicker","返回退出view");
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }
}
