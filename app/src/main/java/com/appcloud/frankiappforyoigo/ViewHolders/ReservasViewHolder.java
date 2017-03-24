package com.appcloud.frankiappforyoigo.ViewHolders;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.appcloud.frankiappforyoigo.Activities.ReservaActivity;
import com.appcloud.frankiappforyoigo.Config;
import com.appcloud.frankiappforyoigo.POJO.Reserva;
import com.appcloud.frankiappforyoigo.R;
import com.appcloud.frankiappforyoigo.Utils.PicassoRoundedBordersTransformation;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by cristian on 13/06/2016.
 */
public class ReservasViewHolder extends RecyclerView.ViewHolder {

    private final View mView;
    private final ImageView ivFoto;
    private TextView tvNombre, tvEmail, tvTelefono, tvFecha;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm");

    public ReservasViewHolder(View view) {
        super(view);
        mView = view;
        ivFoto = (ImageView) view.findViewById(R.id.iv_reserva_foto);
        tvNombre = (TextView)view.findViewById(R.id.tv_reserva_nombre);
        tvEmail = (TextView)view.findViewById(R.id.tv_reserva_email);
        tvFecha = (TextView)view.findViewById(R.id.tv_reserva_fecha);
        tvTelefono = (TextView)view.findViewById(R.id.tv_reserva_telefono);

    }

    public void populate(final Context context,ReservasViewHolder viewHolder, final Reserva reserva) {

        switch (reserva.getKeyTarifa()){
            case Config.TARIFA_CERO:
                mView.setBackground(context.getResources().getDrawable(R.drawable.background_cero));
                break;
            case Config.TARIFA_CINCO:
                mView.setBackground(context.getResources().getDrawable(R.drawable.background_cinco));
                break;
            case Config.TARIFA_SINFIN:
                mView.setBackground(context.getResources().getDrawable(R.drawable.background_sinfin));
                break;
            case Config.TARIFA_UNO:
                mView.setBackground(context.getResources().getDrawable(R.drawable.background_cero));
                break;
            case Config.TARIFA_650:
                mView.setBackground(context.getResources().getDrawable(R.drawable.background_cinco));
                break;
        }
        viewHolder.tvNombre.setText(reserva.getNombre());
        viewHolder.tvEmail.setText(reserva.getEmail());
        Date fechaReserva = new Date(reserva.getFechaReserva());
        viewHolder.tvFecha.setText(simpleDateFormat.format(fechaReserva));
        viewHolder.tvTelefono.setText(reserva.getTelefono());

        if(reserva.getUrlFoto()!=null && !reserva.getUrlFoto().equals(""))
            Picasso.with(context)
                    .load(reserva.getUrlFoto())
                    .transform(new PicassoRoundedBordersTransformation(5,0))
                    .networkPolicy(NetworkPolicy.OFFLINE)
                    .placeholder(R.drawable.ic_account_box_tablet)
                    .into(ivFoto,new Callback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError() {
                            //Try again online if cache failed
                            Picasso.with(context)
                                    .load(reserva.getUrlFoto())
                                    .placeholder(R.drawable.ic_account_box_tablet)
                                    .error(R.drawable.ic_account_box_tablet)
                                    .into(ivFoto);
                        }
                    });
               else
            ivFoto.setImageDrawable(context.getResources().getDrawable(R.drawable.phone_mockup));

        viewHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ReservaActivity.class);
                intent.putExtra(Config.KEY_TERMINAL, reserva.getKeyTerminal());
                intent.putExtra(Config.KEY_TARIFA, reserva.getKeyTarifa());
                intent.putExtra(Config.PAGO_UNICO, reserva.isPagoUnico());
                intent.setAction(Config.VER);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mView.setTransitionName("color_bar");
                }
                Pair<View, String> p1 = Pair.create((View) mView, "color_bar");

                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation((Activity) context, p1);
                context.startActivity(intent, options.toBundle());
            }
        });
    }

}
