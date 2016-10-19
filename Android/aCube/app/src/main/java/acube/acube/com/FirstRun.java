package acube.acube.com;


import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FirstRun extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle("aCube");

        // CONFIGS PÓS COTUCA
        /*try
        {
            File file = new File(getFilesDir()+"/config.xml");
            if(file.exists())
            {
                Intent intent = new Intent(this, MenuPrincipal.class);
                startActivity(intent);
            }
        }catch (Exception e){}*/

    }
    public void ToQuestionario(View view)
    {
        Intent intent = new Intent(this, Questions.class);
        startActivity(intent);
        this.overridePendingTransition(R.anim.animation_leave, R.anim.animation_enter);
    }

}
