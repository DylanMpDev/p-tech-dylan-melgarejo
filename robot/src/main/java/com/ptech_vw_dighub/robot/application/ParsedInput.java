package com.ptech_vw_dighub.robot.application;

import java.util.List;

import com.ptech_vw_dighub.robot.domain.Grid;

public record ParsedInput(Grid grid, List<RobotProgram> robotPrograms) { 
    
}
