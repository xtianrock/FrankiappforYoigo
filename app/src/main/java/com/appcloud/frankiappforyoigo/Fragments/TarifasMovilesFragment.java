package com.appcloud.frankiappforyoigo.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.appcloud.frankiappforyoigo.Activities.ReservaActivity;
import com.appcloud.frankiappforyoigo.Adapters.TerminalTarifaRecyclerAdapter;
import com.appcloud.frankiappforyoigo.Config;
import com.appcloud.frankiappforyoigo.POJO.OfertaTactica;
import com.appcloud.frankiappforyoigo.POJO.TerminalTarifa;
import com.appcloud.frankiappforyoigo.R;
import com.appcloud.frankiappforyoigo.Utils.Commons;
import com.appcloud.frankiappforyoigo.Utils.FirebaseSingleton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class TarifasMovilesFragment extends Fragment implements TerminalTarifaRecyclerAdapter.TerminlTarifaClickListener {

    private String keyTerminal;
    private OfertaTactica ofertaTactica;
    private RecyclerView rvTarifas;
    private LinearLayout lnPagoUnicoPrepago, lnPagoUnicoContrato, lnPagoUnicoCero,lnPagoUnicoCinco,lnPagoUnicoInfinita, lnPagoUnicoSinfin, lnPagoUnicoUno, lnPagoUnico650 ;
    private TextView tvPrepagoUno, tvPrepago650, tvPagoUnicoCero, tvPagoUnicoCinco,tvPagoUnicoInfinita, tvPagoUnicoSinfin;

    public TarifasMovilesFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View parentView = inflater.inflate(R.layout.fragment_tarifas_moviles, container, false);
        rvTarifas = (RecyclerView)parentView.findViewById(R.id.rv_tarifas);
        lnPagoUnicoPrepago = (LinearLayout) parentView.findViewById(R.id.ln_pago_unico_prepago);
        lnPagoUnicoContrato = (LinearLayout) parentView.findViewById(R.id.ln_pago_unico_contrato);
        lnPagoUnicoCero = (LinearLayout) parentView.findViewById(R.id.ln_pago_unico_cero);
        lnPagoUnicoCinco = (LinearLayout) parentView.findViewById(R.id.ln_pago_unico_cinco);
        lnPagoUnicoInfinita = (LinearLayout) parentView.findViewById(R.id.ln_pago_unico_infinita);
        lnPagoUnicoSinfin = (LinearLayout) parentView.findViewById(R.id.ln_pago_unico_sinfin);
        lnPagoUnicoUno = (LinearLayout) parentView.findViewById(R.id.ln_pago_unico_uno);
        lnPagoUnico650 = (LinearLayout) parentView.findViewById(R.id.ln_pago_unico_650);
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
            final List<TerminalTarifa> terminalTarifas = new ArrayList<>();
            DatabaseReference databaseReference = FirebaseSingleton.getDatabase().getReference();
            databaseReference.child("ofertas_tacticas").child(keyTerminal).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    ofertaTactica = dataSnapshot.getValue(OfertaTactica.class);
                    if (ofertaTactica.getCuotaCero() != null && !ofertaTactica.getCuotaCero().equals(""))
                        terminalTarifas.add(new TerminalTarifa(
                                Config.TARIFA_CERO,
                                ofertaTactica.getPagoInicialCero(),
                                ofertaTactica.getCuotaCero(),
                                ofertaTactica.getPagoFinalCero()));
                    if (ofertaTactica.getCuotaCinco() != null && !ofertaTactica.getCuotaCinco().equals(""))
                        terminalTarifas.add(new TerminalTarifa(
                                Config.TARIFA_CINCO,
                                ofertaTactica.getPagoInicialCinco(),
                                ofertaTactica.getCuotaCinco(),
                                ofertaTactica.getPagoFinalCinco()));
                    if (ofertaTactica.getCuotaInfinita() != null && !ofertaTactica.getCuotaInfinita().equals(""))
                        terminalTarifas.add(new TerminalTarifa(
                                Config.TARIFA_INFINITA,
                                ofertaTactica.getPagoInicialInfinita(),
                                ofertaTactica.getCuotaInfinita(),
                                ofertaTactica.getPagoFinalInfinita()));
                    if (ofertaTactica.getCuotaSinFin() != null && !ofertaTactica.getCuotaSinFin().equals(""))
                        terminalTarifas.add(new TerminalTarifa(
                                Config.TARIFA_SINFIN,
                                ofertaTactica.getPagoInicialSinFin(),
                                ofertaTactica.getCuotaSinFin(),
                                ofertaTactica.getPagoFinalSinFin()));

                    rvTarifas.setLayoutManager(new LinearLayoutManager(getContext()));
                    rvTarifas.setAdapter(new TerminalTarifaRecyclerAdapter(terminalTarifas,null,getContext()));

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

                    lnPagoUnicoUno.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(ofertaTactica.getPvprPrepago()!=null && !ofertaTactica.getPvprPrepago().equals("")){
                                TextView tvTarifa = (TextView) v.findViewById(R.id.tv_tarifa_prepago_uno);
                                clickTarifa((LinearLayout) v, Config.TARIFA_UNO, false);
                            }
                        }
                    });
                    lnPagoUnico650.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(ofertaTactica.getPvprPrepago()!=null && !ofertaTactica.getPvprPrepago().equals("")) {
                                TextView tvTarifa = (TextView) v.findViewById(R.id.tv_tarifa_prepago_650);
                                clickTarifa((LinearLayout) v, Config.TARIFA_650, false);
                            }
                        }
                    });
                    lnPagoUnicoCero.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            TextView tvTarifa = (TextView) v.findViewById(R.id.tv_tarifa_contrato_cero);
                            clickTarifa((LinearLayout) v, Config.TARIFA_CERO, true);
                        }
                    });
                    lnPagoUnicoCinco.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            TextView tvTarifa = (TextView) v.findViewById(R.id.tv_tarifa_contrato_cinco);
                            clickTarifa((LinearLayout) v, Config.TARIFA_CINCO, true);
                        }
                    });
                    lnPagoUnicoInfinita.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            TextView tvTarifa = (TextView) v.findViewById(R.id.tv_tarifa_contrato_infinita);
                            clickTarifa((LinearLayout) v, Config.TARIFA_INFINITA, true);
                        }
                    });
                    lnPagoUnicoSinfin.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            TextView tvTarifa = (TextView) v.findViewById(R.id.tv_tarifa_contrato_sinfin);
                            clickTarifa((LinearLayout) v, Config.TARIFA_SINFIN, true);
                        }
                    });

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
    }

    private void clickTarifa(View container, String tarifa, boolean pagoUnico) {
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

    @Override
    public void onItemClick(View vista, TerminalTarifa terminalTarifa, String keyTarifa) {
        clickTarifa(vista, keyTarifa, false);
    }
}
