package com.example.nitish.remote_control;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class Sending_Activity extends Activity {
    private Socket socket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sending_);

        ImageButton slideup = (ImageButton)findViewById(R.id.up);
        ImageButton slidedown = (ImageButton)findViewById(R.id.down);
        Button connect = (Button)findViewById(R.id.connect);
        Button openpp = (Button)findViewById(R.id.openpp);
        ImageButton slideshow = (ImageButton)findViewById(R.id.Slideshow);
        ImageButton Exitslide = (ImageButton)findViewById(R.id.exitslide);


        final EditText filename = (EditText)findViewById(R.id.filename);
        final EditText address = (EditText)findViewById(R.id.ipadd);
        address.setText("192.168.1.");

        Exitslide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "exitslide;exit";
                new SendData().execute(message);
            }
        });
        slideshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "slideshow;show";
                new SendData().execute(message);
            }
        });
        openpp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "open_pp";
                new SendData().execute(message+";"+filename.getText().toString());
            }
        });
        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Connect().execute(address);
            }
        });
        slideup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String slide = "up;uparrow";
                new SendData().execute(slide);
            }
        });
        slidedown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String slide = "down;downarrow";
                new SendData().execute(slide);
            }
        });
    }
    public class Connect extends AsyncTask<EditText,Void,Void>{

        @Override
        protected Void doInBackground(EditText... params) {
            EditText editText = params[0];
            try {
                socket = new Socket(editText.getText().toString(), 4444);
            }
            catch (IOException ioe)
            {
                ioe.printStackTrace();
            }
            return null;
        }
    }
    public class SendData extends AsyncTask<String,Void,Void>{
        @Override
        protected Void doInBackground(String...args)
        {
            String value = args[0];
            try{
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                dos.writeUTF(value);
            }catch (IOException ioe)
            {
                ioe.printStackTrace();
            }
            return null;
        }
    }

}