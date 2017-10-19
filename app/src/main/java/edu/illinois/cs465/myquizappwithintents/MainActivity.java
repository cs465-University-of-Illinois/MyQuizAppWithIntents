package edu.illinois.cs465.myquizappwithintents;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {
    private static final int PEEK_ACTIVITY_REQUEST = 1;

    private Button falseButton;
    private Button trueButton;
    private Button peekButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        falseButton = (Button) findViewById(R.id.false_button);
        falseButton.setOnClickListener(this);

        trueButton = (Button) findViewById(R.id.true_button);
        trueButton.setOnClickListener(this);

        peekButton = (Button) findViewById(R.id.peek_button);
        peekButton.setOnClickListener(this);

    }

    public void onClick(View v) {
        if (v.getId() == R.id.false_button) {
            Toast.makeText(this, "CORRECT", Toast.LENGTH_SHORT).show();
        } else if (v.getId() == R.id.true_button) {
            Toast.makeText(this, "INCORRECT", Toast.LENGTH_SHORT).show();
        } else if (v.getId() == R.id.peek_button) {
            Uri webpage = Uri.parse("https://en.wikipedia.org/wiki/Springfield,_Illinois");
            Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(intent, PEEK_ACTIVITY_REQUEST);
            }

            /* Explicit intent
            Intent intent = new Intent(this, PeekActivity.class);
            startActivityForResult(intent, PEEK_ACTIVITY_REQUEST);
            */
        }
    }

    public void onActivityResult(int activityCode, int resultCode, Intent intent) {
        if (activityCode == PEEK_ACTIVITY_REQUEST) {
            if (resultCode == RESULT_OK) {
                String s = intent.getStringExtra(PeekActivity.ACTIVITY_RETURN_MSG);
                Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
