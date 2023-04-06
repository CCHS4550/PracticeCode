package frc.robot.autonomous;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.DriveTrain;

public class AutoTest extends SequentialCommandGroup {
    public AutoTest(DriveTrain chassis) {
        super.addCommands(
            chassis.driveDistance(10),
            new WaitCommand(1),
            chassis.turnAngle(90)
        );
    }
}
