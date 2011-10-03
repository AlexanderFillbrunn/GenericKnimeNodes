/*
 * Copyright (c) 2011, Marc Röttig.
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

package org.ballproject.knime.base.treetabledialog.itemlist;

import org.ballproject.knime.base.parameter.DoubleListParameter;
import org.ballproject.knime.base.parameter.IntegerListParameter;
import org.ballproject.knime.base.parameter.ListParameter;
import org.ballproject.knime.base.parameter.Parameter;


public class ListParameterModel extends ItemListFillerDialogModel
{
	private Parameter<?> param;
	
	public ListParameterModel(Parameter<?> param)
	{
		super();
		this.param = param;
		init();
	}

	private void init()
	{
		if(param instanceof ListParameter)
		{
			ListParameter lp = (ListParameter) param;
			this.data = lp.getStrings();
			if(param instanceof DoubleListParameter)
			{
				DoubleListParameter dlp = (DoubleListParameter) param;	
				DoubleValidator val = new DoubleValidator(); 
				val.setLowerBound(dlp.getLowerBound());
				val.setUpperBound(dlp.getUpperBound());
				this.setValidator(val);
			}
			if(param instanceof IntegerListParameter)
			{
				IntegerListParameter ilp = (IntegerListParameter) param;	
				IntegerValidator val = new IntegerValidator(); 
				val.setLowerBound(ilp.getLowerBound());
				val.setUpperBound(ilp.getUpperBound());
				this.setValidator(val);
			}
		}
		else
		{
			throw new RuntimeException();
		}
	}
}
