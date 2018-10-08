// DEFINE CODE PACKAGE      // All code used by your robot will reference this package. That code can be:
//      A)  Supplied by FTC
//      B)  Written by someone other than FTC (you, another team, etc.) and then
//          placed inside this code package
//
package org.firstinspires.ftc.teamcode;
//
// IMPORT PROGRAMMING ELEMENTS DESCRIBED ELSEWHERE IN THE CODE PACKAGE FOR USE IN THIS FILE
//      1. Classes
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//
//      2. Utilities
import com.qualcomm.robotcore.util.ElapsedTime;
//
//      3. Hardware Types (ONE import per TYPE of hardware, NOT for each INSTANCE of that TYPE of hardware)
import com.qualcomm.robotcore.hardware.DcMotor;

// Use private unless you need access from other classes.
        //
        // 2. Hardware
        //    NOTE:     This section tells the code that, later, these names will be used to refer to items on the robot.
        //    FORMAT:   HardwareType variableName;
        DcMotor leftDriveMotor;
        DcMotor rightDriveMotor;

        // SET HARDWARE VARIABLE VALUES
        // NOTE:  - The robot's pieces are named in the hardwareMap using the software on the ROBOT CONTROLLER PHONE.
        //        - Values after 'get' MUST match EXACTLY the names used when the robot configuration was
        //          built using the FTC Robot Controller app on the ROBOT CONTROLLER PHONE.
        //        - In this code, each hardware variable name matches the name of the corresponding item in
        //          the hardwareMap. This is not required, but is recommended because it keeps communication
        //          clear and usage consistent.
        // FORMAT:  variableName = hardwareMap.hardwareType.get("nameInHardwareMap");
        leftDriveMotor  = hardwareMap.dcMotor.get("leftDriveMotor");
        rightDriveMotor = hardwareMap.dcMotor.get("rightDriveMotor");



public class DC_Motor_and_Servo_Methods_v00 {
    //****************************************************************************************************************
    // BEGIN LOCALLY-DEFINED METHODS
    //****************************************************************************************************************
    // NOTE:    METHODS are sections of code that are written once but can be used ("called") by the program multiple times.
    //          - A METHOD can have all of its values set internally (see robotStop, below),
    //            or use values passed to it each time the program calls the METHOD (see driveForward, below).
    //          - VARIABLES allow the METHOD's code to be written once but adapt to different situations in the
    //            section of code that is calling it.
    //
    // ********
    // METHOD:  stopDriveMotors()
    // PURPOSE: Stop all motors at current location by setting all power to zero
    // FORMAT:  access level, return type or void, methodName(arguments){
    public void stopDriveMotors(){                                // The empty "()" section means that this method
        leftDriveMotor.setPower(MOTOR_STOP);                //      does not rely on values passed into it
        rightDriveMotor.setPower(MOTOR_STOP);               //      from the section of code that calls it, and
        //      uses the values entered directly here
        //      (MOTOR_STOP in this case)
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
    public double adjustTimeBasedOnPower(double Time, double Power){    // NOTE:    Nested IF / ELSE IF statements could be used
        //          For clarity, separate IF statements are used
        if (Power == DRIVE_POWER_MEDIUM){
            Time = Time * DRIVE_TIME_ADJUSTER_FOR_POWER_MED;            // Increase Time to compensate for reduced power, or
        }                                                               // Leave Power unchanged if Power is not DRIVE_POWER_MEDIUM
        //
        if (Power == DRIVE_POWER_SLOW){
            Time = Time * DRIVE_TIME_ADJUSTER_FOR_POWER_SLOW;           // Increase Time to compensate for reduced power, or
        }                                                               // Leave Power unchanged if Power is not DRIVE_POWER_SLOW
        return Time;
    }
    // END of METHOD adjustTimeBasedOnPower
    // ********
    //
    // ********
    // METHOD:  driveForward(Time,Power)
    // PURPOSE: Adjust the Time value based on the requested Power
    // FORMAT:  access level, return type or void, methodName(arguments){
    public void driveForward(double Time, double Power){                // The variable names Time and Power will be assigned
        //      to the values passed into the method, in the order
        //      they are received
        final double adjustedTime = adjustTimeBasedOnPower(Time,Power); // Declare new variable to hold result of calling the
        //      adjustTimeBasedOnPower METHOD
        //      and immediately define it as the result
        //      of that METHOD call
        //
        leftDriveMotor.setPower(Power);                                 // Run motor with passed Power value
        rightDriveMotor.setPower(Power);                                // Run motor with passed Power value
        sleep((long) adjustedTime);                                     // Wait here in code for duration of passed
        //      adjustedTime value
        //      (allows motors to turn for duration of adjustedTime)
    }
    // END of METHOD driveForward
    // ********
    // ********
    // METHOD:  spinRight(Time,Power)
    // PURPOSE: Adjust the Time value based on the requested Power
    // FORMAT:  access level, return type or void, methodName(arguments){
    public void spinRight(double Time, double Power){                   // The variable names Time and Power will be assigned
        //      to the values passed into the method, in the order
        //      they are received
        final double adjustedTime = adjustTimeBasedOnPower(Time,Power); // Declare new variable to hold result of calling the
        //      adjustTimeBasedOnPower METHOD
        //      and immediately define it as the result
        //      of that METHOD call
        //
        leftDriveMotor.setPower(Power);                                 // Run motor with passed Power value
        rightDriveMotor.setPower(-Power);                               // Run motor with passed Power value inverted
        //      so motor will rotate in reverse
        sleep((long) adjustedTime);                                     // Wait here in code for duration of passed
        //      adjustedTime value
        //      (allows motors to turn for duration of adjustedTime)
    }
    // END of METHOD spinRight
    // ********
    // ********
    // METHOD:  spinLeft(Time,Power)
    // PURPOSE: Adjust the Time value based on the requested Power
    // FORMAT:  access level, return type or void, methodName(arguments){
    public void spinLeft(long Time, double Power){                      // The variable names Time and Power will be assigned
        //      to the values passed into the method, in the order
        //      they are received
        final double adjustedTime = adjustTimeBasedOnPower(Time,Power); // Declare new variable to hold result of calling the
        //      adjustTimeBasedOnPower METHOD
        //      and immediately define it as the result
        //      of that METHOD call
        //
        leftDriveMotor.setPower(-Power);                                // Run motor with passed Power value inverted
        //      so motor will rotate in reverse
        rightDriveMotor.setPower(Power);                                // Run motor with passed Power value
        sleep((long) adjustedTime);                                     // Wait here in code for duration of passed
        //      adjustedTime value
        //      (allows motors to turn for duration of adjustedTime)
    }
    // END of METHOD spinLeft
    // ********
    // ********
}
// END of CLASS AUTON_withMethods_v01d
//
}
