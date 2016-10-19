package acube.acube.com;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Set;

/**
 * Created by aCube Team on 28/04/2016.
 */
public class AmbientValues extends Activity {


    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.condicoes_ambientes);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(R.string.i);
        textView = (TextView)findViewById(R.id.textView13);

        new AlertDialog.Builder(this)
                .setTitle("Conectar")
                .setMessage("Você não está conectado a um aCube. \n Deseja procurar um e se conectar?")
                .setPositiveButton(R.string.e, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    EncotraEConecta();
                    }
                })
                .setNegativeButton(R.string.f, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
        ReadBuffer();



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_ambi, menu);
        return true;
    }
    public void gototemp(View view)
    {
        if (ToothReadWrite.statusTooth()) {
            Intent intent = new Intent(this, GraficosT.class);
            startActivity(intent);
            overridePendingTransition(R.anim.animation_leave, R.anim.animation_enter);
        }
    }
    public void gotolux(View view)
    {
        if (ToothReadWrite.statusTooth()) {
            Intent intent = new Intent(this, GraficosL.class);
            startActivity(intent);
            overridePendingTransition(R.anim.animation_leave, R.anim.animation_enter);
        }
    }
    public void gotohumi(View view)
    {
        if (ToothReadWrite.statusTooth()) {
            Intent intent = new Intent(this, GraficosH.class);
            startActivity(intent);
            overridePendingTransition(R.anim.animation_leave, R.anim.animation_enter);
        }
    }
    public void gotosom(View view)
    {
        if (ToothReadWrite.statusTooth()) {
            Intent intent = new Intent(this, Graficos.class);
            startActivity(intent);
            overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {

            case R.id.conecta:
            {
                EncotraEConecta();
                break;
            }






        }
        return true;
    }
    public void EncotraEConecta()
    {
        if (ToothReadWrite.statusTooth() != true) {
            liga_bluetooth();
        } else {


            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Dispositivos Pareados");


            final EditText input = new EditText(this);


            int i = 0;
            Set<BluetoothDevice> pareados = ToothReadWrite.Pareados();
            if (pareados.size() > 0) {
                for (BluetoothDevice device : pareados) {
                    i++;
                }
            }

            String address[] = new String[i];
            final String names[] = new String[i];
            i = 0;
            if (pareados.size() > 0) {
                for (BluetoothDevice device : pareados) {
                    names[i] = device.getName() + "#" + device.getAddress();
                    i++;
                }
            }
            builder.setItems(names, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                    dialog.dismiss();
                    String[] mac = names[which].toString().split("#");
                    try {

                        ToothReadWrite.Connect(mac[1].trim());



                    } catch (Exception e) {

                    }

                }

            });


            builder.show();

        }

    }

    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(this)
                .setTitle(R.string.h)
                .setMessage(R.string.g)
                .setPositiveButton(R.string.e, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        ToothReadWrite.disconnect();
                    }
                })
                .setNegativeButton(R.string.f, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

        super.onBackPressed();
    }

    public void liga_bluetooth() {
        if (!ToothReadWrite.statusTooth()) {
            Intent liga_blu_intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(liga_blu_intent, 1);
        }

    }


    public void ReadBuffer() {
        final TextView temp = (TextView) findViewById(R.id.displayTemp);
        final TextView lux = (TextView) findViewById(R.id.displayLight);
        final TextView umidade = (TextView) findViewById(R.id.displayAir);
        final TextView som = (TextView) findViewById(R.id.displaySom);
        new CountDownTimer(1000000, 1000) {
            public void onTick(long millisUntilFinished) {
                try {
                    String[] separated = ToothReadWrite.ReadBuffer().toString().trim().split("#");
                    if(separated[0].trim().contains("temp"))
                        {
                            temp.setText(separated[1]+"ºC");
                            Utils.temp=separated[1];
                        }

                    if(separated[2].trim().contains("luz"))
                        {
                            lux.setText(separated[3]+" Lux");
                            Utils.lux=separated[3];
                        }

                    if(separated[4].trim().contains("humi"))
                        {
                            umidade.setText(separated[5]+"%");
                            Utils.humi=separated[5];
                        }

                  if (separated[6].trim().contains("som"))
                    {
                        som.setText(Double.valueOf(separated[7])+" Db");
                        Utils.som=separated[7];
                    }
                    Verificacao verificacao = new Verificacao(separated);
                    textView.setText(verificacao.Status());
                    Verificacao v = new Verificacao(separated);
                    textView.setText(v.Status());
                } catch (Exception e) {
                    Log.v("ERRO:",""+e.toString());

                }


            }

            public void onFinish() {
                ReadBuffer();
            }
        }.start();

    }

}
