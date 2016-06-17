package com.appcloud.frankiappforyoigo.Utils;

import android.content.Context;
import android.util.Log;

import com.appcloud.frankiappforyoigo.POJO.OfertaTactica;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by cristian on 13/06/2016.
 */
public class Commons {

    public static ArrayList<OfertaTactica> importarDatos(Context context) {
        String filename = "oferta_tactica.csv";
        ArrayList<OfertaTactica> ofertas = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(context.getAssets().open(filename)));

            String mLine;
            String ultimoTerminal = "";
            int position = -1;
            while ((mLine = reader.readLine()) != null) {
                String[] str = mLine.split("@", -1);
                if(!str[0].equals(ultimoTerminal))
                {
                    position++;
                    OfertaTactica ofertaTactica = new OfertaTactica();
                    ofertaTactica.setTerminal(str[0]);
                    ofertaTactica.setFoto("");
                    ofertaTactica.setPvp(str[2]);
                    ofertaTactica.setPagoUnicoCero(str[3]);
                    ofertaTactica.setPagoUnicoCinco(str[4]);
                    ofertaTactica.setPagoUnicoSinFin(str[5]);
                    ofertaTactica.setPagoUnicoPrepago(str[6]);

                    ofertas.add(ofertaTactica);
                    ultimoTerminal = str[0];
                }
                else
                {
                    if(!str[3].equals(""))
                    {
                        ofertas.get(position).setPagoInicialCero(str[3]);
                        ofertas.get(position).setFinanciacionCero(str[1]);
                        ofertas.get(position).setPagoFinalCero(str[7]);
                    }
                    if (!str[4].equals(""))
                    {
                        ofertas.get(position).setPagoInicialCinco(str[4]);
                        ofertas.get(position).setFinanciacionCinco(str[1]);
                        ofertas.get(position).setPagoFinalCinco(str[8]);
                    }
                    if (!str[5].equals(""))
                    {
                        ofertas.get(position).setPagoInicialSinFin(str[5]);
                        ofertas.get(position).setFinanciacionSinFin(str[1]);
                        ofertas.get(position).setPagoFinalSinFin(str[9]);
                    }
                }
            }


            Log.i("import", "tarifas importadas con exito");
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return ofertas;
    }
}
