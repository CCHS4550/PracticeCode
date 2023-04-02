package frc.robot.autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;

public class MoveOutAutonomous extends SequentialCommandGroup{

    // Create constructor for when this auto is called in RobotContainer
    // Constuctor must contain sybsystems used so that they can be referenced
    public MoveOutAutonomous(DriveTrain chassis, Intake intake){

        // This adds the commands to the class that this class extends, SequentialCommandGroup
        // So, when you call this auto in RobotContainer, it will run these commands
        // NOTE: ALL LINES HERE MUST BE COMMANDS
        super.addCommands(
            chassis.moveTo(13)
        );
    }
}
