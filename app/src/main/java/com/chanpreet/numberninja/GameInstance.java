package com.chanpreet.numberninja;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import com.chanpreet.numberninja.databinding.ItemMatrixCellBinding;

import java.util.List;

public class GameInstance {
    private int row;
    private int column;

    private final Context context;
    private final MatrixBuilder.MatrixInfo matrixInfo;

    public GameInstance(Context context, MatrixBuilder.MatrixInfo matrixInfo) {
        this.context = context;
        this.matrixInfo = matrixInfo;
        row = -1;
        column = 0;


        List<List<ItemMatrixCellBinding>> listOfBindings = matrixInfo.getListOfBindings();
        for (int i = 0; i < matrixInfo.getRows(); i++) {
            for (int j = 0; j < matrixInfo.getColumns(); j++) {
                EditText curr = listOfBindings.get(i).get(j).editText, next = null, prev = null;
                if (j < matrixInfo.getColumns() - 1) {
                    next = listOfBindings.get(i).get(j + 1).editText;
                }
                if (j > 0) {
                    prev = listOfBindings.get(i).get(j - 1).editText;
                }
                listOfBindings.get(i).get(j).editText.addTextChangedListener(new GameInstance.GenericTextWatcher(curr, next));
                listOfBindings.get(i).get(j).editText.setOnKeyListener(new GameInstance.GenericKeyEvent(curr, prev));

            }
        }

    }


    public void moveToNextRow() {
        List<List<ItemMatrixCellBinding>> listOfBindings = matrixInfo.getListOfBindings();
        if (row != -1) {
            for (int i = 0; i < matrixInfo.getColumns(); i++) {
                listOfBindings.get(row).get(i).editText.setEnabled(false);
            }
        }
        row++;
        column = 0;
        listOfBindings.get(row).get(column).editText.requestFocus();
    }

    private static class GenericKeyEvent implements View.OnKeyListener {
        private final EditText currView, prevView;

        public GenericKeyEvent(EditText currView, EditText prevView) {
            this.currView = currView;
            this.prevView = prevView;
        }

        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (event.getAction() == KeyEvent.ACTION_DOWN
                    && keyCode == KeyEvent.KEYCODE_DEL
                    && currView.getText().toString().length() == 0) {
                if (prevView != null) {
                    prevView.setText(null);
                    prevView.requestFocus();
                    return true;
                }
            }
            return false;
        }
    }

    private static class GenericTextWatcher implements TextWatcher {
        private final EditText currView, nextView;

        public GenericTextWatcher(EditText currView, EditText nextView) {
            this.currView = currView;
            this.nextView = nextView;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.length() == 1) {
                if (nextView != null) {
                    nextView.requestFocus();
                }
            }
        }
    }
}
