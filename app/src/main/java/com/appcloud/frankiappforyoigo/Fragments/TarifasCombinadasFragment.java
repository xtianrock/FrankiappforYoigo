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
import android.widget.RadioButton;
import android.widget.RadioGroup;
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


public class TarifasCombinadasFragment extends Fragment {

    private String keyTerminal;
    private OfertaTactica ofertaTactica;
    private RelativeLayout rlCero, rlCinco,rlInfinita,rlSinfin;
    private LinearLayout lnCero,lnPagoInicialCero,lnGratisCero, lnCinco,lnPagoInicialCinco,lnGratisCinco, lnInfinita,lnPagoInicialInfinita,lnGratisInfinita, lnSinfin,lnPagoInicialSinfin,
            lnGratisSinfin, lnPagoUnicoPrepago, lnPagoUnicoContrato, lnPagoUnicoCero,lnPagoUnicoCinco,lnPagoUnicoInfinita, lnPagoUnicoSinfin, lnPagoUnicoUno, lnPagoUnico650 ;
    private TextView tvPagoInicialCero, tvPagoInicialCero2,tvCuotaMensualCero, tvCuotaMensualCero2, tvPagoFinalCero, tvPagoInicialCinco, tvPagoInicialCinco2,
            tvCuotaMensualCinco, tvCuotaMensualCinco2, tvPagoFinalCinco, tvPagoInicialInfinita, tvPagoInicialInfinita2, tvCuotaMensualInfinita, tvCuotaMensualInfinita2,
            tvPagFinalInfinita, tvPagoInicialSinfin, tvPagoInicialSinfin2, tvCuotaMensualSinfin, tvCuotaMensualSinfin2, tvPagFinalSinfin,
            tvPrepagoUno, tvPrepago650, tvPagoUnicoCero, tvPagoUnicoCinco,tvPagoUnicoInfinita, tvPagoUnicoSinfin;
    private RadioGroup radioGroup;
    private RadioButton rb300, rb50;

    public TarifasCombinadasFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View parentView = inflater.inflate(R.layout.fragment_tarifas_combinadas, container, false);
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
        radioGroup = (RadioGroup) parentView.findViewById(R.id.radio_group);
        rb300 = (RadioButton) parentView.findViewById(R.id.rb_300);
        rb50 = (RadioButton) parentView.findViewById(R.id.rb_50);
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

                    if (ofertaTactica.getCuotaCombinadaNaranja300() == null || ofertaTactica.getCuotaCombinadaNaranja300().equals(""))
                        lnCero.setVisibility(View.GONE);
                    else if(ofertaTactica.getCuotaCombinadaNaranja300().equals("0,00"))
                    {
                        lnCero.setVisibility(View.GONE);
                        rlCero.setVisibility(View.VISIBLE);
                        lnPagoInicialCero.setVisibility(View.GONE);
                        lnGratisCero.setVisibility(View.VISIBLE);
                    }
                    else if(ofertaTactica.getPagoFinalCombinadaNaranja300().equals("0,00"))
                    {
                        lnCero.setVisibility(View.GONE);
                        rlCero.setVisibility(View.VISIBLE);
                        tvPagoInicialCero2.setText(Commons.tratarPrecio(ofertaTactica.getPagoInicialCombinadaNaranja300()));
                        tvCuotaMensualCero2.setText(Commons.tratarPrecio(ofertaTactica.getCuotaCombinadaNaranja300()));
                    }
                    else
                    {
                        tvPagoInicialCero.setText(Commons.tratarPrecio(ofertaTactica.getPagoInicialCombinadaNaranja300()));
                        tvCuotaMensualCero.setText(Commons.tratarPrecio(ofertaTactica.getCuotaCombinadaNaranja300()));
                        tvPagoFinalCero.setText(Commons.tratarPrecio(ofertaTactica.getPagoFinalCombinadaNaranja300()));
                    }
                    if (ofertaTactica.getCuotaCombinadaVerde300() == null || ofertaTactica.getCuotaCombinadaVerde300().equals(""))
                        lnCinco.setVisibility(View.GONE);
                    else if(ofertaTactica.getCuotaCombinadaVerde300().equals("0,00"))
                    {
                        lnCinco.setVisibility(View.GONE);
                        rlCinco.setVisibility(View.VISIBLE);
                        lnPagoInicialCinco.setVisibility(View.GONE);
                        lnGratisCinco.setVisibility(View.VISIBLE);
                    }
                    else if(ofertaTactica.getPagoFinalCombinadaVerde300().equals("0,00"))
                    {
                        lnCinco.setVisibility(View.GONE);
                        rlCinco.setVisibility(View.VISIBLE);
                        tvPagoInicialCinco2.setText(Commons.tratarPrecio(ofertaTactica.getPagoInicialCinco()));
                        tvCuotaMensualCinco2.setText(Commons.tratarPrecio(ofertaTactica.getCuotaCombinadaVerde300()));
                    }
                    else {
                        tvPagoInicialCinco.setText(Commons.tratarPrecio(ofertaTactica.getPagoInicialCombinadaVerde300()));
                        tvCuotaMensualCinco.setText(Commons.tratarPrecio(ofertaTactica.getCuotaCombinadaVerde300()));
                        tvPagoFinalCinco.setText(Commons.tratarPrecio(ofertaTactica.getPagoFinalCombinadaVerde300()));
                    }
                    if (ofertaTactica.getCuotaCombinadaMorada300() == null || ofertaTactica.getCuotaCombinadaMorada300().equals(""))
                        lnInfinita.setVisibility(View.GONE);
                    else if(ofertaTactica.getCuotaCombinadaMorada300().equals("0,00"))
                    {
                        lnInfinita.setVisibility(View.GONE);
                        rlInfinita.setVisibility(View.VISIBLE);
                        lnPagoInicialInfinita.setVisibility(View.GONE);
                        lnGratisInfinita.setVisibility(View.VISIBLE);
                    }
                    else if(ofertaTactica.getPagoFinalCombinadaMorada300().equals("0,00"))
                    {
                        lnInfinita.setVisibility(View.GONE);
                        rlInfinita.setVisibility(View.VISIBLE);
                        tvPagoInicialInfinita2.setText(Commons.tratarPrecio(ofertaTactica.getPagoInicialCombinadaMorada300()));
                        tvCuotaMensualInfinita2.setText(Commons.tratarPrecio(ofertaTactica.getCuotaCombinadaMorada300()));
                    }
                    else {
                        tvPagoInicialInfinita.setText(Commons.tratarPrecio(ofertaTactica.getPagoInicialCombinadaMorada300()));
                        tvCuotaMensualInfinita.setText(Commons.tratarPrecio(ofertaTactica.getCuotaCombinadaMorada300()));
                        tvPagFinalInfinita.setText(Commons.tratarPrecio(ofertaTactica.getPagoFinalCombinadaMorada300()));
                    }
                    if (ofertaTactica.getCuotaCombinadaAzul300() == null || ofertaTactica.getCuotaCombinadaAzul300().equals(""))
                        lnSinfin.setVisibility(View.GONE);
                    else if(ofertaTactica.getCuotaCombinadaAzul300().equals("0,00"))
                    {
                        lnSinfin.setVisibility(View.GONE);
                        rlSinfin.setVisibility(View.VISIBLE);
                        lnPagoInicialSinfin.setVisibility(View.GONE);
                        lnGratisSinfin.setVisibility(View.VISIBLE);
                    }
                    else if(ofertaTactica.getPagoFinalCombinadaAzul300().equals("0,00"))
                    {
                        lnSinfin.setVisibility(View.GONE);
                        rlSinfin.setVisibility(View.VISIBLE);
                        tvPagoInicialSinfin2.setText(Commons.tratarPrecio(ofertaTactica.getPagoInicialCombinadaAzul300()));
                        tvCuotaMensualSinfin2.setText(Commons.tratarPrecio(ofertaTactica.getCuotaCombinadaAzul300()));
                    }
                    else {
                        tvPagoInicialSinfin.setText(Commons.tratarPrecio(ofertaTactica.getPagoInicialCombinadaAzul300()));
                        tvCuotaMensualSinfin.setText(Commons.tratarPrecio(ofertaTactica.getCuotaCombinadaAzul300()));
                        tvPagFinalSinfin.setText(Commons.tratarPrecio(ofertaTactica.getPagoFinalCombinadaAzul300()));
                    }
                    if ((ofertaTactica.getPagoUnicoCombinadaNaranja300() == null || ofertaTactica.getPagoUnicoCombinadaNaranja300().equals(""))
                            && (ofertaTactica.getPagoUnicoCombinadaVerde300() == null || ofertaTactica.getPagoUnicoCombinadaVerde300().equals(""))
                            && (ofertaTactica.getPagoUnicoCombinadaMorada300() == null || ofertaTactica.getPagoUnicoCombinadaMorada300().equals(""))
                            && (ofertaTactica.getPagoUnicoCombinadaAzul300() == null || ofertaTactica.getPagoUnicoCombinadaAzul300().equals(""))) {
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
                    if (ofertaTactica.getPagoUnicoCombinadaNaranja300() == null || ofertaTactica.getPagoUnicoCombinadaNaranja300().equals("")) {
                        lnPagoUnicoCero.setVisibility(View.GONE);
                    }else{
                        tvPagoUnicoCero.setText(Commons.tratarPrecio(ofertaTactica.getPagoUnicoCombinadaNaranja300())+"€");
                    }
                    if (ofertaTactica.getPagoUnicoCombinadaVerde300() == null || ofertaTactica.getPagoUnicoCombinadaVerde300().equals("")) {
                        lnPagoUnicoCinco.setVisibility(View.GONE);
                    }else{
                        tvPagoUnicoCinco.setText(Commons.tratarPrecio(ofertaTactica.getPagoUnicoCombinadaVerde300())+"€");
                    }
                    if (ofertaTactica.getPagoUnicoCombinadaMorada300() == null || ofertaTactica.getPagoUnicoCombinadaMorada300().equals("")) {
                        lnPagoUnicoInfinita.setVisibility(View.GONE);
                    }else{
                        tvPagoUnicoInfinita.setText(Commons.tratarPrecio(ofertaTactica.getPagoUnicoCombinadaMorada300())+"€");
                    }
                    if (ofertaTactica.getPagoUnicoCombinadaAzul300() == null || ofertaTactica.getPagoUnicoCombinadaAzul300().equals("")) {
                        lnPagoUnicoSinfin.setVisibility(View.GONE);
                    }else{
                        tvPagoUnicoSinfin.setText(Commons.tratarPrecio(ofertaTactica.getPagoUnicoCombinadaAzul300())+"€");
                    }
                    lnCero.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            LinearLayout ln_color = (LinearLayout) v.findViewById(R.id.ln_color_cero);
                            TextView tvTarifa = (TextView) v.findViewById(R.id.tv_tarifa_cero);
                            clickTarifa(ln_color, tvTarifa, Config.COMBINADA_NARANJA_300, false);
                        }
                    });
                    rlCero.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            TextView tvTarifa = (TextView) v.findViewById(R.id.tv_tarifa_cero2);
                            clickTarifa(rlCero, tvTarifa, Config.COMBINADA_MORADA_300, false);
                        }
                    });
                    lnCinco.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            LinearLayout ln_color = (LinearLayout) v.findViewById(R.id.ln_color_cinco);
                            TextView tvTarifa = (TextView) v.findViewById(R.id.tv_tarifa_cinco2);
                            clickTarifa(ln_color, tvTarifa, Config.COMBINADA_VERDE_300, false);
                        }
                    });
                    rlCinco.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            TextView tvTarifa = (TextView) v.findViewById(R.id.tv_tarifa_cinco);
                            clickTarifa(rlCinco, tvTarifa, Config.COMBINADA_VERDE_300, false);
                        }
                    });
                    lnInfinita.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            LinearLayout ln_color = (LinearLayout) v.findViewById(R.id.ln_color_infinita);
                            TextView tvTarifa = (TextView) v.findViewById(R.id.tv_tarifa_infinita);
                            clickTarifa(ln_color, tvTarifa, Config.COMBINADA_MORADA_300, false);
                        }
                    });
                    rlInfinita.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            TextView tvTarifa = (TextView) v.findViewById(R.id.tv_tarifa_infinita);
                            clickTarifa(rlInfinita, tvTarifa, Config.COMBINADA_MORADA_300, false);
                        }
                    });
                    lnSinfin.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            LinearLayout ln_color = (LinearLayout) v.findViewById(R.id.ln_color_sinfin);
                            TextView tvTarifa = (TextView) v.findViewById(R.id.tv_tarifa_sinfin);
                            clickTarifa(ln_color, tvTarifa, Config.COMBINADA_AZUL_300, false);
                        }
                    });
                    rlSinfin.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            TextView tvTarifa = (TextView) v.findViewById(R.id.tv_tarifa_sinfin2);
                            clickTarifa(rlSinfin, tvTarifa, Config.COMBINADA_AZUL_300, false);
                        }
                    });

                    lnPagoUnicoCero.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            TextView tvTarifa = (TextView) v.findViewById(R.id.tv_tarifa_contrato_cero);
                            clickTarifa((LinearLayout) v, tvTarifa, Config.COMBINADA_NARANJA_300, true);
                        }
                    });
                    lnPagoUnicoCinco.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            TextView tvTarifa = (TextView) v.findViewById(R.id.tv_tarifa_contrato_cinco);
                            clickTarifa((LinearLayout) v, tvTarifa, Config.COMBINADA_VERDE_300, true);
                        }
                    });
                    lnPagoUnicoInfinita.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            TextView tvTarifa = (TextView) v.findViewById(R.id.tv_tarifa_contrato_infinita);
                            clickTarifa((LinearLayout) v, tvTarifa, Config.COMBINADA_MORADA_300, true);
                        }
                    });
                    lnPagoUnicoSinfin.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            TextView tvTarifa = (TextView) v.findViewById(R.id.tv_tarifa_contrato_sinfin);
                            clickTarifa((LinearLayout) v, tvTarifa, Config.COMBINADA_AZUL_300, true);
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
        Pair<View, String> p2 = Pair.create((View) textView, "nombre_tarifa");
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation( getActivity(), p1);
        startActivity(intent, options.toBundle());

    }

}
