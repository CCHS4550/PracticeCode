package frc.robot.subsystems;

import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.helpers.CCSparkMax;
import frc.helpers.OI;
import frc.maps.RobotMap;

public class Intake extends SubsystemBase{
    // Intake up/down motor
    private final CCSparkMax arm = new CCSparkMax("Arm", "arm", RobotMap.ARM, MotorType.kBrushless, IdleMode.kBrake, RobotMap.ARM_REVERSE);

    // Intake top/bottom spinning motors
    private final CCSparkMax intakeTop = new CCSparkMax("Intake Top", "it", RobotMap.INTAKE_TOP, MotorType.kBrushless, IdleMode.kCoast, RobotMap.INTAKE_TOP_REVERSE);
    private final CCSparkMax intakeBottom = new CCSparkMax("Intake Bottom", "ib", RobotMap.INTAKE_BOTTOM, MotorType.kBrushless, IdleMode.kCoast, RobotMap.INTAKE_BOTTOM_REVERSE);

    // Intake speed limits
    public final double intakeMax = .5;
    public final double intakeMin = -.5;

    // General managing of intake
    public void manageIntake(double intakeSpeed, double armSpeed, boolean onlyBottom){
        spinIntake(intakeSpeed, onlyBottom);
        moveArm(armSpeed);
    }

    // Spin intake wheels
    public void spinIntake(double speed, boolean onlyBottom){
        intakeBottom.set(OI.normalize(speed, intakeMin, intakeMax));
        if(!onlyBottom){
            intakeTop.set(OI.normalize(speed, intakeMin, intakeMax));
        }
    }

    // Move intake up or down
    public void moveArm(double speed){
        arm.set(speed);
    }
}