package dev.yudin.chapter_5_power_mock;

import java.util.Random;

public class MedicalBill {

	public MedicalBill() {
	}

	public static int generateId(){
		return new Random().nextInt();
	}
}
