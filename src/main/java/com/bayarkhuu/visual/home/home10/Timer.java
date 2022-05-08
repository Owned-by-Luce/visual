package com.bayarkhuu.visual.home.home10;

import javafx.concurrent.Task;

/**
 * Timer
 *
 * @author Баярхүү.Лув 2022.05.08 18:34
 */
public class Timer extends Task<Integer> {

    @Override
    protected Integer call() throws Exception {
        for (int i = 10; i >= 0; i--) {
            this.updateMessage("Сонгууль дуусахад: " + i + " секунд");
            Thread.sleep(1000);
        }

        return 0;
    }
}
