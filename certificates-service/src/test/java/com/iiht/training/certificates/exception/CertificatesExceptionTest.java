package com.iiht.training.certificates.exception;

import static com.iiht.training.certificates.testutils.TestUtils.currentTest;
import static com.iiht.training.certificates.testutils.TestUtils.exceptionTestFile;
import static com.iiht.training.certificates.testutils.TestUtils.testReport;
import static com.iiht.training.certificates.testutils.TestUtils.yakshaAssert;
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

import com.iiht.training.certificates.controller.CertificatesRestController;
import com.iiht.training.certificates.dto.CertificatesDto;
import com.iiht.training.certificates.exceptions.CertificateNotFoundException;
import com.iiht.training.certificates.exceptions.ExceptionResponse;
import com.iiht.training.certificates.service.CertificatesService;
import com.iiht.training.certificates.testutils.MasterData;

@WebMvcTest(CertificatesRestController.class)
@AutoConfigureMockMvc
public class CertificatesExceptionTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CertificatesService certificatesService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void certificates_testGenerateCertificateInvalidDataException() throws Exception {
		CertificatesDto certificatesDto = MasterData.getCertificatesDto();
		CertificatesDto savedCertificatesDto = MasterData.getCertificatesDto();
		savedCertificatesDto.setId(1);
		certificatesDto.setSkillName("Ab");

		when(this.certificatesService.generateCertificate(certificatesDto)).thenReturn(savedCertificatesDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/certificates")
				.content(MasterData.asJsonString(certificatesDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);

	}

	@Test
	public void certificates_testUpdateCertificateInvalidDataException() throws Exception {
		CertificatesDto certificatesDto = MasterData.getCertificatesDto();
		CertificatesDto savedCertificatesDto = MasterData.getCertificatesDto();
		savedCertificatesDto.setId(1);
		certificatesDto.setSkillName("Ab");

		when(this.certificatesService.generateCertificate(certificatesDto)).thenReturn(savedCertificatesDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/certificates")
				.content(MasterData.asJsonString(certificatesDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);

	}

	@Test
	public void certificates_testGetCertificateByIdCertificateNotFoundException() throws Exception {
		ExceptionResponse exResponse = new ExceptionResponse("Certificate with Id - 1 does not exists",
				System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());

		when(this.certificatesService.getById(1))
				.thenThrow(new CertificateNotFoundException("Certificate with Id - 1 does not exists"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/certificates/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);

	}

	@Test
	public void certificates_testDeleteCertificateByIdCertificateNotFoundException() throws Exception {
		ExceptionResponse exResponse = new ExceptionResponse("Certificate with Id - 1 does not exists",
				System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());

		when(this.certificatesService.deleteCertificate(1))
				.thenThrow(new CertificateNotFoundException("Certificate with Id - 1 does not exists"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/certificates/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);

	}

}
