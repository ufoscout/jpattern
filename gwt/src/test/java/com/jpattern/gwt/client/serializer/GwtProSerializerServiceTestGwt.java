package com.jpattern.gwt.client.serializer;

import java.util.Map;

import com.jpattern.gwt.client.BaseGwtTestCase;

/**
 * 
 * @author Francesco Cina'
 *
 * 23/ago/2011
 */
public class GwtProSerializerServiceTestGwt extends BaseGwtTestCase {

	@Override
	protected void gwtTestCaseSetUp() throws Exception {
	}

	@Override
	protected void gwtTestCaseTearDown() throws Exception {
	}

	public void testSerializeDeserialize1() throws Exception {
		
//		ISerializerService serializerService = new GwtProJsonSerializerService();
//		IObjectSerializer<CommandMapIntegerStringResult> serializer = serializerService.getObjectSerializer(CommandMapIntegerStringResult.class);
//		
//		HashMap<Integer, String> map = new HashMap<Integer, String>();
//		map.put(1, "first value");
//		map.put(2, "second value");
//		
//
//		
//		CommandMapIntegerStringResult result = new CommandMapIntegerStringResult();
//		result.getErrorMessages().add(new ErrorMessage("error", "message"));
//		result.setReturnedObject(map);
//		
//		String serializedMap = serializer.serialize(result);
//		
//		System.out.println("Serialized map: ");
//		System.out.println( serializedMap );
//		
//		CommandMapIntegerStringResult deserialized = serializer.deserialize(serializedMap);
//		
//		assertEquals( map.size() , deserialized.getReturnedObject().size());
//		assertEquals( map.get(1) , deserialized.getReturnedObject().get(1) );
//		assertEquals( map.get(2) , deserialized.getReturnedObject().get(2) );
	}
	

	public class CommandMapIntegerStringResult extends ASerializableCommandFacadeResult<Map<Integer, String>> {

		private static final long serialVersionUID = 1L;
		private Map<Integer, String> returnedObject;
		
		@Override
		public Map<Integer, String> getReturnedObject() {
			return returnedObject;
		}

		public void setReturnedObject(Map<Integer, String> returnedObject) {
			this.returnedObject = returnedObject;
		}
		
	}
}
