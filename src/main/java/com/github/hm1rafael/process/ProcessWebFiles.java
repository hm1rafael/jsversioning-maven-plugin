package com.github.hm1rafael.process;

import java.io.File;
import java.io.IOException;

public class ProcessWebFiles {

	private File webFilesDirectory;
	private File webAppOutputDirectory;

	public ProcessWebFiles(File webFilesDirectory, File webAppOutputDirectory) {
		this.webFilesDirectory = webFilesDirectory;
		this.webAppOutputDirectory = webAppOutputDirectory;
	}

	public void process(String[] webFilesPaths) throws IOException {
		ScriptTagProcessor scriptTagProcessor = new ScriptTagProcessor(this.webFilesDirectory, this.webAppOutputDirectory);
		for (String webFilePath : webFilesPaths) {
			scriptTagProcessor.processScriptTags(webFilePath);
		}
	}
}
