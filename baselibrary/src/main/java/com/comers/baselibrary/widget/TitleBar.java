package com.comers.baselibrary.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.comers.baselibrary.R;

/**
 * Created by Comers on 2017/11/12.
 * ${DESCRIPTION}
 */

public class TitleBar extends LinearLayout {
    Context mContext;
    private RelativeLayout lyBack;
    private TextView txTitle;
    private TextView txRight;

    public TitleBar(Context context) {
        this(context, null);
    }

    public TitleBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
        initListener();
    }

    private void initListener() {
        lyBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnTitleItemClick!=null)
                    mOnTitleItemClick.onBack();
            }
        });
        txRight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnTitleItemClick!=null)
                    mOnTitleItemClick.onSure();
            }
        });
    }

    private void initView() {
        View view = View.inflate(mContext, R.layout.base_toobar, null);
        addView(view);
        lyBack = (RelativeLayout) view.findViewById(R.id.lyBack);
        txTitle = (TextView) view.findViewById(R.id.txTitle);
        txRight = (TextView) view.findViewById(R.id.txRight);
    }

    OnTitleItemClick mOnTitleItemClick;

   public interface OnTitleItemClick {
        void onBack();
        void onSure();
    }

    public void setOnTitleItemClickListener(OnTitleItemClick onTitleItemClick) {
        mOnTitleItemClick=onTitleItemClick;
    }
    public void setTitle(String title){
        txTitle.setText(title);
    }
    public void setRight(String text,OnClickListener onClickListener){
        txRight.setText(text);
        txRight.setOnClickListener(onClickListener);
    }
}
