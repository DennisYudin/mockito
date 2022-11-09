//package dev.yudin.chapter_5_power_mock;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//import static org.mockito.Mockito.mockStatic;
//import static org.mockito.Mockito.when;
//
//
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//
//class MedicalBillTest {
//
//	public MedicalBillTest() {
//	}
//
//	@Test
//	public void stubs_static_methods() throws Exception {
//
//		System.out.println(MedicalBill.generateId());
//
//		try(mockStatic(MedicalBill.class)) {
//
//
//		when(MedicalBill.generateId()).thenReturn(1);
//
//		assertEquals(1, MedicalBill.generateId());
//		}
//	}
//
//}
