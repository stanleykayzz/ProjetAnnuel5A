package server.service.impl;

import static org.junit.Assert.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;



import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.template.TemplateEngineKind;

/**
 * Created by ileossa on 10/07/2017.
 */
public class TotoTest {

    @Test
    public void testMergeAndGenerateOutput() throws IOException, XDocReportException {

        String templatePath = "C:\\Users\\ileossa\\Downloads\\ThankYouNote_Template.docx";

        Map<String, Object> nonImageVariableMap = new HashMap<String, Object>();
        nonImageVariableMap.put("thank_you_date", "24-June-2013");
        nonImageVariableMap.put("name", "Rajani Jha");
        nonImageVariableMap.put("website", "www.sambhashanam.com");
        nonImageVariableMap.put("author_name", "Dhananjay Jha");
        Map<String, String> imageVariablesWithPathMap =new HashMap<String, String>();
        imageVariablesWithPathMap.put("header_image_logo", "C:\\Users\\ileossa\\Downloads\\unnamed.jpg");

        toto docxDocumentMergerAndConverter = new toto();
        byte[] mergedOutput = docxDocumentMergerAndConverter.mergeAndGenerateOutput(templatePath, TemplateEngineKind.Freemarker, nonImageVariableMap, imageVariablesWithPathMap);
        assertNotNull(mergedOutput);
        FileOutputStream os = new FileOutputStream("C:\\Users\\ileossa\\Downloads\\ThankYouNote_Template"+System.nanoTime()+".docx");
        os.write(mergedOutput);
        os.flush();
        os.close();

    }
}
