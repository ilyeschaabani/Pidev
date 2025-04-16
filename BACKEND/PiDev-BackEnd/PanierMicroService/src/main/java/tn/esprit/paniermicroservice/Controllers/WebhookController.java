package tn.esprit.paniermicroservice.Controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.model.LineItem;
import com.stripe.model.checkout.Session;
import com.stripe.model.checkout.Session;
import com.stripe.model.StripeCollection;


import com.stripe.net.Webhook;
import com.stripe.param.checkout.SessionListLineItemsParams;
import com.stripe.param.checkout.SessionRetrieveParams;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.paniermicroservice.Services.EmailService;
import tn.esprit.paniermicroservice.Services.InvoiceService;


import java.io.ByteArrayOutputStream;
import java.util.List;

@RestController
@RequestMapping("/webhook")
@CrossOrigin(origins = "*")
public class WebhookController {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private EmailService emailService;

    @Value("${stripe.webhook.secret}")
    private String endpointSecret;

    @PostMapping
    public ResponseEntity<String> handleWebhook(
            @RequestBody String payload,
            @RequestHeader("Stripe-Signature") String sigHeader) {

        Event event;

        try {
            event = Webhook.constructEvent(payload, sigHeader, endpointSecret);
        } catch (SignatureVerificationException e) {
            System.out.println("\u274C Signature error: " + e.getMessage());
            return ResponseEntity.badRequest().body("Invalid signature");
        }

        if ("checkout.session.completed".equals(event.getType())) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode root = objectMapper.readTree(payload);
                String sessionId = root.get("data").get("object").get("id").asText();

                // 1. Retrieve session
                Session session = Session.retrieve(sessionId);

                // 2. Get line items
                StripeCollection<LineItem> lineItemsCollection = session.listLineItems();
                List<LineItem> lineItems = lineItemsCollection.getData();

                // 3. Extract other details
                String email = session.getCustomerDetails().getEmail();
                long amountTotal = session.getAmountTotal();
                String currency = session.getCurrency();

                System.out.println("📧 Email: " + email);
                System.out.println("📦 Items:");
                for (LineItem item : lineItems) {
                    System.out.println("- " + item.getDescription() + ": " + (item.getAmountTotal() / 100.0) + " " + currency.toUpperCase());
                }

                // 4. Generate and send invoice
                ByteArrayOutputStream invoicePdf = invoiceService.generateInvoiceWithItems(email, lineItems, amountTotal, currency);
                emailService.sendInvoiceEmail(email, invoicePdf);

            } catch (Exception e) {
                System.out.println("❌ Failed to process session: " + e.getMessage());
                e.printStackTrace();
            }

        }

        return ResponseEntity.ok("\u2705 Webhook received");
    }
}
