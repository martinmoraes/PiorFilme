package com.moraes.usecase;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WorstProducerUseCaseTest {

	@Autowired
	private MovieImportUseCase movieImportUseCase;
	
	@Autowired
	private WorstProducerUseCase worstProducerUseCase;
	
	@Test
	public void shouldReturnJson_whenRunExecute() {
		movieImportUseCase.execute();
		String receivedJson = worstProducerUseCase.execute();
		System.out.println(receivedJson);
		assertThat(receivedJson).isEqualTo("{\"min\":[{\"producer\":\"Yoram Globus and Menahem Golan\",\"interval\":1,\"previousWin\":1986,\"followingWin\":1987},{\"producer\":\"Wyck Godfrey, Stephenie Meyer and Karen Rosenfelt\",\"interval\":1,\"previousWin\":2011,\"followingWin\":2012}],\"max\":[{\"producer\":\"Jerry Weintraub\",\"interval\":9,\"previousWin\":1980,\"followingWin\":1989}]}");
		
	}
}
