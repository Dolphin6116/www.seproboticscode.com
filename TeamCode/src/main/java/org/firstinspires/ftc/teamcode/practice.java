package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


@TeleOp(name="MilanaPractice")
public class practice extends LinearOpMode {


    public void runOpMode() throws InterruptedException {

        DcMotor leftf = hardwareMap.get(DcMotor.class,"front_left");
        DcMotor rightf = hardwareMap.get(DcMotor.class, "front_right");
        DcMotor leftb = hardwareMap.get(DcMotor.class, "back_left");
        DcMotor rightb = hardwareMap.get(DcMotor.class, "back_right");

        waitForStart();
        if (isStopRequested()) {
            return;
        }

        while (opModeIsActive()) {
            boolean ispresseda = gamepad1.a;
            double leftsticky = gamepad1.left_stick_y;
            double leftstickx = -gamepad1.left_stick_x;
            double rightsticky = gamepad1.right_stick_y;
            double rightstickx = gamepad1.right_stick_x;
            double IsPressedLT = gamepad1.left_trigger;
            double IsPressedFT = gamepad1.right_trigger;


            double fleftpower = (leftsticky + rightstickx + leftstickx );
            double bleftpower = (leftsticky - rightstickx + leftstickx );
            double frightpower = (leftsticky - rightstickx - leftstickx);
            double brightpower = (leftsticky + rightstickx - leftstickx);

            leftf.setPower(fleftpower);
            leftb.setPower(bleftpower);
            rightb.setPower(frightpower);
            rightf.setPower(brightpower);

            if (IsPressedLT == 1) {

            }
        }


    }


}
