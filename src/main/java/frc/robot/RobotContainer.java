package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.helpers.OI;
import frc.maps.ControlMap;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;

public class RobotContainer {

    DriveTrain chassis = new DriveTrain();
    Intake intake = new Intake();

    public RobotContainer() {

    }

    public Command getAutoCommand() {
        

        new RunCommand(() -> intake.moveIntake(OI.axis(1, ControlMap.L_JOYSTICK_VERTICAL) > 0 ? 0.3 : -0.3));

       new RunCommand(() -> chassis.driveDistance(5));
       
        return new RunCommand(() -> System.out.println("0"));

    }
}
