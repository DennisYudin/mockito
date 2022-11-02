package dev.yudin.chapter_3.generic_collection_arguments;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

import dev.yudin.chapter_3.void_method.DemoController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

class ServiceImplTest {

	@Mock
	Service service;

	@BeforeEach
	public void beforeEveryTest() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	@SuppressWarnings("unchecked")
	void when_captures_collections() throws Exception {

		Class<List<String>> listClass = (Class<List<String>>) (Class) List.class;

		ArgumentCaptor<List<String>> captor = ArgumentCaptor.forClass(listClass);

//		Service service = new ServiceImpl();
		service.call(Arrays.asList("a", "b"));

		verify(service).call(captor.capture());
		assertTrue(captor.getValue().containsAll(Arrays.asList("a","b")));
	}
}
