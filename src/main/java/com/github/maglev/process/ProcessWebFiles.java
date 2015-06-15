package com.github.maglev.process;

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

	public void process(String[] webFilesPaths) throws IOException {
		ScriptTagProcessor scriptTagProcessor = new ScriptTagProcessor(this.webFilesDirectory, this.webAppOutputDirectory, this.version);
		for (String webFilePath : webFilesPaths) {
			scriptTagProcessor.processScriptTags(webFilePath);
		}
	}
}
