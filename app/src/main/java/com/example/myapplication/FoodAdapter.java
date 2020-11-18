package com.example.myapplication;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public
class FoodAdapter extends BaseQuickAdapter<FoodBean.DataBean, BaseViewHolder> {
    public FoodAdapter() {
        super(R.layout.item_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, FoodBean.DataBean item) {
        helper.setText(R.id.item_text, "略略略");
        Glide.with(mContext)
                .load(item.pic)
                .into((ImageView) helper.getView(R.id.item_img));
    }
}
