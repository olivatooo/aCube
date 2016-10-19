package acube.acube.com;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by aluno on 14/04/2016.
 */

public class Questions extends Activity {
    int ambiesp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questionsnario);
        getActionBar().setDisplayShowTitleEnabled(true);
        getActionBar().setTitle(R.string.perfil);
        final SeekBar sk=(SeekBar) findViewById(R.id.horas);
        final TextView t1 = (TextView)findViewById(R.id.hora_text);
        final Spinner ambiente=(Spinner)findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.ambiente, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ambiente.setAdapter(adapter);
        final Spinner especific=(Spinner)findViewById(R.id.especificacoes);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.hospitais, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        especific.setAdapter(adapter2);
        ambiente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String item = ambiente.getSelectedItem().toString();
                item = item.trim();


                if (item.equals("Hospital")) {

                    ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(parentView.getContext(), R.array.hospitais, android.R.layout.simple_spinner_dropdown_item);
                    especific.setAdapter(adapter2);
                    Utils.ambiente=1;

                }
                if (item.equals("Escolas")) {

                    ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(parentView.getContext(), R.array.escolas, android.R.layout.simple_spinner_dropdown_item);
                    especific.setAdapter(adapter2);
                    Utils.ambiente=2;

                }
                if (item.equals("Hotéis")) {

                    ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(parentView.getContext(), R.array.hoteis, android.R.layout.simple_spinner_dropdown_item);
                    especific.setAdapter(adapter2);
                    Utils.ambiente=3;

                }
                if (item.equals("Residência")) {

                    ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(parentView.getContext(), R.array.residencia, android.R.layout.simple_spinner_dropdown_item);
                    especific.setAdapter(adapter2);
                    Utils.ambiente=4;
                }
                if (item.equals("Restaurantes")) {

                    ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(parentView.getContext(), R.array.restaurantes, android.R.layout.simple_spinner_dropdown_item);
                    especific.setAdapter(adapter2);
                    Utils.ambiente=5;
                }
                if (item.equals("Auditórios")) {

                    ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(parentView.getContext(), R.array.auditorios, android.R.layout.simple_spinner_dropdown_item);
                    especific.setAdapter(adapter2);
                    Utils.ambiente=6;
                }
                if (item.equals("Escritórios")) {

                    ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(parentView.getContext(), R.array.escritorios, android.R.layout.simple_spinner_dropdown_item);
                    especific.setAdapter(adapter2);
                    Utils.ambiente=7;
                }
                if (item.equals("Igrejas e Templos")) {

                    ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(parentView.getContext(), R.array.igreja, android.R.layout.simple_spinner_dropdown_item);
                    especific.setAdapter(adapter2);
                    Utils.ambiente=8;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });





        sk.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekBar.setAlpha(1);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                seekBar.setAlpha(0.5f);
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            t1.setText(""+progress);


            }
        });
    }


    public void SaveAndContinue(View view)
    {
        try {
       final Intent intent = new Intent(this, MenuPrincipal.class);
        EditText nome = (EditText)findViewById(R.id.editText);
        EditText idade = (EditText)findViewById(R.id.editText2);
        RadioGroup sexo = (RadioGroup)findViewById(R.id.radiosex);
        RadioGroup alergias = (RadioGroup)findViewById(R.id.radioGroup);
        RadioGroup Ler = (RadioGroup)findViewById(R.id.radioler);
        RadioGroup lente = (RadioGroup)findViewById(R.id.radiolente);
        Spinner ambiente = (Spinner)findViewById(R.id.especificacoes);
        SeekBar horas = (SeekBar)findViewById(R.id.horas);

        int radioButtonID = sexo.getCheckedRadioButtonId();
        View radioButton = sexo.findViewById(radioButtonID);
        int idx = sexo.indexOfChild(radioButton);
        String all=idx+"#";

        radioButtonID = alergias.getCheckedRadioButtonId();
        radioButton = alergias.findViewById(radioButtonID);
        idx = alergias.indexOfChild(radioButton);
        all=all+idx+"#";

        radioButtonID = Ler.getCheckedRadioButtonId();
        radioButton = Ler.findViewById(radioButtonID);
        idx = Ler.indexOfChild(radioButton);
        all=all+idx+"#";

        radioButtonID = lente.getCheckedRadioButtonId();
        radioButton = lente.findViewById(radioButtonID);
        idx = lente.indexOfChild(radioButton);
        all=all+idx+"#";

        Utils.idade=Integer.valueOf(idade.getText().toString());

        Utils.lux_ideal=Verificacao.lux_ideal(0,Utils.idade,1);
        Utils.db_ideal=45;



            final SaveandLoad a = new SaveandLoad(nome.getText().toString(), Integer.valueOf(idade.getText().toString()), all, ambiente.getSelectedItem().toString(), horas.getProgress());

            new AlertDialog.Builder(this)
                    .setTitle(R.string.a)
                    .setMessage(a.toString())
                    .setPositiveButton(R.string.b, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {


                            startActivity(intent);
                        }
                    })
                    .setNegativeButton(R.string.c, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();

        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), R.string.d,Toast.LENGTH_LONG).show();
        }



    }
}