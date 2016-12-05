package com.pyz.rvhelper.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.pyz.rvhelper.common.ItemViewDelegate;
import com.pyz.rvhelper.common.ItemViewDelegateManager;
import com.pyz.rvhelper.common.ViewHolder;

import java.util.List;

/**
 * @Author: pyz
 * @Package: com.pyz.rvhelper.adapter
 * @Description: 多Item布局的基础适配器
 * @Project: RecyclerViewHelper
 * @Company: 深圳君南信息系统有限公司
 * @Date: 2016/10/21 09:53
 */

public class MultiItemTypeAdapter<T> extends RecyclerView.Adapter<ViewHolder> {
	protected Context mContext;
	protected List<T> mDatas;
	protected ItemViewDelegateManager mItemViewDelegateManager;
	protected OnItemClickListener mOnItemClickListener;

	public MultiItemTypeAdapter(Context mContext, List<T> mDatas) {
		this.mContext = mContext;
		this.mDatas = mDatas;
		this.mItemViewDelegateManager = new ItemViewDelegateManager();
	}

	@Override
	public int getItemViewType(int position) {
		if (!useItemViewDelegateManager()) {
			return super.getItemViewType(position);
		}
		return mItemViewDelegateManager.getItemViewType(mDatas.get(position), position);
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		ItemViewDelegate itemViewDelegate = mItemViewDelegateManager.getItemViewDelegate(viewType);
		int layoutId = itemViewDelegate.getItemViewLayoutId();
		ViewHolder holder = ViewHolder.createViewHolder(mContext, parent, layoutId);
		onViewHolderCreated(holder, holder.getItemView());
		setListener(parent,holder,viewType);
		return holder;
	}



	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		convert(holder, mDatas.get(position));
	}

	@Override
	public int getItemCount() {
		return mDatas.size();
	}

	protected boolean useItemViewDelegateManager() {
		return mItemViewDelegateManager.getItemViewDelegateCount() > 0;
	}

	public void onViewHolderCreated(ViewHolder holder, View itemView) {

	}

	public void convert(ViewHolder holder, T t) {
		mItemViewDelegateManager.convert(holder, t, holder.getAdapterPosition());
	}

	protected void setListener(ViewGroup parent, final ViewHolder holder, int viewType) {
		if (!isEnabled(viewType)) {
			return;
		}

		holder.getItemView().setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mOnItemClickListener != null) {
					int position = holder.getAdapterPosition();
					mOnItemClickListener.onItemClick(v,holder,position);
				}
			}
		});

		holder.getItemView().setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				if (mOnItemClickListener != null) {
					int position = holder.getAdapterPosition();
					return mOnItemClickListener.onItemLongClick(v,holder,position);
				}
				return false;
			}
		});
	}

	protected boolean isEnabled(int viewType) {
		return true;
	}

	public interface OnItemClickListener {
		void onItemClick(View view, RecyclerView.ViewHolder holder, int position);
		boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position);
	}

	public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
		this.mOnItemClickListener = onItemClickListener;
	}

	public MultiItemTypeAdapter addItemViewDelegate(ItemViewDelegate<T> itemViewDelegate) {
		mItemViewDelegateManager.addDeleagte(itemViewDelegate);
		return this;
	}

	public MultiItemTypeAdapter addItemViewDelegate(int viewType, ItemViewDelegate<T> itemViewDelegate) {
		mItemViewDelegateManager.addDeleagte(viewType,itemViewDelegate);
		return this;
	}
}
