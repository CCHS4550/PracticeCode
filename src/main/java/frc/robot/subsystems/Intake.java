package frc.robot.subsystems;

import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.helpers.CCSparkMax;
import frc.helpers.OI;
import frc.maps.ControlMap;
import frc.maps.RobotMap;

public class Intake extends SubsystemBase{
    /*
     * Method to shoot
     * Method to intake
     * Method to bring intake in and out with right controller stick use controller 1
     * 
     * 
     * 
     * 
     * 
     */

    

    CCSparkMax intakeTop = new CCSparkMax("IntakeTop", "itU", RobotMap.INTAKE_TOP, MotorType.kBrushless, null, false);
    CCSparkMax intakeBottom = new CCSparkMax("IntakeBottom", "itB", RobotMap.INTAKE_BOTTOM, MotorType.kBrushless, null, false);

    CCSparkMax move = new CCSparkMax("Moving", "Mo", RobotMap.ARM, MotorType.kBrushless, null, false);


    public void shoot(double shooting){
        intakeBottom.set(OI.normalize(shooting , -0.3 , 0.3));
        intakeTop.set( OI.normalize(-shooting , -0.3 , 0.3));
    }

    public void intake(double speed){
        intakeBottom.set(OI.normalize(speed, -0.3, 0.3));
        intakeTop.set(OI.normalize(-speed, -0.3, 0.3));
    }

   
     
    public void moveIntake(double speed){
        move.set(OI.normalize(speed, -0.3, 0.3));
    }

    public void setZero(){
        intakeBottom.set(0);
        intakeTop.set(0);
    }



    

    

}
