package com.fanc.wheretoplay.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.fanc.wheretoplay.R;

import java.util.List;

/**
 * @author admin
 * @date 2018/3/21
 */

public class SpinnerItem extends RelativeLayout {

    private Spinner spinner;
    private ArrayAdapter<String> arrayAdapter;
    private String value;
    private TextView tv ;

    public SpinnerItem(Context context) {
        super(context);
        init(context);
    }

    public SpinnerItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SpinnerItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        View view = View.inflate(context, R.layout.spinneritem, this);
        spinner = view.findViewById(R.id.sp);
        tv = view.findViewById(R.id.tv);

    }

    public void setData(List<String> list, Context context,String leftData) {


        tv.setText(leftData);
        value = list.get(0);
        arrayAdapter = new ArrayAdapter(context, R.layout.spinner_list_item1, list);
        arrayAdapter.setDropDownViewResource(R.layout.spinner_list_item);
        spinner.setAdapter(arrayAdapter);
    }

    public String getValue() {

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                value = arrayAdapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return value;
    }

}
