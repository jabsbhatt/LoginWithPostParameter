package com.example.jabs.loginwithpostparameter.activities;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


import com.example.jabs.loginwithpostparameter.R;
import com.example.jabs.loginwithpostparameter.api.ApiService;
import com.example.jabs.loginwithpostparameter.api.AppConfig;
import com.example.jabs.loginwithpostparameter.helper.AsyncResponse;
import com.example.jabs.loginwithpostparameter.helper.SeviceCall;
import com.example.jabs.loginwithpostparameter.utils.GlobalVariable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements AsyncResponse {

    AsyncResponse asy;
    TextInputEditText input_email, input_password;
    String userid;
    private String TAG = getClass().getName();
    String userIDlogin = "";
    ProgressDialog progressDialog;
    Button button_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        asy = this;
//        progressDialog = new ProgressDialog(LoginActivity.this);
//        progressDialog.setMessage("Please Wait...");
        input_email = (TextInputEditText) findViewById(R.id.input_email);
        input_password = (TextInputEditText) findViewById(R.id.input_password);
        button_login = (Button) findViewById(R.id.button_login);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Validation()) {
                    callAPIForLogin();
                }
            }
        });


//        String[] perm = new String[]{
//                Manifest.permission.READ_EXTERNAL_STORAGE,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                Manifest.permission.CAMERA, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
//        hasPermissions(this, perm);
//    }


//    public static boolean hasPermissions(Activity context, String... permissions) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
//            for (String permission : permissions) {
//                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
//                    ActivityCompat.requestPermissions(context, permissions, 1);
//                }
//            }
//        }
//        return true;
    }

    private void callAPIForLogin() {
        ArrayList<String> key = new ArrayList<String>();
        ArrayList<String> value = new ArrayList<String>();
        key.add("email");
        value.add(input_email.getText().toString());
        key.add("password");
        value.add(input_password.getText().toString());

        SeviceCall ser = new SeviceCall(LoginActivity.this, GlobalVariable.api_login, true, "Please Wait...", "PUT", asy, 0, key, value);
        ser.execute();

//            progressDialog.show();

//            ApiService getResponse = AppConfig.getRetrofit().create(ApiService.class);
//            Call<ResponseBody> call = getResponse.uploadMultipleFiles(userid, name_value, mobile_value, email, birth_date, gender_value, qualification, qualification_other, specialization, specialization_other, passingyear, registrationNumber, registration_council, registration_council_other, registrationyear, yearOfExperiance, fileToUpload1, fileToUpload2, fileToUpload3);
//            call.enqueue(new Callback<ResponseBody>() {
//                @Override
//                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                    if (response.code() == 200) {
//                        Log.e(TAG, "success");
//                        Log.e(TAG, "response code-->" + response.code());
//                        try {
//                            JSONObject json = new JSONObject(response.body().string());
//                            String status_code = json.getString("status_code");
//                            String msg = json.getString("msg");
//                            Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_LONG).show();
//                            if (status_code.equals("1")) {
//                                // ed.putString("login","yes");
//
//                                JSONArray jsonnode = json.optJSONArray("userdata");
//                            }
//                        } catch (Exception e) {
//                            Toast.makeText(LoginActivity.this, e.toString(), Toast.LENGTH_LONG).show();
//                        }
//                    } else {
//                        Log.e(TAG, "response code-->" + response.code());
//                    }
//                    progressDialog.dismiss();
//                }
//
//                @Override
//                public void onFailure(Call<ResponseBody> call, Throwable t) {
//                    Log.e("Upload error:", t.getMessage());
//                    progressDialog.dismiss();
//                }
//            });
    }

    public boolean Validation() {
        // TODO Auto-generated method stub

        boolean validate = false;
        if (input_email.length() == 0) {
            input_email.setError("Please Enter Email Address!!!");
            input_email.requestFocus();
        } else if (!isValidEmail(input_email.getText().toString())) {
            input_email.setError("Please Enter Valid Email Address!!!");
            input_email.requestFocus();
        } else if (input_password.length() < 2) {
            input_password.setError("Please Enter Password!!!");
            input_password.requestFocus();
        } else
            validate = true;
        return validate;
    }


    @Override
    public void processFinish(String output, int ser) {
        if (ser == 0) {
            try {
                JSONObject json = new JSONObject(output);
                String status_code = json.getString("status_code");
                String msg = json.getString("msg");

                if (status_code.equals("1")) {

                    JSONArray jsonnode = json.optJSONArray("userdata");
                    for (int i = 0; i < jsonnode.length(); i++) {

                        JSONObject item = (JSONObject) jsonnode.get(i);
                        userIDlogin = item.getString("id");
                    }

                } else {
                    Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                Toast.makeText(LoginActivity.this, e.toString(), Toast.LENGTH_LONG).show();
            }
        }
        if (ser == 10) {
            Toast.makeText(LoginActivity.this, output, Toast.LENGTH_LONG).show();
        }
    }

    private boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

}
