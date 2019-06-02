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

import static com.aoindustries.taglib.AttributeUtils.resolveValue;
import com.semanticcms.core.pages.CaptureLevel;
import com.semanticcms.core.renderer.html.PageIndex;
import com.semanticcms.core.taglib.ElementTag;
import com.semanticcms.section.model.SectioningContent;
import java.io.IOException;
import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;

/**
 * <a href="https://www.w3.org/TR/html5/dom.html#sectioning-content">Sectioning content</a>
 * is content that defines the scope of <a href="https://www.w3.org/TR/html5/sections.html#the-header-element">headings</a>
 * and <a href="https://www.w3.org/TR/html5/sections.html#the-footer-element">footers</a>.
 */
abstract public class SectioningContentTag<SC extends SectioningContent> extends ElementTag<SC> {

	private ValueExpression label;
	public void setLabel(ValueExpression label) {
		this.label = label;
	}

	@Override
	abstract protected SC createElement();

	@Override
	protected void evaluateAttributes(SC sectioningContent, ELContext elContext) throws JspTagException, IOException {
		super.evaluateAttributes(sectioningContent, elContext);
		sectioningContent.setLabel(resolveValue(label, String.class, elContext));
	}

	protected PageIndex pageIndex;
	@Override
	protected void doBody(SC sectioningContent, CaptureLevel captureLevel) throws JspException, IOException {
		PageContext pageContext = (PageContext)getJspContext();
		pageIndex = PageIndex.getCurrentPageIndex(pageContext.getRequest());
		super.doBody(sectioningContent ,captureLevel);
	}
}
