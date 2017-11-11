package com.fanc.wheretoplay.view;

import com.fanc.wheretoplay.datamodel.BookList;
import com.fanc.wheretoplay.datamodel.BookListModel;


/**
 * Created by admin on 2017/11/3.
 */

public interface OrderListFragmentView {
    void setOrderListFragmentData(BookListModel.ContentBean contentBean, String action);
}
