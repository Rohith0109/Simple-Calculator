package com.example.simplecalculator;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    MaterialButton C, one, two, three, four, five, six, seven, eight, nine, zero, brace1, point, brace2, del, equal, mul, div, minus, plus;
    TextView inp, output;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        init();

    }

    void clickListener(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    public void init(){
        clickListener(one, R.id.one);
        clickListener(two, R.id.two);
        clickListener(three, R.id.three);
        clickListener(four, R.id.four);
        clickListener(five, R.id.five);
        clickListener(six, R.id.six);
        clickListener(seven, R.id.seven);
        clickListener(eight, R.id.eight);
        clickListener(nine, R.id.nine);
        clickListener(zero, R.id.zero);
        clickListener(brace1, R.id.brace1);
        clickListener(brace2, R.id.brace2);
        clickListener(point, R.id.point);
        clickListener(del, R.id.del);
        clickListener(mul, R.id.mul);
        clickListener(div, R.id.divide);
        clickListener(plus, R.id.plus);
        clickListener(minus, R.id.minus);
        clickListener(equal, R.id.equal);
        clickListener(C, R.id.c);
        inp = findViewById(R.id.inp);
        output = findViewById(R.id.output);
    }


    @Override
    public void onClick(View view){
        MaterialButton btnCurrent = (MaterialButton) view;
        String btnText = btnCurrent.getText().toString();
        String inpText = inp.getText().toString();

        if (btnText.equals("c")){
            inp.setText("");
            output.setText("");
            return;
        }
        if (btnText.equals("del")){
            try {
                inpText = inpText.substring(0, inpText.length() - 1);
            } catch (Exception e){
                inpText = "";
            }
            inp.setText(inpText);
            return;
        }
        if (btnText.equals("=")){
            inp.setText(output.getText());
            output.setText("");
            return;
        }
        else if (btnText.equals("x")){
            inpText += "*";
        }
        else{
            inpText = inpText+btnText;
        }
        inp.setText(inpText);

        String finalResult = getResult(inpText);

        if (!finalResult.equals("Err")){
            output.setText(finalResult);
        }

    }

    String getResult(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String result = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if (result.endsWith(".0"))
                result=result.replace(".0", "");
            return result;
        } catch (Exception e){
            return "Err";
        }
    }
}

/*
    if (btnText.equals("c")){
            inp.setText("");
            output.setText("");
            return;
        }
        if (btnText.equals("del")){
            try {
                inpText = inpText.substring(0, inpText.length() - 1);
            } catch (Exception e){
                inpText = "";
            }
            inp.setText(inpText);
            return;
        }
        if (btnText.equals("=")){
            inp.setText(output.getText());
            output.setText("");
            return;
        }
        else{
            inpText = inpText+btnText;
        }

        inp.setText(inpText);

        String finalResult = getResult(inpText);

        if (!finalResult.equals("Err")){
            output.setText(finalResult);
        }
*/