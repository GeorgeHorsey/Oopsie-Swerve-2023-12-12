package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.ExampleSubsystem;

public final class Autos {
  public static CommandBase exampleAuto(ExampleSubsystem subsystem) {
    return Commands.sequence(new Command[] { (Command)subsystem.exampleMethodCommand(), (Command)new ExampleCommand(subsystem) });
  }
  
  private Autos() {
    throw new UnsupportedOperationException("This is a utility class!");
  }
}
