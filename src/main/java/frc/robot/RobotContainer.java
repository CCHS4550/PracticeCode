package frc.robot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.helpers.OI;
import frc.maps.ControlMap;
import frc.robot.autonomous.AutoTest;
import frc.robot.subsystems.DriveTrain;

public class RobotContainer {

    DriveTrain chassis = new DriveTrain();
    public RobotContainer() {
        chassis.setDefaultCommand(new RunCommand(() -> chassis.axisDrive(OI.axis(0, ControlMap.L_JOYSTICK_VERTICAL), OI.axis(0, ControlMap.R_JOYSTICK_HORIZONTAL)), chassis));
    }

    public Command getAutoCommand() {
        return new AutoTest(chassis);
    }
}
