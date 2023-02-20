package com.chanpreet.numberninja;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.chanpreet.numberninja.databinding.ActivityMainBinding;
import com.chanpreet.numberninja.databinding.ItemMatrixCellBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private GameInstance gameInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MatrixBuilder.MatrixInfo matrixInfo = MatrixBuilder.build(getApplicationContext(), 6, 8);
        binding.linearLayout.addView(matrixInfo.getView());
        gameInstance = new GameInstance(getApplicationContext(), matrixInfo);

        binding.equalBtn.setOnClickListener(v -> gameInstance.CheckButtonClicked());
        binding.backspaceBtn.setOnClickListener(v -> gameInstance.removeCharacter());

        binding.plusBtn.setOnClickListener(v -> gameInstance.pushCharacter(((Button) v).getText().toString().charAt(0)));
        binding.minusBtn.setOnClickListener(v -> gameInstance.pushCharacter(((Button) v).getText().toString().charAt(0)));
        binding.multiplyBtn.setOnClickListener(v -> gameInstance.pushCharacter(((Button) v).getText().toString().charAt(0)));
        binding.divideBtn.setOnClickListener(v -> gameInstance.pushCharacter(((Button) v).getText().toString().charAt(0)));
        binding.equalsBtn.setOnClickListener(v -> gameInstance.pushCharacter(((Button) v).getText().toString().charAt(0)));

        binding.zeroBtn.setOnClickListener(v -> gameInstance.pushCharacter(((Button) v).getText().toString().charAt(0)));
        binding.oneBtn.setOnClickListener(v -> gameInstance.pushCharacter(((Button) v).getText().toString().charAt(0)));
        binding.twoBtn.setOnClickListener(v -> gameInstance.pushCharacter(((Button) v).getText().toString().charAt(0)));
        binding.threeBtn.setOnClickListener(v -> gameInstance.pushCharacter(((Button) v).getText().toString().charAt(0)));
        binding.fourBtn.setOnClickListener(v -> gameInstance.pushCharacter(((Button) v).getText().toString().charAt(0)));

        binding.fiveBtn.setOnClickListener(v -> gameInstance.pushCharacter(((Button) v).getText().toString().charAt(0)));
        binding.sixBtn.setOnClickListener(v -> gameInstance.pushCharacter(((Button) v).getText().toString().charAt(0)));
        binding.sevenBtn.setOnClickListener(v -> gameInstance.pushCharacter(((Button) v).getText().toString().charAt(0)));
        binding.eightBtn.setOnClickListener(v -> gameInstance.pushCharacter(((Button) v).getText().toString().charAt(0)));
        binding.nineBtn.setOnClickListener(v -> gameInstance.pushCharacter(((Button) v).getText().toString().charAt(0)));
    }
}