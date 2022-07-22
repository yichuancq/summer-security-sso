import com.summer.oath.OathServerApplication;
import com.summer.oath.domain.SysRole;
import com.summer.oath.mapper.SysUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
//需要的启动类
@SpringBootTest(classes = {OathServerApplication.class})
public class UserTest {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    public void testPass() {
        String passString = bCryptPasswordEncoder.encode("123456");
        log.info("pass word:{}", passString);
        System.out.println(passString);
        bCryptPasswordEncoder.upgradeEncoding(passString);
        //
        boolean flag = bCryptPasswordEncoder.matches("admin", passString);
        System.out.println(flag);

    }

    @Test
    public void test() {
        List<SysRole> roleList = sysUserMapper.findRoleByUserId(1);
        for (SysRole sysRole : roleList) {
            System.out.println(sysRole);

        }
    }
}
