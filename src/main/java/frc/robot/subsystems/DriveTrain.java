package frc.robot.subsystems;


import com.kauailabs.navx.frc.AHRS;
// import com.pathplanner.lib.PathPlannerTrajectory;
// import com.pathplanner.lib.commands.PPRamseteCommand;

import edu.wpi.first.wpilibj.SPI;

import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.diagnostics.DoubleEntry;
import frc.helpers.CCSparkMax;
import frc.helpers.OI;
import frc.maps.ControlMap;
import frc.maps.RobotMap;

public class DriveTrain {
    CCSparkMax fLeft = new CCSparkMax("fLeft", "fl", RobotMap.FRONT_LEFT, MotorType.kBrushless, IdleMode.kBrake, RobotMap.FRONT_LEFT_REVERSE);
    CCSparkMax fRight = new CCSparkMax("fRight", "fr", RobotMap.FRONT_RIGHT, MotorType.kBrushless, IdleMode.kBrake, RobotMap.FRONT_RIGHT_REVERSE);
    CCSparkMax bLeft = new CCSparkMax("bLeft", "bl", RobotMap.BACK_LEFT, MotorType.kBrushless, IdleMode.kBrake, RobotMap.BACK_LEFT_REVERSE);
    CCSparkMax bRight = new CCSparkMax("bRight", "br", RobotMap.BACK_RIGHT, MotorType.kBrushless, IdleMode.kBrake, RobotMap.BACK_RIGHT_REVERSE);

    MotorControllerGroup left = new MotorControllerGroup(fLeft, bLeft);
    MotorControllerGroup right = new MotorControllerGroup(fRight, bRight);

    PIDController gyroControl = new PIDController(0.5, 0, 0);
    public AHRS gyro = new AHRS(SPI.Port.kMXP);
    double slowmodeFactor = 1;

    public void axisDrive(double targetSpeed, double turnSpeed){
      arcade(targetSpeed * slowmodeFactor, turnSpeed * slowmodeFactor);
    }

    public void arcade(double yAxis, double xAxis){
      left.set(yAxis * .5);
      right.set(yAxis * .5);

    }

    public void toggleSlowmode(){
      slowmodeFactor = slowmodeFactor == 1 ? 1 : .5;
    }

    





     public void balance(double gyroAngle){
        arcade(gyroControl.calculate(gyroAngle), 0);
     }

}