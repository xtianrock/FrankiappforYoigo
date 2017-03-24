package com.appcloud.frankiappforyoigo.ViewHolders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.appcloud.frankiappforyoigo.POJO.OfertaTactica;
import com.appcloud.frankiappforyoigo.R;
import com.appcloud.frankiappforyoigo.Utils.Commons;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

/**
 * Created by cristian on 13/06/2016.
 */
public class TerminalesViewHolder extends RecyclerView.ViewHolder {

    private final View mView;
    private final ImageView ivFoto;
    private LinearLayout lnCero, lnCinco, lnInfinita, lnSinfin;
    private TextView tvNombre, tvPagoInicialCero, tvCuotaMensualCero, tvPagoInicialCinco,
            tvCuotaMensualCinco, tvPagoInicialInfinita, tvCuotaMensualInfinita, tvPagoInicialSinfin, tvCuotaMensualSinfin;

    public TerminalesViewHolder(View view) {
        super(view);
        mView = view;
        ivFoto = (ImageView) view.findViewById(R.id.iv_terminal_foto);
        tvNombre = (TextView) view.findViewById(R.id.tv_terminal_nombre);
        lnCero = (LinearLayout)view.findViewById(R.id.ln_cero);
        lnCinco = (LinearLayout)view.findViewById(R.id.ln_cinco);
        lnInfinita = (LinearLayout)view.findViewById(R.id.ln_infinita);
        lnSinfin = (LinearLayout)view.findViewById(R.id.ln_sinfin);
        tvPagoInicialCero = (TextView)view.findViewById(R.id.tv_pago_inicial_cero);
        tvCuotaMensualCero = (TextView)view.findViewById(R.id.tv_cuota_mensual_cero);
        tvPagoInicialCinco = (TextView)view.findViewById(R.id.tv_pago_inicial_cinco);
        tvCuotaMensualCinco = (TextView)view.findViewById(R.id.tv_cuota_mensual_cinco);
        tvPagoInicialInfinita = (TextView)view.findViewById(R.id.tv_pago_inicial_infinita);
        tvCuotaMensualInfinita = (TextView)view.findViewById(R.id.tv_cuota_mensual_infinita);
        tvPagoInicialSinfin = (TextView)view.findViewById(R.id.tv_pago_inicial_sinfin);
        tvCuotaMensualSinfin = (TextView)view.findViewById(R.id.tv_cuota_mensual_sinfin);

    }

    public void bindToOferta(final Context context, final OfertaTactica oferta, String terminalKey,View.OnClickListener listener) {

        mView.setOnClickListener(listener);
        tvNombre.setText(terminalKey);
        if(oferta.getFotoUrl()!=null && !oferta.getFotoUrl().equals(""))
            Picasso.with(context)
                    .load(oferta.getFotoUrl())
                    .networkPolicy(NetworkPolicy.OFFLINE)
                    .placeholder(R.drawable.phone_mockup)
                    .error(R.drawable.phone_mockup)
                    .into(ivFoto,new Callback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError() {
                            //Try again online if cache failed
                            Picasso.with(context)
                                    .load(oferta.getFotoUrl())
                                    .placeholder(R.drawable.phone_mockup)
                                    .error(R.drawable.phone_mockup)
                                    .into(ivFoto);
                        }
                    });
               else
            ivFoto.setImageDrawable(context.getResources().getDrawable(R.drawable.phone_mockup));

        if(oferta.getPagoInicialCero()==null || oferta.getPagoInicialCero().equals(""))
            lnCero.setVisibility(View.GONE);
        else {
            lnCero.setVisibility(View.VISIBLE);
            if(tvPagoInicialCero!=null && tvCuotaMensualCero!=null)
            {
                tvPagoInicialCero.setText(Commons.tratarPrecio(oferta.getPagoInicialCero())+"€");
                tvCuotaMensualCero.setText(formatoCuota(oferta.getCuotaCero()));
            }
        }
        if(oferta.getPagoInicialCinco()==null || oferta.getPagoInicialCinco().equals(""))
            lnCinco.setVisibility(View.GONE);
        else {
            lnCinco.setVisibility(View.VISIBLE);
            if(tvPagoInicialCinco!=null && tvCuotaMensualCinco!=null)
            {
                tvPagoInicialCinco.setText(Commons.tratarPrecio(oferta.getPagoInicialCinco())+"€");
                tvCuotaMensualCinco.setText(formatoCuota(oferta.getCuotaCinco()));
            }
        }

        /*if(oferta.getPagoInicialInfinita()==null || oferta.getPagoInicialInfinita().equals(""))
            lnInfinita.setVisibility(View.GONE);
        else {
            lnInfinita.setVisibility(View.VISIBLE);
            if(tvPagoInicialInfinita!=null && tvCuotaMensualInfinita!=null)
            {
                tvPagoInicialInfinita.setText(Commons.tratarPrecio(oferta.getPagoInicialSinFin())+"€");
                tvCuotaMensualInfinita.setText(formatoCuota(oferta.getCuotaSinFin()));
            }
        }*/

        if(oferta.getPagoInicialSinFin()==null || oferta.getPagoInicialSinFin().equals(""))
            lnSinfin.setVisibility(View.GONE);
        else {
            lnSinfin.setVisibility(View.VISIBLE);
            if(tvPagoInicialSinfin!=null && tvCuotaMensualSinfin!=null)
            {
                tvPagoInicialSinfin.setText(Commons.tratarPrecio(oferta.getPagoInicialSinFin())+"€");
                tvCuotaMensualSinfin.setText(formatoCuota(oferta.getCuotaSinFin()));
            }
        }

    }
    public String formatoCuota(String cuota)
    {
        return "+" + Commons.tratarFinanciacion(cuota) + "€/mes";
    }

}
