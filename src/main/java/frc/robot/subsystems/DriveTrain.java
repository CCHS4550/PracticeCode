package frc.robot.subsystems;
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.helpers.CCSparkMax;
import frc.helpers.OI;
import frc.maps.RobotMap;

public class DriveTrain extends SubsystemBase {

    AHRS gyro = new AHRS(SPI.Port.kMXP);

    CCSparkMax frontLeft = new CCSparkMax("Front Left", "fl", RobotMap.FRONT_LEFT, MotorType.kBrushless, IdleMode.kBrake, RobotMap.FRONT_LEFT_REVERSE, RobotMap.DRIVE_ENCODER);
    CCSparkMax frontRight = new CCSparkMax("Front Right", "fr", RobotMap.FRONT_RIGHT, MotorType.kBrushless, IdleMode.kBrake, RobotMap.FRONT_RIGHT_REVERSE, RobotMap.DRIVE_ENCODER);
    CCSparkMax backLeft = new CCSparkMax("Back Left", "bl", RobotMap.BACK_LEFT, MotorType.kBrushless, IdleMode.kBrake, RobotMap.BACK_LEFT_REVERSE, RobotMap.DRIVE_ENCODER);
    CCSparkMax backRight = new CCSparkMax("Back Right", "br", RobotMap.BACK_RIGHT, MotorType.kBrushless, IdleMode.kBrake, RobotMap.BACK_RIGHT_REVERSE, RobotMap.DRIVE_ENCODER);

    MotorControllerGroup left = new MotorControllerGroup(frontLeft, backLeft);
    MotorControllerGroup right = new MotorControllerGroup(frontRight, backRight);

    DifferentialDrive driveTrain = new DifferentialDrive(left, right);

    public void axisDrive(double speed, double turnSpeed) {
        driveTrain.arcadeDrive(speed, turnSpeed);
    }

    double kp = 0.1;
    public RunCommand driveDistance(double distance) {
        RunCommand res = new RunCommand(() -> {
            axisDrive(OI.normalize(kp * (distance - frontLeft.getPosition()), 0, 0.6), 0);
        }, this) {
            @Override
            public boolean isFinished(){
                return Math.abs(distance - frontLeft.getPosition()) < (1.0/12);
            }
        };
        return res;
    }

    double kpAng = 0.1;
    public RunCommand turnAngle(double angle) {
        gyro.reset();
        RunCommand res = new RunCommand(() -> {
            axisDrive(0, OI.normalize(kp * (angle - gyro.getAngle()), 0, 0.3));
        }, this) {
            @Override
            public boolean isFinished() {
                return angle - gyro.getAngle() < 5;
            }
        };
        return res;
    }
}
