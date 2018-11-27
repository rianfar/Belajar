package mv.com.qrscanner;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.zxing.Result;

import connection.API;
import helper.RetroClient;
import me.dm7.barcodescanner.zxing.ZXingScannerView;
import model.UserMember;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
    TextView hello;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    @Override
    public void handleResult(Result rawResult) {
        Log.v("TAG", rawResult.getText()); // Prints scan results
        Log.v("TAG", rawResult.getBarcodeFormat().toString());
        mScannerView.removeAllViews(); //<- here remove all the views, it will make an Activity having no View
        mScannerView.stopCamera(); //<- then stop the camera
        setContentView(R.layout.main); //<- and set the View again.
        final String vString = rawResult.getText();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                hello = findViewById(R.id.txthello);
                hello.setText(Preference.getHasilScan(getBaseContext()));
                Toast.makeText(getApplicationContext(), vString, Toast.LENGTH_LONG).show();
            }
        });
        Preference.setHasilScan(getBaseContext(), rawResult.getText());
        Preference.setLoggedInStatus(getBaseContext(), true);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Main.this, Main.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void showqr(View view) {
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    public void set(View view) {
        hello = findViewById(R.id.txthello);
        hello.setText(Preference.getHasilScan(getBaseContext()));
    }

    public void home(View view) {
        Intent intent = new Intent(getApplicationContext(), Home.class);
        startActivity(intent);
    }

    public void logout(View view) {
        Preference.clearLoggedInUser(getBaseContext());
    }

    public void kontak(View view) {
        pDialog = new ProgressDialog(Main.this);
        pDialog.setMessage("Loading Data.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
        String id = "1";
        RetroClient.getClient().create(API.class).responseMember().enqueue(new Callback<UserMember>() {
            @Override
            public void onResponse(Call<UserMember> call, Response<UserMember> response) {
                if (response.isSuccessful()) {
                    pDialog.dismiss();
                    Gson gson = new Gson();
                    String j = gson.toJson(response.body());

                    Log.i("response", j);
                    Log.i("response2", response.raw().request().url().toString());
                }
            }

            @Override
            public void onFailure(Call<UserMember> call, Throwable throwable) {

            }
        });
    }
}
