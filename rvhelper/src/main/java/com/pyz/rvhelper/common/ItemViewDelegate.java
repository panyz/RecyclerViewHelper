package com.pyz.rvhelper.common;

/**
 * @Author: pyz
 * @Package: com.pyz.rvhelper.common
 * @Description: TODO
 * @Project: RecyclerViewHelper
 * @Company: 深圳君南信息系统有限公司
 * @Date: 2016/10/21 09:57
 */

public interface ItemViewDelegate<T> {
	/**
	 * 向Adapter提供item布局文件；当前delegate的布局文件
	 */
	int getItemViewLayoutId();

	/**
	 * 判断提供的item是不是当前应该处理的类型；判断当前item的数据是不是属于当前的delegate
	 */
	boolean isForViewType(T item, int position);

	/**
	 * 完成一些初始化、绑定数据的操作
	 */
	void convert(ViewHolder holder, T t, int position);
}
