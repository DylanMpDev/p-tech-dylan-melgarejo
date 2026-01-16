package com.ptech_vw_dighub.robot.application;

import org.junit.jupiter.api.Test;

//import com.ptech_vw_dighub.robot.application.ParsedInput;
//import com.ptech_vw_dighub.robot.application.RobotProgram;
//import com.ptech_vw_dighub.robot.application.RobotSimulator;
//import com.ptech_vw_dighub.robot.application.SimulationResult;

import com.ptech_vw_dighub.robot.domain.Direction;
import com.ptech_vw_dighub.robot.domain.Grid;
import com.ptech_vw_dighub.robot.domain.Position;
import com.ptech_vw_dighub.robot.domain.Robot;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RobotSimulatorTest {

    @Test
    void shouldMatchProvidedSampleOutput() {
        ParsedInput input = new ParsedInput(
                new Grid(5, 5),
                List.of(
                        new RobotProgram(new Robot(new Position(1, 2), Direction.N), "LMLMLMLMM"),
                        new RobotProgram(new Robot(new Position(3, 3), Direction.E), "MMRMMRMRRM")
                )
        );

        RobotSimulator simulator = new RobotSimulator();
        List<SimulationResult> results = simulator.simulate(input);

        assertEquals(List.of("1 3 N", "5 1 E"),
                results.stream().map(SimulationResult::toString).toList());
    }

    @Test
    void shouldIgnoreMoveOutsideGrid() {
        ParsedInput input = new ParsedInput(
                new Grid(2, 2),
                List.of(
                        new RobotProgram(new Robot(new Position(2, 2), Direction.N), "M")
                )
        );

        RobotSimulator simulator = new RobotSimulator();
        List<SimulationResult> results = simulator.simulate(input);

        assertEquals("2 2 N", results.get(0).toString());
    }

    @Test
    void shouldFailIfRobotStartsOutsideGrid() {
        ParsedInput input = new ParsedInput(
                new Grid(2, 2),
                List.of(
                        new RobotProgram(new Robot(new Position(3, 3), Direction.N), "M")
                )
        );

        RobotSimulator simulator = new RobotSimulator();

        assertThrows(IllegalArgumentException.class, () -> simulator.simulate(input));
    }
}
