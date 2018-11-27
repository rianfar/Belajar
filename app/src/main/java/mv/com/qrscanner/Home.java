package mv.com.qrscanner;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import adapter.HomeAdapter;
import connection.API;
import helper.RetroClient;
import model.Datum;
import model.UserMember;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends AppCompatActivity implements AbsListView.OnScrollListener {
    ListView lv;
    ProgressDialog pDialog;
    final ArrayList<Datum> artikels = new ArrayList<>();
    HomeAdapter homeAdapter;
    List<Datum> listdatum;
    String id, nama, notelp;
    private boolean loadMore = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading Data.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
        data("1");
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {}

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//        int lastInScreen = firstVisibleItem + visibleItemCount;
//        if((lastInScreen == totalItemCount) && (loadMore) && lv.getItemAtPosition(0)!=null){
//            loadMore = true;
//        }
//        Log.i("Scroll", "Last = "+lastInScreen);
//        Log.i("Scroll", "Count = "+totalItemCount);
//        Log.i("Scroll", "Item at 0 = "+(lv.getItemAtPosition(0)!=null));
//        Log.i("Scroll", "Load more = "+(loadMore));
    }

    public void data(String page) {
        RetroClient.getClient().create(API.class).responseArtikel(page).enqueue(new Callback<UserMember>() {
            @Override
            public void onResponse(Call<UserMember> call, Response<UserMember> response) {
                if (response.isSuccessful()) {
                    pDialog.dismiss();
                    Gson gson = new Gson();
                    String j = gson.toJson(response.body());
                    Log.i("response2", response.raw().request().url().toString());
                    Log.i("responseartikel", j);

                    listdatum = response.body().getData();
                    for (int i = 0; i < listdatum.size(); i++) {
                        id = listdatum.get(i).getId();
                        nama = listdatum.get(i).getNama();
                        notelp = listdatum.get(i).getNomor();

                        artikels.add(new Datum(id, nama, notelp));
                        homeAdapter = new HomeAdapter(getBaseContext(), artikels);
                        lv = findViewById(R.id.listView4);
                        lv.setAdapter(homeAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<UserMember> call, Throwable t) {

            }
        });
    }

//    public void setLoadingView(int resId) {
//        LayoutInflater inflater = (LayoutInflater) super.getContext()
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        footer = (View) inflater.inflate(resId, null);
//        this.addFooterView(footer);
//    }
}
