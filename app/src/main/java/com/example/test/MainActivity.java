package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText inputEditText;
    myDbAdapter helper;
    LinearLayout linearLayout ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputEditText = (EditText) findViewById(R.id.inputEditText);
        helper = new myDbAdapter(this);
        linearLayout = (LinearLayout)findViewById(R.id.list);


    }

    public void input(View view) {
        String t1 = inputEditText.getText().toString();
        long add = helper.insertData(t1);
        inputEditText.setText("");
        inputEditText.requestFocus();
        addTextView(helper.getData());

    }

    public void viewAll(View view) {
        String data = helper.getData();
        Message.message(this,data);
    }

    public void addTextView(String text){
        TextView textView = new TextView(getApplicationContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
               LinearLayout.LayoutParams.MATCH_PARENT,
               LinearLayout.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(layoutParams);
        textView.setText(text);
        linearLayout.addView(textView);
    }
}
