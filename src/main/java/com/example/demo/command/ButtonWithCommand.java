package com.example.demo.command;

public class ButtonWithCommand {
    private Command command = new InitCommand();
    public ButtonWithCommand(Command command) {
        setCommand(command);
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressed() {
        command.execute();
    }
}
