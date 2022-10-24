package com.example.purpulse;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.stetho.Stetho;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {

    private Button btn_edit;
    private TextView name,mail,birthday,gender,height,weight;
    private String Account;
    private ArrayList<String> editdata;
    private static final String DataBaseName = "db";
    private static final int DataBaseVersion = 4;
    private static String DataBaseTable = "Users";
    private static SQLiteDatabase DB;
    private SqlDataBaseHelper sqlDataBaseHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        name = view.findViewById(R.id.TV_name);
        mail = view.findViewById(R.id.TV_mail);
        birthday = view.findViewById(R.id.TV_bir);
        gender = view.findViewById(R.id.TV_gender);
        height = view.findViewById(R.id.TV_height);
        weight = view.findViewById(R.id.TV_weight);
        btn_edit = view.findViewById(R.id.btn_edit);
        btn_edit.setOnClickListener(lis);
        editdata = new ArrayList<>();
        //收帳號
        Account = Note.account;
        Log.d("acc","" + Account);

        Stetho.initializeWithDefaults(getActivity());
        // 建立SQLiteOpenHelper物件
        sqlDataBaseHelper = new SqlDataBaseHelper(getActivity(),DataBaseName,null,DataBaseVersion,DataBaseTable);
        DB = sqlDataBaseHelper.getWritableDatabase(); // 開啟資料庫
        Cursor D = DB.rawQuery("SELECT * FROM Users WHERE account LIKE '"+ Account +"'",null);
        D.moveToFirst();
        //設定預設值
        name.setText(D.getString(0));
        mail.setText(D.getString(3));
        birthday.setText(D.getString(4));
        gender.setText(D.getString(5));
        height.setText(D.getString(6));
        weight.setText(D.getString(7));
        return view;
    }

    View.OnClickListener lis = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            editdata.add(name.getText().toString());
            editdata.add(mail.getText().toString());
            editdata.add(birthday.getText().toString());
            editdata.add(gender.getText().toString());
            editdata.add(height.getText().toString());
            editdata.add(weight.getText().toString());
            editdata.add(Account);
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("editdata",editdata);
            Fragment fragment = new EditprofileFragment();
            fragment.setArguments(bundle);
            getFragmentManager().beginTransaction().replace(R.id.relativ_profile, new EditprofileFragment(), "edit").addToBackStack(null).commit();
            //editdata.clear();
        }
    };
}