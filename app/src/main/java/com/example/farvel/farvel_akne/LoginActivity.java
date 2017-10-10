package com.example.farvel.farvel_akne;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    private Button login,signup;
    ProgressDialog progressDialog;
    EditText editTextUsername,editTextPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsername = (EditText) findViewById(R.id.input_email);
        editTextPassword = (EditText) findViewById(R.id.input_password);

/*
        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            return;
        }
*/
        login = (Button)findViewById(R.id.btn_login);
        signup = (Button) findViewById(R.id.btn_regi);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar snackbar = Snackbar
                        .make(findViewById(android.R.id.content), "INTERNET IKKE SLÅET TIL", Snackbar.LENGTH_LONG);

                View snackBarView = snackbar.getView();
                snackBarView.setBackgroundColor(Color.RED);
                TextView mainTextView = (TextView) (snackBarView.findViewById(android.support.design.R.id.snackbar_text));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {


                    mainTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                } else {
                    mainTextView.setGravity(Gravity.CENTER_HORIZONTAL);
                    mainTextView.setGravity(Gravity.CENTER_HORIZONTAL);
                }
                mainTextView.setTypeface(null, Typeface.BOLD);

                if(CheckNetwork.isInternetAvailable(LoginActivity.this)) //returns true if internet available
                {
                    userLogin();
                    //do something. loadwebview.
                }
                else
                {
                    snackbar.show();
                }

                //startActivity(new Intent(LoginActivity.this,MainActivity.class));
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), testSignup.class);
                startActivityForResult(intent, 0);
            }
        });
    }


    private void userLogin() {
        //first getting the values
        final String username = editTextUsername.getText().toString();
        final String password = editTextPassword.getText().toString();

        //validating inputs
        if (!isValidEmail(username)) {
            editTextUsername.setError("Please enter your E-Mail");
            editTextUsername.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("Please enter your password");
            editTextPassword.requestFocus();
            return;
        }

        //if everything is fine

        class UserLogin extends AsyncTask<Void, Void, String> {



            @Override
            protected void onPreExecute() {
                super.onPreExecute();

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);



                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(s);

                    //if no error in response
                    try {
                        if (!obj.getBoolean("error")) {
                            Snackbar snackbar = Snackbar
                                    .make(findViewById(android.R.id.content), obj.getString("message"), Snackbar.LENGTH_LONG);
                            View snackBarView = snackbar.getView();
                            snackBarView.setBackgroundColor(Color.RED);
                            TextView mainTextView = (TextView) (snackBarView.findViewById(android.support.design.R.id.snackbar_text));
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {


                                mainTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                            } else {
                                mainTextView.setGravity(Gravity.CENTER_HORIZONTAL);
                                mainTextView.setGravity(Gravity.CENTER_HORIZONTAL);
                            }
                            mainTextView.setTypeface(null, Typeface.BOLD);
                            snackbar.show();
                            //getting the user from the response
                            JSONObject userJson = obj.getJSONObject("user");

                            //creating a new user object
                            User user = new User(
                                    userJson.getInt("id"),
                                    userJson.getString("username"),
                                    userJson.getString("email"),
                                    userJson.getString("gender")
                            );

                            //storing the user in shared preferences
                            SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);

                            //starting the profile activity
                            finish();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {


                            Toast.makeText(getApplicationContext(), "DER SKET EN FEJL", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler1 requestHandler = new RequestHandler1();

                //creating request parameters
                HashMap<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);

                //returing the response
                return requestHandler.sendPostRequest(URLs.URL_LOGIN, params);
            }
        }

        UserLogin ul = new UserLogin();
        ul.execute();
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
}
