package com.example.signupapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

public class successRegistration extends Activity {
    TextView tv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.success);
        tv=findViewById(R.id.textView7);
        Bundle extras=getIntent().getExtras();
        String msg=extras.getString("msg");
        tv.setText(msg);


    }
}
