/*
 * semanticcms-section-taglib - Sections nested within SemanticCMS pages and elements in a JSP environment.
 * Copyright (C) 2013, 2014, 2015, 2016  AO Industries, Inc.
 *     support@aoindustries.com
 *     7262 Bull Pen Cir
 *     Mobile, AL 36695
 *
 * This file is part of semanticcms-section-taglib.
 *
 * semanticcms-section-taglib is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * semanticcms-section-taglib is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with semanticcms-section-taglib.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.semanticcms.section.taglib;

import static com.aoindustries.taglib.AttributeUtils.resolveValue;
import com.semanticcms.core.model.ElementContext;
import com.semanticcms.core.servlet.CaptureLevel;
import com.semanticcms.core.servlet.PageIndex;
import com.semanticcms.core.taglib.ElementTag;
import com.semanticcms.section.model.Section;
import com.semanticcms.section.servlet.impl.SectionImpl;
import java.io.IOException;
import java.io.Writer;
import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.servlet.ServletException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.SkipPageException;

public class SectionTag extends ElementTag<Section> {

	private ValueExpression label;
	public void setLabel(ValueExpression label) {
		this.label = label;
	}

	@Override
	protected Section createElement() {
		return new Section();
	}

	@Override
	protected void evaluateAttributes(Section section, ELContext elContext) throws JspTagException, IOException {
		super.evaluateAttributes(section, elContext);
		section.setLabel(resolveValue(label, String.class, elContext));
	}

	private PageIndex pageIndex;
	@Override
	protected void doBody(Section section, CaptureLevel captureLevel) throws JspException, IOException {
		PageContext pageContext = (PageContext)getJspContext();
		pageIndex = PageIndex.getCurrentPageIndex(pageContext.getRequest());
		super.doBody(section ,captureLevel);
	}

	@Override
	public void writeTo(Writer out, ElementContext context) throws IOException, ServletException, SkipPageException {
		SectionImpl.writeSection(out, context, getElement(), pageIndex);
	}
}
