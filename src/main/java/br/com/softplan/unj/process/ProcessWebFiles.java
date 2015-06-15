package br.com.softplan.unj.process;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

public class ProcessWebFiles {

	private File webFilesDirectory;
	private File webAppOutputDirectory;

	public ProcessWebFiles(File webFilesDirectory, File webAppOutputDirectory, Collection<File> directoriesToIgnore) {
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
