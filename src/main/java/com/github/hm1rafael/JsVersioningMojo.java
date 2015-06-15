package com.github.hm1rafael;

import java.io.File;
import java.io.IOException;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.codehaus.plexus.util.DirectoryScanner;

import com.github.hm1rafael.process.ProcessWebFiles;

@Mojo(
	name = "versioning",
	defaultPhase = LifecyclePhase.PROCESS_RESOURCES,
	threadSafe = true)
public class JsVersioningMojo extends AbstractMojo {

	@Parameter(defaultValue = "${basedir}/src/main/webapp", required = true)
	private File webFilesDirectory;

	@Parameter(defaultValue = "${project.build.directory}/temp", required = true)
	private File webappOutputDirectory;

	@Parameter(required = false)
	private String version;

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		if (!this.webappOutputDirectory.exists()) {
			this.webappOutputDirectory.mkdirs();
		}
		String[] webFiles = getWebFiles();
		ProcessWebFiles processWebFiles = new ProcessWebFiles(this.webFilesDirectory, this.webappOutputDirectory, this.version);
		processFiles(webFiles, processWebFiles);
	}

	private void processFiles(String[] webFiles, ProcessWebFiles processWebFiles) {
		try {
			processWebFiles.process(webFiles);
		} catch (IOException e) {
			getLog().debug(e);
			throw new IllegalStateException(e);
		}
	}

	private String[] getWebFiles() {
		DirectoryScanner ds = new DirectoryScanner();
		ds.setBasedir(this.webFilesDirectory);
		ds.scan();
		return ds.getIncludedFiles();
	}
}
