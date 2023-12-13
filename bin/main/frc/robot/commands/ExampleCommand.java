package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.ExampleSubsystem;

public class ExampleCommand extends CommandBase {
  private final ExampleSubsystem m_subsystem;
  
  public ExampleCommand(ExampleSubsystem subsystem) {
    this.m_subsystem = subsystem;
    addRequirements(new Subsystem[] { (Subsystem)subsystem });
  }
  
  public void initialize() {}
  
  public void execute() {}
  
  public void end(boolean interrupted) {}
  
  public boolean isFinished() {
    return false;
  }
}
