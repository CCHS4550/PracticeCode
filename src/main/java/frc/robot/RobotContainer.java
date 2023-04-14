package frc.robot;

import java.util.ResourceBundle.Control;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.helpers.OI;
import frc.maps.ControlMap;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.Autonomous;

public class RobotContainer{

    DriveTrain chassis = new DriveTrain();
    Intake intake = new Intake();
    Autonomous auto = new Autonomous();

    public RobotContainer() {
        
        chassis.setDefaultCommand(
            new RunCommand(() -> {

                chassis.axisDrive(OI.axis(0, ControlMap.L_JOYSTICK_VERTICAL) , OI.axis(0, ControlMap.R_JOYSTICK_HORIZONTAL));
                
            } )); 

        intake.setDefaultCommand(new RunCommand(() -> {

            intake.moveIntake(OI.axis(1 , ControlMap.L_JOYSTICK_VERTICAL));
            intake.intake(OI.axis(1 , ControlMap.R_JOYSTICK_VERTICAL));
        }));
    
        
    }
    public Command getAutoCommand() {
        
        return auto.RegularAuto();

    }
}
