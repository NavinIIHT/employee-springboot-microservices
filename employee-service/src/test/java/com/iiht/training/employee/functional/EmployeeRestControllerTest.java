package com.iiht.training.employee.functional;

import static com.iiht.training.employee.testutils.TestUtils.businessTestFile;
import static com.iiht.training.employee.testutils.TestUtils.currentTest;
import static com.iiht.training.employee.testutils.TestUtils.testReport;
import static com.iiht.training.employee.testutils.TestUtils.yakshaAssert;
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

import com.iiht.training.employee.controller.EmployeeRestController;
import com.iiht.training.employee.dto.CertificatesDto;
import com.iiht.training.employee.dto.EmployeeDto;
import com.iiht.training.employee.dto.SkillsDto;
import com.iiht.training.employee.proxy.CertificateServiceProxy;
import com.iiht.training.employee.proxy.SkillsServiceProxy;
import com.iiht.training.employee.service.EmployeeService;
import com.iiht.training.employee.testutils.MasterData;

@WebMvcTest(EmployeeRestController.class)
@AutoConfigureMockMvc
public class EmployeeRestControllerTest {
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
	public void employee_testSaveEmployee() throws Exception {
		EmployeeDto employeeDto = MasterData.getEmployeeDto();
		EmployeeDto savedEmployeeDto = MasterData.getEmployeeDto();
		savedEmployeeDto.setId(1);

		when(this.employeeService.saveEmployee(employeeDto)).thenReturn(savedEmployeeDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/employees")
				.content(MasterData.asJsonString(employeeDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(savedEmployeeDto))
						? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void employee_testSaveEmployeeIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		EmployeeDto employeeDto = MasterData.getEmployeeDto();
		when(this.employeeService.saveEmployee(employeeDto)).then(new Answer<EmployeeDto>() {

			@Override
			public EmployeeDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return employeeDto;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/employees")
				.content(MasterData.asJsonString(employeeDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void employee_testGetAllEmployees() throws Exception {
		List<EmployeeDto> employeeDtos = MasterData.getEmployeeDtoList();

		when(this.employeeService.findAll()).thenReturn(employeeDtos);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/employees")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(employeeDtos)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void employee_testGetAllEmployeesIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		List<EmployeeDto> employeeDtos = MasterData.getEmployeeDtoList();
		when(this.employeeService.findAll()).then(new Answer<List<EmployeeDto>>() {

			@Override
			public List<EmployeeDto> answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return employeeDtos;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/employees")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void employee_testGetEmployeeById() throws Exception {
		EmployeeDto employeeDto = MasterData.getEmployeeDto();
		employeeDto.setId(1);
		when(this.employeeService.getById(1)).thenReturn(employeeDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/employees/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(employeeDto)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void employee_testGetEmployeeByIdIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		EmployeeDto employeeDto = MasterData.getEmployeeDto();
		employeeDto.setId(1);
		when(this.employeeService.getById(1)).then(new Answer<EmployeeDto>() {

			@Override
			public EmployeeDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return employeeDto;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/employees/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void employee_testUpdateEmployee() throws Exception {
		EmployeeDto employeeDto = MasterData.getEmployeeDto();
		EmployeeDto savedEmployeeDto = MasterData.getEmployeeDto();
		savedEmployeeDto.setId(1);

		when(this.employeeService.updateEmployee(employeeDto)).thenReturn(savedEmployeeDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/employees")
				.content(MasterData.asJsonString(employeeDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(savedEmployeeDto))
						? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void employee_testUpdateEmployeeIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		EmployeeDto employeeDto = MasterData.getEmployeeDto();
		when(this.employeeService.updateEmployee(employeeDto)).then(new Answer<EmployeeDto>() {

			@Override
			public EmployeeDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return employeeDto;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/employees")
				.content(MasterData.asJsonString(employeeDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void employee_testDeleteEmployee() throws Exception {
		EmployeeDto employeeDto = MasterData.getEmployeeDto();
		employeeDto.setId(1);

		when(this.employeeService.deleteEmployee(1)).thenReturn(true);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/employees/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(true)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void employee_testDeleteEmployeeIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		EmployeeDto employeeDto = MasterData.getEmployeeDto();
		employeeDto.setId(1);
		when(this.employeeService.deleteEmployee(1)).then(new Answer<Boolean>() {

			@Override
			public Boolean answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return true;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/employees/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void employee_testGetSkillsByEmployeeId() throws Exception {
		List<SkillsDto> skillsDtos = MasterData.getSkillsDtoList();
		when(this.proxy.skillsByEmployeeId(1)).thenReturn(skillsDtos);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/employees/skills/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(skillsDtos)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void employee_testGetSkillsByEmployeeIdIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		List<SkillsDto> skillsDtos = MasterData.getSkillsDtoList();

		when(this.proxy.skillsByEmployeeId(1)).then(new Answer<List<SkillsDto>>() {

			@Override
			public List<SkillsDto> answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return skillsDtos;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/employees/skills/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void employee_testGetCertificatesByEmployeeId() throws Exception {
		List<CertificatesDto> certificateDtos = MasterData.getCertificatesDtoList();
		when(this.serviceProxy.getCertificatesByEmployeeId(1)).thenReturn(certificateDtos);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/employees/certificates/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(certificateDtos))
						? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void employee_testGetCertificatesByEmployeeIdIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		List<CertificatesDto> certificatesDtos = MasterData.getCertificatesDtoList();

		when(this.serviceProxy.getCertificatesByEmployeeId(1)).then(new Answer<List<CertificatesDto>>() {

			@Override
			public List<CertificatesDto> answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return certificatesDtos;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/employees/certificates/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

}
