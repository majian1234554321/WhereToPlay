package com.fanc.wheretoplay.datamodel;

import com.fanc.wheretoplay.base.BaseModel;

/**
 * Created by peace on 2017/11/14.
 */

public class DelectCollection extends BaseModel {

    /**
     * content : {"is_ok":true}
     */

    private ContentBean content;

    public ContentBean getContent() {
        return content;
    }

    public void setContent(ContentBean content) {
        this.content = content;
    }

    public static class ContentBean {
        /**
         * is_ok : true
         */

        private boolean is_ok;

        public boolean isIs_ok() {
            return is_ok;
        }

        public void setIs_ok(boolean is_ok) {
            this.is_ok = is_ok;
        }
    }
}
