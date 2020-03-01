package com.example.gmit_sdp.beinghuman;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.PhoneNumber;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;

public class MainActivity extends AppCompatActivity {
    private int APP_REQUEST_CODE = 15;

    public String firstname1,middlename1,lastname1,email1,mobileno1,username1,password1,gender1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstname1 = getIntent().getExtras().getString("firstname");
        lastname1 = getIntent().getExtras().getString("lastname");
        middlename1 = getIntent().getExtras().getString("middlename");
        username1 = getIntent().getExtras().getString("username");
        password1 = getIntent().getExtras().getString("password");
        email1 = getIntent().getExtras().getString("email");
        mobileno1 = getIntent().getExtras().getString("mobile");
        gender1 = getIntent().getExtras().getString("gender");
        try {
            final Intent intent = new Intent(MainActivity.this, AccountKitActivity.class);
            AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder =
                    new AccountKitConfiguration.AccountKitConfigurationBuilder(
                            LoginType.PHONE,
                            AccountKitActivity.ResponseType.CODE); // or .ResponseType.TOKEN
            // ... perform additional configuration ...
            PhoneNumber n = new PhoneNumber("+1",mobileno1,"Canada");
            configurationBuilder.setInitialPhoneNumber(n);
            intent.putExtra(
                    AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION,
                    configurationBuilder.build());
            startActivityForResult(intent, APP_REQUEST_CODE);


        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "Some problem occurred", Toast.LENGTH_LONG).show();

        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
         {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == APP_REQUEST_CODE) { // confirm that this response matches your request
                AccountKitLoginResult loginResult = data.getParcelableExtra(AccountKitLoginResult.RESULT_KEY);
                String toastMessage;
                if (loginResult.getError() != null) {
                    toastMessage = loginResult.getError().getErrorType().getMessage();

                } else if (loginResult.wasCancelled()) {
                    toastMessage = "Login Cancelled";
                } else {
                    if (loginResult.getAccessToken() != null) {
                        toastMessage = "Success:" + loginResult.getAccessToken().getAccountId();
                    } else {
                        toastMessage = String.format(
                                "Success");
                    }

                    // If you have an authorization code, retrieve it from
                    // loginResult.getAuthorizationCode()
                    // and pass it to your server and exchange it for an access token.

                    // Success! Start your next activity...
                    /*Intent intent = new Intent(MainActivity.this, Bottom.class);
                    startActivity(intent);*/
                    BackgroundWorker backgroundworker= new BackgroundWorker(this);
                    backgroundworker.execute(firstname1,middlename1,lastname1,email1,mobileno1,username1,password1,gender1);
                    finish();
                }

                // Surface the result to your user in an appropriate way.
               /* Toast.makeText(
                        this,
                        toastMessage,
                        Toast.LENGTH_LONG)
                        .show();*/

            }
        }
    }
}
