package frc.robot.subsystems;

import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.helpers.CCSparkMax;
import frc.maps.RobotMap;

public class Intake {
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

    CCSparkMax move = new CCSparkMax(null, null, 0, MotorType.kBrushless, null, false);


    public void shoot(){
        intakeBottom.set(0.8);
        intakeTop.set(0.8);
    }

    public void intake(){
        intakeBottom.set(-0.8);
        intakeTop.set(0.8);
    }

    /**
     * 
     * @param xVal: The 
     * @param yVal
     */
     
    public void moveIntake(double yVal){
        
        move.set( yVal > 0 ? 3: -3);
    }

    

    

}
