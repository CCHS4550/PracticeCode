package frc.controlschemes;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.diagnostics.*;
import frc.helpers.ControlScheme;
import frc.helpers.OI;
import frc.maps.ControlMap;
import frc.robot.subsystems.Intake;

public class IntakeScheme implements ControlScheme {
    private static double defaultSpeed = .6;
    private static double slowModeSpeed = .3;
    private static double fastModeSpeed = .9;
    private static double spinSpeedFactor = defaultSpeed;
    private static DoubleEntry defaultSpeedIndicator = new DoubleEntry("Default Intake Speed", defaultSpeed);


    // Configure default controls (intake up/down and spin)
    public static void configure(Intake intake, int port){
        intake.setDefaultCommand(new RunCommand(() -> {
            spinSpeedFactor = setSpeedMode(port);
            intake.manageIntake(
                OI.axis(port, ControlMap.R_JOYSTICK_VERTICAL) * spinSpeedFactor,
                OI.axis(port, ControlMap.L_JOYSTICK_VERTICAL),
                OI.axis(port, ControlMap.LT) > .5 ? true : false);
        }, intake));

        configureButtons(intake, port);
    }

    // Configure button controls
    public static void configureButtons(Intake intake, int port){
        new JoystickButton(controllers[port], ControlMap.DPAD_UP)
        .onTrue(new InstantCommand(() -> incrementDefaultSpeed()));

        new JoystickButton(controllers[port], ControlMap.DPAD_DOWN)
            .onTrue(new InstantCommand(() -> decrementDefaultSpeed()));
    }
    // Find speed mode based off of left and right trigger inputs.
    // Slow mode takes priority over fast (slow mode likely unnecessary for intake)
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
