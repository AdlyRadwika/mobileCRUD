package com.rmn.sqliteapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ModifyStudentsActivity extends AppCompatActivity {
    private EditText kelasText;
    private Button updateBtn, deleteBtn;
    private EditText namaText;
    private long _id;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_students);
        dbManager = new DBManager(this);
        dbManager.open();
        kelasText = (EditText) findViewById(R.id.kelas_edittext);
        namaText = (EditText) findViewById(R.id.nama_edittext);
        updateBtn = (Button) findViewById(R.id.btn_update);
        deleteBtn = (Button) findViewById(R.id.btn_delete);
/* Membuat objek Intent dengan nilai yang dikirim objek Intent
yang telah memanggil kelas ini sebelumnya */
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String kelas = intent.getStringExtra("kelas");
        String nama = intent.getStringExtra("nama");
        _id = Long.parseLong(id);
        kelasText.setText(kelas);
        namaText.setText(nama);
    }

    public void update(View v) {
// Menyimpan nilai kelas dan nama baru ke variabel
        String kelas = kelasText.getText().toString();
        String nama = namaText.getText().toString();
/* Memanggil fungsi update melalui objek dbManager
fungsi ini membawa tiga parameter yakni _id, kelas, nama */
        dbManager.update(_id, kelas, nama);
        this.returnHome();
    }

    public void delete(View v) {
// Memanggil fungsi delete dengan parameter _id
        dbManager.delete(_id);
        this.returnHome();
    }

    // Fungsi untuk kembali ke halaman awal
    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(),
                com.rmn.sqliteapp.DataStudentsActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }

}