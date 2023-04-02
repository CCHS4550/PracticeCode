package frc.controlschemes;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.diagnostics.*;
import frc.helpers.ControlScheme;
import frc.helpers.OI;
import frc.maps.ControlMap;
import frc.robot.subsystems.DriveTrain;

public class DriveScheme implements ControlScheme {
    private static double defaultSpeed = .8;
    private static double slowModeSpeed = .5;
    private static double fastModeSpeed = 1;
    private static double speedFactor = defaultSpeed;
    private static DoubleEntry defaultSpeedIndicator = new DoubleEntry("Default Drive Speed", defaultSpeed);
    

    // Configure default controls (joysticks included)
    public static void configure(DriveTrain chassis, int port){
        chassis.setDefaultCommand(new RunCommand(() -> {
            speedFactor = setSpeedMode(port);
            chassis.axisDrive(
                OI.axis(port, ControlMap.L_JOYSTICK_VERTICAL) * speedFactor,
                OI.axis(port, ControlMap.R_JOYSTICK_HORIZONTAL) * speedFactor);
        }, chassis));

        configureButtons(chassis, port);
    }

    // Configure button controls
    private static void configureButtons(DriveTrain chassis, int port){
        new JoystickButton(controllers[port], ControlMap.DPAD_UP)
            .onTrue(new InstantCommand(() -> incrementDefaultSpeed()));

        new JoystickButton(controllers[port], ControlMap.DPAD_DOWN)
            .onTrue(new InstantCommand(() -> decrementDefaultSpeed()));
    }

    // Find speed mode based off of left and right trigger inputs.
    // Slow mode takes priority over fast
    private static double setSpeedMode(int port){
        if(OI.axis(port, ControlMap.RT) > .5){
            return slowModeSpeed;
        } else if (OI.axis(port, ControlMap.LT) > .5){
            return fastModeSpeed;
        } else {
            return defaultSpeed;
        }
    }

    // increase default speed by .1
    private static void incrementDefaultSpeed(){
        defaultSpeed = OI.normalize(defaultSpeed += .1, slowModeSpeed, fastModeSpeed);
        defaultSpeedIndicator.set(defaultSpeed);
    }

    // decrease default speed by .1
    private static void decrementDefaultSpeed(){
        defaultSpeed = OI.normalize(defaultSpeed -= .1, slowModeSpeed, fastModeSpeed);
        defaultSpeedIndicator.set(defaultSpeed);
    }
}
