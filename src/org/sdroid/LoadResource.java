package org.sdroid;

import java.io.FileNotFoundException;

import jscheme.JS;
import jsint.InputPort;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class LoadResource extends Activity implements OnClickListener {

    Button mButton1;
    Button mButton2;
    Button mButton3;
    Button mButton4;
    TextView mDisplay;
    public static Context sContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load_resource);
        sContext = this;
        mButton1 = (Button) findViewById(R.id.button1);
        mButton2 = (Button) findViewById(R.id.button2);
        mButton3 = (Button) findViewById(R.id.button3);
        mButton4 = (Button) findViewById(R.id.button4);
        mDisplay = (TextView) findViewById(R.id.dis_2);
        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
        mButton4.setOnClickListener(this);
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
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.button1:
            mDisplay.setText(null);
            calc();
            break;
        case R.id.button2:
            mDisplay.setText(null);
            quicksort();
            break;
        case R.id.button3:
            mDisplay.setText(null);
            mergesort();
            break;
        case R.id.button4:
            mDisplay.setText(null);
            startActivity();
            break;
        default:
            break;
        }
    }

    private void initload() {
        String impt = "/sdcard/import.scm";
        String loadImpt = "(load \"" + impt + "\")";
        JS.load(loadImpt);
        String print = "/sdcard/print.scm";
        String loadPrint = "(load \"" + print + "\")";
        JS.load(loadPrint);
    }

    private void calc() {
        initload();
        String script = "/sdcard/calc.scm";
        String loadString = "(load \"" + script + "\")";
        JS.load("(define target (.findViewById LoadResource.sContext$ org.sdroid.R$id.dis_2$))");
        JS.load(loadString);
    }

    private void quicksort() {
        initload();
        String script = "/sdcard/quicksort.scm";
        String loadString = "(load \"" + script + "\")";
        JS.load("(define target (.findViewById LoadResource.sContext$ org.sdroid.R$id.dis_2$))");
        JS.load(loadString);
    }

    private void mergesort() {
        initload();
        String script = "/sdcard/mergesort.scm";
        String loadString = "(load \"" + script + "\")";
        JS.load("(define target (.findViewById LoadResource.sContext$ org.sdroid.R$id.dis_2$))");
        JS.load(loadString);
    }

    private void startActivity() {
        initload();
        String script = "/sdcard/startActivity.scm";
        String loadString = "(load \"" + script + "\")";
        JS.load("(define target (.findViewById LoadResource.sContext$ org.sdroid.R$id.dis_2$))");
        JS.load(loadString);
    }

}
