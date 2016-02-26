package com.lihuanyu.yiban;

import com.lihuanyu.yiban.model.Creator;
import com.lihuanyu.yiban.model.CreatorDao;
import com.lihuanyu.yiban.services.CreateService;
import com.lihuanyu.yiban.services.TestLotteryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.util.Collection;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ChoujiangApplication.class)
@WebAppConfiguration
public class ChoujiangApplicationTests {

	@Autowired
	private CreatorDao creatorDao;

	@Autowired
	private CreateService createService;

	@Test
	public void contextLoads() {
		TestLotteryService testLotteryService = new TestLotteryService();
		testLotteryService.test();
	}

}
