package com.pyz.recyclerviewhelper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.pyz.rvhelper.adapter.SingleItemTypeAdapter;
import com.pyz.rvhelper.common.ViewHolder;
import com.pyz.rvhelper.wrapper.HeaderAndFooterWrapper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SingleItemActivity extends AppCompatActivity {

	@BindView(R.id.id_single_recyclerview)
	RecyclerView rvSingle;

	private List<String> data = new ArrayList<>();
	private SingleItemTypeAdapter<String> mAdapter;
	private HeaderAndFooterWrapper mHeaderAndFooter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_item);
		ButterKnife.bind(this);
		loadData();
		rvSingle.setLayoutManager(new LinearLayoutManager(this));

		mAdapter = new SingleItemTypeAdapter<String>(this,data,R.layout.item_single) {
			@Override
			protected void convert(ViewHolder holder, String s, int position) {
				TextView tv = holder.getView(R.id.item_single_text);
				tv.setText(s);
			}

		};
		intiHeaderAndFooter();
		rvSingle.setAdapter(mHeaderAndFooter);
	}

	private void intiHeaderAndFooter() {
		mHeaderAndFooter = new HeaderAndFooterWrapper(mAdapter);
		mHeaderAndFooter.addHeaderView(LayoutInflater.from(this).inflate(R.layout.header,rvSingle,false));
		mHeaderAndFooter.addFootView(LayoutInflater.from(this).inflate(R.layout.header,rvSingle,false));

	}

	private void loadData() {
		for (int i = 0; i < 20; i++) {
			data.add("Single item" + i);
		}
	}
}
