package com.appcloud.frankiappforyoigo.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.appcloud.frankiappforyoigo.Activities.ReservaActivity;
import com.appcloud.frankiappforyoigo.Config;
import com.appcloud.frankiappforyoigo.POJO.OfertaTactica;
import com.appcloud.frankiappforyoigo.R;
import com.appcloud.frankiappforyoigo.Utils.Commons;
import com.appcloud.frankiappforyoigo.Utils.FirebaseSingleton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;


public class TarifasMovilesFragment extends Fragment {

    private String keyTerminal;
    private OfertaTactica ofertaTactica;
    private RelativeLayout rlCero, rlCinco,rlInfinita,rlSinfin;
    private LinearLayout lnCero,lnPagoInicialCero,lnGratisCero, lnCinco,lnPagoInicialCinco,lnGratisCinco, lnInfinita,lnPagoInicialInfinita,lnGratisInfinita, lnSinfin,lnPagoInicialSinfin,
            lnGratisSinfin, lnPagoUnicoPrepago, lnPagoUnicoContrato, lnPagoUnicoCero,lnPagoUnicoCinco,lnPagoUnicoInfinita, lnPagoUnicoSinfin, lnPagoUnicoUno, lnPagoUnico650 ;
    private TextView tvPagoInicialCero, tvPagoInicialCero2,tvCuotaMensualCero, tvCuotaMensualCero2, tvPagoFinalCero, tvPagoInicialCinco, tvPagoInicialCinco2,
            tvCuotaMensualCinco, tvCuotaMensualCinco2, tvPagoFinalCinco, tvPagoInicialInfinita, tvPagoInicialInfinita2, tvCuotaMensualInfinita, tvCuotaMensualInfinita2,
            tvPagFinalInfinita, tvPagoInicialSinfin, tvPagoInicialSinfin2, tvCuotaMensualSinfin, tvCuotaMensualSinfin2, tvPagFinalSinfin,
            tvPrepagoUno, tvPrepago650, tvPagoUnicoCero, tvPagoUnicoCinco,tvPagoUnicoInfinita, tvPagoUnicoSinfin;

    public TarifasMovilesFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View parentView = inflater.inflate(R.layout.fragment_tarifas_moviles, container, false);
        lnCero = (LinearLayout) parentView.findViewById(R.id.ln_cero);
        rlCero = (RelativeLayout) parentView.findViewById(R.id.rl_cero);
        lnGratisCero = (LinearLayout) parentView.findViewById(R.id.ln_gratis_cero);
        lnPagoInicialCero = (LinearLayout) parentView.findViewById(R.id.ln_pago_inicial_cero);
        lnCinco = (LinearLayout) parentView.findViewById(R.id.ln_cinco);
        rlCinco = (RelativeLayout) parentView.findViewById(R.id.rl_cinco);
        lnPagoInicialCinco = (LinearLayout) parentView.findViewById(R.id.ln_pago_inicial_cinco);
        lnGratisCinco = (LinearLayout) parentView.findViewById(R.id.ln_gratis_cinco);
        lnSinfin = (LinearLayout) parentView.findViewById(R.id.ln_sinfin);
        rlSinfin = (RelativeLayout) parentView.findViewById(R.id.rl_sinfin);
        lnPagoInicialInfinita = (LinearLayout) parentView.findViewById(R.id.ln_pago_inicial_infinita);
        lnGratisInfinita = (LinearLayout) parentView.findViewById(R.id.ln_gratis_infinita);
        lnInfinita = (LinearLayout) parentView.findViewById(R.id.ln_infinita);
        rlInfinita = (RelativeLayout) parentView.findViewById(R.id.rl_infinita);
        lnPagoInicialSinfin = (LinearLayout) parentView.findViewById(R.id.ln_pago_inicial_sinfin);
        lnGratisSinfin = (LinearLayout) parentView.findViewById(R.id.ln_gratis_sinfin);
        lnPagoUnicoPrepago = (LinearLayout) parentView.findViewById(R.id.ln_pago_unico_prepago);
        lnPagoUnicoContrato = (LinearLayout) parentView.findViewById(R.id.ln_pago_unico_contrato);
        lnPagoUnicoCero = (LinearLayout) parentView.findViewById(R.id.ln_pago_unico_cero);
        lnPagoUnicoCinco = (LinearLayout) parentView.findViewById(R.id.ln_pago_unico_cinco);
        lnPagoUnicoInfinita = (LinearLayout) parentView.findViewById(R.id.ln_pago_unico_infinita);
        lnPagoUnicoSinfin = (LinearLayout) parentView.findViewById(R.id.ln_pago_unico_sinfin);
        lnPagoUnicoUno = (LinearLayout) parentView.findViewById(R.id.ln_pago_unico_uno);
        lnPagoUnico650 = (LinearLayout) parentView.findViewById(R.id.ln_pago_unico_650);
        tvPagoInicialCero = (TextView) parentView.findViewById(R.id.tv_pago_inicial_cero);
        tvPagoInicialCero2 = (TextView) parentView.findViewById(R.id.tv_pago_inicial_cero2);
        tvCuotaMensualCero = (TextView) parentView.findViewById(R.id.tv_cuota_mensual_cero);
        tvCuotaMensualCero2 = (TextView) parentView.findViewById(R.id.tv_cuota_mensual_cero2);
        tvPagoFinalCero = (TextView) parentView.findViewById(R.id.tv_pago_final_cero);
        tvPagoInicialCinco = (TextView) parentView.findViewById(R.id.tv_pago_inicial_cinco);
        tvPagoInicialCinco2 = (TextView) parentView.findViewById(R.id.tv_pago_inicial_cinco2);
        tvCuotaMensualCinco = (TextView) parentView.findViewById(R.id.tv_cuota_mensual_cinco);
        tvCuotaMensualCinco2 = (TextView) parentView.findViewById(R.id.tv_cuota_mensual_cinco2);
        tvPagoFinalCinco = (TextView) parentView.findViewById(R.id.tv_pago_final_cinco);
        tvPagoInicialInfinita = (TextView) parentView.findViewById(R.id.tv_pago_inicial_infinita);
        tvPagoInicialInfinita2 = (TextView) parentView.findViewById(R.id.tv_pago_inicial_infinita2);
        tvCuotaMensualInfinita = (TextView) parentView.findViewById(R.id.tv_cuota_mensual_infinita);
        tvCuotaMensualInfinita2 = (TextView) parentView.findViewById(R.id.tv_cuota_mensual_infinita2);
        tvPagFinalInfinita = (TextView) parentView.findViewById(R.id.tv_pago_final_infinita);
        tvPagoInicialSinfin = (TextView) parentView.findViewById(R.id.tv_pago_inicial_sinfin);
        tvPagoInicialSinfin2 = (TextView) parentView.findViewById(R.id.tv_pago_inicial_sinfin2);
        tvCuotaMensualSinfin = (TextView) parentView.findViewById(R.id.tv_cuota_mensual_sinfin);
        tvCuotaMensualSinfin2 = (TextView) parentView.findViewById(R.id.tv_cuota_mensual_sinfin2);
        tvPagFinalSinfin = (TextView) parentView.findViewById(R.id.tv_pago_final_sinfin);
        tvPrepagoUno = (TextView) parentView.findViewById(R.id.tv_pago_unico_uno);
        tvPrepago650 = (TextView) parentView.findViewById(R.id.tv_pago_unico_650);
        tvPagoUnicoCero = (TextView) parentView.findViewById(R.id.tv_pago_unico_cero);
        tvPagoUnicoCinco = (TextView) parentView.findViewById(R.id.tv_pago_unico_cinco);
        tvPagoUnicoInfinita = (TextView) parentView.findViewById(R.id.tv_pago_unico_infinita);
        tvPagoUnicoSinfin = (TextView) parentView.findViewById(R.id.tv_pago_unico_sinfin);
        return parentView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        keyTerminal = getArguments().getString("keyTerminal");
        if (keyTerminal != null) {
            DatabaseReference databaseReference = FirebaseSingleton.getDatabase().getReference();
            databaseReference.child("ofertas_tacticas").child(keyTerminal).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    ofertaTactica = dataSnapshot.getValue(OfertaTactica.class);

                    if (ofertaTactica.getCuotaCero() == null || ofertaTactica.getCuotaCero().equals(""))
                        lnCero.setVisibility(View.GONE);
                    else if(ofertaTactica.getCuotaCero().equals("0,00"))
                    {
                        lnCero.setVisibility(View.GONE);
                        rlCero.setVisibility(View.VISIBLE);
                        lnPagoInicialCero.setVisibility(View.GONE);
                        lnGratisCero.setVisibility(View.VISIBLE);
                    }
                    else if(ofertaTactica.getPagoFinalCero().equals("0,00"))
                    {
                        lnCero.setVisibility(View.GONE);
                        rlCero.setVisibility(View.VISIBLE);
                        tvPagoInicialCero2.setText(Commons.tratarPrecio(ofertaTactica.getPagoInicialCero()));
                        tvCuotaMensualCero2.setText(Commons.tratarPrecio(ofertaTactica.getCuotaCero()));
                    }
                    else
                    {
                        tvPagoInicialCero.setText(Commons.tratarPrecio(ofertaTactica.getPagoInicialCero()));
                        tvCuotaMensualCero.setText(Commons.tratarPrecio(ofertaTactica.getCuotaCero()));
                        tvPagoFinalCero.setText(Commons.tratarPrecio(ofertaTactica.getPagoFinalCero()));
                    }
                    if (ofertaTactica.getCuotaCinco() == null || ofertaTactica.getCuotaCinco().equals(""))
                        lnCinco.setVisibility(View.GONE);
                    else if(ofertaTactica.getCuotaCinco().equals("0,00"))
                    {
                        lnCinco.setVisibility(View.GONE);
                        rlCinco.setVisibility(View.VISIBLE);
                        lnPagoInicialCinco.setVisibility(View.GONE);
                        lnGratisCinco.setVisibility(View.VISIBLE);
                    }
                    else if(ofertaTactica.getPagoFinalCinco().equals("0,00"))
                    {
                        lnCinco.setVisibility(View.GONE);
                        rlCinco.setVisibility(View.VISIBLE);
                        tvPagoInicialCinco2.setText(Commons.tratarPrecio(ofertaTactica.getPagoInicialCinco()));
                        tvCuotaMensualCinco2.setText(Commons.tratarPrecio(ofertaTactica.getCuotaCinco()));
                    }
                    else {
                        tvPagoInicialCinco.setText(Commons.tratarPrecio(ofertaTactica.getPagoInicialCinco()));
                        tvCuotaMensualCinco.setText(Commons.tratarPrecio(ofertaTactica.getCuotaCinco()));
                        tvPagoFinalCinco.setText(Commons.tratarPrecio(ofertaTactica.getPagoFinalCinco()));
                    }
                    if (ofertaTactica.getCuotaInfinita() == null || ofertaTactica.getCuotaInfinita().equals(""))
                        lnInfinita.setVisibility(View.GONE);
                    else if(ofertaTactica.getCuotaInfinita().equals("0,00"))
                    {
                        lnInfinita.setVisibility(View.GONE);
                        rlInfinita.setVisibility(View.VISIBLE);
                        lnPagoInicialInfinita.setVisibility(View.GONE);
                        lnGratisInfinita.setVisibility(View.VISIBLE);
                    }
                    else if(ofertaTactica.getPagoFinalInfinita().equals("0,00"))
                    {
                        lnInfinita.setVisibility(View.GONE);
                        rlInfinita.setVisibility(View.VISIBLE);
                        tvPagoInicialInfinita2.setText(Commons.tratarPrecio(ofertaTactica.getPagoInicialInfinita()));
                        tvCuotaMensualInfinita2.setText(Commons.tratarPrecio(ofertaTactica.getCuotaInfinita()));
                    }
                    else {
                        tvPagoInicialInfinita.setText(Commons.tratarPrecio(ofertaTactica.getPagoInicialInfinita()));
                        tvCuotaMensualInfinita.setText(Commons.tratarPrecio(ofertaTactica.getCuotaInfinita()));
                        tvPagFinalInfinita.setText(Commons.tratarPrecio(ofertaTactica.getPagoFinalInfinita()));
                    }
                    if (ofertaTactica.getCuotaSinFin() == null || ofertaTactica.getCuotaSinFin().equals(""))
                        lnSinfin.setVisibility(View.GONE);
                    else if(ofertaTactica.getCuotaSinFin().equals("0,00"))
                    {
                        lnSinfin.setVisibility(View.GONE);
                        rlSinfin.setVisibility(View.VISIBLE);
                        lnPagoInicialSinfin.setVisibility(View.GONE);
                        lnGratisSinfin.setVisibility(View.VISIBLE);
                    }
                    else if(ofertaTactica.getPagoFinalInfinita().equals("0,00"))
                    {
                        lnSinfin.setVisibility(View.GONE);
                        rlSinfin.setVisibility(View.VISIBLE);
                        tvPagoInicialSinfin2.setText(Commons.tratarPrecio(ofertaTactica.getPagoInicialSinFin()));
                        tvCuotaMensualSinfin2.setText(Commons.tratarPrecio(ofertaTactica.getCuotaSinFin()));
                    }
                    else {
                        tvPagoInicialSinfin.setText(Commons.tratarPrecio(ofertaTactica.getPagoInicialSinFin()));
                        tvCuotaMensualSinfin.setText(Commons.tratarPrecio(ofertaTactica.getCuotaSinFin()));
                        tvPagFinalSinfin.setText(Commons.tratarPrecio(ofertaTactica.getPagoFinalSinFin()));
                    }
                    if ((ofertaTactica.getPagoUnicoCero() == null || ofertaTactica.getPagoUnicoCero().equals(""))
                            && (ofertaTactica.getPagoUnicoCinco() == null || ofertaTactica.getPagoUnicoCinco().equals(""))
                            && (ofertaTactica.getPagoUnicoInfinita() == null || ofertaTactica.getPagoUnicoInfinita().equals(""))
                            && (ofertaTactica.getPagoUnicoSinFin() == null || ofertaTactica.getPagoUnicoSinFin().equals(""))) {
                        lnPagoUnicoContrato.setVisibility(View.GONE);
                    }
                    if (ofertaTactica.getPvprPrepago() == null || ofertaTactica.getPvprPrepago().equals(""))
                    {
                        lnPagoUnicoPrepago.setVisibility(View.GONE);
                    }
                    else{
                        tvPrepagoUno.setText(Commons.tratarPrecio(ofertaTactica.getPvprPrepago())+"€");
                        tvPrepago650.setText(Commons.tratarPrecio(ofertaTactica.getPvprPrepago())+"€");
                    }
                    if (ofertaTactica.getPagoUnicoCero() == null || ofertaTactica.getPagoUnicoCero().equals("")) {
                        lnPagoUnicoCero.setVisibility(View.GONE);
                    }else{
                        tvPagoUnicoCero.setText(Commons.tratarPrecio(ofertaTactica.getPagoUnicoCero())+"€");
                    }
                    if (ofertaTactica.getPagoUnicoCinco() == null || ofertaTactica.getPagoUnicoCinco().equals("")) {
                        lnPagoUnicoCinco.setVisibility(View.GONE);
                    }else{
                        tvPagoUnicoCinco.setText(Commons.tratarPrecio(ofertaTactica.getPagoUnicoCinco())+"€");
                    }
                    if (ofertaTactica.getPagoUnicoInfinita() == null || ofertaTactica.getPagoUnicoInfinita().equals("")) {
                        lnPagoUnicoInfinita.setVisibility(View.GONE);
                    }else{
                        tvPagoUnicoInfinita.setText(Commons.tratarPrecio(ofertaTactica.getPagoUnicoInfinita())+"€");
                    }
                    if (ofertaTactica.getPagoUnicoSinFin() == null || ofertaTactica.getPagoUnicoSinFin().equals("")) {
                        lnPagoUnicoSinfin.setVisibility(View.GONE);
                    }else{
                        tvPagoUnicoSinfin.setText(Commons.tratarPrecio(ofertaTactica.getPagoUnicoSinFin())+"€");
                    }
                    lnCero.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            LinearLayout ln_color = (LinearLayout) v.findViewById(R.id.ln_color_cero);
                            TextView tvTarifa = (TextView) v.findViewById(R.id.tv_tarifa_cero);
                            clickTarifa(ln_color, tvTarifa, Config.TARIFA_CERO, false);
                        }
                    });
                    rlCero.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            TextView tvTarifa = (TextView) v.findViewById(R.id.tv_tarifa_cero2);
                            clickTarifa(rlCero, tvTarifa, Config.TARIFA_CERO, false);
                        }
                    });
                    lnCinco.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            LinearLayout ln_color = (LinearLayout) v.findViewById(R.id.ln_color_cinco);
                            TextView tvTarifa = (TextView) v.findViewById(R.id.tv_tarifa_cinco2);
                            clickTarifa(ln_color, tvTarifa, Config.TARIFA_CINCO, false);
                        }
                    });
                    rlCinco.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            TextView tvTarifa = (TextView) v.findViewById(R.id.tv_tarifa_cinco);
                            clickTarifa(rlCinco, tvTarifa, Config.TARIFA_CINCO, false);
                        }
                    });
                    lnInfinita.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            LinearLayout ln_color = (LinearLayout) v.findViewById(R.id.ln_color_infinita);
                            TextView tvTarifa = (TextView) v.findViewById(R.id.tv_tarifa_infinita);
                            clickTarifa(ln_color, tvTarifa, Config.TARIFA_INFINITA, false);
                        }
                    });
                    rlInfinita.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            TextView tvTarifa = (TextView) v.findViewById(R.id.tv_tarifa_infinita);
                            clickTarifa(rlInfinita, tvTarifa, Config.TARIFA_INFINITA, false);
                        }
                    });
                    lnSinfin.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            LinearLayout ln_color = (LinearLayout) v.findViewById(R.id.ln_color_sinfin);
                            TextView tvTarifa = (TextView) v.findViewById(R.id.tv_tarifa_sinfin);
                            clickTarifa(ln_color, tvTarifa, Config.TARIFA_SINFIN, false);
                        }
                    });
                    rlSinfin.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            TextView tvTarifa = (TextView) v.findViewById(R.id.tv_tarifa_sinfin2);
                            clickTarifa(rlSinfin, tvTarifa, Config.TARIFA_SINFIN, false);
                        }
                    });
                    lnPagoUnicoUno.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(ofertaTactica.getPvprPrepago()!=null && !ofertaTactica.getPvprPrepago().equals("")){
                                TextView tvTarifa = (TextView) v.findViewById(R.id.tv_tarifa_prepago_uno);
                                clickTarifa((LinearLayout) v, tvTarifa, Config.TARIFA_UNO, false);
                            }
                        }
                    });
                    lnPagoUnico650.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(ofertaTactica.getPvprPrepago()!=null && !ofertaTactica.getPvprPrepago().equals("")) {
                                TextView tvTarifa = (TextView) v.findViewById(R.id.tv_tarifa_prepago_650);
                                clickTarifa((LinearLayout) v, tvTarifa, Config.TARIFA_650, false);
                            }
                        }
                    });
                    lnPagoUnicoCero.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            TextView tvTarifa = (TextView) v.findViewById(R.id.tv_tarifa_contrato_cero);
                            clickTarifa((LinearLayout) v, tvTarifa, Config.TARIFA_CERO, true);
                        }
                    });
                    lnPagoUnicoCinco.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            TextView tvTarifa = (TextView) v.findViewById(R.id.tv_tarifa_contrato_cinco);
                            clickTarifa((LinearLayout) v, tvTarifa, Config.TARIFA_CINCO, true);
                        }
                    });
                    lnPagoUnicoInfinita.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            TextView tvTarifa = (TextView) v.findViewById(R.id.tv_tarifa_contrato_infinita);
                            clickTarifa((LinearLayout) v, tvTarifa, Config.TARIFA_INFINITA, true);
                        }
                    });
                    lnPagoUnicoSinfin.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            TextView tvTarifa = (TextView) v.findViewById(R.id.tv_tarifa_contrato_sinfin);
                            clickTarifa((LinearLayout) v, tvTarifa, Config.TARIFA_SINFIN, true);
                        }
                    });

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

    private void clickTarifa(View container, TextView textView, String tarifa, boolean pagoUnico) {
        Intent intent = new Intent(getActivity(), ReservaActivity.class);
        intent.putExtra(Config.KEY_TERMINAL, keyTerminal);
        intent.putExtra(Config.KEY_TARIFA, tarifa);
        intent.putExtra(Config.PAGO_UNICO, pagoUnico);
        intent.setAction(Config.RESERVAR);

        Pair<View, String> p1 = Pair.create((View) container, "color_bar");
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation( getActivity(), p1);
        startActivity(intent, options.toBundle());

    }

}
