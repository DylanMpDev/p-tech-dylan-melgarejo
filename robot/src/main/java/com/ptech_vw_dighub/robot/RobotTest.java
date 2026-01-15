package com.ptech_vw_dighub.robot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Test class for Robot functionality

class RobotTest {

    // Test methods would go here

    @Test
    void turnLeftFromNorthToWest() {
        Robot robot = new Robot(new Position(0, 0), Direction.N);
        robot.turnLeft();
        assertEquals(Direction.W, robot.getDirection());
    }

    @Test
    void moveForwardFacingNorth(){
        Robot robot = new Robot(new Position(1, 1), Direction.N);
        robot.moveForward();
        assertEquals(new Position(1, 2), robot.getPosition());
    }

}