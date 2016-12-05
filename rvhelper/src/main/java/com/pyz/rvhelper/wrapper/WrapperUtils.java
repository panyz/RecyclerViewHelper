package com.pyz.rvhelper.wrapper;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.ViewGroup;

/**
 * @Author: panyz
 * @Package: com.pyz.rvhelper.wrapper
 * @Description: TODO
 * @Project: RecyclerViewHelper
 * @Company: 深圳君南信息系统有限公司
 * @Date： 2016/11/4 15:07
 */

public class WrapperUtils {

	public interface SpanSizeCallback {
		int getSpanSize(GridLayoutManager layoutManager, GridLayoutManager.SpanSizeLookup oldLookup, int position);
	}

	public static void onAttachedToRecyclerView(RecyclerView.Adapter innerAdapter, RecyclerView recyclerView,
												final SpanSizeCallback callback) {
		innerAdapter.onAttachedToRecyclerView(recyclerView);

		RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
		if (layoutManager instanceof GridLayoutManager) {
			final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
			final GridLayoutManager.SpanSizeLookup spanSizeLookup = gridLayoutManager.getSpanSizeLookup();

			gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
				//这个方法的返回值决定了我们每个position上的item占据的单元格个数
				@Override
				public int getSpanSize(int position) {
					return callback.getSpanSize(gridLayoutManager,spanSizeLookup,position);
				}
			});
			gridLayoutManager.setSpanCount(gridLayoutManager.getSpanCount());
		}
	}

	public static void setFullSpan(RecyclerView.ViewHolder holder) {
		ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();

		if (lp != null && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
			StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
			p.setFullSpan(true);
		}
	}

}
