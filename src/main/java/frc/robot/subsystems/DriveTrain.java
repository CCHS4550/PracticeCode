package frc.robot.subsystems;


import com.kauailabs.navx.frc.AHRS;
// import com.pathplanner.lib.PathPlannerTrajectory;
// import com.pathplanner.lib.commands.PPRamseteCommand;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

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

public class DriveTrain extends SubsystemBase{
    CCSparkMax fLeft = new CCSparkMax("fLeft", "fl", RobotMap.FRONT_LEFT, MotorType.kBrushless, IdleMode.kBrake, RobotMap.FRONT_LEFT_REVERSE);
    CCSparkMax fRight = new CCSparkMax("fRight", "fr", RobotMap.FRONT_RIGHT, MotorType.kBrushless, IdleMode.kBrake, RobotMap.FRONT_RIGHT_REVERSE);
    CCSparkMax bLeft = new CCSparkMax("bLeft", "bl", RobotMap.BACK_LEFT, MotorType.kBrushless, IdleMode.kBrake, RobotMap.BACK_LEFT_REVERSE);
    CCSparkMax bRight = new CCSparkMax("bRight", "br", RobotMap.BACK_RIGHT, MotorType.kBrushless, IdleMode.kBrake, RobotMap.BACK_RIGHT_REVERSE);

    MotorControllerGroup left = new MotorControllerGroup(fLeft, bLeft);
    MotorControllerGroup right = new MotorControllerGroup(fRight, bRight);

    DifferentialDrive driveTrain = new DifferentialDrive(left, right);

    PIDController gyroControl = new PIDController(0.5, 0, 0);
    public AHRS gyro = new AHRS(SPI.Port.kMXP);
    double slowmodeFactor = 1;

    public void axisDrive(double targetSpeed, double turnSpeed){
      driveTrain.arcadeDrive(targetSpeed * slowmodeFactor, turnSpeed * slowmodeFactor);
    }

    double kp = 0.1;
    double max = 1;
    public RunCommand moveTo(double goalPos){
      RunCommand res = new RunCommand(() -> {
        axisDrive(OI.normalize(kp * (goalPos - fLeft.getPosition()), -max, max), 0);
      }, this) {
        @Override public boolean isFinished(){
          return Math.abs(goalPos - fLeft.getPosition()) < 0.1;
        }
      };
      return res;
    }

    public RunCommand turnAngle(double angle){
      RunCommand res = new RunCommand(() -> {
        axisDrive(0, OI.normalize(kp * (angle - gyro.getAngle()), -max, max));
      }, this) {
        @Override
        public boolean isFinished(){
          return Math.abs(angle - gyro.getAngle()) < 0.5;
        }
      };
      return res;
    }

    public void toggleSlowmode(){
      slowmodeFactor = slowmodeFactor == 1 ? 1 : .5;
    }

     public RunCommand balance(){
      RunCommand res = new RunCommand(() -> {
        //what it does
        if(gyro.getRoll() > 2.5)
          axisDrive(.25, 0);
        else if (gyro.getRoll() < -2.5)
          axisDrive(-.25, 0);
     }, this) {
      @Override
      public boolean isFinished(){
        return Math.abs(gyro.getRoll()) < 0.5;
      }
     };
     return res;
    }
     

}