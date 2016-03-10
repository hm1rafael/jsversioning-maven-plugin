package com.github.hm1rafael.jsversioning.process;

import java.io.File;
import java.io.IOException;

public class ProcessWebFiles {

	private File webFilesDirectory;
	private File webAppOutputDirectory;
	private String version;

	public ProcessWebFiles(File webFilesDirectory, File webAppOutputDirectory, String version) {
		this.webFilesDirectory = webFilesDirectory;
		this.webAppOutputDirectory = webAppOutputDirectory;
		this.version = version;
	}

	/**
	 * Just iterates the files and send to the {@code ScriptTagProcessor} to do the heavy work
	 * TODO: Add logic to ignore directories, files and specific extensions
	 * 
	 * @param webFilesPaths
	 * @throws IOException
	 */
	public void process(String[] webFilesPaths) throws IOException {
		ScriptTagProcessor scriptTagProcessor = new ScriptTagProcessor(this.webFilesDirectory, this.webAppOutputDirectory, this.version);
		for (String webFilePath : webFilesPaths) {
			scriptTagProcessor.processScriptTags(webFilePath);
		}
	}
}
