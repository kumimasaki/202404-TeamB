package teamb.com.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import jakarta.servlet.http.HttpSession;
import teamb.com.models.entity.Admin;
import teamb.com.services.AdminService;

@SpringBootTest
@AutoConfigureMockMvc
public class AdminLoginControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AdminService adminService;
	
	@Autowired
	private HttpSession session;
	
	// サービスクラスを使ったデータ作成
	@BeforeEach
	public void prepareData() {
		session = new MockHttpSession();
		Admin admin = new Admin("gero", "gero@test.com", "1234abcd");
		
		// ログイン成功： adminEmail "gero@test.com", password "1234abcd" 
		when(adminService.loginCheck("gero@test.com", "1234abcd")).thenReturn(admin);
		// ログイン失敗： adminEmail="gero@test.com", password="1234"
		when(adminService.loginCheck(any(), eq("1234"))).thenReturn(null);
		// ログイン失敗： adminEmail="batu@test.com", password="1234abcd"
		when(adminService.loginCheck(eq("batu@test.com"), any())).thenReturn(null);

	}
	
	// ログイン画面を正しく取得するテスト
	@Test
	public void testGetAdminLoginPage_Succeed() throws Exception{
		RequestBuilder request = MockMvcRequestBuilders
				.get("/admin/login");
		
		mockMvc.perform(request)
				.andExpect(view().name("adminLogin.html"));
	}
	
	// ログインが成功した場合のテスト
	// ログインが成功したら「coursesList」に遷移すること、入力された値が渡されていることのテスト
	@Test
	public void testAdminLoginProcess_CorrectEmailAndPass_Succeed() throws Exception{
		RequestBuilder request = MockMvcRequestBuilders
				.post("/admin/login/process")
				.param("email", "gero@test.com")
				.param("password", "1234abcd");
		
		mockMvc.perform(request)
				.andExpect(redirectedUrl("/admin/course"));
	}
	
	// ログインが失敗した場合のテスト
	//password 間違った
	@Test
	public void testAdminLoginProcess_OtherPass_False() throws Exception{
		RequestBuilder request = MockMvcRequestBuilders
				.post("/admin/login/process")
				.param("email", "gero@test.com")
				.param("password", "1234");
		
		mockMvc.perform(request)
				.andExpect(view().name("adminLogin.html"));
	}
	
	// ログインが失敗した場合のテスト
	//email間違った
	@Test
	public void testAdminLoginProcess_OtherEmail_False() throws Exception{
		RequestBuilder request = MockMvcRequestBuilders
				.post("/admin/login/process")
				.param("email", "batu@test.com")
				.param("password", "1234abcd");
		
		mockMvc.perform(request)
				.andExpect(view().name("adminLogin.html"));
	}
	
	// ログインが失敗した場合のテスト
	//email と　password間違った
	@Test
	public void testAdminLoginProcess_OtherEmailAndPass_False() throws Exception{
		RequestBuilder request = MockMvcRequestBuilders
				.post("/admin/login/process")
				.param("email", "batu@test.com")
				.param("password", "1234abcd");
		
		mockMvc.perform(request)
				.andExpect(view().name("adminLogin.html"));
	}
	
}
