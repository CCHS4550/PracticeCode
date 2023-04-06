package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.helpers.CCSparkMax;
import frc.helpers.OI;
import frc.maps.RobotMap;

public class DriveTrain {
    
    double currentSpeed = 0.0;
    CCSparkMax frontRight = new CCSparkMax("frontRight", "fr", RobotMap.FRONT_RIGHT, null, null, false, RobotMap.DRIVE_ENCODER);
    CCSparkMax backRight = new CCSparkMax("backRight", "br", RobotMap.BACK_RIGHT, null, null, false, RobotMap.DRIVE_ENCODER);

    

    CCSparkMax frontLeft = new CCSparkMax("frontLeft", "fr", RobotMap.FRONT_LEFT, MotorType.kBrushless, null, false, RobotMap.DRIVE_ENCODER);

    CCSparkMax backLeft = new CCSparkMax("backLeft","br", RobotMap.BACK_LEFT, MotorType.kBrushless, null, false, RobotMap.DRIVE_ENCODER);

    

    MotorControllerGroup left = new MotorControllerGroup(frontLeft , backLeft);
    MotorControllerGroup right = new MotorControllerGroup(frontRight, backRight);

        AHRS gyro = new AHRS();

    public void arcadeDrive(double xVal , double yVal){
        left.set(OI.normalize(yVal - xVal, -1, 1));
        right.set(OI.normalize(yVal + xVal , -1 , 1));
    }

    public void axisDrive(double turnSpeed){
        arcadeDrive((currentSpeed * currentSpeed) * Math.signum(currentSpeed) , (turnSpeed * turnSpeed) * Math.signum(turnSpeed));
    }

    public void moveTo(){

    }

    public RunCommand driveDistance(double distance){
        PIDController pid = new PIDController(0.1, 0, 0);
       return new RunCommand(() -> {

        double power = pid.calculate(frontRight.getPosition() , distance);

        right.set(OI.normalize(power, -0.1 , 0.1));
        left.set(OI.normalize(power, -0.1 , 0.1));

       }
       );
    }

    public void balance(double yaw){
        if(yaw > 2){
            left.set(-2);
            right.set(-2);
        }
        else if(yaw < 2){
            left.set(2);
            right.set(2);
        }
        else{
            left.set(0);
            right.set(0);
        }
        
    }
    


  

    

    


}
