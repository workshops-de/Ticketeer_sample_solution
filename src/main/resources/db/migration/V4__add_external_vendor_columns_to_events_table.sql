ALTER TABLE event
    ADD external_vendor_managed BOOLEAN DEFAULT false;

ALTER TABLE event
    ADD external_vendor_category VARCHAR(255);

ALTER TABLE event
    ADD external_vendor_id UUID;
