package com.gb.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import com.gb.entity.SeaType;

@RunWith(SpringRunner.class)
//@DataJpaTest
@SpringBootTest
public class ISeaTypeDaoTest {

	@Autowired
	ISeaTypeDao iSeaTypeDao;
	
	@Test
	public final void testFindByupidFrom() {
		
		Pageable pageable=new PageRequest(0,6);
		List<SeaType> seaTypes=iSeaTypeDao.findByupidFrom(pageable);
		Assert.notNull(seaTypes);
		for (int i = 0; i < seaTypes.size(); i++) {
			SeaType seaType=seaTypes.get(i);
			System.out.println(seaType.getTname());
		}
		
	}

}
