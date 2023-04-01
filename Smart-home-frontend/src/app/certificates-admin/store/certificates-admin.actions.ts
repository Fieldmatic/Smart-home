import { createAction, props, union } from '@ngrx/store';
import { CSR } from '../model/CSR.model';
import { CertificateType } from '../model/certificate-type.model';
import { Extension } from '../model/extension.model';
import { Capabilities } from '../model/capabilities.model';
import { Certificate } from '../model/certificate.model';

export const get_pending_csrs = createAction(
  '[Certificates Admin] Get Pending CSRs'
);

export const set_csrs = createAction(
  '[Certificates Admin] Set CSRs',
  props<{ csrs: CSR[] }>()
);

export const reject_csr = createAction(
  '[Certificates Admin] Reject CSR',
  props<{ id: string; reason: string }>()
);

export const reject_csr_success = createAction(
  '[Certificates Admin] Reject CSR Success',
  props<{ id: string }>()
);

export const get_certificate_types = createAction(
  '[Certificates Admin] Get Certificate Types'
);

export const set_certificate_types = createAction(
  '[Certificates Admin] Set Certificate Types',
  props<{ certificateTypes: CertificateType[] }>()
);

export const get_certificate_extensions = createAction(
  '[Certificates Admin] Get Certificate Extensions'
);

export const set_certificate_extensions = createAction(
  '[Certificates Admin] Set Certificate Extensions',
  props<{ certificateExtensions: Extension[] }>()
);

export const create_certificate = createAction(
  '[Certificates Admin] Create Certificate',
  props<{
    capabilities: Capabilities[];
    start: Date;
    end: Date;
    csrId: string;
    serialNumber: string;
    caAlias: string;
  }>()
);

export const create_certificate_success = createAction(
  '[Certificates Admin] Create Certificate Success'
);

export const get_certificates = createAction(
  '[Certificates Admin] Get Certificates'
);

export const set_certificates = createAction(
  '[Certificates Admin] Set Certificates',
  props<{ certificates: Certificate[] }>()
);

export const delete_certificate = createAction(
  '[Certificates Admin] Delete Certificate',
  props<{ alias: string }>()
);

export const delete_certificate_success = createAction(
  '[Certificates Admin] Delete Certificate Success',
  props<{ alias: string }>()
);

const all = union({
  get_pending_csrs,
  set_csrs,
  reject_csr,
  reject_csr_success,
  get_certificate_types,
  set_certificate_types,
  get_certificate_extensions,
  set_certificate_extensions,
  create_certificate,
  create_certificate_success,
  get_certificates,
  set_certificates,
  delete_certificate,
  delete_certificate_success,
});

export type CertificatesAdminActionsUnion = typeof all;
