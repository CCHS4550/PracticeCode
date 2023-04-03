package frc.robot.subsystems;

import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import frc.helpers.CCSparkMax;
import frc.maps.RobotMap;

public class DriveTrain {
    
    CCSparkMax frontRight = new CCSparkMax("frontRight", "fr", RobotMap.FRONT_RIGHT, null, null, false);
    CCSparkMax backRight = new CCSparkMax("backRight", "br", RobotMap.BACK_RIGHT, null, null, false);

    

    CCSparkMax frontLeft = new CCSparkMax("frontLeft", "fr", RobotMap.FRONT_LEFT, MotorType.kBrushless, null, false);

    CCSparkMax backLeft = new CCSparkMax("backLeft","br", RobotMap.BACK_LEFT, MotorType.kBrushless, null, false);

    

    MotorControllerGroup left = new MotorControllerGroup(frontLeft , backLeft);
    MotorControllerGroup rightSize = new MotorControllerGroup(frontRight, backRight);

    public void axisDrive(double speed, double turnSpeed){

    }

    

    


}
