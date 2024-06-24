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

import teamb.com.services.UsersService;

@SpringBootTest
@AutoConfigureMockMvc
public class UserRegisterControllerTest {
	@Autowired
	private MockMvc mocMvc;
	//実際のサービスクラスのコードを呼び出すことなく、いくつかの固定の戻り値を直接返すように定義
	@MockBean
	private UsersService userService;
	
	//サービスクラスを使ったデータを作成
	@BeforeEach
	public void prepareData() {
		//登録できる場合 "test" "test@test.com" "test1234" true
		when(userService.createUser("test" , "test@test.com" , "test1234")).thenReturn(true);
		//登録できない場合 userName="error" メールアドレスとパスワードはどんな値でも良い false
		when(userService.createUser(eq("error") , any() , any())).thenReturn(false);
	}
	
	//登録画面が正常に表示できるテスト
	@Test
	public void testGetregisterPage_Succeed() throws Exception {
		RequestBuilder rquest = MockMvcRequestBuilders
				.get("/user/register");
		
		mocMvc.perform(rquest).andExpect(view().name("user_register.html"));
	}
	
	// ユーザーの登録が成功するかのテスト
	@Test
	public void testRegister_NewUser_Succeed() throws Exception {
		RequestBuilder rquest = MockMvcRequestBuilders
				.post("/user/register/process")
				.param("userName", "test")
				.param("userEmail", "test@test.com")
				.param("userPassword", "test1234");
		
		mocMvc.perform(rquest).andExpect(view().name("user_login.html"));
	}
	
	//ユーザーの登録が失敗するのかのテスト
	@Test
	public void testRegister_ExistingUserName_False() throws Exception {
		RequestBuilder rquest = MockMvcRequestBuilders
				.post("/user/register/process")
				.param("userName", "error")
				.param("userEmail", "test@test.com")
				.param("userPassword", "test1234");
		
		mocMvc.perform(rquest).andExpect(view().name("user_register.html"));
	}

}
