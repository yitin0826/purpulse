package com.example.purpulse;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.stetho.Stetho;

public class LoginActivity extends AppCompatActivity {

    Button btn_login,btn_gosignup,btn_forgetpw;
    EditText edittext_account,edittext_password;
    CheckBox checkBox;

    private static final String DataBaseName = "db";
    private static final int DataBaseVersion = 4;
    private static String DataBaseTable = "Users";
    private static SQLiteDatabase DB;
    private SqlDataBaseHelper sqlDataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_login = findViewById(R.id.btn_login);
        btn_gosignup = findViewById(R.id.btn_gosignup);
        btn_forgetpw = findViewById(R.id.btn_forget);
        edittext_account = findViewById(R.id.edit_account);
        edittext_password = findViewById(R.id.edit_password);
        checkBox = findViewById(R.id.checkbox_1);
        btn_login.setOnClickListener(lis);
        btn_gosignup.setOnClickListener(lis);
        btn_forgetpw.setOnClickListener(lis);

        Stetho.initializeWithDefaults(this);
        // 建立SQLiteOpenHelper物件
        sqlDataBaseHelper = new SqlDataBaseHelper(LoginActivity.this,DataBaseName,null,DataBaseVersion,DataBaseTable);
        DB = sqlDataBaseHelper.getWritableDatabase(); // 開啟資料庫
    }

    View.OnClickListener lis = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_login:{
                    Cursor D = DB.rawQuery("SELECT * FROM " + DataBaseTable,null);
                    D.moveToFirst();
                    for (int i=0;i<D.getCount();i++){   //登入檢查
                        if (edittext_account.getText().toString().equals(D.getString(1))  && edittext_password.getText().toString().equals(D.getString(2))){
                            if (checkBox.isChecked()) {    //記住我
                                edittext_account.setText(D.getString(1));
                                edittext_password.setText(D.getString(2));
                            }else {
                                edittext_account.setText("");
                                edittext_password.setText("");
                            }
                            startActivity(new Intent(LoginActivity.this, HomepageActivity.class));
                        }else {
                            if (edittext_account.getText().toString().length()<1 | edittext_password.getText().toString().length()<1){
                                Toast.makeText(LoginActivity.this,"欄位不得空白",Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(LoginActivity.this,"帳號或密碼錯誤",Toast.LENGTH_SHORT).show();
                            }
                        }
                        D.moveToNext();
                    }
                    break;
                }
                case R.id.btn_gosignup:{
                    startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
                    break;
                }
                case R.id.btn_forget:{
                    startActivity(new Intent(LoginActivity.this,PasswordActivity.class));
                    break;
                }
            }
        }
    };
}