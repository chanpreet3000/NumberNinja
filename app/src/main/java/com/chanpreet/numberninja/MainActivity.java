package com.chanpreet.numberninja;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
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

        gameInstance.moveToNextRow();
        binding.equalBtn.setOnClickListener(v -> {
            gameInstance.moveToNextRow();
        });

        
    }
}