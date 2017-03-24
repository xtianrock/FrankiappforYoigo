package com.appcloud.frankiappforyoigo.Utils;

import com.appcloud.frankiappforyoigo.Config;
import com.appcloud.frankiappforyoigo.R;

/**
 * Created by cristian on 13/06/2016.
 */
public class Commons {

    /*public static ArrayList<OfertaTactica> importarDatos(Context context) {
        String filename = "oferta_tactica_julio.csv";
        ArrayList<OfertaTactica> ofertas = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(context.getAssets().open(filename)));

            String mLine;
            String ultimoTerminal = "";
            int position = -1;
            while ((mLine = reader.readLine()) != null) {
                String[] str = mLine.split(";", -1);
                if(!str[0].equals(ultimoTerminal))
                {
                    position++;
                    OfertaTactica ofertaTactica = new OfertaTactica();
                    ofertaTactica.setActivo(true);
                    //ofertaTactica.setTerminal(str[0]);
                    ofertaTactica.setPvprAltaNueva(str[2]);
                    ofertaTactica.setPagoUnicoCero(str[3]);
                    if(str[3].equals("0,00 €")){
                        ofertaTactica.setPagoInicialCero(str[3]);
                        ofertaTactica.setCuotaCero("0*24");
                        ofertaTactica.setPagoFinalCero(str[3]);
                    }
                    ofertaTactica.setPagoUnicoCinco(str[4]);
                    if(str[4].equals("0,00 €")){
                        ofertaTactica.setPagoInicialCinco(str[4]);
                        ofertaTactica.setCuotaCinco("0*24");
                        ofertaTactica.setPagoFinalCinco(str[4]);
                    }
                    ofertaTactica.setPagoUnicoSinFin(str[5]);
                    if(str[5].equals("0,00 €")){
                        ofertaTactica.setPagoInicialSinFin(str[5]);
                        ofertaTactica.setCuotaSinFin("0*24");
                        ofertaTactica.setPagoFinalSinFin(str[5]);
                    }
                    ofertaTactica.setPvprPrepago(str[6]);

                    ofertas.add(ofertaTactica);
                    ultimoTerminal = str[0];
                }else{
                    if(!str[3].equals(""))
                    {
                        ofertas.get(position).setPagoInicialCero(str[3]);
                        ofertas.get(position).setCuotaCero(str[1]);
                        if(str[7].equals("")){
                            ofertas.get(position).setPagoFinalCero("0,00 €");
                        }else{
                            ofertas.get(position).setPagoFinalCero(str[7]);
                        }
                    }
                    if (!str[4].equals(""))
                    {
                        ofertas.get(position).setPagoInicialCinco(str[4]);
                        ofertas.get(position).setCuotaCinco(str[1]);
                        if(str[8].equals("")){
                            ofertas.get(position).setPagoFinalCinco("0,00 €");
                        }else{
                            ofertas.get(position).setPagoFinalCinco(str[8]);
                        }
                    }
                    if (!str[5].equals(""))
                    {
                        ofertas.get(position).setPagoInicialSinFin(str[5]);
                        ofertas.get(position).setCuotaSinFin(str[1]);
                        if(str[9].equals("")){
                            ofertas.get(position).setPagoFinalSinFin("0,00 €");
                        }else{
                            ofertas.get(position).setPagoFinalSinFin(str[9]);
                        }
                    }
                }
            }


            Log.i("import", "tarifas importadas con exito");
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return ofertas;
    }*/

    public static String tratarPrecio(String precio)
    {
        String[] precioSplit = precio.split(",");
        return precioSplit[0];
    }
    public static String tratarFinanciacion(String precio)
    {
        String[] precioSplit = precio.split("\\*");
        return precioSplit[0];
    }

    public static int getTema(String estado)
    {
        switch (estado)
        {
            case Config.TARIFA_CERO:
                return R.style.AppTheme_NoActionBar_cero;
            case Config.TARIFA_CINCO:
                return R.style.AppTheme_NoActionBar_cinco;
            case Config.TARIFA_INFINITA:
                return R.style.AppTheme_NoActionBar_infinita;
            case Config.TARIFA_SINFIN:
                return R.style.AppTheme_NoActionBar_sinfin;
            case Config.COMBINADA_NARANJA_50:
                return R.style.AppTheme_NoActionBar_cero;
            case Config.COMBINADA_VERDE_50:
                return R.style.AppTheme_NoActionBar_cinco;
            case Config.COMBINADA_MORADA_50:
                return R.style.AppTheme_NoActionBar_infinita;
            case Config.COMBINADA_AZUL_50:
                return R.style.AppTheme_NoActionBar_sinfin;
            case Config.COMBINADA_NARANJA_300:
                return R.style.AppTheme_NoActionBar_cero;
            case Config.COMBINADA_VERDE_300:
                return R.style.AppTheme_NoActionBar_cinco;
            case Config.COMBINADA_MORADA_300:
                return R.style.AppTheme_NoActionBar_infinita;
            case Config.COMBINADA_AZUL_300:
                return R.style.AppTheme_NoActionBar_sinfin;
            case Config.TARIFA_UNO:
                return R.style.AppTheme_NoActionBar_cero;
            case Config.TARIFA_650:
                return R.style.AppTheme_NoActionBar_cinco;
            default:
                return R.style.AppTheme_NoActionBar_unico;
        }
    }
}
