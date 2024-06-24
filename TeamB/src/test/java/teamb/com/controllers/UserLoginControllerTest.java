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
import teamb.com.models.entity.Users;
import teamb.com.services.UsersService;

@SpringBootTest
@AutoConfigureMockMvc
public class UserLoginControllerTest {
	@Autowired
	private MockMvc mocMvc;
	//実際のサービスクラスのコードを呼び出すことなく、いくつかの固定の戻り値を直接返すように定義
	@MockBean
	private UsersService userService;
	
	@Autowired
	private HttpSession session;
	
	//サービスクラスを使ったデータを作成
	@BeforeEach
	public void prepareData() {
		session = new MockHttpSession();
		Users user = new Users("test" , "test@test.com" , "test1234");
		
		// ログイン成功： userEmail "test@test.com", password "test1234" 
		when(userService.loginCheck("test@test.com", "test1234")).thenReturn(user);
		// ログイン失敗： パスワード
		when(userService.loginCheck(any(), eq("error1234"))).thenReturn(null);
		// ログイン失敗： メールアドレス
		when(userService.loginCheck(eq("error@test.com"), any())).thenReturn(null);
		// ログイン失敗： メールアドレス　パスワード
		when(userService.loginCheck(eq("error@test.com"), eq("error1234"))).thenReturn(null);
	}
	
	//ログイン画面が正常に表示できるテスト
	@Test
	public void testGetloginPage_Succeed() throws Exception {
		RequestBuilder rquest = MockMvcRequestBuilders
				.get("/user/login");
		
		mocMvc.perform(rquest).andExpect(view().name("user_login.html"));
	}
	
	// ログインが成功した場合のテスト
	@Test
	public void testUseroginProcess_Succeed() throws Exception{
		RequestBuilder request = MockMvcRequestBuilders
			.post("/user/login/process")
			.param("userEmail", "test@test.com")
			.param("userPassword", "test1234");
				
			mocMvc.perform(request).andExpect(redirectedUrl("/user/course/all"));
			
	}
	
	// ログインが失敗した場合のテスト
	// メールアドレス失敗
	@Test
	public void testUserLoginProcess_userEmail_False() throws Exception{
		RequestBuilder request = MockMvcRequestBuilders
			.post("/user/login/process")
			.param("userEmail", "error@test.com")
			.param("userPassword", "test1234");
		
			mocMvc.perform(request).andExpect(view().name("user_login.html"));
	}
	
	// ログインが失敗した場合のテスト
	// パスワード失敗
	@Test
	public void testUserLoginProcess_userPassword_False() throws Exception{
		RequestBuilder request = MockMvcRequestBuilders
			.post("/user/login/process")
			.param("userEmail", "test@test.com")
			.param("userPassword", "error1234");
		
			mocMvc.perform(request).andExpect(view().name("user_login.html"));
	}
	
	// ログインが失敗した場合のテスト
	// メールアドレスパスワード失敗
	@Test
	public void testUserLoginProcess_False() throws Exception{
		RequestBuilder request = MockMvcRequestBuilders
			.post("/user/login/process")
			.param("userEmail", "error@test.com")
			.param("userPassword", "error1234");
		
			mocMvc.perform(request).andExpect(view().name("user_login.html"));
	}
	

}
