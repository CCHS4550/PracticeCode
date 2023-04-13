package frc.robot.subsystems;

import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.helpers.CCSparkMax;
import frc.helpers.OI;
import frc.maps.RobotMap;

public class Intake extends SubsystemBase {
    
    CCSparkMax flyWheelTop = new CCSparkMax("Fly Wheel Top", "fwt", RobotMap.INTAKE_TOP, MotorType.kBrushless, IdleMode.kBrake, RobotMap.INTAKE_TOP_REVERSE, RobotMap.DRIVE_ENCODER);
    CCSparkMax flyWheelBottom = new CCSparkMax("Fly Wheel Bottom", "fwb", RobotMap.INTAKE_BOTTOM, MotorType.kBrushless, IdleMode.kBrake, RobotMap.INTAKE_BOTTOM_REVERSE, RobotMap.DRIVE_ENCODER);

    MotorControllerGroup intake = new MotorControllerGroup(flyWheelBottom, flyWheelTop);

    CCSparkMax arm = new CCSparkMax("arm", "a", RobotMap.ARM, MotorType.kBrushless, IdleMode.kBrake, RobotMap.ARM_REVERSE, RobotMap.INTAKE_POSITION_CONVERSION_FACTOR);

    public void setSpin(double speed) {
        intake.set(speed);
    }
    
    public void setPivot(double speed) {
        arm.set(speed);
    }

    public SequentialCommandGroup spinForTime(double speed, double time) {
        SequentialCommandGroup res = new SequentialCommandGroup(
            new InstantCommand(() -> setSpin(speed), this),
            new WaitCommand(time),
            new InstantCommand(() -> setSpin(0), this)
        );
        return res;
    }
    
    double kp = 0.1;
    public RunCommand setPosition(double position) {
        return new RunCommand(() -> {
            arm.set(OI.normalize((position - arm.getPosition()) * kp, -0.2, 0.2));
        }, this) {
            @Override
            public boolean isFinished() {
                return Math.abs(position - arm.getPosition()) < 1;
            }
        };
    }

}
