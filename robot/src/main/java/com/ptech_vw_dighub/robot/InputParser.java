package com.ptech_vw_dighub.robot;

import java.util.ArrayList;
import java.util.List;

public class InputParser {

    public ParsedInput parse(List<String> lines) {
        if (lines == null || lines.isEmpty()) {
            throw new IllegalArgumentException("Input is empty");
        }

        String firstLine = lines.get(0).trim();
        Grid grid = parseGrid(firstLine);

        List<RobotProgram> robotPrograms = new ArrayList<>();

        int i = 1;
        while (i < lines.size()) {
            String positionLine = lines.get(i).trim();
            if (positionLine.isBlank()) {
                i++;
                continue; // ignoramos líneas vacías si existen
            }

            if (i + 1 >= lines.size()) {
                throw new IllegalArgumentException("Missing instruction line for robot starting at line " + (i + 1));
            }

            String instructionsLine = lines.get(i + 1).trim();

            Robot robot = parseRobotPosition(positionLine);

            robotPrograms.add(new RobotProgram(robot, instructionsLine));

            i += 2; // avanzamos 2 líneas: posición + instrucciones
        }

        return new ParsedInput(grid, robotPrograms);
    }

    private Grid parseGrid(String line) {
        String[] parts = line.split("\\s+");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid grid line: " + line);
        }

        int maxX = Integer.parseInt(parts[0]);
        int maxY = Integer.parseInt(parts[1]);

        return new Grid(maxX, maxY);
    }

    private Robot parseRobotPosition(String line) {
        String[] parts = line.split("\\s+");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid robot position line: " + line);
        }

        int x = Integer.parseInt(parts[0]);
        int y = Integer.parseInt(parts[1]);
        Direction direction = Direction.valueOf(parts[2]);

        return new Robot(new Position(x, y), direction);
    }
}
