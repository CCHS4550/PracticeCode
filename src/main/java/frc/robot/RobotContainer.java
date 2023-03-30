package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;

public class RobotContainer {


    public RobotContainer() {

    }

    public Command getAutoCommand() {
        return new RunCommand(() -> System.out.println("placeholder"));
    }
}
