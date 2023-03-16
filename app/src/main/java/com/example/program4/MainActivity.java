package com.example.program4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView enter;
    EditText name, age, contact;
    Button insert, update, delete, view;

    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        contact = findViewById(R.id.contact);

        insert = findViewById(R.id.insert);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);
        view = findViewById(R.id.view);

        db = new DBHelper(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String ageTXT = age.getText().toString();
                String contactTXT = contact.getText().toString();

                boolean qryStatus = db.insertData(nameTXT, contactTXT,ageTXT);

                if(qryStatus == true)
                {
                    Toast.makeText(MainActivity.this,"New Record Created", Toast.LENGTH_SHORT ).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"New Record Creation Failed", Toast.LENGTH_SHORT ).show();
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String ageTXT = age.getText().toString();
                String contactTXT = contact.getText().toString();

                boolean qryStatus = db.updateData(nameTXT, contactTXT,ageTXT);

                if(qryStatus == true)
                {
                    Toast.makeText(MainActivity.this,"Record Updated", Toast.LENGTH_SHORT ).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Record Updation Failed", Toast.LENGTH_SHORT ).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();

                boolean qryStatus = db.deleteData(nameTXT);

                if(qryStatus == true)
                {
                    Toast.makeText(MainActivity.this,"Record Deleted", Toast.LENGTH_SHORT ).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Record Deletion Failed", Toast.LENGTH_SHORT ).show();
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = db.viewData();

                if(res.getCount() == 0)
                {
                    Toast.makeText(MainActivity.this,"No Record Exist", Toast.LENGTH_SHORT ).show();
                }
                else
                {
                    StringBuffer buffer = new StringBuffer();
                    while(res.moveToNext())
                    {
                        buffer.append("name :" + res.getString(0) + "\n");
                        buffer.append("age :" + res.getString(2) + "\n");
                        buffer.append("contact :" + res.getString(1) + "\n");
                    }
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setCancelable(true);
                    builder.setTitle("user data");
                    builder.setMessage(buffer.toString());
                    builder.show();
                }
            }
        });

    }


}