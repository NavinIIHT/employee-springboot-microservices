package com.iiht.training.certificates.functional;

import static com.iiht.training.certificates.testutils.TestUtils.businessTestFile;
import static com.iiht.training.certificates.testutils.TestUtils.currentTest;
import static com.iiht.training.certificates.testutils.TestUtils.testReport;
import static com.iiht.training.certificates.testutils.TestUtils.yakshaAssert;
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

import com.iiht.training.certificates.controller.CertificatesRestController;
import com.iiht.training.certificates.dto.CertificatesDto;
import com.iiht.training.certificates.service.CertificatesService;
import com.iiht.training.certificates.testutils.MasterData;

@WebMvcTest(CertificatesRestController.class)
@AutoConfigureMockMvc
public class CertificatesRestControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CertificatesService certificatesService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void certificates_testGenerateCertificate() throws Exception {
		CertificatesDto dto = MasterData.getCertificatesDto();
		CertificatesDto savedDto = MasterData.getCertificatesDto();
		when(this.certificatesService.generateCertificate(dto)).thenReturn(savedDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/certificates")
				.content(MasterData.asJsonString(dto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(savedDto)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void certificates_testGenerateCertificateIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		CertificatesDto dto = MasterData.getCertificatesDto();
		when(this.certificatesService.generateCertificate(dto)).then(new Answer<CertificatesDto>() {

			@Override
			public CertificatesDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return dto;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/certificates")
				.content(MasterData.asJsonString(dto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void certificates_testGetAllCertificates() throws Exception {
		List<CertificatesDto> certificatesDtos = MasterData.getCertificatesDtoList();

		when(this.certificatesService.findAll()).thenReturn(certificatesDtos);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/certificates")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(certificatesDtos))
						? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void certificates_testGetAllCertificatesIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		List<CertificatesDto> certificatesDtos = MasterData.getCertificatesDtoList();
		when(this.certificatesService.findAll()).then(new Answer<List<CertificatesDto>>() {

			@Override
			public List<CertificatesDto> answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return certificatesDtos;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/certificates")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void certificates_testGetCertificateById() throws Exception {
		CertificatesDto certificatesDto = MasterData.getCertificatesDto();
		certificatesDto.setId(1);
		when(this.certificatesService.getById(1)).thenReturn(certificatesDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/certificates/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(certificatesDto))
						? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void certificates_testGetCertificateByIdIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		CertificatesDto certificatesDto = MasterData.getCertificatesDto();
		certificatesDto.setId(1);
		when(this.certificatesService.getById(1)).then(new Answer<CertificatesDto>() {

			@Override
			public CertificatesDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return certificatesDto;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/certificates/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void certificates_testUpdateCertificate() throws Exception {
		CertificatesDto certificatesDto = MasterData.getCertificatesDto();
		CertificatesDto savedCertificatesDto = MasterData.getCertificatesDto();

		when(this.certificatesService.updateCertificate(certificatesDto)).thenReturn(savedCertificatesDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/certificates")
				.content(MasterData.asJsonString(certificatesDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(savedCertificatesDto))
						? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void certificates_testUpdateSkillIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		CertificatesDto certificatesDto = MasterData.getCertificatesDto();
		when(this.certificatesService.updateCertificate(certificatesDto)).then(new Answer<CertificatesDto>() {

			@Override
			public CertificatesDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return certificatesDto;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/certificates")
				.content(MasterData.asJsonString(certificatesDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void certificates_testDeleteCertificateById() throws Exception {
		CertificatesDto certificatesDto = MasterData.getCertificatesDto();
		certificatesDto.setId(1);

		when(this.certificatesService.deleteCertificate(1)).thenReturn(true);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/certificates/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(true)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void certificates_testDeleteCertificateIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		CertificatesDto certificatesDto = MasterData.getCertificatesDto();
		certificatesDto.setId(1);
		when(this.certificatesService.deleteCertificate(1)).then(new Answer<Boolean>() {

			@Override
			public Boolean answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return true;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/certificates/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void certificates_testFindCertificateByEmployeeId() throws Exception {
		List<CertificatesDto> certificatesDtos = MasterData.getCertificatesDtoList();
		when(this.certificatesService.findCertificatesByEmployeeId(1)).thenReturn(certificatesDtos);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/certificates/employee/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(certificatesDtos))
						? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void certificates_testFindCertificatesByEmployeeIdIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		List<CertificatesDto> certificatesDtos = MasterData.getCertificatesDtoList();

		when(this.certificatesService.findCertificatesByEmployeeId(1)).then(new Answer<List<CertificatesDto>>() {

			@Override
			public List<CertificatesDto> answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return certificatesDtos;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/certificates/employee/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void certificates_testFindCertificatesBySkillName() throws Exception {
		List<CertificatesDto> certificateDtos = MasterData.getCertificatesDtoList();
		when(this.certificatesService.findCertificatesBySkillName("Java")).thenReturn(certificateDtos);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/certificates/skills/Java")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(certificateDtos))
						? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void certificates_testFindCertificatesBySkillNameIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		List<CertificatesDto> certificatesDtos = MasterData.getCertificatesDtoList();

		when(this.certificatesService.findCertificatesBySkillName("Java")).then(new Answer<List<CertificatesDto>>() {

			@Override
			public List<CertificatesDto> answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return certificatesDtos;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/certificates/skills/Java")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

}
