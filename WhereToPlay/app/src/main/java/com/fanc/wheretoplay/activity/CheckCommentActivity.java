package com.fanc.wheretoplay.activity;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.base.BaseFragmentActivity;
import com.fanc.wheretoplay.databinding.ActivityCheckCommentBinding;
import com.fanc.wheretoplay.fragment.AtmosphereCommentFragment;
import com.fanc.wheretoplay.fragment.CommentDetailFragment;
import com.fanc.wheretoplay.fragment.OtherCommentFragment;
import com.fanc.wheretoplay.fragment.ServiceCommentFragment;
import com.fanc.wheretoplay.util.Constants;
import com.fanc.wheretoplay.util.UIUtils;
import com.fanc.wheretoplay.view.MyViewPager;
import com.fanc.wheretoplay.view.PageHorizontalScrollView;
import com.fanc.wheretoplay.view.TopMenu;

import java.util.ArrayList;
import java.util.List;

public class CheckCommentActivity extends BaseFragmentActivity {
    ActivityCheckCommentBinding binding;

    CoordinatorLayout mCdlCheckComment;
    TopMenu mTmCheckComment;
    MyViewPager mMvpCheckComment;
    PageHorizontalScrollView mPhsvCheckComment;
    LinearLayout mLlPhsvCheckComment;
    AppBarLayout mAblCheckComment;
    ImageView mIvCommentTabLabelBack;
    FrameLayout mFlCheckComment;

    List<Fragment> fragments;

    int mTopMenuHeight;
    // 上滑到顶部时的padding
    int topPadding;
    // 当前选中的tab
    TextView textView;
    // tab总数
    int tabCount;
    // 当前选中的tab下标
    int selectedPosition;

    String storeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_check_comment);
        initViews();
        init();
        setListeners();
    }

    private void initViews() {
        mCdlCheckComment = binding.cdlCheckItem;
        mTmCheckComment = binding.tmCheckComment;
        mMvpCheckComment = binding.mvpCheckComment;
        mPhsvCheckComment = binding.phsvCheckComment;
        mLlPhsvCheckComment = binding.llPhsvCheckComment;
        mAblCheckComment = binding.ablCheckComment;
        mIvCommentTabLabelBack = binding.ivCommentTabLabelBack;
        mFlCheckComment = binding.flCheckCommentTab;
    }

    private void init() {
        mTmCheckComment.setLeftIcon(R.drawable.left);
        mTmCheckComment.setTitle(R.string.check_comment);
        storeId = getIntent().getStringExtra(Constants.STORE_ID);

        initFragmentPage();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            topPadding = getStatuBarHeight();
        } else {
            topPadding = 0;
        }
    }

    private void setListeners() {
        mTmCheckComment.setLeftIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mAblCheckComment.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.w("aaa", "verticalOffset:" + verticalOffset);
                Log.d("aaa", "onOffsetChanged: mTopMenuHeight =" + mTopMenuHeight);
                Log.d("aaa", "onOffsetChanged: selectedPosition = " + selectedPosition + "\t tabCount = " + tabCount);
                float offset = Math.abs(verticalOffset);
                // 返回按钮
                FrameLayout.LayoutParams mIvLp = (FrameLayout.LayoutParams) mIvCommentTabLabelBack.getLayoutParams();
                if (offset <= mTopMenuHeight * 3 / 4) {
                    if (mIvCommentTabLabelBack.getVisibility() != View.GONE) {
                        mIvCommentTabLabelBack.setVisibility(View.GONE);
                    }
                    mIvLp.setMargins(UIUtils.dp2Px(20), 0, 0, 0);
                } else {
                    if (mIvCommentTabLabelBack.getVisibility() != View.VISIBLE) {
                        mIvCommentTabLabelBack.setVisibility(View.VISIBLE);
                    }
                    mIvCommentTabLabelBack.setAlpha(((offset - mTopMenuHeight * 3 / 4) / mTopMenuHeight) * 4);
                    mIvLp.setMargins(UIUtils.dp2Px(20), (int) (topPadding * offset / mTopMenuHeight * 2 / 4), 0, 0);
                }
                mIvCommentTabLabelBack.setLayoutParams(mIvLp);
                // tab
                if (textView != null) {
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) textView.getLayoutParams();

                    if (offset == 0) {
                        // 选中的还原
                        layoutParams.width = (int) (UIUtils.getScreenWidth() * 0.6);
                        layoutParams.height = UIUtils.dp2Px(60);
                        if (selectedPosition == tabCount - 1) {
                            layoutParams.setMargins(UIUtils.dp2Px(10) + 2, 0, (int) (UIUtils.getScreenWidth() * 0.4 - UIUtils.dp2Px(10)), 0);
                        } else {
                            layoutParams.setMargins(UIUtils.dp2Px(10), 0, 0, 0);
                        }
                        textView.setPadding(0, 0, 0, 0);
                        // 外层
                        mFlCheckComment.setPadding(0, UIUtils.dp2Px(10), 0, UIUtils.dp2Px(10));
                    } else {
                        // 选中的放大
                        int width = (int) (UIUtils.getScreenWidth() * (0.6 + 0.4 * (offset * 1 / mTopMenuHeight)));
                        if (width <= (int) (UIUtils.getScreenWidth() * 0.6)) {
                            width = (int) (UIUtils.getScreenWidth() * 0.6);
                        }
                        layoutParams.width = width;
                        layoutParams.height = (int) (UIUtils.dp2Px(60) + UIUtils.dp2Px(20) * offset / mTopMenuHeight);
                        int margin = (int) (UIUtils.dp2Px(10) * ((mTopMenuHeight - offset) / mTopMenuHeight));
                        if (selectedPosition == tabCount - 1) {
                            layoutParams.setMargins(margin + 2, 0, (int) ((UIUtils.getScreenWidth() * 0.4 - UIUtils.dp2Px(10)) * ((mTopMenuHeight - offset) / mTopMenuHeight)), 0);
                        } else {
                            layoutParams.setMargins(margin, 0, 0, 0);
                        }
                        textView.setPadding(0, (int) (topPadding * offset / mTopMenuHeight), 0, 0);
                        // 外层
                        mFlCheckComment.setPadding(0, margin, 0, margin);
                    }
                    textView.setLayoutParams(layoutParams);
                }
                if (offset > 1) {
                    mPhsvCheckComment.setNoScroll(true);
                    mMvpCheckComment.setNoScroll(true);
                } else {
                    mPhsvCheckComment.setNoScroll(false);
                    mMvpCheckComment.setNoScroll(false);
                }
            }
        });
        mPhsvCheckComment.setOnPageSelectedListener(new PageHorizontalScrollView.OnPageSelectedListener() {
            @Override
            public void onPageSelected(int position, View view) {
                selectedPosition = position;
                textView = (TextView) view;
                mMvpCheckComment.setCurrentItem(position);
            }
        });
        mMvpCheckComment.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mPhsvCheckComment.gotoPage(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mIvCommentTabLabelBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initTabLabel() {
        mPhsvCheckComment.gotoPage(0);
    }

    private void initFragmentPage() {
        fragments = new ArrayList<>();
        fragments.add(new CommentDetailFragment().setPageTag(Constants.COMMENT_DETAIL_ENVIRONMENT).setStoreId(storeId));
        fragments.add(new CommentDetailFragment().setPageTag(Constants.COMMENT_DETAIL_ATMOSPHERE).setStoreId(storeId));
        fragments.add(new CommentDetailFragment().setPageTag(Constants.COMMENT_DETAIL_SERVICE).setStoreId(storeId));
        fragments.add(new CommentDetailFragment().setPageTag(Constants.COMMENT_DETAIL_OTHER).setStoreId(storeId));

        mMvpCheckComment.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }
        });
        mMvpCheckComment.setCurrentItem(0);
        mMvpCheckComment.setOffscreenPageLimit(3);

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            mTopMenuHeight = mTmCheckComment.getBottom();
            tabCount = mPhsvCheckComment.getPageCount();
            textView = (TextView) mPhsvCheckComment.getSelectedItem();
        }
    }
}
