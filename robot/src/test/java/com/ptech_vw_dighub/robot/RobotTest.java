package com.ptech_vw_dighub.robot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RobotTest {

    @Test
    void turnsLeftCorrectly() {
        Robot robot = new Robot(new Position(0, 0), Direction.N);
        robot.turnLeft();
        assertEquals(Direction.W, robot.direction());
    }

    @Test
    void turnsRightCorrectly() {
        Robot robot = new Robot(new Position(0, 0), Direction.N);
        robot.turnRight();
        assertEquals(Direction.E, robot.direction());
    }

    @Test
    void movesForwardNorth() {
        Robot robot = new Robot(new Position(1, 1), Direction.N);
        robot.moveForward();
        assertEquals(new Position(1, 2), robot.position());
    }
}