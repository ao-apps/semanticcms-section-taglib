/*
 * semanticcms-section-taglib - Sections nested within SemanticCMS pages and elements in a JSP environment.
 * Copyright (C) 2019, 2020, 2021, 2022, 2023, 2024, 2025, 2026  AO Industries, Inc.
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
 * along with semanticcms-section-taglib.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.semanticcms.section.taglib;

import static com.aoapps.servlet.el.ElUtils.resolveValue;

import com.aoapps.html.any.AnyPalpableContent;
import com.aoapps.html.servlet.DocumentEE;
import com.semanticcms.core.model.ElementContext;
import com.semanticcms.core.servlet.CaptureLevel;
import com.semanticcms.core.servlet.PageIndex;
import com.semanticcms.core.taglib.ElementTag;
import com.semanticcms.section.model.SectioningContent;
import jakarta.el.ELContext;
import jakarta.el.ValueExpression;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspTagException;
import jakarta.servlet.jsp.PageContext;
import jakarta.servlet.jsp.SkipPageException;
import java.io.IOException;
import java.io.Writer;

/**
 * <a href="https://www.w3.org/TR/html5/dom.html#sectioning-content">Sectioning content</a>
 * is content that defines the scope of <a href="https://www.w3.org/TR/html5/sections.html#the-header-element">headings</a>
 * and <a href="https://www.w3.org/TR/html5/sections.html#the-footer-element">footers</a>.
 */
public abstract class SectioningContentTag<C extends SectioningContent> extends ElementTag<C> {

  private ValueExpression label;

  public void setLabel(ValueExpression label) {
    this.label = label;
  }

  @Override
  protected abstract C createElement();

  @Override
  protected void evaluateAttributes(C sectioningContent, ELContext elContext) throws JspTagException {
    super.evaluateAttributes(sectioningContent, elContext);
    sectioningContent.setLabel(resolveValue(label, String.class, elContext));
  }

  private ServletContext servletContext;
  private HttpServletRequest request;
  private HttpServletResponse response;
  private PageIndex pageIndex;

  @Override
  protected void doBody(C sectioningContent, CaptureLevel captureLevel) throws JspException, IOException {
    PageContext pageContext = (PageContext) getJspContext();
    servletContext = pageContext.getServletContext();
    request = (HttpServletRequest) pageContext.getRequest();
    response = (HttpServletResponse) pageContext.getResponse();
    pageIndex = PageIndex.getCurrentPageIndex(request);
    super.doBody(sectioningContent, captureLevel);
  }

  /**
   * {@inheritDoc}
   *
   * @deprecated  You should probably be implementing in
   *              {@link SectioningContentTag#writeTo(jakarta.servlet.ServletRequest, com.aoapps.html.any.AnyPalpableContent, com.semanticcms.core.model.ElementContext, com.semanticcms.core.servlet.PageIndex)}
   *
   * @see  SectioningContentTag#writeTo(jakarta.servlet.ServletRequest, com.aoapps.html.any.AnyPalpableContent, com.semanticcms.core.model.ElementContext, com.semanticcms.core.servlet.PageIndex)
   */
  @Deprecated(forRemoval = false)
  @Override
  public void writeTo(Writer out, ElementContext context) throws IOException, ServletException, SkipPageException {
    writeTo(
        request,
        new DocumentEE(servletContext, request, response, out,
            false, // Do not add extra newlines to JSP
            false  // Do not add extra indentation to JSP
        ),
        context,
        pageIndex
    );
  }

  /**
   * Writes out the content.
   */
  protected abstract void writeTo(ServletRequest request, AnyPalpableContent<?, ?> content, ElementContext context, PageIndex pageIndex) throws IOException, ServletException, SkipPageException;
}
