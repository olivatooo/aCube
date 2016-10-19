package acube.acube.com;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by user on 14/06/2016.
 */
public class MenuPrincipal extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menuprincipal);
        ActionBar actionBar = getActionBar();
        actionBar.setCustomView(R.layout.abs_layout);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
    }

    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(this)
                .setTitle("Sair")
                .setMessage("Você deseja realmente fechar o aplicativo?")
                .setPositiveButton(R.string.e, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        System.exit(0);
                    }
                })
                .setNegativeButton(R.string.f, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }


    public void ToCondicoes(View view)
    {
        Intent intent = new Intent(this, AmbientValues.class);
        startActivity(intent);
        this.overridePendingTransition(R.anim.animation_leave, R.anim.animation_enter);
    }
    public void ToTime(View view)
    {
        Intent intent = new Intent(this, BreakClock.class);
        startActivity(intent);
        this.overridePendingTransition(R.anim.animation_leave, R.anim.animation_enter);
    }
    public void ToQuestion(View view)
    {
        Intent intent = new Intent(this, Questions.class);
        startActivity(intent);
        this.overridePendingTransition(R.anim.animation_leave, R.anim.animation_enter);
    }
    public void toAbout(View view)
    {
        Utils.help = true;
        Intent intent = new Intent(this,Visual.class);
        startActivity(intent);
        this.overridePendingTransition(R.anim.animation_leave, R.anim.animation_enter);
    }
    public void toAjuda(View view)
    {
        Utils.help = false;
        Intent intent = new Intent(this,Visual.class);
        startActivity(intent);
        this.overridePendingTransition(R.anim.animation_leave, R.anim.animation_enter);
    }


}
