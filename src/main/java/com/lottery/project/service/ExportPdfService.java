package com.lottery.project.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;

public interface ExportPdfService {
    ByteArrayInputStream exportReceiptPdf(String templateName, Map<String, Object> data) throws IOException;

}
