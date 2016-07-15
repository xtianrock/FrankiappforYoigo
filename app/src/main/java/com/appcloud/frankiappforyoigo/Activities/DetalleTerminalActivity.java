package com.appcloud.frankiappforyoigo.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.appcloud.frankiappforyoigo.Configuracion;
import com.appcloud.frankiappforyoigo.POJO.OfertaTactica;
import com.appcloud.frankiappforyoigo.R;
import com.appcloud.frankiappforyoigo.Utils.Commons;
import com.appcloud.frankiappforyoigo.Utils.FirebaseSingleton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class DetalleTerminalActivity extends AppCompatActivity {

    private String keyTerminal;
    private Context context = this;
    private ImageView ivFotoTerminal;
    private LinearLayout lnCero, lnCinco, lnSinfin, lnPagoUnico, lnPagoUnicoUno, lnPagoUnico650,
            lnPagoUnicoContrato, lnPagoUnicoCero, lnPagoUnicoCinco, lnPagoUnicoSinfin;
    private TextView tvNombre, tvPagoInicialCero, tvCuotaMensualCero, tvPagoFinalCero, tvPagoInicialCinco,
            tvCuotaMensualCinco, tvPagoFinalCinco, tvPagoInicialSinfin, tvCuotaMensualSinfin,
            tvPagFinalSinfin, tvPrepagoUno, tvPrepago650, tvPagoUnicoCero, tvPagoUnicoCinco, tvPagoUnicoSinfin;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private OfertaTactica ofertaTactica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_terminal);
        keyTerminal = getIntent().getStringExtra(Configuracion.KEY_TERMINAL);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        ivFotoTerminal = (ImageView) findViewById(R.id.iv_terminal_foto);
        tvNombre = (TextView) findViewById(R.id.tv_terminal_nombre);
        lnCero = (LinearLayout) findViewById(R.id.ln_cero);
        lnCinco = (LinearLayout) findViewById(R.id.ln_cinco);
        lnSinfin = (LinearLayout) findViewById(R.id.ln_sinfin);
        lnPagoUnico = (LinearLayout) findViewById(R.id.ln_pago_unico);
        lnPagoUnicoUno = (LinearLayout) findViewById(R.id.ln_pago_unico_uno);
        lnPagoUnico650 = (LinearLayout) findViewById(R.id.ln_pago_unico_650);
        lnPagoUnicoContrato = (LinearLayout) findViewById(R.id.ln_pago_unico_contrato);
        lnPagoUnicoCero = (LinearLayout) findViewById(R.id.ln_pago_unico_cero);
        lnPagoUnicoCinco = (LinearLayout) findViewById(R.id.ln_pago_unico_cinco);
        lnPagoUnicoSinfin = (LinearLayout) findViewById(R.id.ln_pago_unico_sinfin);
        tvPagoInicialCero = (TextView) findViewById(R.id.tv_pago_inicial_cero);
        tvCuotaMensualCero = (TextView) findViewById(R.id.tv_cuota_mensual_cero);
        tvPagoFinalCero = (TextView) findViewById(R.id.tv_pago_final_cero);
        tvPagoInicialCinco = (TextView) findViewById(R.id.tv_pago_inicial_cinco);
        tvCuotaMensualCinco = (TextView) findViewById(R.id.tv_cuota_mensual_cinco);
        tvPagoFinalCinco = (TextView) findViewById(R.id.tv_pago_final_cinco);
        tvPagoInicialSinfin = (TextView) findViewById(R.id.tv_pago_inicial_sinfin);
        tvCuotaMensualSinfin = (TextView) findViewById(R.id.tv_cuota_mensual_sinfin);
        tvPagFinalSinfin = (TextView) findViewById(R.id.tv_pago_final_sinfin);
        tvPrepagoUno = (TextView) findViewById(R.id.tv_pago_unico_uno);
        tvPrepago650 = (TextView) findViewById(R.id.tv_pago_unico_650);
        tvPagoUnicoCero = (TextView) findViewById(R.id.tv_pago_unico_cero);
        tvPagoUnicoCinco = (TextView) findViewById(R.id.tv_pago_unico_cinco);
        tvPagoUnicoSinfin = (TextView) findViewById(R.id.tv_pago_unico_sinfin);

        if (keyTerminal != null) {
            DatabaseReference databaseReference = FirebaseSingleton.getDatabase().getReference();
            databaseReference.child("ofertas_tacticas").child(keyTerminal).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    ofertaTactica = dataSnapshot.getValue(OfertaTactica.class);
                    Picasso.with(context).load(ofertaTactica.getFoto()).into(ivFotoTerminal);
                    if (collapsingToolbarLayout != null)
                        collapsingToolbarLayout.setTitle(ofertaTactica.getTerminal());
                    else
                        tvNombre.setText(ofertaTactica.getTerminal());
                    if (ofertaTactica.getFinanciacionCero() == null || ofertaTactica.getFinanciacionCero().equals(""))
                        lnCero.setVisibility(View.GONE);
                    else {
                        tvPagoInicialCero.setText(Commons.tratarPrecio(ofertaTactica.getPagoInicialCero()));
                        tvCuotaMensualCero.setText(Commons.tratarFinanciacion(ofertaTactica.getFinanciacionCero()));
                        tvPagoFinalCero.setText(Commons.tratarPrecio(ofertaTactica.getPagoFinalCero()));
                    }
                    if (ofertaTactica.getFinanciacionCinco() == null || ofertaTactica.getFinanciacionCinco().equals(""))
                        lnCinco.setVisibility(View.GONE);
                    else {
                        tvPagoInicialCinco.setText(Commons.tratarPrecio(ofertaTactica.getPagoInicialCinco()));
                        tvCuotaMensualCinco.setText(Commons.tratarFinanciacion(ofertaTactica.getFinanciacionCinco()));
                        tvPagoFinalCinco.setText(Commons.tratarPrecio(ofertaTactica.getPagoFinalCinco()));
                    }
                    if (ofertaTactica.getFinanciacionSinFin() == null || ofertaTactica.getFinanciacionSinFin().equals(""))
                        lnSinfin.setVisibility(View.GONE);
                    else {
                        tvPagoInicialSinfin.setText(Commons.tratarPrecio(ofertaTactica.getPagoInicialSinFin()));
                        tvCuotaMensualSinfin.setText(Commons.tratarFinanciacion(ofertaTactica.getFinanciacionSinFin()));
                        tvPagFinalSinfin.setText(Commons.tratarPrecio(ofertaTactica.getPagoFinalSinFin()));
                    }
                    if ((ofertaTactica.getPagoUnicoCero() == null || ofertaTactica.getPagoUnicoCero().equals(""))
                            && (ofertaTactica.getPagoUnicoCinco() == null || ofertaTactica.getPagoUnicoCinco().equals(""))
                            && (ofertaTactica.getPagoUnicoSinFin() == null || ofertaTactica.getPagoUnicoSinFin().equals(""))) {
                        lnPagoUnicoContrato.setVisibility(View.GONE);
                    }
                    if (ofertaTactica.getPagoUnicoPrepago() != null && !ofertaTactica.getPagoUnicoPrepago().equals("")) {
                        tvPrepagoUno.setText(Commons.tratarPrecio(ofertaTactica.getPagoUnicoPrepago())+"€");
                        tvPrepago650.setText(Commons.tratarPrecio(ofertaTactica.getPagoUnicoPrepago())+"€");
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
                            clickTarifa(ln_color, tvTarifa, Configuracion.TARIFA_CERO, false);
                        }
                    });
                    lnCinco.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            LinearLayout ln_color = (LinearLayout) v.findViewById(R.id.ln_color_cinco);
                            TextView tvTarifa = (TextView) v.findViewById(R.id.tv_tarifa_cinco);
                            clickTarifa(ln_color, tvTarifa, Configuracion.TARIFA_CINCO, false);
                        }
                    });
                    lnSinfin.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            LinearLayout ln_color = (LinearLayout) v.findViewById(R.id.ln_color_sinfin);
                            TextView tvTarifa = (TextView) v.findViewById(R.id.tv_tarifa_sinfin);
                            clickTarifa(ln_color, tvTarifa, Configuracion.TARIFA_SINFIN, false);
                        }
                    });
                    lnPagoUnicoUno.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(ofertaTactica.getPagoUnicoPrepago()!=null && !ofertaTactica.getPagoUnicoPrepago().equals("")){
                                TextView tvTarifa = (TextView) v.findViewById(R.id.tv_tarifa_prepago_uno);
                                clickTarifa((LinearLayout) v, tvTarifa, Configuracion.TARIFA_UNO, false);
                            }
                        }
                    });
                    lnPagoUnico650.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(ofertaTactica.getPagoUnicoPrepago()!=null && !ofertaTactica.getPagoUnicoPrepago().equals("")) {
                                TextView tvTarifa = (TextView) v.findViewById(R.id.tv_tarifa_prepago_650);
                                clickTarifa((LinearLayout) v, tvTarifa, Configuracion.TARIFA_650, false);
                            }
                        }
                    });
                    lnPagoUnicoCero.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            TextView tvTarifa = (TextView) v.findViewById(R.id.tv_tarifa_contrato_cero);
                            clickTarifa((LinearLayout) v, tvTarifa, Configuracion.TARIFA_CERO, true);
                        }
                    });
                    lnPagoUnicoCinco.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            TextView tvTarifa = (TextView) v.findViewById(R.id.tv_tarifa_contrato_cinco);
                            clickTarifa((LinearLayout) v, tvTarifa, Configuracion.TARIFA_CINCO, true);
                        }
                    });
                    lnPagoUnicoSinfin.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            TextView tvTarifa = (TextView) v.findViewById(R.id.tv_tarifa_contrato_sinfin);
                            clickTarifa((LinearLayout) v, tvTarifa, Configuracion.TARIFA_SINFIN, true);
                        }
                    });

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

    }


    private void clickTarifa(LinearLayout linearLayout, TextView textView, int tarifa, boolean pagoUnico) {
        Intent intent = new Intent(context, ReservaActivity.class);
        intent.putExtra(Configuracion.KEY_TERMINAL, keyTerminal);
        intent.putExtra(Configuracion.TARIFA, tarifa);
        intent.putExtra(Configuracion.PAGO_UNICO, pagoUnico);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ivFotoTerminal.setTransitionName("foto_terminal");
            lnCero.setTransitionName("color_bar");
        }
        Pair<View, String> p1 = Pair.create((View) ivFotoTerminal, "foto_terminal");
        Pair<View, String> p2 = Pair.create((View) linearLayout, "color_bar");
        Pair<View, String> p3 = Pair.create((View) textView, "tarifa");
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation((Activity) context, p1, p2, p3);
        startActivity(intent, options.toBundle());
    }


}
