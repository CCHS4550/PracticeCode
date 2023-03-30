package frc.robot.subsystems;

import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import frc.helpers.CCSparkMax;
import frc.maps.RobotMap;

public class DriveTrain {
    CCSparkMax fl = new CCSparkMax("frontLeft", "fl", RobotMap.FRONT_LEFT, MotorType.kBrushless, IdleMode.kBrake, false);
    CCSparkMax fr = new CCSparkMax("frontRight", "fr", 0, null, null, false);
    CCSparkMax bl = new CCSparkMax("null", null, 0, null, null, false);
    CCSparkMax br = new CCSparkMax(null, null, 0, null, null, false);
}
