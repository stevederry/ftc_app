//*************************************************************************************************************************
//***************************************** THIS FILE IS A TEACHING TEMPLATE **********************************************
// Edit Date:   September 28, 2018 @ 15:13
// Clone Date:  September 28, 2018 @ 13:43
// Team Name:   _____
// Team Number: _____
// Code Type:   OpMode for AUTONOMOUS
// Description: Brief description of what this code does:
//               0.  Start at predetermined location (positioned by drivers prior to game start)
//               1.  Drive FORWARD, FAST, for 3 seconds
//               2.  STOP
//               3.  Spin LEFT, SLOWLY, for 1 second
//               4.  STOP
//               5.  Drive FORWARD, SLOWLY, for 2 seconds
//               6.  STOP
//               7.  Spin RIGHT, SLOWLY, for 2 seconds
//               8.  STOP
//               9.  Extend arm OUTWARD, SLOWLY, for 1 second
//              10.  STOP arm
//              11.  Set gripper to OPEN position
//              12.  Retract arm INWARD, SLOWLY, for 1 second
//              13.  STOP
//              14.  Wait for Teleop
//*************************************************************************************************************************
//*************************************************************************************************************************
//
// DEFINE CODE PACKAGE      // All code used by your robot will reference this package. That code can be:
                            //      A)  Supplied by FTC
                            //      B)  Written by someone other than FTC (you, another team, etc.) and then
                            //          placed inside this code package
package org.firstinspires.ftc.teamcode;
//
// IMPORT PROGRAMMING ELEMENTS DESCRIBED ELSEWHERE IN THE CODE PACKAGE FOR USE IN THIS CODE
//      1. Classes (specific)
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//
//      2. Utilities (specific)
import com.qualcomm.robotcore.util.ElapsedTime;
//
//      3. Hardware (types: ONE import per TYPE of hardware, NOT for each INSTANCE of that TYPE of hardware)
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
// 
// DEFINE OpMode
// NOTE:    OpMode is the name for a set of code that contains the instructions the robot will perform. It is a class
//          inside the ftc_app code package supplied by FTC.
// FORMAT:  @type(name="OpMode_Name", group="GroupName") 
@Autonomous(name="Template_AUTON_no_custom_methods_v00", group="Derry_FTC_Templates")
//
// DEFINE class
// NOTE:    All JAVA files must have at least one CLASS
// FORMAT:  access level, class class_name, extends NameOfClass this new class extends (if any) {
public class Template_AUTON_no_custom_methods_v00 extends LinearOpMode {
    //
    // DECLARE OpMode MEMBERS
    // 1. Utilities
    //    FORMAT:   access level, UtilityName runtime = new UtilityName();
    private ElapsedTime runtime = new ElapsedTime();        // Use private unless you need access from other classes.
    //
    // 2. Hardware (DECLARE and INTIALIZE variables at the same time)
    //    NOTE:     - This section tells the code that, later, these names will be used to refer to items on the robot.
    //              - The robot's pieces are named in the HardwareMap using the software on the ROBOT CONTROLLER PHONE.
    //              - Values after 'get' MUST match EXACTLY the names used when the robot configuration was 
    //                built using the FTC Robot Controler app on the ROBOT CONTROLLER PHONE.
    //              - In this code, each hardware variable name matches the name of the corresponding item in 
    //                the hardware map. This is not required, but is recommended because it keeps communication
    //                clear and usage consistent.
    //    FORMAT:   HardwareType hardwareVariableName = hardwareMap.hardwareType.get("nameAsAssignedInHardwareMap");             
    DcMotor leftDriveMotor  = hardwareMap.dcMotor.get("leftDriveMotor");
    DcMotor rightDriveMotor = hardwareMap.dcMotor.get("rightDriveMotor");
    DcMotor armMotor        = hardwareMap.dcMotor.get("armMotor");                        
    Servo   gripperServo    = hardwareMap.servo.get("gripperServo";                    
    //
    // SET DC MOTOR DIRECTIONS
    // NOTE:    "Reverse" any motor that runs backwards (relative to desired "forward" motion of element pwoered
    //          by that motor, such as a drive wheel or extension arm) when powered by a positive power value
    // FORMAT:  hardwareName.setDirection(DcMotor.Direction.DIRECTION)
    leftDriveMotor.setDirection(DcMotor.Direction.REVERSE);     
    rightDriveMotor.setDirection(DcMotor.Direction.FORWARD);    
    armMotor.setDirection(DcMotor.Direction.FORWARD);
    //
    // DEFINE CODE CONSTANTS
    // NOTE:    CONSTANTS should generally be defined here (outside of METHOD bodies),
    //          instead of inside runOpMode() or any other METHOD,
    //          especially if you ever want to access them from outside of this CLASS.
    //
    //          CONSTANTS can also be defined in separate JAVA file(s) as long as:
    //          1.  Those files are part of the same project as the file (like this one) that needs to use the CONSTANTS
    //          2.  The CONSTANTS are declared as public so that they can be accessed from outside the separate JAVA file(s)
    //
    // FORMAT:  access level, static yes/no, final yes/no, value type, value name, assigned value
    //          - public means it can be accessed from other classes
    //          - static means there is only one copy no matter how many instances of the CLASS you create
    //          - final means its value never changes (constant)
    //          - long, double, etc. is the type of value held by the variable
    //
    // Drive powers (speeds): all values use range of 0 to 1
    public static final double MOTOR_STOP               = 0;
    public static final double DRIVE_POWER_FAST         = 0.8;
    public static final double DRIVE_POWER_SLOW         = DRIVE_POWER_FAST / 2);
    public static final double ARM_MOTOR_MOVE           = .2;
    //
    // Position address values for servos
    public static final double GRIPPER_SERVO_OPEN       = 0;            // Open to receive game elements
    public static final double GRIPPER_SERVO_CLOSED     = 200;          // Closed to grip game elements
    //
    @Override
    // Override is a note to the compiler stating that you expect that you are replacing a METHOD
    //      with the same name in the parent (extends ______ class) with this METHOD. That way if you typo/change
    //      the METHOD signature you will get an error, stopping you from having two METHODS when you expect only one.
    //
    // Call runOpMode() METHOD from the parent CLASS of LinearOpMode
    // FORMAT:  access level, return type or void, methodName(arguments), type of errors or exception {
    public void runOpMode() throws InterruptedException  {              // "void" means that this METHOD does not return
                                                                        //      data to any code that calls this it. It
                                                                        //      does NOT mean "invalid."
                                                                        // "InterruptedException" keeps the program 
                                                                        //    from freezing completely if there is an error
                                                                        //    that it does not know how to handle
        // Display status and OpMode name on controller phone
        // FORMAT:  telemetry.desiredAction("arguments");
        telemetry.addData("Status", "Initialized", "name");             // Specific info to be sent to controller phone
        telemetry.update();                                             // Send info to controller phone
        //
        // SET ALL MOTORS TO DESIRED STARTING STATUS
        //      DC Motors
        leftDriveMotor.setPower(0);
        rightDriveMotor.setPower(0);                            
        armMotor.setPower(0);      
        //
        //      Servo Motors
        gripperServo.setPosition(GRIPPER_SERVO_CLOSED);                 // Set SERVO motor to desired start position
                                                                        //      (gripping item) using a
                                                                        //      variable defined above
        //
        //****************************************************************************************************************
        // END OF PREPARATIONS
        //****************************************************************************************************************
        //
        // WAIT for driver to press PLAY
        waitForStart();                                                 // The waitForStart() METHOD is part of
                                                                        //    the LinearOpMode CLASS,
                                                                        //    which is defined elsewhere in
                                                                        //    the FTC resource code
        //
        //****************************************************************************************************************
        // AFTER driver presses PLAY, execute code below this line
        //****************************************************************************************************************
        //
        //   0.  Start at predetermined location (positioned by drivers prior to game start)
        //   1.  Drive FORWARD, FAST, for 3 seconds
        leftDriveMotor.setPower(DRIVE_POWER_FAST);
        rightDriveMotor.setPower(DRIVE_POWER_FAST);
        sleep(3000);                                            //  Drive for 3000 miliseconds (3 seconds)
                                                                //  Sleep(X) pauses code execution so that motors
                                                                //      can turn for the time entered as X                                                                
        //
        //   2.  STOP
        leftDriveMotor.setPower(MOTOR_STOP);                    //  You could enter 0 here instead of the variable MOTOR_STOP,
        rightDriveMotor.setPower(MOTOR_STOP);                   //      but this keeps the code readable    
        //
        //   3.  Spin LEFT, SLOWLY, for 2 seconds
        leftDriveMotor.setPower(-DRIVE_POWER_FAST);             //  Negative value to make motor spin wheel backward
        rightDriveMotor.setPower(DRIVE_POWER_FAST);             //  When left motor spins backward, and
                                                                //      right motor spins forward, then the
                                                                //      robot will spin to its left
        sleep(1000);                                            //  Spin for 1 second
        //
        //   4.  STOP
        leftDriveMotor.setPower(MOTOR_STOP); 
        rightDriveMotor.setPower(MOTOR_STOP);
        //
        //   5.  Drive FORWARD, SLOWLY, for 2 seconds
        leftDriveMotor.setPower(DRIVE_POWER_SLOW);
        rightDriveMotor.setPower(DRIVE_POWER_SLOW);
        sleep(2000);                                            //  Drive motors for 2 seconds
        //
        //   6.  STOP
        leftDriveMotor.setPower(MOTOR_STOP); 
        rightDriveMotor.setPower(MOTOR_STOP);
        //
        //   7.  Spin RIGHT, SLOWLY, for 2 seconds
        leftDriveMotor.setPower(DRIVE_POWER_FAST);  
        rightDriveMotor.setPower(-DRIVE_POWER_FAST);            //  Negative value to make motor spin wheel backward
                                                                //  When right motor spins backward, and
                                                                //      left motor spins forward, then the
                                                                //      robot will spin to its right
        //   8.  Extend arm OUTWARD, SLOWLY, for 1 second
        armMotor.setPower(ARM_MOTOR_MOVE); 
        sleep(1000);                                            //  Extend arm for 1 second
        //
        //   9.  STOP arm
        armMotor.setPower(MOTOR_STOP); 
        //
        //  10.  Set gripper to OPEN position
        gripperServo.setPosition(GRIPPER_SERVO_OPEN);
        //
        //  11.  Retract arm INWARD, SLOWLY, for 1 second
        armMotor.setPower(-ARM_MOTOR_MOVE);                     //  Negative value to make gear that moves arm, 
                                                                //      and retracts arm
        sleep(1000);                                            //  Retract arm for 1 second
        //  12.  STOP arm
        armMotor.setPower(MOTOR_STOP); 
        //
        //  13.  Wait for Teleop
    }
    // END of METHOD runOpMode
    //
    //****************************************************************************************************************
    // END of AUTONOMOUS code 
    //****************************************************************************************************************
    //
    //****************************************************************************************************************
    // BEGIN LOCALLY-DEFINED METHODS
    //****************************************************************************************************************
    // NOTE:    METHODS are sections of code that are written once but can be used ("called") by the program multiple times. 
    //          -   A METHOD can have all of its values set internally (see robotStop, below),
    //              or use values passed to it each time the program calls the METHOD (see driveForward, below).
    //          -   VARIABLES allow the METHOD's code to be written once but adapt to different situations in the
    //              section of code that is calling it.
    //
    //          METHODS are defined in one of two places:
    //          1.  In separate files that are part of your overall group of code
    //              files, such as runOpMode(), as used below. The runOpMode METHOD
    //              is in a file supplied by FTC. You can write your own files
    //              that contain METHODS, as well (as this file does).
    //          2.  Inside this file, in the LOCALLY-DEFINED METHODS section, below.    //
    //
    //****************************************************************************************************************
    // END of LOCALLY-DEFINED METHODS
    //****************************************************************************************************************
}
// END of CLASS Template_AUTON_v05a
//
//*************************************************************************************************************************
// END OF FILE
//*************************************************************************************************************************
