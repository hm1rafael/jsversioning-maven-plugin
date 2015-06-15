package com.github.jsversioning.process;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

class ScriptTagProcessor {

	private Pattern pattern;
	private String version;
	private File webFilesDirectory;
	private File webAppOutputDirectory;

	public ScriptTagProcessor(File webFilesDirectory, File webAppOutputDirectory, String version) {
		this.pattern = Pattern.compile("(\\w*.js)\"");
		this.webFilesDirectory = webFilesDirectory;
		this.webAppOutputDirectory = webAppOutputDirectory;
		if (version == null || "".equals(version.trim())) {
			this.version = UUID.randomUUID().toString();
		}
	}

	/**
	 * Iterate the files, finds the scripts tags and change the name of the script
	 * 
	 * @param webFileRelativePath
	 * @throws IOException
	 */
	public void processScriptTags(String webFileRelativePath) throws IOException {
		String probablyHtmlFileText = FileUtils.readFileToString(new File(this.webFilesDirectory, webFileRelativePath));
		Matcher matcher = this.pattern.matcher(probablyHtmlFileText);
		while (matcher.find()) {
			probablyHtmlFileText = matcher.replaceAll("$1?n=" + this.version + "\"");
		}
		File file = new File(this.webAppOutputDirectory, webFileRelativePath);
		FileUtils.writeStringToFile(file, probablyHtmlFileText);
	}
}
