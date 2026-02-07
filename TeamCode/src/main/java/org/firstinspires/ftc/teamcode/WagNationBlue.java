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


@Autonomous(name = "WagNationBlue")
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
        SHOOTR,
        SHOOT1,
        SHOOTEND,
        STAGE6,//Turn 3
        STAGE7,//Drive to Park
        STAGE8,
        END
    };
    State state = State.STAGE1;
    int shots = 0;

    
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
                    strafe(200,1);
                    state = State.STAGE1P;
                    break;
                case STAGE1P:                
                    move(2525,1);
                    state = State.STAGE2;
                    break;
                case STAGE2:
                    turn(1077,1);
                    state = State.STAGE3;
                    break;

                case STAGE3:            
                    move(1835,1);
                    state = State.STAGE4;
                    break;
                case STAGE4:
                    turn(2677,-1);
                    state = State.STAGE5;
                    timer.reset();
                    break;
                case STAGE5:
                    Feeder.setPosition(0.09);
                    Cool2.setPower(0.75);
                    if(timer.milliseconds()>800){
                        Intake.setPower(1);
                        state = State.SHOOT1;
                        timer.reset();
                        break;
                    }
                    break;
                case SHOOT1:
                    if(shots == 3){
                        state = State.SHOOTEND;
                        break;
                    }
                    if(timer.milliseconds() > 1000){
                        Feeder.setPosition(0.05);
                        timer.reset();
                        state = State.SHOOTR;
                    }
                    
                    break;
                case SHOOTR:
                    if(timer.milliseconds() > 200){
                        Feeder.setPosition(0.09);
                        timer.reset();
                        shots++;
                        state = State.SHOOT1;
                    }
                    break;
                case SHOOTEND:
                    Feeder.setPosition(0.09);
                    Intake.setPower(0);
                    Cool2.setPower(0);
                    state = State.STAGE6;
                    break;           
                case STAGE6:
                    turn(1377,1);
                    state = State.STAGE7;
                    break;
                case STAGE7:
                    move(1535,1);
                    state = State.END;
                    break;
                default:

            }
        }
    }
    public void move(int ticks, int mode){
        //1 = forward
        //2 = back
        int direction = (mode == 1) ? 1 : -1;
 
        leftf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftf.setTargetPosition(ticks* direction);
        leftb.setTargetPosition(ticks * direction);
        rightf.setTargetPosition(ticks * direction);
        rightb.setTargetPosition(ticks * direction);

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


    }
    public void turn(int ticks, int mode){
        //1 = left
        //2 = right 
        int direction = (mode == 1) ? 1 : -1;
 
        leftf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftf.setTargetPosition(-ticks* direction);
        leftb.setTargetPosition(-ticks * direction);
        rightf.setTargetPosition(ticks * direction);
        rightb.setTargetPosition(ticks * direction);

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
    }
    public void strafe(int ticks, int mode){
        //1 = strafe left 
        //2 = strafe right 
        int direction = (mode == 1) ? 1 : -1;
 
        leftf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftf.setTargetPosition(ticks* direction);
        leftb.setTargetPosition(-ticks * direction);
        rightf.setTargetPosition(-ticks * direction);
        rightb.setTargetPosition(ticks * direction);

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
    }
            
}
