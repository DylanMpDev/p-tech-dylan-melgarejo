package com.ptech_vw_dighub.robot.application;

import java.util.ArrayList;
import java.util.List;


import com.ptech_vw_dighub.robot.domain.Grid;
import com.ptech_vw_dighub.robot.domain.Position;
import com.ptech_vw_dighub.robot.domain.Robot;
//import com.ptech_vw_dighub.robot.domain.Direction;

public class RobotSimulator {

    public List<SimulationResult> simulate(ParsedInput input) {
        Grid grid = input.grid();
        List<SimulationResult> results = new ArrayList<>();

        for (RobotProgram program : input.robotPrograms()) {
            Robot robot = program.robot();

            validateStart(robot, grid);

            for (char c : program.instructions().toCharArray()) {
                apply(robot, c, grid);
            }

            results.add(new SimulationResult(
                    robot.position().x(),
                    robot.position().y(),
                    robot.direction()
            ));
        }

        return results;
    }

    private void validateStart(Robot robot, Grid grid) {
        Position p = robot.position();
        if (!isInside(p, grid)) {
            throw new IllegalArgumentException("Robot starts outside the grid: " + p.x() + " " + p.y());
        }
    }

    private void apply(Robot robot, char instruction, Grid grid) {
        switch (instruction) {
            case 'L' -> robot.turnLeft();
            case 'R' -> robot.turnRight();
            case 'M' -> moveIfPossible(robot, grid);
            default -> throw new IllegalArgumentException("Invalid instruction: " + instruction);
        }
    }

    private void moveIfPossible(Robot robot, Grid grid) {
        Position current = robot.position();
        Position next = switch (robot.direction()) {
            case N -> current.north();
            case E -> current.east();
            case S -> current.south();
            case W -> current.west();
        };

        if (isInside(next, grid)) {
            robot.moveForward();
        }
        // si no está dentro, ignoramos el movimiento
    }

    private boolean isInside(Position p, Grid grid) {
        return p.x() >= 0 && p.y() >= 0 && p.x() <= grid.maxX() && p.y() <= grid.maxY();
    }
}
