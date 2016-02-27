package com.lihuanyu.yiban;

import com.lihuanyu.yiban.model.CreatorDao;
import com.lihuanyu.yiban.services.CreateService;
import com.lihuanyu.yiban.services.LotteryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ChoujiangApplication.class)
@WebAppConfiguration
public class ChoujiangApplicationTests {

	@Autowired
	private CreatorDao creatorDao;

	@Autowired
	private CreateService createService;

	@Autowired
	private LotteryService lotteryService;

	@Test
	public void contextLoads() {
		int p1 = 1;
		int p2 = 5;
		int p3 = 10;
		int p4 = 10;
		for(int i=0;i<100;i++) {
			System.out.println(lotteryService.lottery(p1,p2,p3,p4,10,100,150,200));
		}
	}

}
