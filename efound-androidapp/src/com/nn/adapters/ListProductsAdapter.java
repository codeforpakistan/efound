package com.nn.adapters;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nn.activities.R;
import com.nn.pojo.ProductListItem;
import com.nn.pojo.TagInfo;

public class ListProductsAdapter extends BaseAdapter {
	private Activity mActivity;
	private List<TagInfo> mProductList;

	public ListProductsAdapter(Activity activity, List<TagInfo> mProductList2) {
		this.mActivity = activity;
		this.mProductList = mProductList2;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mProductList.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return mProductList.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@SuppressLint("ResourceAsColor")
	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		TagInfo productListItem = mProductList.get(arg0);
		View rowView = arg1;
		// reuse views
		if (rowView == null) {
			LayoutInflater inflater = mActivity.getLayoutInflater();
			rowView = inflater.inflate(R.layout.product_list_row, null);
			// configure view holder
			ViewHolder viewHolder = new ViewHolder();
			viewHolder.textName = (TextView) rowView
					.findViewById(R.id.textName);
			viewHolder.textTagId = (TextView) rowView
					.findViewById(R.id.textTagID);
			// viewHolder.imageStatus = (ImageView) rowView
			// .findViewById(R.id.imgStatus);
			viewHolder.textStatus = (TextView) rowView
					.findViewById(R.id.textStatus);
			rowView.setTag(viewHolder);
		}

		// fill data
		ViewHolder holder = (ViewHolder) rowView.getTag();
		holder.textName.setText(productListItem.getEfprName());
		holder.textTagId.setText(productListItem.getEfprNfcid());
		if (productListItem.getEfprStatus().equals("stolen")) {
			holder.textStatus.setText("STOLEN");
			// holder.textStatus.setTextColor(R.color.red);
		} else {
			holder.textStatus.setText("");
		}
		//
		// if (productListItem.getEfprStatus().equals("stolen")) {
		// holder.imageStatus.setImageResource(R.drawable.red);
		// } else {
		// holder.imageStatus.setImageResource(R.drawable.green);
		// }

		return rowView;
	}

	static class ViewHolder {
		public TextView textName;
		public TextView textTagId;
		// public ImageView imageStatus;
		public TextView textStatus;
	}

}
