package com.jpattern.gwt.client.spike;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.Splittable;

/**
 * 
 * @author Francesco Cina'
 *
 * 23/ago/2011
 */
public class AutoBeanSerializerTestGwt extends GWTTestCase {

	@Override
	public String getModuleName() {
		return "com.jpattern.gwt.jpattern_tests";
	}
	
	@Override
	protected void gwtSetUp() throws Exception {
	}

	@Override
	protected void gwtTearDown() throws Exception {
	}

	public void testSerializeDeserialize1() throws Exception {
		
		BeanFactory beanFactory = GWT.create(BeanFactory.class);
				
		Bean bean1 = new Bean("hello-world");
		
		AutoBean<IBean> autoBean = beanFactory.create(IBean.class, bean1);
		String bean1Json = AutoBeanCodex.encode(autoBean).getPayload();
		
		System.out.println("Serialized bean1: " + bean1Json);
		
		IBean bean1Deserialized = AutoBeanCodex.decode(beanFactory, IBean.class, bean1Json).as();
		
		assertEquals( bean1.getHello() , bean1Deserialized.getHello() );
	}
	
	public void testSerializeDeserialize2() throws Exception {
		
		BeanFactory beanFactory = GWT.create(BeanFactory.class);
				
		IBeanList beanList = new BeanList();
		beanList.getList().add( new Bean("hello1") );
		beanList.getList().add( new Bean("hello2") );
		beanList.getList().add( new Bean("hello3") );
		
		for (IBean bean : beanList.getList()) {
			AutoBean<IBean> autoBean = beanFactory.create(IBean.class, bean);
			AutoBeanCodex.encode(autoBean);
		}
		
		AutoBean<IBeanList> autoBean = beanFactory.create(IBeanList.class, beanList);
		Splittable encodeResult = AutoBeanCodex.encode(autoBean);
		String beanListJson = encodeResult.getPayload();
		
//		String beanListJson = "{\"list\":[{\"hello\":\"hello1\"},{\"hello\":\"hello2\"},{\"hello\":\"hello3\"}]}";
		System.out.println("Serialized beanList: " + beanListJson);
		
		IBeanList beanListDeserialized = AutoBeanCodex.decode(beanFactory, IBeanList.class, beanListJson).as();
		
		assertEquals( beanList.getList().size() , beanListDeserialized.getList().size() );
		
		int size = beanListDeserialized.getList().size();
		System.out.println("Deserialized list size: " + size);
		System.out.println("Adding a new element to the list...");
		beanListDeserialized.getList().add( new Bean("hello4") );
		System.out.println("NEW Deserialized list size: " + beanListDeserialized.getList().size());
		int hello; //GWT BUG!! 
//		assertTrue( beanListDeserialized.getList().size() == (size+1) );
	}

	public void testSerializeDeserializeString() throws Exception {
		
		BeanFactory beanFactory = GWT.create(BeanFactory.class);
				
		IStringList stringList = new StringList();
		stringList.getList().add( "hello1" );
		stringList.getList().add( "hello2" );
		stringList.getList().add( "hello3" );
		
		AutoBean<IStringList> autoBean = beanFactory.create(IStringList.class, stringList);
		Splittable encodeResult = AutoBeanCodex.encode(autoBean);
		String beanListJson = encodeResult.getPayload();
		
		System.out.println("Serialized beanList: " + beanListJson);
		
		IStringList beanListDeserialized = AutoBeanCodex.decode(beanFactory, IStringList.class, beanListJson).as();
		
		assertEquals( stringList.getList().size() , beanListDeserialized.getList().size() );
		
		int size = beanListDeserialized.getList().size();
		System.out.println("Deserialized list size: " + size);
		System.out.println("Adding a new element to the list...");
		beanListDeserialized.getList().add( "hello4" );
		System.out.println("NEW Deserialized list size: " + beanListDeserialized.getList().size());
		assertTrue( beanListDeserialized.getList().size() == (size+1) );
	}
}
