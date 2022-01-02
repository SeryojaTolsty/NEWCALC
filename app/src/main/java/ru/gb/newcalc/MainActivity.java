package ru.gb.newcalc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.databinding.DataBindingUtil;

import java.text.DecimalFormat;

import ru.gb.newcalc.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {



    private ActivityMainBinding binding;

    private static final char ADDITION = '+';
    private static final char SUBTRACTION = '-';
    private static final char MULTIPLICATION = '*';
    private static final char DIVISION = '/';
    private char CURRENT_ACTION;


    private TextView textView;
    private EditText editText;
    private Button changeTheme;


    private DecimalFormat decimalFormat;


    public static final String KEY_ONE = "KEY_ONE";
    public static final String KEY_TWO = "KEY_TWO";
    public static final String KEY_RESULT = "KEY_RESULT";


    private double valueOne = Double.NaN;
    private double valueTwo;
    private double valueThree;
    private String result;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        decimalFormat = new DecimalFormat("#.##########");
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);
        changeTheme = findViewById(R.id.buttonTheme);
        changeTheme.setOnClickListener(this);


        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(KEY_ONE)) {
                valueOne = savedInstanceState.getDouble(KEY_ONE);
            }
            if (savedInstanceState.containsKey(KEY_TWO)) {
                valueTwo = savedInstanceState.getDouble(KEY_TWO);
            }
            if (savedInstanceState.containsKey(KEY_RESULT)) {
                result = savedInstanceState.getString(KEY_RESULT);
            }
            textView.setText(result);
        }


        binding.buttonDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + ".");
            }
        });

        binding.buttonZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "0");
            }
        });

        binding.buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "1");
            }
        });

        binding.buttonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "2");
            }
        });

        binding.buttonThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "3");
            }
        });

        binding.buttonFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "4");
            }
        });

        binding.buttonFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "5");
            }
        });

        binding.buttonSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "6");
            }
        });

        binding.buttonSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "7");
            }
        });

        binding.buttonEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "8");
            }
        });

        binding.buttonNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "9");
            }
        });

        binding.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                computeCalculation();
                CURRENT_ACTION = ADDITION;
                binding.textView.setText(decimalFormat.format(valueOne) + "+");

                binding.editText.setText(null);
            }
        });

        binding.buttonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                computeCalculation();
                CURRENT_ACTION = SUBTRACTION;
                binding.textView.setText(decimalFormat.format(valueOne) + "-");

                binding.editText.setText(null);
            }
        });

        binding.buttonMult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                computeCalculation();
                CURRENT_ACTION = MULTIPLICATION;
                binding.textView.setText(decimalFormat.format(valueOne) + "*");

                binding.editText.setText(null);
            }
        });

        binding.buttonDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                computeCalculation();

                CURRENT_ACTION = DIVISION;
                binding.textView.setText(decimalFormat.format(valueOne) + "/");

                binding.editText.setText(null);
            }
        });


        binding.buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                computeCalculation();
                result = binding.textView.getText().toString() +
                        decimalFormat.format(valueTwo) + " = " + decimalFormat.format(valueOne);
                binding.textView.setText(result);
                valueOne = Double.NaN;
                CURRENT_ACTION = '0';
            }
        });


        binding.buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.editText.getText().length() > 0) {
                    CharSequence currentText = binding.editText.getText();
                    binding.editText.setText(currentText.subSequence(0, currentText.length() - 1));
                } else {
                    valueOne = Double.NaN;
                    valueTwo = Double.NaN;
                    valueThree = Double.NaN;
                    binding.editText.setText("");
                    binding.textView.setText("");
                }
            }
        });
//        binding.buttonTheme.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent changeTheme = new Intent(MainActivity.this, ChangeThemeActivity.class);
//                startActivity(changeTheme);
//
//            }
//        });



    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putDouble(KEY_ONE, valueOne);
        outState.putDouble(KEY_TWO, valueTwo);
        outState.putString(KEY_RESULT, result);

    }

    private void computeCalculation() {
        if (!Double.isNaN(valueOne)) {
            valueTwo = Double.parseDouble(binding.editText.getText().toString());
            binding.editText.setText(null);
            if (CURRENT_ACTION == ADDITION)
                valueOne = this.valueOne + valueTwo;
            else if (CURRENT_ACTION == SUBTRACTION)
                valueOne = this.valueOne - valueTwo;
            else if (CURRENT_ACTION == MULTIPLICATION)
                valueOne = this.valueOne * valueTwo;
            else if (CURRENT_ACTION == DIVISION)
                valueOne = this.valueOne / valueTwo;
        } else {
            try {
                valueOne = Double.parseDouble(binding.editText.getText().toString());
            } catch (Exception e) {
            }
        }
    }


    public void onClick(View v) {
        Intent changeThemeActivity = new Intent(this, ChangeThemeActivity.class);
        startActivity(changeThemeActivity);
    }
}