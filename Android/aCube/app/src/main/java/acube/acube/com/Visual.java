package acube.acube.com;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by olivato on 05/10/16.
 */
public class Visual extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!Utils.help)
        {
            setContentView(R.layout.ajuda);
        }
        else
        {
            setContentView(R.layout.sobre);
        }
    }
}
