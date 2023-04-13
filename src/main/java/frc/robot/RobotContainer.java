package frc.robot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.helpers.OI;
import frc.maps.ControlMap;
import frc.robot.autonomous.AutoTest;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;

public class RobotContainer {

    DriveTrain chassis = new DriveTrain();
    Intake intake = new Intake();
    public RobotContainer() {
        chassis.setDefaultCommand(new RunCommand(() -> chassis.axisDrive(OI.axis(0, ControlMap.L_JOYSTICK_VERTICAL), OI.axis(0, ControlMap.R_JOYSTICK_HORIZONTAL)), chassis));
        intake.setDefaultCommand(new RunCommand(() -> intake.setPivot(OI.normalize(OI.axis(1, ControlMap.L_JOYSTICK_VERTICAL), -0.2, 0.2)), intake));
        configureButtons();
    }
    public void configureButtons() {
        new JoystickButton(OI.joystickArray[1], ControlMap.B_BUTTON)
            .onTrue(new InstantCommand(() -> chassis.toggleSlowMode()));
        Trigger b = new JoystickButton(OI.joystickArray[1], ControlMap.A_BUTTON)
            .onTrue(new InstantCommand(() -> intake.setSpin(0.6)));
        new JoystickButton(OI.joystickArray[1], ControlMap.X_BUTTON)
            .onTrue(new InstantCommand(() -> intake.setSpin(-0.6)))
            .negate().and(b.negate()).onTrue(new InstantCommand(() -> intake.setSpin(0)));
    }
    public Command getAutoCommand() {
        return new AutoTest(chassis);
    } 
}
