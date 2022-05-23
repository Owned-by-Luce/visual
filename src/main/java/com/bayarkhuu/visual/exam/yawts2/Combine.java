package com.bayarkhuu.visual.exam.yawts2;

import java.util.List;

/**
 * Комбайн
 */
public class Combine {
    private int harvested;//Хурааж авсан нийт төмсний хэмжээ
    private List<Potato> field;//Комбайны хураах ёстой төмсний талбай

    public Combine(int harvested, List<Potato> field) {
        this.harvested = harvested;
        this.field = field;
    }

    public int getHarvested() {
        return harvested;
    }

    public void setHarvested(int harvested) {
        this.harvested = harvested;
    }

    public List<Potato> getField() {
        return field;
    }

    public void setField(List<Potato> field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return "Harvested: " + harvested;
    }
}