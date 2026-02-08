/*
 * semanticcms-section-taglib - Sections nested within SemanticCMS pages and elements in a JSP environment.
 * Copyright (C) 2021, 2022, 2023, 2025, 2026  AO Industries, Inc.
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
  requires com.aoapps.html.any; // <groupId>com.aoapps</groupId><artifactId>ao-fluent-html-any</artifactId>
  requires com.aoapps.html.servlet; // <groupId>com.aoapps</groupId><artifactId>ao-fluent-html-servlet</artifactId>
  requires com.aoapps.servlet.util; // <groupId>com.aoapps</groupId><artifactId>ao-servlet-util</artifactId>
  requires jakarta.el; // <groupId>jakarta.el</groupId><artifactId>jakarta.el-api</artifactId>
  requires jakarta.servlet; // <groupId>jakarta.servlet</groupId><artifactId>jakarta.servlet-api</artifactId>
  requires jakarta.servlet.jsp; // <groupId>jakarta.servlet.jsp</groupId><artifactId>jakarta.servlet.jsp-api</artifactId>
  requires com.semanticcms.core.model; // <groupId>com.semanticcms</groupId><artifactId>semanticcms-core-model</artifactId>
  requires com.semanticcms.core.pages; // <groupId>com.semanticcms</groupId><artifactId>semanticcms-core-pages</artifactId>
  requires com.semanticcms.core.renderer.html; // <groupId>com.semanticcms</groupId><artifactId>semanticcms-core-renderer-html</artifactId>
  requires com.semanticcms.core.taglib; // <groupId>com.semanticcms</groupId><artifactId>semanticcms-core-taglib</artifactId>
  requires com.semanticcms.section.model; // <groupId>com.semanticcms</groupId><artifactId>semanticcms-section-model</artifactId>
  requires com.semanticcms.section.renderer.html; // <groupId>com.semanticcms</groupId><artifactId>semanticcms-section-renderer-html</artifactId>
}
