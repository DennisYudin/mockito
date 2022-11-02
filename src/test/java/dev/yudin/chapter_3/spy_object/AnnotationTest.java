package dev.yudin.chapter_3.spy_object;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

import dev.yudin.chapter_3.generic_collection_arguments.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

class AnnotationTest {

	@Captor
	ArgumentCaptor<List<String>> captor;
	@Mock
	Service service;

	@BeforeEach
	public void beforeEveryTest() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void when_captor_annotation_is_used() {
		service.call(Arrays.asList("a","b"));
		verify(service).call(captor.capture());

		assertTrue(captor.getValue().containsAll(Arrays.asList("a","b")));
	}
}
