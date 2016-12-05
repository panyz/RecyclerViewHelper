package com.pyz.rvhelper.common;

import android.support.v4.util.SparseArrayCompat;

/**
 * @Author: panyz
 * @Package: com.pyz.rvhelper.common
 * @Description: TODO
 * @Project: RecyclerViewHelper
 * @Company: 深圳君南信息系统有限公司
 * @Date： 2016/10/24 16:42
 */

public class ItemViewDelegateManager<T> {

	SparseArrayCompat<ItemViewDelegate<T>> delegates = new SparseArrayCompat();

	public int getItemViewDelegateCount() {
		return delegates.size();
	}

	public ItemViewDelegateManager<T> addDeleagte(ItemViewDelegate<T> delegate) {
		int viewType = delegates.size();
		if (delegate != null) {
			delegates.put(viewType,delegate);
			viewType++;
		}
		return this;
	}

	public ItemViewDelegateManager<T> addDeleagte(int viewType, ItemViewDelegate<T> delegate) {
		if (delegates.get(viewType) != null) {
			throw new IllegalArgumentException(
					"An ItemViewDelegate is already registered for the viewType = "
					+ viewType
					+ ". Already registered ItemViewDelegate is "
					+ delegates.get(viewType));
		}
		delegates.put(viewType,delegate);
		return this;
	}

	public ItemViewDelegateManager<T> removeDelegate(ItemViewDelegate<T> delegate) {
		if (delegate == null) {
			throw new NullPointerException("ItemViewDelegate is null");
		}

		int indexOfRemove = delegates.indexOfValue(delegate);

		if (indexOfRemove >= 0) {
			delegates.removeAt(indexOfRemove);
		}
		return this;
	}

	public ItemViewDelegateManager<T> removeDelegate(int viewType) {
		int indexOfRemove = delegates.indexOfKey(viewType);
		if (indexOfRemove >= 0) {
			delegates.removeAt(indexOfRemove);
		}
		return this;
	}

	public int getItemViewType(T item, int position) {
		int delegatesCount = delegates.size();
		for (int i=delegatesCount-1; i>=0; i--) {
			ItemViewDelegate<T> delegate = delegates.valueAt(i);
			if (delegate.isForViewType(item, position)) {
				return delegates.keyAt(i);
			}
		}
		throw new IllegalArgumentException(
				"No ItemViewDelegate added that matches position = " + position + "in data source");
	}

	public void convert (ViewHolder holder, T item, int position) {
		int delegatesCount = delegates.size();
		for (int i=0; i<delegatesCount; i++) {
			ItemViewDelegate<T> delegate = delegates.valueAt(i);
			if (delegate.isForViewType(item,position)) {
				delegate.convert(holder,item,position);
				return;
			}
		}
		throw new IllegalArgumentException(
				"No ItemViewDelegate added that matches position = " + position + "in data source");
	}

	public ItemViewDelegate getItemViewDelegate(int viewType) {
		return delegates.get(viewType);
	}

	public int getItemViewLayoutId(int viewType) {
		return getItemViewDelegate(viewType).getItemViewLayoutId();
	}

	public int getItemViewType(ItemViewDelegate itemViewDelegate){
		return delegates.indexOfValue(itemViewDelegate);
	}

}
