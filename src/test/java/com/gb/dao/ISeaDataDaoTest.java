package com.gb.dao;

import java.sql.Timestamp;
import java.util.List;

import javax.print.attribute.standard.DateTimeAtCompleted;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import com.gb.entity.SeaData;
import com.gb.service.specification.SimpleSpecificationBuilder;
import com.gb.service.specification.SpecificationOperator.Operator;

@RunWith(SpringRunner.class)
//@DataJpaTest
@SpringBootTest
public class ISeaDataDaoTest {

	@Autowired
	private ISeaDataDao iSeaDataDao;
	
	@Test
	public void testFindByvAddtime() {
//		SeaData seaData=iSeaDataDao.findOne(5);
//		System.out.println(seaData.getVActor());
		
//		iSeaDataDao.findOne(spec);
		
//		long count=iSeaDataDao.count();
//		System.out.println(count);
//		Assert.isNull(seaData);
//		assertThat(seaData.getVActor()).isEqualTo("1");
//		Assert
//		assertt
//		SimpleSpecificationBuilder<SeaData> builder = new SimpleSpecificationBuilder<SeaData>();
//		String searchText = "抉择";
//		if(StringUtils.isNotBlank(searchText)){
//			builder.add("vName", Operator.likeAll.name(), searchText);
//		}
//		List<SeaData> seadatas=iSeaDataDao.findAll(builder.generateSpecification());
		String cYmd="2016-8-29";
		Timestamp fromTs = Timestamp.valueOf(cYmd + " 00:00:00");//Timestamp aa = new Timestamp(0l);
		Timestamp toTs = Timestamp.valueOf(cYmd + " 23:59:59");
		System.out.println(fromTs.getTime()/1000);
		System.out.println(toTs.getTime()/1000);
		
//		DateTime today=new DateTime();
		
		Pageable pageable=new PageRequest(0,5);
//		List<SeaData> seadatas=iSeaDataDao.findByvAddtimeBetweenTime((int)(fromTs.getTime()/1000), (int)(toTs.getTime()/1000),pageable);
//		for (int i = 0; i < seadatas.size(); i++) {
//		SeaData seaData=seadatas.get(i);
////		System.out.println(seaData.getVName());
////		System.out.println(seaData.getVAddtime());
////		System.out.println(seaData.getSeaContent().getBody());
//		
//	}
		Page<SeaData> seadatas=iSeaDataDao.findByvAddtimePage((int)(fromTs.getTime()/1000), (int)(toTs.getTime()/1000),pageable);
		
//		Assert.notNull(seadatas);;
		
		System.out.println("seadatas.getTotalPages:"+seadatas.getTotalPages());;
		
		for (int i = 0; i < seadatas.getContent().size(); i++) {
			SeaData seaData=seadatas.getContent().get(i);
			System.out.println(seaData.getVName());
		}
		
	}

}
