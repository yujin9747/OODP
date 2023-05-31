package com.example.demo.command;

public class InitCommand implements Command{
    public InitCommand() {

    }

    public void execute() {
        System.out.println("Initial Command");
    }
}
