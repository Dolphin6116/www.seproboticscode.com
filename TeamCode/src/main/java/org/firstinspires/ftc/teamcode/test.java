import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "test")
public class test extends LinearOpMode {
    DcMotorEx testMotor1;
    DcMotorEx testMotor2;
    DcMotorEx testMotor3;
    DcMotorEx testMotor4;
    @Override
    public void runOpMode() throws InterruptedException {
        testMotor1 = hardwareMap.get(DcMotorEx.class,"frontLeftMotor");
        testMotor2 = hardwareMap.get(DcMotorEx.class,"backLeftMotor");
        testMotor3 = hardwareMap.get(DcMotorEx.class,"frontRightMotor");
        testMotor4 = hardwareMap.get(DcMotorEx.class,"backRightMotor");
        testMotor1.setDirection(DcMotorSimple.Direction.REVERSE);
        testMotor2.setDirection(DcMotorSimple.Direction.REVERSE);
        waitForStart();
        while (opModeIsActive()) {
            double forward = -gamepad1.left_stick_y;
            testMotor1.setPower(forward);
            testMotor2.setPower(forward);
            testMotor3.setPower(forward);
            testMotor4.setPower(forward);

        }
    }
}