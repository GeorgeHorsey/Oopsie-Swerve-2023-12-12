package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.Drivetrain;

public class Teleop extends CommandBase {
  private CommandXboxController xboxController;
  
  private Drivetrain drivetrain;
  
  public Teleop(CommandXboxController xboxController_, Drivetrain drivetrain_) {
    addRequirements(new Subsystem[] { (Subsystem)drivetrain_ });
    this.xboxController = xboxController_;
    this.drivetrain = drivetrain_;
  }
  
  public void initialize() {}
  
  public void execute() {
    this.drivetrain.translateSpin(this.xboxController.getLeftX(), this.xboxController.getLeftY(), this.xboxController.getRightX());
  }
  
  public void end(boolean interrupted) {}
  
  public boolean isFinished() {
    return false;
  }
}
