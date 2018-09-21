/**
 * 
 */
package com.gb.handle;

import static org.junit.Assert.*;

import java.sql.Timestamp;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author huangdb
 *
 *@date 2018年4月19日
 */
@RunWith(SpringRunner.class)
//@DataJpaTest
@SpringBootTest
public class SeaCmsContentHandleTest {
	
	@Autowired
	SeaCmsContentHandle seaCmsMakeHtml;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * Test method for {@link com.gb.handle.SeaCmsContentHandle#makeHtml()}.
	 */
	@Ignore
	@Test
	public void testMakeHtml() {
//		seaCmsMakeHtml.makeHtml();
	}
	
	@Test
	public void getData() {
		String cYmd="2016-8-29";
		Timestamp fromTs = Timestamp.valueOf(cYmd + " 00:00:00");//Timestamp aa = new Timestamp(0l);
		Timestamp toTs = Timestamp.valueOf(cYmd + " 23:59:59");
		Pageable pageable=new PageRequest(0,15);
		
		seaCmsMakeHtml.makeByData((int)(fromTs.getTime()/1000), (int)(toTs.getTime()/1000),pageable);
	}
	
}
