package com.ptech_vw_dighub.robot;

public record SimulationResult(int x, int y, Direction direction) {
    @Override
    public String toString() {
        return x + " " + y + " " + direction;
    }
}
