package com.appcloud.frankiappforyoigo.ViewHolders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.appcloud.frankiappforyoigo.POJO.OfertaTactica;
import com.appcloud.frankiappforyoigo.R;
import com.squareup.picasso.Picasso;

/**
 * Created by cristian on 13/06/2016.
 */
public class TerminalesViewHolder extends RecyclerView.ViewHolder {

    public final View mView;
    public final ImageView ivFoto;
    public final TextView tvNombre;

    public TerminalesViewHolder(View view) {
        super(view);
        mView = view;
        ivFoto = (ImageView) view.findViewById(R.id.iv_terminal_foto);
        tvNombre = (TextView) view.findViewById(R.id.tv_terminal_nombre);

    }

    public void bindToOferta(Context context, OfertaTactica oferta, View.OnClickListener listener) {

        mView.setOnClickListener(listener);
        tvNombre.setText(oferta.getTerminal());
        if(oferta.getFoto()!=null && !oferta.getFoto().equals(""))
            Picasso
                    .with(context)
                    .load(oferta.getFoto())
                    .placeholder(R.drawable.phone_mockup)
                    .error(R.drawable.phone_mockup)
                    .into(ivFoto);
        else
            ivFoto.setImageDrawable(context.getResources().getDrawable(R.drawable.phone_mockup));

    }

}
