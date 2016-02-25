package com.lihuanyu.yiban;

import com.lihuanyu.yiban.model.Creator;
import com.lihuanyu.yiban.model.CreatorDao;
import com.lihuanyu.yiban.services.CreateService;
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
		int yibanid = 5394432;
		String yibanname = "haha";
		String lotteryname = "123";
		String lotteryintro = "222";
		int a = 1;
		int b = 2;
		int c = 3;
		int d = 4;
		Timestamp timebegin = Timestamp.valueOf("2016-02-26 01:01:00.0");
		Timestamp timeend = Timestamp.valueOf("2016-02-27 01:01:00.0");
		String result = createService.saveCreatorAndLottery(yibanid,yibanname,lotteryname,lotteryintro,timebegin,timeend,a,b,c,d);
		System.out.println(result);
	}

}
