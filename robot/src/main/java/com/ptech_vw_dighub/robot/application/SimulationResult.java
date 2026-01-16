package com.ptech_vw_dighub.robot.application;

import com.ptech_vw_dighub.robot.domain.Direction;



public record SimulationResult(int x, int y, Direction direction) {
    @Override
    public String toString() {
        return x + " " + y + " " + direction;
    }
}
