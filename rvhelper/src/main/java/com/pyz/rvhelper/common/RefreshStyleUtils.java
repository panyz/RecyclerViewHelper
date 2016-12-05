package com.pyz.rvhelper.common;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;

/**
 * @Author: panyz
 * @Package: com.pyz.rvhelper.common
 * @Description: TODO
 * @Project: RecyclerViewHelper
 * @Company: 深圳君南信息系统有限公司
 * @Date： 2016/12/5 17:38
 */

public class RefreshStyleUtils {

	public static void setRefreshStyle(SwipeRefreshLayout refreshLayout){
		refreshLayout.setColorSchemeColors(Color.RED,Color.BLUE,Color.GREEN);
		refreshLayout.setDistanceToTriggerSync(400);
		refreshLayout.setSize(SwipeRefreshLayout.DEFAULT);
	}

}
