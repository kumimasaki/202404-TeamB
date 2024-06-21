package teamb.com.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import teamb.com.services.AdminService;

@SpringBootTest
@AutoConfigureMockMvc
public class AdminRegisterControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AdminService adminService;
	
	@BeforeEach
	public void prepareDate() {
		//登録できる場合　"test","test@test.com","test1234"
		when(adminService.createAdmin("test", "test@test.com", "test1234")).thenReturn(true);
		//登録できない場合　"test1","test1@test.com","test12345"
		when(adminService.createAdmin(any(), eq("test1@test.com"), any())).thenReturn(false);
	}
	
	// 登録画面が正常に表示できるテスト
	@Test
	public void getRegisterPage_succeed() throws Exception{
		RequestBuilder request = MockMvcRequestBuilders
				.get("/admin/register");
		mockMvc.perform(request)
			.andExpect(view().name("adminRegister.html"));
	}
	
	// ユーザーの登録が成功するかのテスト
	@Test
	public void testRegister_NewEmail_succeed() throws Exception{
		RequestBuilder request = MockMvcRequestBuilders
				.post("/admin/register/process")
				.param("userName", "test")
				.param("email", "test@test.com")
				.param("password", "test1234");
		mockMvc.perform(request)
			.andExpect(view().name("adminLogin.html"));
	}
	
	// ユーザーの登録が失敗するかのテスト
	@Test
	public void testRegister_Existing_false() throws Exception{
		RequestBuilder request = MockMvcRequestBuilders
				.post("/admin/register/process")
				.param("userName", "test1")
				.param("email", "test1@test.com")
				.param("password", "test12345");
		mockMvc.perform(request)
			.andExpect(view().name("adminRegister.html"));
	}
	
	
	
	
	
}
