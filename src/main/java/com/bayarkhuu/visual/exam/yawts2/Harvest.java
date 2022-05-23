package com.bayarkhuu.visual.exam.yawts2;

import javafx.concurrent.Task;

import java.text.DecimalFormat;

public class Harvest extends Task<Combine> {
    private final Combine combine;
    private final DecimalFormat formatter = new DecimalFormat("#.00");

    public Harvest(Combine combine) {
        this.combine = combine;
    }

    @Override
    protected Combine call() throws Exception {
        int current = combine.getHarvested();

        for (Potato potato : combine.getField()) {
            current++;
            potato.setHarvested(true);

            this.updateProgress(Math.min(current, combine.getField().size()), combine.getField().size());
            double percent = 100 / (double) combine.getField().size() * current;

            combine.setHarvested(Math.min(current, combine.getField().size()));
            update(formatter.format(percent) + "%",
                    combine.getField().stream().filter(Potato::isHarvested).mapToInt(Potato::getSize).sum(),
                    potato);
        }

        return combine;
    }

    private void update(String value, int sum, Potato potato) throws Exception {
        this.updateMessage(value);
        this.updateTitle("Болцын нийлбэр: " + sum);
        Thread.sleep(potato.getTime() * 100L);
    }
}
