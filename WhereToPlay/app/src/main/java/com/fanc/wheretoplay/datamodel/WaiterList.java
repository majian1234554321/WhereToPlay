package com.fanc.wheretoplay.datamodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.fanc.wheretoplay.BR;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.base.BaseModel;
import com.fanc.wheretoplay.image.BlurBitmap;
import com.fanc.wheretoplay.network.Network;
import com.fanc.wheretoplay.util.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017/7/7.
 */

public class WaiterList extends BaseModel {
    private String explain;// 解释说明
    private List<WaiterInfo> list;


    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public List<WaiterInfo> getList() {
        return list;
    }

    public void setList(List<WaiterInfo> list) {
        this.list = list;
    }

    public static class WaiterInfo extends BaseObservable {
        /**
         * id: string, 服务员ID
         * name: string, 服务员名字
         * image：string, 服务员图片路径
         * price：string, 查看服务员图片价格，单位元
         * type: string，是否查看了服务员，0-未查看 1-已查看
         */

        private String id;
        private String name;
        private String image;
        private String price;
        private int type;

//        @BindingAdapter(value = {"url"}, requireAll = false)
//        public static void setWaiterImage(final ImageView imageView, String url) {
//            int id = imageView.getId();
//            if (id == R.id.iv_item_waiter_distinct) {
//                Glide.with(imageView.getContext()).load(Network.IMAGE + url)
//                        .placeholder(R.drawable.default_square).error(R.drawable.default_square)
//                        .into(imageView);
//            } else if (id == R.id.iv_item_waiter_fuzzy) {
//                if (!TextUtils.isEmpty(url)) {
//                    try {
//                        String imageName = url.substring(url.lastIndexOf("/") + 1, url.length() - 1);
//                        final File image = new File(FileUtils.getCacheDir() + imageName);
//                        if (image.exists()) {// 图片已经存在
//                            imageView.setImageBitmap(BitmapFactory.decodeStream(new FileInputStream(image)));
//                        } else {// 不存在
//                            Glide.with(imageView.getContext()).load(Network.IMAGE + url).asBitmap()
//                                    .placeholder(R.drawable.default_square).error(R.drawable.default_square)
//                                    .into(new SimpleTarget<Bitmap>() {
//                                        @Override
//                                        public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
//                                            if (bitmap != null) {
//                                                // 模糊处理
//                                                Bitmap bm = BlurBitmap.fastblur(imageView.getContext(), bitmap, 25);
//                                                imageView.setImageBitmap(bm);
//                                                // 保存模糊后的图片
//                                                try {
//                                                    FileOutputStream fileOutputStream = new FileOutputStream(image);
//                                                    bm.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream);
//                                                    fileOutputStream.flush();
//                                                    fileOutputStream.close();
//                                                } catch (FileNotFoundException e) {
//                                                    e.printStackTrace();
//                                                } catch (IOException e) {
//                                                    e.printStackTrace();
//                                                }
//                                            }
//                                        }
//
//                                        @Override
//                                        public void onLoadFailed(Exception e, Drawable errorDrawable) {
//                                            e.printStackTrace();
//                                            Bitmap bm = BlurBitmap.fastblur(imageView.getContext(),
//                                                    BitmapFactory.decodeResource(imageView.getContext().getResources(), R.drawable.default_square), 25);
//                                            imageView.setImageBitmap(bm);
//                                            super.onLoadFailed(e, errorDrawable);
//                                        }
//                                    });
//                        }
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                } else {
//                    imageView.setImageResource(R.drawable.default_square);
//                }
//            } else {
//                Glide.with(imageView.getContext()).load(Network.IMAGE + url)
//                        .placeholder(R.drawable.default_rect)
//                        .into(imageView);
//            }
//        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        @Bindable
        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
            notifyPropertyChanged(BR.type);
        }
    }
}
