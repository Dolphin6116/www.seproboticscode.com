package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.Base64;

@TeleOp(name="NickTest")
public class nickteleopisbest extends LinearOpMode {


    @Override

    public void runOpMode() throws InterruptedException {

//Motor and servo declaration. Not inside the Opmode
        DcMotor leftf = hardwareMap.get(DcMotor.class, "Left_front");
        DcMotor leftb = hardwareMap.get(DcMotor.class, "Left_back");
        DcMotor rightf = hardwareMap.get(DcMotor.class, "Right_front");
        DcMotor rightb = hardwareMap.get(DcMotor.class, "Right_back");
        DcMotor Cool = hardwareMap.get(DcMotor.class, "skib");
        DcMotor Cool2 = hardwareMap.get(DcMotor.class, "skib2");
        DcMotor Intake = hardwareMap.get(DcMotor.class,"Intake");
        Servo Test1 = hardwareMap.get(Servo.class, "Servo1");
        Servo rampA = hardwareMap.get(Servo.class, "RampA");
        //reverses the right side
        leftf.setDirection(DcMotorSimple.Direction.REVERSE);
        leftb.setDirection(DcMotorSimple.Direction.REVERSE);
        rampA.setPosition(0.078);
        //Encoders make the directionals velocity based (RPM)
        waitForStart();
        if (isStopRequested()) {
            return;
        }
//Declares motors that were previously defined. Note that these below are only active when the opmode is

        while (opModeIsActive()) {
            boolean ispresseda = gamepad1.a;
            boolean ispressedb = gamepad1.b;
            boolean ispressedx = gamepad1.x;
            boolean ispressedy = gamepad1.y;
            double ispressedLT = gamepad1.left_trigger;
            double ispressedRB = gamepad1.right_trigger;
            //declares the actual controls from the control hub
            double leftstickx =gamepad1.right_stick_x;
            double leftsticky = -gamepad1.left_stick_y;//-
            double rightstickx = gamepad1.left_stick_x;
            double rightsticky = gamepad1.right_stick_y;//-
//Driving variables, formulas to calculate wheel positionality
            //If curious, please refer to various Mechanum wheel guides online


            double fleftpower = (leftsticky + rightstickx + leftstickx);
            double bleftpower = (leftsticky - rightstickx + leftstickx);
            double frightpower = (leftsticky -rightstickx - leftstickx);
            double brightpower = (leftsticky + rightstickx - leftstickx);


//assigning motor power values
            leftf.setPower(fleftpower);
            leftb.setPower(bleftpower);
            rightf.setPower(frightpower);
            rightb.setPower(brightpower);
//sets the launching motor's position to 0 when the later called function offsets itb
//Test code for launcher. Somewhat irrelevant right now (OPEN HOUSE).
            if (ispresseda) {
                Cool.setPower(.5);
            } else {
                Cool.setPower(0);
                //Using launcher
                if (ispressedb) {
                    Cool.setPower(-.5);
                } else {
                    Cool.setPower(0);
                }

                if (ispressedLT==1) {
                    Intake.setPower(1);
                } else {
                    Intake.setPower(0);
                }
                if (ispressedLT == 1) {
                    Cool.setPower(.95);
                    Cool2.setPower(.95);
                } else {
                    Cool.setPower(0);
                    Cool2.setPower(0);


                }

                    Test1.setPosition(0.09);
                    if (ispressedRB == 1) {
                        Test1.setPosition(0.05);
                        sleep(100);
                    }
                    if(ispressedy){
                        rampA.setPosition(0.82);
                    }
                    if(gamepad1.dpad_down){
                        rampA.setPosition(0.078);
                    }
            }


        }
    }
}


// Milana's practice code


//  Servo no = hardwareMap.get(Servo.class, deviceName:"no");
//  DcMotor no2 = hardwareMap.get(DcMotor.class, deviceName:"no2");
//  no2.setPower(fleftpower);
//  no.setPower(frightpower);
 //   no.setPosition(0);
//    no2.setPosition(0);
//
 //   if (ispresseda) {
 //       no.setPower(.5);
 //     } else {
//        no.setPower(0);
//      }
 //   if (ispressedb) {
//        no2.setPower(.5);
//      } else {
//         no2.setPower(0);
//      }
/*
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="NickTest")
public class NickTeleOpIsBest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        DcMotor leftf = hardwareMap.get(DcMotor.class, "Left_front");
        DcMotor leftb = hardwareMap.get(DcMotor.class, "Left_back");
        DcMotor rightf = hardwareMap.get(DcMotor.class, "Right_front");
        DcMotor rightb = hardwareMap.get(DcMotor.class, "Right_back");
        DcMotor Cool = hardwareMap.get(DcMotor.class, "skib");
        DcMotor Cool2 = hardwareMap.get(DcMotor.class, "skib2");
        Servo Test1 = hardwareMap.get(Servo.class, "Servo1");

        // Set motor directions for mecanum
        leftf.setDirection(DcMotorSimple.Direction.FORWARD);
        leftb.setDirection(DcMotorSimple.Direction.FORWARD);
        rightf.setDirection(DcMotorSimple.Direction.REVERSE);
        rightb.setDirection(DcMotorSimple.Direction.REVERSE);

        Test1.setPosition(0); // initialize once

        waitForStart();
        if (isStopRequested()) return;

        while (opModeIsActive()) {
            // Gamepad values
            double leftstickx = gamepad1.left_stick_x;
            double leftsticky = -gamepad1.left_stick_y;
            double rightstickx = gamepad1.right_stick_x;

            // Mecanum formulas
            double fleftpower = leftsticky + leftstickx + rightstickx;
            double bleftpower = leftsticky - leftstickx + rightstickx;
            double frightpower = leftsticky - leftstickx - rightstickx;
            double brightpower = leftsticky + leftstickx - rightstickx;

            // Normalize
            double max = Math.max(Math.abs(fleftpower),
                    Math.max(Math.abs(bleftpower),
                    Math.max(Math.abs(frightpower), Math.abs(brightpower))));
            if (max > 1.0) {
                fleftpower /= max;
                bleftpower /= max;
                frightpower /= max;
                brightpower /= max;
            }

            // Apply motor powers
            leftf.setPower(fleftpower);
            leftb.setPower(bleftpower);
            rightf.setPower(frightpower);
            rightb.setPower(brightpower);

            // Launcher control
            if (gamepad1.a) {
                Cool.setPower(0.5);
            } else if (gamepad1.b) {
                Cool.setPower(-0.5);
            } else {
                Cool.setPower(0);
            }

            // Dual launcher
            if (gamepad1.left_trigger > 0.5) {
                Cool.setPower(0.5);
                Cool2.setPower(0.5);
            } else {
                Cool2.setPower(0);
            }

            // Servo control
            if (gamepad1.y) {
                Test1.setPosition(0.0);
            } else if (gamepad1.right_trigger > 0.5) {
                Test1.setPosition(0.045)*/