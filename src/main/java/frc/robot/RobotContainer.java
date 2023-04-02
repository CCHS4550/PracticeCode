package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.controlschemes.DriveScheme;
import frc.controlschemes.IntakeScheme;
import frc.robot.autonomous.MoveOutAutonomous;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;

public class RobotContainer {
    DriveTrain chassis = new DriveTrain();
    Intake intake = new Intake();

    public RobotContainer() {
        DriveScheme.configure(chassis, 0);
        IntakeScheme.configure(intake, 1);
    }

    public Command getAutoCommand() {
        return new MoveOutAutonomous(chassis, intake);
    }
}