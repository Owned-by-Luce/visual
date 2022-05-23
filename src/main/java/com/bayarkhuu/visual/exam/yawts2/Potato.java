package com.bayarkhuu.visual.exam.yawts2;

public class Potato {
    private final int size;//Болц
    private final int time;//Нэгж хугацаа (Милсекундээр)
    private boolean harvested;

    public Potato(int size) {
        this.size = size;
        this.time = size == 1 ? 5 : size == 2 ? 4 : size == 3 ? 3 : size == 4 ? 2 : size == 5 ? 1 : 0;
        this.harvested = false;
    }

    public int getSize() {
        return size;
    }

    public int getTime() {
        return time;
    }

    public boolean isHarvested() {
        return harvested;
    }

    public void setHarvested(boolean harvested) {
        this.harvested = harvested;
    }


}
