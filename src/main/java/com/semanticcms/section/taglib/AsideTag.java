/*
 * semanticcms-section-taglib - Sections nested within SemanticCMS pages and elements in a JSP environment.
 * Copyright (C) 2019  AO Industries, Inc.
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

import com.semanticcms.core.model.ElementContext;
import com.semanticcms.section.model.Aside;
import com.semanticcms.section.renderer.html.SectionHtmlRenderer;
import java.io.IOException;
import java.io.Writer;
import javax.servlet.ServletException;
import javax.servlet.jsp.SkipPageException;

/**
 * <a href="https://www.w3.org/TR/html5/sections.html#the-aside-element">The aside element</a>
 */
public class AsideTag extends SectioningContentTag<Aside> {

	@Override
	protected Aside createElement() {
		return new Aside();
	}

	@Override
	public void writeTo(Writer out, ElementContext context) throws IOException, ServletException, SkipPageException {
		SectionHtmlRenderer.writeAside(out, context, getElement(), pageIndex);
	}
}
