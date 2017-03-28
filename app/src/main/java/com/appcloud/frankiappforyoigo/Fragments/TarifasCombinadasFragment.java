package com.appcloud.frankiappforyoigo.Fragments;

import android.content.Intent;
import android.os.Build;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
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


public class TarifasCombinadasFragment extends Fragment implements TerminalTarifaRecyclerAdapter.TerminlTarifaClickListener{

    private String keyTerminal;
    private OfertaTactica ofertaTactica;
    private RecyclerView rvTarifas;
    private LinearLayout lnPagoUnicoPrepago, lnPagoUnicoContrato, lnPagoUnicoCero,lnPagoUnicoCinco,lnPagoUnicoInfinita, lnPagoUnicoSinfin, lnPagoUnicoUno, lnPagoUnico650 ;
    private TextView tvPrepagoUno, tvPrepago650, tvPagoUnicoCero, tvPagoUnicoCinco,tvPagoUnicoInfinita, tvPagoUnicoSinfin;


    public TarifasCombinadasFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View parentView = inflater.inflate(R.layout.fragment_tarifas_combinadas, container, false);
        rvTarifas = (RecyclerView)parentView.findViewById(R.id.rv_tarifas);
        lnPagoUnicoContrato = (LinearLayout) parentView.findViewById(R.id.ln_pago_unico_contrato);
        lnPagoUnicoCero = (LinearLayout) parentView.findViewById(R.id.ln_pago_unico_cero);
        lnPagoUnicoCinco = (LinearLayout) parentView.findViewById(R.id.ln_pago_unico_cinco);
        lnPagoUnicoInfinita = (LinearLayout) parentView.findViewById(R.id.ln_pago_unico_infinita);
        lnPagoUnicoSinfin = (LinearLayout) parentView.findViewById(R.id.ln_pago_unico_sinfin);
        tvPagoUnicoCero = (TextView) parentView.findViewById(R.id.tv_pago_unico_cero);
        tvPagoUnicoCinco = (TextView) parentView.findViewById(R.id.tv_pago_unico_cinco);
        tvPagoUnicoInfinita = (TextView) parentView.findViewById(R.id.tv_pago_unico_infinita);
        tvPagoUnicoSinfin = (TextView) parentView.findViewById(R.id.tv_pago_unico_sinfin);
        return parentView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        rvTarifas.setNestedScrollingEnabled(false);
        rvTarifas.setLayoutManager(new LinearLayoutManager(getContext()));
        final List<TerminalTarifa> terminalTarifas = new ArrayList<>();
        rvTarifas.setAdapter(new TerminalTarifaRecyclerAdapter(terminalTarifas,this,getContext()));
        keyTerminal = getArguments().getString("keyTerminal");
        if (keyTerminal != null) {
            DatabaseReference databaseReference = FirebaseSingleton.getDatabase().getReference();
            databaseReference.child("ofertas_tacticas").child(keyTerminal).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    ofertaTactica = dataSnapshot.getValue(OfertaTactica.class);

                    if (ofertaTactica.getCuotaCombinadaNaranja300() != null && !ofertaTactica.getCuotaCombinadaNaranja300().equals(""))
                        terminalTarifas.add(new TerminalTarifa(
                                Config.COMBINADA_NARANJA_300,
                                ofertaTactica.getPagoInicialCombinadaNaranja300(),
                                ofertaTactica.getCuotaCombinadaNaranja300(),
                                ofertaTactica.getPagoFinalCombinadaNaranja300()));
                    rvTarifas.getAdapter().notifyItemInserted(terminalTarifas.size() - 1);
                    /*if (ofertaTactica.getCuotaCombinadaNaranja50() != null && !ofertaTactica.getCuotaCombinadaNaranja50().equals(""))
                        terminalTarifas.add(new TerminalTarifa(
                                Config.COMBINADA_NARANJA_50,
                                ofertaTactica.getPagoInicialCombinadaNaranja50(),
                                ofertaTactica.getCuotaCombinadaNaranja50(),
                                ofertaTactica.getPagoFinalCombinadaNaranja50()));
                    rvTarifas.getAdapter().notifyItemInserted(terminalTarifas.size() - 1);*/
                    if (ofertaTactica.getCuotaCombinadaVerde300() != null && !ofertaTactica.getCuotaCombinadaVerde300().equals(""))
                        terminalTarifas.add(new TerminalTarifa(
                                Config.COMBINADA_VERDE_300,
                                ofertaTactica.getPagoInicialCombinadaVerde300(),
                                ofertaTactica.getCuotaCombinadaVerde300(),
                                ofertaTactica.getPagoFinalCombinadaVerde300()));
                    rvTarifas.getAdapter().notifyItemInserted(terminalTarifas.size() - 1);
                    /*if (ofertaTactica.getCuotaCombinadaVerde50() != null && !ofertaTactica.getCuotaCombinadaVerde50().equals(""))
                        terminalTarifas.add(new TerminalTarifa(
                                Config.COMBINADA_VERDE_50,
                                ofertaTactica.getPagoInicialCombinadaVerde50(),
                                ofertaTactica.getCuotaCombinadaVerde50(),
                                ofertaTactica.getPagoFinalCombinadaVerde50()));
                    rvTarifas.getAdapter().notifyItemInserted(terminalTarifas.size() - 1);*/
                    if (ofertaTactica.getCuotaCombinadaMorada300() != null && !ofertaTactica.getCuotaCombinadaMorada300().equals(""))
                        terminalTarifas.add(new TerminalTarifa(
                                Config.COMBINADA_MORADA_300,
                                ofertaTactica.getPagoInicialCombinadaMorada300(),
                                ofertaTactica.getCuotaCombinadaMorada300(),
                                ofertaTactica.getPagoFinalCombinadaMorada300()));
                    rvTarifas.getAdapter().notifyItemInserted(terminalTarifas.size() - 1);
                    /*if (ofertaTactica.getCuotaCombinadaMorada50() != null && !ofertaTactica.getCuotaCombinadaMorada50().equals(""))
                        terminalTarifas.add(new TerminalTarifa(
                                Config.COMBINADA_MORADA_50,
                                ofertaTactica.getPagoInicialCombinadaMorada50(),
                                ofertaTactica.getCuotaCombinadaMorada50(),
                                ofertaTactica.getPagoFinalCombinadaMorada50()));
                    rvTarifas.getAdapter().notifyItemInserted(terminalTarifas.size() - 1);*/
                    if (ofertaTactica.getCuotaCombinadaAzul300() != null && !ofertaTactica.getCuotaCombinadaAzul300().equals(""))
                        terminalTarifas.add(new TerminalTarifa(
                                Config.COMBINADA_AZUL_300,
                                ofertaTactica.getPagoInicialCombinadaAzul300(),
                                ofertaTactica.getCuotaCombinadaAzul300(),
                                ofertaTactica.getPagoFinalCombinadaAzul300()));
                    rvTarifas.getAdapter().notifyItemInserted(terminalTarifas.size() - 1);
                    /*if (ofertaTactica.getCuotaCombinadaAzul50() != null && !ofertaTactica.getCuotaCombinadaAzul50().equals(""))
                        terminalTarifas.add(new TerminalTarifa(
                                Config.COMBINADA_AZUL_50,
                                ofertaTactica.getPagoInicialCombinadaAzul50(),
                                ofertaTactica.getCuotaCombinadaAzul50(),
                                ofertaTactica.getPagoFinalCombinadaAzul50()));
                    rvTarifas.getAdapter().notifyItemInserted(terminalTarifas.size() - 1);*/

                    if ((ofertaTactica.getPagoUnicoCombinadaNaranja300() == null || ofertaTactica.getPagoUnicoCombinadaNaranja300().equals(""))
                            && (ofertaTactica.getPagoUnicoCombinadaVerde300() == null || ofertaTactica.getPagoUnicoCombinadaVerde300().equals(""))
                            && (ofertaTactica.getPagoUnicoCombinadaMorada300() == null || ofertaTactica.getPagoUnicoCombinadaMorada300().equals(""))
                            && (ofertaTactica.getPagoUnicoCombinadaAzul300() == null || ofertaTactica.getPagoUnicoCombinadaAzul300().equals(""))) {
                        lnPagoUnicoContrato.setVisibility(View.GONE);
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

                    lnPagoUnicoCero.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            TextView tvTarifa = (TextView) v.findViewById(R.id.tv_tarifa_contrato_cero);
                            clickTarifa(v, Config.COMBINADA_NARANJA_300, true);
                        }
                    });
                    lnPagoUnicoCinco.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            TextView tvTarifa = (TextView) v.findViewById(R.id.tv_tarifa_contrato_cinco);
                            clickTarifa(v, Config.COMBINADA_VERDE_300, true);
                        }
                    });
                    lnPagoUnicoInfinita.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            TextView tvTarifa = (TextView) v.findViewById(R.id.tv_tarifa_contrato_infinita);
                            clickTarifa(v, Config.COMBINADA_MORADA_300, true);
                        }
                    });
                    lnPagoUnicoSinfin.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            TextView tvTarifa = (TextView) v.findViewById(R.id.tv_tarifa_contrato_sinfin);
                            clickTarifa(v, Config.COMBINADA_AZUL_300, true);
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

        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Pair<View, String> p1 = Pair.create((View) container, "color_bar");
            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation( getActivity(), p1);
            startActivity(intent, options.toBundle());
        }
        else
        {
            startActivity(intent);
        }*/
        startActivity(intent);
    }

    @Override
    public void onItemClick(View vista, TerminalTarifa terminalTarifa) {
        clickTarifa(vista,terminalTarifa.getNombreTarifa(), false);
    }
}
