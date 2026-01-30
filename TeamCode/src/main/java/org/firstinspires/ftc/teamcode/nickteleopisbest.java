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

//Motor and servo declaration. Not inside the Opmode.
        //Note that any power declarations here will activate during initialization
        DcMotor leftf = hardwareMap.get(DcMotor.class, "Left_front");
        DcMotor leftb = hardwareMap.get(DcMotor.class, "Left_back");
        DcMotor rightf = hardwareMap.get(DcMotor.class, "Right_front");
        DcMotor rightb = hardwareMap.get(DcMotor.class, "Right_back");
        DcMotor liftarm1 = hardwareMap.get(DcMotor.class, "Actuator");
        DcMotor liftarm2 = hardwareMap.get(DcMotor.class, "Actuator2");
        DcMotor Cool2 = hardwareMap.get(DcMotor.class, "skib2");
        DcMotor Intake = hardwareMap.get(DcMotor.class,"Intake");
        Servo Feeder = hardwareMap.get(Servo.class, "Servo1");
        Servo rampA = hardwareMap.get(Servo.class, "RampA");
        //reverses the right side
        leftf.setDirection(DcMotorSimple.Direction.REVERSE);
        leftb.setDirection(DcMotorSimple.Direction.REVERSE);


        //Anything beyond this point is activated during ROBOT START
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
            boolean ispressedRBB = gamepad1.right_bumper;
            double ispressedLT = gamepad1.left_trigger;
            double ispressedRB = gamepad1.right_trigger;
            double leftstickx = gamepad1.right_stick_x;
            double leftsticky = -gamepad1.left_stick_y;//-
            double rightstickx = gamepad1.left_stick_x;
            double rightsticky = gamepad1.right_stick_y;//-
//Driving variables, formulas to calculate wheel positionality
            //If curious, please refer to various Mechanum wheel guides online


            double fleftpower = (leftsticky + rightstickx + leftstickx);
            double bleftpower = (leftsticky - rightstickx + leftstickx);
            double frightpower = (leftsticky - rightstickx - leftstickx);
            double brightpower = (leftsticky + rightstickx - leftstickx);


//assigning motor power values
            leftf.setPower(fleftpower);
            leftb.setPower(bleftpower);
            rightf.setPower(frightpower);
            rightb.setPower(brightpower);

            //Intake Button Function

        if (ispressedRBB){
        Intake.setPower(1);
        }else {
            Intake.setPower(0);
        }




            //Front Flywheel
            if (ispressedLT == 1) {
                Cool2.setPower(.95);
            } else {
                Cool2.setPower(0);
            }
            //Actuator Right
            if (ispresseda){
                liftarm1.setPower(1);
            }else {
                liftarm1.setPower (0);
            }
            if(ispressedx){
                liftarm1.setPower(-1);

            }
            //Actuator Left

            if (ispressedb){
                liftarm2.setPower(1);
            }else {
                liftarm2.setPower (0);
            }
            if (ispressedy){
                liftarm2.setPower(-1);
            }
            //Feeding Servo
            Feeder.setPosition(0.09);
            if (ispressedRB == 1) {
                Feeder.setPosition(0.05);
                sleep(100);
            }
        }


        }
    }

