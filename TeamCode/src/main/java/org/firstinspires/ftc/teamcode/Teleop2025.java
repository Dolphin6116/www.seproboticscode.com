package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "SEP2025")
public class Teleop2025 extends LinearOpMode {
    DcMotorEx front_left;
    DcMotorEx back_left;
    DcMotorEx front_right;
    DcMotorEx back_right;
    DcMotorEx launcher_back;
    DcMotorEx launcher_left;
    DcMotorEx launcher_right;
    Servo servo;
    double maxPower = 2000;
    boolean slowMode = false;
    double speedMultiplier = 1;
    boolean lastToggleState = false;
    @Override

    public void runOpMode() throws InterruptedException{
        front_left = hardwareMap.get(DcMotorEx.class,"frontLeftMotor");
        back_left = hardwareMap.get(DcMotorEx.class,"backLeftMotor");
        front_right = hardwareMap.get(DcMotorEx.class,"frontRightMotor");
        back_right = hardwareMap.get(DcMotorEx.class,"backRightMotor");
        launcher_back = hardwareMap.get(DcMotorEx.class,"launcherBackMotor");
        launcher_left = hardwareMap.get(DcMotorEx.class,"launcherLeftMotor");
        launcher_right = hardwareMap.get(DcMotorEx.class,"launcherRightMotor");
        servo = hardwareMap.get(Servo.class,"Servo1");

        front_left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        back_left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        front_right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        back_right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        launcher_left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        launcher_right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        front_left.setDirection(DcMotorSimple.Direction.REVERSE);
        back_left.setDirection(DcMotorSimple.Direction.REVERSE);
        launcher_back.setDirection(DcMotorSimple.Direction.REVERSE);
        servo.setPosition(0.1);

        waitForStart();

        if(isStopRequested()) {
            return;
        }

        while(opModeIsActive()) {
            double y =  gamepad1.left_stick_x;//forward
            double x =  -gamepad1.left_stick_y;//strafe
            double rx = gamepad1.right_stick_x;//turn
            double lt = gamepad1.left_trigger;
            boolean du = gamepad1.dpad_up;
            boolean a = gamepad1.a;
            double rb = gamepad1.right_trigger;
            double frontLeftPower = y + x + rx;
            double backLeftPower = y - x + rx;
            double frontRightPower = y - x - rx;
            double backRightPower = y + x - rx;
            double launcherPower;
            double maxi = Math.max(
                    Math.max(Math.abs(frontLeftPower), Math.abs(backLeftPower)),
                    Math.max(Math.abs(frontRightPower), Math.abs(backRightPower))
            );
            if(maxi>1) {
                frontLeftPower /= maxi;
                frontRightPower /= maxi;
                backLeftPower /= maxi;
                backRightPower /= maxi;
            }
            if (Math.abs(x) < 0.05) x = 0;
            if (Math.abs(y) < 0.05) y = 0;

            if(du && !lastToggleState){
                slowMode = !slowMode;
            }
            lastToggleState = du;
            if(slowMode){
                speedMultiplier = 0.5;
            }else{
                speedMultiplier = 1;
            }

            front_left.setVelocity(frontLeftPower*maxPower*speedMultiplier);
            front_right.setVelocity(frontRightPower*maxPower*speedMultiplier);
            back_left.setVelocity(backLeftPower*maxPower*speedMultiplier);
            back_right.setVelocity(backRightPower*maxPower*speedMultiplier);

            if(gamepad1.aWasPressed()) {
                launcher_left.setPower(1);
                launcher_right.setPower(1);
            }else{
                launcher_left.setPower(0);
                launcher_right.setPower(0);
            }
            if(rb == 1){
                servo.setPosition(0.05);
                sleep(100);
                servo.setPosition(0.1);
            }
            if(gamepad1.xWasPressed()){
                launcher_back.setPower(1);
            }else{
                launcher_back.setPower(0);
            }

            telemetry.addData("Slow Mode", slowMode ? "ON" : "OFF");
            telemetry.addData("Button Pressed",du ? "Yes" : "No");
            telemetry.addData("A button pressed", frontLeftPower);
            telemetry.addData("Left Stick Forward", frontRightPower);
            telemetry.addData("Left Stick Side", backLeftPower);
            telemetry.addData("Right Stick ", backRightPower);
            telemetry.update();
        }
    }
}
