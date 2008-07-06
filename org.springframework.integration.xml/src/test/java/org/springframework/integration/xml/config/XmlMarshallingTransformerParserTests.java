/*
 * Copyright 2002-2007 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.integration.xml.config;

import static org.junit.Assert.*;

import javax.xml.transform.dom.DOMResult;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.message.GenericMessage;
import org.springframework.integration.transformer.MessageTransformer;
import org.springframework.xml.transform.StringResult;
import org.w3c.dom.Document;

/**
 * 
 * @author Jonas Partner
 *
 */
public class XmlMarshallingTransformerParserTests  {
	
	ApplicationContext appContext;
	
	@Before
	public void setUp(){
		appContext = new ClassPathXmlApplicationContext("XmlMarshallingTransformerParserTests-context.xml", getClass());
	}
	
	@Test
	public void testDefault() throws Exception{
		MessageTransformer transformer = (MessageTransformer)appContext.getBean("marshallingTransfomerNoResultFactory");
		GenericMessage<Object> message = new GenericMessage<Object>("hello");
		transformer.transform(message);
		assertTrue("Wrong payload type ", message.getPayload() instanceof DOMResult);
		Document doc = (Document)((DOMResult)message.getPayload()).getNode();
		assertEquals("Wrong palyoad", "hello", doc.getDocumentElement().getTextContent());
	}
	
	@Test
	public void testDOMResult() throws Exception{
		MessageTransformer transformer = (MessageTransformer)appContext.getBean("marshallingTransfomerDOMResultFactory");
		GenericMessage<Object> message = new GenericMessage<Object>("hello");
		transformer.transform(message);
		assertTrue("Wrong payload type ", message.getPayload() instanceof DOMResult);
		Document doc = (Document)((DOMResult)message.getPayload()).getNode();
		assertEquals("Wrong palyoad", "hello", doc.getDocumentElement().getTextContent());
	}
	
	@Test
	public void testStringResult() throws Exception{
		MessageTransformer transformer = (MessageTransformer)appContext.getBean("marshallingTransfomerStringResultFactory");
		GenericMessage<Object> message = new GenericMessage<Object>("hello");
		transformer.transform(message);
		assertTrue("Wrong payload type ", message.getPayload() instanceof StringResult);
	}

}
