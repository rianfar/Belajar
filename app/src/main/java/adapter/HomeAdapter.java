package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import model.Datum;
import model.UserMember;
import mv.com.qrscanner.R;

public class HomeAdapter extends ArrayAdapter<Datum> {
    private Context context;
    TextView id,name,nomor;

    public HomeAdapter(Context context, ArrayList<Datum> homeAdapter) {
        super(context, 0, homeAdapter);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            convertView = layoutInflater.inflate(R.layout.mylisthome, null);
        }

        Datum Datum = getItem(position);
        id = convertView.findViewById(R.id.id);
        id.setText(Datum.getId());
        name = convertView.findViewById(R.id.nama);
        name.setText(Datum.getNama());
        nomor = convertView.findViewById(R.id.notlp);
        nomor.setText(Datum.getNomor());
        return convertView;
    }
}
