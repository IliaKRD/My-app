package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import com.example.myapplication.R;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView result, soluton;

    MaterialButton buttonC, buttonOpen, buttonClose;
    MaterialButton buttonDevide, buttonMultiply, buttonPlus, buttonMinus, buttonEquals;
    MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    MaterialButton buttonAC, buttonTochka;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result=findViewById(R.id.result);
        soluton=findViewById(R.id.solution);
        soluton.setText("");

        assignId(buttonC,R.id.button_c);
        assignId(buttonOpen,R.id.button_open);
        assignId(buttonClose,R.id.button_close);
        assignId(buttonDevide,R.id.button_divide);
        assignId(buttonMultiply,R.id.button_multiply);
        assignId(buttonPlus,R.id.button_plus);
        assignId(buttonMinus,R.id.button_minus);
        assignId(buttonEquals,R.id.button_equals);
        assignId(button0, R.id.button_0);
        assignId(button1,R.id.button_1);
        assignId(button2,R.id.button_2);
        assignId(button3,R.id.button_3);
        assignId(button4,R.id.button_4);
        assignId(button5,R.id.button_5);
        assignId(button6,R.id.button_6);
        assignId(button7,R.id.button_7);
        assignId(button8,R.id.button_8);
        assignId(button9,R.id.button_9);
        assignId(buttonAC,R.id.button_AC);
        assignId(buttonTochka,R.id.button_tochka);

    }

    void assignId(MaterialButton btn, int id) {
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        MaterialButton button = (MaterialButton) v;
        String buttonText = button.getText().toString();
        String dataToCalculate = soluton.getText().toString();

        if (buttonText.equals("=")) {
            soluton.setText(result.getText());
            return;
        }

        if (buttonText.equals("C")) {
            if (!dataToCalculate.isEmpty()) {
                dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
            }
        } else if (buttonText.equals("AC")) {
            dataToCalculate = "";
            result.setText("0");
        } else {
            dataToCalculate = dataToCalculate + buttonText;
        }

        soluton.setText(dataToCalculate);

        if (!dataToCalculate.isEmpty()) {
            String finalResult = getResult(dataToCalculate);
            if (!finalResult.equals("Error")) {
                result.setText(finalResult);
            }
        }
    }

    String getResult(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult=finalResult.replace(".0","");
            }
            return finalResult;
        } catch (Exception e){
            return "Error";
        }
    }
}