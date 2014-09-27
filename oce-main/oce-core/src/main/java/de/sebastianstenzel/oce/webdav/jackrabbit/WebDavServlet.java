/*******************************************************************************
 * Copyright (c) 2014 Sebastian Stenzel
 * This file is licensed under the terms of the MIT license.
 * See the LICENSE.txt file for more info.
 * 
 * Contributors:
 *     Sebastian Stenzel - initial API and implementation
 ******************************************************************************/
package de.sebastianstenzel.oce.webdav.jackrabbit;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.apache.jackrabbit.webdav.DavLocatorFactory;
import org.apache.jackrabbit.webdav.DavResource;
import org.apache.jackrabbit.webdav.DavResourceFactory;
import org.apache.jackrabbit.webdav.DavSessionProvider;
import org.apache.jackrabbit.webdav.WebdavRequest;
import org.apache.jackrabbit.webdav.server.AbstractWebdavServlet;

import de.sebastianstenzel.oce.crypto.Cryptor;

public class WebDavServlet extends AbstractWebdavServlet {

	private static final long serialVersionUID = 7965170007048673022L;
	public static final String CFG_FS_ROOT = "oce.fs.root";
	public static final String CFG_HTTP_ROOT = "oce.http.root";
	private DavSessionProvider davSessionProvider;
	private DavLocatorFactory davLocatorFactory;
	private DavResourceFactory davResourceFactory;
	private final Cryptor cryptor;

	public WebDavServlet(final Cryptor cryptor) {
		super();
		this.cryptor = cryptor;
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		davSessionProvider = new WebDavSessionProvider();

		final String fsRoot = config.getInitParameter(CFG_FS_ROOT);
		final String httpRoot = config.getInitParameter(CFG_HTTP_ROOT);
		this.davLocatorFactory = new WebDavLocatorFactory(fsRoot, httpRoot, cryptor);

		this.davResourceFactory = new WebDavResourceFactory(cryptor);
	}

	@Override
	protected boolean isPreconditionValid(WebdavRequest request, DavResource resource) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public DavSessionProvider getDavSessionProvider() {
		return davSessionProvider;
	}

	@Override
	public void setDavSessionProvider(DavSessionProvider davSessionProvider) {
		this.davSessionProvider = davSessionProvider;
	}

	@Override
	public DavLocatorFactory getLocatorFactory() {
		return davLocatorFactory;
	}

	@Override
	public void setLocatorFactory(DavLocatorFactory locatorFactory) {
		this.davLocatorFactory = locatorFactory;
	}

	@Override
	public DavResourceFactory getResourceFactory() {
		return davResourceFactory;
	}

	@Override
	public void setResourceFactory(DavResourceFactory resourceFactory) {
		this.davResourceFactory = resourceFactory;
	}

}
