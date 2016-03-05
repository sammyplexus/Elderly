package com.example.sammybobo.elderly;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Sammy bobo on 19/02/2016.
 */
public class Feedback extends Activity
{
    private Button send_feedback;
    private EditText message_feedback;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        send_feedback = (Button)findViewById(R.id.button_send_feedback);
        message_feedback = (EditText)findViewById(R.id.message_text_feedback);
        progressDialog = new ProgressDialog(this);

        send_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (message_feedback.getText().toString().isEmpty())
                {
                    Toast.makeText(Feedback.this, "Please enter your message", Toast.LENGTH_LONG).show();
                }
                else
                {
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressDialog.setTitle("Sending message");
                    progressDialog.setMessage("Hold on. We would get back to you as soon as we get your message");
                    progressDialog.show();
                }
            }
        });
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle("Sending message");
        progressDialog.setMessage("Hold on. We would get back to you as soon as we get your message");
        progressDialog.show();
        setContentView(R.layout.feedback);

    }
}
