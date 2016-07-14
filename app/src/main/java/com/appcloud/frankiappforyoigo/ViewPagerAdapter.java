package com.appcloud.frankiappforyoigo;

import android.content.Context;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by cristian on 11/07/2016.
 */
public class ViewPagerAdapter extends PagerAdapter {
    // Declare Variables
    Context context;
    ArrayList<Uri> images;
    LayoutInflater inflater;

    public ViewPagerAdapter(Context context, ArrayList<Uri> images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        // Declare Variables
        final ImageView image;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.viewpager_item, container,
                false);

        // Locate the ImageView in viewpager_item.xml
        image = (ImageView) itemView.findViewById(R.id.flag);
        // Capture position and set to the ImageView
        //Log.i("url ",images.get(position).toString());
        if(position==0){
            image.setImageDrawable(context.getResources().getDrawable(R.mipmap.oferta));
        }else {
            Picasso.with(context).load(images.get(position))
                    .networkPolicy(NetworkPolicy.OFFLINE)
                    .into(image, new Callback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError() {
                            //Try again online if cache failed
                            Picasso.with(context)
                                    .load(images.get(position).toString())
                                    .error(R.mipmap.yoigo_logo)
                                    .into(image);
                        }
                    });
        }


        // Add viewpager_item.xml to ViewPager
        (container).addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // Remove viewpager_item.xml from ViewPager
        (container).removeView((LinearLayout) object);

    }
}
