package chunying.fivechesses_v2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {
    public static boolean isPlay;
    private ImageView setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setting = (ImageView)findViewById(R.id.setting);
    }

    public void newgame(View v) {
        Intent it = new Intent(this, BoardActivity.class);
        startActivity(it);
    }

    public void setting(View v) {
        isPlay = !isPlay;
        setting.setImageResource(isPlay?R.drawable.mute:R.drawable.sound);
//        isPlay?R.drawable.mute:R.drawable.sound;
        if (!isPlay) {
            Music.play(this, R.raw.music);
            Toast.makeText(MainActivity.this,"Sound On",Toast.LENGTH_SHORT).show();
        } else {
            Music.stop(this);
            Toast.makeText(MainActivity.this,"Sound Off",Toast.LENGTH_SHORT).show();
        }
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

    @Override
    public void finish() {
            Music.stop(this);
            super.finish();
        }
    }



