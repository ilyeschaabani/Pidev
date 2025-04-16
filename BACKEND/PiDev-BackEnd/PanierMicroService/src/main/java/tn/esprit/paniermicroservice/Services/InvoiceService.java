package tn.esprit.paniermicroservice.Services;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.stripe.model.LineItem;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class InvoiceService {

    public ByteArrayOutputStream generateInvoiceWithItems(String email, List<LineItem> lineItems, long amountTotal, String currency) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            Document document = new Document();
            PdfWriter.getInstance(document, out);
            document.open();

            Font titleFont = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD);
            Font boldFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
            Font normalFont = new Font(Font.FontFamily.HELVETICA, 12);

            // Header
            Paragraph title = new Paragraph("🧾 Facture d'achat", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(Chunk.NEWLINE);

            document.add(new Paragraph("Client: " + email, normalFont));
            document.add(new Paragraph("Devise: " + currency.toUpperCase(), normalFont));
            document.add(Chunk.NEWLINE);

            // Table: Formation + Price
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{4, 1});

            PdfPCell header1 = new PdfPCell(new Phrase("Formation", boldFont));
            PdfPCell header2 = new PdfPCell(new Phrase("Prix (" + currency.toUpperCase() + ")", boldFont));
            header1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            header2.setBackgroundColor(BaseColor.LIGHT_GRAY);
            header1.setPadding(5);
            header2.setPadding(5);
            table.addCell(header1);
            table.addCell(header2);

            for (LineItem item : lineItems) {
                table.addCell(new Phrase(item.getDescription(), normalFont));
                double price = item.getAmountTotal() / 100.0;
                table.addCell(new Phrase(String.format("%.2f", price), normalFont));
            }

            // Total
            PdfPCell totalLabel = new PdfPCell(new Phrase("Total", boldFont));
            totalLabel.setColspan(1);
            totalLabel.setHorizontalAlignment(Element.ALIGN_RIGHT);
            totalLabel.setBackgroundColor(BaseColor.LIGHT_GRAY);
            totalLabel.setPadding(5);

            PdfPCell totalValue = new PdfPCell(new Phrase(String.format("%.2f", amountTotal / 100.0), boldFont));
            totalValue.setBackgroundColor(BaseColor.LIGHT_GRAY);
            totalValue.setPadding(5);
            totalValue.setHorizontalAlignment(Element.ALIGN_RIGHT);

            table.addCell(totalLabel);
            table.addCell(totalValue);

            document.add(table);
            document.close();

            return out;
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate invoice PDF", e);
        }
    }

}
