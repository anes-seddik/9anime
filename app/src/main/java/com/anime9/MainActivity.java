package com.anime9;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputBinding;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        EditText search = findViewById(R.id.search);
        ImageButton icon = findViewById(R.id.srch_icon);
        Button up_login = findViewById(R.id.up_login_btn);
        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String anime_value = search.getText().toString();
                if (!anime_value.isEmpty()) {
                    Intent i = new Intent(MainActivity.this, list.class);
                    startActivity(i);
                }
                else search.requestFocus();
                return;
            }
        });

        SharedPreferences log =getSharedPreferences("log_status",Context.MODE_PRIVATE);
        SharedPreferences sp =getSharedPreferences("user_info",Context.MODE_PRIVATE);
        Button home = findViewById(R.id.go_home);
        Dialog login = new Dialog(MainActivity.this);
        login.setContentView(R.layout.login_dialog);
        login.setCanceledOnTouchOutside(false);
        login.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_shape));
        EditText email = login.findViewById(R.id.email_address);
        EditText pass = login.findViewById(R.id.password);
        TextInputLayout email_layout = login.findViewById(R.id.email_layout);
        TextInputLayout pass_layout = login.findViewById(R.id.pass_layout);


        ImageButton profile = findViewById(R.id.profile);
        TextView register = login.findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login.dismiss();
                Dialog registration = new Dialog(MainActivity.this);
                registration.setContentView(R.layout.registration_dialog);
                registration.setCanceledOnTouchOutside(false);
                registration.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_shape));
                ImageButton close2 = registration.findViewById(R.id.close2);
                Button register = registration.findViewById(R.id.register_btn);
                EditText user = registration.findViewById(R.id.user_name);
                EditText email = registration.findViewById(R.id.email_address);
                EditText pass1 = registration.findViewById(R.id.password);
                EditText pass2 = registration.findViewById(R.id.password2);
                TextView sign_in = registration.findViewById(R.id.sign_in);
                TextInputLayout email_layout = registration.findViewById(R.id.email_layout);
                TextInputLayout name_layout = registration.findViewById(R.id.name_layout);
                TextInputLayout pass1_layout = registration.findViewById(R.id.pass_layout);
                TextInputLayout pass2_layout = registration.findViewById(R.id.pass2_layout);
                sign_in.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        registration.dismiss();
                        login.show(); }
                });
                close2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        registration.dismiss();
                    }
                });
                register.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String user_val = user.getText().toString().trim();
                        String R_email_val = email.getText().toString().trim();
                        String pass1_val = pass1.getText().toString().trim();
                        String pass2_val = pass2.getText().toString().trim();
                        if (user_val.isEmpty()) {
                            name_layout.setError("enter a user name please.."); }
                        else if (user_val.length()<4){
                            name_layout.setError("your user name is too short..");
                        }
                        else {
                            name_layout.setErrorEnabled(false);
                        }

                        if (R_email_val.isEmpty()){
                            email_layout.setError("enter your email please..");
                        }
                        else if (!Patterns.EMAIL_ADDRESS.matcher(R_email_val).matches()){
                            email_layout.setError("enter a valid email address please..");
                        }
                        else if (!R_email_val.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(R_email_val).matches()){email_layout.setErrorEnabled(false);}
                        if (pass1_val.isEmpty()) {
                            pass1_layout.setError("you can't put an empty password..");
                        }
                        else if (pass1_val.length()<8){
                            pass1_layout.setError("Too short..");
                        }
                        else pass1_layout.setErrorEnabled(false);
                        if (!pass2_val.equals(pass1_val)) {
                            pass2_layout.setError("please confirm the previous password..");
                        } else pass2_layout.setErrorEnabled(false);

                        if (user_val.length()>=4 && !R_email_val.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(R_email_val).matches() && pass1_val.length()>=8 && pass2_val.equals(pass1_val)) {
                            registration.dismiss();
                            login.show();
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString("name",user_val);
                            editor.putString("email",R_email_val);
                            editor.putString("pass",pass2_val);

                            editor.apply();
                            Toast.makeText(MainActivity.this,"successfully registered",Toast.LENGTH_LONG).show();
                        }
                    }

                });
                registration.show();
            }
        });
        Button log_in = login.findViewById(R.id.login_btn);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email_layout.setError(null);
            }
        });
        pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pass_layout.setErrorEnabled(false);
            }
        });
        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name_sp = sp.getString("name","");
                String email_sp = sp.getString("email","");
                String pass_sp = sp.getString("pass","");
                String email_val = email.getText().toString().trim();
                String pass_val = pass.getText().toString().trim();
                if (Patterns.EMAIL_ADDRESS.matcher(email_val).matches() && !email_val.equals(email_sp)) {
                    email_layout.setError("Wrong email..");
                    pass_layout.setErrorEnabled(false);

                }
                else if (!Patterns.EMAIL_ADDRESS.matcher(email_val).matches() && !email_val.equals(name_sp)){
                    email_layout.setError("Wrong user name..");
                    pass_layout.setErrorEnabled(false);
                }

                else if (!pass_val.equals(pass_sp)) {
                    pass_layout.setError("Wrong password..");
                    email_layout.setError(null);
                    pass.setText("");
                } else {
                    login.dismiss();
                    Toast.makeText(MainActivity.this,"successfully logged in",Toast.LENGTH_LONG).show();
                    SharedPreferences.Editor editor = log.edit();
                    editor.putBoolean("log_status",true);
                    editor.apply();
                    up_login.setVisibility(View.GONE);
                    profile.setVisibility(View.VISIBLE);

                }
            }
        });
        ImageButton x = login.findViewById(R.id.close);
        x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login.dismiss();
            }
        });
        login.show();

        //reloading page1 by clicking on the logo
        ImageView logo = findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                startActivity(i);
                finish();

            }
        });

        up_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login.show();
            }
        });

    }


}