package com.togogotrain.moumoubicycle;

import com.github.pagehelper.PageInfo;
import com.togogotrain.moumoubicycle.entity.Journey;
import com.togogotrain.moumoubicycle.entity.User;
import com.togogotrain.moumoubicycle.web.service.JourneyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cjd
 * Date: 2018/10/11
 * Time: 22:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JourneyServiceTest {

    @Autowired
    private JourneyService journeyService;

    @Test
    public void findJourneyByUser() {
        User user = new User();
        user.setId(1L);
        List<Journey> page =
                journeyService.getJourneys(user, 1, 10);
        System.out.println(new PageInfo<Journey>(page).getList().toString());
    }
}
