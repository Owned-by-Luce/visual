package com.bayarkhuu.visual.labs.lab13;

import javafx.concurrent.Task;

import java.util.Random;

public class Drop extends Task<Integer> {

    @Override
    protected Integer call() throws Exception {
        Random random = new Random();
        int size = 50;

        for (int i = 1; i <= size; i++) {
            copy(i, random.nextInt(6) + 1);
            this.updateProgress(i, size);
        }

        return size;
    }

    private void copy(int i, int drop) throws Exception {
        this.updateMessage(i + "-р шоог шидэхэд " + drop + " буулаа");
        Thread.sleep(200);
    }
}
