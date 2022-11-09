package dev.yudin.chapter_3.generic_collection_arguments;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
class ServiceImplTest {

	@Mock
	private Service service;

//	@BeforeEach
//	public void beforeEveryTest() {
//		MockitoAnnotations.initMocks(this);
//	}

	@Test
	@SuppressWarnings("unchecked")
	public void when_captures_collections() throws Exception {

		Class<List<String>> listClass = (Class<List<String>>) (Class) List.class;
		ArgumentCaptor<List<String>> captor = ArgumentCaptor.forClass(listClass);

		service.call(Arrays.asList("a", "b"));

		verify(service).call(captor.capture());
		assertTrue(captor.getValue().containsAll(Arrays.asList("a","b")));
	}
}
