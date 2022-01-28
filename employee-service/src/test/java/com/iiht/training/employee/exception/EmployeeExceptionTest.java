package com.iiht.training.employee.exception;

import static com.iiht.training.employee.testutils.TestUtils.currentTest;
import static com.iiht.training.employee.testutils.TestUtils.exceptionTestFile;
import static com.iiht.training.employee.testutils.TestUtils.testReport;
import static com.iiht.training.employee.testutils.TestUtils.yakshaAssert;
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

import com.iiht.training.employee.controller.EmployeeRestController;
import com.iiht.training.employee.dto.EmployeeDto;
import com.iiht.training.employee.exceptions.EmployeeNotFoundException;
import com.iiht.training.employee.exceptions.ExceptionResponse;
import com.iiht.training.employee.proxy.CertificateServiceProxy;
import com.iiht.training.employee.proxy.SkillsServiceProxy;
import com.iiht.training.employee.service.EmployeeService;
import com.iiht.training.employee.testutils.MasterData;

@WebMvcTest(EmployeeRestController.class)
@AutoConfigureMockMvc
public class EmployeeExceptionTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EmployeeService employeeService;
	@MockBean
	private SkillsServiceProxy proxy;

	@MockBean
	private CertificateServiceProxy serviceProxy;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void employee_testRegisterEmpoyeeInvalidDataException() throws Exception {
		EmployeeDto employeeDto = MasterData.getEmployeeDto();
		EmployeeDto savedEmployeeDto = MasterData.getEmployeeDto();
		savedEmployeeDto.setId(1);
		employeeDto.setUsername("Ab");

		when(this.employeeService.saveEmployee(savedEmployeeDto)).thenReturn(savedEmployeeDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/employees")
				.content(MasterData.asJsonString(employeeDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);

	}

	@Test
	public void employee_testUpdateEmpoyeeInvalidDataException() throws Exception {
		EmployeeDto employeeDto = MasterData.getEmployeeDto();
		EmployeeDto savedEmployeeDto = MasterData.getEmployeeDto();
		savedEmployeeDto.setId(1);
		employeeDto.setUsername("Ab");

		when(this.employeeService.updateEmployee(savedEmployeeDto)).thenReturn(savedEmployeeDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/employees")
				.content(MasterData.asJsonString(employeeDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);

	}

	@Test
	public void employee_testGetEmployeeByIdEmployeeNotFoundException() throws Exception {
		ExceptionResponse exResponse = new ExceptionResponse("Employee with Id - 1 does not exists",
				System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());

		when(this.employeeService.getById(1))
				.thenThrow(new EmployeeNotFoundException("Employee with Id - 1 does not exists"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/employees/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);

	}

	@Test
	public void employee_testDeleteEmployeeByIdEmployeeNotFoundException() throws Exception {
		ExceptionResponse exResponse = new ExceptionResponse("Employee with Id - 1 does not exists",
				System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());

		when(this.employeeService.deleteEmployee(1))
				.thenThrow(new EmployeeNotFoundException("Employee with Id - 1 does not exists"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/employees/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);

	}

	@Test
	public void employee_testGetSkillsByEmployeeIdEmployeeNotFoundException() throws Exception {
		ExceptionResponse exResponse = new ExceptionResponse("Employee with Id - 1 does not exists",
				System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());

		when(this.proxy.skillsByEmployeeId(1))
				.thenThrow(new EmployeeNotFoundException("Employee with Id - 1 does not exists"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/employees/skills/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);

	}

	@Test
	public void employee_testGetCertificatesByEmployeeIdEmployeeNotFoundException() throws Exception {
		ExceptionResponse exResponse = new ExceptionResponse("Employee with Id - 1 does not exists",
				System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());

		when(this.serviceProxy.getCertificatesByEmployeeId(1))
				.thenThrow(new EmployeeNotFoundException("Employee with Id - 1 does not exists"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/employees/certificates/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);

	}

}
