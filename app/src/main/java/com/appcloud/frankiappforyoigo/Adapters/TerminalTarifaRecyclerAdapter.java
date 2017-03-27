package com.appcloud.frankiappforyoigo.Adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
        mListener = listener;
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

        holder.tvPagoInicial.setText(Commons.tratarPrecio(holder.terminalTarifa.getPagoInicial()));
        holder.tvCuotaMensual.setText(Commons.tratarPrecio(holder.terminalTarifa.getCuota()));
        holder.tvPagoFinal.setText(Commons.tratarPrecio(holder.terminalTarifa.getPagoFinal()));
        int color = 0;
        switch (holder.terminalTarifa.getNombreTarifa())
        {
            case Config.TARIFA_CERO:
                holder.tvTarifa.setText(context.getResources().getString(R.string.tarifa_cero));
                color = ContextCompat.getColor(context, R.color.color_cero);
                holder.lnBorder.setBackground(context.getResources().getDrawable(R.drawable.border_cero));
                break;
            case Config.TARIFA_CINCO:
                holder.tvTarifa.setText(context.getResources().getString(R.string.tarifa_cinco));
                color = ContextCompat.getColor(context, R.color.color_cinco);
                holder.lnBorder.setBackground(context.getResources().getDrawable(R.drawable.border_cinco));
                break;
            case Config.TARIFA_INFINITA:
                holder.tvTarifa.setText(context.getResources().getString(R.string.tarifa_infinita));
                color = ContextCompat.getColor(context, R.color.color_infinita);
                holder.lnBorder.setBackground(context.getResources().getDrawable(R.drawable.border_infinita));
                break;
            case Config.TARIFA_SINFIN:
                holder.tvTarifa.setText(context.getResources().getString(R.string.tarifa_sinfin));
                color = ContextCompat.getColor(context, R.color.color_sinfin);
                holder.lnBorder.setBackground(context.getResources().getDrawable(R.drawable.border_sinfin));
                break;
        }
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




        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onItemClick(v, holder.terminalTarifa,holder.terminalTarifa.getNombreTarifa());
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
        private final TextView tvTarifa, tvPagoInicial, tvCuotaMensual, tvPagoFinal, tvPagoInicial2, tvCuotaMensual2,
                tvColor1, tvColor2, tvColor3, tvColor4, tvColor5, tvColor6, tvColor7;
        private final LinearLayout lnColor, lnBorder;

        private TerminalTarifa terminalTarifa;

        private ViewHolder(View view) {
            super(view);
            mView = view;
            cardView = (CardView) view.findViewById(R.id.card_view);
            tvTarifa = (TextView) view.findViewById(R.id.tv_tarifa);
            tvPagoInicial = (TextView) view.findViewById(R.id.tv_pago_inicial);
            tvCuotaMensual = (TextView) view.findViewById(R.id.tv_cuota_mensual);
            tvPagoFinal = (TextView) view.findViewById(R.id.tv_pago_final);
            tvPagoInicial2 = (TextView) view.findViewById(R.id.tv_pago_inicial2);
            tvCuotaMensual2 = (TextView) view.findViewById(R.id.tv_cuota_mensual2);
            lnColor = (LinearLayout) view.findViewById(R.id.ln_color);
            lnBorder = (LinearLayout) view.findViewById(R.id.ln_border);
            tvColor1 = (TextView) view.findViewById(R.id.tv_color1);
            tvColor2 = (TextView) view.findViewById(R.id.tv_color2);
            tvColor3 = (TextView) view.findViewById(R.id.tv_color3);
            tvColor4 = (TextView) view.findViewById(R.id.tv_color4);
            tvColor5 = (TextView) view.findViewById(R.id.tv_color5);
            tvColor6 = (TextView) view.findViewById(R.id.tv_color6);
            tvColor7 = (TextView) view.findViewById(R.id.tv_color7);
        }

    }

    public interface TerminlTarifaClickListener {
        void onItemClick(View vista, TerminalTarifa terminalTarifa, String keyTarifa);
    }
}