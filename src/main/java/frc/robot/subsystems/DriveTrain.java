package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.helpers.CCSparkMax;
import frc.helpers.OI;
import frc.maps.RobotMap;

public class DriveTrain extends SubsystemBase{
    
    double currentSpeed = 0.0;
    CCSparkMax frontRight = new CCSparkMax("frontRight", "fr", RobotMap.FRONT_RIGHT, null, null, false, RobotMap.DRIVE_ENCODER);
    CCSparkMax backRight = new CCSparkMax("backRight", "br", RobotMap.BACK_RIGHT, null, null, false, RobotMap.DRIVE_ENCODER);

    

    CCSparkMax frontLeft = new CCSparkMax("frontLeft", "fr", RobotMap.FRONT_LEFT, MotorType.kBrushless, null, false, RobotMap.DRIVE_ENCODER);

    CCSparkMax backLeft = new CCSparkMax("backLeft","br", RobotMap.BACK_LEFT, MotorType.kBrushless, null, false, RobotMap.DRIVE_ENCODER);

    

    MotorControllerGroup left = new MotorControllerGroup(frontLeft , backLeft);
    MotorControllerGroup right = new MotorControllerGroup(frontRight, backRight);

        AHRS gyro = new AHRS();
/* 
    public void arcadeDrive(double xVal , double yVal){
        left.set(OI.normalize(yVal - xVal, -1, 1));
        right.set(OI.normalize(yVal + xVal , -1 , 1));
    }
*/
    public void axisDrive(double speed , double turnSpeed){
        DifferentialDrive dif = new DifferentialDrive(left, right);
        dif.arcadeDrive(0.5, turnSpeed);
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

        //does not work
    public RunCommand turnAngle(double angle){
        PIDController ang = new PIDController(0.01, 0, 0);

        return new RunCommand(() -> {
            double turnspeed = ang.calculate(gyro.getAngle() , gyro.getAngle() + angle);

            left.set(OI.normalize(turnspeed , -0.1 , 0.1));
            right.set(OI.normalize(-turnspeed , -0.1 , 0.1));
        });
    }

    public void balance(AHRS gyro2){
        PIDController bal = new PIDController(0.01 , 0, 0);
        double speed = 5; 
        double damp = Math.abs(bal.calculate(gyro.getYaw() , 0)) / 100;
        
        while(gyro.getYaw() >= 0){
            left.set(OI.normalize(speed * damp, -2, 2));
            right.set(OI.normalize(speed  * damp , -2 , 2));
        }
        
    }

   

    
    


  

    

    


}
