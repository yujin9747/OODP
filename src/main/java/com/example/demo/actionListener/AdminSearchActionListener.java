package com.example.demo.actionListener;
import com.example.demo.command.ButtonWithCommand;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminSearchActionListener implements ActionListener {

    private ButtonWithCommand buttonWithCommand;

    public AdminSearchActionListener(ButtonWithCommand buttonWithCommand) {
        this.buttonWithCommand = buttonWithCommand;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        buttonWithCommand.pressed();
    }

}
