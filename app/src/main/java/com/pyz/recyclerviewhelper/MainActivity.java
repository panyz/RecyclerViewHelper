package com.pyz.recyclerviewhelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
	@BindView(R.id.id_single)
	Button btnSingle;
	@BindView(R.id.id_multi)
	Button btnMulti;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
	}

	@OnClick({R.id.id_single, R.id.id_multi})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.id_single:
				Intent intent = new Intent(this,SingleItemActivity.class);
				startActivity(intent);
				break;
			case R.id.id_multi:
				Intent i = new Intent(this,MultiItemActivity.class);
				startActivity(i);
				break;

			default:
				break;
		}
	}
}
