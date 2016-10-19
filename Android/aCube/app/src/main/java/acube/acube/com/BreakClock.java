package acube.acube.com;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;


public class BreakClock extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contador);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.condambi:
            {

                Intent intent = new Intent(this, AmbientValues.class);
                startActivity(intent);
                break;
            }
            case R.id.conecta:
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
                    String names[] = new String[i];
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
                            switch (which) {
                                case 0:

                                    break;
                                case 1:


                                    break;
                            }
                        }

                    });


                    builder.show();

                }

                break;
            }






        }
        return true;
    }
    public  void IniciarTrampo(View view)
    {
        final GregorianCalendar gcalendar = new GregorianCalendar();
        final ArcProgress a = (ArcProgress)findViewById(R.id.arc_progress);
        a.setBottomText("FIM DO EXPEDIENTE");
        final TextView end = (TextView)findViewById(R.id.fim_exp);
        final TextView fim = (TextView)findViewById(R.id.tempo_rest);

        //fim.setText(gcalendar.get(Calendar.HOUR) + SaveandLoad.horas + ":" + gcalendar.get(Calendar.MINUTE) + ":" + gcalendar.get(Calendar.SECOND));
       // final int hora = (gcalendar.get(Calendar.HOUR))+SaveandLoad.horas;
        final int min = (gcalendar.get(Calendar.MINUTE));
        final int sec = (gcalendar.get(Calendar.SECOND));
        new CountDownTimer(SaveandLoad.horas*60*60*1000, 1000) {
            int i=0;
            public void onTick(long millisUntilFinished) {
                a.setProgress(i++);
                end.setText(gcalendar.get(Calendar.HOUR) + SaveandLoad.horas + ":" + gcalendar.get(Calendar.MINUTE) + ":" + gcalendar.get(Calendar.SECOND));

            }

            public void onFinish() {
                end.setText("HORA DE IR EMBORA!");
                fim.setText("HORA DE IR EMBORA!");
            }

        }.start();

    }
    public void liga_bluetooth() {
        if (!ToothReadWrite.statusTooth()) {
            Intent liga_blu_intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(liga_blu_intent, 1);
        }

    }



}