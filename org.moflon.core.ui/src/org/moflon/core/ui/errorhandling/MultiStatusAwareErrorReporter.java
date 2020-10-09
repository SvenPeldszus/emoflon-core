package org.moflon.core.ui.errorhandling;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.moflon.core.utilities.ErrorReporter;
import org.moflon.core.utilities.ExceptionUtil;
import org.moflon.core.utilities.LogUtils;
import org.moflon.core.utilities.WorkspaceHelper;

/**
 * This {@link ErrorReporter} traverses the hierarchy of a {@link MultiStatus}
 * and invokes {@link #reportLeafStatus(IStatus)} for all {@link Status} that
 * are no {@link MultiStatus}
 *
 * @author Roland Kluge - Initial implementation
 */
public class MultiStatusAwareErrorReporter implements ErrorReporter {
	private static final Logger logger = Logger.getLogger(MultiStatusAwareErrorReporter.class);

	private IFile file;

	/**
	 * Marks the {@link IFile} for which errors shall be reported
	 *
	 * @param file
	 */
	public MultiStatusAwareErrorReporter(final IFile file) {
		this.file = file;
	}

	/**
	 * Traverses the given {@link Status} and invokes
	 * {@link #reportLeafStatus(IStatus)} for each 'leaf status'.
	 */
	@Override
	public final void report(final IStatus status) {
		if (status != null && !status.isOK()) {
			if (status.isMultiStatus()) {
				for (final IStatus childStatus : status.getChildren()) {
					report(childStatus);
				}
			} else {
				try {
					reportLeafStatus(status);

				} catch (final CoreException e) {
					LogUtils.error(logger, e, "Problem while reporting eMoflon errors in Eclipse: "
							+ ExceptionUtil.displayExceptionAsString(e));
				}
			}
		}
	}

	/**
	 * Returns the file configured via the constructor
	 * 
	 * @return the file of this reporter
	 */
	public IFile getFile() {
		return file;
	}

	/**
	 * This method reports a leaf status (not a {@link MultiStatus}) by creating a
	 * marker at the configured file (#getFile())
	 * 
	 * @param status
	 *            the status to report
	 * @throws CoreException
	 *             if creating the marker fails
	 */
	protected void reportLeafStatus(final IStatus status) throws CoreException {
		final IFile file = getFile();
		final IResource markedResource = file.exists() ? file : file.getProject();
		final IMarker validationMarker = markedResource.createMarker(WorkspaceHelper.MOFLON_PROBLEM_MARKER_ID);
		validationMarker.setAttribute(IMarker.MESSAGE, status.getException()+ ": " +  status.getMessage());
		validationMarker.setAttribute(IMarker.PRIORITY, IMarker.PRIORITY_HIGH);
		validationMarker.setAttribute(IMarker.SEVERITY,
				convertStatusSeverityToEclipseMarkerSeverity(status.getSeverity()));
		LogUtils.error(logger, status.getMessage());
	}

	/**
	 * Converts the severity values from the world of {@link IStatus} to the world
	 * of {@link IMarker}.
	 * 
	 * @param value
	 *            the {@link IStatus} severity
	 * @return the {@link IMarker} severity
	 * @throws CoreException
	 *             if the value cannot be translated
	 */
	private static final int convertStatusSeverityToEclipseMarkerSeverity(final int value) throws CoreException {
		if (value >= IStatus.ERROR) {
			return IMarker.SEVERITY_ERROR;
		} else if (value >= IStatus.WARNING) {
			return IMarker.SEVERITY_WARNING;
		} else if (value >= IStatus.INFO) {
			return IMarker.SEVERITY_INFO;
		}
		final IStatus invalidSeverityConversion = new Status(IStatus.ERROR,
				WorkspaceHelper.getPluginId(MultiStatusAwareErrorReporter.class),
				"Cannot convert status severity value " + value + " to a marker");
		throw new CoreException(invalidSeverityConversion);
	}
}
