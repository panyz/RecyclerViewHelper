package com.pyz.rvhelper.adapter;

import android.content.Context;
import android.view.LayoutInflater;

import com.pyz.rvhelper.common.ItemViewDelegate;
import com.pyz.rvhelper.common.ViewHolder;

import java.util.List;

/**
 * @Author: panyz
 * @Package: com.pyz.rvhelper.common
 * @Description: TODO
 * @Project: RecyclerViewHelper
 * @Company: 深圳君南信息系统有限公司
 * @Date： 2016/10/24 18:00
 */

public abstract class SingleItemTypeAdapter<T> extends MultiItemTypeAdapter<T> {
	protected Context mContext;
	protected int layoutId;
	protected List<T> mDatas;
	protected LayoutInflater mInflater;

	public SingleItemTypeAdapter(Context mContext, List<T> mDatas, final int layoutId) {
		super(mContext, mDatas);
		this.mContext = mContext;
		this.mDatas = mDatas;
		this.layoutId = layoutId;
		mInflater = LayoutInflater.from(mContext);

		addItemViewDelegate(new ItemViewDelegate<T>() {
			@Override
			public int getItemViewLayoutId() {
				return layoutId;
			}

			@Override
			public boolean isForViewType(T item, int position) {
				return true;
			}

			@Override
			public void convert(ViewHolder holder, T t, int position) {
				SingleItemTypeAdapter.this.convert(holder,t,position);
			}
		});
	}

	protected abstract void convert(ViewHolder holder, T t, int position);
}
