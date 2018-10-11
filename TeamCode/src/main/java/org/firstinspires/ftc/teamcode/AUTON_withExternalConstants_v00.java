// *************************************************************************************************************************
// *************************************************************************************************************************
// Edit Date:   October 11, 2018 @ 08:38
// Clone Date:  October 11, 2018 @ 08:36
// Team Name:   _____
// Team Number: _____
// Code Type:   OpMode for AUTONOMOUS
// NOTE:        This code is based on AUTON_withMethods_v01d,
//              but adds custom METHODS stored in this file.
// Description: Order of operations
//               0.  Start at predetermined location (positioned by drivers prior to game start)
//               1.  Drive FORWARD, FAST, to CHECKPOINT_ONE
//               2.  STOP driving
//               3.  Spin LEFT, FAST, 90 degrees
//               4.  STOP spinning
//               5.  Drive FORWARD, FAST, from CHECKPOINT ONE to CHECKPOINT TWO
//               6.  STOP driving
//               7.  Spin RIGHT, FAST, 45 degrees
//               8.  STOP spinning
//               9.  Wait for Teleop
// *************************************************************************************************************************
// *************************************************************************************************************************
//
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
//
// DEFINE OpMode
// NOTE:    OpMode is the name for a set of code that contains the instructions the robot
//              will perform. It is a CLASS inside the ftc_app code package supplied by FTC.
//          The NAME property:
//            - Can be any text that helps the user identify this program.
//            - Will be displayed on the DRIVER CONTROL phone when the user chooses which program to run
//            - Does NOT need to be the same as the FILENAME, but often is. The most common reason
//              to name the OpMode differently from the FILE is to allow the OpMode name as displayed on
//              the DRIVER CONTROL phone to have a shortened name that sorts in a desired order.
//          The GROUP property can be any text that helps the user group this program with related programs
// FORMAT:  @type(name="OpMode_Name", group="GroupName")
@Autonomous(name="AUTON_withExternalConstants_v00", group="Derry_FTC_Templates")
//
// DEFINE class
// NOTE:    - All JAVA files must have at least one public CLASS, but can have more,
//            as long as they are private.
//          - This file will create one CLASS, and that CLASS will be an OpMode that
//            extends the pre-defined CLASS named LinearOpMode, which is supplied
//            by FTC as part of the CODE PACKAGE.
//          - Not all CLASSES are OpModes.
//          - The public CLASS name MUST match the FILENAME (EXcluding the ".java" extension)
// FORMAT:  access level, class class_name, extends NameOfClass_this_new_class_extends_(if_any) {
public class AUTON_withExternalConstants_v00 extends LinearOpMode {
    // DECLARE OpMode MEMBERS
    // 1. Utilities
    //    FORMAT:   access level, UtilityName runtime = new UtilityName();
    private ElapsedTime runtime = new ElapsedTime();        // Use private unless you need access from other classes.
    //
    // 2. Hardware
    //    NOTE:     This section tells the code that, later, these names will be used to refer to items on the robot.
    //    FORMAT:   HardwareType variableName;
    DcMotor leftDriveMotor;
    DcMotor rightDriveMotor;
    //
    // CONSTANTS were defined here but have been moved to Constants CLASS in separate Constants.java file
  //
    @Override
    // Override is a note to the compiler stating that you expect that you are replacing a METHOD
    //    with the same name in the parent (extends ______ class) with this METHOD. This way, if you typo/change
    //    the METHOD name you will get an error, stopping you from having two METHODS when you expect only one.
    //
    // Call runOpMode() METHOD from the parent CLASS of LinearOpMode
    // FORMAT:  access_level, return_type or void, methodName(arguments), type of errors or exception {
    public void runOpMode() throws InterruptedException  {              // "void" means that this METHOD does not return
        //      data to any code that calls it. It
        //      does NOT mean "invalid."
        // "InterruptedException" keeps the program
        //    from freezing completely if there is an error
        //    that it does not know how to handle
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
        //
        // SET DC MOTOR DIRECTIONS
        // NOTE:    "Reverse" any motor that runs backwards (relative to desired "forward" motion of element powered
        //          by that motor, such as a drive wheel or extension arm) when powered by a positive power value
        // FORMAT:  hardwareName.setDirection(DcMotor.Direction.DIRECTION)
        leftDriveMotor.setDirection(DcMotor.Direction.REVERSE);
        rightDriveMotor.setDirection(DcMotor.Direction.FORWARD);
        //
        // Display status and OpMode name on controller phone
        // FORMAT:  telemetry.desiredAction("arguments");
        telemetry.addData("Status", "Initialized");       // Specific info to be sent to controller phone
        telemetry.update();                                             // Send info to controller phone
        //
        // SET ALL MOTORS TO DESIRED STARTING STATUS
        //      DC Motors
        leftDriveMotor.setPower(MOTOR_STOP);                            // All DC motors STOPPED
        rightDriveMotor.setPower(MOTOR_STOP);
        //
        // ****************************************************************************************************************
        // END OF PREPARATIONS
        // ****************************************************************************************************************
        //
        // WAIT for driver to press PLAY
        waitForStart();                                                 // The waitForStart() METHOD is part of
        //    the LinearOpMode CLASS,
        //    which is defined elsewhere in
        //    the FTC resource code
        //
        // ****************************************************************************************************************
        // AFTER driver presses PLAY, the ROBOT CONTROLLER PHONE will execute the code below this line
        // ****************************************************************************************************************
        //
        telemetry.addData("Status", "Running");                         // Specific info to be sent to controller phone
        telemetry.update();                                             // Send info to controller phone
        //
        //   0.  Start at predetermined location (positioned by drivers prior to game start)
        //       with robot gripper holding a pre-loaded item
        //
        //   1.  Drive FORWARD, FAST, to CHECKPOINT_ONE

        telemetry.addData("Status", "Drive FORWARD");       // Specific info to be sent to controller phone
        telemetry.update();                                             // Send info to controller phone



        driveForward(DRIVE_TIME_START_TO_CHECKPOINT_ONE,DRIVE_POWER_FAST);
        //
        //   2.  STOP driving
        stopDriveMotors();                                      // Call stopDriveMotors METHOD to set drive motor
                                                                //      powers to zero
        sleep(1000);                                //  Wait 1 second
        //
        //   3.  Spin LEFT, FAST, 90 degrees

        telemetry.addData("Status", "Spin LEFT 90deg");       // Specific info to be sent to controller phone
        telemetry.update();                                             // Send info to controller phone



        spinLeft(DRIVE_TIME_90_DEG_TURN,DRIVE_POWER_MEDIUM);
        //
        //   4.  STOP spinning
        stopDriveMotors();
        sleep(1000);                                //  Wait 1 second
        //
        //   5.  Drive FORWARD, FAST, from CHECKPOINT ONE to CHECKPOINT TWO

        telemetry.addData("Status", "Drive FORWARD");       // Specific info to be sent to controller phone
        telemetry.update();                                             // Send info to controller phone


        driveForward(DRIVE_TIME_CHECKPOINT_ONE_TO_TWO,DRIVE_POWER_FAST);
        //
        //   6.  STOP driving
        stopDriveMotors();
        sleep(1000);                                //  Wait 1 second
        //
        //   7.  Spin RIGHT, FAST, 45 degrees

        telemetry.addData("Status", "Spin RIGHT 45deg");       // Specific info to be sent to controller phone
        telemetry.update();                                             // Send info to controller phone


        spinRight(DRIVE_TIME_45_DEG_TURN,DRIVE_POWER_FAST);
        //
        //   8.  STOP spinning
        stopDriveMotors();
        //
        //   9.  Wait for Teleop
    }
    // END of METHOD runOpMode
    //
    // ****************************************************************************************************************
    // END of AUTONOMOUS code
    // ****************************************************************************************************************
    //
    // ****************************************************************************************************************
    // BEGIN LOCALLY-DEFINED METHODS
    // ****************************************************************************************************************
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
// END of CLASS AUTON_withExternalConstants_v00
//
// *************************************************************************************************************************
// END OF FILE
// *************************************************************************************************************************
