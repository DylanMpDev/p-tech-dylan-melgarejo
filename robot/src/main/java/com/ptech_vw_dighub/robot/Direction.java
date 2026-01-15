package com.ptech_vw_dighub.robot;

// Enum to represent the four cardinal directions

public enum Direction {
    N, E, S, W;

    public Direction turnLeft(){
        return switch(this){
            case N -> W;
            case W -> S;
            case S -> E;
            case E -> N;
        };
    }

    public Direction turnRight(){
        return switch(this){
            case N -> E;
            case E -> S;
            case S -> W;
            case W -> N;
        };
    }

}