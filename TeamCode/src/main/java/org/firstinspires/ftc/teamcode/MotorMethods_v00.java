// *************************************************************************************************************************
// *************************************************************************************************************************
// Edit Date:   October 11, 2018 @ 12:04
// Team Name:   Lightning Robotics
// Team Number: FRC862
// Code Type:   Repository for custom METHODS to be used by multiple OpMode files for AUTONOMOUS and TELEOP
//              for control of DC Motors and Servo Motors.
// NOTE:        This code is based on AUTON_withMethods_v01d, but moves custom METHODS to external location.
//              This code also uses the external CLASS, Constants.
// METHOD List: - stopDriveMotors
//              - adjustTimeBasedOnPower
//              - driveForward
//              - spinRight
//              - spinLeft
//
// *************************************************************************************************************************
// *************************************************************************************************************************
//
// DEFINE CODE PACKAGE      
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

public class MotorMethods_v00 {
    // ****************************************************************************************************************
    // BEGIN METHODS DEFINED HERE FOR USE IN OTHER FILES
    // ****************************************************************************************************************
    //
    // ********
    // METHOD:  stopDriveMotors()
    // PURPOSE: Stop all motors at current location by setting all power to zero
    // FORMAT:  access level, return type or void, methodName(arguments){
    public static void stopDriveMotors(DcMotor leftDriveMotor, DcMotor rightDriveMotor, double MOTOR_STOP){
        leftDriveMotor.setPower(MOTOR_STOP);
        rightDriveMotor.setPower(MOTOR_STOP);
    }
    // END of METHOD stopDriveMotors
    // ********
    //
    // ********
    // METHOD:  adjustTimeBasedOnPower(Time,Power)
    // PURPOSE: Adjust the Time value based on the requested Power value
    // NOTE:    == means "____ is currently equal to ____"
    //           = means "____ is now equal to ____"
    // FORMAT:  access level, return type or void, methodName(arguments){
    public static double adjustTimeBasedOnPower(double Time, double Power){
        if (Power == Constants.DRIVE_POWER_MEDIUM){
            Time = Time * Constants.DRIVE_TIME_ADJUSTER_FOR_POWER_MED;
        }
        //
        if (Power == Constants.DRIVE_POWER_SLOW){
            Time = Time * Constants.DRIVE_TIME_ADJUSTER_FOR_POWER_SLOW;
        }
        return Time;
    }
    // END of METHOD adjustTimeBasedOnPower
    // ********
    // ********
    // METHOD:  driveForward(Time,Power)
    // PURPOSE: Adjust the Time value based on the requested Power
    // FORMAT:  access level, return type or void, methodName(arguments){
    public static void driveForward(double Time, double Power){
        final double adjustedTime = adjustTimeBasedOnPower(Time,Power); 
        leftDriveMotor.setPower(Power);
        rightDriveMotor.setPower(Power);
        sleep((long) adjustedTime);
    }
    // END of METHOD driveForward
    // ********
    // ********
    // METHOD:  spinRight(Time,Power)
    // PURPOSE: Adjust the Time value based on the requested Power
    // FORMAT:  access level, return type or void, methodName(arguments){
    public static void spinRight(double Time, double Power){
        final double adjustedTime = adjustTimeBasedOnPower(Time,Power);
        leftDriveMotor.setPower(Power);
        rightDriveMotor.setPower(-Power);
        sleep((long) adjustedTime);
    }
    // END of METHOD spinRight
    // ********
    // ********
    // METHOD:  spinLeft(Time,Power)
    // PURPOSE: Adjust the Time value based on the requested Power
    // FORMAT:  access level, return type or void, methodName(arguments){
    public static void spinLeft(long Time, double Power){
        final double adjustedTime = adjustTimeBasedOnPower(Time,Power);
        leftDriveMotor.setPower(-Power);
        rightDriveMotor.setPower(Power);
        sleep((long) adjustedTime);
    }
    // END of METHOD spinLeft
    // ********
    // ********
}
// END of CLASS MotorMethods_v00
//
// *************************************************************************************************************************
// END OF FILE
// *************************************************************************************************************************
