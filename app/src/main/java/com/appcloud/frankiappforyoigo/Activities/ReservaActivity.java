package com.appcloud.frankiappforyoigo.Activities;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.appcloud.frankiappforyoigo.Config;
import com.appcloud.frankiappforyoigo.POJO.OfertaTactica;
import com.appcloud.frankiappforyoigo.POJO.Reserva;
import com.appcloud.frankiappforyoigo.POJO.Tarifa;
import com.appcloud.frankiappforyoigo.R;
import com.appcloud.frankiappforyoigo.Utils.Commons;
import com.appcloud.frankiappforyoigo.Utils.FirebaseSingleton;
import com.appcloud.frankiappforyoigo.Utils.PicassoRoundedBordersTransformation;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.joanfuentes.hintcase.HintCase;
import com.joanfuentes.hintcase.RectangularShape;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;

public class ReservaActivity extends BaseActivity {

    private Context context = this;
    private int cuotaTerminal=0;
    private Toolbar toolbar;
    private LinearLayout lnDetalleTarifa, lnPlus;
    private TextView tvTitle;
    private Tarifa tarifa;
    private String keyTerminal, keyTarifa;
    private OfertaTactica ofertaTactica;
    private Button btReserva;
    private boolean pagoUnico, tarifaCargada = false, terminalCargado = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        keyTerminal = getIntent().getStringExtra(Config.KEY_TERMINAL);
        pagoUnico = getIntent().getBooleanExtra(Config.PAGO_UNICO,false);
        keyTarifa = getIntent().getStringExtra(Config.KEY_TARIFA);
        if(keyTarifa !=null)
            setTheme(Commons.getTema(keyTarifa));
        setContentView(R.layout.activity_reserva);

        lnDetalleTarifa = (LinearLayout)findViewById(R.id.ln_detalle_tarifa);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        btReserva = (Button)findViewById(R.id.bt_reserva);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getIntent().getAction().equals(Config.VER)){
            btReserva.setVisibility(View.GONE);
        }
        btReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reservar();
            }
        });

        prepararDatosOfertaTactica();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child(Config.TARIFAS).child(keyTarifa);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                tarifa = dataSnapshot.getValue(Tarifa.class);
                tvTitle.setText(tarifa.getTarifa());
                getSupportActionBar().setTitle(tarifa.getTarifa());
                setDetalleTarifa();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }



    private void prepararDatosOfertaTactica(){
        if (keyTerminal != null) {
            DatabaseReference databaseReference = FirebaseSingleton.getDatabase().getReference();
            databaseReference.child(Config.OFERTAS_TACTICAS).child(keyTerminal).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    ofertaTactica = dataSnapshot.getValue(OfertaTactica.class);
                    final ImageView ivFotoTerminal = (ImageView)findViewById(R.id.iv_terminal_foto);
                    Picasso.with(context)
                            .load(ofertaTactica.getFotoUrl())
                            .transform(new PicassoRoundedBordersTransformation(5,0))
                            .networkPolicy(NetworkPolicy.OFFLINE)
                            .placeholder(R.drawable.phone_mockup)
                            .into(ivFotoTerminal,new Callback() {
                                @Override
                                public void onSuccess() {

                                }

                                @Override
                                public void onError() {
                                    //Try again online if cache failed
                                    Picasso.with(context)
                                            .load(ofertaTactica.getFotoUrl())
                                            .placeholder(R.drawable.phone_mockup)
                                            .error(R.drawable.phone_mockup)
                                            .into(ivFotoTerminal);
                                }
                            });
                    TextView tvNombreTerminal = (TextView)findViewById(R.id.tv_terminal_nombre);
                    tvNombreTerminal.setText(keyTerminal);
                    terminalCargado=true;
                    prepararTotal();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

    private void prepararTotal(){
        if(terminalCargado && tarifaCargada){
            TextView tvCuotaTerminal = (TextView)findViewById(R.id.tv_cuota_terminal);
            TextView tvPagoInicial =(TextView)findViewById(R.id.tv_pago_inicial);
            TextView tvCuotaTotal =(TextView)findViewById(R.id.tv_cuota_total);
            TextView tvPagoFinal =(TextView)findViewById(R.id.tv_pago_final);
            int cuotaTarifa = Integer.parseInt(tarifa.getCuota());
            switch (keyTarifa){
                case Config.TARIFA_CERO:
                    cuotaTerminal = Integer.parseInt(Commons.tratarPrecio(ofertaTactica.getCuotaCero()));
                    tvPagoInicial.setText(Commons.tratarPrecio(ofertaTactica.getPagoInicialCero()));
                    tvPagoFinal.setText(Commons.tratarPrecio(ofertaTactica.getPagoFinalCero()));
                    if(pagoUnico){
                        tvPagoInicial.setText(Commons.tratarPrecio(ofertaTactica.getPagoUnicoCero()));
                        setPagoUnico();
                    }
                    break;
                case Config.TARIFA_CINCO:
                    cuotaTerminal = Integer.parseInt(Commons.tratarPrecio(ofertaTactica.getCuotaCinco()));
                    tvPagoInicial.setText(Commons.tratarPrecio(ofertaTactica.getPagoInicialCinco()));
                    tvPagoFinal.setText(Commons.tratarPrecio(ofertaTactica.getPagoFinalCinco()));
                    if(pagoUnico){
                        tvPagoInicial.setText(Commons.tratarPrecio(ofertaTactica.getPagoUnicoCinco()));
                        setPagoUnico();
                    }
                    break;
                case Config.TARIFA_INFINITA:
                    cuotaTerminal = Integer.parseInt(Commons.tratarPrecio(ofertaTactica.getCuotaInfinita()));
                    tvPagoInicial.setText(Commons.tratarPrecio(ofertaTactica.getPagoInicialInfinita()));
                    tvPagoFinal.setText(Commons.tratarPrecio(ofertaTactica.getPagoFinalInfinita()));
                    if(pagoUnico){
                        tvPagoInicial.setText(Commons.tratarPrecio(ofertaTactica.getPagoUnicoInfinita()));
                        setPagoUnico();
                    }
                    break;
                case Config.TARIFA_SINFIN:
                    cuotaTerminal = Integer.parseInt(Commons.tratarPrecio(ofertaTactica.getCuotaSinFin()));
                    tvPagoInicial.setText(Commons.tratarPrecio(ofertaTactica.getPagoInicialSinFin()));
                    tvPagoFinal.setText(Commons.tratarPrecio(ofertaTactica.getPagoFinalSinFin()));
                    if(pagoUnico){
                        tvPagoInicial.setText(Commons.tratarPrecio(ofertaTactica.getPagoUnicoSinFin()));
                        setPagoUnico();
                    }
                    break;
                case Config.COMBINADA_NARANJA_50:
                    cuotaTerminal = Integer.parseInt(Commons.tratarPrecio(ofertaTactica.getCuotaCombinadaNaranja50()));
                    tvPagoInicial.setText(Commons.tratarPrecio(ofertaTactica.getPagoInicialCombinadaNaranja50()));
                    tvPagoFinal.setText(Commons.tratarPrecio(ofertaTactica.getPagoFinalCombinadaNaranja50()));
                    if(pagoUnico){
                        tvPagoInicial.setText(Commons.tratarPrecio(ofertaTactica.getPagoUnicoCombinadaNaranja50()));
                        setPagoUnico();
                    }
                case Config.COMBINADA_NARANJA_300:
                    cuotaTerminal = Integer.parseInt(Commons.tratarPrecio(ofertaTactica.getCuotaCombinadaNaranja300()));
                    tvPagoInicial.setText(Commons.tratarPrecio(ofertaTactica.getPagoInicialCombinadaNaranja300()));
                    tvPagoFinal.setText(Commons.tratarPrecio(ofertaTactica.getPagoFinalCombinadaNaranja300()));
                    if(pagoUnico){
                        tvPagoInicial.setText(Commons.tratarPrecio(ofertaTactica.getPagoUnicoCombinadaNaranja300()));
                        setPagoUnico();
                    }
                case Config.COMBINADA_VERDE_50:
                    cuotaTerminal = Integer.parseInt(Commons.tratarPrecio(ofertaTactica.getCuotaCombinadaVerde50()));
                    tvPagoInicial.setText(Commons.tratarPrecio(ofertaTactica.getPagoInicialCombinadaVerde50()));
                    tvPagoFinal.setText(Commons.tratarPrecio(ofertaTactica.getPagoFinalCombinadaVerde50()));
                    if(pagoUnico){
                        tvPagoInicial.setText(Commons.tratarPrecio(ofertaTactica.getPagoUnicoCombinadaVerde50()));
                        setPagoUnico();
                    }
                case Config.COMBINADA_VERDE_300:
                    cuotaTerminal = Integer.parseInt(Commons.tratarPrecio(ofertaTactica.getCuotaCombinadaVerde300()));
                    tvPagoInicial.setText(Commons.tratarPrecio(ofertaTactica.getPagoInicialCombinadaVerde300()));
                    tvPagoFinal.setText(Commons.tratarPrecio(ofertaTactica.getPagoFinalCombinadaVerde300()));
                    if(pagoUnico){
                        tvPagoInicial.setText(Commons.tratarPrecio(ofertaTactica.getPagoUnicoCombinadaVerde300()));
                        setPagoUnico();
                    }
                case Config.COMBINADA_MORADA_50:
                    cuotaTerminal = Integer.parseInt(Commons.tratarPrecio(ofertaTactica.getCuotaCombinadaMorada50()));
                    tvPagoInicial.setText(Commons.tratarPrecio(ofertaTactica.getPagoInicialCombinadaMorada50()));
                    tvPagoFinal.setText(Commons.tratarPrecio(ofertaTactica.getPagoFinalCombinadaMorada50()));
                    if(pagoUnico){
                        tvPagoInicial.setText(Commons.tratarPrecio(ofertaTactica.getPagoUnicoCombinadaMorada50()));
                        setPagoUnico();
                    }
                case Config.COMBINADA_MORADA_300:
                    cuotaTerminal = Integer.parseInt(Commons.tratarPrecio(ofertaTactica.getCuotaCombinadaMorada300()));
                    tvPagoInicial.setText(Commons.tratarPrecio(ofertaTactica.getPagoInicialCombinadaMorada300()));
                    tvPagoFinal.setText(Commons.tratarPrecio(ofertaTactica.getPagoFinalCombinadaMorada300()));
                    if(pagoUnico){
                        tvPagoInicial.setText(Commons.tratarPrecio(ofertaTactica.getPagoUnicoCombinadaMorada300()));
                        setPagoUnico();
                    }
                case Config.COMBINADA_AZUL_50:
                    cuotaTerminal = Integer.parseInt(Commons.tratarPrecio(ofertaTactica.getCuotaCombinadaAzul50()));
                    tvPagoInicial.setText(Commons.tratarPrecio(ofertaTactica.getPagoInicialCombinadaAzul50()));
                    tvPagoFinal.setText(Commons.tratarPrecio(ofertaTactica.getPagoFinalCombinadaAzul50()));
                    if(pagoUnico){
                        tvPagoInicial.setText(Commons.tratarPrecio(ofertaTactica.getPagoUnicoCombinadaAzul50()));
                        setPagoUnico();
                    }
                case Config.COMBINADA_AZUL_300:
                    cuotaTerminal = Integer.parseInt(Commons.tratarPrecio(ofertaTactica.getCuotaCombinadaAzul300()));
                    tvPagoInicial.setText(Commons.tratarPrecio(ofertaTactica.getPagoInicialCombinadaAzul300()));
                    tvPagoFinal.setText(Commons.tratarPrecio(ofertaTactica.getPagoFinalCombinadaAzul300()));
                    if(pagoUnico){
                        tvPagoInicial.setText(Commons.tratarPrecio(ofertaTactica.getPagoUnicoCombinadaAzul300()));
                        setPagoUnico();
                    }
                    break;
                case Config.TARIFA_UNO:
                    tvPagoInicial.setText(Commons.tratarPrecio(ofertaTactica.getPvprPrepago()));
                    setPagoUnico();
                    break;
                case Config.TARIFA_650:
                    tvPagoInicial.setText(Commons.tratarPrecio(ofertaTactica.getPvprPrepago()));
                    setPagoUnico();
                    break;
            }
            tvCuotaTotal.setText(String.valueOf(cuotaTarifa+cuotaTerminal));
            tvCuotaTerminal.setText(String.valueOf(cuotaTerminal));

        }
    }

    private void  setDetalleTarifa(){
        LinearLayout lnCosteMinuto = (LinearLayout) findViewById(R.id.ln_coste_minuto);
        RelativeLayout rlIlimitadas = (RelativeLayout) findViewById(R.id.ln_ilimitadas);
        LinearLayout lnMinutos = (LinearLayout)findViewById(R.id.ln_minutos);
        LinearLayout lnMovil = (LinearLayout)findViewById(R.id.ln_tarifa_movil);
        LinearLayout lnFibra = (LinearLayout)findViewById(R.id.ln_tarifa_fibra);
        lnPlus = (LinearLayout)findViewById(R.id.ln_plus);
        TextView tvLlamadas = (TextView) findViewById(R.id.tv_llamadas);
        TextView tvMinnutos = (TextView) findViewById(R.id.tv_min_gratis);
        TextView tvDatos = (TextView) findViewById(R.id.tv_datos);
        TextView tvUnidadDatos = (TextView) findViewById(R.id.tv_unidad_datos);
        TextView tvCuota = (TextView) findViewById(R.id.tv_cuota);
        TextView tvVelocidad = (TextView) findViewById(R.id.tv_velocidad);
        if(tarifa.isIlimitadas()){
            lnCosteMinuto.setVisibility(View.GONE);
        }else{
            rlIlimitadas.setVisibility(View.GONE);
            tvLlamadas.setText(tarifa.getPrecio_minuto());
            if(tarifa.getMinutos_gratis()!=null && !tarifa.getMinutos_gratis().equals(""))
            {
                tvMinnutos.setText("+"+tarifa.getMinutos_gratis()+"min");
            }else{
                tvMinnutos.setVisibility(View.GONE);
            }
        }
        if(tarifa.getUnidad_datos().equals("MB")){
            tvDatos.setTextSize(TypedValue.COMPLEX_UNIT_SP,35);
        }

        double datos = tarifa.getDatos()+tarifa.getDatos_extra();
        if(datos == (long) datos)
            tvDatos.setText(String.format("%d",(long)datos));
        else
            tvDatos.setText( String.format("%s",datos));

        tvUnidadDatos.setText(tarifa.getUnidad_datos());
        if(tarifa.getCuota_promo()!= null)
            tvCuota.setText(tarifa.getCuota_promo());
        else
            tvCuota.setText(tarifa.getCuota());

        if(tarifa.getVelocidad_fibra()!=null)
        {
            lnPlus.setVisibility(View.VISIBLE);
            lnFibra.setVisibility(View.VISIBLE);
            if(tarifa.getVelocidad_adsl()!=null)
                tvVelocidad.setText(R.string.fibra_50);
            else
                tvVelocidad.setText(R.string.fibra_300);
        }

        tarifaCargada=true;
        prepararTotal();
    }

    private void setPagoUnico(){
        cuotaTerminal=0;
        TextView tvPagoFinal =(TextView)findViewById(R.id.tv_pago_final);
        tvPagoFinal.setText("-");
        findViewById(R.id.ln_cuota_terminal).setVisibility(View.GONE);
        findViewById(R.id.tv_pago_final_euro).setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        lnPlus.setVisibility(View.GONE);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_reserva, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.info_tarifa:
                //showHint();
                Snackbar.make(tvTitle, "Se deberia abrir el detalle de la tarifa", Snackbar.LENGTH_LONG)
                        .show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void reservar(){

        final AlertDialog.Builder builder= new AlertDialog.Builder(context);
        final AlertDialog alertDialog = builder.create();
        View rootView = getLayoutInflater().inflate(R.layout.reserva_dialog, null);
        TextView tvReservar = (TextView) rootView.findViewById(R.id.tv_reservar);
        TextView tvCancelar = (TextView)rootView.findViewById(R.id.tv_cancelar);
        final TextInputEditText etTelefono = (TextInputEditText)rootView.findViewById(R.id.et_telefono);

        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if(tarifa!=null && ofertaTactica!=null){
                  prepararTotal();
                }
            }
        });
        tvCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        tvReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etTelefono.getText().length()<9){
                    etTelefono.setError("introduce un nº de telefono valido");
                }else{
                    DatabaseReference reservas = FirebaseSingleton.getDatabase().getReference().child("reservas");
                    String key = reservas.push().getKey();
                    Reserva reserva = new Reserva();
                    List<UserInfo> providers = (List<UserInfo>) getUser().getProviderData();
                    for (UserInfo userInfo : providers) {
                        if (userInfo.getProviderId().equals("google.com")) {
                            reserva.setNombre(userInfo.getDisplayName());
                            if(userInfo.getPhotoUrl()!=null){
                                reserva.setUrlFoto(userInfo.getPhotoUrl().toString());
                            }
                        }
                    }
                    reserva.setEmail(getUser().getEmail());
                    reserva.setTelefono(etTelefono.getText().toString());
                    reserva.setFechaReserva(new Date().getTime());
                    reserva.setKeyTerminal(keyTerminal);
                    reserva.setKeyTarifa(keyTarifa);
                    reserva.setPagoUnico(pagoUnico);
                    reserva.setTarifa(tarifa.getTarifa());
                    mDatabase.child("reservas").child(key).setValue(reserva);
                    alertDialog.dismiss();
                    confirmationDialog();
                }
            }
        });
        alertDialog.setView(rootView);
        alertDialog.show();
    }

    private void confirmationDialog(){

        final AlertDialog.Builder builder= new AlertDialog.Builder(context);
        View rootView = getLayoutInflater().inflate(R.layout.confirmation_dialog, null);
        builder.setView(rootView);
        builder.setPositiveButton("Aceptar",null);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    protected void showHint() {

        View parentView = getWindow().getDecorView();
        new HintCase(parentView)
                .setTarget(findViewById(R.id.info_tarifa), new RectangularShape(), HintCase.TARGET_IS_NOT_CLICKABLE)
                .show();
    }
}
