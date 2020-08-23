package com.example.modul4implisitintent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private EditText phoneNumber;
    private EditText webUri;
    private EditText locUri;
    private EditText shareText;
    private Button btnWeb;
    private Button btnLoc;
    private Button btnText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phoneNumber = (EditText) findViewById(R.id.edt_phone_number);
        webUri = (EditText) findViewById(R.id.edt_web_url);
        locUri = (EditText) findViewById(R.id.edt_lokasi);
        shareText = (EditText) findViewById(R.id.edt_text);

        btnWeb = (Button) findViewById(R.id.btn_web_url);
        btnWeb.setOnClickListener(this);

        btnWeb = (Button) findViewById(R.id.btn_lokasi);
        btnWeb.setOnClickListener(this);

        btnWeb = (Button) findViewById(R.id.btn_share_text);
        btnWeb.setOnClickListener(this);
    }

    public void DialPhone(View view)
    {
        if( TextUtils.isEmpty(phoneNumber.getText()))
        {
            Toast.makeText(getApplicationContext(),"MASUKKAN NOMOR HANDPHONE", Toast.LENGTH_LONG).show();
        }else{
            Intent dialPhone = new Intent(Intent.ACTION_DIAL);
            dialPhone.setData(Uri.parse("tel:"+ phoneNumber.getText().toString()));
            startActivity(dialPhone);
        }
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.btn_web_url:
                if( TextUtils.isEmpty(phoneNumber.getText()))
                {
                    Toast.makeText(getApplicationContext(),"MASUKKAN pencarian browser", Toast.LENGTH_LONG).show();
                }else
                    {
                    Intent openWebsite = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=" + webUri.getText().toString()));
                    startActivity(openWebsite);
                    }
                 break;
            case R.id.btn_lokasi:
                if( TextUtils.isEmpty(phoneNumber.getText()))
                {
                    Toast.makeText(getApplicationContext(),"MASUKKAN lokasi", Toast.LENGTH_LONG).show();
                }else
                    {
                    Intent openLocation = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + locUri.getText().toString()));
                    startActivity(openLocation);
                    }
                break;
            case R.id.btn_share_text:
                if( TextUtils.isEmpty(phoneNumber.getText()))
                {
                    Toast.makeText(getApplicationContext(),"MASUKKAN text", Toast.LENGTH_LONG).show();
                }else {
                    ShareCompat.IntentBuilder
                            .from(this)
                            .setType("text/plan")
                            .setChooserTitle("Share this text with : ")
                            .setText(shareText.getText().toString())
                            .startChooser();
                    }
                break;
        }
    }
}