package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ExampleSubsystem extends SubsystemBase {
  public CommandBase exampleMethodCommand() {
    return runOnce(() -> {
        
        });
  }
  
  public boolean exampleCondition() {
    return false;
  }
  
  public void periodic() {}
  
  public void simulationPeriodic() {}
}
