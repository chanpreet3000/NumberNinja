package com.chanpreet.numberninja;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.chanpreet.numberninja.databinding.ItemMatrixCellBinding;

import java.util.ArrayList;
import java.util.List;


public class MatrixBuilder {

    public static MatrixInfo build(Context context, int rows, int columns) {
        LinearLayout parent = new LinearLayout(context);
        parent.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        parent.setOrientation(LinearLayout.VERTICAL);

        List<List<ItemMatrixCellBinding>> listOfBindings = new ArrayList<>();


        for (int i = 0; i < rows; i++) {
            LinearLayout child = new LinearLayout(context);
            child.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            child.setOrientation(LinearLayout.HORIZONTAL);
            listOfBindings.add(new ArrayList<>());

            for (int j = 0; j < columns; j++) {
                ItemMatrixCellBinding cellBinding = ItemMatrixCellBinding.inflate(LayoutInflater.from(context), child, false);
                child.addView(cellBinding.getRoot());
                listOfBindings.get(i).add(cellBinding);
            }
            parent.addView(child);
        }
        return new MatrixInfo(rows, columns, listOfBindings, parent);
    }

    public static class MatrixInfo {
        private final Integer rows, columns;
        private final List<List<ItemMatrixCellBinding>> listOfBindings;
        private final View view;

        public MatrixInfo(Integer rows, Integer columns, List<List<ItemMatrixCellBinding>> listOfBindings, View view) {
            this.rows = rows;
            this.columns = columns;
            this.listOfBindings = listOfBindings;
            this.view = view;
        }

        public Integer getRows() {
            return rows;
        }

        public Integer getColumns() {
            return columns;
        }

        public List<List<ItemMatrixCellBinding>> getListOfBindings() {
            return listOfBindings;
        }

        public View getView() {
            return view;
        }
    }
}
