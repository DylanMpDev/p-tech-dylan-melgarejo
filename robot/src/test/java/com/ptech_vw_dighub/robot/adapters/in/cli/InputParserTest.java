package com.ptech_vw_dighub.robot.adapters.in.cli;

import org.junit.jupiter.api.Test;

//import com.ptech_vw_dighub.robot.adapters.in.cli.InputParser;

import com.ptech_vw_dighub.robot.application.ParsedInput;
import com.ptech_vw_dighub.robot.application.RobotProgram;

import com.ptech_vw_dighub.robot.domain.Direction;
import com.ptech_vw_dighub.robot.domain.Grid;
import com.ptech_vw_dighub.robot.domain.Position;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InputParserTest {

    @Test
    void shouldParseSampleInput() {
        List<String> lines = List.of(
                "5 5",
                "1 2 N",
                "LMLMLMLMM",
                "3 3 E",
                "MMRMMRMRRM"
        );

        InputParser parser = new InputParser();
        ParsedInput parsed = parser.parse(lines);

        assertEquals(new Grid(5, 5), parsed.grid());
        assertEquals(2, parsed.robotPrograms().size());

        RobotProgram first = parsed.robotPrograms().get(0);
        assertEquals(new Position(1, 2), first.robot().position());
        assertEquals(Direction.N, first.robot().direction());
        assertEquals("LMLMLMLMM", first.instructions());

        RobotProgram second = parsed.robotPrograms().get(1);
        assertEquals(new Position(3, 3), second.robot().position());
        assertEquals(Direction.E, second.robot().direction());
        assertEquals("MMRMMRMRRM", second.instructions());
    }

    @Test
    void shouldFailWhenInstructionLineIsMissing() {
        List<String> lines = List.of(
                "5 5",
                "1 2 N"
        );

        InputParser parser = new InputParser();

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> parser.parse(lines));
        assertTrue(ex.getMessage().toLowerCase().contains("missing instruction"));
    }
}
