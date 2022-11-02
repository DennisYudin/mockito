package dev.yudin.chapter_3.spy_object;

import static org.junit.jupiter.api.Assertions.assertNull;

import dev.yudin.chapter_3.void_method.error.Error;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

class SpyAnnotationTest {

	@BeforeEach
	public void beforeEveryTest() {
		MockitoAnnotations.initMocks(this);
	}

	@Spy
	Error error;

	@Test
	public void when_spy_annotation_is_used() throws Exception {
		error.setErrorCode(null);

		assertNull(error.getErrorCode());
	}
}
