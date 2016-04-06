package com.danielsapps.packbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.danielsapps.model.EmailAndPasswordBean;
import com.danielsapps.packbuddycontroller.SendEmailAndPassword;

import java.util.concurrent.ExecutionException;

/**
 * @author daniel
 */
public class LogIn extends AppCompatActivity {
    String info="LogIn info: ";
    TextView tvEmail;
    TextView tvPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tvEmail = (TextView) findViewById(R.id.logInEmail);
        tvPassword = (TextView) findViewById(R.id.logInPassword);

    }

    public void submitEmailAndPassword(View v){
        Log.d(info, "In submitEmailAndPassword()");
        EmailAndPasswordBean epb = new EmailAndPasswordBean(tvEmail.getText().toString(), tvPassword.getText().toString());
        SendEmailAndPassword sep = new SendEmailAndPassword(epb);
        sep.execute();
        try {
            if(sep.get()){
                Intent i = new Intent(this, HostelSearch.class);
                i.putExtra("email",SendEmailAndPassword.getPfb().getEmail());
                startActivity(i);
            }
            else{
                Toast t = Toast.makeText(this,"Email or password is not valid",Toast.LENGTH_SHORT);
                t.show();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
