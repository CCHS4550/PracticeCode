package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;

public class Autonomous extends SubsystemBase{
   private  DriveTrain chassis = new DriveTrain();
   private  Intake shooter = new Intake();
    private AHRS gyro = new AHRS();

    public SequentialCommandGroup RegularAuto(){
    SequentialCommandGroup auto = new SequentialCommandGroup(
        new RunCommand(() -> shooter.shoot()),
        new RunCommand(() -> shooter.setZero()),
        new RunCommand(() -> chassis.driveDistance(5)),
        new RunCommand(() -> chassis.driveDistance(-5)),
        new RunCommand(() -> chassis.balance(gyro))
    );

    return auto;
    }

    

    
}
