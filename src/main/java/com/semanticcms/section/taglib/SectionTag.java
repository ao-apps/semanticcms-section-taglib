/*
 * semanticcms-section-taglib - Sections nested within SemanticCMS pages and elements in a JSP environment.
 * Copyright (C) 2013, 2014, 2015, 2016, 2017, 2019, 2020  AO Industries, Inc.
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

import com.aoindustries.html.servlet.HtmlEE;
import com.semanticcms.core.model.ElementContext;
import com.semanticcms.section.model.Section;
import com.semanticcms.section.servlet.impl.SectionImpl;
import java.io.IOException;
import java.io.Writer;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.SkipPageException;

/**
 * <a href="https://www.w3.org/TR/html5/sections.html#the-section-element">The section element</a>
 */
public class SectionTag extends SectioningContentTag<Section> {

	@Override
	protected Section createElement() {
		return new Section();
	}

	@Override
	public void writeTo(Writer out, ElementContext context) throws IOException, ServletException, SkipPageException {
		PageContext pageContext = (PageContext)getJspContext();
		SectionImpl.writeSection(
			HtmlEE.get(pageContext.getServletContext(), (HttpServletRequest)pageContext.getRequest(), out),
			context,
			getElement(),
			pageIndex
		);
	}
}
