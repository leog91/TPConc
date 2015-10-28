package concTest;

import org.junit.*;

import conc.ConcurVector;
import junit.framework.TestCase;

public class ConcurVectorTest{

	
	ConcurVector cv, cv0,cv2,cv3,cvMask;
	
	
	@Before
	public void setUp(){
		
		cv = new ConcurVector(10, 2);
//		cv = new ConcurVector(10);
//		cv0 = new ConcurVector(10);
//		cv2 = new ConcurVector(10);
//		cv3 = new ConcurVector(3);
//		cvMask = new ConcurVector(10);

	}
	
	@Test
	public void dimensionTest(){
		
		Assert.assertEquals(10, cv.dimension());
	}
	
	@Test
	public void setAndGetTets(){
		
		cv.set(3);
		Assert.assertEquals(3, cv.get(2),0.001);
		Assert.assertEquals(3, cv.get(3),0.001);
		Assert.assertEquals(3, cv.get(4),0.001);
		
		cv.set(6, 6);
		cv.set(5, 5);
		Assert.assertEquals(3, cv.get(3),0.001);
		Assert.assertEquals(5, cv.get(5),0.001);
		Assert.assertEquals(6, cv.get(6),0.001);
		
	}

	@Test
	public void assignTest(){
		
		cv.set(4);
		cv2.assign(cv);
		
		Assert.assertEquals(4, cv2.get(3),0.01);
		
	}
	
	@Test
	public void assignWithMaskTest(){
		
		cv2.set(7);
		cv.set(0);
		cvMask.set(1);
		cvMask.set(6, -3);
		cv.assign(cvMask, cv2);
		
		Assert.assertEquals(7, cv.get(3),0.01);
		Assert.assertEquals(0, cv.get(6),0.01);
		
	}

	@Test
	public void absTest(){
	
		cv.set(-5);
		cv.abs();
		
		Assert.assertEquals(5, cv.get(3),0.01);
		
	}
	
	@Test
	public void addTest(){
		
		cv.set(3);
		cv0.set(4);
		cv.add(cv0);
		
		Assert.assertEquals(7, cv.get(3),0.01);
	}
	
	@Test
	public void subTest(){
		
		cv.set(11);
		cv0.set(-3);
		cv.sub(cv0);
		
		Assert.assertEquals(14, cv.get(2),0.01);
	}
	
	@Test
	public void mulTest(){
		
		cv.set(3);
		cv0.set(2);
		cv0.set(4, 11);
		cv.mul(cv0);
		
		Assert.assertEquals(6,cv.get(2),0);
		Assert.assertEquals(33,cv.get(4),0);
	}
	
	@Test
	public void divTest(){
	
		cv.set(40);
		cv0.set(10);
		cv0.set(3, 4);
		cv.div(cv0);
		
		Assert.assertEquals(4, cv.get(2),0.01);
		Assert.assertEquals(10, cv.get(3),0.01);
	}
	
	@Test
	public void sumTest(){
	
		cv.set(1);
		cv0.set(3);
		
		Assert.assertEquals(10, cv.sum(),0.01);
		Assert.assertEquals(30, cv0.sum(),0.01);
	}
//	
//	@Test
//	public void dotProductTest(){
//	
//		cv.set(1);
//		cv0.set(2);
//		
//		Assert.assertEquals(20, cv.prod(cv0),0.01);
//		
//		cv0.set(0);
//		
//		Assert.assertEquals(0, cv.prod(cv0),0.01);
//	}
//	
//	@Test
//	public void normTest(){
//	
//		cv3.set(1);
//		
//		Assert.assertEquals(Math.sqrt(3), cv3.norm(),0.01);
//		
//		cv3.set(2,6);
//		
//		Assert.assertEquals(Math.sqrt(38), cv3.norm(),0.01);
//	}
//	
//	@Test
//	public void nomalizeTest(){
//		
//		cv0.set(9);
//		cv2.set(-2);
//		cv3.set(4);
//		cv0.normalize();
//		cv2.normalize();
//		cv3.normalize();
//		
//		Assert.assertTrue(cv0.get(2)<= 1 && cv0.get(2)>= -1);
//		Assert.assertTrue(cv2.get(2)<= 1 && cv2.get(2)>= -1);
//		Assert.assertTrue(cv3.get(2)<= 1 && cv3.get(2)>= -1);
//	}
//	
//	@Test
//	public void maxTest(){
//	
//		cv.set(7);
//		cv0.set(3);
//		cv0.set(4, 20);
//		cv.max(cv0);
//		
//		Assert.assertEquals(7, cv.get(2),0.01);
//		Assert.assertEquals(20, cv.get(4),0.01);
//	}
//	
//	@Test
//	public void testMin(){
//		
//		cv.set(7);
//		cv0.set(3);
//		cv0.set(4, -2);
//		cv.min(cv0);
//		
//		Assert.assertEquals(3, cv.get(2),0.01);
//		Assert.assertEquals(-2, cv.get(4),0.01);
//	}
	
}

