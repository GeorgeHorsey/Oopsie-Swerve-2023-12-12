package frc.robot.subsystems;

import com.ctre.phoenix.sensors.CANCoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.simulation.EncoderSim;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SwerveModule extends SubsystemBase {
  private CANCoder angleEncoder;
  
  private CANSparkMax angleMotor;
  
  private CANSparkMax driveMotor;
  
  private Encoder encoder;
  
  private EncoderSim simEncoder;
  
  private PIDController turnPID = new PIDController(0.01D, 0.0D, 0.0D);
  
  private String placement;
  
  public SwerveModule(String modulePlacement, int angleEncoderID, int angleMotorID, int driveMotorID, boolean angleMotorReversed, boolean driveMotorReversed) {
    this.angleEncoder = new CANCoder(angleEncoderID);
    this.angleMotor = new CANSparkMax(angleMotorID, CANSparkMaxLowLevel.MotorType.kBrushless);
    this.driveMotor = new CANSparkMax(driveMotorID, CANSparkMaxLowLevel.MotorType.kBrushless);
    this.driveMotor.setInverted(driveMotorReversed);
    this.angleMotor.setInverted(angleMotorReversed);
    this.turnPID.enableContinuousInput(-180.0D, 180.0D);
    this.placement = modulePlacement;
  }
  
  public void periodic() {
    SmartDashboard.putNumber(String.valueOf(this.placement) + " Module Angle", moduleAngle());
  }
  
  public double moduleAngle() {
    return this.angleEncoder.getAbsolutePosition();
  }
  
  public void setAngle(double theta) {
    SmartDashboard.putNumber(String.valueOf(this.placement) + " Angle", theta);
    double anglePID = MathUtil.clamp(this.turnPID.calculate(moduleAngle(), theta), -1.0D, 1.0D);
    SmartDashboard.putNumber(String.valueOf(this.placement) + " PID", anglePID);
    this.angleMotor.set(anglePID);
  }
  
  public Boolean angleTurnFinished() {
    return Boolean.valueOf(this.turnPID.atSetpoint());
  }
  
  public double getDistance() {
    return this.driveMotor.getEncoder().getPosition();
  }
  
  public void setSpeed(double speed) {
    SmartDashboard.putNumber(String.valueOf(this.placement) + " Speed", speed);
    this.driveMotor.set(speed);
  }
  
  public String getPlacement() {
    return this.placement;
  }
}
