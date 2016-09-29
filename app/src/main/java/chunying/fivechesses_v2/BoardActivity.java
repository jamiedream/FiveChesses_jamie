package chunying.fivechesses_v2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;


public class BoardActivity extends AppCompatActivity {
    private Chess img;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        img = (Chess) findViewById(R.id.img);

    }


    public void home(View view) {
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);

    }

    public void zoomin(View view) {

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) img.getLayoutParams();
        params.height = 1200;
        img.requestLayout();

    }
    public void zoomout(View view) {

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) img.getLayoutParams();
        params.height = 1050;
        img.requestLayout();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Music.play(this, R.raw.music);
    }
    @Override
    protected void onPause() {
        super.onPause();
        Music.stop(this);
    }




}
