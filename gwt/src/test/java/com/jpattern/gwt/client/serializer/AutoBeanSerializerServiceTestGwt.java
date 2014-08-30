package com.jpattern.gwt.client.serializer;

import java.util.HashMap;

import org.fusesource.restygwt.client.JsonEncoderDecoder;

import com.google.gwt.core.client.GWT;
import com.jpattern.gwt.client.BaseGwtTestCase;

/**
 * 
 * @author Francesco Cina'
 *
 * 23/ago/2011
 */
public class AutoBeanSerializerServiceTestGwt extends BaseGwtTestCase {

	@Override
	protected void gwtTestCaseSetUp() throws Exception {
	}

	@Override
	protected void gwtTestCaseTearDown() throws Exception {
	}

	public void testSerializeDeserialize1() throws Exception {
		
		ASerializerService serializerService = new AutoBeanSerializerService<IBeanFactory>( (IBeanFactory) GWT.create(ITestBean1Factory.class));
		serializerService.setProvider(getProvider());
		IObjectSerializer<IBean> bean1Serializer = serializerService.getObjectSerializer(IBean.class);
		
		Bean bean1 = new Bean();
		bean1.setHello("hello");
		bean1.setMale(true);
		bean1.setYear(2011);
		
		String bean1Json = bean1Serializer.serialize(bean1);
		
		System.out.println("Serialized bean1: ");
		System.out.println( bean1Json );
		
		IBean bean1Deserialized = bean1Serializer.deserialize(bean1Json);
		
		assertEquals( bean1.getHello() , bean1Deserialized.getHello() );
		assertEquals( bean1.getYear() , bean1Deserialized.getYear());
		assertEquals( bean1.isMale() , bean1Deserialized.isMale());
	}
	
	public void testSerializeDeserializeHolder1() throws Exception {
		
		ASerializerService serializerService = new AutoBeanSerializerService<IBeanFactory>( (IBeanFactory) GWT.create(ITestBean1Factory.class));
		serializerService.setProvider(getProvider());
		IObjectSerializer<IBean1Holder> bean1Serializer = serializerService.getObjectSerializer(IBean1Holder.class);
		
		Bean bean1 = new Bean();
		bean1.setHello("hello1");
		bean1.setMale(true);
		bean1.setYear(2011);
		
		Bean bean2 = new Bean();
		bean2.setHello("hello2");
		bean2.setMale(true);
		bean2.setYear(2012);
		
		Bean bean3 = new Bean();
		bean3.setHello("hello3");
		bean3.setMale(true);
		bean3.setYear(2013);
		
		Bean1Holder beanHolder = new Bean1Holder();
		beanHolder.setBean1(bean1);
		beanHolder.setBean2(bean2);
		beanHolder.setBean3(bean3);
		
		String beanHolderJson = bean1Serializer.serialize(beanHolder);
		
		System.out.println("Serialized beanHolder: ");
		System.out.println( beanHolderJson );
		
//		IBean1 bean1Deserialized = bean1Serializer.deserialize(bean1Json);
//		
//		assertEquals( bean3.getHello() , bean1Deserialized.getHello() );
//		assertEquals( bean3.getYear() , bean1Deserialized.getYear());
//		assertEquals( bean3.isMale() , bean1Deserialized.isMale());
	}
	
	
	public void testSerializeDeserialize2() throws Exception {
		
		ASerializerService serializerService = new AutoBeanSerializerService<IBeanFactory>( (IBeanFactory) GWT.create(ITestBean1ListFactory.class));
		serializerService.setProvider(getProvider());
		IObjectSerializer<IBean1List> bean1ListSerializer = serializerService.getObjectSerializer(IBean1List.class);
		
		Bean bean1 = new Bean();
		bean1.setHello("hello1");
		bean1.setMale(true);
		bean1.setYear(2011);
		
		Bean bean2 = new Bean();
		bean2.setHello("hello2");
		bean2.setMale(false);
		bean2.setYear(2012);
		
		IBean1List bean1List = new Bean1List();
		
		IObjectSerializer<IBean> bean1Serializer = serializerService.getObjectSerializer(IBean.class); 
		bean1Serializer.serialize(bean1);
		bean1Serializer.serialize(bean2);
		
		bean1List.getList().add(bean1);
		bean1List.getList().add(bean2);
		
		String bean1ListJson = bean1ListSerializer.serialize(bean1List);
		
		System.out.println("Serialized bean1List: ");
		System.out.println( bean1ListJson );
		
		IBean1List bean1ListDeserialized = bean1ListSerializer.deserialize(bean1ListJson);
		
		
		assertEquals( bean1List.getList().size() , bean1ListDeserialized.getList().size());
		assertEquals( bean1.getHello() , bean1ListDeserialized.getList().get(0).getHello() );
		assertEquals( bean1.getYear() , bean1ListDeserialized.getList().get(0).getYear());
		assertEquals( bean1.isMale() , bean1ListDeserialized.getList().get(0).isMale());
		assertEquals( bean2.getHello() , bean1ListDeserialized.getList().get(1).getHello() );
		assertEquals( bean2.getYear() , bean1ListDeserialized.getList().get(1).getYear());
		assertEquals( bean2.isMale() , bean1ListDeserialized.getList().get(1).isMale());
	}
	
//	public void testRestGwtSerializeDeserialize() throws Exception {
//		
//		IBean1ListCodec bean1ListSerializer = GWT.create(IBean1ListCodec.class);
//		
//		Bean1 bean1 = new Bean1();
//		bean1.setHello("hello1");
//		bean1.setMale(true);
//		bean1.setYear(2011);
//		
//		Bean1 bean2 = new Bean1();
//		bean1.setHello("hello2");
//		bean1.setMale(false);
//		bean1.setYear(2012);
//		
//		Bean1List bean1List = new Bean1List();
//		
//		bean1List.getList().add(bean1);
//		bean1List.getList().add(bean2);
//		
//		JSONValue bean1ListJsonValue = bean1ListSerializer.encode(bean1List);
//		String bean1ListJson = bean1ListSerializer.encode(bean1List).toString();
//		
//		System.out.println("Serialized bean1List: ");
//		System.out.println( bean1ListJson );
//		
//		IBean1List bean1ListDeserialized = bean1ListSerializer.decode(bean1ListJsonValue);
//		
//		assertEquals( bean1List.getList().size() , bean1ListDeserialized.getList().size());
//		assertEquals( bean1.getHello() , bean1ListDeserialized.getList().get(0).getHello() );
//		assertEquals( bean1.getYear() , bean1ListDeserialized.getList().get(0).getYear());
//		assertEquals( bean1.isMale() , bean1ListDeserialized.getList().get(0).isMale());
//		assertEquals( bean2.getHello() , bean1ListDeserialized.getList().get(1).getHello() );
//		assertEquals( bean2.getYear() , bean1ListDeserialized.getList().get(1).getYear());
//		assertEquals( bean2.isMale() , bean1ListDeserialized.getList().get(1).isMale());
//	}

//	public void testRestGwtSerializeDeserializeMapLongString() throws Exception {
//		
//		IMapLongStringCodec serializer = GWT.create(IMapLongStringCodec.class);
//		
//		HashMap<Long, String> map = new HashMap<Long, String>();
//		map.put(1l, "first value");
//		map.put(2l, "second value");
//		
//		JSONValue bean1ListJsonValue = serializer.encode(map);
//		
//		System.out.println("Serialized map: ");
//		System.out.println( serializer.encode(map).toString() );
//		
//		Map<Long, String> deserialized = serializer.decode(bean1ListJsonValue);
//		
//		assertEquals( map.size() , deserialized.size());
//		assertEquals( map.get(1l) , deserialized.get(1l) );
//		assertEquals( map.get(2l) , deserialized.get(2l) );
//	}
	
	public interface IBean1ListCodec extends JsonEncoderDecoder<Bean1List> {
	}

	public interface IBean1Codec extends JsonEncoderDecoder<IBean> {
	}
	
	public interface IMapLongStringCodec extends JsonEncoderDecoder<HashMap<Long, String>> {
	}
	
}
