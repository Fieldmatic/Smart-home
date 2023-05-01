insert into PUBLIC.ROLE (ID, NAME) values ('9c4cf245-67c7-466e-9ff2-9878acef91cb', 'ADMIN');
insert into PUBLIC.ROLE (ID, NAME) values ('faf8829f-cb60-4365-b419-14f7b80afa4c', 'OWNER');
insert into PUBLIC.ROLE (ID, NAME) values ('ebc16e4e-64e1-4b35-946a-0885ab058453', 'TENANT');
insert into ROLE_PERMISSIONS (ROLE_ID, PERMISSIONS) values ('9c4cf245-67c7-466e-9ff2-9878acef91cb', 'SEND_CSR_REQUEST');
insert into ROLE_PERMISSIONS (ROLE_ID, PERMISSIONS) values ('9c4cf245-67c7-466e-9ff2-9878acef91cb', 'CSR_MANIPULATION');
insert into ROLE_PERMISSIONS (ROLE_ID, PERMISSIONS) values ('9c4cf245-67c7-466e-9ff2-9878acef91cb', 'USER_MANIPULATION');
insert into ROLE_PERMISSIONS (ROLE_ID, PERMISSIONS) values ('9c4cf245-67c7-466e-9ff2-9878acef91cb', 'CERTIFICATE_MANIPULATION');
insert into ROLE_PERMISSIONS (ROLE_ID, PERMISSIONS) values ('9c4cf245-67c7-466e-9ff2-9878acef91cb', 'PROPERTY_MANIPULATION');
insert into ROLE_PERMISSIONS (ROLE_ID, PERMISSIONS) values ('faf8829f-cb60-4365-b419-14f7b80afa4c', 'SEND_CSR_REQUEST');
insert into ROLE_PERMISSIONS (ROLE_ID, PERMISSIONS) values ('ebc16e4e-64e1-4b35-946a-0885ab058453', 'SEND_CSR_REQUEST');
insert into PUBLIC.ADMIN (ID, EMAIL, PASSWORD_HASH, ROLE_ID, VERIFIED) values ('201ab15f-3b22-4659-b839-235b9e6a728e', 'milicftn@gmail.com', '$2a$10$5tykbOhp3Uo2QrY2t3/uCOmsuwvEZl4KgEgZgN3At6JB3HQX.Z75y', '9c4cf245-67c7-466e-9ff2-9878acef91cb', true);
insert into PUBLIC.ADMIN (ID, EMAIL, PASSWORD_HASH, ROLE_ID, VERIFIED) values ('0d6df743-a243-4e80-86a0-ef5710a57e4c', 'istevanovic3112@gmail.com', '$2a$10$5tykbOhp3Uo2QrY2t3/uCOmsuwvEZl4KgEgZgN3At6JB3HQX.Z75y', '9c4cf245-67c7-466e-9ff2-9878acef91cb', true);
insert into PUBLIC.ADMIN (ID, EMAIL, PASSWORD_HASH, ROLE_ID, VERIFIED) values ('83aebc30-729c-4923-8aa1-ef71bb1e5247', 'prijovic.uros13@gmail.com', '$2a$10$5tykbOhp3Uo2QrY2t3/uCOmsuwvEZl4KgEgZgN3At6JB3HQX.Z75y', '9c4cf245-67c7-466e-9ff2-9878acef91cb', true);

insert into extension (id, name) values ('033eca81-61ee-43e3-bfbf-7463063976b1', 'Key Usage');
insert into extension (id, name) values ('5737e19b-0074-47b8-a3e1-52794895a24d', 'Extended Key Usage');
insert into capabilities (id, name, extension_id) values ('16c83a6a-c56c-4e62-9543-dbebfd3bea0c', 'Signature', '033eca81-61ee-43e3-bfbf-7463063976b1');
insert into capabilities (id, name, extension_id) values ('4bd6841c-ea4e-426b-994a-b19f4a3d6063', 'Non Repudiation', '033eca81-61ee-43e3-bfbf-7463063976b1');
insert into capabilities (id, name, extension_id) values ('c0327335-5307-46d3-948c-1c5ba129dc91', 'Encipher Only', '033eca81-61ee-43e3-bfbf-7463063976b1');
insert into capabilities (id, name, extension_id) values ('aba0182c-c1ed-4dc1-bf61-2892c4011e9f', 'Key Agreement', '033eca81-61ee-43e3-bfbf-7463063976b1');
insert into capabilities (id, name, extension_id) values ('3f1d4d2b-568f-469a-ae1e-3c902131a3e5', 'Key Encipherment', '033eca81-61ee-43e3-bfbf-7463063976b1');

insert into capabilities (id, name, extension_id) values ('74fe44b4-9398-478b-b284-f6bf7df7e28e', 'Email Protection', '5737e19b-0074-47b8-a3e1-52794895a24d');
insert into capabilities (id, name, extension_id) values ('30c70ea7-14f1-4b86-9088-8a0a9db376a0', 'SSL Client Authentication', '5737e19b-0074-47b8-a3e1-52794895a24d');
insert into capabilities (id, name, extension_id) values ('d364ca60-40e1-4d5a-9802-378885a8e308', 'Code Signing', '5737e19b-0074-47b8-a3e1-52794895a24d');
insert into capabilities (id, name, extension_id) values ('86a3d0d3-abdf-4095-95ed-f38ee51b3639', 'Any', '5737e19b-0074-47b8-a3e1-52794895a24d');

insert into certificate_type (id, name) values ('ed1d18d6-3b33-495e-9840-64e83e144e3a', 'S/MIME (Email)');
insert into certificate_type_capability (certificate_type_id, capability_id) values ('ed1d18d6-3b33-495e-9840-64e83e144e3a', '16c83a6a-c56c-4e62-9543-dbebfd3bea0c');
insert into certificate_type_capability (certificate_type_id, capability_id) values ('ed1d18d6-3b33-495e-9840-64e83e144e3a', '74fe44b4-9398-478b-b284-f6bf7df7e28e');
insert into certificate_type_capability (certificate_type_id, capability_id) values ('ed1d18d6-3b33-495e-9840-64e83e144e3a', '3f1d4d2b-568f-469a-ae1e-3c902131a3e5');


insert into certificate_type (id, name) values ('18ffc74f-81c2-4833-a5f4-ae7f2017b4cf', 'SSL Client');
insert into certificate_type_capability (certificate_type_id, capability_id) values ('18ffc74f-81c2-4833-a5f4-ae7f2017b4cf', '16c83a6a-c56c-4e62-9543-dbebfd3bea0c');
insert into certificate_type_capability (certificate_type_id, capability_id) values ('18ffc74f-81c2-4833-a5f4-ae7f2017b4cf', '30c70ea7-14f1-4b86-9088-8a0a9db376a0');