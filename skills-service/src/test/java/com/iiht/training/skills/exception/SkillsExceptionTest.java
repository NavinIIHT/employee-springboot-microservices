package com.iiht.training.skills.exception;

import static com.iiht.training.skills.testutils.TestUtils.currentTest;
import static com.iiht.training.skills.testutils.TestUtils.exceptionTestFile;
import static com.iiht.training.skills.testutils.TestUtils.testReport;
import static com.iiht.training.skills.testutils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.iiht.training.skills.controller.SkillsRestController;
import com.iiht.training.skills.dto.SkillsDto;
import com.iiht.training.skills.exceptions.ExceptionResponse;
import com.iiht.training.skills.exceptions.SkillNotFoundException;
import com.iiht.training.skills.proxy.CertificatesServiceProxy;
import com.iiht.training.skills.service.SkillsService;
import com.iiht.training.skills.testutils.MasterData;

@WebMvcTest(SkillsRestController.class)
@AutoConfigureMockMvc
public class SkillsExceptionTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SkillsService skillsService;

	@MockBean
	private CertificatesServiceProxy serviceProxy;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void skills_testAddSkillInvalidDataException() throws Exception {
		SkillsDto skillsDto = MasterData.getSkillsDto();
		SkillsDto savedSkillsDto = MasterData.getSkillsDto();

		savedSkillsDto.setId(1);
		skillsDto.setName("Ab");

		when(this.skillsService.addSkill(skillsDto)).thenReturn(savedSkillsDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/skills")
				.content(MasterData.asJsonString(skillsDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);

	}

	@Test
	public void skills_testUpdateSkillInvalidDataException() throws Exception {
		SkillsDto skillsDto = MasterData.getSkillsDto();
		SkillsDto savedSkillsDto = MasterData.getSkillsDto();

		savedSkillsDto.setId(1);
		skillsDto.setName("Ab");

		when(this.skillsService.addSkill(skillsDto)).thenReturn(savedSkillsDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/skills")
				.content(MasterData.asJsonString(skillsDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);

	}
	@Test
	public void skills_testGetSkillByIdSkillNotFoundException() throws Exception {
		ExceptionResponse exResponse = new ExceptionResponse("Skill with Id - 1 does not exists",
				System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());

		when(this.skillsService.getSkillById(1))
				.thenThrow(new SkillNotFoundException("Skill with Id - 1 does not exists"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/skills/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);

	}

	@Test
	public void skills_testDeleteSkillByIdSkillNotFoundException() throws Exception {
		ExceptionResponse exResponse = new ExceptionResponse("Skill with Id - 1 does not exists",
				System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());

		when(this.skillsService.deleteSkill(1))
				.thenThrow(new SkillNotFoundException("Skill with Id - 1 does not exists"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/skills/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);

	}

}
