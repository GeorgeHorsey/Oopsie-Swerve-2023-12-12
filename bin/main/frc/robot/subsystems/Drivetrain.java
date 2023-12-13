package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {
  private SwerveModule frontRightMod = new SwerveModule(
      "Front Right", 
      0, 0, 
      1, 
      Constants.swerveConstants.swerveModuleFR.angleMotorReversed.booleanValue(), Constants.swerveConstants.swerveModuleFR.driveMotorReversed.booleanValue());
  
  private SwerveModule frontLeftMod = new SwerveModule(
      "Front Left", 
      1, 2, 
      3, 
      Constants.swerveConstants.swerveModuleFL.angleMotorReversed.booleanValue(), Constants.swerveConstants.swerveModuleFL.driveMotorReversed.booleanValue());
  
  private SwerveModule backRightMod = new SwerveModule(
      "Back Right", 
      2, 4, 
      5, 
      Constants.swerveConstants.swerveModuleBR.angleMotorReversed.booleanValue(), Constants.swerveConstants.swerveModuleBR.driveMotorReversed.booleanValue());
  
  private SwerveModule backLeftMod = new SwerveModule(
      "Back Left", 
      3, 6, 
      7, 
      Constants.swerveConstants.swerveModuleBL.angleMotorReversed.booleanValue(), Constants.swerveConstants.swerveModuleBL.driveMotorReversed.booleanValue());
  
  public void periodic() {}
  
  public void translate(double inputX, double inputY) {
    double angle = Math.atan(inputY / inputX) * 57.29577951308232D;
    this.frontRightMod.setAngle(angle);
    this.frontLeftMod.setAngle(angle);
    this.backRightMod.setAngle(angle);
    this.backLeftMod.setAngle(angle);
    double input = Math.sqrt(Math.pow(inputX, 2.0D) + Math.pow(inputY, 2.0D));
    this.frontRightMod.setSpeed(input);
    this.frontLeftMod.setSpeed(input);
    this.backRightMod.setSpeed(input);
    this.backLeftMod.setSpeed(input);
  }
  
  public void spin(double inputX) {
    double angle = 45.0D;
    this.frontRightMod.setAngle(angle);
    this.frontLeftMod.setAngle(angle);
    this.backRightMod.setAngle(angle);
    this.backLeftMod.setAngle(angle);
    this.frontRightMod.setSpeed(inputX);
    this.frontLeftMod.setSpeed(inputX);
    this.backRightMod.setSpeed(inputX);
    this.backLeftMod.setSpeed(inputX);
  }
  
  public void translateSpin(double speedX, double speedY, double turnX) {
    double TurnVectorXM1 = turnX * Math.cos(0.7853981633974483D);
    double TurnVectorYM1 = turnX * Math.sin(0.7853981633974483D);
    double TurnVectorXM2 = turnX * Math.cos(2.356194490192345D);
    double TurnVectorYM2 = turnX * Math.sin(2.356194490192345D);
    double TurnVectorXM3 = turnX * Math.cos(3.9269908169872414D);
    double TurnVectorYM3 = turnX * Math.sin(3.9269908169872414D);
    double TurnVectorXM4 = turnX * Math.cos(5.497787143782138D);
    double TurnVectorYM4 = turnX * Math.sin(5.497787143782138D);
    double M1VectorAddedX = TurnVectorXM1 + speedX;
    double M1VectorAddedY = TurnVectorYM1 + speedY;
    double M2VectorAddedX = TurnVectorXM2 + speedX;
    double M2VectorAddedY = TurnVectorYM2 + speedY;
    double M3VectorAddedX = TurnVectorXM3 + speedX;
    double M3VectorAddedY = TurnVectorYM3 + speedY;
    double M4VectorAddedX = TurnVectorXM4 + speedX;
    double M4VectorAddedY = TurnVectorYM4 + speedY;
    double M1VectorAngle = Math.atan2(M1VectorAddedX, M1VectorAddedY) * 57.29577951308232D;
    double M2VectorAngle = Math.atan2(M2VectorAddedX, M2VectorAddedY) * 57.29577951308232D;
    double M3VectorAngle = Math.atan2(M3VectorAddedX, M3VectorAddedY) * 57.29577951308232D;
    double M4VectorAngle = Math.atan2(M4VectorAddedX, M4VectorAddedY) * 57.29577951308232D;
    double M1VectorLength = Math.sqrt(Math.pow(M1VectorAddedX, 2.0D) + Math.pow(M1VectorAddedY, 2.0D));
    double M2VectorLength = Math.sqrt(Math.pow(M2VectorAddedX, 2.0D) + Math.pow(M2VectorAddedY, 2.0D));
    double M3VectorLength = Math.sqrt(Math.pow(M3VectorAddedX, 2.0D) + Math.pow(M3VectorAddedY, 2.0D));
    double M4VectorLength = Math.sqrt(Math.pow(M4VectorAddedX, 2.0D) + Math.pow(M4VectorAddedY, 2.0D));
    double vectorLengthMax1 = Math.max(Math.abs(M1VectorLength), Math.abs(M2VectorLength));
    double vectorLengthMax2 = Math.max(Math.abs(M3VectorLength), Math.abs(M4VectorLength));
    double vectorLengthMaxT = Math.max(vectorLengthMax1, vectorLengthMax2);
    double M1VectorLengthNorm = 0.0D;
    double M2VectorLengthNorm = 0.0D;
    double M3VectorLengthNorm = 0.0D;
    double M4VectorLengthNorm = 0.0D;
    SmartDashboard.putNumber("Vector Max Length", vectorLengthMaxT);
    if (vectorLengthMaxT >= 1.0D) {
      M1VectorLengthNorm = M1VectorLength / vectorLengthMaxT;
      M2VectorLengthNorm = M2VectorLength / vectorLengthMaxT;
      M3VectorLengthNorm = M3VectorLength / vectorLengthMaxT;
      M4VectorLengthNorm = M4VectorLength / vectorLengthMaxT;
    } else {
      M1VectorLengthNorm = M1VectorLength;
      M2VectorLengthNorm = M2VectorLength;
      M3VectorLengthNorm = M3VectorLength;
      M4VectorLengthNorm = M4VectorLength;
    } 
    this.frontRightMod.setAngle(M1VectorAngle);
    this.frontLeftMod.setAngle(M2VectorAngle);
    this.backRightMod.setAngle(M3VectorAngle);
    this.backLeftMod.setAngle(M4VectorAngle);
    this.frontRightMod.setSpeed(M1VectorLengthNorm);
    this.frontLeftMod.setSpeed(M2VectorLengthNorm);
    this.backRightMod.setSpeed(M3VectorLengthNorm);
    this.backLeftMod.setSpeed(M4VectorLengthNorm);
  }
}
