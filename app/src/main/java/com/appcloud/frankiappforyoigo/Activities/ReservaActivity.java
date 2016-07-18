package com.appcloud.frankiappforyoigo.Activities;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.appcloud.frankiappforyoigo.Configuracion;
import com.appcloud.frankiappforyoigo.Fragments.ReservaDialogFragment;
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
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.List;

public class ReservaActivity extends BaseActivity {

    private Context context = this;
    private int codTarifa ,cuotaTerminal=0;
    private LinearLayout lnDetalleTarifa;
    private TextView tvTitle;
    private Tarifa tarifa;
    private String keyTerminal;
    private boolean pagoUnico;
    private OfertaTactica ofertaTactica;
    private Button btReserva;
    private boolean tarifaCargada = false;
    private boolean terminalCargado = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        keyTerminal = getIntent().getStringExtra(Configuracion.KEY_TERMINAL);
        pagoUnico = getIntent().getBooleanExtra(Configuracion.PAGO_UNICO,false);
        codTarifa = getIntent().getIntExtra(Configuracion.TARIFA,-1);
        if(codTarifa!=-1)
            setTheme(Commons.getTema(codTarifa));
        setContentView(R.layout.activity_reserva);

        lnDetalleTarifa = (LinearLayout)findViewById(R.id.ln_detalle_tarifa);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        btReserva = (Button)findViewById(R.id.bt_reserva);

        if(getIntent().getAction().equals(Configuracion.VER)){
            btReserva.setVisibility(View.GONE);
        }
        btReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reservar();
            }
        });

        prepararDatosOfertaTactica();
        prepararDatosTarifa();
    }

    private void prepararDatosTarifa() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("tarifas");
        switch (codTarifa){
            case Configuracion.TARIFA_CERO:
                ref.child("cero").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        tarifa = dataSnapshot.getValue(Tarifa.class);
                        setDetalleTarifa();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {}
                });
                tvTitle.setText(getString(R.string.tarifa_cero));
                break;
            case Configuracion.TARIFA_CINCO:
                ref.child("cinco").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        tarifa = dataSnapshot.getValue(Tarifa.class);
                        setDetalleTarifa();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {}
                });
                tvTitle.setText(getString(R.string.tarifa_cinco));
                break;
            case Configuracion.TARIFA_SINFIN:
                ref.child("sinfin").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        tarifa = dataSnapshot.getValue(Tarifa.class);
                        setDetalleTarifa();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {}
                });
                tvTitle.setText(getString(R.string.tarifa_sinfin));
                break;
            case Configuracion.TARIFA_UNO:
                ref.child("uno").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        tarifa = dataSnapshot.getValue(Tarifa.class);
                        setDetalleTarifa();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {}
                });
                tvTitle.setText(getString(R.string.tarifa_uno));
                break;
            case Configuracion.TARIFA_650:
                ref.child("650").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        tarifa = dataSnapshot.getValue(Tarifa.class);
                        setDetalleTarifa();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {}
                });
                tvTitle.setText(getString(R.string.tarifa_650));
                break;
        }
    }

    private void prepararDatosOfertaTactica(){
        if (keyTerminal != null) {
            DatabaseReference databaseReference = FirebaseSingleton.getDatabase().getReference();
            databaseReference.child("ofertas_tacticas").child(keyTerminal).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    ofertaTactica = dataSnapshot.getValue(OfertaTactica.class);
                    final ImageView ivFotoTerminal = (ImageView)findViewById(R.id.iv_terminal_foto);
                    Picasso.with(context)
                            .load(ofertaTactica.getFoto())
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
                                            .load(ofertaTactica.getFoto())
                                            .placeholder(R.drawable.phone_mockup)
                                            .error(R.drawable.phone_mockup)
                                            .into(ivFotoTerminal);
                                }
                            });
                    TextView tvNombreTerminal = (TextView)findViewById(R.id.tv_terminal_nombre);
                    tvNombreTerminal.setText(ofertaTactica.getTerminal());
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
            Drawable background = null;
            switch (codTarifa){
                case Configuracion.TARIFA_CERO:
                    cuotaTerminal = Integer.parseInt(Commons.tratarFinanciacion(ofertaTactica.getFinanciacionCero()));
                    tvPagoInicial.setText(Commons.tratarPrecio(ofertaTactica.getPagoInicialCero()));
                    tvPagoFinal.setText(Commons.tratarPrecio(ofertaTactica.getPagoFinalCero()));
                    background=getResources().getDrawable(R.drawable.background_cero);
                    if(pagoUnico){
                        tvPagoInicial.setText(Commons.tratarPrecio(ofertaTactica.getPagoUnicoCero()));
                        setPagoUnico();
                    }
                    break;
                case Configuracion.TARIFA_CINCO:
                    cuotaTerminal = Integer.parseInt(Commons.tratarFinanciacion(ofertaTactica.getFinanciacionCinco()));
                    tvPagoInicial.setText(Commons.tratarPrecio(ofertaTactica.getPagoInicialCinco()));
                    tvPagoFinal.setText(Commons.tratarPrecio(ofertaTactica.getPagoFinalCinco()));
                    background=getResources().getDrawable(R.drawable.background_cinco);
                    if(pagoUnico){
                        tvPagoInicial.setText(Commons.tratarPrecio(ofertaTactica.getPagoUnicoCinco()));
                        setPagoUnico();
                    }
                    break;
                case Configuracion.TARIFA_SINFIN:
                    cuotaTerminal = Integer.parseInt(Commons.tratarFinanciacion(ofertaTactica.getFinanciacionSinFin()));
                    tvPagoInicial.setText(Commons.tratarPrecio(ofertaTactica.getPagoInicialSinFin()));
                    tvPagoFinal.setText(Commons.tratarPrecio(ofertaTactica.getPagoFinalSinFin()));
                    background=getResources().getDrawable(R.drawable.background_sinfin);
                    if(pagoUnico){
                        tvPagoInicial.setText(Commons.tratarPrecio(ofertaTactica.getPagoUnicoSinFin()));
                        setPagoUnico();
                    }
                    break;
                case Configuracion.TARIFA_UNO:
                    tvPagoInicial.setText(Commons.tratarPrecio(ofertaTactica.getPagoUnicoPrepago()));
                    setPagoUnico();
                    background=getResources().getDrawable(R.drawable.background_cero);
                    break;
                case Configuracion.TARIFA_650:
                    tvPagoInicial.setText(Commons.tratarPrecio(ofertaTactica.getPagoUnicoPrepago()));
                    setPagoUnico();
                    background=getResources().getDrawable(R.drawable.background_cinco);
                    break;
            }
            tvCuotaTotal.setText(String.valueOf(cuotaTarifa+cuotaTerminal));
            tvCuotaTerminal.setText(String.valueOf(cuotaTerminal));
            findViewById(R.id.ln_pago_inicial).setBackground(background);
            findViewById(R.id.ln_cuota_total).setBackground(background);
            findViewById(R.id.ln_pago_final).setBackground(background);
            findViewById(R.id.bt_reserva).setBackground(background);
        }
    }

    private void  setDetalleTarifa(){
        LinearLayout lnCosteMinuto = (LinearLayout) findViewById(R.id.ln_coste_minuto);
        RelativeLayout rlIlimitadas = (RelativeLayout) findViewById(R.id.ln_ilimitadas);
        LinearLayout lnMinutos = (LinearLayout)findViewById(R.id.ln_minutos);
        TextView tvLlamadas = (TextView) findViewById(R.id.tv_llamadas);
        TextView tvMinnutos = (TextView) findViewById(R.id.tv_min_gratis);
        TextView tvDatos = (TextView) findViewById(R.id.tv_datos);
        TextView tvUnidadDatos = (TextView) findViewById(R.id.tv_unidad_datos);
        TextView tvCuota = (TextView) findViewById(R.id.tv_cuota);
        if(tarifa.isIlimitadas()){
            lnCosteMinuto.setVisibility(View.GONE);
        }else{
            rlIlimitadas.setVisibility(View.GONE);
            tvLlamadas.setText(tarifa.getPrecio_minuto());
            if(tarifa.getMinutos_gratis()!=null && !tarifa.getMinutos_gratis().equals(""))
            {
                tvMinnutos.setText(tarifa.getMinutos_gratis());
            }else{
                lnMinutos.setVisibility(View.INVISIBLE);
            }
        }
        if(tarifa.getUnidad_datos().equals("MB")){
            tvDatos.setTextSize(TypedValue.COMPLEX_UNIT_SP,35);
        }
        tvDatos.setText(tarifa.getDatos());
        tvUnidadDatos.setText(tarifa.getUnidad_datos());
        tvCuota.setText(tarifa.getCuota());

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
        lnDetalleTarifa.setVisibility(View.GONE);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if(tarifa!=null && ofertaTactica!=null){
          prepararTotal();
        }
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
                reserva.setTerminal(ofertaTactica.getTerminal());
                reserva.setKeyTerminal(keyTerminal);
                reserva.setCodTarifa(codTarifa);
                reserva.setPagoUnico(pagoUnico);
                reserva.setTarifa(tarifa.getTarifa());
                mDatabase.child("reservas").child(key).setValue(reserva);
                alertDialog.dismiss();
            }
        });
        alertDialog.setView(rootView);
        alertDialog.show();
    }
}
