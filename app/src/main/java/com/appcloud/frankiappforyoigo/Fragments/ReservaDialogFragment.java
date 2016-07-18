package com.appcloud.frankiappforyoigo.Fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.appcloud.frankiappforyoigo.R;

/**
 * Created by cristian on 18/07/2016.
 */
public class ReservaDialogFragment extends DialogFragment {
    private AlertDialog.Builder builder;
    private TextView tvReservar;
    private TextView tvCancelar;
    private View.OnClickListener clickReservar;
    private View.OnClickListener clickCancelar;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        builder = new AlertDialog.Builder(getActivity());
        // Create the AlertDialog object and return it
        return builder.create();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.reserva_dialog, container, false);
        tvReservar = (TextView) rootView.findViewById(R.id.tv_reservar);
        tvCancelar = (TextView)rootView.findViewById(R.id.tv_cancelar);
       return rootView;
    }



    public void setDismissListener(DialogInterface.OnDismissListener listener)
    {
        builder.setOnDismissListener(listener);
    }

    public void setCancelarClickListener(View.OnClickListener listener)
    {
        tvCancelar.setOnClickListener(listener);
    }
    public void setReservarClickListener(View.OnClickListener listener)
    {
        tvReservar.setOnClickListener(listener);
    }
}