package com.lottery.project.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;


@Service
public class ExportPdfServiceImpl implements ExportPdfService {
    private Logger logger = LoggerFactory.getLogger(ExportPdfServiceImpl.class);

    @Autowired
    private TemplateEngine templateEngine;

    public ByteArrayInputStream exportReceiptPdf(String templateName, Map<String, Object> data) throws IOException {
        Context context = new Context();
        context.setVariables(data);
        String htmlContent = templateEngine.process(templateName, context);

        ByteArrayInputStream byteArrayInputStream = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ITextRenderer renderer = new ITextRenderer();
            renderer.getFontResolver().addFont("fonts/tahoma.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            renderer.setDocumentFromString(htmlContent);
            renderer.layout();
            renderer.createPDF(byteArrayOutputStream, false);
            renderer.finishPDF();
            byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        } catch (DocumentException e) {
            logger.error(e.getMessage(), e);
        }

        return byteArrayInputStream;
    }
}