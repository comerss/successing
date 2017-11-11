package com.qiezzi.choseviewlibrary.bean;

import com.qiezzi.choseviewlibrary.model.IPickerViewData;

/**
 * Created by code5 on 2017/1/12
 */
public class District implements IPickerViewData {
    public String D;
    public String DC;

    @Override
    public String getPickerViewText() {
        return D;
    }
}
