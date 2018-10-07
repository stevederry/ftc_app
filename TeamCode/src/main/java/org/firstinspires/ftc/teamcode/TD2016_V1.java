package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name="Blue_TechDogauto Final")

public class TD2016_V1 extends LinearOpMode {
    DcMotor leftDriveMotor;
    DcMotor rightDriveMotor;

    public void stop_robot(){
        leftDriveMotor.setPower(0);
        rightDriveMotor.setPower(0);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        leftDriveMotor = hardwareMap.dcMotor.get("leftDriveMotor");
        rightDriveMotor = hardwareMap.dcMotor.get("rightDriveMotor");


        waitForStart();
        // go straight
        leftDriveMotor.setPower(-0.5);
        rightDriveMotor.setPower(0.5);
        sleep(600);
        stop_robot();
        // turn right
        leftDriveMotor.setPower(-0.5);
        rightDriveMotor.setPower(-0.5);
        sleep(800);
        stop_robot();
        //go straight again
        leftDriveMotor.setPower(-1.0);
        rightDriveMotor.setPower(1.0);
        sleep(750);
        stop_robot();
        //turn a little bit to face corner vortex
        leftDriveMotor.setPower(-0.5);
        rightDriveMotor.setPower(-0.5);
        sleep(800);
        stop_robot();
        //turn 180 degrees
        leftDriveMotor.setPower(0.5);
        rightDriveMotor.setPower(0);
        sleep(2500);
        stop_robot();
        //move forward one
        leftDriveMotor.setPower(-1.0);
        rightDriveMotor.setPower(1.0);
        sleep(850);
        stop_robot();
        leftDriveMotor.setPower(-1.0);
        sleep(1000);
        stop_robot();
        //move forward again(don't add because of time)
        leftDriveMotor.setPower(-1.0);
        rightDriveMotor.setPower(1.0);
        sleep(900);
        stop_robot();


    }
}

