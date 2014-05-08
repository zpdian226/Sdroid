package org.sdroid;

import java.io.FileNotFoundException;

import jscheme.JS;
import jsint.InputPort;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

    private Button mEval;
    private EditText mEvalEdit;
    private TextView mResult;
    public static MainActivity sContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sContext = this;
        mEval = (Button) findViewById(R.id.button_eval);
        mEvalEdit = (EditText) findViewById(R.id.edit);
        mResult = (TextView) findViewById(R.id.result);
        mEval.setOnClickListener(this);
    }

    public Object evalResource(String resource) {
        Object result = null;
        Object expression;

        InputPort inputPort = null;
        try {
            inputPort = new InputPort(new java.io.FileReader(resource));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        while ((expression = inputPort.read()) != InputPort.EOF) {
            result = JS.eval(((jsint.Pair) expression).toString());
            Log.d("myTag", "result ==== " + result);
        }
        return result;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        Log.d("myTag", "11111111111111111");
        return super.onMenuItemSelected(featureId, item);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.action_load_resource:
            Intent i = new Intent(this, LoadResource.class);
            startActivity(i);
            break;
        default:
            break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.button_eval:
            eval();
            break;
        default:
            break;
        }
    }

    private void eval() {
        String s = mEvalEdit.getText().toString();
        mEvalEdit.setText(null);
        Object res = "";
        try {
            JS.load("(define target (.findViewById MainActivity.sContext$ org.sdroid.R$id.dis_1$))");
            res = JS.eval(s);
        } catch (Exception e) {
            Log.e("myTag", "load or eval error");
        }
        mResult.setText(s + "\n> " + res.toString());
    }
}
