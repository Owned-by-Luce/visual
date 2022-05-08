package com.bayarkhuu.visual.home.home10;

import javafx.concurrent.Task;

import java.text.DecimalFormat;

/**
 * Election
 *
 * @author Баярхүү.Лув 2022.05.08 17:34
 */
public class Election extends Task<Province> {
    private final Province model;
    private final DecimalFormat formatter = new DecimalFormat("#.00");

    public Election(Province province) {
        model = province;
    }

    @Override
    protected Province call() throws Exception {
        int current = model.getCurrent();

        while (current <= model.getPopulation()) {
            current += model.getSize();
            this.updateProgress(Math.min(current, model.getPopulation()), model.getPopulation());

            double percent = 100 / (double) model.getPopulation() * current;

            model.setCurrent(Math.min(current, model.getPopulation()));
            update(formatter.format(percent < 100 ? percent : 100) + "%");
        }

        model.setCurrent(Math.min(current, model.getPopulation()));
        return model;
    }

    private void update(String value) throws Exception {
        this.updateMessage(value);
        Thread.sleep(200);
    }
}
