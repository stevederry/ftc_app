//***************************************** THIS FILE TO BECOME TEACHING TEMPLATE *****************************************
// Edit Date:   September 17, 2018 @ 14:21
// Clone Date:	September 18, 2018 @ 08:38
// Team Name:   _____
// Team Number: _____
// Code Type:   OpMode for AUTONOMOUS
// Description: Brief description of what this code does, such as:
//              1. Start at predetermined location (positioned by drivers prior to game start)
//              2. Drive forward to make contact with game object, then pause to let object flex/bounce/roll/slide
//              3. Spin left to push object off its original position, then pause to let object flex/bounce/roll/slide
//              4. Spin right to return to original orientation and prepare to park on object's original location
//              5. Drive forward onto object's original location, then stop
//              6. Set gripper to open position
//              7. Wait for Teleop
//*************************************************************************************************************************
//
// DEFINE CODE PACKAGE
package org.firstinspires.ftc.teamcode;
//
// IMPORT PROGRAMMING ELEMENTS DESCRIBED ELSEWHERE FOR USE IN THIS CODE
//      OpModes (specific)
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//
//      Hardware types (ONE import per TYPE of hardware, NOT for each INSTANCE of that type of hardware)
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
//
//      Utilities (specific)
import com.qualcomm.robotcore.util.ElapsedTime;
// 
// DEFINE OpMode
// FORMAT: @type(name="OpMode_Name",group="GroupName") 
@Autonomous(name="Template_AUTON_v06", group="Derry_FTC_Templates")
//
// DEFINE Class
// FORMAT: access level, class class_name, extends name of class this new class extends (if any) {
public class Template_AUTON_v06 extends LinearOpMode {
    //
    // DECLARE OpMode MEMBERS
    //   Utilities
    //   FORMAT: access level, UtilityName = new UtilityName(); starting value
    private ElapsedTime runtime = new ElapsedTime();        // Use private unless you need access from other classes.
    //
    //   Hardware
    //   FORMAT: hardware type, specificNameOfHardware = starting_value;
    DcMotor leftDriveMotor  = null;                         // One line for each hardware item
    DcMotor rightDriveMotor = null;                         // Name before '=' MUST match EXACTLY the names used when the
    DcMotor sweeperMotor    = null;                         //   robot configuration was built using the FTC Robot Controler app
    Servo gripperServo      = null;                         //   on the robot controller phone
    Servo sweeperServo      = null;
    Servo armServo          = null;
    //
    // DEFINE CODE CONSTANTS
    // NOTE: Constants should generally be defined here (outside of METHOD bodies) 
    //          instead of inside runOpMode() or any other METHOD,
    //          especially if you ever want to access them from outside of this CLASS
    // FORMAT: access level, static yes/no, final yes/no, value type, value name, assigned value
    //          - public means it can be accessed from other classes
    //          - static means there is only one copy no matter how many instances of the CLASS you create
    //          - final means its value never changes (constant)
    //          - long, double, etc. is the type of value held by the variable
    //
    // Drive times: all values are in milliseconds
    public static final long DRIVE_TIME_TO_OBJECT       = 10000;        // All times assume motors are using DRIVE_POWER_FAST value
    public static final long DRIVE_TIME_OBJECT_TO_BASE  = 2000;         
    public static final long DRIVE_TIME_45_DEG_TURN     = 1000;
    public static final long DRIVE_TIME_90_DEG_TURN     = DRIVE_TIME_45_DEG_TURN * 2;
    //
    // Drive powers (speeds): all values use range of 0 to 1
    public static final double DRIVE_POWER_FAST                     = .8;
    public static final double DRIVE_POWER_ADJUSTER                 = 2;
    public static final double DRIVE_POWER_MEDIUM                   = DRIVE_POWER_FAST / DRIVE_POWER_ADJUSTER;
    public static final double DRIVE_POWER_SLOW                     = DRIVE_POWER_FAST / (DRIVE_POWER_ADJUSTER * 2);
    public static final double DRIVE_TIME_ADJUSTER_FOR_POWER_MED    = DRIVE_POWER_ADJUSTER;
    public static final double DRIVE_TIME_ADJUSTER_FOR_POWER_SLOW   = DRIVE_POWER_ADJUSTER * 2;

    //
    // Position address values for servos
    public static final double GRIPPER_SERVO_START      = 10;           // Open and stowed to fit inside 18" sizing cube
                                                                        //      but not clear of other moving parts of robot
    public static final double GRIPPER_SERVO_REST       = 50;           // Open and fully clear of all moving parts of robot
    public static final double GRIPPER_SERVO_OPEN       = 100;          // Open to receive game elements
    public static final double GRIPPER_SERVO_GRIP_LG    = 150;          // Closed to grip large game elements
    public static final double GRIPPER_SERVO_GRIP_SM    = 200;          // Closed to grip small game elements
    public static final double GRIPPER_SERVO_CLOSED     = 250;          // Closed completely
    //
    @Override
    // Override is a note to the compiler stating that you expect that you are replacing a METHOD
    //   with the same name in the parent (extends ______ class) with this METHOD. That way if you typo/change
    //   the METHOD signature you will get an error, stopping you from having two METHODS when you expect only one.
    //
    // call runOpMode() METHOD from the parent CLASS of LinearOpMode
    public void runOpMode() throws InterruptedException  {              // "Interrupted exception" keeps the program 
                                                                        //    from freezing completely if there is an error
                                                                        //    that it does not know how to handle
        // display status and OpMode name on controller phone
        telemetry.addData("Status", "Initialized", "name");             // Specific info to send to controller phone
        telemetry.update();                                             // Send info now
        //
        // INITIALIZE HARDWARE VARIABLES
        // FORMAT: hardware variable name = location within hardware map (" value as defined in hardware map ");
        //   Values after 'get' MUST match EXACTLY the names used when the
        //      robot configuration was built using the FTC Robot Controler app
        //      on the robot controller phone.
        //   In this code, each hardware variable name matches the name of the corresponding item in the hardware map. This
        //      is not required, but is recommended because it keeps communication clear and usage consistent.
        leftDriveMotor  = hardwareMap.dcMotor.get("leftDriveMotor");
        rightDriveMotor = hardwareMap.dcMotor.get("rightDriveMotor");
        sweeperMotor    = hardwareMap.dcMotor.get("sweeperMotor");
        gripperServo    = hardwareMap.servo.get("gripperServo");
        //
        // SET DC MOTOR DIRECTIONS
        // "Reverse" any motor that runs backwards (relative to "forward" direction of robot) when powered by positive value
        // FORMAT: hardware_name.setDirection(DcMotor.Direction.DIRECTION)
        leftDriveMotor.setDirection(DcMotor.Direction.REVERSE);     
        rightDriveMotor.setDirection(DcMotor.Direction.FORWARD);    
        sweeperMotor.setDirection(DcMotor.Direction.FORWARD);       // Assumes sweeperMotor is same orientation as rightDriveMotor
        //
        // SET ALL MOTORS TO DESIRED STARTING STATUS
        //      DC Motors
        stopRobot();                        // Use METHOD call to set all DC motors to STOP (power value = 0)
                                            // METHODS are defined in one of two places:
                                            //   1. In separate files that are part of your overall group of code
                                            //      files, such as runOpMode(), as used above. The runOpMode METHOD
                                            //      is in a file supplied by FTC. You can write your own separate files
                                            //      that contain METHODS, as well (as this file does).
                                            //   2. Inside this file, in the LOCALLY-DEFINED METHODS section, below.
        //      Servo Motors
        gripperServo.setPosition(GRIPPER_SERVO_START);              // Set SERVO motor to desired start position
                                                                    //      using variable defined above
        //
        //****************************************************************************************************************
        // END OF PREPARATIONS
        //****************************************************************************************************************
        //
        // WAIT for driver to press PLAY
        waitForStart();                     // The waitForStart() METHOD is part of the LinearOpMode CLASS,
                                            //      which is defined elsewhere in the FTC resrouce code
        //
        //****************************************************************************************************************
        // AFTER driver presses PLAY, execute code below this line
        //****************************************************************************************************************
        //
        // 1. Start at predetermined location (positioned by drivers prior to game start)
        //
        // 2. Drive forward to make contact with game object, then pause to let object flex/bounce/roll/slide
        driveForward((DRIVE_TIME_TO_OBJECT,DRIVE_POWER_FAST);   // Arguments MUST be in order expected by method
        stopRobot();                                            // Stop      
        sleep((long) 2);                                        // 2 seconds allows Object to bounce/flex before rogbot moves again
        //
        // 3. Spin left to push object off its base, then pause to let object flex/bounce/roll
        spinLeft(DRIVE_TIME_45_DEG_TURN,DRIVE_POWER_MEDIUM);    // Spin 45 deg. to left
        stopRobot();                                            // Stop        
        sleep((long) 2);                                        // 2 seconds allows Object to bounce/flex before rogbot moves again
        //
        // 4. Spin right to prepare to park on object's original location
        spinRight(DRIVE_TIME_45_DEG_TURN,DRIVE_POWER_MEDIUM);   // Spin 45 deg. to right to return to original orientation
        //
        // 5. Drive forward onto object's original location, then stop
        driveForward(DRIVE_TIME_OBJECT_TO_BASE,DRIVE_POWER_SLOW);    
        stopRobot();                                            // Final stop until beginning of Teleop
        //
        // 6. If any parts of robot need to be repositioned (arms, etc.) to prepare for Teleop,
        //    place that code here before next "}" character
        //
        // Open gripper hand
        gripperServo.setPosition(GRIPPER_SERVO_OPEN);
        //
        // 7. Wait for Teleop
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
    //   Methods are small sections of code that are written once but can be used ("called")
    //   by the program multiple times. A method can have all of its values set internally (see robotStop, below)
    //   or use values passed to it each time the program calls the method (see driveForward, below). Variables allow the
    //   method's code to be written once but adapt to different situations in the section of code that is calling it.
    //
    // METHOD stopRobot()
    //    stop all motors at current location by setting all power to zero
    public void stopRobot(){                                // The empty "()" section means that this method
        leftDriveMotor.setPower(0);                         //      does not rely on values passed into it
        rightDriveMotor.setPower(0);                        //      from the section of code that calls it,
        sweeperMotor.setPower(0);                           //      and uses the values entered directly here (0 in this case)
    }                                                       
    // END of METHOD stopRobot
    //
    // METHOD adjustTimeBasedOnPower(Time,Power)                
    public double adjustTimeBasedOnPower(double Time, double Power){    // Adjust the Time value based on the requested Power 
                                                                        // NOTE: For clarity, separate IF statements are used below.
                                                                        //      Nested IF / ELSE IF statements could be used
        if (Power = DRIVE_POWER_MEDIUM){                             
            Time = Time * DRIVE_TIME_ADJUSTER_FOR_POWER_MED;            //  Increase Time to compensate for reduced power
        }                                                               //  Leave Power unchanged if Power is not DRIVE_POWER_MEDIUM
                                                                        //
        if (Power = DRIVE_POWER_SLOW){
            Time = Time * DRIVE_TIME_ADJUSTER_FOR_POWER_SLOW;           //  Increase Time to compensate for reduced power
        }                                                               //  Leave Power unchanged if Power is not DRIVE_POWER_SLOW
        return Time;
    }
    // END of METHOD adjustTimeBasedOnPower
    //
    // METHOD driveForward(Time,Power)
    public void driveForward(double Time, double Power){    // The variable names Time and Power will be assigned
                                                            //      to the values passed into the method, in the order
                                                            //      they are received
                                                            //
        adjustTimeBasedOnPower(Time,Power)                  // Send Time and Power to adjustTimeBasedOnPower METHOD
                                                            //      to see if Time value should be adjusted
                                                            //
        leftDriveMotor.setPower(Power);                     // Run motor with passed Power value
        rightDriveMotor.setPower(Power);                    // Run motor with passed Power value
        sleep((long) Time);                                 // Wait here in code for duration of passed Time value
                                                            //      (allows motors to turn for duration of Time)
    } 
    // END of METHOD driveForward
    //
    // METHOD spinRight(Time,Power)
    public void spinRight(double Time, double Power){       // The variable names Time and Power will be assigned
                                                            //      to the values passed into the method, in the order
                                                            //      they are received
                                                            //
        adjustTimeBasedOnPower(Time,Power)                  // Send Time and Power to adjustTimeBasedOnPower METHOD
                                                            //      to see if Time value should be adjusted
                                                            //
        leftDriveMotor.setPower(Power);                     // Run motor with passed Power value
        rightDriveMotor.setPower(-Power);                   // Run motor with passed Power value inverted
                                                            //      so motor will rotate in reverse
        sleep((long) Time);                                 // Wait here in code for duration of passed Time value
                                                            //      (allows motors to run for duration of Time)
    }
    // END of METHOD spinRight 
    //
    // METHOD spinLeft(Time,Power)
    public void spinLeft(long Time, double Power){          // The variable names Time and Power will be assigned
                                                            //      to the values passed into the method, in the order
                                                            //      they are received
                                                            //
        adjustTimeBasedOnPower(Time,Power)                  // Send Time and Power to adjustTimeBasedOnPower METHOD
                                                            //      to see if Time value should be adjusted
                                                            //
        leftDriveMotor.setPower(-Power);                    // Run motor with passed Power value inverted
                                                            //      so motor will rotate in reverse
        rightDriveMotor.setPower(Power);                    // Run motor with passed Power value
        sleep(Time);                                        // Wait here in code for duration of passed Time value
                                                            //      (allows motors to run for duration of Time)
    }
    // END of METHOD spinLeft
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
