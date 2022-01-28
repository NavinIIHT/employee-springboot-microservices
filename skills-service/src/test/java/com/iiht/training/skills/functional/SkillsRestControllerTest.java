package com.iiht.training.skills.functional;

import static com.iiht.training.skills.testutils.TestUtils.businessTestFile;
import static com.iiht.training.skills.testutils.TestUtils.currentTest;
import static com.iiht.training.skills.testutils.TestUtils.testReport;
import static com.iiht.training.skills.testutils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.iiht.training.skills.controller.SkillsRestController;
import com.iiht.training.skills.dto.CertificatesDto;
import com.iiht.training.skills.dto.SkillsDto;
import com.iiht.training.skills.proxy.CertificatesServiceProxy;
import com.iiht.training.skills.service.SkillsService;
import com.iiht.training.skills.testutils.MasterData;

@WebMvcTest(SkillsRestController.class)
@AutoConfigureMockMvc
public class SkillsRestControllerTest {
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
	public void skills_testAddSkill() throws Exception {
		SkillsDto skillsDto = MasterData.getSkillsDto();
		SkillsDto savedSkillsDto = MasterData.getSkillsDto();

		when(this.skillsService.addSkill(skillsDto)).thenReturn(savedSkillsDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/skills")
				.content(MasterData.asJsonString(skillsDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(savedSkillsDto))
						? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void skills_testAddSkillIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		SkillsDto skillsDto = MasterData.getSkillsDto();
		when(this.skillsService.addSkill(skillsDto)).then(new Answer<SkillsDto>() {

			@Override
			public SkillsDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return skillsDto;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/skills")
				.content(MasterData.asJsonString(skillsDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void skills_testGetAllSkills() throws Exception {
		List<SkillsDto> skillsDtos = MasterData.getSkillsDtoList();

		when(this.skillsService.findAll()).thenReturn(skillsDtos);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/skills")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(skillsDtos)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void skills_testGetAllSkillsIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		List<SkillsDto> skillsDtos = MasterData.getSkillsDtoList();
		when(this.skillsService.findAll()).then(new Answer<List<SkillsDto>>() {

			@Override
			public List<SkillsDto> answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return skillsDtos;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/skills")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void skills_testGetSkillById() throws Exception {
		SkillsDto skillsDto = MasterData.getSkillsDto();
		skillsDto.setId(1);
		when(this.skillsService.getSkillById(1)).thenReturn(skillsDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/skills/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(skillsDto)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void skills_testGetSkillByIdIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		SkillsDto skillsDto = MasterData.getSkillsDto();
		skillsDto.setId(1);
		when(this.skillsService.getSkillById(1)).then(new Answer<SkillsDto>() {

			@Override
			public SkillsDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return skillsDto;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/skills/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void skills_testUpdateSkill() throws Exception {
		SkillsDto skillsDto = MasterData.getSkillsDto();
		SkillsDto savedSkillsDto = MasterData.getSkillsDto();

		when(this.skillsService.updateSkill(skillsDto)).thenReturn(savedSkillsDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/skills")
				.content(MasterData.asJsonString(skillsDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(savedSkillsDto))
						? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void skills_testUpdateSkillIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		SkillsDto skillsDto = MasterData.getSkillsDto();
		when(this.skillsService.updateSkill(skillsDto)).then(new Answer<SkillsDto>() {

			@Override
			public SkillsDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return skillsDto;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/skills")
				.content(MasterData.asJsonString(skillsDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}
	@Test
	public void skills_testDeleteSkill() throws Exception {
		SkillsDto skillsDto = MasterData.getSkillsDto();
		skillsDto.setId(1);

		when(this.skillsService.deleteSkill(1)).thenReturn(true);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/skills/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(true)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void skills_testDeleteSkillIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		SkillsDto skillsDto = MasterData.getSkillsDto();
		skillsDto.setId(1);
		when(this.skillsService.deleteSkill(1)).then(new Answer<Boolean>() {

			@Override
			public Boolean answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return true;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/skills/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void skills_testGetSkillsByEmployeeId() throws Exception {
		List<SkillsDto> skillsDtos = MasterData.getSkillsDtoList();
		when(this.skillsService.skillsByEmployeeId(1)).thenReturn(skillsDtos);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/skills/by/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(skillsDtos)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void skills_testGetSkillsByEmployeeIdIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		List<SkillsDto> skillsDtos = MasterData.getSkillsDtoList();

		when(this.skillsService.skillsByEmployeeId(1)).then(new Answer<List<SkillsDto>>() {

			@Override
			public List<SkillsDto> answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return skillsDtos;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/skills/by/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void skills_testGetCertificatesBySkillName() throws Exception {
		List<CertificatesDto> certificateDtos = MasterData.getCertificatesDtoList();
		when(this.serviceProxy.getCertificatesBySkillName("Java")).thenReturn(certificateDtos);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/skills/certificates-by-skill-name/Java")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(certificateDtos))
						? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void skills_testGetCertificatesBySkillNameIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		List<CertificatesDto> certificatesDtos = MasterData.getCertificatesDtoList();

		when(this.serviceProxy.getCertificatesBySkillName("Java")).then(new Answer<List<CertificatesDto>>() {

			@Override
			public List<CertificatesDto> answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return certificatesDtos;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/skills/certificates-by-skill-name/Java")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

}
