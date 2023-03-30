package frc.robot.subsystems;

import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax.IdleMode;

import frc.helpers.CCSparkMax;
import frc.maps.RobotMap;

public class DriveTrain {
    CCSparkMax fLeft = new CCSparkMax("fLeft", "fl", RobotMap.FRONT_LEFT, MotorType.kBrushless, IdleMode.kBrake, RobotMap.FRONT_LEFT_REVERSE);
    CCSparkMax fRight = new CCSparkMax("fRight", "fr", RobotMap.FRONT_RIGHT, MotorType.kBrushless, IdleMode.kBrake, RobotMap.FRONT_RIGHT_REVERSE);
    CCSparkMax bLeft = new CCSparkMax("bLeft", "bl", RobotMap.BACK_LEFT, MotorType.kBrushless, IdleMode.kBrake, RobotMap.BACK_LEFT_REVERSE);
    CCSparkMax bRight = new CCSparkMax("bRight", "br", RobotMap.BACK_RIGHT, MotorType.kBrushless, IdleMode.kBrake, RobotMap.BACK_RIGHT_REVERSE);
}