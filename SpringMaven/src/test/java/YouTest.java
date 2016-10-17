
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yvdedu.guan.entity.Access;
import com.yvdedu.guan.service.AccessService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:Annotation.xml")
public class YouTest {

	@Resource
	private AccessService accessService;
	
	@Test
	public void test() {
//		String mm = accessService.findbyId(1);
//		System.err.println(mm);
	}

}
