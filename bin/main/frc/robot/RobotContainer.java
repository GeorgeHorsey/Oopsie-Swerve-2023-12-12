package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.Autos;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.Teleop;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.ExampleSubsystem;

public class RobotContainer {
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  
  private final Drivetrain r_drivetrain = new Drivetrain();
  
  private final CommandXboxController m_driverController = new CommandXboxController(0);
  
  private final Teleop r_teleop = new Teleop(this.m_driverController, this.r_drivetrain);
  
  public void setDefaultCommands() {
    CommandScheduler.getInstance().setDefaultCommand((Subsystem)this.r_drivetrain, (Command)this.r_teleop);
  }
  
  public void init() {
    setDefaultCommands();
  }
  
  public RobotContainer() {
    init();
    configureBindings();
  }
  
  private void configureBindings() {
    (new Trigger(this.m_exampleSubsystem::exampleCondition))
      .onTrue((Command)new ExampleCommand(this.m_exampleSubsystem));
    this.m_driverController.b().whileTrue((Command)this.m_exampleSubsystem.exampleMethodCommand());
  }
  
  public Command getAutonomousCommand() {
    return (Command)Autos.exampleAuto(this.m_exampleSubsystem);
  }
}
