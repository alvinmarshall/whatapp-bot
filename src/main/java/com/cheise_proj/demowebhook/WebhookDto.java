package com.cheise_proj.demowebhook;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

class WebhookDto {
    public record ChangesItem(

            @JsonProperty("field")
            String field,

            @JsonProperty("value")
            Value value
    ) {
    }

    public record ContactsItem(

            @JsonProperty("profile")
            Profile profile,

            @JsonProperty("wa_id")
            String waId
    ) {
    }

    public record Conversation(

            @JsonProperty("expiration_timestamp")
            String expirationTimestamp,

            @JsonProperty("origin")
            Origin origin,

            @JsonProperty("id")
            String id
    ) {
    }

    public record EntryItem(

            @JsonProperty("changes")
            List<ChangesItem> changes,

            @JsonProperty("id")
            String id
    ) {
    }

    public record MessagesItem(

            @JsonProperty("from")
            String from,

            @JsonProperty("id")
            String id,

            @JsonProperty("text")
            Text text,

            @JsonProperty("type")
            String type,

            @JsonProperty("timestamp")
            String timestamp
    ) {
    }

    public record Origin(

            @JsonProperty("type")
            String type
    ) {
    }

    public record Pricing(

            @JsonProperty("pricing_model")
            String pricingModel,

            @JsonProperty("category")
            String category,

            @JsonProperty("billable")
            boolean billable
    ) {
    }

    public record Profile(

            @JsonProperty("name")
            String name
    ) {
    }

    public record Response(

            @JsonProperty("entry")
            List<EntryItem> entry,

            @JsonProperty("object")
            String object
    ) {
    }

    public record StatusesItem(

            @JsonProperty("id")
            String id,

            @JsonProperty("conversation")
            Conversation conversation,

            @JsonProperty("pricing")
            Pricing pricing,

            @JsonProperty("status")
            String status,

            @JsonProperty("timestamp")
            String timestamp,

            @JsonProperty("recipient_id")
            String recipientId
    ) {
    }

    public record Text(

            @JsonProperty("body")
            String body
    ) {
    }

    public record Value(
            @JsonProperty("metadata")
            Metadata metadata,

            @JsonProperty("messaging_product")
            String messagingProduct,

            @JsonProperty("messages")
            List<MessagesItem> messages,

            @JsonProperty("contacts")
            List<ContactsItem> contacts,

            @JsonProperty("statuses")
            List<StatusesItem> statuses
    ) {
    }

    public record Metadata(

            @JsonProperty("phone_number_id")
            String phoneNumberId,

            @JsonProperty("display_phone_number")
            String displayPhoneNumber
    ) {
    }


}
