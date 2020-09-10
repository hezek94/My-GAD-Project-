package com.s.mygadproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.s.mygadproject.save.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmitActivity<SPLASH_TIME_OUT> extends AppCompatActivity {
    private final String Tag = "show outPut";
    ApiInterface apiInterface;
    EditText fName_etxt, lName_etxt, email_etxt, gitLink_etxt;
    ImageView submit_img, back_tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        apiInterface = APIGAD.getClient().create(ApiInterface.class);
        initializeEntriesIds();
        backToLeaderAct();
        submit_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmSummbmision();

//                submitProject(email_etxt.getText().toString(),
//                        fName_etxt.getText().toString(),
//                        lName_etxt.getText().toString(),
//                        gitLink_etxt.getText().toString());
            }
//            private void submitProject(String email, String fname, String lName, String github) {
//                Log.i(Tag, "submitProject: in progress ");
//                Call call = apiInterface.submitProject(email, fname,lName,github);
//                call.enqueue(new Callback() {
//                    @Override
//                    public void onResponse(Call call, Response response) {
//                        Log.i(Tag, "successful"+response.isSuccessful());
//                        Toast.makeText(SubmitActivity.this, "is correct", Toast.LENGTH_SHORT).show();
//
//                    }
//
//                    @Override
//                    public void onFailure(Call call, Throwable t) {
//                        Log.i(Tag, "Failure"+t.getLocalizedMessage());
//                    }
//                });
//
//               Log.i(Tag, "submitProject:complete ");
//
//            }
        });
    }

    private void confirmSummbmision() {
        final AlertDialog alertDialog;
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view = layoutInflater.inflate(R.layout.submit_dialogue, null);
        Button yes_btn = (Button) view.findViewById(R.id.btn_yes);
        ImageView cancel_img = (ImageView) view.findViewById(R.id.cancel);

        alertDialog = new AlertDialog.Builder(this)
                .setView(view)
                .create();
        alertDialog.show();

        yes_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitProject(email_etxt.getText().toString(),
                        fName_etxt.getText().toString(),
                        lName_etxt.getText().toString(),
                        gitLink_etxt.getText().toString());
                alertDialog.dismiss();

            }

        });

        cancel_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.dismiss();
            }
        });
    }

    private void dialogFailure() {
        final AlertDialog alertDialog;
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view = layoutInflater.inflate(R.layout.dial_failure, null);

        alertDialog = new AlertDialog.Builder(this)
                .setView(view)
                .create();
        alertDialog.show();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                alertDialog.dismiss();
            }

        }, 3000);
    }
    private void SuccessDialogue() {
        final AlertDialog alertDialog;
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view = layoutInflater.inflate(R.layout.success_dial, null);

        alertDialog = new AlertDialog.Builder(this)
                .setView(view)
                .create();
        alertDialog.show();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                alertDialog.dismiss();
            }

        }, 3000);
    }

    private void backToLeaderAct() {
        back_tab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SubmitActivity.this, LeadersBoardActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initializeEntriesIds() {
        fName_etxt = (EditText) findViewById(R.id.fname);
        lName_etxt = (EditText) findViewById(R.id.lname);
        email_etxt = (EditText) findViewById(R.id.email);
        gitLink_etxt = (EditText) findViewById(R.id.github_link);
        submit_img = (ImageView) findViewById(R.id.submit);
        back_tab = (ImageView) findViewById(R.id.back_tab);
    }

    private void submitProject(String email, String fname, String lName, String github) {
        Log.i(Tag, "submitProject: in progress ");
        Call call = apiInterface.submitProject(email, fname, lName, github);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {

                Log.i(Tag, "successful" + response.isSuccessful());
                Toast.makeText(SubmitActivity.this, "is correct", Toast.LENGTH_SHORT).show();
                SuccessDialogue();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.i(Tag, "Failure" + t.getLocalizedMessage());
                dialogFailure();
            }
        });

        Log.i(Tag, "submitProject:complete ");
        clean();

    }
    private void clean() {
        email_etxt.setText("");
        fName_etxt.setText("");
        lName_etxt.setText("");
        gitLink_etxt.setText("");
    }

}