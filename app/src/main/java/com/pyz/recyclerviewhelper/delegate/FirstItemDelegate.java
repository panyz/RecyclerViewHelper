package com.pyz.recyclerviewhelper.delegate;

import android.widget.TextView;

import com.pyz.recyclerviewhelper.R;
import com.pyz.recyclerviewhelper.TitleBean;
import com.pyz.rvhelper.common.ViewHolder;

/**
 * @Author: panyz
 * @Package: com.pyz.recyclerviewhelper
 * @Description: TODO
 * @Project: RecyclerViewHelper
 * @Company: 深圳君南信息系统有限公司
 * @Date： 2016/12/5 16:30
 */
public class FirstItemDelegate implements com.pyz.rvhelper.common.ItemViewDelegate<TitleBean> {
	@Override
	public int getItemViewLayoutId() {
		return R.layout.item_multi;
	}

	@Override
	public boolean isForViewType(TitleBean item, int position) {
		return item.isOne();
	}

	@Override
	public void convert(ViewHolder holder, TitleBean titleBean, int position) {
		TextView tv = holder.getView(R.id.item_multi_text);
		tv.setText(titleBean.getTitle());
	}
}
