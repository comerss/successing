package com.qiezzi.choseviewlibrary.view;


import android.view.View;

import com.qiezzi.choseviewlibrary.R;
import com.qiezzi.choseviewlibrary.adapter.ArrayWheelAdapter;
import com.qiezzi.choseviewlibrary.lib.WheelView;
import com.qiezzi.choseviewlibrary.listener.OnItemSelectedListener;

import java.util.ArrayList;


public class TimeSelectOptions<T> {
	private View view;
	private WheelView wv_option1;
	private WheelView wv_option2;
	private WheelView wv_option3;
	private WheelView wv_option4;

	private ArrayList<T> mOptions1Items;
	private ArrayList<T> mOptions2Items;
	private ArrayList<T> mOptions3Items;
	private ArrayList<T> mOptions4Items;

    private boolean linkage = false;
    private OnItemSelectedListener wheelListener_option1;
    private OnItemSelectedListener wheelListener_option2;

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	public TimeSelectOptions(View view) {
		super();
		this.view = view;
		setView(view);
	}

	public void setPicker(ArrayList<T> options1Items,
			ArrayList<T> options2Items,
			ArrayList<T> options3Items,ArrayList<T> options4Items,
			boolean linkage) {
        this.linkage = linkage;
		this.mOptions1Items = options1Items;
		this.mOptions2Items = options2Items;
		this.mOptions3Items = options3Items;
		this.mOptions4Items = options4Items;
		int len = ArrayWheelAdapter.DEFAULT_LENGTH;
		if (this.mOptions3Items == null)
			len = 8;
		if (this.mOptions2Items == null)
			len = 12;
		// 选项1
		wv_option1 = (WheelView) view.findViewById(R.id.options1);
		wv_option1.setAdapter(new ArrayWheelAdapter(mOptions1Items, len));// 设置显示数据
		wv_option1.setCurrentItem(0);// 初始化时显示的数据
		wv_option1.setVisibleCount(9);
		// 选项2
		wv_option2 = (WheelView) view.findViewById(R.id.options2);
		if (mOptions2Items != null)
			wv_option2.setAdapter(new ArrayWheelAdapter(mOptions2Items));// 设置显示数据
		wv_option2.setCurrentItem(wv_option1.getCurrentItem());// 初始化时显示的数据
		wv_option2.setVisibleCount(9);
		// 选项3
		wv_option3 = (WheelView) view.findViewById(R.id.options3);
		if (mOptions3Items != null)
			wv_option3.setAdapter(new ArrayWheelAdapter(mOptions3Items));// 设置显示数据
		wv_option3.setCurrentItem(wv_option3.getCurrentItem());// 初始化时显示的数据
		wv_option3.setVisibleCount(9);

		wv_option4 = (WheelView) view.findViewById(R.id.options4);
		if (mOptions4Items != null)
			wv_option4.setAdapter(new ArrayWheelAdapter(mOptions4Items));// 设置显示数据
		wv_option4.setCurrentItem(wv_option4.getCurrentItem());// 初始化时显示的数据
		wv_option4.setVisibleCount(9);

		int textSize = 21;

		wv_option1.setTextSize(textSize);
		wv_option2.setTextSize(textSize);
		wv_option3.setTextSize(textSize);
		wv_option4.setTextSize(textSize);

		if (this.mOptions2Items == null)
			wv_option2.setVisibility(View.GONE);
		if (this.mOptions3Items == null)
			wv_option3.setVisibility(View.GONE);
	}


	/**
	 * 设置是否循环滚动
	 * @param cyclic 是否循环
	 */
	public void setCyclic(boolean cyclic) {
		if(wv_option1 !=null){
			wv_option1.setCyclic(cyclic);
		}
		if (wv_option2!=null){
			wv_option2.setCyclic(cyclic);
		}
		if (wv_option3!=null){
			wv_option3.setCyclic(cyclic);
		}
		if (wv_option4!=null){
			wv_option4.setCyclic(cyclic);
		}
	}

	/**
	 * 分别设置第一二三级是否循环滚动
	 * @param cyclic1,cyclic2,cyclic3 是否循环
	 */
	public void setCyclic(boolean cyclic1,boolean cyclic2,boolean cyclic3) {
        wv_option1.setCyclic(cyclic1);
        wv_option2.setCyclic(cyclic2);
        wv_option3.setCyclic(cyclic3);
	}
    /**
     * 设置第二级是否循环滚动
     * @param cyclic 是否循环
     */
    public void setOption2Cyclic(boolean cyclic) {
        wv_option2.setCyclic(cyclic);
    }

	/**
     * 设置第三级是否循环滚动
     * @param cyclic 是否循环
     */
    public void setOption3Cyclic(boolean cyclic) {
        wv_option3.setCyclic(cyclic);
    }

	/**
	 * 返回当前选中的结果对应的位置数组 因为支持三级联动效果，分三个级别索引，0，1，2
	 * @return 索引数组
     */
	public int[] getCurrentItems() {
		int[] currentItems = new int[4];
		currentItems[0] = wv_option1.getCurrentItem();
		currentItems[1] = wv_option2.getCurrentItem();
		currentItems[2] = wv_option3.getCurrentItem();
		currentItems[3] = wv_option4.getCurrentItem();
		return currentItems;
	}

	public void setCurrentItems(int option1, int option2, int option3,int option4) {
        if(linkage){
            itemSelected(option1, option2, option3, option4);
        }
        wv_option1.setCurrentItem(option1);
        wv_option2.setCurrentItem(option2);
        wv_option3.setCurrentItem(option3);
        wv_option4.setCurrentItem(option4);
	}

	private void itemSelected(int opt1Select, int opt2Select, int opt3Select,int opt4Select) {
		if (mOptions1Items != null) {
			wv_option1.setAdapter(new ArrayWheelAdapter(mOptions1Items));
			wv_option1.setCurrentItem(opt1Select);
		}
		if (mOptions2Items != null) {
			wv_option2.setAdapter(new ArrayWheelAdapter(mOptions2Items));
			wv_option2.setCurrentItem(opt2Select);
		}
		if (mOptions3Items != null) {
			wv_option3.setAdapter(new ArrayWheelAdapter(mOptions3Items));
			wv_option3.setCurrentItem(opt3Select);
		}
		if(mOptions4Items!=null){
			wv_option4.setAdapter(new ArrayWheelAdapter(mOptions4Items));
			wv_option4.setCurrentItem(opt4Select);
		}
	}


}
