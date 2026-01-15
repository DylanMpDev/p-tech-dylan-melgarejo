package com.ptech_vw_dighub.robot;

// Record to represent a position on a 2D grid

public record Position(int x, int y) {

    public Position moveNorth() { return new Position(x, y + 1); }
    public Position moveSouth() { return new Position(x, y - 1); }
    public Position moveEast() { return new Position(x + 1, y); }
    public Position moveWest() { return new Position(x - 1, y); }

}


