package br.com.softplan.unj.process;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

class ScriptTagProcessor {

	private Pattern pattern;
	private String randomUUID;
	private File webFilesDirectory;
	private File webAppOutputDirectory;

	public ScriptTagProcessor(File webFilesDirectory, File webAppOutputDirectory) {
		this.pattern = Pattern.compile("(\\w*.js)\"");
		this.randomUUID = UUID.randomUUID().toString();
		this.webFilesDirectory = webFilesDirectory;
		this.webAppOutputDirectory = webAppOutputDirectory;
	}

	public void processScriptTags(String webFileRelativePath) throws IOException {
		String probablyHtmlFileText = FileUtils.readFileToString(new File(this.webFilesDirectory, webFileRelativePath));
		Matcher matcher = this.pattern.matcher(probablyHtmlFileText);
		while (matcher.find()) {
			probablyHtmlFileText = matcher.replaceAll("$1?n=" + this.randomUUID + "\"");
		}
		File file = new File(this.webAppOutputDirectory, webFileRelativePath);
		FileUtils.writeStringToFile(file, probablyHtmlFileText);
	}
}
