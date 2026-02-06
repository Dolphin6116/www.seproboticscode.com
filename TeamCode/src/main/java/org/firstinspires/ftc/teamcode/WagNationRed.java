package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;


@Autonomous(name = "WagNationRed")
public class WagNationRed extends LinearOpMode {
    private ElapsedTime timer = new ElapsedTime();

    DcMotorEx leftf;
    DcMotorEx leftb;
    DcMotorEx rightf;
    DcMotorEx rightb;
    DcMotorEx liftarm1;
    DcMotorEx liftarm2;
    DcMotorEx Cool2;
    DcMotorEx Intake;
    Servo Feeder;
    Servo rampA;


    public enum State{
        STAGE1,//Drive Forward
        STAGE2,//Turn 1
        STAGE3,//Drive Forward
        STAGE4,//Turn 2
        STAGE5,//Shoot
        STAGE6,//Turn 3
        STAGE7,//Drive to Park
        STAGE8,
        END
    };
    State state = State.STAGE1;

    @Override
    public void runOpMode() throws InterruptedException {


        leftf = hardwareMap.get(DcMotorEx.class, "Left_front");
        leftb = hardwareMap.get(DcMotorEx.class, "Left_back");
        rightf = hardwareMap.get(DcMotorEx.class, "Right_front");
        rightb = hardwareMap.get(DcMotorEx.class, "Right_back");
        liftarm1 = hardwareMap.get(DcMotorEx.class, "Actuator");
        liftarm2 = hardwareMap.get(DcMotorEx.class, "Actuator2");
        Cool2 = hardwareMap.get(DcMotorEx.class, "skib2");
        Intake = hardwareMap.get(DcMotorEx.class, "Intake");
        Feeder = hardwareMap.get(Servo.class, "Servo1");//1.17
        rampA = hardwareMap.get(Servo.class, "RampA");



        leftf.setDirection(DcMotorSimple.Direction.REVERSE);
        leftb.setDirection(DcMotorSimple.Direction.REVERSE);

        leftf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Feeder.setPosition(0.9);
        waitForStart();

        timer.reset();
        //gamepad1.rumbleBlips(2);

        while (opModeIsActive()) {



            switch(state) {
                case STAGE1:
                    leftf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    leftb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    rightf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    rightb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    leftf.setTargetPosition(-200);
                    leftb.setTargetPosition(200);
                    rightf.setTargetPosition(200);
                    rightb.setTargetPosition(-200);
                    leftf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    leftb.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    rightf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    rightb.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    leftf.setPower(0.7);
                    leftb.setPower(0.7);
                    rightf.setPower(0.7);
                    rightb.setPower(0.7);
                    while(opModeIsActive() && (leftf.isBusy()||rightf.isBusy()||leftb.isBusy()||rightb.isBusy())) {

                    }
                    leftf.setPower(0);
                    leftb.setPower(0);
                    rightf.setPower(0);
                    rightb.setPower(0);

                    int x = 2525;
                    leftf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    leftb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    rightf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    rightb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    leftf.setTargetPosition(x);
                    leftb.setTargetPosition(x);
                    rightf.setTargetPosition(x);
                    rightb.setTargetPosition(x);
                    leftf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    leftb.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    rightf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    rightb.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    leftf.setPower(0.7);
                    leftb.setPower(0.7);
                    rightf.setPower(0.7);
                    rightb.setPower(0.7);//1170
                    while(opModeIsActive() && (leftf.isBusy()||rightf.isBusy()||leftb.isBusy()||rightb.isBusy())) {

                    }
                    leftf.setPower(0);
                    leftb.setPower(0);
                    rightf.setPower(0);
                    rightb.setPower(0);

                    state = State.STAGE2;
                    break;
                case STAGE2:
                    int y = 1077;
                    leftf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    leftb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    rightf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    rightb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

                    leftf.setTargetPosition(-y);
                    leftb.setTargetPosition(-y);
                    rightf.setTargetPosition(y);
                    rightb.setTargetPosition(y);

                    leftf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    leftb.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    rightf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    rightb.setMode(DcMotor.RunMode.RUN_TO_POSITION);


                    leftf.setPower(0.7);
                    leftb.setPower(0.7);
                    rightf.setPower(0.7);
                    rightb.setPower(0.7);

                    while(opModeIsActive() && (leftf.isBusy()||rightf.isBusy()||leftb.isBusy()||rightb.isBusy())) {

                    }
                    leftf.setPower(0);
                    leftb.setPower(0);
                    rightf.setPower(0);
                    rightb.setPower(0);
                    state = State.STAGE3;
                    break;

                case STAGE3:
                    int z = 1835;
                    leftf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    leftb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    rightf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    rightb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

                    leftf.setTargetPosition(z);
                    leftb.setTargetPosition(z);
                    rightf.setTargetPosition(z);
                    rightb.setTargetPosition(z);

                    leftf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    leftb.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    rightf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    rightb.setMode(DcMotor.RunMode.RUN_TO_POSITION);


                    leftf.setPower(0.7);
                    leftb.setPower(0.7);
                    rightf.setPower(0.7);
                    rightb.setPower(0.7);

                    while(opModeIsActive() && (leftf.isBusy()||rightf.isBusy()||leftb.isBusy()||rightb.isBusy())) {

                    }
                    leftf.setPower(0);
                    leftb.setPower(0);
                    rightf.setPower(0);
                    rightb.setPower(0);

                    state = State.STAGE4;
                    break;
                case STAGE4:
                    int a = 2677;
                    leftf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    leftb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    rightf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    rightb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

                    leftf.setTargetPosition(a);
                    leftb.setTargetPosition(a);
                    rightf.setTargetPosition(-a);
                    rightb.setTargetPosition(-a);

                    leftf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    leftb.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    rightf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    rightb.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                    leftf.setPower(0.7);
                    leftb.setPower(0.7);
                    rightf.setPower(0.7);
                    rightb.setPower(0.7);

                    while(opModeIsActive() && (leftf.isBusy()||rightf.isBusy()||leftb.isBusy()||rightb.isBusy())) {

                    }
                    leftf.setPower(0);
                    leftb.setPower(0);
                    rightf.setPower(0);
                    rightb.setPower(0);
                    state = State.STAGE5;
                    break;



                case STAGE5:
                    Feeder.setPosition(0.09);
                    Cool2.setPower(0.75);
                    sleep(1000);

                    Feeder.setPosition(0.05);
                    sleep(300);
                    Feeder.setPosition(0.09);

                    sleep(3000);
                    Feeder.setPosition(0.05);
                    sleep(300);
                    Feeder.setPosition(0.09);
                    Intake.setPower(1);
                    sleep(3000);
                    Feeder.setPosition(0.05);
                    sleep(300);
                    Feeder.setPosition(0.09);
                    Feeder.setPosition(0.05);
                    sleep(300);
                    Feeder.setPosition(0.09);
                    Feeder.setPosition(0.05);
                    sleep(300);
                    Feeder.setPosition(0.09);
                    sleep(3000);
                    Feeder.setPosition(0.05);
                    sleep(300);
                    Feeder.setPosition(0.09);
                    Intake.setPower(0);
                    Cool2.setPower(0);
                    state = State.STAGE6;
                    break;
                case STAGE6:
                    int b = 1377;
                    leftf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    leftb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    rightf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    rightb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

                    leftf.setTargetPosition(-b);
                    leftb.setTargetPosition(-b);
                    rightf.setTargetPosition(b);
                    rightb.setTargetPosition(b);

                    leftf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    leftb.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    rightf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    rightb.setMode(DcMotor.RunMode.RUN_TO_POSITION);


                    leftf.setPower(1);
                    leftb.setPower(1);
                    rightf.setPower(1);
                    rightb.setPower(1);

                    while(opModeIsActive() && (leftf.isBusy()||rightf.isBusy()||leftb.isBusy()||rightb.isBusy())) {

                    }
                    leftf.setPower(0);
                    leftb.setPower(0);
                    rightf.setPower(0);
                    rightb.setPower(0);
                    state = State.STAGE7;
                    break;
                case STAGE7:
                    int c = 1535;
                    leftf.setTargetPosition(c);
                    leftb.setTargetPosition(c);
                    rightf.setTargetPosition(c);
                    rightb.setTargetPosition(c);
                    leftf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    leftb.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    rightf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    rightb.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    leftf.setPower(0.7);
                    leftb.setPower(0.7);
                    rightf.setPower(0.7);
                    rightb.setPower(0.7);//1170
                    while(opModeIsActive() && (leftf.isBusy()||rightf.isBusy()||leftb.isBusy()||rightb.isBusy())) {

                    }
                    leftf.setPower(0);
                    leftb.setPower(0);
                    rightf.setPower(0);
                    rightb.setPower(0);

                    state = State.END;
                    break;
                default:

            }






        }
    }
}
