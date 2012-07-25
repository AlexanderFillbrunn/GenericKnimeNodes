/**
 * Copyright (c) 2012, Stephan Aiche.
 *
 * This file is part of GenericKnimeNodes.
 * 
 * GenericKnimeNodes is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.07.23 at 06:13:20 PM CEST 
//

package com.genericworkflownodes.knime.outputconverter.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Defines a list of conversion rules.
 * 
 * <p>
 * Java class for outputConvertersType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="outputConvertersType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="converter" type="{}converterType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
public class OutputConverters {

	/**
	 * The list of output converters.
	 */
	private Map<String, List<Converter>> converters;

	/**
	 * Default c'tor.
	 */
	public OutputConverters() {
		converters = new TreeMap<String, List<Converter>>();
	}

	/**
	 * Gets the value of the converters property.
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link Converter }
	 * 
	 * @return The list of output converters.
	 * 
	 */
	public Collection<Converter> getConverters() {
		List<Converter> availableConverter = new ArrayList<Converter>();
		for (List<Converter> subList : converters.values()) {
			availableConverter.addAll(subList);
		}
		return availableConverter;
	}

	/**
	 * Adds a new {@link Converter} to the list of {@link OutputConverters}.
	 * 
	 * @param converter
	 *            The new converter.
	 */
	public void addConverter(final Converter converter) {
		if (!converters.containsKey(converter.getRef())) {
			converters.put(converter.getRef(), new ArrayList<Converter>());
		}
		converters.get(converter.getRef()).add(converter);
	}

	/**
	 * Gets all {@link Converter}s that should be applied to the given port. If
	 * no converter is available <code>null</code> will be returned.
	 * 
	 * @param ref
	 *            The id of the output port.
	 * @return A list of {@link Converter}s that can be applied to the specified
	 *         port, <code>null</code> if no {@link Converter} is available.
	 */
	public Collection<Converter> findConverter(final String ref) {
		List<Converter> foundConverters = null;

		if (converters.containsKey(ref)) {
			foundConverters = new ArrayList<Converter>();
			foundConverters.addAll(converters.get(ref));
		}

		return foundConverters;
	}
}
