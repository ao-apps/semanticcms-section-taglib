/*
 * semanticcms-section-taglib - Sections nested within SemanticCMS pages and elements in a JSP environment.
 * Copyright (C) 2021, 2022, 2023  AO Industries, Inc.
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
module com.semanticcms.section.taglib {
  exports com.semanticcms.section.taglib;
  // Direct
  requires com.aoapps.encoding; // <groupId>com.aoapps</groupId><artifactId>ao-encoding</artifactId>
  requires com.aoapps.encoding.servlet; // <groupId>com.aoapps</groupId><artifactId>ao-encoding-servlet</artifactId>
  requires com.aoapps.html; // <groupId>com.aoapps</groupId><artifactId>ao-fluent-html</artifactId>
  requires com.aoapps.html.any; // <groupId>com.aoapps</groupId><artifactId>ao-fluent-html-any</artifactId>
  requires com.aoapps.servlet.util; // <groupId>com.aoapps</groupId><artifactId>ao-servlet-util</artifactId>
  requires javax.el.api; // <groupId>javax.el</groupId><artifactId>javax.el-api</artifactId>
  requires javax.servlet.api; // <groupId>javax.servlet</groupId><artifactId>javax.servlet-api</artifactId>
  requires javax.servlet.jsp.api; // <groupId>javax.servlet.jsp</groupId><artifactId>javax.servlet.jsp-api</artifactId>
  requires com.semanticcms.core.model; // <groupId>com.semanticcms</groupId><artifactId>semanticcms-core-model</artifactId>
  requires com.semanticcms.core.servlet; // <groupId>com.semanticcms</groupId><artifactId>semanticcms-core-servlet</artifactId>
  requires com.semanticcms.core.taglib; // <groupId>com.semanticcms</groupId><artifactId>semanticcms-core-taglib</artifactId>
  requires com.semanticcms.section.model; // <groupId>com.semanticcms</groupId><artifactId>semanticcms-section-model</artifactId>
  requires com.semanticcms.section.servlet; // <groupId>com.semanticcms</groupId><artifactId>semanticcms-section-servlet</artifactId>
}
