/*
 * semanticcms-section-taglib - Sections nested within SemanticCMS pages and elements in a JSP environment.
 * Copyright (C) 2019, 2020, 2021, 2022, 2023  AO Industries, Inc.
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

import com.aoapps.encoding.Doctype;
import com.aoapps.encoding.Serialization;
import com.aoapps.encoding.servlet.DoctypeEE;
import com.aoapps.encoding.servlet.SerializationEE;
import com.aoapps.html.Document;
import com.aoapps.html.any.AnyPalpableContent;
import com.semanticcms.core.model.ElementContext;
import com.semanticcms.core.servlet.CaptureLevel;
import com.semanticcms.core.servlet.PageIndex;
import com.semanticcms.core.taglib.ElementTag;
import com.semanticcms.section.model.SectioningContent;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.Charset;
import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.SkipPageException;

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
  protected void evaluateAttributes(C sectioningContent, ELContext elContext) throws JspTagException, IOException {
    super.evaluateAttributes(sectioningContent, elContext);
    sectioningContent.setLabel(resolveValue(label, String.class, elContext));
  }

  private HttpServletRequest request;
  private PageIndex pageIndex;
  private Serialization serialization;
  private Doctype doctype;
  private Charset characterEncoding;

  @Override
  protected void doBody(C sectioningContent, CaptureLevel captureLevel) throws JspException, IOException {
    PageContext pageContext = (PageContext) getJspContext();
    ServletContext servletContext = pageContext.getServletContext();
    request = (HttpServletRequest) pageContext.getRequest();
    pageIndex = PageIndex.getCurrentPageIndex(request);
    serialization = SerializationEE.get(servletContext, request);
    doctype = DoctypeEE.get(servletContext, request);
    characterEncoding = Charset.forName(pageContext.getResponse().getCharacterEncoding());
    super.doBody(sectioningContent, captureLevel);
  }

  /**
   * {@inheritDoc}
   *
   * @deprecated  You should probably be implementing in
   *              {@link #writeTo(javax.servlet.ServletRequest, com.aoapps.html.any.AnyPalpableContent, com.semanticcms.core.model.ElementContext, com.semanticcms.core.servlet.PageIndex)}
   *
   * @see  #writeTo(javax.servlet.ServletRequest, com.aoapps.html.any.AnyPalpableContent, com.semanticcms.core.model.ElementContext, com.semanticcms.core.servlet.PageIndex)
   */
  @Deprecated(forRemoval = false)
  @Override
  public void writeTo(Writer out, ElementContext context) throws IOException, ServletException, SkipPageException {
    writeTo(
        request,
        new Document(serialization, doctype, characterEncoding, out)
            .setAutonli(false)// Do not add extra newlines to JSP
            .setIndent(false), // Do not add extra indentation to JSP
        context,
        pageIndex
    );
  }

  protected abstract void writeTo(ServletRequest request, AnyPalpableContent<?, ?> content, ElementContext context, PageIndex pageIndex) throws IOException, ServletException, SkipPageException;
}
