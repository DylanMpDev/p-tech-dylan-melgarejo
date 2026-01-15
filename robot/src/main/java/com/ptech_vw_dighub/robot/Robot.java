package com.ptech_vw_dighub.robot;

public class Robot {
    private Position position;
    private Direction direction;

    public Robot(Position position, Direction direction) {
        this.position = position;
        this.direction = direction;
    }

    public Position position() { return position; }
    public Direction direction() { return direction; }

    public void turnLeft() { direction = direction.turnLeft(); }
    public void turnRight() { direction = direction.turnRight(); }

    public void moveForward() {
        position = switch (direction) {
            case N -> position.north();
            case E -> position.east();
            case S -> position.south();
            case W -> position.west();
        };
    }
}