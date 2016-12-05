package com.pyz.rvhelper.common;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @Author: pyz
 * @Package: com.pyz.rvhelper.common
 * @Description: TODO
 * @Project: RecyclerViewHelper
 * @Company: 深圳君南信息系统有限公司
 * @Date: 2016/10/21 09:33
 */

public class ViewHolder extends RecyclerView.ViewHolder {

	private SparseArray<View> viewArray;//保存view控件的集合
	private View itemView;
	private Context mContext;

	public ViewHolder(Context context,View itemView) {
		super(itemView);
		this.mContext = context;
		this.itemView = itemView;
		viewArray = new SparseArray<>();
	}

	/**
	 * 通过构造器创建ViewHolder对象
	 * @param context
	 * @param itemView
	 * @return
	 */
	public static ViewHolder createViewHolder (Context context, View itemView) {
		return new ViewHolder(context,itemView);
	}

	/**
	 * 根据不同的布局创建不同的ViewHolder对象
	 * @param context
	 * @param parent
	 * @param layoutId
	 * @return
	 */
	public static ViewHolder createViewHolder(Context context, ViewGroup parent, int layoutId) {
		View itemView = LayoutInflater.from(context).inflate(layoutId,parent,false);
		return new ViewHolder(context,itemView);
	}

	/**
	 * 通过viewId来获取控件
	 * @param viewId
	 * @param <T>
	 * @return
	 */
	public <T extends View> T getView(int viewId){
		View view = viewArray.get(viewId);
		if (view == null) {
			view = itemView.findViewById(viewId);
			viewArray.put(viewId,view);
		}
		return (T) view;
	}

	public View getItemView(){
		return itemView;
	}
}
