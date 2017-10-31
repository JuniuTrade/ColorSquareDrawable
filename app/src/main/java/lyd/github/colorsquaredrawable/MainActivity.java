package lyd.github.colorsquaredrawable;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import lyd.github.library.ColorSquareDrawable;

public class MainActivity extends AppCompatActivity {

    private ImageView image1;
    private ImageView image2;
    private ImageView image3;
    private ImageView image4;
    private ImageView image5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image1 = (ImageView) findViewById(R.id.image1);
        image2 = (ImageView) findViewById(R.id.image2);
        image3 = (ImageView) findViewById(R.id.image3);
        image4 = (ImageView) findViewById(R.id.image4);
        image5 = (ImageView) findViewById(R.id.image5);

        image1.setImageDrawable(new ColorSquareDrawable(ContextCompat.getColor(this, R.color.colorAccent)));
        image2.setImageDrawable(new ColorSquareDrawable(ContextCompat.getColor(this, R.color.colorPrimary), false));
        image3.setImageDrawable(new ColorSquareDrawable(ContextCompat.getColor(this, R.color.colorPrimaryDark), 50));
        image4.setImageDrawable(new ColorSquareDrawable(ContextCompat.getColor(this, R.color.colorAccent),40, 0, 80, 0));
        image5.setImageDrawable(new ColorSquareDrawable(ContextCompat.getColor(this, R.color.colorPrimaryDark), 70, 70, 0, 70));
    }
}
