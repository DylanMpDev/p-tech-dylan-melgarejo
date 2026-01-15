package com.ptech_vw_dighub.robot;


// Class to represent a robot on a 2D grid

public class Robot {

    private Position position;
    private Direction direction;

    public Robot(Position position, Direction direction) {
        this.position = position;
        this.direction = direction;
    }

    public Position getPosition() {
        return position;
    }

    Public Direction getDirection() {
        return direction;
    }

    public void turnLeft() {
        this.direction = this.direction.turnLeft();
    }

    public void turnRight() {
        this.direction = this.direction.turnRight();
    }
    
    public void moveForward() {
        this.position = switch (this.direction) {
            case N -> this.position.moveNorth();
            case E -> this.position.moveEast();
            case S -> this.position.moveSouth();
            case W -> this.position.moveWest();
        };
    }

}
