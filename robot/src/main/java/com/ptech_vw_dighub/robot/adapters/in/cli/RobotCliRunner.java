package com.ptech_vw_dighub.robot.adapters.in.cli;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ptech_vw_dighub.robot.application.ParsedInput;
import com.ptech_vw_dighub.robot.application.RobotSimulator;
import com.ptech_vw_dighub.robot.application.SimulationResult;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

@Component
public class RobotCliRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        List<String> lines = readAllLinesFromStdIn();
        if (lines.isEmpty()) return;

        ParsedInput input = new InputParser().parse(lines);
        List<SimulationResult> results = new RobotSimulator().simulate(input);

        for (SimulationResult r : results) {
            System.out.println(r);
        }
    }

    private List<String> readAllLinesFromStdIn() throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            return reader.lines().toList();
        }
    }
}
