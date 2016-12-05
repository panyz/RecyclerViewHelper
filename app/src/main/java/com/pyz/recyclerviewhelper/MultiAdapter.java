package com.pyz.recyclerviewhelper;

import android.content.Context;

import com.pyz.recyclerviewhelper.delegate.FirstItemDelegate;
import com.pyz.recyclerviewhelper.delegate.SecondItemDelegate;
import com.pyz.rvhelper.adapter.MultiItemTypeAdapter;

import java.util.List;

/**
 * @Author: panyz
 * @Package: com.pyz.recyclerviewhelper
 * @Description: TODO
 * @Project: RecyclerViewHelper
 * @Company: 深圳君南信息系统有限公司
 * @Date： 2016/12/5 16:29
 */

public class MultiAdapter extends MultiItemTypeAdapter<TitleBean> {

	public MultiAdapter(Context mContext, List<TitleBean> mDatas) {
		super(mContext, mDatas);
		addItemViewDelegate(new FirstItemDelegate());
		addItemViewDelegate(new SecondItemDelegate());
	}
}
