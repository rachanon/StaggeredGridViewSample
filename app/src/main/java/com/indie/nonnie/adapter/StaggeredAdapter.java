package com.indie.nonnie.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.indie.nonnie.imageloader.ImageLoader;
import com.indie.nonnie.staggeredgridviewsample.R;
import com.indie.nonnie.view.ScaleImageView;


public class StaggeredAdapter extends ArrayAdapter<String> {

	private ImageLoader mLoader;

	public StaggeredAdapter(Context context, int textViewResourceId,
			String[] objects) {
		super(context, textViewResourceId, objects);
		mLoader = new ImageLoader(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder;

		if (convertView == null) {
			LayoutInflater layoutInflator = LayoutInflater.from(getContext());
			convertView = layoutInflator.inflate(R.layout.row_staggered_demo,
					null);
			holder = new ViewHolder();
			holder.imageView = (ScaleImageView) convertView .findViewById(R.id.imageView1);
			holder.coverText = (TextView) convertView .findViewById(R.id.coverText);
			convertView.setTag(holder);
		}

		holder = (ViewHolder) convertView.getTag();

		mLoader.DisplayImage(getItem(position), holder.imageView);
		holder.coverText.setText("Position:"+position);

		return convertView;
	}

	static class ViewHolder {
		ScaleImageView imageView;
		TextView coverText;
	}
}
