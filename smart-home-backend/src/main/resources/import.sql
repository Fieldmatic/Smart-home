insert into PUBLIC.ROLE (ID,CREATED_AT, NAME) values ('9c4cf245-67c7-466e-9ff2-9878acef91cb',CURRENT_TIMESTAMP, 'ADMIN');
insert into PUBLIC.ROLE (ID,CREATED_AT, NAME) values ('faf8829f-cb60-4365-b419-14f7b80afa4c',CURRENT_TIMESTAMP, 'OWNER');
insert into PUBLIC.ROLE (ID,CREATED_AT, NAME) values ('ebc16e4e-64e1-4b35-946a-0885ab058453',CURRENT_TIMESTAMP, 'TENANT');
insert into ROLE_PERMISSIONS (ROLE_ID, PERMISSIONS) values ('9c4cf245-67c7-466e-9ff2-9878acef91cb', 'SEND_CSR_REQUEST');
insert into ROLE_PERMISSIONS (ROLE_ID, PERMISSIONS) values ('9c4cf245-67c7-466e-9ff2-9878acef91cb', 'READ_ADMIN_STATISTICS');
insert into ROLE_PERMISSIONS (ROLE_ID, PERMISSIONS) values ('9c4cf245-67c7-466e-9ff2-9878acef91cb', 'CSR_MANIPULATION');
insert into ROLE_PERMISSIONS (ROLE_ID, PERMISSIONS) values ('9c4cf245-67c7-466e-9ff2-9878acef91cb', 'USER_MANIPULATION');
insert into ROLE_PERMISSIONS (ROLE_ID, PERMISSIONS) values ('9c4cf245-67c7-466e-9ff2-9878acef91cb', 'CERTIFICATE_MANIPULATION');
insert into ROLE_PERMISSIONS (ROLE_ID, PERMISSIONS) values ('9c4cf245-67c7-466e-9ff2-9878acef91cb', 'PROPERTY_MANIPULATION');
insert into ROLE_PERMISSIONS (ROLE_ID, PERMISSIONS) values ('faf8829f-cb60-4365-b419-14f7b80afa4c', 'SEND_CSR_REQUEST');
insert into ROLE_PERMISSIONS (ROLE_ID, PERMISSIONS) values ('ebc16e4e-64e1-4b35-946a-0885ab058453', 'SEND_CSR_REQUEST');
insert into PUBLIC.ADMIN (ID, CREATED_AT, EMAIL, PASSWORD_HASH, ROLE_ID, VERIFIED) values ('201ab15f-3b22-4659-b839-235b9e6a728e', CURRENT_TIMESTAMP, 'milicftn@gmail.com', '$2a$10$5tykbOhp3Uo2QrY2t3/uCOmsuwvEZl4KgEgZgN3At6JB3HQX.Z75y', '9c4cf245-67c7-466e-9ff2-9878acef91cb', true);
insert into PUBLIC.ADMIN (ID, CREATED_AT, EMAIL, PASSWORD_HASH, ROLE_ID, VERIFIED) values ('0d6df743-a243-4e80-86a0-ef5710a57e4c', CURRENT_TIMESTAMP, 'istevanovic3112@gmail.com', '$2a$10$5tykbOhp3Uo2QrY2t3/uCOmsuwvEZl4KgEgZgN3At6JB3HQX.Z75y', '9c4cf245-67c7-466e-9ff2-9878acef91cb', true);
insert into PUBLIC.ADMIN (ID, CREATED_AT, EMAIL, PASSWORD_HASH, ROLE_ID, VERIFIED) values ('83aebc30-729c-4923-8aa1-ef71bb1e5247', CURRENT_TIMESTAMP, 'prijovic.uros13@gmail.com', '$2a$10$5tykbOhp3Uo2QrY2t3/uCOmsuwvEZl4KgEgZgN3At6JB3HQX.Z75y', '9c4cf245-67c7-466e-9ff2-9878acef91cb', true);
insert into PERSON (ID, CREATED_AT, EMAIL, PASSWORD_HASH, ROLE_ID, VERIFIED) values ('e0e389ee-ac19-4ad7-97a9-4c956935bd15', CURRENT_TIMESTAMP, 'prijovic.sw79.2019@uns.ac.rs', '$2a$10$5tykbOhp3Uo2QrY2t3/uCOmsuwvEZl4KgEgZgN3At6JB3HQX.Z75y', 'faf8829f-cb60-4365-b419-14f7b80afa4c', true);

insert into PROPERTY (id, created_at, address, name, owner_id) values ('b838d2d8-35e7-4592-b3c8-fb99edc8a962', CURRENT_TIMESTAMP, 'Adresa', 'Vikendica', 'e0e389ee-ac19-4ad7-97a9-4c956935bd15');
insert into device (id, created_at, name, activated, value, property_id, read_period, message_regex, device_type, attack) values ('60775bb4-fe74-49d6-8cc8-f12d489d5c1a', CURRENT_TIMESTAMP, 'Ulaz', false, null, 'b838d2d8-35e7-4592-b3c8-fb99edc8a962', 1, '.*.opened', 'DOOR', false);
insert into device (id, created_at, name, activated, value, property_id, read_period, message_regex, device_type, attack) values ('f37989f3-c5dd-4783-822e-6281fd5db13f', CURRENT_TIMESTAMP, 'Dnevna soba', false, null, 'b838d2d8-35e7-4592-b3c8-fb99edc8a962', 1, '.*', 'LIGHT', false);
insert into device (id, created_at, name, activated, value, property_id, read_period, message_regex, device_type, attack) values ('abf80778-901c-42c7-87fd-db2149efaac6', CURRENT_TIMESTAMP, 'Dvoriste', false, null, 'b838d2d8-35e7-4592-b3c8-fb99edc8a962', 1, '.*.', 'CAMERA', false);
insert into property_members (property_id, members_id) values ('b838d2d8-35e7-4592-b3c8-fb99edc8a962', 'e0e389ee-ac19-4ad7-97a9-4c956935bd15');

insert into device (id, created_at, name, activated, value, property_id, read_period, message_regex, device_type, attack) values ('666ed3a7-277b-403d-8d7b-4c4f7c427260', CURRENT_TIMESTAMP, 'Klima', null, null, 'b838d2d8-35e7-4592-b3c8-fb99edc8a962', 1, '.*.', 'THERMOMETER', false);
insert into device (id, created_at, name, activated, value, property_id, read_period, message_regex, device_type, attack) values ('7b3e7182-d732-474a-9ffe-8cebaca697ae', CURRENT_TIMESTAMP, 'Bojler', null, null, 'b838d2d8-35e7-4592-b3c8-fb99edc8a962', 1, '.*.', 'BAROMETER', false);


insert into extension (id, CREATED_AT, name) values ('033eca81-61ee-43e3-bfbf-7463063976b1', CURRENT_TIMESTAMP, 'Key Usage');
insert into extension (id, CREATED_AT, name) values ('5737e19b-0074-47b8-a3e1-52794895a24d', CURRENT_TIMESTAMP, 'Extended Key Usage');
insert into capabilities (id, CREATED_AT, name, extension_id) values ('16c83a6a-c56c-4e62-9543-dbebfd3bea0c', CURRENT_TIMESTAMP, 'Signature', '033eca81-61ee-43e3-bfbf-7463063976b1');
insert into capabilities (id, CREATED_AT, name, extension_id) values ('4bd6841c-ea4e-426b-994a-b19f4a3d6063', CURRENT_TIMESTAMP, 'Non Repudiation', '033eca81-61ee-43e3-bfbf-7463063976b1');
insert into capabilities (id, CREATED_AT, name, extension_id) values ('c0327335-5307-46d3-948c-1c5ba129dc91', CURRENT_TIMESTAMP, 'Encipher Only', '033eca81-61ee-43e3-bfbf-7463063976b1');
insert into capabilities (id, CREATED_AT, name, extension_id) values ('aba0182c-c1ed-4dc1-bf61-2892c4011e9f', CURRENT_TIMESTAMP, 'Key Agreement', '033eca81-61ee-43e3-bfbf-7463063976b1');
insert into capabilities (id, CREATED_AT, name, extension_id) values ('3f1d4d2b-568f-469a-ae1e-3c902131a3e5', CURRENT_TIMESTAMP, 'Key Encipherment', '033eca81-61ee-43e3-bfbf-7463063976b1');

insert into capabilities (id, CREATED_AT, name, extension_id) values ('74fe44b4-9398-478b-b284-f6bf7df7e28e', CURRENT_TIMESTAMP, 'Email Protection', '5737e19b-0074-47b8-a3e1-52794895a24d');
insert into capabilities (id, CREATED_AT, name, extension_id) values ('30c70ea7-14f1-4b86-9088-8a0a9db376a0', CURRENT_TIMESTAMP, 'SSL Client Authentication', '5737e19b-0074-47b8-a3e1-52794895a24d');
insert into capabilities (id, CREATED_AT, name, extension_id) values ('d364ca60-40e1-4d5a-9802-378885a8e308', CURRENT_TIMESTAMP, 'Code Signing', '5737e19b-0074-47b8-a3e1-52794895a24d');
insert into capabilities (id, CREATED_AT, name, extension_id) values ('86a3d0d3-abdf-4095-95ed-f38ee51b3639', CURRENT_TIMESTAMP, 'Any', '5737e19b-0074-47b8-a3e1-52794895a24d');

insert into certificate_type (id, CREATED_AT, name) values ('ed1d18d6-3b33-495e-9840-64e83e144e3a', CURRENT_TIMESTAMP, 'S/MIME (Email)');
insert into certificate_type_capability (certificate_type_id, capability_id) values ('ed1d18d6-3b33-495e-9840-64e83e144e3a', '16c83a6a-c56c-4e62-9543-dbebfd3bea0c');
insert into certificate_type_capability (certificate_type_id, capability_id) values ('ed1d18d6-3b33-495e-9840-64e83e144e3a', '74fe44b4-9398-478b-b284-f6bf7df7e28e');
insert into certificate_type_capability (certificate_type_id, capability_id) values ('ed1d18d6-3b33-495e-9840-64e83e144e3a', '3f1d4d2b-568f-469a-ae1e-3c902131a3e5');


insert into certificate_type (id, CREATED_AT, name) values ('18ffc74f-81c2-4833-a5f4-ae7f2017b4cf', CURRENT_TIMESTAMP, 'SSL Client');
insert into certificate_type_capability (certificate_type_id, capability_id) values ('18ffc74f-81c2-4833-a5f4-ae7f2017b4cf', '16c83a6a-c56c-4e62-9543-dbebfd3bea0c');
insert into certificate_type_capability (certificate_type_id, capability_id) values ('18ffc74f-81c2-4833-a5f4-ae7f2017b4cf', '30c70ea7-14f1-4b86-9088-8a0a9db376a0');