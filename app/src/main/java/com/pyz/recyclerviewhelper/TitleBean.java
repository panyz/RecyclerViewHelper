package com.pyz.recyclerviewhelper;

/**
 * @Author: panyz
 * @Package: com.pyz.recyclerviewhelper
 * @Description: TODO
 * @Project: RecyclerViewHelper
 * @Company: 深圳君南信息系统有限公司
 * @Date： 2016/12/5 16:27
 */

public class TitleBean {
	private String title;
	private boolean isFirst;

	public TitleBean(String title, boolean isFirst) {
		this.title = title;
		this.isFirst = isFirst;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isOne() {
		return isFirst;
	}

	public void setOne(boolean one) {
		isFirst = one;
	}
}
