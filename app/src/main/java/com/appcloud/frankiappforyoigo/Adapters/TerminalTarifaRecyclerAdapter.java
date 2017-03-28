package com.appcloud.frankiappforyoigo.Adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.appcloud.frankiappforyoigo.Config;
import com.appcloud.frankiappforyoigo.POJO.TerminalTarifa;
import com.appcloud.frankiappforyoigo.R;
import com.appcloud.frankiappforyoigo.Utils.Commons;

import java.util.List;

/**
 * Created by cristian on 27/03/2017.
 */


public class TerminalTarifaRecyclerAdapter extends RecyclerView.Adapter<TerminalTarifaRecyclerAdapter.ViewHolder> {

    private final List<TerminalTarifa> terminalTarifas;
    private final TerminlTarifaClickListener mListener;
    private Context context;

    public TerminalTarifaRecyclerAdapter(List<TerminalTarifa> terminalTarifas, TerminlTarifaClickListener listener, Context context) {
        this.terminalTarifas = terminalTarifas;
        this.context = context;
        this.mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_oferta_tactica, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.terminalTarifa = terminalTarifas.get(position);

        int color = 0;
        switch (holder.terminalTarifa.getNombreTarifa())
        {
            case Config.TARIFA_CERO:
                holder.tvTarifa.setText(context.getResources().getString(R.string.tarifa_cero));
                holder.tvTarifa2.setText(context.getResources().getString(R.string.tarifa_cero));
                color = ContextCompat.getColor(context, R.color.color_cero);
                holder.lnBorder.setBackground(context.getResources().getDrawable(R.drawable.border_cero));
                holder.rlSinPagoFinal.setBackground(context.getResources().getDrawable(R.drawable.border_cero));
                break;
            case Config.TARIFA_CINCO:
                holder.tvTarifa.setText(context.getResources().getString(R.string.tarifa_cinco));
                holder.tvTarifa2.setText(context.getResources().getString(R.string.tarifa_cinco));
                color = ContextCompat.getColor(context, R.color.color_cinco);
                holder.lnBorder.setBackground(context.getResources().getDrawable(R.drawable.border_cinco));
                holder.rlSinPagoFinal.setBackground(context.getResources().getDrawable(R.drawable.border_cinco));
                break;
            case Config.TARIFA_INFINITA:
                holder.tvTarifa.setText(context.getResources().getString(R.string.tarifa_infinita));
                holder.tvTarifa2.setText(context.getResources().getString(R.string.tarifa_infinita));
                color = ContextCompat.getColor(context, R.color.color_infinita);
                holder.lnBorder.setBackground(context.getResources().getDrawable(R.drawable.border_infinita));
                holder.rlSinPagoFinal.setBackground(context.getResources().getDrawable(R.drawable.border_infinita));
                break;
            case Config.TARIFA_SINFIN:
                holder.tvTarifa.setText(context.getResources().getString(R.string.tarifa_sinfin));
                holder.tvTarifa2.setText(context.getResources().getString(R.string.tarifa_sinfin));
                color = ContextCompat.getColor(context, R.color.color_sinfin);
                holder.lnBorder.setBackground(context.getResources().getDrawable(R.drawable.border_sinfin));
                holder.rlSinPagoFinal.setBackground(context.getResources().getDrawable(R.drawable.border_sinfin));
                break;
            case Config.COMBINADA_NARANJA_300:
                holder.tvTarifa.setText(context.getResources().getString(R.string.combinada_naranja300));
                holder.tvTarifa2.setText(context.getResources().getString(R.string.combinada_naranja300));
                color = ContextCompat.getColor(context, R.color.color_cero);
                holder.lnBorder.setBackground(context.getResources().getDrawable(R.drawable.border_cero));
                holder.rlSinPagoFinal.setBackground(context.getResources().getDrawable(R.drawable.border_cero));
                break;
            case Config.COMBINADA_NARANJA_50:
                holder.tvTarifa.setText(context.getResources().getString(R.string.combinada_naranja50));
                holder.tvTarifa2.setText(context.getResources().getString(R.string.combinada_naranja50));
                color = ContextCompat.getColor(context, R.color.color_cero);
                holder.lnBorder.setBackground(context.getResources().getDrawable(R.drawable.border_cero));
                holder.rlSinPagoFinal.setBackground(context.getResources().getDrawable(R.drawable.border_cero));
                break;
            case Config.COMBINADA_VERDE_300:
                holder.tvTarifa.setText(context.getResources().getString(R.string.combinada_verde300));
                holder.tvTarifa2.setText(context.getResources().getString(R.string.combinada_verde300));
                color = ContextCompat.getColor(context, R.color.color_cinco);
                holder.lnBorder.setBackground(context.getResources().getDrawable(R.drawable.border_cinco));
                holder.rlSinPagoFinal.setBackground(context.getResources().getDrawable(R.drawable.border_cinco));
                break;
            case Config.COMBINADA_VERDE_50:
                holder.tvTarifa.setText(context.getResources().getString(R.string.combinada_verde50));
                holder.tvTarifa2.setText(context.getResources().getString(R.string.combinada_verde50));
                color = ContextCompat.getColor(context, R.color.color_cinco);
                holder.lnBorder.setBackground(context.getResources().getDrawable(R.drawable.border_cinco));
                holder.rlSinPagoFinal.setBackground(context.getResources().getDrawable(R.drawable.border_cinco));
                break;
            case Config.COMBINADA_MORADA_300:
                holder.tvTarifa.setText(context.getResources().getString(R.string.combinada_morada300));
                holder.tvTarifa2.setText(context.getResources().getString(R.string.combinada_morada300));
                color = ContextCompat.getColor(context, R.color.color_infinita);
                holder.lnBorder.setBackground(context.getResources().getDrawable(R.drawable.border_infinita));
                holder.rlSinPagoFinal.setBackground(context.getResources().getDrawable(R.drawable.border_infinita));
                break;
            case Config.COMBINADA_MORADA_50:
                holder.tvTarifa.setText(context.getResources().getString(R.string.combinada_morada50));
                holder.tvTarifa2.setText(context.getResources().getString(R.string.combinada_morada50));
                color = ContextCompat.getColor(context, R.color.color_infinita);
                holder.lnBorder.setBackground(context.getResources().getDrawable(R.drawable.border_infinita));
                holder.rlSinPagoFinal.setBackground(context.getResources().getDrawable(R.drawable.border_infinita));
                break;
            case Config.COMBINADA_AZUL_300:
                holder.tvTarifa.setText(context.getResources().getString(R.string.combinada_azul300));
                holder.tvTarifa2.setText(context.getResources().getString(R.string.combinada_azul300));
                color = ContextCompat.getColor(context, R.color.color_sinfin);
                holder.lnBorder.setBackground(context.getResources().getDrawable(R.drawable.border_sinfin));
                holder.rlSinPagoFinal.setBackground(context.getResources().getDrawable(R.drawable.border_sinfin));
                break;
            case Config.COMBINADA_AZUL_50:
                holder.tvTarifa.setText(context.getResources().getString(R.string.combinada_azul50));
                holder.tvTarifa2.setText(context.getResources().getString(R.string.combinada_azul50));
                color = ContextCompat.getColor(context, R.color.color_sinfin);
                holder.lnBorder.setBackground(context.getResources().getDrawable(R.drawable.border_sinfin));
                holder.rlSinPagoFinal.setBackground(context.getResources().getDrawable(R.drawable.border_sinfin));
                break;
        }
        if(holder.terminalTarifa.getPagoFinal().equals("0,00") && holder.terminalTarifa.getPagoInicial().equals("0,00") && holder.terminalTarifa.getCuota().equals("0,00"))
        {
            holder.lnPagoFinal.setVisibility(View.GONE);
            holder.rlSinPagoFinal.setVisibility(View.VISIBLE);
            holder.lnPagoInicial.setVisibility(View.GONE);
            holder.lnGratis.setVisibility(View.VISIBLE);
            holder.tvTarifa2.setTextColor(color);
            holder.tvColor8.setTextColor(color);
            holder.tvColor13.setTextColor(color);
        }
        else if(holder.terminalTarifa.getPagoFinal().equals("0,00"))
        {
            holder.lnPagoFinal.setVisibility(View.GONE);
            holder.rlSinPagoFinal.setVisibility(View.VISIBLE);
            holder.tvPagoInicial2.setText(Commons.tratarPrecio(holder.terminalTarifa.getPagoInicial()));
            holder.tvCuotaMensual2.setText(Commons.tratarPrecio(holder.terminalTarifa.getCuota()));
            holder.tvTarifa2.setTextColor(color);
            holder.tvPagoInicial2.setTextColor(color);
            holder.tvCuotaMensual2.setTextColor(color);
            holder.tvColor9.setTextColor(color);
            holder.tvColor10.setTextColor(color);
            holder.tvColor11.setTextColor(color);
            holder.tvColor12.setTextColor(color);
        }
        else
        {
            holder.tvPagoInicial.setText(Commons.tratarPrecio(holder.terminalTarifa.getPagoInicial()));
            holder.tvCuotaMensual.setText(Commons.tratarPrecio(holder.terminalTarifa.getCuota()));
            holder.tvPagoFinal.setText(Commons.tratarPrecio(holder.terminalTarifa.getPagoFinal()));
            holder.lnColor.setBackgroundColor(color);
            holder.tvPagoFinal.setTextColor(color);
            holder.lnColor.setBackgroundColor(color);
            holder.tvColor1.setTextColor(color);
            holder.tvColor2.setTextColor(color);
            holder.tvColor3.setTextColor(color);
            holder.tvColor4.setTextColor(color);
            holder.tvColor5.setTextColor(color);
            holder.tvColor6.setTextColor(color);
            holder.tvColor7.setTextColor(color);
        }


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onItemClick(v, holder.terminalTarifa);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return terminalTarifas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final View mView;
        private final CardView cardView;
        private final TextView tvTarifa,tvTarifa2, tvPagoInicial, tvCuotaMensual, tvPagoFinal, tvPagoInicial2, tvCuotaMensual2,
                tvColor1, tvColor2, tvColor3, tvColor4, tvColor5, tvColor6, tvColor7,tvColor8,
                tvColor9, tvColor10, tvColor11, tvColor12, tvColor13;
        private final LinearLayout lnColor, lnBorder,lnPagoFinal, lnPagoInicial, lnGratis;
        private final RelativeLayout rlSinPagoFinal;

        private TerminalTarifa terminalTarifa;

        private ViewHolder(View view) {
            super(view);
            mView = view;
            cardView = (CardView) view.findViewById(R.id.card_view);
            tvTarifa = (TextView) view.findViewById(R.id.tv_tarifa);
            tvTarifa2 = (TextView) view.findViewById(R.id.tv_tarifa2);
            tvPagoInicial = (TextView) view.findViewById(R.id.tv_pago_inicial);
            tvCuotaMensual = (TextView) view.findViewById(R.id.tv_cuota_mensual);
            tvPagoFinal = (TextView) view.findViewById(R.id.tv_pago_final);
            tvPagoInicial2 = (TextView) view.findViewById(R.id.tv_pago_inicial2);
            tvCuotaMensual2 = (TextView) view.findViewById(R.id.tv_cuota_mensual2);
            lnColor = (LinearLayout) view.findViewById(R.id.ln_color);
            lnBorder = (LinearLayout) view.findViewById(R.id.ln_border);
            lnPagoFinal = (LinearLayout) view.findViewById(R.id.ln_pago_final);
            rlSinPagoFinal = (RelativeLayout) view.findViewById(R.id.rl_sin_pago_final);
            lnPagoInicial = (LinearLayout) view.findViewById(R.id.ln_pago_inicial);
            lnGratis = (LinearLayout) view.findViewById(R.id.ln_gratis);
            tvColor1 = (TextView) view.findViewById(R.id.tv_color1);
            tvColor2 = (TextView) view.findViewById(R.id.tv_color2);
            tvColor3 = (TextView) view.findViewById(R.id.tv_color3);
            tvColor4 = (TextView) view.findViewById(R.id.tv_color4);
            tvColor5 = (TextView) view.findViewById(R.id.tv_color5);
            tvColor6 = (TextView) view.findViewById(R.id.tv_color6);
            tvColor7 = (TextView) view.findViewById(R.id.tv_color7);
            tvColor9 = (TextView) view.findViewById(R.id.tv_color9);
            tvColor8 = (TextView) view.findViewById(R.id.tv_color8);
            tvColor10 = (TextView) view.findViewById(R.id.tv_color10);
            tvColor11 = (TextView) view.findViewById(R.id.tv_color11);
            tvColor12 = (TextView) view.findViewById(R.id.tv_color12);
            tvColor13 = (TextView) view.findViewById(R.id.tv_color13);
        }

    }

    public interface TerminlTarifaClickListener {
        void onItemClick(View vista, TerminalTarifa terminalTarifa);
    }
}