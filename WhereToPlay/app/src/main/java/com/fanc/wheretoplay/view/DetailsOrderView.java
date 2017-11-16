package com.fanc.wheretoplay.view;

import com.fanc.wheretoplay.datamodel.OrderDetailModel;

/**
 * Created by admin on 2017/11/15.
 */

public interface DetailsOrderView {
    public void setDetailsOrderViewData(OrderDetailModel.ContentBean contentBean);
    public void cancelOrderAction();
}
