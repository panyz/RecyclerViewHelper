package com.pyz.recyclerviewhelper;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.pyz.rvhelper.common.RefreshStyleUtils;
import com.pyz.rvhelper.wrapper.LoadMoreWrapper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MultiItemActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
	@BindView(R.id.id_multi_recyclerview)
	RecyclerView rvMulti;
	@BindView(R.id.refreshLayout)
	SwipeRefreshLayout refreshLayout;
	private boolean isRefresh = false;

	private List<TitleBean> data = new ArrayList<>();
	private LoadMoreWrapper mLoadMore;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_multi_item);
		ButterKnife.bind(this);
		RefreshStyleUtils.setRefreshStyle(refreshLayout);
		refreshLayout.setOnRefreshListener(this);
		loadData();
		rvMulti.setLayoutManager(new LinearLayoutManager(this));
		MultiAdapter adapter = new MultiAdapter(this,data);
		mLoadMore = new LoadMoreWrapper(adapter);
		mLoadMore.setLoadMoreView(LayoutInflater.from(this).inflate(R.layout.default_loading, rvMulti, false));
		mLoadMore.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
			@Override
			public void onLoadMoreRequest() {
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						data.add(new TitleBean("load more item", true));
						mLoadMore.notifyDataSetChanged();
					}
				},3000);
			}
		});
		rvMulti.setAdapter(mLoadMore);

	}

	private void loadData() {
		for (int i = 0; i < 20; i++) {
			if (i%3 == 0) {
				data.add(new TitleBean("item" + i, true));
			} else {
				data.add(new TitleBean("item" + i, false));
			}
		}
	}

	@Override
	public void onRefresh() {
		if (!isRefresh) {
			isRefresh = true;
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					refreshLayout.setRefreshing(false);
					data.add(0,new TitleBean("refresh more item",false));
					mLoadMore.notifyDataSetChanged();
					isRefresh = false;
				}
			}, 3000);
		}
	}

}
