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

import java.io.IOException;

import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;

import org.springframework.oxm.Marshaller;
import org.springframework.oxm.XmlMappingException;
import org.springframework.xml.transform.StringSource;

public class StubMarshaller implements Marshaller {

	public void marshal(Object graph, Result result) throws XmlMappingException, IOException {
		try {
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			StringSource stringSource = new StringSource("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><root>" + graph.toString() + "</root>");
			transformer.transform(stringSource, result);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	@SuppressWarnings("unchecked")
	public boolean supports(Class clazz) {
		return true;
	}

}
